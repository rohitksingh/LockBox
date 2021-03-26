package com.rohitksingh.lockbox.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import io.embrace.android.embracesdk.Embrace;

import android.os.Bundle;
import com.rohitksingh.lockbox.R;
import com.rohitksingh.lockbox.listener.SignupListener;
import com.rohitksingh.lockbox.models.Credential;
import com.rohitksingh.lockbox.viewmodels.CredentialViewModel;
import com.rohitksingh.lockbox.views.LoginFragment;
import com.rohitksingh.lockbox.views.SignUpFragment;

public class CredentialActivity extends AppCompatActivity implements SignupListener {

    private CredentialViewModel viewModel;
    private static final String TAG = "CredentialActivity";

    /***********************************************************************************************
     *                                  Lifecycle methods
     **********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);
        Embrace.getInstance().start(this);
        initViewModel();
        if(savedInstanceState==null)
            observeViewModel();
    }

    /***********************************************************************************************
     *                                  Callback methods
     **********************************************************************************************/
    @Override
    public void submit(Credential credential) {
        viewModel.savePassword(credential);
    }

    /***********************************************************************************************
     *                                  Private methods
     **********************************************************************************************/
    private void initViewModel(){

        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication());

        viewModel = new ViewModelProvider(this, factory).get(CredentialViewModel.class);

    }

    private void observeViewModel(){
        viewModel.credentialLiveData.observe(this, credential -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.credentialFragmentHolder, getFragment(), "FRAGMENT")
                    .commit();
        });

        viewModel.getCredential();
    }

    private Fragment getFragment(){

        if(ifAlreadyLoggedIn()){
            return LoginFragment.getInstance(viewModel.credentialLiveData.getValue());
        }else{
            return SignUpFragment.getInstance();
        }
    }


    //Use something else to determine the if the user is logged in// may be match default password
    private boolean ifAlreadyLoggedIn(){
        int passwordLength = viewModel.credentialLiveData.getValue().getPassword().length();
        return passwordLength>0;
    }

}