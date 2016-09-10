package com.example.priyamkumar.newcleanzo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Priyam Kumar on 7/28/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList <PagerItem> pagerTabData=new ArrayList<>();

    public void addFragments(PagerItem pagerItem){

        this.pagerTabData.add(pagerItem);
    }



    public  ViewPagerAdapter(FragmentManager fm){

        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return pagerTabData.get(position).getFragment();

    }


    @Override
    public int getCount() {

        return pagerTabData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return pagerTabData.get(position).getTitle();
    }
}
