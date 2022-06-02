package com.example.myfarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class Final extends AppCompatActivity {
    private int REQUEST_PICK_IMAGE=1000;
    ImageView imageView;
    Button pick,startCamera;
   /* public void getPhoto(){
        Intent photoIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(photoIntent,1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults)
    {super.onRequestPermissionsResult(requestCode, permissions,grandResults);
        if(requestCode==1){
            if(grandResults.length>0 && grandResults[0]== PackageManager.PERMISSION_GRANTED){
                getPhoto();
            }
        }}*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        imageView=(ImageView)findViewById(R.id.imageViewId);
        pick=(Button)findViewById(R.id.pickId);
        startCamera=(Button)findViewById(R.id.startCameraId);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
onPickImage();
            }
        });
        /*
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },1);
        }
        else{
            getPhoto();
        }*/
    }
    public void onPickImage(){
Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
intent.setType("image/*");
startActivityForResult(intent,REQUEST_PICK_IMAGE);
    }
    public void onStartCamera(View view){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK){
            if(requestCode== REQUEST_PICK_IMAGE){
                Uri uri= data.getData();
                Bitmap bitmap= loadFromUri(uri);
                imageView.setImageBitmap(bitmap);

            }
        }
    }
    private Bitmap loadFromUri(Uri uri){
        Bitmap bitmap= null;
        try{
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1){
                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(),uri);
                bitmap= ImageDecoder.decodeBitmap(source);
            }
            else{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.share){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                },1);
            }
            else{
                getPhoto();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        Uri selectedImage= data.getData();
        if(requestCode == 1 && resultCode== RESULT_OK && data!=null){
            try{
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);
                byte[] byteArray= stream.toByteArray();
                ParseFile file= new ParseFile("image.png",byteArray);
                ParseObject object= new ParseObject("Images");
                object.put("image",file);
                object.put("username", ParseUser.getCurrentUser().getUsername());
                object.saveInBackground(new SaveCallback(){
                    @Override
                    public void done(ParseException e){
                        if(e==null){
                            Toast.makeText(Final.this,"Image has been shared!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Final.this,"There has been an issue uploading the image",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            catch(Exception e){

            }
        }
    }*/
}