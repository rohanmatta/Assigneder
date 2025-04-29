package com.example.assigneder;

import static com.example.assigneder.AddClass.COURSES_KEY;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddAssignment extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "ASSIGNEDER";
    private static final String ASSIGNMENTS_KEY = "ASSIGNMENTS";

    private static final String TITLE_KEY = "TITLE";
    private static final String COURSE_KEY = "COURSE";
    private static final String DUE_DATE_KEY = "DUE_DATE";
    private static final String DESCRIPTION_KEY = "DESCRIPTION";
    private static final String URGENT_KEY = "URGENT";
    private static final String COMPLETED_KEY = "COMPLETED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false);
        if (isDarkMode) {
            setTheme(R.style.Theme_Assigneder_Dark);
        } else {
            setTheme(R.style.Theme_Assigneder_Light);
        }

        setContentView(R.layout.activity_add_assignment);

        String coursesJson = sharedPreferences.getString(COURSES_KEY, "[]");
        JSONArray coursesArr = null;
        List<String> courseNames;
        try {
            coursesArr = new JSONArray(coursesJson);
            courseNames = new ArrayList<>();
            for (int i = 0; i < coursesArr.length(); i++) {
                courseNames.add(coursesArr.getString(i));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Spinner courseSpinner = findViewById(R.id.spinner_classes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, courseNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(adapter);


        Button addButton = findViewById(R.id.button_add_assignment);
        addButton.setOnClickListener((View.OnClickListener) this);

        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener((View.OnClickListener) this);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button_cancel){
            finish();
        } else if (id == R.id.button_add_assignment) {
            EditText titleText = findViewById(R.id.assignment_title);
            String title = titleText.getText().toString();

            EditText descriptionText = findViewById(R.id.description_field);
            String description = descriptionText.getText().toString();

            EditText dayText = findViewById(R.id.due_day);
            int day = Integer.parseInt(dayText.getText().toString());

            EditText monthText = findViewById(R.id.due_month);
            int month = Integer.parseInt(monthText.getText().toString());

            EditText yearText = findViewById(R.id.due_year);
            int year = Integer.parseInt(yearText.getText().toString());

            CheckBox urgentBox = findViewById(R.id.check_urgent);
            boolean isUrgent = urgentBox.isChecked();

            Spinner classSpinner = findViewById(R.id.spinner_classes);
            String course = classSpinner.getSelectedItem().toString();

            LocalDate dueDate = null;
            dueDate = LocalDate.of(year, month, day);

            Assignment newAssignment = new Assignment(title, course, dueDate, description);
            newAssignment.setUrgent(isUrgent);
            newAssignment.setCompleted(false);

            String json = sharedPreferences.getString(ASSIGNMENTS_KEY,"[]");
            try {
                JSONArray assignmentsArray = new JSONArray(json);
                JSONObject assignmentObj = new JSONObject();
                assignmentObj.put(TITLE_KEY, title);
                assignmentObj.put(COURSE_KEY, course);
                assignmentObj.put(DUE_DATE_KEY, dueDate.toString());
                assignmentObj.put(DESCRIPTION_KEY, description);
                assignmentObj.put(URGENT_KEY, isUrgent);
                assignmentObj.put(COMPLETED_KEY, false);
                assignmentsArray.put(assignmentObj);
                sharedPreferences.edit().putString(ASSIGNMENTS_KEY, assignmentsArray.toString()).apply();
            } catch (JSONException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.error_header);
                builder.setMessage(R.string.alert_box_assignment_error);
                builder.setNegativeButton(R.string.alert_box_ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_secondary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menu_settings) {
            Intent aboutIntent = new Intent(this, SettingsActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if(menuId == R.id.menu_back){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}