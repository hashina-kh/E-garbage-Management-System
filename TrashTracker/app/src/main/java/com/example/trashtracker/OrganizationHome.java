package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class OrganizationHome extends AppCompatActivity {
    CardView useroptions;
    CardView article,logout,collected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_organization_home );


        logout=findViewById( R.id.logout );
        collected=findViewById( R.id.collected );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(OrganizationHome.this,Selection.class) );
                finish();
            }
        } );


        collected.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(OrganizationHome.this,CompletedList.class) );
            }
        } );
    }
}