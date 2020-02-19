package com.example.whywontitwork.SyenrgyParsing;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.whywontitwork.DataObjects.CourseDataObject;
import com.example.whywontitwork.DataObjects.DataHolder;
import com.example.whywontitwork.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static android.content.Context.MODE_PRIVATE;

class DataCaching {
    static GsonBuilder gsonb = new GsonBuilder();
    static Gson mGson = gsonb.create();


     static boolean saveToCache(MainActivity context , String courseCache) {
        SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            String writeValue = mGson.toJson(DataHolder.getCourseDataObjects());
            editor.putString(courseCache, writeValue);
            editor.commit();
            Log.d("DataCaching", "saveToCache: Save Completed ");
            System.out.println(writeValue);
            return true;
        }
        catch(Exception e)
        {
            Log.d("Cache Error", "saveToCache: Failed");
            return false;
        }
    }

     static CourseDataObject[] readFromCache(MainActivity context, String courseCache) {
         SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String loadValue = sharedPreferences.getString(courseCache, "");
        CourseDataObject[] holderOfTheData = mGson.fromJson(loadValue, CourseDataObject[].class);
         Log.d("DataCaching", "readFromCache: Copied Data from cache");
         return holderOfTheData;
    }
    static SharedPreferences getSharedPreferences(MainActivity context){
        return  context.getPreferences(MODE_PRIVATE);
    }

}