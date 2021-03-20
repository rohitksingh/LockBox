package com.rohitksingh.lockbox.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginFragmentViewModel extends ViewModel {

    public int numberOfAttemptes = 0;

    private int thrasoldAttempts = 2;

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
    }

    public void startTimer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (timerValue.getValue()!=0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int value = timerValue.getValue();
                    timerValue.postValue(value--);
                }
                showTimer.postValue(false);
                timerValue.setValue(10);
            }
        }).start();
    }

}
