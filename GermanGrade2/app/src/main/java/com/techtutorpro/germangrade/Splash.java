package com.techtutorpro.germangrade;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.techtutorpro.germangrade.adscontrol.Admob;

public class Splash extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Admob.loadInt(Splash.this);



         textView=findViewById(R.id.splashTextView);
         imageView=findViewById(R.id.imageView);




        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.splashanimation);


      textView.setAnimation(animation);
       imageView.setAnimation(animation);
        Intent intent= new Intent(Splash.this,MainActivity.class);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(intent);
               finish();

           }
       },3000);
    }
}