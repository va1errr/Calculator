import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Calculator {
    public static boolean quit = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printGreetings();
        String line;
        do {
            line = sc.nextLine();
            handleLine(line);
        } while (!quit);
    }

    public static Operation defineOperation(char op) {
        return switch (op) {
            case '+' -> new Addition();
            case '-' -> new Subtraction();
            case '*' -> new Multiplication();
            case '/' -> new Division();
            case '^' -> new Power();
            case 's' -> new SquareRoot();
            case '!' -> new Factorial();
            default -> new Operation();
        };
    }

    public static void handleLine(String line) {
        if (line.isEmpty()) {
            System.out.println("You did not enter anything!");
            return;
        }
        if (line.charAt(0) == 'q') {
            System.out.println("You quited!");
            quit = true;
            return;
        }
        if (line.charAt(0) == 'l') {
            printHelp();
            return;
        }
        Scanner sc = new Scanner(line);
        try {
            double firstNumber = sc.nextDouble();
            char op = sc.next(".").charAt(0);
            if (op != '+' && op != '-' && op != '*' && op != '/' && op != '^' && op != 's' && op != '!') {
                System.out.println("No such operator!");
                return;
            }
            Operation operation = defineOperation(op);
            if (!(operation instanceof SquareRoot || operation instanceof Factorial)) {
                try {
                    double secondNumber = sc.nextDouble();
                    Double result = operation.calculate(firstNumber, secondNumber);
                    if (result == null) {
                        System.out.println("Invalid input!");
                        return;
                    }
                    System.out.println("= " + result);
                }
                catch (NoSuchElementException _) {
                    System.out.println("You should provide second number for this kind of operation!");
                }
            }
            else {
                try {
                    sc.nextDouble();
                    System.out.println("There is no need for the second number!");
                }
                catch (NoSuchElementException _) {  }
                catch (Exception _) {
                    System.out.println("Invalid input, but it does not affect anything!");
                }
                Double result = operation.calculate(firstNumber);
                if (result == null) {
                    System.out.println("Invalid input!");
                    return;
                }
                System.out.println("= " + result);
            }
        }
        catch (Exception _) {
            System.out.println("Invalid input!");
            return;
        }
    }

    public static void printHelp() {
        System.out.println("List of all available operations: ");
        System.out.println("'+' - addition, ");
        System.out.println("'-' - subtraction, ");
        System.out.println("'*' - multiplication, ");
        System.out.println("'/' - division, ");
        System.out.println("'s' - square root, ");
        System.out.println("'^' - power, ");
        System.out.println("'!' - factorial, ");
        System.out.println("You can quit whenever you want by writing 'q' as first number.");
        System.out.println("To show help, write 'l' as first number.");
        System.out.println("""
                You should write in this format:\s
                '{number1} {operator} {number2}'.
                """);
    }

    public static void printGreetings() {
        System.out.println("Hello! \nThis is Console Calculator App. " +
                "Here you can apply some operations on numbers and see the result.");
        printHelp();
        System.out.println("Let's start!\n");
    }
}
