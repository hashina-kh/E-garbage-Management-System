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
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    EditText editText1, editText2;

    Button btnotpforgot, verifyotp;


    String otpcheck, verotp, num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        btnotpforgot = findViewById(R.id.btnOTPforgot);
        editText1 = findViewById(R.id.etPhonenumberforgot);
        editText2 = findViewById(R.id.otpforgot);
        verifyotp = findViewById(R.id.verifyotp);


        btnotpforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = editText1.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    editText1.setError("Please Enter Your number");
                    editText1.requestFocus();
                } else if (!isPhoneValid(num)) {
                    editText1.setError("Invalid Phone Number!");
                    editText1.requestFocus();
                } else {
                    checkPermission();
                }
            }
        });

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotfn();
            }
        });
    }


    public void sendOTP() {
        Random r = new Random();
        int otp = r.nextInt((9999 - 1000) + 1) + 1000;  //Range: [0,8999]+1000 = [1000,9999]

        String msg = "Your OTP for changing the password in Trashtracker is " + otp;

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(num, null, msg, null, null);

        otpcheck = Integer.toString(otp);


    }


    // Function to check and request permission.
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(ForgotPassword.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(ForgotPassword.this, new String[]{Manifest.permission.SEND_SMS}, 1);
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
                Toast.makeText(ForgotPassword.this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void forgotfn() {

        verotp = editText2.getText().toString();

        if (verotp.equals(otpcheck)) {
            Intent intent = new Intent(ForgotPassword.this, Userconfirmpassword.class);
            intent.putExtra("mobile", num);
            startActivity(intent);
        } else {
            Toast.makeText(ForgotPassword.this, "Otp mismatch", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isPhoneValid(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

}