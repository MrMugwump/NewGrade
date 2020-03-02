package com.example.whywontitwork;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.whywontitwork.DataObjects.CourseDataObject;

class UpdateUI {
    static void updateUI(CourseView courseView, CourseDataObject[] courseDataObjects){
        View view = courseView.findViewById(R.id.periodOneAndTwo);
        CourseDataObject[] courses = new CourseDataObject[]{courseDataObjects[0], courseDataObjects[1]};

        changeCourseObjects(courseView, view, courses, new int[]{1,2});

        view = courseView.findViewById(R.id.periodThreeAndFour);
        courses = new CourseDataObject[]{courseDataObjects[2], courseDataObjects[3]};

        changeCourseObjects(courseView, view, courses, new int[]{3,4});

        view = courseView.findViewById(R.id.periodFiveAndSix);
        courses = new CourseDataObject[]{courseDataObjects[4], courseDataObjects[5]};

        changeCourseObjects(courseView, view, courses, new int[]{5,6});

        view = courseView.findViewById(R.id.periodSevenAndEight);
        courses = new CourseDataObject[]{courseDataObjects[6], courseDataObjects[7]};

        changeCourseObjects(courseView, view, courses, new int[]{7,8});

    }

    private static void changeCourseObjects(CourseView courseView, View view, CourseDataObject[] courses, int[] periodNumbers){ //
        View[] courseBlocks = new View[] {view.findViewById(R.id.first_course_block), view.findViewById(R.id.second_course_block)};
        changeGradeImages(courseView, courseBlocks, courses);
        
        final int nameLengthLimit = 16;

        for (int i = 0; i < 2; i++) {
            String courseTitle = courses[i].courseName;

            if (courses[i].courseName.length() > nameLengthLimit){
                courseTitle = courses[i].courseName.substring(0,nameLengthLimit) + "...";//Shrinks course name if too long
            }
            TextView courseName = courseBlocks[i].findViewById(R.id.courseName);
            courseName.setText(courseTitle); //I have to instantiate the textview or it doesn't let me change the text

            TextView grade = courseBlocks[i].findViewById(R.id.grade);
            grade.setText(courses[i].gradeScore);

            TextView teacherName = courseBlocks[i].findViewById(R.id.teacherName);
            teacherName.setText(courses[i].teacherName);

            TextView roomNumber = courseBlocks[i].findViewById(R.id.roomNumber);
            roomNumber.setText(courses[i].room);

            TextView periodNumber = courseBlocks[i].findViewById(R.id.period);
            periodNumber.setText("Period "+periodNumbers[i]);
        }
    }

    private static void changeGradeImages(CourseView courseView, View[] courseBlocks, CourseDataObject[] courses){
        ImageView[] images  = new  ImageView[]{courseBlocks[0].findViewById(R.id.gradeLetter), courseBlocks[1].findViewById(R.id.gradeLetter)};

        for (int i = 0; i < 2; i++) {
            switch (courses[i].gradeLetter){
                case "A":
                    images[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.a));
                    break;
                case "B":
                    images[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.b));
                    break;
                case "C":
                    images[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.c));
                    break;
                case "D":
                    images[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.d));
                    break;
                case "F":
                    images[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.f));
                    break;
                default:
                    images[i].setImageDrawable(ContextCompat.getDrawable(courseView, R.drawable.na));
                    break;
            }
        }
    }
    
}
