package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    EditText textSend;
    Button send;
    ListView chatview;
    ArrayList<String> databaseText;
    ChatAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        textSend=findViewById(R.id.chatText);
        send=findViewById(R.id.sendButtonMes);
        chatview=findViewById(R.id.chatView);

        databaseText = new ArrayList<>();
        messageAdapter =new ChatAdapter( this );
        chatview.setAdapter(messageAdapter);
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String value = textSend.getText().toString();
                databaseText.add(value);
                messageAdapter.notifyDataSetChanged();
                textSend.setText("");
            }
        });

    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }
        public int getCount(){
            int counting = databaseText.size();
            return counting;
        }
        public String getItem(int position){
            return databaseText.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView message = result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;
        }
    }
}