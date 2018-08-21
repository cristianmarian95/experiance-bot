package me.cristian.app.models;

public class MessageLogModel {

    private long discord_id;
    private long time;

    public MessageLogModel(long discord_id, long time){
        this.discord_id = discord_id;
        this.time = time;
    }

    public long getDiscord_id() {
        return discord_id;
    }

    public long getTime() {
        return time;
    }
}
