package ru.startandroid.develop.simplenetworkconnector;

import android.app.Application;

import androidx.room.Room;

import ru.startandroid.develop.simplenetworkconnector.database.RestaurantDatabase;

@Deprecated
public class App extends Application {
    public static App instance;
    private RestaurantDatabase database;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, RestaurantDatabase.class, "Restaurant_db").build();
    }

    public static App getInstance(){
        return instance;
    }

    public RestaurantDatabase getDatabase(){
        return database;
    }
}
