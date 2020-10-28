import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    public static JDA api;
    public static String token;

    public static void main(String[] args) {
        new GUI();
        token = ReadToken.loadToken();
    }



    public static boolean startBot() throws InterruptedException {

        JDABuilder preBuild = JDABuilder.createDefault(token);
        try {
            api = preBuild.build();
        } catch (Exception e) {
            e.printStackTrace();
            GUI.label.setText("Couldn't login to Discord.");
            System.out.println("Resetting GUI");
            GUI.resetGUI();
            return false;
        }
        api.addEventListener(new Main());
        api.awaitReady();
        return true;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        //If the user running the command is a bot, return
        if (event.getAuthor().isBot())
            return;
        //Set message content to a variable
        String message = event.getMessage().getContentRaw();

        switch (message) {
            //If user does `help
            case "`help":
                Help.helpCommand(event);
                break;
            //If user does `ping
            case "`ping":
                Ping.pingCommand(event);
                break;
            //If the message is something else
            default:
                //If message starts with `
                if (message.charAt(0) == '`') {
                    //If message contains more than one `, return.
                    int tildeCounter = 0;
                    for (int i = 0; i < message.length(); i++) {
                        if (message.charAt(i) == '`') {
                            tildeCounter++;
                            if (tildeCounter > 1)
                                return;
                        }
                    }

                    //If you haven't returned yet, then go ahead and calculate
                    Calculate.calculate(event);
                }
        }
    }
}
