package me.cristian.app.models;

public class UserModel {

    private long discord_id;
    private float experience;
    private float coins;

    public UserModel(long discord_id, float experience, float coins){
        this.discord_id = discord_id;
        this.experience = experience;
        this.coins = coins;
    }

    public long getDiscord_id() {
        return discord_id;
    }

    public float getExperience() {
        return experience;
    }

    public float getCoins() {
        return coins;
    }
}
