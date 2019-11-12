package ru.startandroid.develop.simplenetworkconnector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper = new DBHelper(this);
    private Button Search;
    private EditText str;
    private ListView L1;
    private ListView L2;
    private String[] arr = new String[0];
    private String[] match = new String[0];
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        Search=(Button)findViewById(R.id.search);
        str=(EditText)findViewById(R.id.searchingText);
        L1=(ListView)findViewById(R.id.list1);
        L2=(ListView)findViewById(R.id.list2);


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("food", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("ID");
            int nameColIndex = c.getColumnIndex("NAME");
            int priceColIndex = c.getColumnIndex("PRICE");

            do {
                // получаем значения по номерам столбцов
                String str__ = c.getString(nameColIndex);
                arr = Arrays.copyOf(arr, arr.length + 1);
                arr[arr.length - 1] = str__;
                Log.d(">>>", arr[arr.length - 1]);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }
        c.close();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        addListenerOnButton();
        ////
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
          return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnButton(){
        Search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = str.getText().toString();
                match = Arrays.copyOf(match, 0);
                for(int i = 0; i < arr.length; i++){
                    if (arr[i].indexOf(s) != -1) {
                        match = Arrays.copyOf(match, match.length + 1);
                        match[match.length - 1]=arr[i];
                        Log.d(">>>",match[match.length - 1]);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, match);
                L1.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                str.setText("");

            }
        });
        L1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = match[position];
                // установка текста элемента TextView
                Log.d(">>",selectedItem);

            }
        });
    }

}

class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dishes.db";
    public static final String TABLE_NAME = "food";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "PRICE";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "food", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table food ("
                + "ID INTEGER primary key autoincrement,"
                + "NAME TEXT,"
                + "PRICE REAL" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

