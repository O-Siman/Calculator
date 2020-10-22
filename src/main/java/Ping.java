import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Ping {
    public static void pingCommand(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Pong! 🏓").queue();
    }
}
