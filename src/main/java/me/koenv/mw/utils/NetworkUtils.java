package me.koenv.mw.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NetworkUtils {

    /**
     * The getFromAPI method can be used to retrieve data from the API.
     * Our API Will return different kinds of data depending on what is provided on the specific route you use.
     * @param route is the used route to get data from the API
     * @return the response the API gave, in most cases this is going to be JSON.
     */
    public static String getFromAPI(final String route) {

        // StringBuilder in which the response of the method will be put.
        final StringBuilder response = new StringBuilder();

        // Create a new thread, as networking is not allowed on the main thread.
        final Thread thread = new Thread(() -> {
            // Creating HttpURLConnection variable for later usage
            HttpURLConnection con = null;
            try {
                // Instantiate new URL with our route variable.
                URL url = new URL(route);
                // Instantiate our HttpURLConnection variable with our URL variable.
                // After instantiating, we set its properties.
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept","application/json");
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


    /**
     * The postToAPI method can be used to post data to the API.
     * Our API Will return different kinds of data depending on what is provided on the specific route you use.
     * @param route is the used route to get data from the API
     * @param json this is the JSON Object / Array as string that will be posted to the API
     * @return the response the API gave, in most cases this is going to be JSON as well, or a status code of some sort.
     */
    public static String postToAPI(final String route, final String json) {

        final StringBuilder response = new StringBuilder();
        final Thread thread = new Thread(() -> {
            HttpsURLConnection con = null;
            try {
                URL url = new URL(route);
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept","application/json");
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);

                DataOutputStream os = new DataOutputStream(con.getOutputStream());
                os.writeBytes(json);

                os.flush();
                os.close();

                getResponse(con, response);

            } catch (Exception e) {
                e.printStackTrace();
            } if (con != null) {
                con.disconnect();
            }
        });
        thread.start();
        try {
            thread.join();
            return response.toString();
        } catch(InterruptedException e) {
            e.getCause();
            return null;
        }
    }

    private static String putToAPI(final String route){

        final StringBuilder response = new StringBuilder();
        final Thread thread = new Thread(() -> {
            HttpsURLConnection con = null;
            try {
                URL url = new URL(route);

                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept","application/json");
                con.setDoInput(true);

                getResponse(con, response);
            } catch(Exception e){
                e.fillInStackTrace();
            } if (con != null) {
                con.disconnect();
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

    private static String deleteFromAPI(final String route){

        final StringBuilder response = new StringBuilder();
        final Thread thread = new Thread(() -> {
            HttpsURLConnection con = null;
            try {
                URL url = new URL(route);

                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("DELETE");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept","application/json");
                con.setDoInput(true);

                getResponse(con, response);

            } catch(Exception e){
                e.fillInStackTrace();
            } if (con != null) {
                con.disconnect();
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
