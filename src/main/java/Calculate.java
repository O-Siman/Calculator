import java.util.ArrayList;

public class Calculate {
    public static void calculate(String message) {
        // Makes sure that no letters or special characters are used
        if (!message.matches("^[1234567890*x+\\-\\/r.\\s]+$")) {
            System.err.println("Invalid syntax: A calculation can only include numbers and operations (+, -, * or x, /, r).");
            return;
        }

        // Remove all spaces from the message
        message = message.replaceAll("\\s", "");

        ArrayList<ArrayList> parsed = MessageParser.parseCalculation(message);
        // Numbers is first, then operations
        // List of all numbers in the operation
        ArrayList<Double> listOfNumbers = parsed.get(0);
        // List of all operations
        ArrayList<Character> listOfOperations = parsed.get(1);

//        //Reset currentNumber (not necessary)
//        currentNumber = "";
/*

        System.out.println("List of all numbers in this equation:");
        for (int i = 0; i < listOfNumbers.size(); i++) {
            System.out.println(listOfNumbers.get(i).toString());
        }

        System.out.println("List of all operations in this equation:");
        for (int i = 0; i < listOfOperations.size(); i++) {
            System.out.println(listOfOperations.get(i).toString());
        }
*/

        /*
        Parse all operations to integer IDs
        IDs for Math
        Addition (+) = 0
        Subtraction (-) = 1
        Multiplication (* or x) = 2
        Division (/) = 3
         */

        // Make a new ArrayList that will hold IDs of the operation instead of characters
        ArrayList<Integer> listOfOperationIDs = OperationConverter.operationsToIds(listOfOperations);

        //Start calculating here

        double firstNumber;
        double secondNumber;
        int operation;

        firstNumber = listOfNumbers.get(0);

        //Order of events that must happen: Set second number, set operation, calculate set firstNumber to result.
        for (int i = 0; i < listOfNumbers.size() - 1; i++) {
            double result = 0;
            secondNumber = listOfNumbers.get(i + 1);
            operation = listOfOperationIDs.get(i);

            //Check what operation it is
            switch (operation) {
                //If it's addition, add the numbers
                case 0: result = firstNumber + secondNumber; break;
                case 1: result = firstNumber - secondNumber; break;
                case 2: result = firstNumber * secondNumber; break;
                //Division, so we must make sure you don't divide by 0
                case 3: {
                    if (secondNumber == 0) {
                        System.err.println("Invalid Calculation: Expression involved dividing by zero");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
                }
            }

            firstNumber = result;
        }

        System.out.println("Result: " + firstNumber);
//        System.out.println("Result is " + firstNumber);
    }
}
