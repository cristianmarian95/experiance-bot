package me.cristian.app.commands.base;

import me.cristian.app.commands.Command;
import me.cristian.app.commands.CommandType;
import me.cristian.app.models.ConfigModel;
import me.cristian.app.models.UserModel;
import me.cristian.app.utils.SQLManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;


public class Converse extends Command {
    private final static String help = "Converse the experience to coins.";

    public Converse(String name, CommandType type){
        super(name, type, help);
    }

    public void execute(String[] args, Message message) {
        EmbedBuilder content = new EmbedBuilder();
        UserModel userModel = SQLManager.getInstance().getUser(message.getAuthor().getId());
        ConfigModel configModel = SQLManager.getInstance().getConfigs();
        float coins = userModel.getExperience() * configModel.getCoins_rate();
        try {
            SQLManager.getInstance().execute(String.format("UPDATE users SET experience = 0, coins = %f WHERE discord_id = %s", (coins + userModel.getCoins()), userModel.getDiscord_id()));
        }catch (Exception e){ e.printStackTrace(); }
        content.setColor(Color.green);
        content.setDescription(String.format("%s's earn %f coins", message.getAuthor().getName(), coins));
        message.getChannel().sendMessage(content.build()).queue();
    }
}
