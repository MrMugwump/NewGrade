package com.example.whywontitwork.SyenrgyParsing;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

class GradeBookParse {
    //Uses GradeBook URL and cookies from loginForm to open the GradesPage
    static Document ConnectToGradesPage(Connection.Response loginForm, String GradeBookUrl) throws IOException {
        ParseGradebookUrl parseGradebookUrl = new ParseGradebookUrl(GradeBookUrl);

        return Jsoup.connect(parseGradebookUrl.createGradeBookUrl())
                .cookies(loginForm.cookies())
                .post();
    }
}
