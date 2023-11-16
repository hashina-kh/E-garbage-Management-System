package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class truckcontributewaste extends AppCompatActivity {

    RecyclerView contributereccycler;
    String url = Config.baseurl+"contributelist.php";
    private ArrayList<Contributedatamodel> contributedatamodelArrayList;


    private contributeadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckcontributewaste);

        contributereccycler = findViewById(R.id.contributerecycler);


        reportfn();


    }

    private void reportfn() {


        StringRequest contributewaste = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                contributedatamodelArrayList = new ArrayList<>();

                try {

                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);


                        contributedatamodelArrayList.add(new Contributedatamodel(
                                object.getString("id"),
                                object.getString("wastetype"),
                                object.getString("quantity"),
                                object.getString("mobile"),
                                object.getString("latitude"),
                                object.getString("longitude"),
                                object.getString("description")
                        ));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                privatefn();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue vu = Volley.newRequestQueue(this);
        vu.add(contributewaste);

    }

    private void privatefn() {
        adapter = new contributeadapter(truckcontributewaste.this,contributedatamodelArrayList);
        contributereccycler.setHasFixedSize(true);
        contributereccycler.setAdapter(adapter);
        contributereccycler.setLayoutManager(new LinearLayoutManager(truckcontributewaste.this, LinearLayoutManager.VERTICAL, false));


    }
}