package com.example.whywontitwork;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.whywontitwork.DataObjects.DataHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static android.content.Context.MODE_PRIVATE;

public class DataCaching {
    static GsonBuilder gsonb = new GsonBuilder();
    static Gson mGson = gsonb.create();

    private static boolean saveToCache(MainActivity context, DataHolder dataObject, String courseCache) {
        SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        try {
            String writeValue = mGson.toJson(dataObject);
            editor.putString(courseCache, writeValue);
            editor.commit();
            return true;
        }
        catch(Exception e)
        {
            Log.d("Cache Error", "saveToCache: Failed");
            return false;
        }
    }

    private static DataHolder readFromCache(MainActivity context, String courseCache) {
        SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String loadValue = sharedPreferences.getString(courseCache, "");
        DataHolder holderOfTheData = mGson.fromJson(loadValue, DataHolder.class);
        return holderOfTheData;
    }
}