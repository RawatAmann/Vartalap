package com.example.vartalap;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginOtpActivity extends AppCompatActivity {

    String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);

        phonenumber=getIntent().getExtras().getString("phone");
        Toast.makeText(getApplicationContext(),phonenumber,Toast.LENGTH_LONG).show();


    }
}