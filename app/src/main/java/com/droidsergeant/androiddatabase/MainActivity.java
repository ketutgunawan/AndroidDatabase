package com.droidsergeant.androiddatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;
    EditText user_name;
    Button save_button, view_list;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = (EditText) findViewById(R.id.user_name);
        view_list = (Button) findViewById(R.id.see_list);
        view_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        save_button = (Button) findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user_name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                } else {
                    values = new ContentValues();
                    values.put("user_name", user_name.getText().toString());
                    helper = new DatabaseHelper(getApplicationContext());
                    helper.open();
                    helper.insertData("user_detail", values);
                    helper.close();
                }
            }
        });
    }
}
