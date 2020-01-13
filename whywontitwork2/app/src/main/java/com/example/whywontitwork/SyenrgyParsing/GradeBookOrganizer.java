package com.example.whywontitwork.SyenrgyParsing;


import android.util.Log;

import com.example.whywontitwork.DataObjects.CourseDataObject;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class GradeBookOrganizer {

    static CourseDataObject[] fillDataArray(Document gradeBookPage){
        CourseDataObject[] courseDataObjects = new CourseDataObject[]{
                new CourseDataObject(), new CourseDataObject(), new CourseDataObject(), new CourseDataObject(),
                new CourseDataObject(), new CourseDataObject(), new CourseDataObject(), new CourseDataObject()
        }; //it doesn't like it if I don't declare all of these

        // HTML path selector that grabs the data table that contains all other required data
        Elements dataTable = gradeBookPage.select("#ctl00_ctl00_MainContent_PXPMainContent_repSchoolClasses_ctl00_ctl00_SchoolClassesPanel > table");
        // grabs names of all courses by selecting buttons with the class name "course-title" which have the course title as their text
        Elements courseTitles = dataTable.select("button.course-title");
        // grabs teacher name by selecting divisions with the class name "teacher" which have the teacher names
        Elements teacherNames = dataTable.select("div.teacher");
        // grabs the rooms the courses are in by selecting divisions with the class name "teacher-room" which have the rooms
        Elements roomNumbers = dataTable.select("div.teacher-room");
        // grabs the grade letters by selecting spans with the name mark or score
        Elements gradeLetters = dataTable.select("span.mark");
        Elements gradeNumbers = dataTable.select("span.score");


        for (int i = 0; i < 8; i++) {
            courseDataObjects[i].courseName = courseTitles.get(i).text();
            courseDataObjects[i].teacherName = teacherNames.get(i).text();
            courseDataObjects[i].room = roomNumbers.get(i).text();
            courseDataObjects[i].gradeLetter = gradeLetters.get(i).text();
            courseDataObjects[i].gradeScore = gradeNumbers.get(i).text();
        }

        return courseDataObjects;
    }

}