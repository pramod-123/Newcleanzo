package com.example.priyamkumar.newcleanzo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;
import java.util.Map;

public class EditDetailsActivity extends AppCompatActivity {
    TextView email;
    EditText firstName,lastName,phoneNo;
    CircularImageView profilePic;
    RelativeLayout bg;
    Button ok;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        intialize();
        setSupportActionBar(toolbar);
        if(ProfileUpdate.profileBitmap!=null) {
            profilePic.setImageBitmap(ProfileUpdate.profileBitmap);
            bg.setBackground(new BitmapDrawable(BlurBuilder.blur(getApplicationContext(), ProfileUpdate.profileBitmap)));
        }else {
            profilePic.setImageResource(R.drawable.default_profile_pic);
            Drawable myDrawable = getResources().getDrawable(R.drawable.default_profile_pic);
            Bitmap anImage      = ((BitmapDrawable) myDrawable).getBitmap();
            bg.setBackground(new BitmapDrawable(BlurBuilder.blur(getApplicationContext(),anImage)));
        }
        firstName.setText(ProfileUpdate.firstName);
        lastName.setText(ProfileUpdate.lastName);
        email.setText(ProfileUpdate.email);
        phoneNo.setText(ProfileUpdate.phoneNo);
        ok.setVisibility(View.GONE);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditDetailsActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int r_id=item.getItemId();
        if(r_id==R.id.save_profile_items){
            update(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),phoneNo.getText().toString());
        }

        return true;
    }

    void intialize(){
        toolbar= (Toolbar) findViewById(R.id.edit_details_toolbar);
        ok= (Button) findViewById(R.id.ok_edit_detail);
        email= (TextView) findViewById(R.id.email_id_edit_detail);
        firstName= (EditText) findViewById(R.id.first_name_edit_detail);
        lastName= (EditText) findViewById(R.id.last_name_edit_detail);
        phoneNo= (EditText) findViewById(R.id.phone_no_edit_detail);
        profilePic= (CircularImageView) findViewById(R.id.profile_pic_edit_detail);
        bg= (RelativeLayout) findViewById(R.id.edit_detail_bg);
    }

    void update(final String firstName,final String lastName,final String email,final String phoneNo){

        String url="http://192.168.1.5/myapp/update_php.php";
        final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                ProfileUpdate.getInstance(getApplicationContext()).writeIntoSharedPref(firstName,lastName,email,phoneNo);
                ProfileUpdate.getInstance(getApplicationContext()).updateProfileInfo();
                ok.setVisibility(View.VISIBLE);
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

}
