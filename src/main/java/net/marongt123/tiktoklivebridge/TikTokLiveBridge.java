package net.marongt123.tiktoklivebridge;

import net.marongt123.tiktoklivebridge.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TikTokLiveBridge extends JavaPlugin {

    private static TikTokLiveBridge instance;

    private ConfigManager configManager;
    private TikTokClientManager clientManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        configManager = new ConfigManager(this);
        clientManager = new TikTokClientManager(this);

        clientManager.connect();

        getLogger().info("TikTokLiveBridge enabled");
    }

    @Override
    public void onDisable() {
        if (clientManager != null) {
            clientManager.disconnect();
        }

        getLogger().info("TikTokLiveBridge disabled");
    }

    public static TikTokLiveBridge getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}