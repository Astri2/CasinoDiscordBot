package me.astri.casino.commands;

import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.commandHandler.ICommand;
import me.astri.casino.eventWaiter.EventWaiter;
import me.astri.casino.eventWaiter.Waiter;
import me.astri.casino.main.Lang;
import me.astri.casino.main.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LangCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Lang lang;
        if(ctx.getArgs().isEmpty() || (lang = Lang.getEnumFromName(ctx.getArgs().get(0))) == null)
            askLanguage(ctx);
        else {
            setLanguage(null,ctx.getChannel(), ctx.getAuthor(),lang);
        }
    }

    private void askLanguage(CommandContext ctx) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("Select your language", ctx.getAuthor().getAvatarUrl());
        eb.setFooter("Requested by " + ctx.getAuthor().getAsTag());
        eb.setTimestamp(Instant.now());

        ctx.reply(eb.build()).queue(message -> {
            Utils.addReactions(message,
                    Lang.ENGLISH.getEmoji(),
                    Lang.FRENCH.getEmoji(),
                    Lang.GERMAN.getEmoji()
            );

            EventWaiter.register(new Waiter<>(
                    MessageReactionAddEvent.class,
                    e -> (e.getMessageId().equals(message.getId()) && e.getUser().equals(ctx.getAuthor()) && Utils.hasBotReacted(e)),
                    action -> {
                        String emoji = action.getEvent().getReactionEmote().getEmoji();
                        Lang lang = Lang.getEnumFromEmoji(emoji);

                        setLanguage(message, ctx.getChannel(), ctx.getAuthor(), lang);
                    },
                    failureAction -> {
                        if(failureAction.getEvent().getMessageId().equals(message.getId()) && !failureAction.getEvent().getUser().equals(failureAction.getEvent().getJDA().getSelfUser()))
                            failureAction.getEvent().getReaction().removeReaction(failureAction.getEvent().getUser()).queue();
                    },
                    true,
                    2L, TimeUnit.MINUTES,
                    () -> message.clearReactions().queue()
            ));
        });
    }

    private void setLanguage(@Nullable Message message, TextChannel channel, User user, Lang lang) {
        Player.getPlayer(user.getId()).setLanguage(lang);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("Select your language", null, user.getAvatarUrl());
        eb.setDescription("Language set to **" + lang.name + "** " + lang.emoji);
        eb.setFooter("Requested by " + user.getAsTag());
        eb.setTimestamp(Instant.now());
        if(message == null)
            channel.sendMessage(eb.build()).queue();
        else {
            message.editMessage(eb.build()).queue();
            message.clearReactions().queue();
        }
    }

    @Override
    public String getName() {
        return "lang";
    }

    @Override
    public List<String> getAlias() {
        return List.of("language","langue","sprache");
    }

    @Override
    public String getHelp() {
        return "set player language";
    }

    @Override
    public String getUsage(CommandContext ctx) {
        return ctx.getPrefix() + ctx.getCommand() + " <language>";
    }
}
