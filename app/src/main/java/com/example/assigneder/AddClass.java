package com.example.assigneder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddClass extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "ASSIGNEDER";
    public static final String COURSES_KEY = "COURSES";

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

        setContentView(R.layout.activity_add_class);

        Button addClassButton  = findViewById(R.id.button_add_class);
        addClassButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText titleText = findViewById(R.id.new_course_field);
        String course = titleText.getText().toString();

        String json = sharedPreferences.getString(COURSES_KEY,"[]");
        try {
            JSONArray coursesArray = new JSONArray(json);
            coursesArray.put(course);
            sharedPreferences.edit().putString(COURSES_KEY, coursesArray.toString()).apply();
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