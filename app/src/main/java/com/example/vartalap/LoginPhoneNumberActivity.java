package com.example.vartalap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class LoginPhoneNumberActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    Button sendOtpBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);

        countryCodePicker=findViewById(R.id.login_countrycode);
        phoneInput=findViewById(R.id.login_mobile_number);
        sendOtpBtn=findViewById(R.id.send_otp_btn);
        progressBar=findViewById(R.id.login_progress_bar);

        // now we will link the phone number with the country code
        countryCodePicker.registerCarrierNumberEditText(phoneInput);

        //now we will set onclick() in send otp button
        sendOtpBtn.setOnClickListener((v)->
        {
            if(!countryCodePicker.isValidFullNumber())
            {
                phoneInput.setError("Invalid Phone Number");
                return;
            }
            Intent intent=new Intent(LoginPhoneNumberActivity.this,LoginOtpActivity.class);
            intent.putExtra("phone_number",countryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        });
    }
}