package me.astri.casino.commandHandler;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.Arrays;
import java.util.List;

public class CommandContext {
    private final String command;
    private final String prefix;
    private final Message message;
    private final List<String> args;
    private final MessageReceivedEvent event;
    private final User author;
    private final JDA jda;

    public CommandContext(String command, String prefix, MessageReceivedEvent e) {
        this.command = command;
        this.prefix = prefix;
        this.message = e.getMessage();
        this.args = Arrays.stream(e.getMessage().getContentRaw().split("\\s+")).skip(1).toList();
        this.event = e;
        this.author = e.getAuthor();
        this.jda = e.getJDA();
    }

    public String getCommand() {
        return this.command;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public Message getMessage() {
        return this.message;
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

    public JDA getJda() {
        return this.jda;
    }


    public MessageAction reply(String message) {
        return this.event.getChannel().sendMessage(message);
    }

    public MessageAction reply(MessageEmbed eb) {
        return this.event.getChannel().sendMessage(eb);
    }
}
