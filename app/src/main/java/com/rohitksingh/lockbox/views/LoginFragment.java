package com.rohitksingh.lockbox.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitksingh.lockbox.R;
import com.rohitksingh.lockbox.databinding.FragmentLoginBinding;
import com.rohitksingh.lockbox.models.Credential;
import com.rohitksingh.lockbox.viewmodels.LoginFragmentViewModel;
import com.rohitksingh.lockbox.views.activities.SecretListActivity;

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

    /***********************************************************************************************
     *                                  Lifecycle methods
     **********************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        credential = (Credential) getArguments().getSerializable(PASSWORD);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);

        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getActivity().getApplication());

        viewModel = new ViewModelProvider(this, factory).get(LoginFragmentViewModel.class);


        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        observeViewModel();
        return binding.getRoot();
    }

    /***********************************************************************************************
     *                                  Private methods
     **********************************************************************************************/
    private void observeViewModel(){
        viewModel.valiadationPassed.observe(this, validationPassed -> {
            if (validationPassed) {
                startSecretListActivity();
            } else {
                showValidationError();
            }
        });

        viewModel.showSubmitButton.observe(this, showtimer -> disableForm(showtimer));
    }

    private void startSecretListActivity(){
        Intent intent = new Intent(getActivity(), SecretListActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void showValidationError(){
        binding.password.setError(getString(R.string.incorrect_password));
    }

    private void disableForm(boolean enable){
        if(enable){
            binding.submitButton.setVisibility(View.VISIBLE);
        }else {
            binding.submitButton.setVisibility(View.GONE);
        }
    }

}
