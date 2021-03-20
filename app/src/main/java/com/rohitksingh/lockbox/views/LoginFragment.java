package com.rohitksingh.lockbox.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitksingh.lockbox.R;
import com.rohitksingh.lockbox.databinding.FragmentLoginBinding;
import com.rohitksingh.lockbox.models.Credential;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    private Credential credential;
    private static final String PASSWORD = "LoginFragment.PASSWORD";
    private FragmentLoginBinding binding;

    private static final String TAG = "LoginFragment";

    public static LoginFragment getInstance(Credential credential){
        Bundle args = new Bundle();
        args.putSerializable(PASSWORD, credential);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(args);
        return loginFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        credential = (Credential) getArguments().getSerializable(PASSWORD);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setLoginFragment(this);
        return binding.getRoot();
    }

    public void submit(){

        if(credential.getPassword().equals(binding.password.getText().toString())){
            Intent intent = new Intent(getActivity(), SecretListActivity.class);
            startActivity(intent);
            getActivity().finish();
        }else{
            binding.password.setError(getString(R.string.incorrect_password));
        }
    }



}
