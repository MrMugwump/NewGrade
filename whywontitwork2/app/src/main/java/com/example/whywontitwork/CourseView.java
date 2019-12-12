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


        final String[] gpa = (String[]) (bundle.get("GPA array"));
        changeStrings(gpa);

        final TextView textView = findViewById(R.id.GPA);

        Switch toggle = findViewById(R.id.gpaSwitch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setText("Weighted " + gpa[1]);
                } else {
                    textView.setText("Unweighted " + gpa[0]);

                }
            }
        });
    }

    private void changeStrings(String[] gpa){
        TextView textView = findViewById(R.id.GPA);

        Switch toggle = findViewById(R.id.gpaSwitch);
        if (toggle.isChecked())
            textView.setText("Weighted" + gpa[1]);
        else
            textView.setText("Unweighted" + gpa[0]);


    }
}
