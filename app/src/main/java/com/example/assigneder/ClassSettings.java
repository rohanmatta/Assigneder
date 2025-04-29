package com.example.assigneder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ClassSettings extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    ListView classListView;
    ArrayAdapter<String> classAdapter;
    List<String> courseList;

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

        setContentView(R.layout.activity_class_settings);

        Button addClassButton = findViewById(R.id.add_class);
        addClassButton.setOnClickListener(this);

        classListView = findViewById(R.id.class_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCourses();
    }

    private void loadCourses() {
        courseList = new ArrayList<>();
        String coursesJson = sharedPreferences.getString(COURSES_KEY, "[]");

        try {
            JSONArray coursesArray = new JSONArray(coursesJson);
            for (int i = 0; i < coursesArray.length(); i++) {
                courseList.add(coursesArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        classListView.setAdapter(classAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.add_class) {
            Intent intent = new Intent(this, AddClass.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menu_add_assignment) {
            Intent aboutIntent = new Intent(this, AddAssignment.class);
            startActivity(aboutIntent);
            return true;
        } else if (menuId == R.id.menu_back) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
