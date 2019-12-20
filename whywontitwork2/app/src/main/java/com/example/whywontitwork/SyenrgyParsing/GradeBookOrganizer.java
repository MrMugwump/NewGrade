package com.example.whywontitwork.SyenrgyParsing;


import com.example.whywontitwork.DataObjects.CourseDataObject;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class GradeBookOrganizer {

    // Takes the required information on the Gradebook page and puts it into a 2D array

    //Creates an array of the Course data objects and returns it
    /*public static CourseDataObject[] fillCourseArray(Document gradeBookPage ){
        return alternativeFillDataArray(gradeBookPage);
    }*/
    /*public static CourseDataObject[] fillDataArray(Document gradeBookPage){
        //Creates a 2D ArrayList of strings and fills it by grabbing information from the gradeBookPage Document
        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        dataArray.add(new ArrayList<String>());
        dataArray.add(new ArrayList<String>());
        dataArray.add(new ArrayList<String>());
        dataArray.add(new ArrayList<String>());
        dataArray.add(new ArrayList<String>());
        dataArray.add(new ArrayList<String>());
        // HTML path selector that grabs the data table that contains all other required data
        Elements dataTable = gradeBookPage.select("#ctl00_ctl00_MainContent_PXPMainContent_repSchoolClasses_ctl00_ctl00_SchoolClassesPanel > table");
        // grabs names of all courses by selecting buttons with the class name "course-title" which have the course title as their text
        Elements courseTitles = dataTable.select("button.course-title");
        // grabs teacher name by selecting divisions with the class name "teacher" which have the teacher names
        Elements teacherNames = dataTable.select("div.teacher");
        // grabs the rooms the courses are in by selecting divisions with the class name "teacher-room" which have the rooms
        Elements roomNumbers = dataTable.select("div.teacher-room");
        // grabs the grade letters by selecting
        Elements gradeLetters = dataTable.select("span.mark");
        Elements gradeNumbers = dataTable.select("span.score");
        for (Element Element:courseTitles
        ) {
            dataArray.get(0).add(Element.text());
        }
        for (Element Element:teacherNames
        ) {
            dataArray.get(1).add(Element.text());
        } for (Element Element:roomNumbers
        ) {
            dataArray.get(2).add(Element.text());
        } for (Element Element:gradeLetters
        ) {
            dataArray.get(3).add(Element.text());
        } for (Element Element:gradeNumbers
        ) {
            dataArray.get(4).add(Element.text());
        }
        // Creates an ArrayList full of CourseDataObjects and sets their values using data from the dataArray 2D ArrayList
        CourseDataObject[] courseDataObjects = new CourseDataObject[8];
        String thing = courseTitles.get(0).text();
        for (int i = 0; i < 8 ; i++) {
            courseDataObjects[i].courseName = dataArray.get(0).get(i);
            courseDataObjects[i].teacherName = dataArray.get(1).get(i);
            courseDataObjects[i].roomNumber = Integer.parseInt(dataArray.get(2).get(i));
            courseDataObjects[i].gradeLetter = dataArray.get(3).get(i);
            courseDataObjects[i].grade = Float.parseFloat(dataArray.get(4).get(i));
        }
        // courseArrayList is returned to fillCourseArray so it can be returned to main
        return courseDataObjects;
    }*/

    static CourseDataObject[] alternativeFillDataArray(Document gradeBookPage){
        CourseDataObject[] courseDataObjects = new CourseDataObject[]{
                new CourseDataObject(), new CourseDataObject(), new CourseDataObject(), new CourseDataObject(),
                new CourseDataObject(), new CourseDataObject(), new CourseDataObject(), new CourseDataObject()
        };

        // HTML path selector that grabs the data table that contains all other required data
        Elements dataTable = gradeBookPage.select("#ctl00_ctl00_MainContent_PXPMainContent_repSchoolClasses_ctl00_ctl00_SchoolClassesPanel > table");
        // grabs names of all courses by selecting buttons with the class name "course-title" which have the course title as their text
        Elements courseTitles = dataTable.select("button.course-title");
        // grabs teacher name by selecting divisions with the class name "teacher" which have the teacher names
        Elements teacherNames = dataTable.select("div.teacher");
        // grabs the rooms the courses are in by selecting divisions with the class name "teacher-room" which have the rooms
        Elements roomNumbers = dataTable.select("div.teacher-room");
        // grabs the grade letters by selecting
        Elements gradeLetters = dataTable.select("span.mark");
        Elements gradeNumbers = dataTable.select("span.score");
        for (int i = 0; i < 8; i++) {
            courseDataObjects[i].courseName = courseTitles.get(i).text();
        }
        for (int i = 0; i < 8; i++) {
            courseDataObjects[i].teacherName = teacherNames.get(i).text();
        }
        for (int i = 0; i < 8; i++) {
            courseDataObjects[i].room = roomNumbers.get(i).text();
        }
        for (int i = 0; i < 8; i++) {
            courseDataObjects[i].gradeLetter = gradeLetters.get(i).text();
        }
        for (int i = 0; i < 8; i++) {
            courseDataObjects[i].gradeScore = gradeNumbers.get(i).text();
        }

        return courseDataObjects;
    }

}