package me.cristian.app.commands;

import java.util.ArrayList;

import me.cristian.app.commands.base.Balance;
import me.cristian.app.commands.base.Converse;
import me.cristian.app.commands.base.HelpCommand;
import me.cristian.app.commands.base.SetConfigs;
import net.dv8tion.jda.core.entities.Message;

public abstract class Command {


    private final String name;
    private final String help;
    private final CommandType type;
    private static ArrayList<Command> commandList = new ArrayList<Command>();

    /**
     * Create a new command instance
     * @param name String
     * @param type CommandType
     * @param help String
     */
    public Command(String name, CommandType type, String help) {
        this.name = name;
        this.type = type;
        this.help = help;
        commandList.add(this);
    }

    /**
     * @param args String
     * @param message MessageReceived
     */
    public abstract void execute(String args[], Message message);

    /**
     * Get the command name
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the command type
     * @return CommandType
     */
    public CommandType getType() {
        return this.type;
    }

    /**
     * Return the command usage
     * @return String
     */
    public String getHelp() {
        return this.help;
    }

    /**
     * Return all the registered commands
     * @return ArrayList
     */
    public static ArrayList<Command> getCommandList(){
        return commandList;
    }

    /**
     * Function used to register all the commands
     */
    public static void registerCommands(){
        new HelpCommand("help", CommandType.ADMINISTRATIVE);
        new Balance("balance", CommandType.ADMINISTRATIVE);
        new SetConfigs("config", CommandType.ADMINISTRATIVE);
        new Converse("getcoins", CommandType.ADMINISTRATIVE);
    }
}