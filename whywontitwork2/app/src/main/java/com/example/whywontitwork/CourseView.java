package com.example.whywontitwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Element;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.whywontitwork.DataObjects.CourseDataObject;
import com.example.whywontitwork.DataObjects.DataHolder;
import com.example.whywontitwork.SyenrgyParsing.Login;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CourseView extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Content content = new Content(this);
        //content.execute();

        setContentView(R.layout.activity_view_courses);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;


        final String[] gpa = DataHolder.getGpaArray();
        final CourseDataObject[] courseDataObjects = DataHolder.getCourseDataObjects();

        changeStrings(gpa, courseDataObjects);

        final TextView textView = findViewById(R.id.GPA);

        Switch toggle = findViewById(R.id.gpaSwitch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeGPAText(textView, isChecked, gpa);
            }
        });
    }

    private void changeStrings(String[] gpa, CourseDataObject[] courseDataObjects){
        TextView textView = findViewById(R.id.GPA);
        Switch toggle = findViewById(R.id.gpaSwitch);
        changeGPAText(textView, toggle.isChecked(), gpa); //Sets gpa text

        UpdateUI.changeTextviews(this, courseDataObjects);
        
        /*TextView courseGrade = findViewById(R.id.periodOneGrade);
        TextView courseName = findViewById(R.id.periodOneName);
        TextView teacherName = findViewById(R.id.periodOneTeacher);
        TextView roomNumber = findViewById(R.id.periodOneRoom);
        
        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[0]);
        
        courseGrade = findViewById(R.id.periodTwoGrade);
        courseName = findViewById(R.id.periodTwoName);
        teacherName = findViewById(R.id.periodTwoTeacher);
        roomNumber = findViewById(R.id.periodTwoRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[1]);

        courseGrade = findViewById(R.id.periodThreeGrade);
        courseName = findViewById(R.id.periodThreeName);
        teacherName = findViewById(R.id.periodThreeTeacher);
        roomNumber = findViewById(R.id.periodThreeRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[2]);

        courseGrade = findViewById(R.id.periodFourGrade);
        courseName = findViewById(R.id.periodFourName);
        teacherName = findViewById(R.id.periodFourTeacher);
        roomNumber = findViewById(R.id.periodFourRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[3]);

        courseGrade = findViewById(R.id.periodFiveGrade);
        courseName = findViewById(R.id.periodFiveName);
        teacherName = findViewById(R.id.periodFiveTeacher);
        roomNumber = findViewById(R.id.periodFiveRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[4]);

        courseGrade = findViewById(R.id.periodSixGrade);
        courseName = findViewById(R.id.periodSixName);
        teacherName = findViewById(R.id.periodSixTeacher);
        roomNumber = findViewById(R.id.periodSixRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[5]);

        courseGrade = findViewById(R.id.periodSevenGrade);
        courseName = findViewById(R.id.periodSevenName);
        teacherName = findViewById(R.id.periodSevenTeacher);
        roomNumber = findViewById(R.id.periodSevenRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[6]);

        courseGrade = findViewById(R.id.periodEightGrade);
        courseName = findViewById(R.id.periodEightName);
        teacherName = findViewById(R.id.periodEightTeacher);
        roomNumber = findViewById(R.id.periodEightRoom);

        changeCourseText(courseGrade, courseName, teacherName, roomNumber, courseDataObjects[7]);*/
        
    }
    
    private void changeGPAText(TextView textView, boolean isChecked, String[] gpa){
        if (isChecked)
            textView.setText("Weighted " + gpa[1]); //Change this later so it doesn't yell at me.
        else
            textView.setText("Unweighted " + gpa[0]);
    }
    
    private void changeCourseText(TextView grade, TextView courseName, TextView teacherName, TextView roomNumber, CourseDataObject courseDataObject){
        grade.setText(courseDataObject.gradeScore);
        courseName.setText(courseDataObject.courseName);
        teacherName.setText(courseDataObject.teacherName);
        roomNumber.setText(courseDataObject.room);
    }
}
