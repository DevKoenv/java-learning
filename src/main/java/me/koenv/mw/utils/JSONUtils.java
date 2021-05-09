package me.koenv.mw.utils;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class JSONUtils {

    public static String serializeObject(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object deserializeObject(String response, Class<?> classType){
        Gson gson = new Gson();
        return gson.fromJson(response, classType);
    }

    public static Object deserializeArrayObject(String response, Class<?> classType) {
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray(response);
            return gson.fromJson(jsonArray.getJSONObject(0).toString(), classType);
        } catch (JSONException e) {
            e.fillInStackTrace();
            return null;
        }
    }

}
