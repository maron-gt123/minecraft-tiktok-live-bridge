package net.marongt123.tiktoklivebridge.listeners;

import net.marongt123.tiktoklivebridge.TikTokLiveBridge;
import org.bukkit.Bukkit;

public class GiftListener {

    private final TikTokLiveBridge plugin;

    public GiftListener(TikTokLiveBridge plugin) {
        this.plugin = plugin;
    }

    public void onGift(String username, String gift) {

        String format = plugin.getConfig()
                .getString("messages.gift");

        Bukkit.broadcastMessage(
                format
                        .replace("%user%", username)
                        .replace("%gift%", gift)
                        .replace("&", "§")
        );
    }
}