package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    EditText text;
    View v;
    String changed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        changed = "You selected item 1";
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "menu bar clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()) {
            case R.id.action_one:
                Snackbar.make(getWindow().getDecorView().findViewById(R.id.toolbar)
                        , changed, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.d("toolbar", "option 1 is selected");
                break;
            case R.id.action_two:
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle(R.string.alertMessage);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

                Log.d("toolbar", "option 2 is selected ");
                break;
            case R.id.action_three:
                Log.d("toolbar", "option 3 is selected");
                AlertDialog.Builder customBuilder = new AlertDialog.Builder(TestToolbar.this);
                customBuilder.setTitle("Change text");
                LayoutInflater inflater = this.getLayoutInflater();
                v = inflater.inflate(R.layout.custom_dialog_box, null);
                customBuilder.setView(v);
                customBuilder.setPositiveButton(R.string.setText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        text = v.findViewById(R.id.newMessage);
                        changed = text.getText().toString();
                    }
                });
                customBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
// Create the AlertDialog
                AlertDialog customDialog = customBuilder.create();
                customDialog.show();


                break;
            default:
//                action button
                Toast toast = Toast.makeText(this, "Version 1.0, by Hamdan Kasem", Toast.LENGTH_LONG);
                toast.show();
        }
        return false;
    }
}