package me.cristian.app.commands;

import me.cristian.app.utils.EmojiType;

public enum CommandType {

    INFO("Info",EmojiType.INFORMATION),
    FUN("Fun",EmojiType.GAME),
    MUSIC("Music",EmojiType.MUSIC),
    GAME("Game",EmojiType.GAME),
    NSFW("Nsfw",EmojiType.NSFW),
    ADMINISTRATIVE("Administrative",EmojiType.MODERATION),
    MODERATION("Moderation",EmojiType.ADMINISTRATION);

    private final String text;
    private final String emoji;

    CommandType(String text, String emoji){
        this.text = text;
        this.emoji = emoji;
    }

    public String getText() {
        return this.text;
    }

    public String getEmoji() {
        return this.emoji;
    }
}