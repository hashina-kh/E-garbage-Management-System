package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class OrganizationLogin extends AppCompatActivity {
    TextView tvregister;
    EditText etPhonenumber, etPassword;
    Button btnLogin;

    String phonenumber, password;
    String id, name, email, address,mobile,pass,ward;

    TextView forgot;
    ProgressBar progressBar;
    String url = Config.baseurl + "organization_login.php", status, error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_organization_login );
        tvregister=findViewById( R.id.tvRegister );
        etPhonenumber = findViewById(R.id.etPhonenumber);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        forgot = findViewById(R.id.etforgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u = new Intent(OrganizationLogin.this, OrgForgotPassword.class);
                startActivity(u);

            }
        });
        tvregister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(OrganizationLogin.this,Organizationregister.class) );
            }
        } );
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginfn();

            }
        });
    }

    private void loginfn() {
        phonenumber = etPhonenumber.getText().toString();
        password = etPassword.getText().toString();

        if (TextUtils.isEmpty(phonenumber)) {
            etPhonenumber.setError("PLEASE ENTER YOUR PHONE NUMBER");
            etPhonenumber.requestFocus();
            return;
        } else if (!isPhoneValid(phonenumber)) {
            etPhonenumber.setError("INVALID PHONE NUMBER");
            etPhonenumber.requestFocus();
            return;
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("PLEASE ENTER YOUR PASSWORD");
            etPassword.requestFocus();
            return;
        } else if (password.length()<6){
            etPassword.setError("Required atleast 6 characters");
            etPassword.requestFocus();
            return;
        }


        StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    error = jsonObject.getString("error");
                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    email = jsonObject.getString("email");
                    mobile = jsonObject.getString("mobilenumber");
                    pass = jsonObject.getString("password");
                    address = jsonObject.getString("address");
//                    ward = jsonObject.getString("ward");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status.equals("1")) {
                    Toast.makeText(OrganizationLogin.this, "SIGN IN SUCCESSFULL", Toast.LENGTH_SHORT).show();
//                    new MemberSession(OrganizationLogin.this).createLoginSession(id, name, email, mobile, pass,address,ward);

                    Intent l = new Intent(OrganizationLogin.this, OrganizationHome.class);
                    startActivity(l);

                } else {

                    Toast.makeText(OrganizationLogin.this, "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();


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