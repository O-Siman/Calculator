import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculate {
    public static void calculate(GuildMessageReceivedEvent event) {
        //Set message content to a variable
        String message = event.getMessage().getContentRaw();
        TextChannel channel = event.getChannel();

        //Remove the ` prefix
        message = message.substring(1);

        //Makes sure that no letters or special characters are used
        if (!message.matches("^[1234567890*x+-\\/\\s]+$")) {
            channel.sendMessage("**Invalid Syntax:** A calculation can only include numbers and operations (`+`, `-`, `*` or `x`, `/`).").queue();
            return;
        }

        //Remove all spaces from the message
        message = message.replaceAll("\\s", "");

        //Since numbers can be more than double digits, log the number
        String currentNumber = "";

        //List of all numbers in the operation
        ArrayList<String> listOfNumbers = new ArrayList<>();

        //List of all operations
        ArrayList<Character> listOfOperations = new ArrayList<>();

        //In case there are two operations in a row
        boolean previousCharIsOperation = false;

        //Calculation loop starts here. Will loop through every character in message
        for (int i = 0; i < message.length(); i++) {

            //"Upgrade" from primitive type to whatever this is
            Character currentChar = message.charAt(i);

            //if we are on the first character
            if (i == 0) {
                //And if the character is not a number
                if (!currentChar.toString().matches("[1234567890]")) {
                    channel.sendMessage("**Invalid syntax:** Your calculation must start with a number.").queue();
                    continue;
                }
            }

            //If it is a calculation
            if (currentChar.toString().matches("[\\/*\\-+x]")) {
                //If this is the second operation in a row
                if (previousCharIsOperation) {
                    channel.sendMessage("**Invalid syntax:** You can't have two operations in a row!").queue();
                    continue;
                }
                //Valid operation

                //Set to true because this char is an operation
                previousCharIsOperation = true;

                //Log completed number in array
                listOfNumbers.add(currentNumber);
                //Reset currentNumber
                currentNumber = "";
                //Log operation in array
                listOfOperations.add(currentChar);

                //Flag as addition, subtraction, mult, div. Then perform calculation outside of for loop
                continue;
            }

            //Mark as false, because this char is a number
            previousCharIsOperation = false;

            //If it is a number, log the number
            currentNumber = currentNumber + currentChar.toString();

        }
        //Add final number to list
        listOfNumbers.add(currentNumber);

//        //Reset currentNumber (not necessary)
//        currentNumber = "";

        //TODO: Debug
        channel.sendMessage("List of all numbers in this equation:").queue();
        for (int i = 0; i < listOfNumbers.size(); i++) {
            channel.sendMessage(listOfNumbers.get(i)).queue();
        }

        //TODO: Debug
        channel.sendMessage("List of all operations in this equation:").queue();
        for (int i = 0; i < listOfOperations.size(); i++) {
            channel.sendMessage(listOfOperations.get(i).toString()).queue();
        }

    }
}
