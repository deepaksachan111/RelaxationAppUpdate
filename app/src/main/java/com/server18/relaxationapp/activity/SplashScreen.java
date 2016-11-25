package com.server18.relaxationapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.server18.relaxationapp.HomeActivity;
import com.server18.relaxationapp.R;

public class SplashScreen extends Activity {

    private String Fkid;
     int a = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ImageView image = (ImageView)findViewById(R.id.image_vew);

        startactivity();


    }
    private void startactivity(){

        Thread thread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                      SharedPreferences sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();

                    Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                    intent.putExtra("int",a);
                    startActivity(intent);
                    finish();
                }
            }

        };thread.start();
    }
}
