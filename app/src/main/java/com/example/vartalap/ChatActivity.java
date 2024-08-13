package com.example.vartalap;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vartalap.model.ChatroomModel;
import com.example.vartalap.model.UserModel;
import com.example.vartalap.utils.AndroidUtil;
import com.example.vartalap.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    UserModel otherUser;
    EditText messageInput;
    ImageButton sendMessageBtn, backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;
    String chatroomId;
    ChatroomModel chatroomModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //get UserModel
        otherUser = AndroidUtil.getUserModelFromIntent(getIntent() );
        chatroomId = FirebaseUtil.getChatroomId(FirebaseUtil.currentUserId(),otherUser.getUserId());

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


        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=messageInput.getText().toString().trim();
                if(message.isEmpty())
                    return;
                sendMessageToUser(message);
            }
        });

        getOrCreateChatroomModel();



    }       // onCreate method closed

    void sendMessageToUser(String message)
    {

    }

    void getOrCreateChatroomModel()    {
        FirebaseUtil.getChatroomReference(chatroomId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                chatroomModel = task.getResult().toObject(ChatroomModel.class);
            }
            if(chatroomModel==null)
            {   // first time chat
                chatroomModel = new ChatroomModel(
                        chatroomId,
                        Arrays.asList(FirebaseUtil.currentUserId(),otherUser.getUserId()),
                        Timestamp.now(),
                        "" );
                FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);
            }
        });

    }

}