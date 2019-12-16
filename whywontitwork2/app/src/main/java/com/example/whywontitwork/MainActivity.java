package com.example.whywontitwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whywontitwork.SyenrgyParsing.Login;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        sharedPreferences.getString("Email", null);
    }

    public void checkForIfYouCanLogin(View view) {
        EditText emailField = findViewById(R.id.emailForm);
        email = emailField.getText().toString();
        EditText passwordField = findViewById(R.id.passwordForm);
        password = passwordField.getText().toString();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.apply();

        editor.putString("Password", password);
        editor.apply();

        Content content = new Content(this);
        content.execute();

    }

    private void startNewActivity(String[] gpaArray){
        Intent intent = new Intent (this, CourseView.class);
        intent.putExtra("GPA array", gpaArray);
        startActivity(intent);
    }

    private class Content extends AsyncTask<Void, Void, Void> { //This allows the app to actually surf the internet in th background

        MainActivity mainActivity;
        String[] gpaArray;

        Content(MainActivity mainActivity){
            this.mainActivity = mainActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                gpaArray = Login.login(password, email);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mainActivity.startNewActivity(gpaArray);
            super.onPostExecute(aVoid);
        }

    }

}
