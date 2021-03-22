package com.rohitksingh.lockbox.viewmodels;

import android.app.Application;

import com.rohitksingh.lockbox.repositories.CredentialRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginFragmentViewModel extends AndroidViewModel {

    private static final String TAG = "LoginFragmentViewModel";

    public MutableLiveData<Boolean> valiadationPassed;

    public int numberOfAttemptes = 0;

    private final int thrasoldAttempts = 2;

    public MutableLiveData<Boolean> showTimer;
    public MutableLiveData<Integer> timerValue;
    public MutableLiveData<String> password;
    public MutableLiveData<Boolean> showSubmitButton;

    private CredentialRepository repository;

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        showTimer = new MutableLiveData<>();
        timerValue = new MutableLiveData<>();
        valiadationPassed = new MutableLiveData<>();
        showTimer.setValue(false);
        showSubmitButton = new MutableLiveData<>();
        showSubmitButton.setValue(false);
        timerValue.setValue(10);
        repository = CredentialRepository.getInstance(application.getApplicationContext());
        password = new MutableLiveData<>();
    }


    public void increaseAttempt(){
        numberOfAttemptes++;
        if(numberOfAttemptes>=thrasoldAttempts){
            showTimer.setValue(true);
            showSubmitButton.setValue(false);
            startTimer();
        }
    }

    public void startTimer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (timerValue.getValue()>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int value = timerValue.getValue();
                    timerValue.postValue(--value);
                }
                showTimer.postValue(false);
                showSubmitButton.postValue(true);
                timerValue.postValue(10);
            }
        }).start();
    }

    public void submit(){

        String storedPassword = repository.getCredential().getPassword();

        if (storedPassword.equals(password.getValue())) {
            valiadationPassed.setValue(true);
        }else {
            valiadationPassed.setValue(false);
            increaseAttempt();
        }

    }



}
