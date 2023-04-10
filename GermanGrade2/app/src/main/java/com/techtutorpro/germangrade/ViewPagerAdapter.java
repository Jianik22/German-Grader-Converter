package com.techtutorpro.germangrade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public  class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new BscFragment();
        }else {
            return new MastersFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Bachelor";
        }else{
            return "Masters";
        }

    }
}
