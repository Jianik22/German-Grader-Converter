package com.techtutorpro.germangrade.adscontrol;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.techtutorpro.germangrade.R;


public class Admob {





    static OnDismiss onDismiss;

    public Admob(OnDismiss onDismiss) {
        this.onDismiss = onDismiss;
    }

    public Admob() {
    }

    public  static AdManagerInterstitialAd mAdManagerInterstitialAd;


    public static void loadInt(Context context){
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });




            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

            AdManagerInterstitialAd.load(context, context.getString(R.string.AdMob_Interstitial), adRequest,
                    new AdManagerInterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                            // The mAdManagerInterstitialAd reference will be null until
                            // an ad is loaded.
                            mAdManagerInterstitialAd = interstitialAd;
                            Log.i(TAG, "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            Log.d(TAG, loadAdError.toString());
                            mAdManagerInterstitialAd = null;
                        }
                    });






    }



    public  void ShowInt(Activity activity){


        if (mAdManagerInterstitialAd != null) {
            mAdManagerInterstitialAd.show(activity);

        mAdManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();

                mAdManagerInterstitialAd=null;
                loadInt(activity);

                onDismiss.onDismiss();


            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);

                onDismiss.onDismiss();
            }
        });

        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");

            onDismiss.onDismiss();
        }
    }
}
