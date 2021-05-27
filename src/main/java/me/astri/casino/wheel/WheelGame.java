package me.astri.casino.wheel;

import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.main.Utils;
import net.dv8tion.jda.api.EmbedBuilder;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WheelGame {
    private final static HashMap<Long,String> wheelNumberEmotes = initNumberEmotes();
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
        eb.setDescription(ctx.getAuthor().getAsMention() + " you bet **" + pBet.getAmount() + "**$." +
                "\nMay you be lucky!" +
                "\nmultiplier: **" + pBet.getMultiplier() + "**");
        eb.setThumbnail(pBet.getUrl());
        eb.setFooter("Requested by " + ctx.getAuthor().getAsTag());
        eb.setTimestamp(Instant.now());
        ctx.reply(eb.build()).queue(message -> {
            long draw = drawNumber();
            boolean win = pBet.getIssues().contains(draw);
            eb.addField(wheelNumberEmotes.get(draw) + " Result",Long.toString(draw),false);
            message.editMessage(eb.build()).queueAfter(2000,TimeUnit.MILLISECONDS);
            eb.addField(win ? "💰 Win":
                            "💸 Loose",
                    win ? "+" + pBet.getAmount() * pBet.getMultiplier() + "$" :
                            "-" + pBet.getAmount() + "$",false);
            message.editMessage(eb.build()).queueAfter(2500,TimeUnit.MILLISECONDS);

            if(pBet.getIssues().contains(draw)) {
                player.getBank().add(pBet.getAmount() * pBet.getMultiplier());
            }
            else {
                player.getBank().withdraw(pBet.getAmount());
            }

        });
    }

    private long drawNumber() {
        return new Random().nextInt(37);
    }

    private static HashMap<Long,String> initNumberEmotes() {
        HashMap<Long,String> map = new HashMap<>();
        for(List<String> args : Utils.readCSV("WHEEL_NUMBER_EMOTE")) {
            map.put(Long.parseLong(args.get(0)),args.get(1));
        }
        return map;
    }
}
