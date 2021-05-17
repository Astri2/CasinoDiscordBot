package me.astri.casino.wheel;

import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.main.Bot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WheelGame {
    private final PlayerBet pBet;

    public WheelGame(CommandContext ctx, PlayerBet pBet) {
        this.pBet = pBet;
        play(ctx);
    }

    private void play(CommandContext ctx) {
        Player player = Player.getPlayer(ctx.getAuthor().getId());
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(0x00DD00);
        eb.setAuthor("Wheel Game", null, ctx.getAuthor().getAvatarUrl());
        eb.setDescription(player.getName() + " you bet " + pBet.getAmount() +
                ".\nMay you be lucky!" +
                "\nmultiplier: **" + pBet.getBet().getMultiplier() + "**");
        eb.setImage(pBet.getBet().getUrl());
        eb.setFooter("Requested by " + ctx.getAuthor().getAsTag(), Bot.getJDA().getSelfUser().getAvatarUrl());
        ctx.getEvent().getChannel().sendMessage(eb.build()).queue();

        int draw = drawNumber();
        if(pBet.getBet().getIssues().contains(draw)) {
            ctx.getEvent().getChannel().sendMessage("win! " + draw).queueAfter(2, TimeUnit.SECONDS);
            player.getBank().add(pBet.getAmount() * pBet.getBet().getMultiplier());
        }
        else {
            ctx.getEvent().getChannel().sendMessage("loose! " + draw).queueAfter(2, TimeUnit.SECONDS);
            player.getBank().withdraw(pBet.getAmount());
        }
    }

    private int drawNumber() {
        return new Random().nextInt(37);
    }
}
