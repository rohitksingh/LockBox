package com.rohitksingh.lockbox.viewmodels;

import android.app.Application;
import android.content.Context;

import com.rohitksingh.lockbox.models.Credential;
import com.rohitksingh.lockbox.repositories.CredentialRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CredentialViewModel extends ViewModel {

    public MutableLiveData<Credential> credentialLiveData = new MutableLiveData<>();
    private CredentialRepository repository;

    public LiveData<Credential> getCredential(Context context){
        loadCredential(context);
        return credentialLiveData;
    }

    public void loadCredential(Context context){
        repository = CredentialRepository.getInstance(context);
        Credential credential = repository.getCredential();
        credentialLiveData.postValue(credential);
    }

    public void savePassword(Credential credential){
        repository.saveCredential(credential);
        credentialLiveData.postValue(credential);
    }

}
