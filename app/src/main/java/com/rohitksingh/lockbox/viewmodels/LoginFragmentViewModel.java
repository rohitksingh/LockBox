package com.rohitksingh.lockbox.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginFragmentViewModel extends ViewModel {

    private static final String TAG = "LoginFragmentViewModel";

    public int numberOfAttemptes = 0;

    private final int thrasoldAttempts = 2;

    public MutableLiveData<Boolean> showTimer;
    public MutableLiveData<Integer> timerValue;

    public LoginFragmentViewModel(){
        showTimer = new MutableLiveData<>();
        timerValue = new MutableLiveData<>();
        showTimer.setValue(false);
        timerValue.setValue(10);
    }


    public void increaseAttempt(){
        numberOfAttemptes++;
        if(numberOfAttemptes>=thrasoldAttempts){
            showTimer.setValue(true);
            startTimer();
        }
        Log.d(TAG, "increaseAttempt: "+numberOfAttemptes);
    }

    public void startTimer(){
        Log.d(TAG, "timerstart: ");
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
                    Log.d(TAG, "timerstart: "+ timerValue.getValue()+" "+showTimer.getValue());
                }
                showTimer.postValue(false);
                timerValue.postValue(10);
            }
        }).start();
    }

}
