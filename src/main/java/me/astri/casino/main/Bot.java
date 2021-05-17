package me.astri.casino.main;

import me.astri.casino.eventWaiter.EventWaiter;
import me.astri.casino.commandHandler.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {
    private static JDA jda;

    private Bot() throws LoginException {
        JDABuilder builder = JDABuilder
                .createDefault(Config.get("TOKEN"));
        builder.addEventListeners(
                new Listeners(),
                new CommandListener(),
                new EventWaiter()
        );
        jda = builder.build();
    }

    public static JDA getJDA() {
        return jda;
    }

    public static void main(String[] args) throws LoginException {
        new Bot(); //init jda
    }
}
