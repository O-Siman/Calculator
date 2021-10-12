import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                \u001B[4mUsage:\u001B[0m
                +\t\t\t: Addition
                -\t\t\t: Subtraction
                * (or x)\t: Multiplication
                /\t\t\t: Division
                r (after #)\t: Square root
                
                \u001B[4mExample:\u001B[0m
                5 + 9r / 2
                Result: \u001B[1m4.0\u001B[0m
                """);
        System.out.println("What do you want to calculate?");
        Scanner s = new Scanner(System.in);
        String input;
        while (true) {
            input = s.nextLine();
            Calculate.calculate(input);
        }
    }
}
