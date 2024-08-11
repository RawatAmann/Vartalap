package com.example.vartalap.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.vartalap.model.UserModel;

public class AndroidUtil
{

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void passUserModelAsIntent(Intent i, UserModel model)
    {
        i.putExtra("username", model.getUsername() );
        i.putExtra("phone", model.getPhone() );
        i.putExtra("userId", model.getUserId());
    }

    public static UserModel getUserModelFromIntent(Intent i)
    {
        UserModel userModel = new UserModel();
        userModel.setUsername(i.getStringExtra("username") );
        userModel.setPhone(i.getStringExtra("phone") );
        userModel.setUserId(i.getStringExtra("userId") );

        return userModel;

    }
}

