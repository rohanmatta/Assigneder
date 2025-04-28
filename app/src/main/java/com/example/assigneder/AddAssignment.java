package com.example.assigneder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddAssignment extends AppCompatActivity implements View.OnClickListener{

    public static final String SHARED_PREF_NAME = "ASSIGNEDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        Button addButton = findViewById(R.id.button_add_assignment);
        addButton.setOnClickListener((View.OnClickListener) this);

        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button_cancel){
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