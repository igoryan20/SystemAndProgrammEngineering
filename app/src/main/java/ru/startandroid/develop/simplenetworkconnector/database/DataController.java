package ru.startandroid.develop.simplenetworkconnector.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


@Deprecated
public class DataController  {

    private MutableLiveData<String> liveData = new MutableLiveData<>();

    LiveData<String> getData(){
        return liveData;
    }

}
