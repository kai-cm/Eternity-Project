import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        
        DisplayMenu();
        System.out.print("Please choose an operation > ");
        int operation = scanner.nextInt();
        while(operation < 1 || operation > 8)
        {
            System.out.println("Please choose a valid operation between 1 and 8");
            operation = scanner.nextInt();
        }

        switch (operation) {
            case 1:
                // insert function here
                break;
            case 2:
                // insert function here
                break;
            case 3:
                // insert function here
                break;
            case 4:
                // insert function here
                break;
            case 5:
                MAD.MADfunction();
                break;
            case 6:
                // insert function here
                break;
            case 7:
                // insert function here
                break;
            case 8:
                // insert function here
                break;
            default:
                break;
        }

        scanner.close();
    }

    private static void DisplayMenu() {
        System.out.print(System.lineSeparator());
        System.out.println("Welcome to ETERNITY");
        System.out.println("1. sin(x) XOR cos(x)");
        System.out.println("2. 10\u02e3 XOR π\u02e3");
        System.out.println("3. log\u2091(x) = ln(x) XOR log\u2081\u2080(x)");
        System.out.println("4. e\u02e3 XOR a\u02e3");
        System.out.println("5. MAD (Mean Absolute Deviation)");
        System.out.println("6. σ (Standard Deviation)");
        System.out.println("7. sinh(x) XOR cosh(x) XOR tanh(x)");
        System.out.println("8. x\u02b8");
    }
}