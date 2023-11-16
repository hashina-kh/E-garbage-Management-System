
package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Wastebin1 extends AppCompatActivity {
String ward;

EditText binname,binlocation,bindescription;
Button binsubmit;
String Status,Message;
    String type="Wastebin1";

String name,location,description,wid,ty,id,wa,userid,drivername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_wastebin1 );
        ward=getIntent().getStringExtra( "ward" );
        binname=findViewById( R.id.binname );
        binlocation=findViewById( R.id.binlocation );
        bindescription=findViewById( R.id.bindescription );
        binsubmit=findViewById( R.id.binsubmit );

        name=getIntent().getStringExtra( "name" );
        location=getIntent().getStringExtra( "location" );
        wid=getIntent().getStringExtra( "wid" );
        ty=getIntent().getStringExtra( "type" );
        id=getIntent().getStringExtra( "id" );
        wa=getIntent().getStringExtra( "ward" );

        binname.setText( name );
        binlocation.setText( location );

        binsubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        } );
        HashMap<String,String> data= new UserSession(Wastebin1.this).getUserDetails();
        userid=data.get( "id" );

    }

    private void save() {

        description = bindescription.getText().toString();




        String url = Config.baseurl + "Waste_report.php";

        if (TextUtils.isEmpty( description )) {
            bindescription.requestFocus();
            bindescription.setError( "Required Field" );
            return;
        }

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                          //  Toast.makeText( Wastebin1.this, response, Toast.LENGTH_SHORT ).show();
                            JSONObject c = new JSONObject( response );
                            Status = c.getString( "status" );
                            Message = c.getString( "message" );
                            checkLogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( Wastebin1.this, String.valueOf( error ), Toast.LENGTH_SHORT ).show();
                    }
                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put( "name", name );
                params.put( "location", location );
                params.put( "description", description );
                params.put( "wid", wid );
                params.put( "type", ty );
                params.put( "id", id );
                params.put( "ward", wa );
                params.put( "userid", userid );
//                params.put("drivername",drivername);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }
    private void checkLogin() {
        if (Status.equals( "0" )) {
            Toast.makeText( this, "Failed", Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText( this, "Reported", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( Wastebin1.this, View_Ward.class );
            startActivity( i );
            finish();

        }


    }


}