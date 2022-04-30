package me.acclashcorporation.acsecurity.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AntiCheat implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                if (player.isFlying()) {
                    player.kickPlayer("Flying is not enabled on this server");
                }
            }
        }
    }
}
