package com.example.assigneder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Show the “Up” button on the toolbar
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        // Dark Mode switch
        Switch darkSwitch = findViewById(R.id.switch_dark_mode);
        darkSwitch.setChecked(
                AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        );
        darkSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AppCompatDelegate.setDefaultNightMode(
                    isChecked
                            ? AppCompatDelegate.MODE_NIGHT_YES
                            : AppCompatDelegate.MODE_NIGHT_NO
            );
        });

        // “Class Settings” row tap → launch your AddAssignment activity
        findViewById(R.id.row_class_settings).setOnClickListener(v ->
                startActivity(new Intent(this, AddAssignment.class))
        );

        // “Change name” row tap → TODO: show rename dialog
        findViewById(R.id.row_change_name).setOnClickListener(v -> {
            // TODO: implement a dialog to let the user enter a new name
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the Up arrow
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
