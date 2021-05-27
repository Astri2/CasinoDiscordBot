package me.astri.casino.Exception;

import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.commandHandler.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.concurrent.TimeUnit;

public class ArgumentException extends Exception{
    public ArgumentException(CommandContext ctx, ICommand command, String ExceptionName, String ExceptionMessage) {
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(0xDD0000)
                .setAuthor(ExceptionName,null,ctx.getAuthor().getAvatarUrl())
                .setDescription(ExceptionMessage)
                .setFooter(command.getUsage(ctx));
        ctx.reply(eb.build()).queue(msg -> msg.delete().queueAfter(20, TimeUnit.SECONDS));
    }
}
