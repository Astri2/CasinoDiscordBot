package me.astri.casino.main;

import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.commandHandler.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class SandboxCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
    }

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public List<String> getAlias() {
        return List.of("sandbox");
    }

    @Override
    public Boolean hasPermission(MessageReceivedEvent e) {
        return (e.getMember().getId().equalsIgnoreCase(Config.get("OWNER_ID")));
    }

    @Override
    public String getHelp() {
        return "used to experiment things";
    }

    @Override
    public String getUsage(CommandContext ctx) {
        return ctx.getPrefix() + ctx.getCommand();
    }
}
