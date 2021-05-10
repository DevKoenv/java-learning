package me.koenv.mw;

import me.koenv.mw.utils.NetworkUtils;
import org.json.JSONObject;

public class NetworkManager {
    private Main main = Main.getMain();
    private DesktopInfo DI = Main.getDesktopInfo();

    private JSONObject jsonData;
    private JSONObject registerData;

    public void run() {
        // Get JSON From URL
        String ipUrlData = NetworkUtils.getFromAPI(Main.getIpUrl());
        String registerUrlData = NetworkUtils.postToAPI(Main.getStorageUrl() + "auth/register", "{ \"username\": \"" + "d" +"\", \"password\": \"TestPass123$\" }");
        // Set Data To JSON Object
        jsonData = ipUrlData != null ? new JSONObject(ipUrlData) : new JSONObject("");
        registerData = registerUrlData != null ? new JSONObject(registerUrlData) : null;
    }

    public JSONObject getRegisterData() {
        return registerData;
    }

    public JSONObject getJsonData() {
        return jsonData;
    }

    public void setJsonData(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

    public void setRegisterData(JSONObject registerData) {
        this.registerData = registerData;
    }
}
