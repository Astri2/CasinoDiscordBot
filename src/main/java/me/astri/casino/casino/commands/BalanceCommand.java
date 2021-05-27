package me.astri.casino.casino.commands;

import me.astri.casino.Exception.ArgumentException;
import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.commandHandler.ICommand;
import me.astri.casino.main.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.time.Instant;
import java.util.List;

public class BalanceCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        String id;
        String name;

        if(ctx.getMessage().isFromGuild() && !ctx.getMessage().getMentionedMembers().isEmpty()) {
            Member member = ctx.getMessage().getMentionedMembers().get(0);
            id = member.getId();
            name = member.getEffectiveName();
        } else {
            id = ctx.getAuthor().getId();
            name = ctx.getAuthor().getName();
        }

        long bal = Player.getPlayer(id).getBank().getBalance();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(name + "'s balance : " + bal + "$",null,ctx.getAuthor().getAvatarUrl());
        eb.setFooter("Requested by " + ctx.getAuthor().getAsTag());
        eb.setTimestamp(Instant.now());
        ctx.reply(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "balance";
    }

    @Override
    public List<String> getAlias() {
        return List.of("bal","money");
    }

    @Override
    public String getHelp() {
        return "get player balance";
    }

    @Override
    public String getUsage(CommandContext ctx) {
        return ctx.getPrefix() + ctx.getCommand() + " <user>";
    }
}
