package me.astri.casino.commandHandler;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class CommandContext {
    private final String command;
    private final String prefix;
    private final List<String> args;
    private final MessageReceivedEvent event;
    private final User author;

    public CommandContext(String command, String prefix, MessageReceivedEvent e) {
        this.command = command;
        this.prefix = prefix;
        this.args = Arrays.stream(e.getMessage().getContentRaw().split("\\s+")).skip(1).toList();
        this.event = e;
        this.author = e.getAuthor();
    }

    public String getCommand() {
        return this.command;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public List<String> getArgs() {
        return this.args;
    }

    public MessageReceivedEvent getEvent() {
        return this.event;
    }

    public User getAuthor() {
        return this.author;
    }
}
