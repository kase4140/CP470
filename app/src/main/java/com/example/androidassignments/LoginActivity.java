package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginActivity";
    Button loginbut;
    SharedPreferences sharedpref;
    private EditText loginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME,"IN onResume()");
        loginbut = findViewById(R.id.loginButton);
        loginName = findViewById(R.id.loginName);
        sharedpref = getSharedPreferences("emailAddress", Context.MODE_PRIVATE);
        loginName.setText(sharedpref.getString("EmailAddress","email@domain.com"));
        loginbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharedpref.edit();
                edit.putString("EmailAddress",loginName.getText().toString());
                edit.commit();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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