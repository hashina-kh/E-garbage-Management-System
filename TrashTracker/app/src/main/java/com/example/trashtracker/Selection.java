package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selection extends AppCompatActivity {

    Button truck,user,ward,organization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        truck = findViewById(R.id.truckdriver);
        user = findViewById(R.id.user);
        ward=findViewById( R.id.wardmember );
        organization=findViewById( R.id.organization );

        organization.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selection.this,OrganizationLogin.class);
                startActivity(intent);
            }
        } );

        ward.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selection.this,Wardlogin.class);
                startActivity(intent);
            }
        } );

        truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selection.this,Truckdriverlogin.class);
                startActivity(intent);
            }
        });


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u =new Intent(Selection.this,LoginActivity.class);
                startActivity(u);
            }
        });
    }


}