package me.astri.casino.commandHandler;

import me.astri.casino.wheel.WheelCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<Command> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new WheelCommand());
    }

    private void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Command getCommand(String name) {

        for(Command command : commands) {
            if(command.getName().equalsIgnoreCase(name) ||
                    command.getAlias().stream().anyMatch(a -> a.equalsIgnoreCase(name))) {
                return command;
            }
        }
        return null;
    }

    void handle(String commandName, String prefix, MessageReceivedEvent e) {
        Command cmd = getCommand(commandName);
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
