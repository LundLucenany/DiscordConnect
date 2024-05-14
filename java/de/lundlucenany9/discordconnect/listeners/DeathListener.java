package de.lundlucenany9.discordconnect.listeners;

import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.discord.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(!DiscordConnect.sendDeathMessages) return;
        Utils.sendEmbed(DiscordConnect.dChannel, "Server", e.getDeathMessage(), Color.YELLOW);
    }
}
