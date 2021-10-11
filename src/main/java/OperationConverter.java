import java.util.ArrayList;

public class OperationConverter {
    public static ArrayList<Integer> operationsToIds(ArrayList<Character> listOfOperations) {
        // Make a new ArrayList that will hold IDs of the operation instead of characters
        ArrayList<Integer> listOfOperationIDs = new ArrayList<>();

        // Loop through all characters in listOfOperations
        for (int i = 0; i < listOfOperations.size(); i++) {
            switch (listOfOperations.get(i)) {
                case '+': listOfOperationIDs.add(0); break;
                case '-': listOfOperationIDs.add(1); break;
                case '*': // Merge with next case
                case 'x': listOfOperationIDs.add(2); break;
                case '/': listOfOperationIDs.add(3); break;
            }
        }

        return listOfOperationIDs;
    }
}
