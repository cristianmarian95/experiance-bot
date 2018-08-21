package me.cristian.app.models;

public class ConfigModel {
    private int cool_down;
    private float experience_rate;
    private float coins_rate;

    public ConfigModel(int cool_down, float experience_rate, float coins_rate){
        this.cool_down = cool_down;
        this.experience_rate = experience_rate;
        this.coins_rate = coins_rate;
    }

    public float getCoins_rate() {
        return coins_rate;
    }

    public int getCool_down() {
        return cool_down;
    }

    public float getExperience_rate() {
        return experience_rate;
    }
}
