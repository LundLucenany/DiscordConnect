package de.lundlucenany9.discordconnect.discord.listeners;

import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class WhitelistEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (!DiscordConnect.dWhitelist) return;
        if (!e.getChannel().getId().equals(DiscordConnect.dWhitelistC)) return;
        if (e.getAuthor().isBot()) return;
        String name = e.getMessage().getContentRaw();

        if (Utils.getWhitelist(name)) {
            Bukkit.getServer().getScheduler().runTask(DiscordConnect.plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist remove " + name);
            });
        } else {
            Bukkit.getServer().getScheduler().runTask(DiscordConnect.plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist add " + name);
            });
        }

        de.lundlucenany9.discordconnect.discord.utils.Utils.sendMessage(DiscordConnect.dWhitelistC, "Der Spieler " + name + " wurde " + (Utils.getWhitelist(name) ? "aus der Whitelist entfernt." : "zur Whitelist hinzugefuegt."));
    }
}
