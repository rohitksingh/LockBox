package com.rohitksingh.lockbox.views;

import android.os.Bundle;

import com.rohitksingh.lockbox.R;
import com.rohitksingh.lockbox.databinding.ActivitySecretListBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class SecretListActivity extends AppCompatActivity {

    ActivitySecretListBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_secret_list);
    }
}
