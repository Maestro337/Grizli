package org.example.listeners;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.util.PermissionChecker;



public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("kick")){
            handleKick(event);
        }
    }

    private void handleKick(SlashCommandInteractionEvent event) {

        Member executor = event.getMember();

        if(executor == null || !PermissionChecker.hasPermission(executor, Permission.KICK_MEMBERS)){
            event.reply("У тебя нету прав на исключение участников").setEphemeral(true).queue();
            return;
        }

        //todo
        event.reply("Команды /kick пока не релизована до конца").setEphemeral(true).queue();

    }
}