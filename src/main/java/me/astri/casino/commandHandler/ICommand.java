package me.astri.casino.commandHandler;

import me.astri.casino.Exception.ArgumentException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface ICommand {
    void handle(CommandContext ctx) throws ArgumentException;

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
    String getUsage(CommandContext ctx);
}
