package ru.startandroid.develop.simplenetworkconnector;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MenuActivity extends AppCompatActivity {

    private ListView l1, l2;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Log.d("TAG", "MeA | onCreate");

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


}
