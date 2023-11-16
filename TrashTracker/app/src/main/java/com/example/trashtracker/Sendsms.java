package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sendsms extends AppCompatActivity {
EditText mobile,message;
Button send;
String id,mobilenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sendsms );
        mobile=findViewById( R.id.mobile );
        message=findViewById( R.id.message );
        send=findViewById( R.id.send );
        id=getIntent().getStringExtra( "id" );
        mobilenumber=getIntent().getStringExtra( "mobilenumber" );
        mobile.setText( mobilenumber );


        send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mob=mobile.getText().toString();
                String desc=message.getText().toString();
                sendMessage(mob,desc);
            }
        } );

    }

    private void sendMessage(String mob, String desc) {
        String msg=desc;
        Intent intent=new Intent(getApplicationContext(),Truckdriverhome.class);
        PendingIntent pi=PendingIntent.getActivity( getApplicationContext(),0,intent,0 );
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage( mob,null,msg,pi,null );

    }
}