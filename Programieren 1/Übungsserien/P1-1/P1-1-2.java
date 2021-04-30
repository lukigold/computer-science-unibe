
//Lukas Ingold 20-123-998
import java.util.Scanner;

public class Divide {

    // Calculates a^2/b based on values entered by the user

    public static void main(String[] args) {
        int a;
        int b;
        int c = 2;
        double result;
        int remainder;

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the value of a: ");
        a = scan.nextInt();

        System.out.print("Enter the value of b: ");
        b = scan.nextInt();

        result = (double) Math.pow(a, c) / b;
        remainder = (int) Math.pow(a, c) % b;

        System.out.println("The result is: " + result);
        System.out.println("The rounded result is: " + (int) result + " remainder " + remainder);

    }
}