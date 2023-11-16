package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

public class TruConfirmPassword extends AppCompatActivity {
    Button btnokpass;
    String url = Config.baseurl + "DriresetPass.php", status, error;

    EditText resetPass, ConfirmPass;

    String confirmPassword, resetPassword,phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tru_confirm_password);
        resetPass = findViewById(R.id.oretPasswordreset);
        ConfirmPass = findViewById(R.id.oretPasswordconfirm);
        btnokpass = findViewById(R.id.orbtnokpass);

        Intent getValues = getIntent();
        phoneNum = getValues.getStringExtra("mobile");

        btnokpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginfn();

            }
        });
    }

    private void loginfn () {

        resetPassword = resetPass.getText().toString();
        confirmPassword = ConfirmPass.getText().toString();

        if (TextUtils.isEmpty(resetPassword)) {
            resetPass.setError("PLEASE ENTER YOUR PASSWORD ");
            resetPass.requestFocus();
            return;
        } else if (resetPassword.length() < 6) {
            resetPass.setError("Password is too Weak!");
            resetPass.requestFocus();
            return;
        } else if (!confirmPassword.equals(resetPassword)){
            ConfirmPass.setError("PASSWORD DOES NOT MATCH");
            ConfirmPass.requestFocus();
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

                if (status.equals("1")) {
                    Toast.makeText(TruConfirmPassword.this, "SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    Intent pa = new Intent(TruConfirmPassword.this, Truckdriverlogin.class);
                    startActivity(pa);

                } else {

                    Toast.makeText(TruConfirmPassword.this, "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> lg = new HashMap<String, String>();
                lg.put("password", resetPassword);
                lg.put("mobile_num", phoneNum);
                return lg;

            }


        };

        RequestQueue q = Volley.newRequestQueue(this);
        q.add(request);


    }



}