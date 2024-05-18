package de.lundlucenany9.discordconnect.commands;

import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.discord.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SharedChatCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Der geteilte Chat ist aktuell " + (DiscordConnect.dConnect ? "aktiviert." : "deaktiviert."));
            return true;
        }
        if (args[0].equals("enable")) {
            DiscordConnect.dConnect = true;
            Bukkit.broadcastMessage(DiscordConnect.mSharedChatEnable);
            Utils.sendEmbed(DiscordConnect.dChannel, "Server", DiscordConnect.mSharedChatEnable, Color.MAGENTA);
            return true;
        }
        if (args[0].equals("disable")) {
            DiscordConnect.dConnect = false;
            Bukkit.broadcastMessage(DiscordConnect.mSharedChatDisable);
            Utils.sendEmbed(DiscordConnect.dChannel, "Server", DiscordConnect.mSharedChatDisable, Color.MAGENTA);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1)
            return new ArrayList<>(List.of(new String[]{"enable", "disable"}));
        return null;
    }
}
