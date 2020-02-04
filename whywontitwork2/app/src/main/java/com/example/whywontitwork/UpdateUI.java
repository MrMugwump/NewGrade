package com.example.whywontitwork;

import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.whywontitwork.DataObjects.CourseDataObject;

public class UpdateUI {
    static void changeUIObjects(CourseView courseView, CourseDataObject[] courseDataObjects){
        changeTextViews(courseView, courseDataObjects);
        changeGradeImages(courseView, courseDataObjects);
    }
    private static void changeTextViews(CourseView courseView, CourseDataObject[] courseDataObjects){
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

    private static void changeGradeImages(CourseView courseView, CourseDataObject[] courseDataObjects){
        ImageView[] gradeLetters = new ImageView[] {courseView.findViewById(R.id.periodOneLetter), courseView.findViewById(R.id.periodTwoLetter),
                courseView.findViewById(R.id.periodThreeLetter), courseView.findViewById(R.id.periodFourLetter),
                courseView.findViewById(R.id.periodFiveLetter), courseView.findViewById(R.id.periodSixLetter),
                courseView.findViewById(R.id.periodSevenLetter), courseView.findViewById(R.id.periodEightLetter)};
        for (int i = 0; i < gradeLetters.length; i++) {
            switch (courseDataObjects[i].gradeLetter){
                case "A":
                    gradeLetters[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.a));
                    break;
                case "B":
                    gradeLetters[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.b));
                    break;
                case "C":
                    gradeLetters[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.c));
                    break;
                case "D":
                    gradeLetters[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.d));
                    break;
                case "F":
                    gradeLetters[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.f));
                    break;
                default:
                    gradeLetters[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.na));
                    break;
            }
        }
    }
    
}
