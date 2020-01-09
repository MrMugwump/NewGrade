package com.example.whywontitwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whywontitwork.DataObjects.CourseDataObject;
import com.example.whywontitwork.DataObjects.DataHolder;

public class CourseView extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_courses);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;


        final String[] gpa = DataHolder.getGpaArray();
        final CourseDataObject[] courseDataObjects = DataHolder.getCourseDataObjects();

        changeStrings(gpa, courseDataObjects);

        final TextView textView = findViewById(R.id.GPA);

        Switch toggle = findViewById(R.id.gpaSwitch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeGPAText(textView, isChecked, gpa);
            }
        });
    }

    private void changeStrings(String[] gpa, CourseDataObject[] courseDataObjects){
        TextView textView = findViewById(R.id.GPA);
        Switch toggle = findViewById(R.id.gpaSwitch);
        changeGPAText(textView, toggle.isChecked(), gpa); //Sets gpa text

        UpdateUI.changeTextviews(this, courseDataObjects);

        
    }
    
    private void changeGPAText(TextView textView, boolean isChecked, String[] gpa){
        if (isChecked)
            textView.setText("Weighted " + gpa[1]); //Change this later so it doesn't yell at me.
        else
            textView.setText("Unweighted " + gpa[0]);
    }

    public void GoBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        DataHolder.setLoginAutomatically(false);
        //SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        //sharedPreferences.edit().putString("Failsafe", "don't continue").apply();
        //Log.d("otherThing", "GoBack: " + sharedPreferences.getString("Failsafe", null));
        startActivity(intent);
    }

    public void ShowPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.course_selection);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.periodTwoOption:
                DataHolder.setCourseChosen(1);
                break;
            case R.id.periodThreeOption:
                DataHolder.setCourseChosen(2);
                break;
            case R.id.periodFourOption:
                DataHolder.setCourseChosen(3);
                break;
            case R.id.periodFiveOption:
                DataHolder.setCourseChosen(4);
                break;
            case R.id.periodSixOption:
                DataHolder.setCourseChosen(5);
                break;
            case R.id.periodSevenOption:
                DataHolder.setCourseChosen(6);
                break;
            case R.id.periodEightOption:
                DataHolder.setCourseChosen(7);
                break;
            default:
               DataHolder.setCourseChosen(0);
               break;

        }
        Toast.makeText(this, "Period chosen for widget: " + DataHolder.getCourseChosen(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
