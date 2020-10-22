import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculate {
    public static void calculate(GuildMessageReceivedEvent event) {
        //Set message content to a variable
        String message = event.getMessage().getContentRaw();

        //Remove the ` prefix
        message = message.substring(1);

        //Makes sure that no letters or special characters are used
        if (!message.matches("^[1234567890*x+-\\/\\s]+$")) {
            event.getChannel().sendMessage("That's not a valid calculation.").queue();
            return;
        }

        //Remove all spaces from the message
        message = message.replaceAll("\\s", "");

        //Since numbers can be more than double digits, log the number
        String currentNumber = "";

        //Calculation loop starts here. Will loop through every character in message
        for (int i = 0; i < message.length(); i++) {

            //"Upgrade" from primitive type to whatever this is
            Character currentChar = message.charAt(0);

            //if we are on the first character
            if (i == 0) {
                //And if the character is not a number
                if (!currentChar.toString().matches("[1234567890]")) {
                    event.getChannel().sendMessage("Your calculation must start with a number.").queue();
                    return;
                }
            }

            //If it is a calculation
            if (currentChar.toString().matches("[\\/*-+x]")) {
                //Flag as addition, subtraction, mult, div. Then perform calculation outside of for loop
                return;
            }

            //If it is a number, log the number
            currentNumber = currentNumber + currentChar.toString();

        }

    }
}
