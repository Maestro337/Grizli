package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickCommand implements Command {

    private static final Logger log = LoggerFactory.getLogger(KickCommand.class);

    @Override
    public void execute(SlashCommandInteractionEvent event){

        Member executor = event.getMember();

        if(executor == null || !PermissionChecker.hasPermission(executor, Permission.KICK_MEMBERS)){
            event.reply("У тебя нету прав на исключение участников").setEphemeral(true).queue();
            return;
        }

        User targetUser = event.getOption("user").getAsUser();
        String reason = event.getOption("reason") != null
                ? event.getOption("reason").getAsString()
                : "Причина указана";

        event.getGuild().kick(targetUser)
                .reason(reason)
                .queue(

                  success -> event.reply("Пользователь " + targetUser.getAsTag() + "Кикнуть. Причина: " + reason).queue(),
                        error ->{
                                log.error("Не удалось кикнуть пользователя {}", targetUser.getAsTag(), error);
                                event.reply("Не удалось кикнуть пользователя: " + error.getMessage()).setEphemeral(true).queue();}

                );
    }
}
