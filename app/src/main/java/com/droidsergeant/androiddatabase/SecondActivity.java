package com.droidsergeant.androiddatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    DatabaseHelper helper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        helper = new DatabaseHelper(SecondActivity.this);
        helper.open();
        cursor = helper.getData("user_detail");
        cursor.moveToFirst();

        ListView view = (ListView) findViewById(R.id.name_list);
        ListAdapter listAdapter = new ListAdapter(cursor);
        view.setAdapter(listAdapter);

    }

    public class ListAdapter extends BaseAdapter {

        Cursor cursor;

        public ListAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            cursor.moveToPosition(position);
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_row, null);
            TextView user_name = (TextView) view.findViewById(R.id.name);
            user_name.setText(cursor.getString(cursor.getColumnIndex("user_name")));
            return view;
        }
    }
}
