package com.example.assigneder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
            finish();
        }else if(id == R.id.row_class_settings){
            finish();
        } else if(id == R.id.row_about) {
            finish();
        }
    }
}
