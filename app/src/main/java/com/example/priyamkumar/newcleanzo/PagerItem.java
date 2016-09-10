package com.example.priyamkumar.newcleanzo;

import android.support.v4.app.Fragment;

/**
 * Created by pramod on 9/9/16.
 */
public class PagerItem {
    private String mTitle;
    private Fragment mFragment;


    public PagerItem( Fragment mFragment,String mTitle) {
        this.mTitle = mTitle;
        this.mFragment = mFragment;
    }
    public String getTitle() {
        return mTitle;
    }
    public Fragment getFragment() {
        return mFragment;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

}
