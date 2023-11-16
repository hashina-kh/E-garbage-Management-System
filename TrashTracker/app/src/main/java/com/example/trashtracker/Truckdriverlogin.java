package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Truckdriverlogin extends AppCompatActivity {

    EditText truckmobile, truckpassword;
    Button trucklogin;
    TextView truckreg,forgot;
    String status, error, url = Config.baseurl + "trucklogin.php", phonenumber, password;
    String id, name, email, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckdriverlogin);
        truckmobile = findViewById(R.id.truckmobile);
        truckpassword = findViewById(R.id.truckpassword);
        trucklogin = findViewById(R.id.trucklogin);
        truckreg = findViewById(R.id.truckreg);
        forgot = findViewById(R.id.etforgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u = new Intent(Truckdriverlogin.this, TruForgotPassword.class);
                startActivity(u);

            }
        });


        trucklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginfn();
            }
        });


        truckreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tr = new Intent(Truckdriverlogin.this, Truckdriverregister.class);
                startActivity(tr);

            }
        });


    }

    private void loginfn() {

        phonenumber = truckmobile.getText().toString();
        password = truckpassword.getText().toString();

        if (TextUtils.isEmpty(phonenumber)) {
            truckmobile.setError("PLEASE ENTER YOUR PHONE NUMBER");
            truckmobile.requestFocus();
            return;
        } else if (!isPhoneValid(phonenumber)) {
            truckmobile.setError("INVALID PHONE NUMBER");
            truckmobile.requestFocus();
            return;
        } else if (TextUtils.isEmpty(password)) {
            truckpassword.setError("PLEASE ENTER YOUR PASSWORD");
            truckpassword.requestFocus();
            return;
        }else if (password.length()<6){
            truckpassword.setError("Required atleast 6 characters");
            truckpassword.requestFocus();
            return;
        }


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    error = jsonObject.getString("error");
                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    email = jsonObject.getString("email");
                    address = jsonObject.getString("address");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status.equals("1")) {
                    Toast.makeText(Truckdriverlogin.this, "SIGN IN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    new UserSession(Truckdriverlogin.this).createLoginSession(id, name, email, address, phonenumber);

                    Intent l = new Intent(Truckdriverlogin.this, Truckdriverhome.class);
                    startActivity(l);

                } else {

                    Toast.makeText(Truckdriverlogin.this, "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map lg = new HashMap<String, String>();
                lg.put("phonenumber", phonenumber);
                lg.put("password", password);
                return lg;

            }


        };

        RequestQueue q = Volley.newRequestQueue(this);
        q.add(request);


    }

    public static boolean isPhoneValid(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

}