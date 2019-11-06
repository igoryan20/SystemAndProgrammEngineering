package ru.startandroid.develop.simplenetworkconnector.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.startandroid.develop.simplenetworkconnector.WaitersMainActivity;
import ru.startandroid.develop.simplenetworkconnector.database.Employee;
import ru.startandroid.develop.simplenetworkconnector.database.RestaurantDatabase;

public class CheckLoginRemoteDB extends AsyncTask<String, Integer, String> {

    String login, password;
    Context context;
    AlertDialog.Builder builder;
    TextView tvError;

    public CheckLoginRemoteDB(Context context, String login, String password, TextView tvError) {
        this.login = login;
        this.password = password;
        this.context = context;
        this.tvError = tvError;
    }

    protected String doInBackground(String... strings) {

        try {

            URL url = new URL("http://192.168.0.101:8080/cgi-bin/isLoginCorrect.py?login=" + login + "&password=" + password);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String result = br.lines().collect(Collectors.joining("\n"));

            urlConnection.disconnect();

            return result;


        } catch (Exception e) {
            Log.d("TAG", e.getMessage() + "   oj     " + e.toString());

        }
        return "Fall";
    }

    @Override
    protected void onPostExecute(final String result) {
        Log.d("TAG", "onPostExecute");
        if (result.equals("False")) {
            tvError.setVisibility(View.VISIBLE);
        } else if (result.equals("Fall")) {
            builder = new AlertDialog.Builder(context);
            builder.setTitle("Ошибка")
                    .setMessage("Потеряна связь с сервером!")
                    .setCancelable(false)
                    .setNegativeButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else if(result == null){
                tvError.setText("Введите логин и пароль");
                tvError.setVisibility(View.VISIBLE);
        } else{
            tvError.setVisibility(View.GONE);
            Log.d("TAG", "else");
            Intent intent = new Intent(context, WaitersMainActivity.class);
            context.startActivity(intent);
            RestaurantDatabase db = Room.databaseBuilder(context, RestaurantDatabase.class, "Restaurant_db").build();
            Employee employee = new Employee();
            String[] results = delimeStr(result);
            employee.setLogin(results[1]);
            employee.setPassword(results[2]);
            employee.setFull_name(results[3]);
            employee.setPosition(results[4]);
            ;
            //Employee employee1 = db.employeeDao().getByLogin(login);
            Log.d("TAG","Sure?");

        }
    }

    String[] delimeStr(String str){
        String delimer = ",";
        String[] substr = str.split(delimer, 5);
        substr[4] = substr[4].replace(")]", "");
        return substr;
    }

}
