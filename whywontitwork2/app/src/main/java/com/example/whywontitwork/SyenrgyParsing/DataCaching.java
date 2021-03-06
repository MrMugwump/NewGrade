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

 public class  DataCaching {
    static GsonBuilder gsonb = new GsonBuilder();
    static Gson mGson = gsonb.create();


     static boolean saveToCache(MainActivity context , String courseCache) {
        SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            String writeValue = mGson.toJson(DataHolder.getCourseDataObjects());
            editor.putString(courseCache, writeValue);
            editor.commit();
            Log.d("DataCaching", "saveToCache: CourseInfoSave Completed ");
            return true;
        }
        catch(Exception e)
        {
            Log.d("Cache Error", "saveToCache: Failed");
            return false;
        }
    }

    static boolean saveGPAInfo(MainActivity context, String gPACacheOne, String gPACacheTwo){
         SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         try{
             String writeValue1 = DataHolder.getGpaArray()[0];
             String writeValue2 = DataHolder.getGpaArray()[1];
             editor.putString(gPACacheOne, writeValue1);
             editor.putString(gPACacheTwo,writeValue2);
             editor.commit();

             return true;
         }
         catch(Exception e){
             Log.d("Cache Error", "saveGPAInfo: Failed");
             return false;
        }
     }


    static CourseDataObject[] readCourseInfoFromCache(MainActivity context, String courseCache) {
         SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         String loadValue = sharedPreferences.getString(courseCache, "");
         CourseDataObject[] holderOfTheData = mGson.fromJson(loadValue, CourseDataObject[].class);
         Log.d("DataCaching", "readCourseInfoFromCache: Copied Data from cache");
         return holderOfTheData;
     }
    static String[] readGPAInfoFromCache(MainActivity context, String gPACacheOne,String gPACacheTwo){
        SharedPreferences sharedPreferences = context.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String loadValueOne = sharedPreferences.getString(gPACacheOne, "");
        String loadValueTwo = sharedPreferences.getString(gPACacheTwo, "");
        String[] holderOfTheData = new String[2];
        holderOfTheData[0] = loadValueOne;
        holderOfTheData[1] = loadValueTwo;
        return holderOfTheData;
    }


     static SharedPreferences getSharedPreferences(MainActivity context){
        return  context.getPreferences(MODE_PRIVATE);
    }






 }