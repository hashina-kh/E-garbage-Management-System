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

public class Add_Wastebin1 extends AppCompatActivity {
EditText wastebin_name,wastebin_location;
Button waste_submit;
String name,location,id,type,ward;
String Status,Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_wastebin1 );
        wastebin_name=findViewById( R.id.wastebin_name );
        wastebin_location=findViewById( R.id.wastebin_location );
        waste_submit=findViewById( R.id.waste_submit );

        HashMap<String,String> data= new MemberSession(getApplicationContext()).getUserDetails();
        id=data.get( "id" );
        ward=data.get( "ward" );
     //   Toast.makeText( this, ward, Toast.LENGTH_SHORT ).show();

        waste_submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        } );


    }

    private void save() {
        name = wastebin_name.getText().toString();
        location = wastebin_location.getText().toString();
        type = "Wastebin1";




        String url = Config.baseurl + "wastebin1.php";

        if (TextUtils.isEmpty( name )) {
            wastebin_name.requestFocus();
            wastebin_location.setError( "Required Field" );
            return;
        }
        if (TextUtils.isEmpty( location )) {
            wastebin_location.requestFocus();
            wastebin_location.setError( "Required Field" );
            return;
        }

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
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
                        Toast.makeText( Add_Wastebin1.this, String.valueOf( error ), Toast.LENGTH_SHORT ).show();
                    }
                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put( "name", name );
                params.put( "location", location );
                params.put( "type", type );
                params.put( "id", id );
                params.put( "ward", ward );
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }
    private void checkLogin() {
        if (Status.equals( "0" )) {
            Toast.makeText( this, "Already Added", Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText( this, "Wastebin Added", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( Add_Wastebin1.this, Wastebin.class );
            startActivity( i );
            finish();

        }
    }
}