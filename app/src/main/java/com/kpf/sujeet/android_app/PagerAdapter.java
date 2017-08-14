package com.kpf.sujeet.android_app;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by SUJEET on 8/10/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    String tabtitles[] = {"Actions","Show List"};

    public PagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        //according to position setting the fragments
        switch (position){
            case 0:
                return new Action();
            case 1:
                return new Show_List();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
//for swaping the titles
    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}
