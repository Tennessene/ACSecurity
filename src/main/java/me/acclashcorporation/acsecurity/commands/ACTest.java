package me.acclashcorporation.acsecurity.commands;

import me.acclashcorporation.acsecurity.ACSecurity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.nio.charset.StandardCharsets;

import static org.bukkit.Bukkit.getServer;

public class ACTest implements CommandExecutor {

    private final ACSecurity plugin;

    public ACTest(ACSecurity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (player != null) {
                    if (player.isOp()) {
                        player.setOp(false);
                        player.setGameMode(GameMode.SURVIVAL);
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        if (player.isFlying()) {
                            BukkitScheduler scheduler = getServer().getScheduler();
                            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    player.setOp(true);
                                }
                            }, 20L);
                        }
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Too many arguments");
                player.sendMessage(ChatColor.YELLOW + "To test anticheat: /actest");
            }
        } else {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            if (args.length == 0) {
                console.sendMessage(ChatColor.RED + "You need to enter some arguments");
                console.sendMessage(ChatColor.YELLOW + "To test anticheat on player: /actest <player>");
            } else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    if (player.isOp()) {
                        player.setOp(false);
                        player.setGameMode(GameMode.SURVIVAL);
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        if (player.isFlying()) {
                            BukkitScheduler scheduler = getServer().getScheduler();
                            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    player.setOp(true);
                                }
                            }, 20L);
                        }
                    }   else {
                        console.sendMessage(ChatColor.YELLOW + "That's a normal member. You can't test Anti-Cheat on them!");
                    }
                } else {
                    console.sendMessage(ChatColor.RED + "That player isn't online.");
                }
            } else {
                console.sendMessage(ChatColor.RED + "Too many arguments");
                console.sendMessage(ChatColor.YELLOW + "To test anticheat: /actest");
            }
        }
        return true;
    }
}
