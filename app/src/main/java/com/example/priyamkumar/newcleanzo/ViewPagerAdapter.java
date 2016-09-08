package com.example.priyamkumar.newcleanzo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Priyam Kumar on 7/28/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList <Fragment> fragments=new ArrayList<>();
    ArrayList <String> tabTitles=new ArrayList<>();

    public void addFragments(Fragment fragment,String string){
        this.fragments.add(fragment);
        this.tabTitles.add(string);


    }

    public  ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}