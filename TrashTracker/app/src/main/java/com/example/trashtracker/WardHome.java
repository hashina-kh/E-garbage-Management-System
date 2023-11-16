package com.example.trashtracker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class WardHome extends AppCompatActivity {
CardView useroptions;
CardView article,logout,wastebin;
CardView reportwaste,collectedlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ward_home );
        useroptions=findViewById( R.id.useroptions );
        logout=findViewById( R.id.logout );
        wastebin=findViewById( R.id.wastebin );
        reportwaste=findViewById( R.id.reportwaste );
        collectedlist=findViewById( R.id.collectedlist );
        collectedlist.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(WardHome.this,Collected_List.class) );
            }
        } );
        reportwaste.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(WardHome.this,View_reports.class) );
            }
        } );
        wastebin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(WardHome.this,Wastebin.class) );
            }
        } );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(WardHome.this,Selection.class) );
                finish();
            }
        } );
        useroptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uo = new Intent(WardHome.this,optionsselection.class);
                startActivity(uo);
            }
        });
        article=findViewById( R.id.article);
        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Config.baseurl + "article.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData( Uri.parse(url));
                startActivity(i);
            }
        });


    }
}