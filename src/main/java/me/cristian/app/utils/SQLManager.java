package me.cristian.app.utils;

import me.cristian.app.models.ConfigModel;
import me.cristian.app.models.MessageLogModel;
import me.cristian.app.models.UserModel;

import java.sql.*;

public class SQLManager {

    private Connection connection;
    private static SQLManager instance = new SQLManager();

    public SQLManager() {}

    public static SQLManager getInstance() {
        return instance;
    }

    private void setConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discord_bot","root","");
        }
    }

    public void execute(String sql){
        try{
            setConnection();
            Statement smt = connection.createStatement();
            smt.execute(sql);
            smt.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public UserModel getUser(String discord_id){
        try {
            setConnection();
            Statement smt = connection.createStatement();
            ResultSet result = smt.executeQuery(String.format("SELECT * FROM users WHERE discord_id = %s LIMIT 1", discord_id));
            if(result.next()) {
                return new UserModel(result.getLong("discord_id"), result.getFloat("experience"), result.getFloat("coins"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ConfigModel getConfigs(){
        try {
            setConnection();
            Statement smt = connection.createStatement();
            ResultSet result = smt.executeQuery("SELECT * FROM configs WHERE id = 1 LIMIT 1");
            if(result.next()) {
                return new ConfigModel(result.getInt("cool_down"), result.getFloat("experience_rate"), result.getFloat("coins_rate"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public MessageLogModel getMessage(String discord_id){
        try {
            setConnection();
            Statement smt = connection.createStatement();
            ResultSet result = smt.executeQuery(String.format("SELECT * FROM message_log WHERE discord_id = %s LIMIT 1", discord_id));
            if(result.next()) {
                return new MessageLogModel(result.getLong("discord_id"), result.getLong("time"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
