package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME,"IN onResume()");
        mainButton = findViewById(R.id.imbutton);
        mainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);
            }
        });
    }
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if(requestCode==10){
            Log.i(ACTIVITY_NAME,"Returned to MainActivity.onActivityResult");
        }
        if(requestCode == Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
            Toast toast = Toast.makeText(this, "ListItemsActivity passed: " + messagePassed+" My information to share", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME,"IN onResume()");
    }
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"IN onStart()");
    }

    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"IN onPause()");
    }
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"IN onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"IN onDestroy()");
    }
}