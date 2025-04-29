package com.example.assigneder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "ASSIGNEDER";
    private static final String DARK_MODE_KEY = "DARK_MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        Switch darkModeSwitch = findViewById(R.id.switch_dark_mode);
        boolean isDarkMode = sharedPreferences.getBoolean(DARK_MODE_KEY, false);
        darkModeSwitch.setChecked(isDarkMode);

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(DARK_MODE_KEY, isChecked);
            editor.apply();

            recreate();
        });

        TextView changeName = findViewById(R.id.row_change_name);
        changeName.setOnClickListener(this);

        TextView classSettings = findViewById(R.id.row_class_settings);
        classSettings.setOnClickListener(this);

        TextView about = findViewById(R.id.row_about);
        about.setOnClickListener(this);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.back_button){
            finish();
        }else if(id == R.id.row_change_name){
            Intent aboutIntent = new Intent(this, ChangeName.class);
            startActivity(aboutIntent);
        }else if(id == R.id.row_class_settings){
            Intent aboutIntent = new Intent(this, ClassSettings.class);
            startActivity(aboutIntent);
        } else if(id == R.id.row_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.about_text);
            builder.setNegativeButton(R.string.alert_box_ok_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
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
        } else if(menuId == R.id.menu_back){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
