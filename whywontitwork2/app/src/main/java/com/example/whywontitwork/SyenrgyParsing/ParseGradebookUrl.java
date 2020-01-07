package com.example.whywontitwork.SyenrgyParsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParseGradebookUrl {
    private final String homePageUrl;
    ParseGradebookUrl(String homePageUrl){
        this.homePageUrl = homePageUrl;
    }

    //Uses a RegX function to find the gradebook Url and constructs it as a String
    private String studentGuNumber(){

        Pattern MY_PATTERN = Pattern.compile("(?<=src=\"Photos)(.*)(?=_Photo.PNG\")");
        String serialNumber = "";
        Matcher m = MY_PATTERN.matcher(homePageUrl);
        if (m.find()) {
            serialNumber = m.group(1);

        }
        return serialNumber;
    }

    final String createGradeBookUrl(){

        StringBuilder GradeBookUrl = new StringBuilder("https://parent-portland.cascadetech.org/portland/PXP2_Gradebook.aspx?AGU=0&studentGU=");

        for (int i = 4; i < studentGuNumber().length(); i++) {
            GradeBookUrl.append(studentGuNumber().charAt(i));
        }
        return GradeBookUrl.toString();
    }
}