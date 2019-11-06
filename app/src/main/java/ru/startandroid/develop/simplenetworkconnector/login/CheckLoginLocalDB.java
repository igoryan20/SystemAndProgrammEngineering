package ru.startandroid.develop.simplenetworkconnector.login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.room.Room;

import java.util.List;

import ru.startandroid.develop.simplenetworkconnector.database.Employee;
import ru.startandroid.develop.simplenetworkconnector.database.RestaurantDatabase;

public class CheckLoginLocalDB extends AsyncTask<String, Integer, String> {

    Context context;
    String login, password;
    TextView tvError;
    CheckLoginLocalDB(Context context, String login, String password, TextView tvError){
        this.context = context;
        this.login = login;
        this.password = password;
        this.tvError = tvError;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... strings) {
        RestaurantDatabase db = Room.databaseBuilder(context.getApplicationContext(), RestaurantDatabase.class, "Restaurant_db").build();
        List<Employee> employees = db.employeeDao().getAll();
        if(employees.isEmpty()){
            Log.d("TAG", "he's here");
            return null;

        } else {
            Log.d("TAG", "He's there");
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (s == null){
            CheckLoginRemoteDB remoteDB = new CheckLoginRemoteDB(context, login, password, tvError);
            remoteDB.execute();

        }
    }
}
