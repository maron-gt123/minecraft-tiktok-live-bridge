package net.marongt123.tiktoklivebridge.listeners;

import net.marongt123.tiktoklivebridge.TikTokLiveBridge;
import org.bukkit.Bukkit;

public class FollowListener {

    private final TikTokLiveBridge plugin;

    public FollowListener(TikTokLiveBridge plugin) {
        this.plugin = plugin;
    }

    public void onFollow(String username) {

        String format = plugin.getConfig()
                .getString("messages.follow");

        Bukkit.broadcastMessage(
                format
                        .replace("%user%", username)
                        .replace("&", "§")
        );
    }
}