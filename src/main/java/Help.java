import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help {
    public static void helpCommand(GuildMessageReceivedEvent event) {
        event.getAuthor().openPrivateChannel().queue((channel) -> {
            channel.sendMessage("This is where I help you").queue();
    });
    }
}
