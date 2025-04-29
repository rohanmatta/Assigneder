package com.example.assigneder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class ChangeName extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;

    public static final String SHARED_PREF_NAME = "ASSIGNEDER";
    public static final String NAME_KEY = "STUDENT_NAME";

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

        setContentView(R.layout.activity_change_name);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
    }

    public String getInputFromEditText(int id) {
        EditText v = findViewById(id);
        return v.getText().toString();
    }

    public void changeName(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME_KEY, name);
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        String name = getInputFromEditText(R.id.change_name_field);
        if(name.isBlank()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error_header);
            builder.setMessage(R.string.alert_box_empty_name);
            builder.setNegativeButton(R.string.alert_box_ok_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        }else {
            changeName(name);
            Button button = findViewById(R.id.save_button);
            Snackbar.make(button,
                    "Changed name to " + name + "!",
                    Snackbar.LENGTH_LONG).show();
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