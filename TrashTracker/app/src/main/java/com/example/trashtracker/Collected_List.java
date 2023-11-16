package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

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

public class Collected_List extends AppCompatActivity {
    RecyclerView reportreccycler;
    String url=Config.baseurl+"collect.php";
    private ArrayList<Reportdatamodel> reportdatamodelArrayList;
    String user,ward;
    CollectAdapter collectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_collected_list );
        reportreccycler=findViewById(R.id.collectedrecycler);

        HashMap<String,String> data= new MemberSession(Collected_List.this).getUserDetails();
        user=data.get( "id" );
        ward=data.get( "ward" );

        reportfn();
    }

    private void reportfn() {
        StringRequest reportwaste=new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                reportdatamodelArrayList=new ArrayList<>();
                Toast.makeText(Collected_List.this, response, Toast.LENGTH_SHORT).show();

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
                params.put( "ward",ward );
                return params;
            }
        };

        RequestQueue vi= Volley.newRequestQueue(this);
        vi.add(reportwaste);

    }

    private void  privatefn(){
        collectAdapter= new CollectAdapter(Collected_List.this, reportdatamodelArrayList);
        reportreccycler.setHasFixedSize(true);
        reportreccycler.setAdapter(collectAdapter);
        reportreccycler.setLayoutManager(new LinearLayoutManager(Collected_List.this, LinearLayoutManager.VERTICAL, false));





    }
}