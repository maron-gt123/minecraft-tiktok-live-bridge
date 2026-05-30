package net.marongt123.tiktoklivebridge.config;

import net.marongt123.tiktoklivebridge.TikTokLiveBridge;

public class ConfigManager {

    private final TikTokLiveBridge plugin;

    public ConfigManager(TikTokLiveBridge plugin) {
        this.plugin = plugin;
    }

    public String getMessage(String key) {

        return plugin.getConfig().getString(
                "messages." + key,
                ""
        );
    }

    public String getTikTokUsername() {

        return plugin.getConfig().getString(
                "tiktok.username",
                ""
        );
    }
}