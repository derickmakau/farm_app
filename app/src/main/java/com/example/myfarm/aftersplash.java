package com.example.myfarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class aftersplash extends AppCompatActivity {
EditText emailAddress, password;
Button login, register;
CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId(getString(R.string.back4app_app_id))
        .clientKey(getString(R.string.back4app_client_key))
        .server(getString(R.string.back4app_server_url))
        .build());
        setContentView(R.layout.activity_aftersplash);
        emailAddress=(EditText)findViewById(R.id.editTextTextEmailAddress);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        login=(Button)findViewById(R.id.loginId);
        register=(Button)findViewById(R.id.register);
        checkBox=(CheckBox)findViewById(R.id.checkId);
        /*  pwd.trasformationMethod= HideReturnsTransformationMethod.getInstance()
        * showHideBtn.text="Hide"*/
        /* pwd.transformationMethod= passwordTransformationMethod.getInstance()
        * showHideBtn.text="show"
        * */
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(!isChecked){
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
            else{
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
                }
        });

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
               String emailAddressTwo=emailAddress.getText().toString();
               String passwordTwo=password.getText().toString();

                if(TextUtils.isEmpty(emailAddressTwo)&& TextUtils.isEmpty(passwordTwo)){
                    Toast.makeText(aftersplash.this,"Please enter email Address and password",Toast.LENGTH_SHORT).show();
                }
                else{login();}
            }
        });
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clickRegister();
            }
        });
    }
    private void clickRegister(){
        ParseUser user= new ParseUser();
        String registeremail=emailAddress.getText().toString();
        String registerpassword=password.getText().toString();
        user.setUsername(registeremail);
        user.setPassword(registerpassword);
        if(isEmail(emailAddress)==false){
            emailAddress.setError("Enter valid email!");
        }
        else{
        user.signUpInBackground(new SignUpCallback(){
            @Override
            public void done(ParseException e){
                if(e==null){
                    mainScreen();
                }
                else{
                    ParseUser.logOut();
                    Toast.makeText(aftersplash.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });}
    }
    private boolean isEmail(EditText text){
        CharSequence email= text.getText().toString();
        return(!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
   private void mainScreen(){
        Intent viewOne= new Intent(aftersplash.this,mainScreen.class);
        startActivity(viewOne);
    }
    private void mainScreenTwo(){
        Intent viewTwo= new Intent(aftersplash.this,mainScreen.class);
        startActivity(viewTwo);
    }
    /* private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
} */

    private void login(){
        ParseUser parseUser= new ParseUser();
 ParseUser.logInInBackground(emailAddress.getText().toString(), password.getText().toString(), new LogInCallback() {
     @Override
     public void done(ParseUser user, ParseException e) {
         if(parseUser!=null){
             mainScreenTwo();
         }
         else{

         }
     }
     });
 }


}