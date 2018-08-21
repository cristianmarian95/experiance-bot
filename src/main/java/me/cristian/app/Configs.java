package me.cristian.app;

public class Configs {

    /**
     * Set the bot configs to private and final
     */
    private static final String token = "NDgxMTYzMDc5OTU0OTg5MDU4.DlyVvw.MHt3J2hUtJchZIU_wx_s03KtzOM";
    private static final String prefix = "-";

    /**
     * Return the token
     * @return string token
     */
    static String getToken() {
        return token;
    }

    /**
     * Retrun the prefix
     * @return string prefix
     */
    public static String getPrefix() {
        return prefix;
    }
}