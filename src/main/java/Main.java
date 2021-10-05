import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("What do you want to calculate?");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        Calculate.calculate(input);
    }
}
