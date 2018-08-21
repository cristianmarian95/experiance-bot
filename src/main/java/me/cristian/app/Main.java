package me.cristian.app;

import me.cristian.app.commands.Command;
import me.cristian.app.listeners.MessageReceived;


import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import org.jetbrains.annotations.NotNull;
import javax.security.auth.login.LoginException;


public class Main {

    /**
     * Main function
     * @param args String
     */
    public static void main(String[] args) throws LoginException, RuntimeException, InterruptedException {
        JDA jda = new JDABuilder(AccountType.BOT).setToken(Configs.getToken()).buildBlocking();
        Command.registerCommands();
        registerEvents(jda);
    }

    /**
     * Register the events
     * @param jda JDA object instance
     */
    private static void registerEvents(@NotNull JDA jda) {
        jda.addEventListener(new MessageReceived());
    }
}
