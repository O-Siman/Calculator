import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class Help {
    public static void helpCommand(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage(event.getAuthor().getName() + ", check your DM's.").queue();

        event.getAuthor().openPrivateChannel().queue((channel) -> {
            channel.sendMessage("This is where I help you").queue();
        });
    }
}
