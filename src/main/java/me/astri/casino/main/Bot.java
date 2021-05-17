package me.astri.casino.main;

import me.astri.casino.commandHandler.CommandListener;
import me.astri.casino.eventWaiter.EventWaiter;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder
                .createDefault(Config.get("TOKEN"));
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.addEventListeners(
                new Listeners(),
                new CommandListener(),
                new EventWaiter()
        );
        builder.build().awaitReady();
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        new Bot(); //init jda

    }
}
