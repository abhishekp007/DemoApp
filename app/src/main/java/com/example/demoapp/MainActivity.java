package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demoapp.Adapter.DataAdapter;
import com.example.demoapp.DBHelper.DBConnection;
import com.example.demoapp.DBHelper.DataController;
import com.example.demoapp.model.DataModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_addDataScreen;
    ListView lstview;
    private DataController controller;
    private DataModel model;
    DBConnection DBCON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        DBCON = new DBConnection(getApplicationContext());

        btn_addDataScreen = findViewById(R.id.btn_addData);

        try {


            controller = new DataController(getApplicationContext());


            lstview = findViewById(R.id.listview_ShowData);

            DataController controller = new DataController(getApplicationContext());
            DataAdapter adapter = new DataAdapter(getApplicationContext(),R.layout.data_show_design,controller.ListAll());
            lstview.setAdapter(adapter);



        } catch (Exception ex) {
            Toast.makeText(this, ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        btn_addDataScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddNoteScreenActivity.class);
                startActivity(i);
            }
        });
    }


}