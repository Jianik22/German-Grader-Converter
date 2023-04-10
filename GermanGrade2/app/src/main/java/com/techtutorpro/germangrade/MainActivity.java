package com.techtutorpro.germangrade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;


import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;




import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tab;
     DrawerLayout drawerLayout;
     NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //tab layout
        tab=findViewById(R.id.tab);
        tab.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        tab.setTabIconTint(ColorStateList.valueOf(getResources().getColor(R.color.white)));


        viewPager=findViewById(R.id.viewpager);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        //navigation view setup

        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.naviagationID);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id=item.getItemId();
               switch (id){
                   case R.id.about:
                       about();
                       break;
                       
                   case R.id.contact:
                       contact();
                       break;
                   default:
                       privacy();



               }



                return true;
            }
        });

    }

    private void privacy() {

        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://digitalapplicationstudio.blogspot.com/2021/05/privacy-policy-for-digital-application.html"));
        startActivity(intent);

    }

    private void contact() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] recipients = {"alexkrein9.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_CC, "");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    private void about() {

       Intent intent=new Intent(MainActivity.this, about.class);
       startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.uppermenu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.starup:
                star();
                break;
                
            default:
                share();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {
        final String AppPackageName=getPackageName();
        Intent shareIntent=new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                 "Download"+ getResources().getString(R.string.app_name) + " : " +"https://play.google.com/store/apps/details?id="+ AppPackageName);

        shareIntent.setType("text/plan");
          startActivity(shareIntent);
    }

    private void star() {
        final String AppPackageName=getPackageName();
        try {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+ AppPackageName));
            startActivity(intent);

        }catch (Exception exception){

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ AppPackageName));
              startActivity(intent);
        }

    }

     void backdialog(){
         AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
         builder.setTitle("Do you want to Exit?");
         builder.setIcon(R.drawable.ic_baseline_add_alert_24);
         builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 finish();
             }
         });
         builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {

             }
         });

         builder.setCancelable(false);
         AlertDialog alertDialog= builder.create();
         alertDialog.show();

     }

    @Override
    public void onBackPressed() {

         if (drawerLayout.isDrawerOpen(GravityCompat.START)){
             drawerLayout.closeDrawer(GravityCompat.START);
         }else{
             backdialog();
         }

    }









}