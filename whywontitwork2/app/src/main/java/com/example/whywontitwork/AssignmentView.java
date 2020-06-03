package com.example.whywontitwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AssignmentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_view);
        String periodNumber = getIntent().getStringExtra("Period Number");

        TextView period = findViewById(R.id.PeriodNumber);

        period.setText("Period: "+periodNumber);
    }
}
