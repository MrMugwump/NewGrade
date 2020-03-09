package com.example.whywontitwork.SyenrgyParsing;

import android.util.Log;

import com.example.whywontitwork.DataObjects.DataHolder;
import com.example.whywontitwork.MainActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Login {


    public static void login(String password, String id, MainActivity context) throws IOException{


        Connection.Response loginForm = Jsoup.connect("https://parent-portland.cascadetech.org/portland/PXP2_Login_Student.aspx?regenerateSessionId=True")
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36")
                .execute();

        Document loginDoc = loginForm.parse(); // this is the document that contains response html

        /*
            We need to get the values for the login form
         */
        Element e = loginDoc.select("input[id=__VIEWSTATE]").first();
        String viewState = e.attr("value");

        e = loginDoc.select("input[id=__EVENTVALIDATION]").first();
        String eventValidation = e.attr("value");

        /*
            We now have all the data needed to fill in the login form and login
         */
        Document doc = Jsoup.connect("https://parent-portland.cascadetech.org/portland/PXP2_Login_Student.aspx?regenerateSessionId=True")
                .data("__VIEWSTATE", viewState)             //Filling in the login form
                .data("__EVENTVALIDATION", eventValidation)
                .data("ctl00$MainContent$username", id)
                .data("ctl00$MainContent$password", password)
                .cookies(loginForm.cookies())
                .post(); //logs in

        String HomePageHtml = doc.toString();
        DataHolder.setDoc(doc);

        DataHolder.setLoginAutomatically(checkLogin(doc));
        if(!checkLogin(doc)) //Checks if you logged in before running anything else
            return;


        ParseGradebookUrl StringParserForGradeBookUrl = new ParseGradebookUrl(HomePageHtml);

        String gradeBookUrl = StringParserForGradeBookUrl.createGradeBookUrl();

        GradeBookParse.ConnectToGradesPage(loginForm, gradeBookUrl);

        Document GradeBookPage = GradeBookParse.ConnectToGradesPage(loginForm, gradeBookUrl);


        /*

        Checks if there is Course info saved in SharedPreferences, and loads it if it is present. If not present connects to synergy and resaves it

         */
        if(DataCaching.getSharedPreferences(context).contains("gPACacheOne")){
            DataHolder.setGpaArray(DataCaching.readGPAInfoFromCache(context, "gPACacheOne","GpaCacheTwo"));

        }
        else{
            DataHolder.setGpaArray(GpaParse.gpaParse(loginForm));
            DataCaching.getSharedPreferences(context).edit().remove("gPACacheOne").commit();
            DataCaching.getSharedPreferences(context).edit().remove("GpaCacheTwo").commit();
            DataCaching.saveGPAInfo(context, "gPACacheOne", "GpaCacheTwo");
        }


        if (DataCaching.getSharedPreferences(context).contains("courseCache")){
            DataHolder.setCourseDataObjects(DataCaching.readCourseInfoFromCache(context,"courseCache"));
        }
        else{
            DataHolder.setCourseDataObjects(GradeBookOrganizer.fillDataArray(GradeBookPage)); //Stores data as a static reference.
            DataCaching.getSharedPreferences(context).edit().remove("courseCache").commit();
            DataCaching.saveToCache(context,"courseCache");
        }
        if (DataHolder.getCourseDataObjects().length == 0)
            Log.d("Login error", "login: no data pulled from synergy");


    }

    public static boolean checkLogin(Document doc) {
        return !doc.toString().contains("Return to common login");
    }

}