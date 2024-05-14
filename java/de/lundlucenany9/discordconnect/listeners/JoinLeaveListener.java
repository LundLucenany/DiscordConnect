package de.lundlucenany9.discordconnect.listeners;

import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.discord.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!DiscordConnect.sendJoinLeaveMessage) return;
        Utils.sendMessage(DiscordConnect.dChannel, e.getJoinMessage());
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        if(!DiscordConnect.sendJoinLeaveMessage) return;
        Utils.sendMessage(DiscordConnect.dChannel, e.getQuitMessage());
    }
}
