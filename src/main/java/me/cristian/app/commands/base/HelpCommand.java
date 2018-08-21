package me.cristian.app.commands.base;

import me.cristian.app.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import me.cristian.app.commands.CommandType;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;

public class HelpCommand extends Command {

    private final static String help = "Provide a list of commands.";

    public HelpCommand(String name, CommandType type){
        super(name, type, help);
    }

    public void execute(String[] args, Message message) {
        EmbedBuilder content = new EmbedBuilder();
        content.setColor(Color.green);
        content.setTitle(message.getGuild().getName() + " help");

        for(Command c : Command.getCommandList()){
            content.addField("-" + c.getName(), c.getHelp(), false);
        }

        message.getChannel().sendMessage(content.build()).queue();
    }
}