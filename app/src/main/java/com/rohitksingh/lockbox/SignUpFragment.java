package com.rohitksingh.lockbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {


    public static SignUpFragment getInstance(){
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_signup, parent, false);
        return view;

    }
}
