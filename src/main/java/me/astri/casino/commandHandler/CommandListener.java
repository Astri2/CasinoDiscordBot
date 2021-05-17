package me.astri.casino.commandHandler;

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
                .toLowerCase().replaceAll("<@[!]?843441113570344980>",prefix);

        if(e.isFromGuild())
            prefix = "c!"; //TODO custom prefix -> db

        if(message.startsWith(prefix)) {
            String commandName = message.split("\\s+")[0].replace(prefix,"");
            manager.handle(commandName, prefix, e);
        }
    }
}
