package de.lundlucenany9.discordconnect.listeners;

import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.discord.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;

public class JoinLeaveListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!DiscordConnect.sendJoinLeaveMessage) return;
        Utils.sendEmbed(DiscordConnect.dChannel, "Server", e.getPlayer().getDisplayName() + " ist dem Server beigetreten.", Color.green);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        if(!DiscordConnect.sendJoinLeaveMessage) return;
        Utils.sendEmbed(DiscordConnect.dChannel, "Server", e.getPlayer().getDisplayName() + " ist dem Server beigetreten.", Color.yellow);
    }
}
