package com.example.androidassignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    ImageButton imgbtn;
    Switch switchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME,"IN onResume()");
        imgbtn = findViewById(R.id.imgbutton);
        imgbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent takepictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takepictureIntent,1);

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgbtn.setImageBitmap(imageBitmap);
        }
    }
    public void setOnCheckedChange(View v){
        CharSequence text = "";// "Switch is Off"

        int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off
        switchbtn = findViewById(R.id.switchbtn);
        if(switchbtn.isChecked()==true){
            duration=Toast.LENGTH_SHORT;
            text="Switch is On";
        }else if(switchbtn.isChecked()==false){
            text = "Switch is Off";
            duration=Toast.LENGTH_LONG;
        }
        Toast toast = Toast.makeText(this , text, duration); //this is the ListActivity
        toast.show(); //display your message box
    }
    public void onCheckChanged(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml
                .setTitle(R.string.dialog_title)

                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
//                                finish();
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "Here is my response");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();

                            }
                        })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                    dialog.dismiss();
                }
            })
            .show();

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