package ru.startandroid.develop.simplenetworkconnector.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee")
    List<Employee> getAll();

    @Query("SELECT * FROM employee where login = :login and password = :password")
    Employee getByLoginAndPassword(String login, String password);

    @Query("SELECT * FROM employee WHERE login = :login")
    Employee getByLogin(String login);

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

}
