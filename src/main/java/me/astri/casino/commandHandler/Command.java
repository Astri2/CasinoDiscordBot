package me.astri.casino.commandHandler;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface Command {
    void handle(CommandContext ctx);

    String getName();
    default List<String> getAlias() {
        return List.of();
    }

    default Boolean guildOnly() {
        return false;
    }
    default Boolean hasPermission(MessageReceivedEvent e) {
        return true;
    }

    String getHelp();
    String getUsage(String command);

}
