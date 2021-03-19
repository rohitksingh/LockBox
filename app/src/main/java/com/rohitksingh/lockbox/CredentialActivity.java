package com.rohitksingh.lockbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class CredentialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.credentialFragmentHolder, getFragment(), "CREDENTIAL_HOLDER")
                .commit();

    }

    public Fragment getFragment(){

        if(ifAlreadyLoggedIn()){
            return LoginFragment.getInstance();
        }else{
            return SignUpFragment.getInstance();
        }
    }


    private boolean ifAlreadyLoggedIn(){
        return false;
    }

}