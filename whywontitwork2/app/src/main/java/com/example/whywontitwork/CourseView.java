package com.example.whywontitwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Element;
import android.util.Log;
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
        changeStrings((String[]) (bundle.get("GPA array")));
    }

    private void changeStrings(String[] gpa){
        TextView textView = findViewById(R.id.GPA);

        textView.setText(gpa[0]);
    }



    /*private class Content extends AsyncTask<Void, Void, Void> { //This allows the app to actually surf the internet in th background

        CourseView courseView;
        String[] gpaArray;


        public Content(CourseView courseView){
            this.courseView = courseView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressDialog progressDialog = new ProgressDialog(CourseView.this);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                gpaArray = Login.login();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            courseView.changeStrings(gpaArray);
            super.onPostExecute(aVoid);
        }

    }*/
}
