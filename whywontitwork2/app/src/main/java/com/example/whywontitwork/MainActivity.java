package com.example.whywontitwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whywontitwork.DataObjects.DataHolder;
import com.example.whywontitwork.SyenrgyParsing.Login;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    String failsafe = "don't continue";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.logo);
        imageView.setImageResource(R.drawable.actuallieeitsorange); //For some reason won't load image without me doing this

        final SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        if (sharedPreferences.getString("Failsafe", null) != null)
            failsafe = sharedPreferences.getString("Failsafe", null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(sharedPreferences.getString("LogInAutomatically", null), String.valueOf(true))){
                if (!failsafe.equals("don't continue") && DataHolder.isLoginAutomatically()) {
                    String email = sharedPreferences.getString("Email", null);
                    String password = sharedPreferences.getString("Password", null);
                    Content content = new Content(this, email, password);
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
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

    public void attemptToLogin(View view) {
        EditText emailField = findViewById(R.id.emailForm);
        String email = emailField.getText().toString();
        EditText passwordField = findViewById(R.id.passwordForm);
        String password = passwordField.getText().toString();

        Content content = new Content(this, email, password);
        content.execute();

    }

    private void storeCredentials(){
        EditText emailField = findViewById(R.id.emailForm);
        String email = emailField.getText().toString();
        if (email.isEmpty())
            return;
        EditText passwordField = findViewById(R.id.passwordForm);
        String password = passwordField.getText().toString();


        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.apply();

        editor.putString("Password", password); //Saves Password and id, so we could log in automatically later.
        editor.apply();
    }

    private void startNewActivity(boolean loggedIn){
        Intent intent;
        if (!loggedIn) {
            intent = new Intent(this, MainActivity.class);
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            Toast.makeText(this, "Failed Login", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putString("Failsafe", "don't continue").apply();
        }
        else {
            intent = new Intent(this, CourseView.class);
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            sharedPreferences.edit().putString("Failsafe", "continue").apply();
            storeCredentials();
        }

        startActivity(intent);
    }

    //unavoidable warning thingy that wants us to make this class static. But we can't do that because we have to use a callback
    private class Content extends AsyncTask<Void, Void, Void> { //This allows the app to actually surf the internet in th background

        MainActivity mainActivity;
        boolean loggedIn = false;
        ProgressDialog progressDialog;
        String id;
        String password;

        Content(MainActivity mainActivity, String id, String password){
            this.mainActivity = mainActivity;
            this.id = id;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Setting Up");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                progressDialog.setMessage("Logging In");
                Login.login(password, id, MainActivity.this);
                loggedIn = Login.checkLogin(DataHolder.getDoc());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mainActivity.startNewActivity(loggedIn);
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }

    }

}
