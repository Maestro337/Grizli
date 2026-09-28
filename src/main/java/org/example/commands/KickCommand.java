package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KickCommand extends ModerationCommand {

    private static final Logger log = LoggerFactory.getLogger(KickCommand.class);

    @Override
    protected Permission permission() {

        return Permission.KICK_MEMBERS;

    }

    @Override
    protected String denyMessage(){

        return "У тебя нету прав на исключение участников";

    }

    @Override
    protected void run(SlashCommandInteractionEvent event, Member executor) {

        User targetUser = event.getOption("user").getAsUser();

        String reason = event.getOption("reason") != null
                ? event.getOption("reason").getAsString()
                : "Причина указана";

        event.getGuild().kick(targetUser)
                .reason(reason)
                .queue(
                        success -> event.reply("Пользователь " + targetUser.getAsTag() + "Кикнуть. Причина: " + reason).queue(),
                        error -> {
                            log.error("Не удалось кикнуть пользователя {}", targetUser.getAsTag(), error);
                            event.reply("Не удалось кикнуть пользователя: " + error.getMessage()).setEphemeral(true).queue();
                        }
                );
    }

}
