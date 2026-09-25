/*
 * PlayerProfileTest.java -- unit tests for the Lesson 5 exercise
 * ICS 4U0 - Lesson 5 Exercise: Player Profile
 *
 * These tests run PlayerProfile.main() exactly as it will be graded: they
 * feed it the input lines it expects on System.in and check the lines it
 * prints to System.out. You don't need to change this file -- just run the
 * tests (see README.md) and fix PlayerProfile.java until they all pass.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerProfileTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private Locale originalLocale;

    @BeforeEach
    public void redirectIoAndPinLocale() {
        originalIn = System.in;
        originalOut = System.out;
        originalLocale = Locale.getDefault();
        // printf("%.2f") follows the default locale -- on a machine that uses
        // a comma for decimals, output like "9,00" would fail the exercise
        // for reasons that have nothing to do with your code, so every test
        // pins the locale before calling main().
        Locale.setDefault(Locale.CANADA);
    }

    @AfterEach
    public void restoreIoAndLocale() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        Locale.setDefault(originalLocale);
    }

    /**
     * Feeds {@code input} to PlayerProfile.main() on System.in and returns
     * everything it printed to System.out.
     */
    private String run(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));

        PlayerProfile.main(new String[0]);

        return captured.toString(StandardCharsets.UTF_8);
    }

    /**
     * Compares output line by line, trimming trailing whitespace on each
     * line and ignoring one blank line at the very end (println's final
     * newline). Everything else -- including the space after each colon --
     * is compared exactly.
     */
    private void assertOutputEquals(String expected, String actual) {
        String[] expectedLines = expected.stripTrailing().split("\n", -1);
        String[] actualLines = actual.stripTrailing().split("\n", -1);

        assertEquals(expectedLines.length, actualLines.length,
                "Expected " + expectedLines.length + " line(s) of output, got " + actualLines.length
                        + ".\n--- expected ---\n" + expected + "--- actual ---\n" + actual);

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(expectedLines[i].stripTrailing(), actualLines[i].stripTrailing(),
                    "Line " + (i + 1) + " didn't match.\n--- expected ---\n" + expected
                            + "--- actual ---\n" + actual);
        }
    }

    @Test
    public void exampleFromReadme() {
        String input = "diego\nRAMIREZ\n81\n65\n";
        String expected = "Username: Dramirez\n"
                + "Full name length: 12\n"
                + "Score difference: 16\n"
                + "Power rating: 9.00\n"
                + "Bonus points: 256.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void usernameCasingIsNormalizedBothWays() {
        // Opposite casing from the README example: first name already
        // uppercase, last name already lowercase. Catches a username built
        // without toUpperCase()/toLowerCase() at all.
        String input = "ANA\nsmith\n40\n40\n";
        String expected = "Username: Asmith\n"
                + "Full name length: 8\n"
                + "Score difference: 0\n"
                + "Power rating: 6.32\n"
                + "Bonus points: 0.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void scoreDifferenceUsesAbsoluteValueWhenRivalIsHigher() {
        // rivalScore > score. Without Math.abs(), scoreDifference comes out
        // negative instead of 20.
        String input = "sam\njones\n50\n70\n";
        String expected = "Username: Sjones\n"
                + "Full name length: 8\n"
                + "Score difference: 20\n"
                + "Power rating: 7.07\n"
                + "Bonus points: 400.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void mathPowArgumentsAreNotReversed() {
        // scoreDifference is 10, so Math.pow(scoreDifference, 2) is 100.00.
        // A solution that reverses the arguments computes Math.pow(2, 10),
        // which prints 1024.00 instead.
        String input = "lee\nchen\n30\n40\n";
        String expected = "Username: Lchen\n"
                + "Full name length: 7\n"
                + "Score difference: 10\n"
                + "Power rating: 5.48\n"
                + "Bonus points: 100.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void nameLengthCombinesBothNames() {
        String input = "maximilian\nng\n90\n81\n";
        String expected = "Username: Mng\n"
                + "Full name length: 12\n"
                + "Score difference: 9\n"
                + "Power rating: 9.49\n"
                + "Bonus points: 81.00\n";
        assertOutputEquals(expected, run(input));
    }
}
