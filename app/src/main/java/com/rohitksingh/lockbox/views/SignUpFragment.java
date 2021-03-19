package com.rohitksingh.lockbox.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rohitksingh.lockbox.R;
import com.rohitksingh.lockbox.databinding.FragmentSignupBinding;
import com.rohitksingh.lockbox.models.Credential;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {

    private Credential credential;
    FragmentSignupBinding binding;

    public static SignUpFragment getInstance(){
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        binding = FragmentSignupBinding.inflate(inflater, parent, false);
        credential = new Credential();
        binding.setCredential(credential);
        binding.setSignUpFragment(this);
        return binding.getRoot();
    }

    public void submit(){
        Toast.makeText(getContext(), "Credential is "+credential.getPassword(), Toast.LENGTH_SHORT).show();
    }

    //TODO
    public boolean validate(){
        return true;
        //Write method to validate the method
    }
}
