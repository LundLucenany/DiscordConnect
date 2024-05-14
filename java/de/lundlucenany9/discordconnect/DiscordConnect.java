package de.lundlucenany9.discordconnect;

import de.lundlucenany9.discordconnect.commands.DiscordCommand;
import de.lundlucenany9.discordconnect.commands.SharedChatCommand;
import de.lundlucenany9.discordconnect.discord.Bot;
import de.lundlucenany9.discordconnect.discord.utils.Utils;
import de.lundlucenany9.discordconnect.listeners.ChatListener;
import de.lundlucenany9.discordconnect.listeners.DeathListener;
import de.lundlucenany9.discordconnect.listeners.JoinLeaveListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.Objects;

public final class DiscordConnect extends JavaPlugin {

    public static String botId;
    public static String dServer;
    public static String dChannel;
    public static String dWhitelistC;
    public static boolean dWhitelist;
    public static boolean dConnect;
    public static boolean sendJoinLeaveMessage;
    public static boolean sendDeathMessages;
    public static DiscordConnect plugin;

    public static String mNoPermission;

    @Override
    public void onEnable() {
        plugin = this;
        saveResource("config.yml", false);
        botId = getConfig().getString("botId");
        dServer = getConfig().getString("discordServerId");
        dChannel = getConfig().getString("discordMainChannelID");
        dWhitelistC = getConfig().getString("discordWhitelistChannelID");
        dWhitelist = getConfig().getBoolean("whitelistChannel");
        dConnect = getConfig().getBoolean("connectedChat");
        sendJoinLeaveMessage = getConfig().getBoolean("sendJoinLeaveMessage");
        sendDeathMessages = getConfig().getBoolean("sendDeathMessages");
        if (Objects.equals(dServer, "") || Objects.equals(dChannel, "") || Objects.equals(botId, "") || (dWhitelist && Objects.equals(dWhitelistC, ""))) {
            this.getLogger().warning("You must first configure the config file!");
            return;
        }
        try {
            Bot.main(new String[]{botId, dServer});
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mNoPermission = getConfig().getString("noPermission");

        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new ChatListener(), this);
        manager.registerEvents(new JoinLeaveListener(), this);
        manager.registerEvents(new DeathListener(), this);

        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("discord").setTabCompleter(new DiscordCommand());
        getCommand("sharedchat").setExecutor(new SharedChatCommand());
        getCommand("sharedchat").setTabCompleter(new SharedChatCommand());
    }

    @Override
    public void onDisable() {
        Utils.sendEmbed(DiscordConnect.dChannel, "Server", "Der Server ist geschlossen worden.", Color.BLUE);
    }
}

