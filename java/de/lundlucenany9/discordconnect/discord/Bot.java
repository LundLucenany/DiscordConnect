package de.lundlucenany9.discordconnect.discord;

import de.lundlucenany9.discordconnect.discord.listeners.ChatEvent;
import de.lundlucenany9.discordconnect.discord.listeners.WhitelistEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;

public class Bot {

    public static Guild dGuild;
    //public static List<TextChannel> channels;
    private static JDA jda;

    public static void main(String[] args) throws InterruptedException {
        jda = JDABuilder.createDefault(args[0]).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        jda.awaitReady();
        dGuild = jda.getGuildById(args[1]);
        //channels = jda.getTextChannels();

        jda.addEventListener(new ChatEvent());
        jda.addEventListener(new WhitelistEvent());
    }

    public static TextChannel getChannel(String name) {
        return jda.getTextChannelsByName(name, false).get(0);
    }

    public static ArrayList<String> getChannels() {
        ArrayList<String> ret = new ArrayList<>();
        for (TextChannel c : dGuild.getTextChannels()) {
            ret.add(c.getName());
        }
        return ret;
    }

    public static Member getMember(String name) {
        return dGuild.getMembersByName(name, false).get(0);
    }

    public static ArrayList<String> getMembers() {
        ArrayList<String> ret = new ArrayList<>();
        for (Member m : dGuild.getMembers()) {
            ret.add(m.getUser().getName());
        }
        return ret;
    }

    public static ArrayList<String> getRoles() {
        ArrayList<String> ret = new ArrayList<>();
        for (Role r : dGuild.getRoles()) {
            ret.add(r.getName());
        }
        return ret;
    }

    public static Role getRole(String name) {
        return dGuild.getRolesByName(name, false).get(0);
    }
}
