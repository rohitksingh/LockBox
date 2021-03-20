package com.rohitksingh.lockbox.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.rohitksingh.lockbox.R;
import com.rohitksingh.lockbox.listener.LoginFragmentListener;
import com.rohitksingh.lockbox.models.Credential;
import com.rohitksingh.lockbox.viewmodels.CredentialViewModel;

public class CredentialActivity extends AppCompatActivity implements LoginFragmentListener {

    private CredentialViewModel viewModel;
    private static final String TAG = "CredentialActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);
        initViewModel();
        viewModel.getCredential(this);

    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(CredentialViewModel.class);
        viewModel.credentialLiveData.observe(this, credential -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.credentialFragmentHolder, getFragment(), "Something")
                    .commit();
        });
    }

    public Fragment getFragment(){

        if(ifAlreadyLoggedIn()){
            Log.d(TAG, "getFragment: already logged in");
            return LoginFragment.getInstance();
        }else{
            Log.d(TAG, "getFragment: already not logged in");
            return SignUpFragment.getInstance();
        }
    }


    private boolean ifAlreadyLoggedIn(){
        int passwordLength = viewModel.credentialLiveData.getValue().getPassword().length();
        Log.d(TAG, "ifAlreadyLoggedIn: L="+passwordLength);
        //return viewModel.credentialLiveData.getValue().getPassword().length()>0;
        return passwordLength>0;
    }


    @Override
    public void submit(Credential credential) {
        viewModel.savePassword(credential);
    }
}