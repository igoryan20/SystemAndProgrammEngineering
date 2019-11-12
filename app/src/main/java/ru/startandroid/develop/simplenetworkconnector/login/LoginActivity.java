package ru.startandroid.develop.simplenetworkconnector.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import ru.startandroid.develop.simplenetworkconnector.R;
import ru.startandroid.develop.simplenetworkconnector.WaitersMainActivity;
import ru.startandroid.develop.simplenetworkconnector.database.Employee;
import ru.startandroid.develop.simplenetworkconnector.database.MyViewModel;
import ru.startandroid.develop.simplenetworkconnector.database.RestaurantDatabase;

public class LoginActivity extends FragmentActivity {

    CheckLoginLocalDB checkLogin;
    EditText login, password;
    Button button, forDevelopers;
    TextView tv_error;
    RestaurantDatabase db;
    MyViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);
        button = (Button)findViewById(R.id.button);
        tv_error = (TextView)findViewById(R.id.tv_error);
        model = ViewModelProviders.of(this).get(MyViewModel.class);
        forDevelopers = (Button)findViewById(R.id.for_developers);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Обрабатываю кнопку "Вход"
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    checkLogin = new CheckLoginLocalDB(LoginActivity.this, login.getText().toString(), password.getText().toString(), tv_error);
                    checkLogin.execute();

            }
        });

        //Обрабатываю кнопку "For developers"
        forDevelopers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextToWaiters();
            }
        });
    }


    public void nextToWaiters(){
        this.startActivity(new Intent(this, WaitersMainActivity.class));
    }
}