package com.example.priyamkumar.newcleanzo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pramod on 9/9/16.
 */
public class ImageDownHelper extends AsyncTask<String, Void, Bitmap> {
    Activity activity;
    Context context;
    public ImageDownHelper(Context c, Activity activity){
        context=c;
        this.activity=activity;
    }
    public Bitmap doInBackground(String... urls) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(urls[0]);
            Log.d("photolink",aURL.toString());
            ProfileUpdate.firstName=urls[2];
            ProfileUpdate.lastName=urls[3];
            ProfileUpdate.email=urls[1];
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("img", "Error getting bitmap", e);
        }
        return bm;
    }

    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
        ProfileUpdate.profileBitmap=bm;
        retieveDataFromServee(ProfileUpdate.email);
        Log.d("ProfileEmail",ProfileUpdate.email);
    }


    void retieveDataFromServee(final String email){
        String url="http://192.168.1.5/myapp/login.php";
        RequestQueue requestQueue= Volley.newRequestQueue(activity.getApplicationContext());
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                   try {
                        JSONObject jsonObject=new JSONObject(response);
                        ProfileUpdate.getInstance(context).writeIntoSharedPref(jsonObject.getString("first_name"),jsonObject.getString("last_name"),jsonObject.getString("email"),jsonObject.getString("phone_no"));
                        ProfileUpdate.getInstance(context).updateProfileInfo();
                        activity.startActivity(new Intent(activity,MainActivity.class));
                       ProfileUpdate.getInstance(context).writeBitmapTosharePref(ProfileUpdate.profileBitmap);
                        activity.finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                       Log.d("JSONExcp",e.getMessage());
                       Log.d("EditFragment","hello");
                       activity.startActivity(new Intent(activity,EditProfileFragment.class));
                       activity.finish();

                   }

                               }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("email",email);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}