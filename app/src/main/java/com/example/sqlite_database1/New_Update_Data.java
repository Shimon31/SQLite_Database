package com.example.sqlite_database1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class New_Update_Data extends AppCompatActivity {

    EditText nameET, ageEt,searchET;
    Database_Helper database_helper;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_update_data);


        nameET = findViewById(R.id.nameET);
        ageEt = findViewById(R.id.ageET);
        searchET = findViewById(R.id.searchET);

        database_helper = new Database_Helper(this);



    }

    public void searchBTN(View view) {

         ID = searchET.getText().toString();

        if (ID.isEmpty()){
            Toast.makeText(this, "Enter Search ID", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Cursor cursor = database_helper.searchData(Integer.parseInt(ID));

            while (cursor.moveToNext()){

                String name = cursor.getString(cursor.getColumnIndexOrThrow(database_helper.Col_Name));
                String age = cursor.getString(cursor.getColumnIndexOrThrow(database_helper.Col_Age));

                nameET.setText(name);
                ageEt.setText(age);




            }


        }




    }

    public void updateBTN(View view) {

        String name = nameET.getText().toString();
        String age = ageEt.getText().toString();

        if (name.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "Please Search First", Toast.LENGTH_SHORT).show();
        }
        else
        {

           boolean check =  database_helper.updateData(Integer.parseInt(ID),name,age);

            if (check){
                Toast.makeText(this, "Updated SuccessFully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }


        }

    }

    public void deleteBTN(View view) {


        String name = nameET.getText().toString();
        String age = ageEt.getText().toString();

        if (name.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "Please Search First", Toast.LENGTH_SHORT).show();
        }
        else {

            int Check = database_helper.deleteData(Integer.parseInt(ID));

            if (Check>0){

                Toast.makeText(this, "Delete Data Successfully", Toast.LENGTH_SHORT).show();
                nameET.setText("");
                ageEt.setText("");

            }
            else
            {
                Toast.makeText(this, "Something Is wrong", Toast.LENGTH_SHORT).show();
            }

        }

    }
}