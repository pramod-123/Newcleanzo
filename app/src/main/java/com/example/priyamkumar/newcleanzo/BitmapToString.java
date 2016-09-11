package com.example.priyamkumar.newcleanzo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by pramod on 9/11/16.
 */
public class BitmapToString {

    public static String BitToString(Bitmap bm){
        if(bm!=null){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        String encoded = Base64.encodeToString(b, Base64.DEFAULT);
        return encoded;
        }
        return null;
    }
    public static Bitmap StringToBitmap(String encodedImage){
        if(encodedImage!=null){
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
        }return null;
    }
}
