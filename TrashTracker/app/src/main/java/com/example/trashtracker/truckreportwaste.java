package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class truckreportwaste extends AppCompatActivity {


    RecyclerView reportreccycler;
    String url=Config.baseurl+"reportlist.php";
    private ArrayList<Reportdatamodel> reportdatamodelArrayList;
    String user;


    reportadapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckreportwaste);

        reportreccycler=findViewById(R.id.reportrecycler);

        HashMap<String,String> data= new UserSession(truckreportwaste.this).getUserDetails();
       user=data.get( "id" );
        reportfn();


    }

    private void reportfn(){


        StringRequest reportwaste=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                reportdatamodelArrayList=new ArrayList<>();

                try {

                JSONArray array=new JSONArray(response);

                for (int i = 0;i<array.length();i++){

                    JSONObject object=array.getJSONObject(i);


                        reportdatamodelArrayList.add(new Reportdatamodel(
                                object.getString("id"),
                                object.getString("name"),
                                object.getString("location"),
                                object.getString("description"),
                                object.getString("wid"),
                                object.getString("type"),
                                object.getString("mid"),
                                object.getString("ward"),
                                object.getString("userid"),
                                object.getString("status"),
                                object.getString("driverid"),
                                object.getString("drivername"),
                                object.getString( "date" ),
                                object.getString( "time" )

                        ));
                    }



                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                privatefn();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put( "id", user);
                return params;
            }
            };

        RequestQueue vi= Volley.newRequestQueue(this);
        vi.add(reportwaste);

    }

    private void  privatefn(){
       adapter= new reportadapter(truckreportwaste.this, reportdatamodelArrayList);
        reportreccycler.setHasFixedSize(true);
        reportreccycler.setAdapter(adapter);
        reportreccycler.setLayoutManager(new LinearLayoutManager(truckreportwaste.this, LinearLayoutManager.VERTICAL, false));



    }








}