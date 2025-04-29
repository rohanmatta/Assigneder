package com.example.assigneder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

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
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false);
        if (isDarkMode) {
            setTheme(R.style.Theme_Assigneder_Dark);
        } else {
            setTheme(R.style.Theme_Assigneder_Light);
        }

        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String assignmentsJson = sharedPreferences.getString(ASSIGNMENTS_KEY, "[]");
        JSONArray assignmentsArr = null;
        List<Assignment> assignments;
        try {
            assignmentsArr = new JSONArray(assignmentsJson);
            assignments = new ArrayList<>();
            for (int i = 0; i < assignmentsArr.length(); i++) {
                JSONObject obj = assignmentsArr.getJSONObject(i);

                String title = obj.getString(TITLE_KEY);
                String course = obj.getString(COURSE_KEY);
                LocalDate dueDate = LocalDate.parse(obj.getString(DUE_DATE_KEY));
                String desc = obj.getString(DESCRIPTION_KEY);
                boolean urgent = obj.getBoolean(URGENT_KEY);
                boolean completed = obj.getBoolean(COMPLETED_KEY);

                Assignment a = new Assignment(title, course, dueDate, desc);

                a.setUrgent(urgent);
                a.setCompleted(completed);
                assignments.add(a);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(assignments, new Comparator<Assignment>() {
            public int compare(Assignment a1, Assignment a2) {
                if (a1.isUrgent() && !a2.isUrgent()) return -1;
                if (!a1.isUrgent() && a2.isUrgent()) return 1;

                int dateComp = a1.getDueDate().compareTo(a2.getDueDate());
                return dateComp;
            }
        });

        List<Object> items = new ArrayList<>();
        Assignment.DateHeader d1 = new Assignment.DateHeader(LocalDate.now());
        d1.setCustomHeader("Urgent Assignments");
        items.add(d1);
        for(Assignment a : assignments){
            if(a.isUrgent()){
                items.add(a);
            }
        }
        LocalDate currentDate = null;
        for (Assignment a : assignments) {
            if (currentDate == null || !a.getDueDate().equals(currentDate)) {
                if(!a.isUrgent()) {
                    currentDate = a.getDueDate();
                    items.add(new Assignment.DateHeader(currentDate));
                }
            }
            if(!a.isUrgent()) {
                items.add(a);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        AssignmentAdapter adapter = new AssignmentAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menu_add_assignment) {
            Intent aboutIntent = new Intent(this, AddAssignment.class);
            startActivity(aboutIntent);
            return true;
        } else if(menuId == R.id.menu_settings){
            Intent aboutIntent = new Intent(this, SettingsActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}