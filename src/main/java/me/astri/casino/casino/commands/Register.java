package me.astri.casino.casino.commands;

import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.commandHandler.ICommand;

import java.util.List;

public class Register implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if(Player.getPlayer(ctx.getAuthor().getId()) != null)
            throw new IllegalArgumentException("you are already registered!");
        Player.addPlayer(new Player(ctx.getAuthor()));
        ctx.reply("👍").queue();
    }

    @Override
    public String getName() {
        return "register";
    }

    @Override
    public List<String> getAlias() {
        return List.of("r");
    }

    @Override
    public String getHelp() {
        return "register oneself";
    }

    @Override
    public String getUsage(CommandContext ctx) {
        return ctx.getPrefix() + ctx.getCommand();
    }
}
