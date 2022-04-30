package me.acclashcorporation.acsecurity;

import me.acclashcorporation.acsecurity.commands.ACTest;
import me.acclashcorporation.acsecurity.listeners.AntiCheat;
import me.acclashcorporation.acsecurity.listeners.ChatFilter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.UnsupportedEncodingException;

public final class ACSecurity extends JavaPlugin implements PluginMessageListener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new AntiCheat(), this);
        getServer().getPluginManager().registerEvents(new ChatFilter(), this);
        getCommand("actest").setExecutor(new ACTest(this));
        Messenger messenger;
        String version = Bukkit.getBukkitVersion();
        version = version.substring(version.indexOf(".") + 1);
        version = version.substring(0, version.indexOf("."));
        if (Integer.parseInt(version) < 13) {
            messenger = Bukkit.getMessenger();
            messenger.registerIncomingPluginChannel(this, "MC|Brand", this);
        } else {
            messenger = Bukkit.getMessenger();
            messenger.registerIncomingPluginChannel(this, "minecraft:brand", this);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] msg) {
        //Finish brands (put list in config)
        try {
            p.sendMessage("Your client brand: " + new String(msg, "UTF-8").substring(1));
        } catch (UnsupportedEncodingException e) {
            p.sendMessage("Unable to retrieve client version.");
        }
    }
}
