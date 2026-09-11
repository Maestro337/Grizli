package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;

import java.time.Duration;


public class MuteCommand  implements Command{

    @Override
    public void execute(SlashCommandInteractionEvent event){

        Member executor = event.getMember();

        if (executor == null || !PermissionChecker.hasPermission(executor, Permission.MODERATE_MEMBERS)) {
            event.reply("У тебя нету прав на выдачу мута").setEphemeral(true).queue();
            return;
        }

        Member targetMember = event.getOption("user").getAsMember();
        int minutes = (int) event.getOption("minutes").getAsLong();

        if (targetMember == null) {
            event.reply("Не удалось найти этого участника на сервере").setEphemeral(true).queue();
            return;
        }

        targetMember.timeoutFor(Duration.ofMinutes(minutes))
                .queue(
                        success -> event.reply("Пользователь " + targetMember.getUser().getAsTag() + " замучен на " + minutes + " минут").queue(),
                        error -> event.reply("Не удалось замутить пользователя: " + error.getMessage()).setEphemeral(true).queue()
                );
    }
}
