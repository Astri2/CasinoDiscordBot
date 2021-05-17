package me.astri.casino.wheel;

import me.astri.casino.casino.Player;
import me.astri.casino.commandHandler.ICommand;
import me.astri.casino.commandHandler.CommandContext;
import me.astri.casino.main.Utils;

import java.util.List;

public class WheelCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        if(ctx.getArgs().size() >=3) {
            new WheelGame(ctx, new PlayerBet(getAmount(args.get(0),ctx.getAuthor().getId()),getBet(args)));
        }
        else {
            throw new IllegalArgumentException("not enough arguments!");
            //TODO event waiter :(
        }
    }

    private int getAmount(String arg,String playerId) {
        if(!Utils.areNumbers(arg))
            throw new IllegalArgumentException("NaN");
        int amount = Integer.parseInt(arg);
        if(amount <= 0)
            throw new IllegalArgumentException("amount must be positive");
        if(amount > Player.getPlayer(playerId).getBank().getBalance())
            throw new IllegalArgumentException("amount > balance");
        return Integer.parseInt(arg);
    }

    private Bet getBet(List<String> args) {
        String betName = "wheel.bet." + args.get(1)+"."+ args.get(2);
        System.out.println(betName);
        if(Bet.getBet(betName) == null)
            throw new IllegalArgumentException("invalid bet");
        return Bet.getBet(betName);
    }

    @Override
    public String getName() {
        return "wheel";
    }

    @Override
    public List<String> getAlias() {
        return List.of("wh");
    }

    @Override
    public String getHelp() {
        return "play a wheel game";
    }

    @Override
    public String getUsage(CommandContext ctx) {
        return ctx.getPrefix() + ctx.getCommand() + " <amount> <betType> <bet>";
    }
}
