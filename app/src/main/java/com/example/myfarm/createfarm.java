package com.example.myfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class createfarm extends AppCompatActivity{
TextView farmName, typeOfFarm, farmSlogan,phoneNumber,emailAddress;
Button submit;
EditText farmType,slogan,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createfarm);
        Intent intent = getIntent();
        String str= intent.getStringExtra("message_key");
        farmName=(TextView)findViewById(R.id.farmName);
        farmName.setText(str);
        submit=(Button)findViewById(R.id.submitId);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent goingfinal=new Intent(createfarm.this, Final.class);
                startActivity(goingfinal);
            }
        });
    }
}