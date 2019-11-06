package ru.startandroid.develop.simplenetworkconnector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WaitersMainActivity extends AppCompatActivity {

    ImageButton table2;
    Toolbar waiters_toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiters_main_activity);


        table2 = (ImageButton) findViewById(R.id.img_table2);
        table2.setImageResource(R.drawable.baseline_restaurant_black_48);

        waiters_toolbar = (Toolbar)findViewById(R.id.waiters_toolbar);
        setSupportActionBar(waiters_toolbar);

        table2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaitersMainActivity.this, MenuActivity.class));
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_favorite){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
