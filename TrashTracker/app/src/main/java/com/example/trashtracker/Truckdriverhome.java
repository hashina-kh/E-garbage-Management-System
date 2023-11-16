package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Truckdriverhome extends AppCompatActivity {

    TextView viewreport,viewcontribute,truckpublicbin;
    CardView truckoptions;
    CardView assigned;
    CardView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckdriverhome);


       // viewcontribute=findViewById(R.id.viewcontribute);
        truckpublicbin=findViewById(R.id.truckpublicbin);
        truckoptions=findViewById(R.id.truckoptions);
        logout=findViewById( R.id.logout );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Truckdriverhome.this,Selection.class) );
                finish();
            }
        } );

        assigned=findViewById( R.id.assigned );
        assigned.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vr = new Intent(Truckdriverhome.this,truckreportwaste.class);
              startActivity(vr);
            }
        } );

//        viewreport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent vr = new Intent(Truckdriverhome.this,truckreportwaste.class);
//                startActivity(vr);
//            }
//        });
//        viewcontribute.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Truckdriverhome.this,truckcontributewaste.class));
//
//
//            }
//        });


        truckpublicbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pb = new Intent(Truckdriverhome.this,trashbinsactivity.class);
                startActivity(pb);
            }
        });

        truckoptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to = new Intent(Truckdriverhome.this,optionsselection.class);
                startActivity(to);
            }
        });






    }
}