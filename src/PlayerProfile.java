/*
 * Name:
 * Description:
 * Created by:
 * Last edited:
 */
import java.util.Scanner;

public class PlayerProfile {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // read the player's first and last name (one per line)
        String firstName = input.nextLine();


        // read score and rivalScore (int)


        // build username -- first letter of firstName uppercased, plus lastName lowercased (substring, toUpperCase, toLowerCase)


        // calculate nameLength as firstName.length() + lastName.length()


        // calculate scoreDifference as Math.abs(score - rivalScore)


        // calculate powerRating as Math.sqrt(score)


        // calculate bonusPoints as Math.pow(scoreDifference, 2)


        // print the five output lines (README.md); powerRating and bonusPoints via printf %.2f

    }
}
