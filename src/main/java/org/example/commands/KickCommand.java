package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;

public class KickCommand implements Command {

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
                        error -> event.reply("Не удалось кикнуть пользователя: " + error.getMessage()).setEphemeral(true).queue()


                );
    }
}
