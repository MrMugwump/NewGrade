package com.example.whywontitwork;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whywontitwork.DataObjects.DataHolder;
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

        editor.putString("Password", password); //Saves Password and id, so we could log in automatically later.
        editor.apply();

        Content content = new Content(this);
        content.execute();

    }

    private void startNewActivity(boolean loggedIn){
        Intent intent;
        if (!loggedIn)
            intent = new Intent(this, MainActivity.class);
        else
            intent = new Intent (this, CourseView.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak") //Gets rid of an unavoidable warning thingy that wants us to make this class static. But we can't do that because we have to use a callback
    private class Content extends AsyncTask<Void, Void, Void> { //This allows the app to actually surf the internet in th background

        MainActivity mainActivity;
        boolean loggedIn = false;

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
                Login.login(password, email);
                loggedIn = Login.checkLogin(DataHolder.getDoc());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mainActivity.startNewActivity(loggedIn);
            super.onPostExecute(aVoid);
        }

    }

}
