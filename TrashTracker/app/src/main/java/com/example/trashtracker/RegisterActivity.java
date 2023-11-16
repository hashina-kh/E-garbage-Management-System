package com.example.trashtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    String name, email;
    String phones;
    String password, address, otp2, otp1;
    EditText etName, etEmail, etPhonenumber, etPassword, etAddress, etOTP;
    Button btnLogin;
    Button btnOTP;
    TextView tvSignin;
    String num;
    int otp;

    ProgressBar progressBar;
    String url = Config.baseurl + "register.php", status, error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etPhonenumber = findViewById(R.id.etPhonenumberreg);
        etPassword = findViewById(R.id.etPasswordreg);
        etOTP = findViewById(R.id.etOTP);

        progressBar = findViewById(R.id.progresreg);
        btnOTP = findViewById(R.id.btnOTP);
        btnLogin = findViewById(R.id.btnreg);
        tvSignin = findViewById(R.id.tvSignin);

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ai = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(ai);
            }
        });

        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerfn();
            }
        });

    }

    private void registerfn() {

        name = etName.getText().toString();
        email = etEmail.getText().toString();
        phones = etPhonenumber.getText().toString();
        password = etPassword.getText().toString();
        address = etAddress.getText().toString();
        otp1 = etOTP.getText().toString();
//        Toast.makeText(this, otp1+"\n"+otp2, Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(name)) {
            etName.setError("PLEASE ENTER YOUR NAME ");
            etName.requestFocus();
            return;
        } else if (TextUtils.isEmpty(email)) {
            etEmail.setError("PLEASE ENTER YOUR EMAIL ");
            etEmail.requestFocus();
            return;
        } else if (!isEmailValid(email)) {
            etEmail.setError("Invalid Email!");
            etEmail.requestFocus();
            return;
        } else if (TextUtils.isEmpty(phones)) {
            etPhonenumber.setError("PLEASE ENTER YOUR PHONE NUMBER ");
            etPhonenumber.requestFocus();
            return;
        } else if (!isPhoneValid(phones)) {
            etPhonenumber.setError("INVALID PHONE NUMBER");
            etPhonenumber.requestFocus();
            return;

        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("PLEASE ENTER YOUR PASSWORD ");
            etPassword.requestFocus();
            return;
        } else if (password.length() < 6) {
            etPassword.setError("Password is too Weak!");
            etPassword.requestFocus();
            return;
        } else if (TextUtils.isEmpty(address)) {
            etAddress.setError("PLEASE ENTER YOUR ADDRESS ");
            etAddress.requestFocus();
            return;
        } else if (TextUtils.isEmpty(otp1)) {
            etOTP.setError("PLEASE ENTER YOUR OTP ");
            etOTP.requestFocus();
            return;
        } else if (!otp1.equals(otp2)) {
            etOTP.setError("INCORRECT OTP ");
            etOTP.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    error = jsonObject.getString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status.equals("0")) {
                    Toast.makeText(RegisterActivity.this, "cannot register", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "registered", Toast.LENGTH_SHORT).show();
                    Intent g = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(g);
                }

                progressBar.setVisibility(View.INVISIBLE);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegisterActivity.this, "error" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map map = new HashMap<String, String>();
                map.put("name", name);
                map.put("email", email);
                map.put("phone", phones);
                map.put("password", password);
                map.put("address", address);


                return map;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }


    public void sendOTP() {
        num = etPhonenumber.getText().toString();
        if(num.length()>10){
            Toast.makeText( this, "Enter Valid Number", Toast.LENGTH_SHORT ).show();
        }
        else {


            if (TextUtils.isEmpty( num )) {
                etPhonenumber.setError( "PLEASE ENTER YOUR PHONENUMBER " );
                etPhonenumber.requestFocus();
                return;
            }

            if (TextUtils.isEmpty( num )) {
                etPhonenumber.setError( "Enter Phonenumber" );
            }
            Random r = new Random();
            otp = r.nextInt( (9999 - 1000) + 1 ) + 1000;  //Range: [0,8999]+1000 = [1000,9999]
            otp2 = String.valueOf( otp );

            String msg = "Your OTP for Registration in Trashtracker is" + otp;

            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage( num, null, msg, null, null );
        }

    }


    // Function to check and request permission.
    public void checkPermission() {
            if (ContextCompat.checkSelfPermission( RegisterActivity.this, Manifest.permission.SEND_SMS ) == PackageManager.PERMISSION_DENIED) {

                // Requesting the permission
                ActivityCompat.requestPermissions( RegisterActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1 );
            } else {
                sendOTP();
            }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOTP();
            } else {
                Toast.makeText(RegisterActivity.this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static boolean isPhoneValid(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }


}