package com.example.whywontitwork.DataObjects;

import org.jsoup.nodes.Document;

public class DataHolder {
    private static CourseDataObject[] courseDataObjects;
    private static String[] gpaArray;
    private static Document doc;
    private static int courseChosen;
    private static boolean loginAutomatically = true;

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        DataHolder.loggedIn = loggedIn;
    }

    private static boolean loggedIn = false;

    public static int getCourseChosen() {
        return courseChosen;
    }

    public static boolean isLoginAutomatically() {
        return loginAutomatically;
    }

    public static void setLoginAutomatically(boolean loginAutomatically) {
        DataHolder.loginAutomatically = loginAutomatically;
    }

    public static void setCourseChosen(int courseChosen) {
        DataHolder.courseChosen = courseChosen;
    }


    public static Document getDoc() {
        return doc;
    }

    public static void setDoc(Document doc) {
        DataHolder.doc = doc;
    }

    public static String[] getGpaArray() {
        return gpaArray;
    }

    public static void setGpaArray(String[] gpaArray) {
        DataHolder.gpaArray = gpaArray;
    }

    public static void setCourseDataObjects(CourseDataObject[] courseDataObjects){
        DataHolder.courseDataObjects = courseDataObjects;
    }
    public static CourseDataObject[] getCourseDataObjects(){
        return courseDataObjects;
    }
}
