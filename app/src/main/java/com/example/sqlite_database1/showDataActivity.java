package com.example.sqlite_database1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class showDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<User> userList;
    User_Adapter user_adapter;
    Database_Helper database_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        database_helper = new Database_Helper(this);
        recyclerView= findViewById(R.id.dataRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        user_adapter = new User_Adapter(userList);
        recyclerView.setAdapter(user_adapter);

        Cursor cursor = database_helper.showData();

        while (cursor.moveToNext()){

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(database_helper.Col_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(database_helper.Col_Name));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(database_helper.Col_Age));

            userList.add(new User(ID,name,age));
            user_adapter.notifyDataSetChanged();


        }

    }
}