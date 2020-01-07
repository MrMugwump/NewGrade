package com.example.whywontitwork;

import android.widget.Switch;
import android.widget.TextView;

import com.example.whywontitwork.DataObjects.CourseDataObject;

public class UpdateUI {
    public static void changeTextviews(CourseView courseView, CourseDataObject[] courseDataObjects){
        TextView textView = courseView.findViewById(R.id.GPA);
        Switch toggle = courseView.findViewById(R.id.gpaSwitch);

        TextView courseGrade = courseView.findViewById(R.id.periodOneGrade);
        TextView courseName = courseView.findViewById(R.id.periodOneName);
        TextView teacherName = courseView.findViewById(R.id.periodOneTeacher);
        TextView roomNumber = courseView.findViewById(R.id.periodOneRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[0]);

        courseGrade = courseView.findViewById(R.id.periodTwoGrade);
        courseName = courseView.findViewById(R.id.periodTwoName);
        teacherName = courseView.findViewById(R.id.periodTwoTeacher);
        roomNumber = courseView.findViewById(R.id.periodTwoRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[1]);

        courseGrade = courseView.findViewById(R.id.periodThreeGrade);
        courseName = courseView.findViewById(R.id.periodThreeName);
        teacherName = courseView.findViewById(R.id.periodThreeTeacher);
        roomNumber = courseView.findViewById(R.id.periodThreeRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[2]);

        courseGrade = courseView.findViewById(R.id.periodFourGrade);
        courseName = courseView.findViewById(R.id.periodFourName);
        teacherName = courseView.findViewById(R.id.periodFourTeacher);
        roomNumber = courseView.findViewById(R.id.periodFourRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[3]);

        courseGrade = courseView.findViewById(R.id.periodFiveGrade);
        courseName = courseView.findViewById(R.id.periodFiveName);
        teacherName = courseView.findViewById(R.id.periodFiveTeacher);
        roomNumber = courseView.findViewById(R.id.periodFiveRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[4]);

        courseGrade = courseView.findViewById(R.id.periodSixGrade);
        courseName = courseView.findViewById(R.id.periodSixName);
        teacherName = courseView.findViewById(R.id.periodSixTeacher);
        roomNumber = courseView.findViewById(R.id.periodSixRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[5]);

        courseGrade = courseView.findViewById(R.id.periodSevenGrade);
        courseName = courseView.findViewById(R.id.periodSevenName);
        teacherName = courseView.findViewById(R.id.periodSevenTeacher);
        roomNumber = courseView.findViewById(R.id.periodSevenRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[6]);

        courseGrade = courseView.findViewById(R.id.periodEightGrade);
        courseName = courseView.findViewById(R.id.periodEightName);
        teacherName = courseView.findViewById(R.id.periodEightTeacher);
        roomNumber = courseView.findViewById(R.id.periodEightRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[7]);

    }
    private static void changeCourseText(TextView grade, TextView courseName, TextView teacherName, TextView roomNumber, CourseDataObject courseDataObject){
        grade.setText(courseDataObject.gradeScore);
        courseName.setText(courseDataObject.courseName);
        teacherName.setText(courseDataObject.teacherName);
        roomNumber.setText(courseDataObject.room);
    }
    
}