package com.example.crudapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.crudapplication.adapters.MenuItemAdapter;
import com.example.crudapplication.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview);
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem("Пицца грибная", 300, "Грибы, курица, сыр", R.drawable.pizza1));
        list.add(new MenuItem("Пицца деликатесная", 400, "курица, сыр, помидоры", R.drawable.pizza2));
        list.add(new MenuItem("Пицца Пеперони", 350, "колабаса, сыр, помидоры", R.drawable.pizza3));
        list.add(new MenuItem("Суши терияки", 300, "Рис, нури, лосоь", R.drawable.sushi1));
        list.add(new MenuItem("Суши горячие", 300, "Рис, нури, угорь", R.drawable.sushi2));

        MenuItemAdapter itemAdapter = new MenuItemAdapter(this, list, R.layout.menuitem_template);
        listView.setAdapter(itemAdapter);

        Button addButon = findViewById(R.id.add_button);
        addButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity(v);
            }
        });
    }

    public void AddActivity(View view) {
        Intent intent = new Intent(this,AddActivity.class);
        //intent.putExtra("count",count);
        startActivityForResult(intent,1);
    }
}