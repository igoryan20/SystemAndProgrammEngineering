package ru.startandroid.develop.simplenetworkconnector.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.security.auth.callback.Callback;

public class MyViewModel extends ViewModel {
    MutableLiveData<String> data;

    public LiveData<String> getData(){
        if(data == null){
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }

    private void loadData(){

    }
}
