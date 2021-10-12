import java.util.ArrayList;

public class MessageParser {
    public static ArrayList<ArrayList> parseCalculation(String message) {
        // List of all numbers in the operation
        ArrayList<Double> listOfNumbers = new ArrayList<>();

        // List of all operations
        ArrayList<Character> listOfOperations = new ArrayList<>();

        // Since numbers can be more than a single character, log the numbers
        StringBuilder currentNumber = new StringBuilder();

        // In case there are two operations in a row
        boolean previousCharIsOperation = false;
        // Ensure that we do an operation after a square root
        boolean operationMandatory = false;

        // Parsing loop starts here. Will loop through every character in message
        for (int i = 0; i < message.length(); i++) {

            // "Upgrade" from primitive type
            Character currentChar = message.charAt(i);

            // If we are on the first character
            if (i == 0) {
                //And if the character is not a number
                if (!currentChar.toString().matches("[1234567890.]")) {
                    System.err.println("\u001B[31mInvalid syntax\u001B[0m: Your calculation must start with a number.");
                    return null;
                }
            }

            // If it is square root
            if (currentChar.equals('r')) {
                // We don't check operationMandatory because we can square root twice in a row
                // Get the square root of our current number
                try {
                    double squareRoot = Math.sqrt(Double.parseDouble(currentNumber.toString()));
//                    // Add it to our list
//                    listOfNumbers.add(squareRoot);
                    // Set currentNumber to our number
                    currentNumber = new StringBuilder().append(squareRoot);
                    // Request next character to be operation
                    operationMandatory = true;
                } catch (NumberFormatException e) {
                    System.err.println("\u001B[31mInvalid syntax\u001B[0m: You must square root a number!");
                    return null;
                }

                continue;
            }

            // If it is a calculation
            if (currentChar.toString().matches("[\\/*\\-+x]")) {
                // If this is the second operation in a row
                if (previousCharIsOperation) {
                    System.err.println("\u001B[31mInvalid syntax\u001B[0m: You can't have two operations in a row!");
                    return null;
                }
                // Only reaches here if it is a valid operation

                // Set to true because this char is an operation
                previousCharIsOperation = true;

                // Log completed number in array
                listOfNumbers.add(Double.parseDouble(currentNumber.toString()));
                // Reset currentNumber
                currentNumber = new StringBuilder();
                // Log operation in array
                listOfOperations.add(currentChar);
                // Operation was just used so not mandatory anymore
                operationMandatory = false;

                // Next character
                continue;
            }

            if (operationMandatory) {
                System.err.println("\u001B[31mInvalid syntax\u001B[0m: You must perform an operation after a square root!");
                return null;
            }

            // Mark as false, because this char is a number
            previousCharIsOperation = false;

            // If it is a number, log the number
            currentNumber.append(currentChar);

        }

        // Add final number to list
        try {
            listOfNumbers.add(Double.parseDouble(currentNumber.toString()));
        } catch (NumberFormatException e) {
            // If there are too many operations vs numbers (such as in: 8 + 2 - 3 *)
            System.err.println("\u001B[31mInvalid syntax\u001B[0m: You have too many operations.");
            return null;
        }

        ArrayList<ArrayList> toReturn = new ArrayList<>();
        toReturn.add(listOfNumbers);
        toReturn.add(listOfOperations);
        return toReturn;
    }
}
