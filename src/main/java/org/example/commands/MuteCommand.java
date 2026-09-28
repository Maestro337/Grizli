package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class MuteCommand  extends ModerationCommand {

    private static final Logger log = LoggerFactory.getLogger(MuteCommand.class);

    @Override
    protected Permission permission() {

        return Permission.MODERATE_MEMBERS;

    }

    @Override
    protected String denyMessage() {

        return "У тебя нету прав на выдачу мута";

    }

    @Override
    protected void run(SlashCommandInteractionEvent event, Member executor) {

        Member targetMember = event.getOption("user").getAsMember();

        int minutes = (int) event.getOption("minutes").getAsLong();

        if (minutes <= 0 || minutes > 40320) {

            event.reply("Время мута должно быть от 1 минуты до 28 дней").setEphemeral(true).queue();
            return;

        }

        if (targetMember == null) {

            event.reply("Не удалось найти этого участника на сервере").setEphemeral(true).queue();
            return;
            
        }

        targetMember.timeoutFor(Duration.ofMinutes(minutes))
                .queue(
                        success -> event.reply("Пользователь " + targetMember.getUser().getAsTag() + " замучен на " + minutes + " минут").queue(),
                        error -> {
                            log.error("Не удалось замутить пользователя {}", targetMember.getUser().getAsTag(), error);
                            event.reply("Не удалось замутить пользователя: " + error.getMessage()).setEphemeral(true).queue();
                        }
                );
    }
}
