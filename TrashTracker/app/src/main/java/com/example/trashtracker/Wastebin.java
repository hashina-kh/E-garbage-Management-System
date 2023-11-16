package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Wastebin extends AppCompatActivity {
CardView wastebin1,wastebin2,wastebin3,wastebin4,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_wastebin );
        wastebin1=findViewById( R.id.wastebin1 );
        wastebin2=findViewById( R.id.wastebin2);
        wastebin3=findViewById( R.id.wastebin3);
        wastebin4=findViewById( R.id.wastebin4 );
        logout=findViewById( R.id.logout );

        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Wastebin.this,Selection.class) );
                finish();
            }
        } );

        wastebin1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Wastebin.this,Add_Wastebin1.class) );
            }
        } );
        wastebin2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Wastebin.this,Add_Wastebin2.class) );
            }
        } );
        wastebin3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Wastebin.this,Add_Wastebin3.class) );
            }
        } );
        wastebin4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Wastebin.this,Add_Wastebin4.class) );
            }
        } );
    }
}