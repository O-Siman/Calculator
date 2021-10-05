import java.util.ArrayList;

public class Calculate {
    public static void calculate(String message) {
        //Makes sure that no letters or special characters are used
        if (!message.matches("^[1234567890*x+\\-\\/.\\s]+$")) {
            System.out.println("**Invalid Syntax:** A calculation can only include numbers and operations (`+`, `-`, `*` or `x`, `/`).");
            return;
        }

        //Remove all spaces from the message
        message = message.replaceAll("\\s", "");

        //Since numbers can be more than double digits, log the number
        String currentNumber = "";

        //List of all numbers in the operation
        ArrayList<Double> listOfNumbers = new ArrayList<>();

        //List of all operations
        ArrayList<Character> listOfOperations = new ArrayList<>();

        //In case there are two operations in a row
        boolean previousCharIsOperation = false;

        //Parsing loop starts here. Will loop through every character in message
        for (int i = 0; i < message.length(); i++) {

            //"Upgrade" from primitive type to whatever this is
            Character currentChar = message.charAt(i);

            //if we are on the first character
            if (i == 0) {
                //And if the character is not a number
                if (!currentChar.toString().matches("[1234567890.]")) {
                    System.out.println("**Invalid syntax:** Your calculation must start with a number.");
                    return;
                }
            }

            //If it is a calculation
            if (currentChar.toString().matches("[\\/*\\-+x]")) {
                //If this is the second operation in a row
                if (previousCharIsOperation) {
                    System.out.println("**Invalid syntax:** You can't have two operations in a row!");
                    return;
                }
                //Only reaches here if it is a valid operation

                //Set to true because this char is an operation
                previousCharIsOperation = true;

                //Log completed number in array
                listOfNumbers.add(Double.parseDouble(currentNumber));
                //Reset currentNumber
                currentNumber = "";
                //Log operation in array
                listOfOperations.add(currentChar);

                //Next character
                continue;
            }

            //Mark as false, because this char is a number
            previousCharIsOperation = false;

            //If it is a number, log the number
            currentNumber = currentNumber + currentChar.toString();

        }

        //Add final number to list
        try {
            listOfNumbers.add(Double.parseDouble(currentNumber));
        } catch (Exception e) {
            //If there too many operations vs numbers (such as in example: 8 + 2 - 3 *)
            System.out.println("**Invalid Syntax:** You have too many operations.");
            return;
        }

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

        /*Parse all operations to integer IDs
        IDs for Math
        Addition (+) = 0
        Subtraction (-) = 1
        Multiplication (* or x) = 2
        Division (/) = 3
         */

        //Make a new ArrayList that will hold IDs of the operation instead of characters
        ArrayList<Integer> listOfOperationIDs = new ArrayList<>();

        //Loop through all characters in listOfOperations
        for (int i = 0; i < listOfOperations.size(); i++) {
            switch (listOfOperations.get(i)) {
                case '+': listOfOperationIDs.add(0); break;
                case '-': listOfOperationIDs.add(1); break;
                case '*': listOfOperationIDs.add(2); break;
                case 'x': listOfOperationIDs.add(2); break;
                case '/': listOfOperationIDs.add(3); break;
            }
        }

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
                        System.out.println("**Invalid Calculation:** Expression involved dividing by zero");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
                }
            }

            firstNumber = result;
        }

        System.out.println("Result: **" + firstNumber + "**");
//        System.out.println("Result is " + firstNumber);
    }
}
