/*
 * Name:        Lesson 5: String Methods and Math Functions (StringMathDemo.java)
 * Description: A runnable tour of String concatenation, core String methods,
 *              equals vs ==, compareTo, and the Math class.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-09-11
 */

public class StringMathDemo {

    public static void main(String[] args) {
        // below we are calling methods (functions) that are defined later in this file.
        concatenationGotchas();
        stringMethodsBasics();
        comparingStrings();
        caseAndSearching();
        mathClassBasics();
        randomNumbers();
    }

    // method to demonstrate the + operator mixing strings and numbers
    public static void concatenationGotchas() {
        System.out.println();
        System.out.println("=== 1. concatenation isn't always concatenation ===");

        System.out.println("Total: " + 3 + 2);     // <-- "Total: 32", evaluated left to right
        System.out.println("Total: " + (3 + 2));   // "Total: 5", parentheses force the addition first

        // Strings are immutable: toUpperCase() returns a new String, it doesn't change word.
        String word = "hello";
        word.toUpperCase();
        System.out.println(word);       // still "hello"
        word = word.toUpperCase();
        System.out.println(word);       // now "HELLO"
    }

    // method to demonstrate length, charAt, and substring
    public static void stringMethodsBasics() {
        System.out.println();
        System.out.println("=== 2. string methods ===");

        String province = "Ontario";
        System.out.println(province.length());        // 7
        System.out.println(province.charAt(0));        // 'O'
        System.out.println(province.substring(0, 3));  // "Ont"
        System.out.println(province.substring(3));     // "ario"

        // Out-of-range index would throw StringIndexOutOfBoundsException:
        // province.charAt(100);

        // Swap the characters at positions i and j -- build a new String,
        // since the original can't be edited in place.
        String word = "cta";
        int i = 0, j = 2;
        String swapped = word.substring(0, i) + word.charAt(j)
                        + word.substring(i + 1, j) + word.charAt(i)
                        + word.substring(j + 1);
        System.out.println(word + " -> " + swapped);   // cta -> atc
    }

    // method to demonstrate equals vs == and compareTo
    public static void comparingStrings() {
        System.out.println();
        System.out.println("=== 3. comparing strings: equals, not == ===");

        String a = "cat";
        String b = "cat";
        String c = new String("cat");

        System.out.println("a == b: " + (a == b));               // true -- literals are reused, this is a trap
        System.out.println("a == c: " + (a == c));                // false -- c is a distinct object
        System.out.println("a.equals(c): " + a.equals(c));        // true -- equals compares content, always

        // <-- the bug: a value that came from input is never == to a literal,
        // even when the characters match exactly.
        String fromInput = new String("cat");   // stands in for text read with Scanner
        System.out.println("fromInput == \"cat\": " + (fromInput == "cat"));           // false
        System.out.println("fromInput.equals(\"cat\"): " + fromInput.equals("cat"));   // true

        System.out.println("CAT".equalsIgnoreCase("cat"));   // true

        System.out.println("apple".compareTo("banana"));   // negative
        System.out.println("banana".compareTo("apple"));   // positive
        System.out.println("apple".compareTo("apple"));    // 0
    }

    // method to demonstrate toUpperCase, toLowerCase, and indexOf
    public static void caseAndSearching() {
        System.out.println();
        System.out.println("=== 4. case and searching ===");

        String s = "Ontario";
        System.out.println(s.toUpperCase());     // "ONTARIO"
        System.out.println(s.toLowerCase());     // "ontario"
        System.out.println(s.indexOf("tar"));    // 2
        System.out.println(s.indexOf("xyz"));    // -1, not found

        // Counting occurrences: search again starting just past the last match.
        String sentence = "the cat sat on the mat";
        int count = 0;
        int pos = sentence.indexOf("at");
        while (pos != -1) {
            count++;
            pos = sentence.indexOf("at", pos + 1);
        }
        System.out.println("occurrences of \"at\": " + count);   // 3
    }

    // method to demonstrate Math class methods
    public static void mathClassBasics() {
        System.out.println();
        System.out.println("=== 5. the Math class ===");

        System.out.println(Math.abs(-7));       // 7
        System.out.println(Math.abs(-3.5));     // 3.5
        System.out.println(Math.pow(2, 10));    // 1024.0 -- pow always returns a double
        System.out.println(Math.sqrt(81));      // 9.0
    }

    // method to demonstrate Math.random() and scaling it into a range
    public static void randomNumbers() {
        System.out.println();
        System.out.println("=== 6. random numbers ===");

        System.out.println(Math.random());   // some double, 0.0 <= x < 1.0

        // A random integer in [min, max], inclusive on both ends.
        int min = 1, max = 6;
        int roll = (int) (Math.random() * (max - min + 1)) + min;
        System.out.println("die roll (1-6): " + roll);
    }
}
