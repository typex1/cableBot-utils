import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");

        System.out.print("Enter an index: ");
        int index = scanner.nextInt();

        System.out.print("Enter a sentence: ");
        String sentence = scanner.next();

        System.out.println("\nYour sentence: " + sentence);
        System.out.println("Your index: " + index);
    }
}
