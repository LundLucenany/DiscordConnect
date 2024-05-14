package de.lundlucenany9.discordconnect.discord.listeners;

import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.utils.Utils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ChatEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (!DiscordConnect.dConnect) return;
        if (e.getChannel().getId().equals(DiscordConnect.dChannel) && !e.getAuthor().isBot()) {
            String name = e.getMember().getEffectiveName();
            String msg = e.getMessage().getContentRaw();
            Member member = e.getMember();

            TextComponent s = new TextComponent();

            TextComponent d = new TextComponent("DISCORD ");
            d.setColor(ChatColor.BLUE);

            TextComponent n = new TextComponent("<" + name + "> ");
            n.setColor(ChatColor.of((member.getColor() == null ? Color.WHITE : member.getColor())));
            n.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(e.getAuthor().getName())));

            TextComponent m = new TextComponent(msg);

            s.addExtra(d);
            s.addExtra(n);
            s.addExtra(m);

            Utils.sendMessage(s, Bukkit.getOnlinePlayers());
        }
    }
}
