package com.example.trashtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import mumayank.com.airlocationlibrary.AirLocation;

public class Usercontributewaste extends AppCompatActivity {

    TextView contributelocationview;
    EditText wastetype,contributequantity,contributedescription;
    String  type,quantity,description,phonenumber,latitude,longitude,user_id;
    Button contributelocation,contributesubmit;

    private AirLocation airLocation;
    private static ProgressDialog mProgressDialog;
    String url=Config.baseurl+"contributewaste.php",status,error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercontributewaste);

        contributelocationview = findViewById(R.id.contributelocationview);
        wastetype = findViewById(R.id.wastetype);
        contributequantity=findViewById(R.id.contributequantity);
        contributedescription=findViewById(R.id.contributedescription);
        contributelocation = findViewById(R.id.contributelocation);
        contributesubmit = findViewById(R.id.contributesubmit);


        HashMap<String,String> data= new UserSession(Usercontributewaste.this).getUserDetails();
        phonenumber = data.get("phonenumber");
        user_id =data.get("id");
        Toast.makeText(this, user_id+","+phonenumber, Toast.LENGTH_SHORT).show();

        contributesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerfn();
            }
        });

        contributelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLocation();

            }
        });


    }



    private void registerfn(){

        type=wastetype.getText().toString();
        quantity=contributequantity.getText().toString();
        description=contributedescription.getText().toString();



        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Usercontributewaste.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    status=jsonObject.getString("status");
                    error=jsonObject.getString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                    if (status.equals("0")){
//                        Toast.makeText(Usercontributewaste.this, "cannot register", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(Usercontributewaste.this, "registered", Toast.LENGTH_SHORT).show();
//                    }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Usercontributewaste.this, "error"+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams(){

                Map<String ,String> map=new HashMap<>();
                map.put("waste_type",type);
                map.put("quantity",quantity);
                map.put("description",description);
                map.put("latitude",latitude);
                map.put("longitude",longitude);
                map.put("phone",phonenumber);
                map.put("user_id",user_id);
                map.put("status","null");

                return map;

            }
        };

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

    //Call this method whenever you need to fetch the co-ordinates...
    private void fetchLocation() {

        showSimpleProgressDialog(this, "Loading",
                "Fetching current location coordinates", true);

        //Location fetching process using AirLocation API...
        airLocation = new AirLocation(Usercontributewaste.this, true, true, new AirLocation.Callbacks() {
            @Override
            public void onSuccess(@NonNull Location location) {
                removeSimpleProgressDialog();
                Toast.makeText(Usercontributewaste.this, "Location fetched successfully", Toast.LENGTH_LONG).show();
                double lat, lng;
                lat = location.getLatitude();
                lng = location.getLongitude();

                latitude=Double.toString(lat);
                longitude=Double.toString(lng);



                contributelocationview.setText("Latitude: "+latitude+"\n Longitude: "+longitude);
            }

            @Override
            public void onFailed(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
                removeSimpleProgressDialog();
                Toast.makeText(Usercontributewaste.this, "Location fetching failed. Please check your internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }


    // override and call airLocation object's method by the same name
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        airLocation.onActivityResult(requestCode, resultCode, data);
    }


    // override and call airLocation object's method by the same name
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            Log.e("Log", "inside catch IllegalArgumentException");
            ie.printStackTrace();

        } catch (RuntimeException re) {
            Log.e("Log", "inside catch RuntimeException");
            re.printStackTrace();
        } catch (Exception e) {
            Log.e("Log", "Inside catch Exception");
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

















}
