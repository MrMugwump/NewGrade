package com.example.whywontitwork.DataObjects;

public class DataHolder {
    private static CourseDataObject[] courseDataObjects;
    private static String[] gpaArray;

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
