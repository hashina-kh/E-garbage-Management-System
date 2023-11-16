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

public class Truckdriverregister<request> extends AppCompatActivity {

    String name;
    String phones,email;
    String password, address, num, trOtp, otps;
    int otp;
    EditText truckname, truckphone,truckemail, truckpass, truckaddress, truckotp;
    Button truckotpbutton;
    Button truckreg;
    TextView trucklogin2;


    ProgressBar progressBar;
    String url = Config.baseurl + "truckregister.php", status, error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckdriverregister);

        trucklogin2 = findViewById(R.id.trucklogin2);
        truckotpbutton = findViewById(R.id.truckotpbutton);
        truckname = findViewById(R.id.truckname);
        truckpass = findViewById(R.id.truckpass);
        truckaddress = findViewById(R.id.truckaddress);
        progressBar = findViewById(R.id.progresreg);
        truckreg = findViewById(R.id.truckreg);
        truckphone = findViewById(R.id.truckphone);
        truckemail = findViewById(R.id.truckemail);
        truckotp = findViewById(R.id.truckotp);


        trucklogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ai = new Intent(Truckdriverregister.this, Truckdriverlogin.class);
                startActivity(ai);
            }
        });

        truckotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();

            }
        });

        truckreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trOtp = truckotp.getText().toString();


                if (TextUtils.isEmpty(trOtp)) {
                     truckotp.setError("PLEASE ENTER YOUR OTP ");
                     truckotp.requestFocus();
                    return;
                }
                else if (!otps.equals(trOtp)) {
                     truckotp.setError("INCORRECT OTP ");
                     truckotp.requestFocus();
                    return;
                }
                else {
                    registerfn();
                }
            }
        });


    }


    private void registerfn() {

        name = truckname.getText().toString();
        phones = truckphone.getText().toString();
        email = truckemail.getText().toString();
        password = truckpass.getText().toString();
        address = truckaddress.getText().toString();
        address = truckaddress.getText().toString();

        if (TextUtils.isEmpty(name)) {
            truckname.setError("PLEASE ENTER YOUR NAME ");
            truckname.requestFocus();
            return;
        } else if (TextUtils.isEmpty(phones)) {
            truckphone.setError("PLEASE ENTER YOUR PHONE NUMBER ");
            truckphone.requestFocus();
            return;

        }else if (!isPhoneValid(phones)) {
            truckphone.setError("INVALID PHONE NUMBER");
            truckphone.requestFocus();
            return;

        }else if (TextUtils.isEmpty(email)) {
            truckemail.setError("PLEASE ENTER YOUR EMAIL ");
            truckemail.requestFocus();
            return;
        } else if (!isEmailValid(email)) {
            truckemail.setError("Invalid Email!");
            truckemail.requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            truckpass.setError("PLEASE ENTER YOUR PASSWORD ");
            truckpass.requestFocus();
            return;
        } else if (password.length() < 6) {
            truckpass.setError("Password is too Weak!");
            truckpass.requestFocus();
            return;
        } else if (TextUtils.isEmpty(address)) {
            truckaddress.setError("PLEASE ENTER YOUR ADDRESS ");
            truckaddress.requestFocus();
            return;
        }


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
                    Toast.makeText(Truckdriverregister.this, "cannot register", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Truckdriverregister.this, "registered", Toast.LENGTH_SHORT).show();
                    Intent g = new Intent(Truckdriverregister.this, Truckdriverlogin.class);
                    startActivity(g);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Truckdriverregister.this, "error" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("phone", phones);
                map.put("email",email);
                map.put("password", password);
                map.put("address", address);


                return map;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


    public void sendOTP() {
        num = truckphone.getText().toString();
        if(num.length()>10){
            Toast.makeText( this, "Enter Valid Number", Toast.LENGTH_SHORT ).show();
        }
        else {
            if (TextUtils.isEmpty( num )) {
                truckphone.setError( "Enter Phonenumber" );
            }
            Random r = new Random();
            otp = r.nextInt( (9999 - 1000) + 1 ) + 1000;  //Range: [0,8999]+1000 = [1000,9999]
            otps = String.valueOf( otp );


            String msg = "Your OTP for Trashtracker is" + otp;

            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage( num, null, msg, null, null );

        }
    }


    // Function to check and request permission.
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(Truckdriverregister.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Truckdriverregister.this, new String[]{Manifest.permission.SEND_SMS}, 1);
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
                Toast.makeText(Truckdriverregister.this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
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