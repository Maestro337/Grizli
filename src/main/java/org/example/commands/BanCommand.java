package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BanCommand extends ModerationCommand {

    private static final Logger log = LoggerFactory.getLogger(BanCommand.class);

    @Override
    protected Permission permission() {

        return Permission.BAN_MEMBERS;

    }

    @Override
    protected String denyMessage() {

        return "У тебя нету прав на бан участников";

    }

    @Override
    protected void run(SlashCommandInteractionEvent event, Member executor) {

        User targetUser = event.getOption("user").getAsUser();

        String reason = event.getOption("reason") != null
                ? event.getOption("reason").getAsString()
                : "Причина не указана";

        event.getGuild().ban(targetUser, 0, TimeUnit.DAYS)
                .reason(reason)
                .queue(
                        success -> event.reply("Пользователь" + targetUser.getAsTag() + "забанен. Причина: " + reason).queue(),
                        error -> {
                            log.error("Не удалось забанить пользователя {}", targetUser.getAsTag(), error);
                            event.reply(" Не удалось забанить пользователя: " + error.getMessage()).setEphemeral(true).queue();
                        }
                );
    }

}
