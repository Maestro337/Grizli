package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Main {
    public static void main(String[] args) {

        String token = System.getenv("DISCORD_TOKEN");

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .build();

        jda.awaitReady();
        System.out.println("Grizli запущен как " + jda.getSelfUser().getName());
        
    }
}