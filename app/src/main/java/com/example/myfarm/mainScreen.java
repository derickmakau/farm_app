package com.example.myfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class mainScreen extends AppCompatActivity {
Button createFarm,manageFarm;
private mainScreen listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        createFarm = (Button) findViewById(R.id.createFarmId);
        manageFarm = (Button) findViewById(R.id.manageFarmId);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        AlertDialog.Builder builder= new AlertDialog.Builder(mainScreen.this);
        builder.setTitle("New farm");
        final View customLayout= getLayoutInflater().inflate(R.layout.custom_layout,null);
        builder.setView(customLayout);
        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                EditText editText= customLayout.findViewById(R.id.editText);
                sendDialogDataToActivity(editText.getText().toString());
            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();
            }
            private void sendDialogDataToActivity(String data){
                Toast.makeText(mainScreen.this, data, Toast.LENGTH_SHORT).show();
                Intent farmName=new Intent(getApplicationContext(),createfarm.class);
                String  str=data;
                farmName.putExtra("message_key",str);
                startActivity(farmName);
            }
        });
        manageFarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                account();
            }
        });
    }
    public void account(){
        Intent accounts=new Intent(mainScreen.this,managefarm.class);
        startActivity(accounts);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mainscreenmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.searchId:
                Toast.makeText(getApplicationContext(),"you clicked search icon", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settingsId:
                Toast.makeText(getApplicationContext(),"you clicked settings",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logoutId:
                ParseUser.logOutInBackground(e -> {
                    dismiss();

                });
                return true;
            case R.id.share:
                Toast.makeText(getApplicationContext(),"you clicked share",Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void dismiss(){
        Intent back= new Intent(mainScreen.this,aftersplash.class);
        startActivity(back);
    }



}