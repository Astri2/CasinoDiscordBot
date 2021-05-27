package me.astri.casino.commandHandler;

import me.astri.casino.Exception.ArgumentException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    private final CommandManager manager = new CommandManager();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if(e.getAuthor().isBot() || e.isWebhookMessage())
            return;

        String prefix = "c!";
        String message = e.getMessage().getContentRaw()
                .toLowerCase().replaceAll("<@[!]?843441113570344980>\\s*",prefix);

        if(e.isFromGuild())
            prefix = "c!"; //TODO custom prefix -> db

        if(message.startsWith(prefix)) {
            String commandName = message.split("\\s+")[0].replace(prefix,"");
            try {
                manager.handle(commandName, prefix, e);
            }
            catch(ArgumentException ignored) {}
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
