package me.acclashcorporation.acsecurity.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFilter implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String msg = e.getMessage().toLowerCase();
        if (msg.contains("test")) {
            msg.replace("test", "****");
        }
    }
}
