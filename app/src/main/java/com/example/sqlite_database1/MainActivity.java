package com.example.sqlite_database1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2,searchET;
    Button button;
    Database_Helper database_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText1 = findViewById(R.id.nameET);
        searchET = findViewById(R.id.searchET);
        editText2 = findViewById(R.id.ageET);
        button = findViewById(R.id.insertBTN);

        database_helper = new Database_Helper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText1.getText().toString();
                String age = editText2.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if (age.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();
                }

                else {

                   long id = database_helper.insertData(name,age);
                    Toast.makeText(MainActivity.this, "Data Insert & ID is ="+id, Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    public void showData(View view) {

        startActivity(new Intent(MainActivity.this,showDataActivity.class));

    }

    public void searchBTN(View view) {

        String ID = searchET.getText().toString();

        if (ID.isEmpty()){
            Toast.makeText(MainActivity.this,"Enter Your ID",Toast.LENGTH_SHORT).show();


        }
        else
        {
            Cursor cursor = database_helper.searchData(Integer.parseInt(ID));

            while (cursor.moveToNext()){

                String name = cursor.getString(cursor.getColumnIndexOrThrow(database_helper.Col_Name));
                String age = cursor.getString(cursor.getColumnIndexOrThrow(database_helper.Col_Age));


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Result For Your ID :"+ID);
                builder.setMessage("Name : "+name+ "\nAge :"+age);
                builder.setCancelable(false);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //Toast.makeText(this, "Name:"+name+ " Age :"+age, Toast.LENGTH_SHORT).show();
        }



        }


    }

    public void updateDataBTN(View view) {

        startActivity(new Intent(MainActivity.this,New_Update_Data.class));

    }
}