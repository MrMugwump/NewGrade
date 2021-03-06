package com.example.whywontitwork.SyenrgyParsing;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

class GpaParse {

    private static Document connectToCourseHistoryPage(Connection.Response loginForm) throws IOException {
        String courseHistoryUrl = "https://parent-portland.cascadetech.org/portland/PXP2_CourseHistory.aspx?AGU=0";
        return Jsoup.connect(courseHistoryUrl)
                .cookies(loginForm.cookies())
                .post();
    }
    static String[] gpaParse(Connection.Response loginForm) throws IOException {
        Elements unweightedGpa = connectToCourseHistoryPage(loginForm).select("#ctl00_ctl00_MainContent_PXPMainContent_CourseHistoryContent > div.right-panel > div:nth-child(1) > div > span.gpa-score");
        Elements weightedGpa = connectToCourseHistoryPage(loginForm).select("#ctl00_ctl00_MainContent_PXPMainContent_CourseHistoryContent > div.right-panel > div:nth-child(2) > div > span.gpa-score");

        String unweightedGpaString = unweightedGpa.toString();
        String weightedGpaString = weightedGpa.toString();

        String crudeUnweightedGpaString = unweightedGpaString.replace("<span class=\"gpa-score\">","");
        String crudeWeightedGpaString = weightedGpaString.replace("<span class=\"gpa-score\">","");

        String pureUnweightedGpaString = crudeUnweightedGpaString.replace("</span>","");
        String pureWeightedGpaString = crudeWeightedGpaString.replace("</span>","");

        String[] gpaArray = new String[2];
        gpaArray[0] = pureUnweightedGpaString;
        gpaArray[1] = pureWeightedGpaString;

        return gpaArray;
    }

    public Connection.Response getLoginForm(Connection.Response loginForm){
        return loginForm;
    }

}