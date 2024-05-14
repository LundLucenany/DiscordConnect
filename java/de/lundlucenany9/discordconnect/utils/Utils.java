package de.lundlucenany9.discordconnect.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Objects;

public class Utils {
    public static void sendMessage(BaseComponent msg, Collection<? extends Player> p) {
        for (Player pl : p) {
            pl.spigot().sendMessage(msg);
        }
    }

    public static boolean getWhitelist(String name) {
        for (OfflinePlayer p : Bukkit.getWhitelistedPlayers()) {
            if (Objects.equals(p.getName(), name)) {
                return p.isWhitelisted();
            }
        }

        return false;
    }

    @Deprecated
    public static void setWhitelist(String name) {
        if (Bukkit.getWhitelistedPlayers().contains(Bukkit.getOfflinePlayer(name))) {
            Bukkit.getServer().getWhitelistedPlayers().remove(Bukkit.getOfflinePlayer(name));
            return;
        }
        Bukkit.getServer().getWhitelistedPlayers().add(Bukkit.getOfflinePlayer(name));

    }

    public static void sendCommad(String cmd) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }

}
