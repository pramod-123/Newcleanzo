package com.example.priyamkumar.newcleanzo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SingUpActivity extends AppCompatActivity {
    EditText firstName,lastName,email,password,phoneNo;
    Button signUp;
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        intialize();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToSerever(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),phoneNo.getText().toString());

            }
        });
    }


    void intialize(){
        signUp= (Button) findViewById(R.id.sign_up);
        firstName= (EditText) findViewById(R.id.first_name);
        lastName= (EditText) findViewById(R.id.last_name);
        email= (EditText) findViewById(R.id.email_id);
        password= (EditText) findViewById(R.id.password);
        phoneNo= (EditText) findViewById(R.id.phone_no);
    }

    void saveToSerever(final String firstName,final String lastName,final String email,final String phoneNo){

        String url="http://192.168.1.5/myapp/insert.php";
        final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.equals("Error")){
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    ProfileUpdate.getInstance(getApplicationContext()).writeIntoSharedPref(firstName,lastName,email,phoneNo);
                    ProfileUpdate.getInstance(getApplicationContext()).updateProfileInfo();
                    startActivity(new Intent(SingUpActivity.this,MainActivity.class));
                    finish();

                }
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
