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

        String password = binding.password.getText().toString();
        String confirmPassword = binding.confirmpassword.getText().toString();

        if(!password.equals(confirmPassword)){
            binding.confirmpassword.setError(getString(R.string.password_not_matched));
        }

    }


}
