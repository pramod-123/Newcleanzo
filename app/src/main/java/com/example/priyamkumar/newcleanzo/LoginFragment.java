package com.example.priyamkumar.newcleanzo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by pramod on 9/9/16.
 */
public class LoginFragment extends Fragment {
    Button btnFBSignIn,btnGoogleSignIn,btnSignIn;
    EditText email,password;
    TextView forgotPassword,signUp;
    OnFragmentButtonsClickListner onFragmentButtonsClickListner;
    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_fragement,container,false);

        intialize(view);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentButtonsClickListner.setOnSignUpClickListner();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentButtonsClickListner.setOnForgetPasswordCliceListner();
            }
        });


        btnFBSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentButtonsClickListner.setOnFBLoginClickListner();

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             onFragmentButtonsClickListner.setOnSignInClickListner(email.getText().toString(),password.getText().toString());
            }

        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onFragmentButtonsClickListner.setOnSignUpClickListner();
            }
        });

        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentButtonsClickListner.setOnGoogleClickListner();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onFragmentButtonsClickListner=(OnFragmentButtonsClickListner) activity;
    }

    private void intialize(View view){
        forgotPassword= (TextView) view.findViewById(R.id.forgot_password);
        signUp= (TextView) view.findViewById(R.id.sign_up);
        btnFBSignIn= (Button) view.findViewById(R.id.fb_login);
        btnGoogleSignIn= (Button) view.findViewById(R.id.google_sign_in);
        btnSignIn= (Button) view.findViewById(R.id.sign_in);
        email= (EditText) view.findViewById(R.id.email);
        password= (EditText) view.findViewById(R.id.password);
    }

    public interface OnFragmentButtonsClickListner{
        void setOnFBLoginClickListner();
        void setOnGoogleClickListner();
        void setOnForgetPasswordCliceListner();
        void setOnSignInClickListner(String email,String password);
        void setOnSignUpClickListner();
    }


}
