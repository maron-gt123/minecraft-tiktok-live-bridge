package net.marongt123.tiktoklivebridge.listeners;

import net.marongt123.tiktoklivebridge.TikTokLiveBridge;
import org.bukkit.Bukkit;

public class CommentListener {

    private final TikTokLiveBridge plugin;

    public CommentListener(TikTokLiveBridge plugin) {
        this.plugin = plugin;
    }

    public void onComment(String username, String message) {

        String format = plugin.getConfig()
                .getString("messages.comment");

        Bukkit.broadcastMessage(
                format
                        .replace("%user%", username)
                        .replace("%message%", message)
                        .replace("&", "§")
        );
    }
}