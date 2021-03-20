package com.rohitksingh.lockbox.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.rohitksingh.lockbox.models.Credential;


public class CredentialRepository {

    private static CredentialRepository repository;
    private static SharedPreferences credentialSharedPreference;
    private Credential credential;
    private static final String PASSWORD = "CredentialRepository.PASSWORD";
    private static final String CREDENTIAL_PREFERENCES = "CredentialRepository.CREDENTIAL_PREFERENCES";

    private CredentialRepository(){}

    public static CredentialRepository getInstance(Context context){
        if(repository==null){
            repository = new CredentialRepository();
            credentialSharedPreference = context.getSharedPreferences(CREDENTIAL_PREFERENCES, Context.MODE_PRIVATE);
        }
        return repository;
    }


    public Credential getCredential(){
        String password = credentialSharedPreference.getString(PASSWORD, "");
        credential = new Credential();
        credential.setPassword(password);
        return credential;
    }

    public void saveCredential(Credential credential){
        SharedPreferences.Editor editor = credentialSharedPreference.edit();
        editor.putString(PASSWORD, credential.getPassword()).commit();
    }

    public void updateCredential(Credential credential){
        saveCredential(credential);
    }
}
