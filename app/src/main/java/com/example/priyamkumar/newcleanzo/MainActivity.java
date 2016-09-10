package com.example.priyamkumar.newcleanzo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentButtonsClickListner,UserProfileFragment.OnUserProfielButtonsClickListner {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        callbackManager=CallbackManager.Factory.create();
        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                /*Toast.makeText(MainActivity.this,profile.getFirstName(),Toast.LENGTH_LONG).show();
                FBLogin.setText("LOGOUT");*/
                if(currentProfile!=null){

                }

            }
        };

        profileTracker.startTracking();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                String email;
                                try {
                                     email= object.getString("email");
                                    Log.v("mail", email);
                                    String []sArray=new String[]{"https://graph.facebook.com/"+ Profile.getCurrentProfile().getId()+"/picture?type=large",email,Profile.getCurrentProfile().getFirstName(),Profile.getCurrentProfile().getLastName()};
                                    new ImageDownHelper(getApplicationContext(),MainActivity.this).execute(sArray);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

   }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        ProfileUpdate.getInstance(getApplicationContext()).updateProfileInfo();

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new PagerItem(new SchedulePickup(),"Schedule your PickUp"));
        if(!ProfileUpdate.getSession())
            viewPagerAdapter.addFragments(new PagerItem(new LoginFragment(),"You"));
        else viewPagerAdapter.addFragments(new PagerItem(new UserProfileFragment(),"You"));
        viewPagerAdapter.addFragments(new PagerItem(new Offers(),"Offers and Rewards"));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);




    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void setOnFBLoginClickListner() {
        ArrayList<String> a = new ArrayList<String>();
        a.add("email");
        a.add("public_profile");
        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, a);
    }

    @Override
    public void setOnGoogleClickListner() {

    }

    @Override
    public void setOnForgetPasswordCliceListner() {
        startActivity(new Intent(MainActivity.this,ForgotPasswordActivity.class));
    }

    @Override
    public void setOnSignInClickListner(String email, String password) {
        retieveDataFromServee(email);
    }



    @Override
    public void setOnSignUpClickListner() {
        startActivity(new Intent(MainActivity.this,SingUpActivity.class));
    }

    @Override
    public void setOnLogOutClickListner() {
        ProfileUpdate.getInstance(getApplicationContext()).deletSharedPref();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(MainActivity.this,MainActivity.class));
        finish();
    }

    void retieveDataFromServee(final String email){
        String url="http://192.168.1.5/myapp/login.php";
        final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    ProfileUpdate.getInstance(getApplicationContext()).writeIntoSharedPref(jsonObject.getString("first_name"),jsonObject.getString("last_name"),jsonObject.getString("email"),jsonObject.getString("phone_no"));
                    ProfileUpdate.getInstance(getApplicationContext()).updateProfileInfo();
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Credential not exist",Toast.LENGTH_SHORT).show();
                    requestQueue.stop();
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
