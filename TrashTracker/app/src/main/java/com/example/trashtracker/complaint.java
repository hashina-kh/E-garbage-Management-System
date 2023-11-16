package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class complaint extends AppCompatActivity {
    EditText complaint;
    Button sendcomplaint;
    String cp,userid;
    String url = Config.baseurl + "complaint.php";
    String status, error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        userid = new UserSession(this).getUserDetails().get("name");
//        Toast.makeText(this, userid, Toast.LENGTH_SHORT).show();
        complaint = findViewById(R.id.complaint);
        sendcomplaint = findViewById(R.id.sendcomplaint);

        sendcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendcomplaints();
            }
        });
    }
    private void sendcomplaints() {
        cp = complaint.getText().toString();
        if (TextUtils.isEmpty(cp)) {
            complaint.setError("Please Enter Complaints");
            complaint.requestFocus();
            return;
        }
        StringRequest c = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                Toast.makeText(Sendcomplaints.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject c = new JSONObject(response);
                    status = c.getString("status");
                    error = c.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status.equals("0")) {
                    Toast.makeText(complaint.this, error, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(complaint.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    startActivity(i);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("complaints", cp);
                map.put("user_id",userid);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(c);
    }
}