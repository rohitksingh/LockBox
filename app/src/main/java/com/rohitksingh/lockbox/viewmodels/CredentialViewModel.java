package com.rohitksingh.lockbox.viewmodels;

import android.app.Application;
import android.content.Context;

import com.rohitksingh.lockbox.models.Credential;
import com.rohitksingh.lockbox.repositories.CredentialRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CredentialViewModel extends AndroidViewModel {

    public MutableLiveData<Credential> credentialLiveData = new MutableLiveData<>();
    private CredentialRepository repository;
    private Application application;

    public CredentialViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<Credential> getCredential(){
        loadCredential();
        return credentialLiveData;
    }

    public void loadCredential(){
        repository = CredentialRepository.getInstance(application.getApplicationContext());
        Credential credential = repository.getCredential();
        credentialLiveData.postValue(credential);
    }

    public void savePassword(Credential credential){
        repository.saveCredential(credential);
        credentialLiveData.postValue(credential);
    }

}
