package com.example.trashtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import okio.Options;

public class feedback extends AppCompatActivity {
    EditText feedback;
    Button sendfeedbacks;
    String fd,userid;
    String url = Config.baseurl + "feedback.php";
    String status, error;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        userid = new UserSession(this).getUserDetails().get("name");
       // Toast.makeText( this, userid, Toast.LENGTH_SHORT ).show();
        name=userid;
        feedback = findViewById(R.id.feedback);
        sendfeedbacks = findViewById(R.id.sendfeedbacks);

        sendfeedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendfeedbacks();

            }
        });
    }

    private void sendfeedbacks() {
        fd = feedback.getText().toString();
        if (TextUtils.isEmpty(fd)) {
            feedback.setError("Please Enter Your Valuable Feedbacks");
            feedback.requestFocus();
            return;
        }

        StringRequest f = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(Sendfeedbacks.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject c = new JSONObject(response);
                    status = c.getString("status");
                    error = c.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status.equals("0")) {
                    Toast.makeText(feedback.this, error, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(feedback.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), optionsselection.class);
                    startActivity(i);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(feedback.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("feedback", fd);
                map.put("name",name);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(f);
    }
}
