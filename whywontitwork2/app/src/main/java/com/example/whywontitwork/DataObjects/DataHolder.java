package com.example.whywontitwork.DataObjects;

import org.jsoup.nodes.Document;

public class DataHolder {
    private static CourseDataObject[] courseDataObjects;
    private static String[] gpaArray;
    private static Document doc;
    private static int courseChosen;

    public static int getCourseChosen() {
        return courseChosen;
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
