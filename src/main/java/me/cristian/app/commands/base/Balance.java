package me.cristian.app.commands.base;

import me.cristian.app.commands.Command;
import me.cristian.app.commands.CommandType;
import me.cristian.app.models.UserModel;
import me.cristian.app.utils.SQLManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;

public class Balance extends Command {

    private final static String help = "Show balance.";

    public Balance(String name, CommandType type){
        super(name, type, help);
    }

    public void execute(String[] args, Message message) {
        EmbedBuilder content = new EmbedBuilder();
        UserModel userModel = SQLManager.getInstance().getUser(message.getAuthor().getId());
        content.setColor(Color.green);
        content.setTitle(message.getAuthor().getName() + "'s balance");
        content.addField("Experience", String.valueOf(userModel.getExperience()), true);
        content.addField("Coins", String.valueOf(userModel.getCoins()), true);
        message.getChannel().sendMessage(content.build()).queue();
    }

}
