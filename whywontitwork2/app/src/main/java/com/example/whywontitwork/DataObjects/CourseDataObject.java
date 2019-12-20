package com.example.whywontitwork.DataObjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CourseDataObject {
    public String courseName;
    public String teacherName;
    public int roomNumber;
    public String room;
    public int periodNumber;
    public float grade; //may be null
    public String gradeScore;
    public String gradeLetter;
    private List<AssignmentDataObject> listOfAssignements = new ArrayList<>();


    public void addAssignment(AssignmentDataObject assignment){
        listOfAssignements.add(assignment);
    }

    public List getListOfAssignments(){
        return listOfAssignements;
    }

}
