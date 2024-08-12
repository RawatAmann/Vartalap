package com.example.vartalap;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vartalap.model.UserModel;
import com.example.vartalap.utils.AndroidUtil;

public class ChatActivity extends AppCompatActivity {

    UserModel otherUser;
    EditText messageInput;
    ImageButton sendMessageBtn, backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //get UserModel
        otherUser = AndroidUtil.getUserModelFromIntent(getIntent() );

        otherUsername = findViewById(R.id.other_username);
        messageInput = findViewById(R.id.chat_message_input);
        backBtn = findViewById(R.id.back_btn);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        recyclerView = findViewById(R.id.chat_recycler_view);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });

        otherUsername.setText(otherUser.getUsername() );


    }
}