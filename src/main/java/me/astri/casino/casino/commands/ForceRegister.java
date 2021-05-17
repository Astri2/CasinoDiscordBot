package me.astri.casino.casino.commands;

import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.commandHandler.ICommand;
import me.astri.casino.main.Config;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ForceRegister implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        if(ctx.getMessage().getMentionedUsers().isEmpty())
            throw new IllegalArgumentException("no user found");
        User user = ctx.getMessage().getMentionedUsers().get(0);
        if(Player.getPlayer(user.getId()) != null)
            throw new IllegalArgumentException("this member is already registered");
        Player.addPlayer(new Player(user));
        ctx.reply("👍").queue();
    }

    @Override
    public String getName() {
        return "forceRegister";
    }

    @Override
    public List<String> getAlias() {
        return List.of("fr");
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
        return "create a new bank account to someone";
    }

    @Override
    public String getUsage(CommandContext ctx) {
        return ctx.getPrefix() + ctx.getCommand() + " [user]";
    }
}
