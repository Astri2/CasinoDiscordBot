package me.astri.casino.main;

import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listeners extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent e) {
        System.out.println("Bot online!");
    }

    @Override
    public void onDisconnect(@NotNull DisconnectEvent e) {
        System.out.println("Bot offline!");
    }
}
