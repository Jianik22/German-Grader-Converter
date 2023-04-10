package com.techtutorpro.germangrade;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class about extends AppCompatActivity {

     Toolbar toolbar;
         StringBuilder stringBuilder;

         TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textView=findViewById(R.id.DevelopTextView);

        toolbar=findViewById(R.id.aboutToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("About Us");
        actionBar.setDisplayHomeAsUpEnabled(true);
        stringBuilder=new StringBuilder();

        stringBuilder.append(getResources().getString(R.string.app_name)+ " "+ " is developed by Digital Application Studio Team. You can contact us to make any android Application.");



        textView.setText(stringBuilder);






    }
}