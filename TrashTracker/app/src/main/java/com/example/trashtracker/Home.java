package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {



    CardView reportwaste,contribute,useroptions,Logout;
    TextView trashbin,article;
    CardView waste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // reportwaste=findViewById(R.id.reportwaste);
     //   contribute=findViewById(R.id.contribute);
        trashbin=findViewById(R.id.trashbin);
        useroptions=findViewById(R.id.useroptions);
        article=findViewById(R.id.article);
        Logout=findViewById(R.id.logout);
        waste=findViewById( R.id.waste );

        trashbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tb=new Intent(Home.this,trashbinsactivity.class);
                startActivity(tb);
            }
        });

        waste.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this,View_Ward.class) );
            }
        } );


//        contribute.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cc =new Intent(Home.this,Usercontributewaste.class);
//                startActivity(cc);
//            }
//        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserSession(Home.this).logoutUser();
                startActivity(new Intent(Home.this, Selection.class));
                finish();
            }
        });





//        reportwaste.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent rr =new Intent(Home.this,Userreportwaste.class);
//                startActivity(rr);
//            }
//        });

        useroptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uo = new Intent(Home.this,optionsselection.class);
                startActivity(uo);
            }
        });

        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Config.baseurl + "article.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }

}