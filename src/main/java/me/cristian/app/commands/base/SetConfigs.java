package me.cristian.app.commands.base;

import me.cristian.app.commands.Command;
import me.cristian.app.commands.CommandType;
import me.cristian.app.models.ConfigModel;
import me.cristian.app.utils.SQLManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import java.awt.*;

public class SetConfigs extends Command {

    private final static String help = "Set the bot configs.";

    public SetConfigs(String name, CommandType type){
        super(name, type, help);
    }

    public void execute(String[] args, Message message) {

        EmbedBuilder content = new EmbedBuilder();
        ConfigModel configModel = SQLManager.getInstance().getConfigs();

        if(args.length >= 2 && args.length < 4) {

            if (args[1].equals("info")) {
                content.setColor(Color.green);
                content.setTitle("Experience and coins configs");
                content.addField("Experience rate", String.format("%f", configModel.getExperience_rate()), true);
                content.addField("Coins rate", String.format("%f", configModel.getCoins_rate()), true);
                content.addField("Cool down", String.format("%d", configModel.getCool_down()), true);
                message.getChannel().sendMessage(content.build()).queue();
                return;
            }

            if (args[1].equals("setxp") && !args[2].isEmpty()) {
                try {
                    SQLManager.getInstance().execute(String.format("UPDATE configs SET experience_rate = %s WHERE id = 1", args[2]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                content.setColor(Color.green);
                content.setTitle("Experience rate updated!");
                message.getChannel().sendMessage(content.build()).queue();
                return;
            }

            if (args[1].equals("setcoins") && !args[2].isEmpty()) {
                try {
                    SQLManager.getInstance().execute(String.format("UPDATE configs SET coins_rate = %s WHERE id = 1", args[2]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                content.setColor(Color.green);
                content.setTitle("Coins rate updated!");
                message.getChannel().sendMessage(content.build()).queue();
                return;
            }

            if (args[1].equals("setcooldown") && !args[2].isEmpty()) {
                try {
                    SQLManager.getInstance().execute(String.format("UPDATE configs SET cool_down = %s WHERE id = 1", args[2]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                content.setColor(Color.green);
                content.setTitle("Cool down updated!");
                message.getChannel().sendMessage(content.build()).queue();
                return;
            }
        }

        content.setColor(Color.RED);
        content.setDescription("Usage: -config <info> | <setxp> <float> | <setcoins> <float> | <setcooldown> <int>");
        message.getChannel().sendMessage(content.build()).queue();
    }

}
