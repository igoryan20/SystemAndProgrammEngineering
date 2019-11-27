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

    ImageButton table1, table2, table3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiters_main_activity);


        table1 = (ImageButton) findViewById(R.id.img_table1);
        table2 = (ImageButton) findViewById(R.id.img_table2);
        table3 = (ImageButton) findViewById(R.id.img_table3);



        table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaitersMainActivity.this, SearchFood.class));
            }
        });

        table2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaitersMainActivity.this, AdminFood.class));
            }
        });

        table3.setOnClickListener(new View.OnClickListener() {
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
