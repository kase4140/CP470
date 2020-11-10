package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
    SQLiteDatabase db2;
    ChatDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        textSend=findViewById(R.id.chatText);
        send=findViewById(R.id.sendButtonMes);
        chatview=findViewById(R.id.chatView);
        databaseText = new ArrayList<>();

//        part 5
        dbHelper  = new ChatDatabaseHelper(this);
        db2 = dbHelper.getWritableDatabase();

        Cursor c = db2.rawQuery("SELECT KEY_MESSAGE FROM ChatTable", null);
        c.moveToFirst();
        while(!c.isAfterLast() ) {
            Log.i("ChatWindow", "SQL MESSAGE:" + c.getString(c.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
//        Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + c.getColumnCount() );
            databaseText.add(c.getString(c.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            c.moveToNext();
        }
        Log.i("ChatWindow", "Cursor’s  column count =" + c.getColumnCount() );
        for(int i = 0; i < c.getColumnCount(); i++){
            Log.i("ChatWindow","table name column: "+c.getColumnName(i));
        }
        c.close();


        messageAdapter =new ChatAdapter( this );
        chatview.setAdapter(messageAdapter);
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ContentValues valueSql = new ContentValues();
                String value = textSend.getText().toString();
                valueSql.put(ChatDatabaseHelper.KEY_MESSAGE, value);
                db2.insert(ChatDatabaseHelper.TABLE_NAME, null, valueSql);
                databaseText.add(value);
                messageAdapter.notifyDataSetChanged();
                textSend.setText("");
            }
        });
    }
    public void onDestroy(){
        super.onDestroy();
        db2.close();
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