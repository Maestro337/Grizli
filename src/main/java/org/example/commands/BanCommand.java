package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BanCommand implements Command {

    private static final Logger log = LoggerFactory.getLogger(BanCommand.class);

    @Override
    public void execute(SlashCommandInteractionEvent event){

        Member executor = event.getMember();

        if(executor == null || !PermissionChecker.hasPermission(executor, Permission.BAN_MEMBERS )){

            event.reply("У тебя нету прав на бан участников").setEphemeral(true).queue();
            return;

        }

        User targetUser = event.getOption("user").getAsUser();
        String reason = event.getOption("reason") != null
                ?event.getOption("reason").getAsString()
                : "Причина не указана";

        event.getGuild().ban(targetUser, 0, TimeUnit.DAYS)
                .reason(reason)
                .queue(
                        success -> event.reply("Пользователь" + targetUser.getAsTag() + "забанен. Причина: " + reason).queue(),
                        error -> event.reply(" Не удалось забанить пользователя: " + error.getMessage()).setEphemeral(true).queue()
                );
    }

}
