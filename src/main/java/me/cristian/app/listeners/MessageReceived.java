package me.cristian.app.listeners;

import me.cristian.app.Configs;
import me.cristian.app.models.ConfigModel;
import me.cristian.app.models.MessageLogModel;
import me.cristian.app.models.UserModel;
import me.cristian.app.utils.EmojiType;
import me.cristian.app.commands.Command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import me.cristian.app.utils.SQLManager;

import java.awt.*;
import java.util.Date;

public class MessageReceived extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Check if the sender is the bot if yes return
        if (event.getAuthor().isBot()) {
            return;
        }

        // Check if the channel is private
        if(!event.getMessage().isFromType(ChannelType.PRIVATE)){
            UserModel user = SQLManager.getInstance().getUser(event.getAuthor().getId());
            ConfigModel config = SQLManager.getInstance().getConfigs();
            long date = new Date().getTime();

            if(user == null){
                try {
                    SQLManager.getInstance().execute(String.format("INSERT INTO users(discord_id, experience, coins) VALUES(%s, %s, %s)", event.getAuthor().getId(), 0, 0));
                    SQLManager.getInstance().execute(String.format("INSERT INTO message_log(discord_id, time) VALUES(%s, %s)", event.getAuthor().getId(), date));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                MessageLogModel msg = SQLManager.getInstance().getMessage(event.getAuthor().getId());
                if((Math.abs(date - msg.getTime()) / 1000) > config.getCool_down()){
                    float exp = user.getExperience() + config.getExperience_rate();
                    try {
                        SQLManager.getInstance().execute(String.format("UPDATE users SET experience = %s", exp));
                        SQLManager.getInstance().execute(String.format("UPDATE message_log SET time = %s WHERE discord_id = %s", date, event.getAuthor().getId()));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }


            // Check if the message start with the prefix if not return
            if(!event.getMessage().getContentDisplay().startsWith(Configs.getPrefix())) {
                return;
            }

            // Get the message without the prefix
            String message = event.getMessage().getContentDisplay().substring(Configs.getPrefix().length());

            // Check if the message is empty
            if(!message.isEmpty()) {

                String cmd[] = message.split(" ");
                EmbedBuilder eb = new EmbedBuilder().setDescription("This command doesn't exist.").setColor(Color.red);

                // Check if the the command exists
                for (Command c : Command.getCommandList()) {
                    if (c.getName().equals(cmd[0])) {
                        c.execute(cmd, event.getMessage());
                        return;
                    }
                }

                // Show message error
                event.getChannel().sendMessage(eb.build()).queue();
                return;
            }
        }

        // Error if you access the bot out of the server
        EmbedBuilder eb = new EmbedBuilder().setDescription("I do not work in private channels. Come and find me on community server " + EmojiType.WINK).setColor(Color.red);
        event.getChannel().sendMessage(eb.build()).queue();
    }

}
