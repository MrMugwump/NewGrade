package com.example.whywontitwork.SyenrgyParsing;
import android.util.Log;

import com.example.whywontitwork.DataObjects.CourseDataObject;
import com.example.whywontitwork.DataObjects.DataHolder;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Login {
    private static final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" + "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
    private static final String USER_ID = "bbridenbaug7077";
    private static final String USER_PASSWORD = "Groovyman23";
    private static final String DEBUG_TAG = "WebScraperStuff";


    public static void login(String password, String id) throws IOException, InterruptedException {

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
        TimeUnit.SECONDS.sleep(1);

        ParseGradebookUrl StringParserForGradeBookUrl = new ParseGradebookUrl(HomePageHtml);
        String gradeBookUrl = StringParserForGradeBookUrl.createGradeBookUrl();
        GradeBookParse.ConnectToGradesPage(loginForm, gradeBookUrl);
        Document GradeBookPage = GradeBookParse.ConnectToGradesPage(loginForm, gradeBookUrl);

        DataHolder.setCourseDataObjects(GradeBookOrganizer.alternativeFillDataArray(GradeBookPage)); //Stores data as a static reference.
        DataHolder.setGpaArray(GpaParse.gpaparse(loginForm));

    }

}