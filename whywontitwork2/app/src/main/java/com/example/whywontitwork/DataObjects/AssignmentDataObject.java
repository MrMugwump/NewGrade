package com.example.whywontitwork.DataObjects;

public class AssignmentDataObject {
    public String assignmentName;
    public float grade; //may be null
    public int pointsAssigned;
    public int pointsAwarded;

    public AssignmentDataObject(String assignmentName, float grade, int pointsAssigned, int pointsAwarded) {
        this.assignmentName = assignmentName;
        this.grade = grade;
        this.pointsAssigned = pointsAssigned;
        this.pointsAwarded = pointsAwarded;
    }
}
