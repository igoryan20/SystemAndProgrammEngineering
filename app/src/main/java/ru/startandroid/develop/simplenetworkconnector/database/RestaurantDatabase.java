package ru.startandroid.develop.simplenetworkconnector.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 1)
public abstract class RestaurantDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

}
