package com.rohitksingh.lockbox.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitksingh.lockbox.R;
import com.rohitksingh.lockbox.databinding.FragmentSignupBinding;
import com.rohitksingh.lockbox.listener.SignupListener;
import com.rohitksingh.lockbox.models.Credential;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {

    private Credential credential;
    private FragmentSignupBinding binding;
    private SignupListener listener;

    public static SignUpFragment getInstance(){
        return new SignUpFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (SignupListener)context;
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
        }else{
            Credential credential = new Credential();
            credential.setPassword(password);
            listener.submit(credential);
        }

    }

}
