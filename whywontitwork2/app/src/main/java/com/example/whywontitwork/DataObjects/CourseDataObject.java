package com.example.whywontitwork.DataObjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CourseDataObject implements Parcelable {
    public String courseName;
    public String teacherName;
    public int roomNumber;
    public String room;
    public int periodNumber;
    public float grade; //may be null
    public String gradeScore;
    public String gradeLetter;
    private List<AssignmentDataObject> listOfAssignements = new ArrayList<>();

    public CourseDataObject() {
        /*this.courseName = courseName;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
        this.periodNumber = periodNumber;
        this.grade = grade;*/
    }

    public void addAssignment(AssignmentDataObject assignment){
        listOfAssignements.add(assignment);
    }

    public List getListOfAssignments(){
        return listOfAssignements;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
