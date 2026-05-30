package net.marongt123.tiktoklivebridge;

import io.github.jwdeveloper.tiktok.TikTokLive;
import io.github.jwdeveloper.tiktok.data.events.TikTokCommentEvent;
import io.github.jwdeveloper.tiktok.data.events.gift.TikTokGiftEvent;
import io.github.jwdeveloper.tiktok.data.events.social.TikTokFollowEvent;
import io.github.jwdeveloper.tiktok.data.events.social.TikTokJoinEvent;
import io.github.jwdeveloper.tiktok.live.LiveClient;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class TikTokClientManager {

    private final TikTokLiveBridge plugin;
    private LiveClient client;

    public TikTokClientManager(TikTokLiveBridge plugin) {
        this.plugin = plugin;
    }

    // config //
    private String msg(String path) {
        return plugin.getConfig().getString("messages." + path);
    }

    private String format(String template, String user, String message, String gift) {
        if (template == null) return "";

        return template
                .replace("%user%", user == null ? "" : user)
                .replace("%message%", message == null ? "" : message)
                .replace("%gift%", gift == null ? "" : gift);
    }

    // color //
    private String prefix(String tag, ChatColor color) {
        return ChatColor.BOLD.toString() + color + "[" + tag + "]" + ChatColor.RESET + " ";
    }

    // features //
    private boolean enabled(String path) {
        return plugin.getConfig().getBoolean("features." + path, true);
    }

    public void connect() {

        String username = plugin.getConfig().getString("tiktok.username");

        if (username == null || username.isBlank() || username.equals("your_tiktok_username")) {

            // console warning
            plugin.getLogger().severe("====================================");
            plugin.getLogger().severe(" TikTokLiveBridge CONFIG ERROR");
            plugin.getLogger().severe(" Username is not set or invalid");
            plugin.getLogger().severe(" Please set: tiktok.username (no @)");
            plugin.getLogger().severe("====================================");

            // in-game alert
            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.broadcastMessage(ChatColor.RED +
                        "[TikTokLiveBridge] CONFIG ERROR: username is not set!");
            });

            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        plugin.getLogger().info("Connecting to TikTok Live: " + username);

        try {
            client = TikTokLive
                    .newClient(username)

                    .onEvent(TikTokCommentEvent.class, (client, event) -> handleComment(event))
                    .onEvent(TikTokFollowEvent.class, (client, event) -> handleFollow(event))
                    .onEvent(TikTokJoinEvent.class, (client, event) -> handleJoin(event))
                    .onEvent(TikTokGiftEvent.class, (client, event) -> handleGift(event))

                    .buildAndConnect();

            plugin.getLogger().info("TikTok connected");

        } catch (Exception e) {

            plugin.getLogger().warning("TikTok Live connection failed: " + e.getMessage());
            plugin.getLogger().warning("Check config username or whether the account is live.");

            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.broadcastMessage(ChatColor.RED +
                        "[TikTokLiveBridge] Connection to TikTok Live failed (username or LIVE status issue)");
            });

            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    // comment //
    private void handleComment(TikTokCommentEvent event) {
        if (!enabled("comment")) return;
        String msg = prefix("TikTok_COMMENT", ChatColor.GRAY)
                + format(
                msg("comment"),
                event.getUser().getProfileName(),
                event.getText(),
                null
        );
        Bukkit.getScheduler().runTask(plugin,
                () -> Bukkit.broadcastMessage(msg));
    }

    // follow //
    private void handleFollow(TikTokFollowEvent event) {
        if (!enabled("follow")) return;
        String msg = prefix("TikTok_Follow", ChatColor.GREEN)
                + format(
                msg("follow"),
                event.getUser().getProfileName(),
                null,
                null
        );
        Bukkit.getScheduler().runTask(plugin,
                () -> Bukkit.broadcastMessage(msg));
    }

    // Join //
    private void handleJoin(TikTokJoinEvent event) {
        if (!enabled("join")) return;
        String msg = prefix("TikTok_Join", ChatColor.AQUA)
                + format(
                msg("join"),
                event.getUser().getProfileName(),
                null,
                null
        );
        Bukkit.getScheduler().runTask(plugin,
                () -> Bukkit.broadcastMessage(msg));
    }

    // Gift //
    private void handleGift(TikTokGiftEvent event) {
        if (!enabled("gift")) return;
        String giftName = event.getGift().getName();
        String msg = prefix("TikTok_Gift", ChatColor.GOLD)
                + format(
                msg("gift"),
                event.getUser().getProfileName(),
                null,
                giftName
        );
        Bukkit.getScheduler().runTask(plugin,
                () -> Bukkit.broadcastMessage(msg));
    }

    public void disconnect() {
        if (client != null) {
            client.disconnect();
        }
    }
}