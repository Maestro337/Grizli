package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.listeners.CommandListener;


public class Main {
    public static void main(String[] args) {

        String token = System.getenv("DISCORD_TOKEN");

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .build();

        jda.awaitReady();
        System.out.println("Grizli запущен как " + jda.getSelfUser().getName());

        for(Guild guild : jda.getGuilds()){

            guild.updateCommands().addCommands(

                    Commands.slash("kick", "Кикнуть участника с сервера")
                            .addOption(OptionType.USER, "user", "Кого кикнуть", true)
                            .addOption(OptionType.STRING, "reason", "Причина", false),
                    Commands.slash("ban", "Забанить участника сервера")
                            .addOption(OptionType.USER, "user", "Кого забанить", true)
                            .addOption(OptionType.STRING, "reason", "Причина", false)

            ).queue();
        }
        
    }
}