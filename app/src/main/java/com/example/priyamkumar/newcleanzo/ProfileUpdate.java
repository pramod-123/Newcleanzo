package com.example.priyamkumar.newcleanzo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

/**
 * Created by pramod on 9/9/16.
 */
public class ProfileUpdate {
    private static ProfileUpdate ourInstance;
    private Context context;
    static String firstName;
    static String lastName;
    static String email;
    static String phoneNo;
    static Bitmap profileBitmap;
    static String profilePic;
    private static boolean isSassion;
    public static boolean getSession(){

        return isSassion;
    }
    public static void setSession(boolean set) {
        isSassion=set;
    }

    void updateProfileInfo(){

        SharedPreferences sharedPreferences=context.getSharedPreferences(context.getString(R.string.USER_PROFILE),Context.MODE_PRIVATE);
        phoneNo=sharedPreferences.getString(context.getString(R.string.phone_no)," ");
        email=sharedPreferences.getString(context.getString(R.string.email)," ");
        firstName=sharedPreferences.getString(context.getString(R.string.first_name)," ");
        lastName=sharedPreferences.getString(context.getString(R.string.last_name)," ");
        setSession(sharedPreferences.getBoolean(context.getString(R.string.session),false));

    }
    public static ProfileUpdate getInstance(Context context) {

        if (ourInstance==null){
            ourInstance=new ProfileUpdate(context);
        }
        return ourInstance;
    }
    void deletSharedPref(){
        SharedPreferences sharedPreferences=context.getApplicationContext().getSharedPreferences(context.getString(R.string.USER_PROFILE),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        ProfileUpdate.profileBitmap=null;
        editor.clear();
        editor.commit();
    }

    void writeIntoSharedPref(final  String firstName,final String lastName,final String email,final String phoneNo){

        SharedPreferences sharedPreferences=context.getSharedPreferences(context.getString(R.string.USER_PROFILE),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.session),true);
        editor.putString(context.getString(R.string.first_name),firstName);
        editor.putString(context.getString(R.string.last_name),lastName);
        editor.putString(context.getString(R.string.email),email);
        editor.putString(context.getString(R.string.phone_no),phoneNo);
        editor.commit();


    }

    private ProfileUpdate(Context context) {

        this.context=context;
    }
}
