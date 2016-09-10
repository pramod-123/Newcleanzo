package com.example.priyamkumar.newcleanzo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfileFragment extends AppCompatActivity{
    TextView email;
    EditText firstName,lastName,phoneNo;
    CircularImageView profilePic;
    RelativeLayout bg;
    Button ok;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_fragment);
        intialize();
        if(ProfileUpdate.profileBitmap==null){
            Drawable drawable=getDrawable(R.drawable.default_profile_pic);
            Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
            bg.setBackground(new BitmapDrawable(BlurBuilder.blur(getApplicationContext(),bitmap)));
            profilePic.setBackgroundResource(R.drawable.default_profile_pic);
        }else {
            bg.setBackground(new BitmapDrawable(BlurBuilder.blur(getApplicationContext(),ProfileUpdate.profileBitmap)));
            profilePic.setImageBitmap(ProfileUpdate.profileBitmap);

        }
        firstName.setText(ProfileUpdate.firstName);
        lastName.setText(ProfileUpdate.lastName);
        email.setText(ProfileUpdate.email);
        phoneNo.setText("");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToSerever(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),phoneNo.getText().toString());
                ProfileUpdate.getInstance(getApplicationContext()).writeIntoSharedPref(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),phoneNo.getText().toString());
                ProfileUpdate.getInstance(getApplicationContext()).updateProfileInfo();
                startActivity(new Intent(EditProfileFragment.this,MainActivity.class));
                finish();
            }
        });
    }
    void saveToSerever(final String firstName,final String lastName,final String email,final String phoneNo){

        String url="http://192.168.1.5/myapp/insert.php";
        final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                requestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            requestQueue.stop();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String>map=new HashMap<>();
                map.put("first_name",firstName);
                map.put("last_name",lastName);
                map.put("email",email);
                map.put("phone_no",phoneNo);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    void retieveDataFromServee(final String email){
        String url="";
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("email",email);
                return map;
            }
        };
    }
    void intialize(){
        ok= (Button) findViewById(R.id.ok);
        email= (TextView) findViewById(R.id.email_id);
        firstName= (EditText) findViewById(R.id.first_name);
        lastName= (EditText) findViewById(R.id.last_name);
        phoneNo= (EditText) findViewById(R.id.phone_no);
        profilePic= (CircularImageView) findViewById(R.id.profile_pic);
        bg= (RelativeLayout) findViewById(R.id.edit_profile_bg);
    }
}
