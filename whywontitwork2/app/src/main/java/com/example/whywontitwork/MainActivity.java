package com.example.whywontitwork;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whywontitwork.DataObjects.DataHolder;
import com.example.whywontitwork.SyenrgyParsing.Login;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String email;
    String password;
    String failsafe = "don't continue";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        if (sharedPreferences.getString("Failsafe", null) != null)
            failsafe = sharedPreferences.getString("Failsafe", null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(sharedPreferences.getString("LogInAutomatically", null), String.valueOf(true))){
                if (!failsafe.equals("don't continue") && DataHolder.isLoginAutomatically()) {
                    email = sharedPreferences.getString("Email", null);
                    password = sharedPreferences.getString("Password", null);
                    Content content = new Content(this);
                    content.execute();
                    return;
                }
            }
        }

        Switch toggle = findViewById(R.id.switch3);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit()
                        .putString("LogInAutomatically", String.valueOf(isChecked))
                        .apply();
            }
        });
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
        if (!loggedIn) {
            intent = new Intent(this, MainActivity.class);
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            Toast.makeText(this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putString("Failsafe", "don't continue").apply();
        }
        else {
            intent = new Intent(this, CourseView.class);
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            sharedPreferences.edit().putString("Failsafe", "continue").apply();
        }

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
            } catch (InterruptedException e) {
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
