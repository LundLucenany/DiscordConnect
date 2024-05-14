package de.lundlucenany9.discordconnect.commands;

import de.lundlucenany9.discordconnect.DiscordConnect;
import de.lundlucenany9.discordconnect.discord.Bot;
import de.lundlucenany9.discordconnect.discord.utils.Utils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DiscordCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 3)
            return false;
        if (args[0].equals("channel")) {
            if (!sender.hasPermission("discordconnect.discord.channel")) {
                sender.sendMessage(DiscordConnect.mNoPermission);
                return true;
            }
            if (args[1].equals("send")) {
                if (!sender.hasPermission("discordconnect.discord.channel.send")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                if (args.length < 4) return false;
                TextChannel channel = Bot.getChannel(args[2]);
                if (channel == null)
                    return false;
                String msg = args[3];
                for (int i = 4; i < args.length; i++) {
                    msg = msg + " " + args[i];
                }
                Utils.sendMessage(channel.getId(), msg);
                sender.sendMessage("Die Nachicht wurde in den Channel " + channel.getName() + " gestellt.");
                return true;
            } else if (args[1].equals("create")) {
                if (!sender.hasPermission("discordconnect.discord.channel.create")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                String name = args[2];
                if (Bot.getChannels().contains(name)) {
                    sender.sendMessage("Dieser Channel existiert bereits!");
                    return true;
                }
                Utils.createChannel(name);
                sender.sendMessage("Der Channel " + name + " wurde erstellt");
                return true;
            }
        } else if (args[0].equals("member")) {
            if (!sender.hasPermission("discordconnect.discord.member")) {
                sender.sendMessage(DiscordConnect.mNoPermission);
                return true;
            }
            Member member = Bot.getMember(args[1]);
            if (member == null) {
                sender.sendMessage("Dieses Mitglied gibt es nicht.");
                return false;
            }

            if (args[2].equals("mute")) {
                if (!sender.hasPermission("discordconnect.discord.member.mute")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                try {
                    Utils.muteMember(member.getId(), true);
                } catch (Exception e) {
                    sender.sendMessage("Diese Person befindet sich nicht in einem VoiceChannel!");
                    return false;
                }
                sender.sendMessage(member.getEffectiveName() + " wurde gemutet.");
                return true;
            }
            if (args[2].equals("unmute")) {
                if (!sender.hasPermission("discordconnect.discord.member.unmute")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                try {
                    Utils.muteMember(member.getId(), false);
                } catch (Exception e) {
                    sender.sendMessage("Diese Person befindet sich nicht in einem VoiceChannel!");
                    return false;
                }
                sender.sendMessage(member.getEffectiveName() + " wurde entmutet.");
                return true;
            }
            if (args[2].equals("kick")) {
                if (!sender.hasPermission("discordconnect.discord.member.kick")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                try {
                    Utils.kickMember(member.getId());
                    sender.sendMessage(member.getEffectiveName() + " wurde vom Server gekickt.");
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("Die Person konnte nicht gekickt werden!");
                    return false;
                }
            }
            if (args[2].equals("ban")) {
                if (!sender.hasPermission("discordconnect.discord.member.ban")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                try {
                    Utils.banMember(member.getId());
                    sender.sendMessage(member.getEffectiveName() + " wurde vom Server verbannt.");
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("Die Person konnte nicht verbannt werden!");
                    return false;
                }
            }
            if (args[2].equals("timeout")) {
                if (!sender.hasPermission("discordconnect.discord.member.timeout")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                if (args.length < 5) return false;
                long dur;
                TimeUnit unit;
                try {
                    dur = Long.parseLong(args[3]);
                    unit = TimeUnit.valueOf(args[4]);
                } catch (Exception ignored) {
                    return false;
                }
                try {
                    Utils.timeoutMember(member.getId(), dur, unit);
                    sender.sendMessage(member.getEffectiveName() + " wurde vom getimeouted.");
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("Die Person konnte nicht getimeouted werden!");
                    return false;
                }
            }
            if (args[2].equals("role")) {
                if (!sender.hasPermission("discordconnect.discord.member.role")) {
                    sender.sendMessage(DiscordConnect.mNoPermission);
                    return true;
                }
                if (args.length < 5) return false;

                Role role = Bot.getRole(args[4]);
                if (role == null) {
                    sender.sendMessage("Diese Rolle gibt es nicht.");
                    return false;
                }
                if (args[3].equals("add")) {
                    Utils.addRole(member.getId(), role);
                    sender.sendMessage("Die Rolle " + role.getName() + " wurde dem Mitglied " + member.getEffectiveName() + " hinzugefügt.");
                    return true;
                }
                if (args[3].equals("remove")) {
                    Utils.removeRole(member.getId(), role);
                    sender.sendMessage("Die Rolle " + role.getName() + " wurde dem Mitglied " + member.getEffectiveName() + " entzogen.");
                    return true;
                }
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1)
            return new ArrayList<>(List.of(new String[]{"channel", "member"}));
        if (args.length == 2 && args[0].equals("channel"))
            return new ArrayList<>(List.of(new String[]{"send", "create"}));
        if (args.length == 3 && args[1].equals("send") && args[0].equals("channel"))
            return Bot.getChannels();
        if (args.length == 2 && args[0].equals("member"))
            return Bot.getMembers();
        if (args.length == 3 && args[0].equals("member"))
            return new ArrayList<>(List.of(new String[]{"mute", "unmute", "kick", "ban", "timeout", "role"}));
        if (args.length == 4 && args[0].equals("member") && args[2].equals("role"))
            return new ArrayList<>(List.of((new String[]{"add", "remove"})));
        if (args.length == 5 && args[0].equals("member") && args[2].equals("role"))
            return Bot.getRoles();
        if (args.length == 5 && args[0].equals("member") && args[2].equals("timeout"))
            return new ArrayList<>(List.of(new String[]{"NANOSECONDS", "MICROSECONDS", "MILLISECONDS", "SECONDS", "MINUTES", "HOURS", "DAYS"}));

        return null;
    }
}
