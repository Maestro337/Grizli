package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.util.PermissionChecker;

public abstract class ModerationCommand implements Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        Member executor = event.getMember();

        if (executor == null || !PermissionChecker.hasPermission(executor, permission())) {


            event.reply(denyMessage()).setEphemeral(true).queue();
            return;

        }

        run(event, executor);

    }

    protected abstract Permission permission();

    protected abstract String denyMessage();

    protected abstract void run(SlashCommandInteractionEvent event, Member executor);
}