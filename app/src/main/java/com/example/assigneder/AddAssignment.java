package com.example.assigneder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
}