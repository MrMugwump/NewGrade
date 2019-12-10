package com.example.whywontitwork.DataObjects;

import java.util.ArrayList;
import java.util.List;

public class CourseDataObject {
    public String courseName;
    public String teacherName;
    public int roomNumber;
    public int periodNumber;
    public float grade; //may be null
    private List<AssignmentDataObject> listOfAssignements = new ArrayList<>();

    public CourseDataObject(String courseName, String teacherName, int roomNumber, int periodNumber, float grade) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
        this.periodNumber = periodNumber;
        this.grade = grade;
    }

    public void addAssignment(AssignmentDataObject assignment){
        listOfAssignements.add(assignment);
    }

    public List getListOfAssignments(){
        return listOfAssignements;
    }

}
