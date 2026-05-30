package net.marongt123.tiktoklivebridge.listeners;

import net.marongt123.tiktoklivebridge.TikTokLiveBridge;
import org.bukkit.Bukkit;

public class JoinListener {

    private final TikTokLiveBridge plugin;

    public JoinListener(TikTokLiveBridge plugin) {
        this.plugin = plugin;
    }

    public void onJoin(String username) {

        String format = plugin.getConfig()
                .getString("messages.join");

        Bukkit.broadcastMessage(
                format
                        .replace("%user%", username)
                        .replace("&", "§")
        );
    }
}