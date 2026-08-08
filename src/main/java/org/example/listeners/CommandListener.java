package org.example.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.commands.BanCommand;
import org.example.commands.Command;
import org.example.commands.KickCommand;

import java.util.Map;


public class CommandListener extends ListenerAdapter {


    private final Map<String, Command> commands = Map.of(

            "kick", new KickCommand(),
            "ban", new BanCommand()

    );

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        Command command = commands.get(event.getName());

        if (command != null) {
            command.execute(event);
        }

    }
}