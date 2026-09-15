package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.listeners.CommandListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        String token = System.getenv("DISCORD_TOKEN");

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new CommandListener())
                .build();

        jda.awaitReady();
        log.info("Grizli запущен как {} ", jda.getSelfUser().getName());

        for(Guild guild : jda.getGuilds()){

            guild.updateCommands().addCommands(

                    Commands.slash("kick", "Кикнуть участника с сервера")
                            .addOption(OptionType.USER, "user", "Кого кикнуть", true)
                            .addOption(OptionType.STRING, "reason", "Причина", false),
                    Commands.slash("ban", "Забанить участника сервера")
                            .addOption(OptionType.USER, "user", "Кого забанить", true)
                            .addOption(OptionType.STRING, "reason", "Причина", false),
                    Commands.slash("mute", "Замутить участника на время")
                            .addOption(OptionType.USER, "user", "Кого замутить", true)
                            .addOption(OptionType.INTEGER, "minutes", "На сколько минут", true)
            ).queue();
        }
        
    }
}