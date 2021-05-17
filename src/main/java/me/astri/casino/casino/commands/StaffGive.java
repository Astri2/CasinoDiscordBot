package me.astri.casino.casino.commands;

import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.commandHandler.ICommand;
import me.astri.casino.main.Config;
import me.astri.casino.main.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Instant;
import java.util.List;

public class StaffGive implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if(ctx.getMessage().getMentionedUsers().isEmpty())
            throw new IllegalArgumentException("No user found");
        User user = ctx.getMessage().getMentionedUsers().get(0);
        if(Player.getPlayer(user.getId()) == null)
            throw new IllegalArgumentException("User not registered, use forceRegister!");
        if(ctx.getArgs().size() < 2 || !Utils.areNumbers(ctx.getArgs().get(1)) || Integer.parseInt(ctx.getArgs().get(1)) < 0)
            throw new IllegalArgumentException("amount must be positive number");
        long amount = Integer.parseInt(ctx.getArgs().get(1));
        Player.getPlayer(user.getId()).getBank().add(amount);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("StaffGive",ctx.getAuthor().getAvatarUrl());
        eb.setDescription(amount + "$ were given to " + user.getAsMention());
        eb.setFooter("Requested by " + ctx.getAuthor().getAsTag());
        eb.setTimestamp(Instant.now());
        ctx.reply(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "staffGive";
    }

    @Override
    public List<String> getAlias() {
        return List.of("sg");
    }

    @Override
    public Boolean guildOnly() {
        return true;
    }

    @Override
    public Boolean hasPermission(MessageReceivedEvent e) {
        return (e.getMember().getId().equalsIgnoreCase(Config.get("OWNER_ID")));
    }

    @Override
    public String getHelp() {
        return "give money to users";
    }

    @Override
    public String getUsage(CommandContext ctx) {
        return ctx.getPrefix() + ctx.getCommand() + "[user] [amount]";
    }
}
