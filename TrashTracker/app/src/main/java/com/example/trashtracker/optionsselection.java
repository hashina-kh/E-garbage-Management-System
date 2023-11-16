package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class optionsselection extends AppCompatActivity {
    Button feedbackbt,commplaintbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionsselection);

        feedbackbt = findViewById(R.id.feedbackbt);
        commplaintbt = findViewById(R.id.complaintbt);

        feedbackbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(optionsselection.this,feedback.class);
                startActivity(intent);
            }
        });


        commplaintbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u =new Intent(optionsselection.this,complaint.class);
                startActivity(u);
            }
        });
    }
}