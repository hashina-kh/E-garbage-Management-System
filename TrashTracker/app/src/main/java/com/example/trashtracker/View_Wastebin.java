package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class View_Wastebin extends AppCompatActivity {
CardView wastebin1,wastebin2,wastebin3,wastebin4,logout;
String ward;
    String Status,Message;
    String type="Wastebin1";
    String type1="Wastebin2";
    String type3="Wastebin3";
    String type4="Wastebin4";

    String name,location,description,wid,ty,id,wa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_wastebin );
        ward=getIntent().getStringExtra( "ward" );

        wastebin1=findViewById( R.id.wastebin1 );
        wastebin2=findViewById( R.id.wastebin2 );
        wastebin3=findViewById( R.id.wastebin3);
        wastebin4=findViewById( R.id.wastebin4);

        wastebin1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              fetch();
            }
        } );
        wastebin2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch2();
            }
        } );
        wastebin3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch3();
            }
        } );
        wastebin4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch4();
            }
        } );
    }

    private void fetch4() {
        String url = Config.baseurl + "view_waste.php";

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject c = new JSONObject( response );
                            Status = c.getString( "status" );
                            Message = c.getString( "message" );


                            name=c.getString( "name" );
                            location=c.getString( "location" );
                            ty=c.getString( "type" );
                            id=c.getString( "id" );
                            wa=c.getString( "ward" );
                            wid=c.getString( "wid" );

//                            Toast.makeText( View_Wastebin.this, name, Toast.LENGTH_SHORT ).show();



                            checkLogin4();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( View_Wastebin.this, String.valueOf( error ), Toast.LENGTH_SHORT ).show();
                    }
                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put( "type", type4);
                params.put( "ward", ward );

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }
    private void checkLogin4() {
        if (Status.equals( "0" )) {
            Toast.makeText( this, "Failed", Toast.LENGTH_SHORT ).show();
        } else {
            // Toast.makeText( this, "Wastebin Added", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( View_Wastebin.this, Wastebin1.class );
            i.putExtra( "name",name );
            i.putExtra( "location",location );
            i.putExtra( "type",ty );
            i.putExtra( "id",id );
            i.putExtra( "ward",ward );
            i.putExtra( "wid",wid );
            startActivity( i );
            finish();

        }
    }

    private void fetch3() {
        String url = Config.baseurl + "view_waste.php";

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject c = new JSONObject( response );
                            Status = c.getString( "status" );
                            Message = c.getString( "message" );


                            name=c.getString( "name" );
                            location=c.getString( "location" );
                            ty=c.getString( "type" );
                            id=c.getString( "id" );
                            wa=c.getString( "ward" );
                            wid=c.getString( "wid" );

//                            Toast.makeText( View_Wastebin.this, name, Toast.LENGTH_SHORT ).show();



                            checkLogin3();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( View_Wastebin.this, String.valueOf( error ), Toast.LENGTH_SHORT ).show();
                    }
                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put( "type", type3);
                params.put( "ward", ward );

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }
    private void checkLogin3() {
        if (Status.equals( "0" )) {
            //Toast.makeText( this, "Failed", Toast.LENGTH_SHORT ).show();
        } else {
            // Toast.makeText( this, "Wastebin Added", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( View_Wastebin.this, Wastebin1.class );
            i.putExtra( "name",name );
            i.putExtra( "location",location );
            i.putExtra( "type",ty );
            i.putExtra( "id",id );
            i.putExtra( "ward",ward );
            i.putExtra( "wid",wid );
            startActivity( i );
            finish();

        }
    }

    private void fetch2() {
        String url = Config.baseurl + "view_waste.php";

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject c = new JSONObject( response );
                            Status = c.getString( "status" );
                            Message = c.getString( "message" );


                            name=c.getString( "name" );
                            location=c.getString( "location" );
                            ty=c.getString( "type" );
                            id=c.getString( "id" );
                            wa=c.getString( "ward" );
                            wid=c.getString( "wid" );

//                            Toast.makeText( View_Wastebin.this, name, Toast.LENGTH_SHORT ).show();



                            checkLogin1();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( View_Wastebin.this, String.valueOf( error ), Toast.LENGTH_SHORT ).show();
                    }
                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put( "type", type1);
                params.put( "ward", ward );

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }
    private void checkLogin1() {
        if (Status.equals( "0" )) {
            //Toast.makeText( this, "Failed", Toast.LENGTH_SHORT ).show();
        } else {
            // Toast.makeText( this, "Wastebin Added", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( View_Wastebin.this, Wastebin1.class );
            i.putExtra( "name",name );
            i.putExtra( "location",location );
            i.putExtra( "type",ty );
            i.putExtra( "id",id );
            i.putExtra( "ward",ward );
            i.putExtra( "wid",wid );
            startActivity( i );
            finish();

        }

    }

    private void fetch() {
        String url = Config.baseurl + "view_waste.php";

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject c = new JSONObject( response );
                            Status = c.getString( "status" );
                            Message = c.getString( "message" );


                            name=c.getString( "name" );
                            location=c.getString( "location" );
                            ty=c.getString( "type" );
                            id=c.getString( "id" );
                            wa=c.getString( "ward" );
                            wid=c.getString( "wid" );

//                            Toast.makeText( View_Wastebin.this, name, Toast.LENGTH_SHORT ).show();



                            checkLogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( View_Wastebin.this, String.valueOf( error ), Toast.LENGTH_SHORT ).show();
                    }
                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put( "type", type );
                params.put( "ward", ward );

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }
    private void checkLogin() {
        if (Status.equals( "0" )) {
            //Toast.makeText( this, "Failed", Toast.LENGTH_SHORT ).show();
        } else {
           // Toast.makeText( this, "Wastebin Added", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( View_Wastebin.this, Wastebin1.class );
            i.putExtra( "name",name );
            i.putExtra( "location",location );
            i.putExtra( "type",ty );
            i.putExtra( "id",id );
            i.putExtra( "ward",ward );
            i.putExtra( "wid",wid );
            startActivity( i );
            finish();

        }


    }
}