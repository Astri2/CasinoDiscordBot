package me.astri.casino.commandHandler;

import me.astri.casino.Exception.ArgumentException;
import me.astri.casino.casino.commands.BalanceCommand;
import me.astri.casino.casino.commands.StaffGiveCommand;
import me.astri.casino.commands.LangCommand;
import me.astri.casino.main.SandboxCommand;
import me.astri.casino.wheel.WheelCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new WheelCommand());
        addCommand(new BalanceCommand());
        addCommand(new StaffGiveCommand());
        addCommand(new SandboxCommand());
        addCommand(new LangCommand());
    }

    private void addCommand(ICommand cmd) {
        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    public ICommand getICommand(String name) {

        for(ICommand command : commands) {
            if(command.getName().equalsIgnoreCase(name) ||
                    command.getAlias().stream().anyMatch(a -> a.equalsIgnoreCase(name))) {
                return command;
            }
        }
        return null;
    }

    void handle(String commandName, String prefix, MessageReceivedEvent e) throws ArgumentException {
        ICommand cmd = getICommand(commandName);
        if(cmd == null)
            return;
        //else
        if(!e.isFromGuild() && cmd.guildOnly())
            return;
        //else
        if(!cmd.hasPermission(e))
            return;
        //else
        cmd.handle(new CommandContext(commandName, prefix, e));
    }
}
