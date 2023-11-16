package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class View_Ward extends AppCompatActivity {
    Spinner spin;
    String[] t = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String ward;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_ward );
        spin=findViewById( R.id.spinner );
        next=findViewById( R.id.next );
        ArrayAdapter<String> adapter = new ArrayAdapter<>( View_Ward.this, R.layout.support_simple_spinner_dropdown_item, t );
        spin.setAdapter( adapter );


        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(View_Ward.this,View_Wastebin.class);
                ward=spin.getSelectedItem().toString();
                intent.putExtra( "ward",ward );
               // Toast.makeText( View_Ward.this, ward, Toast.LENGTH_SHORT ).show();
                startActivity( intent );
            }
        } );


    }
}