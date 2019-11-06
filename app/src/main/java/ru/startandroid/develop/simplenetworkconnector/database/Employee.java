package ru.startandroid.develop.simplenetworkconnector.database;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String login, password;
    private String full_name, position;

    public String getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
