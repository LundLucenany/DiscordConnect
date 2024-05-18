package de.lundlucenany9.discordconnect.listeners;


import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.discord.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!DiscordConnect.dConnect) return;
        Utils.sendMessage(DiscordConnect.dChannel, e.getPlayer().getDisplayName() + e.getMessage());
    }
}
