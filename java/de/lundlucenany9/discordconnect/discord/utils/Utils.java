package de.lundlucenany9.discordconnect.discord.utils;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.UserSnowflake;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static de.lundlucenany9.discordconnect.discord.Bot.dGuild;

public class Utils {
    public static void sendMessage(String cId, String msg) {
        dGuild.getTextChannelById(cId).sendMessage(msg).queue();
    }

    public static void createChannel(@NotNull String name) {
        dGuild.createTextChannel(name).queue();
    }

    public static void muteMember(@NotNull String id, boolean mute) {
        dGuild.mute(Objects.requireNonNull(dGuild.getMemberById(id)), mute).queue();
    }

    public static void kickMember(@NotNull String id) {
        dGuild.kick(UserSnowflake.fromId(id));
    }

    public static void banMember(String id) {
        dGuild.ban(UserSnowflake.fromId(id), 0, TimeUnit.MICROSECONDS).queue();
    }

    public static void timeoutMember(String id, long amount, TimeUnit unit) {
        dGuild.timeoutFor(UserSnowflake.fromId(id), 1, unit).queue();
    }

    public static void addRole(String id, Role role) {
        dGuild.addRoleToMember(UserSnowflake.fromId(id), role);
    }

    public static void removeRole(String id, Role role) {
        dGuild.removeRoleFromMember(UserSnowflake.fromId(id), role);
    }
}
