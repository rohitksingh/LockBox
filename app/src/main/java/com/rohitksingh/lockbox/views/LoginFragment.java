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
import com.rohitksingh.lockbox.viewmodels.LoginFragmentViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class LoginFragment extends Fragment {

    private Credential credential;
    private static final String PASSWORD = "LoginFragment.PASSWORD";
    private FragmentLoginBinding binding;
    private LoginFragmentViewModel viewModel;

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
        viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLoginFragment(this);
        binding.setLifecycleOwner(this);
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

        viewModel.increaseAttempt();
    }




}
