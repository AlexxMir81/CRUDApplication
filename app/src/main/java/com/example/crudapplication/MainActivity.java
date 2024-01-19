package com.example.crudapplication;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crudapplication.adapters.MenuItemAdapter;
import com.example.crudapplication.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int ADD_REQUEST_CODE=1;
    ListView listView;
    List<MenuItem> list;
    MenuItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        list = new ArrayList<>();
        list.add(new MenuItem("Пицца грибная", 300, "Грибы, курица, сыр", R.drawable.pizza1));
        list.add(new MenuItem("Пицца деликатесная", 400, "курица, сыр, помидоры", R.drawable.pizza2));
        list.add(new MenuItem("Пицца Пеперони", 350, "колабаса, сыр, помидоры", R.drawable.pizza3));
        list.add(new MenuItem("Суши терияки", 300, "Рис, нури, лосоь", R.drawable.sushi1));
        list.add(new MenuItem("Суши горячие", 300, "Рис, нури, угорь", R.drawable.sushi2));

        itemAdapter = new MenuItemAdapter(this, list, R.layout.menuitem_template);
        listView.setAdapter(itemAdapter);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity(v);
            }
        });

    }
    public void AddActivity(View view) {
        Intent intent = new Intent(this,AddActivity.class);
        startActivityForResult(intent,ADD_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE && resultCode == RESULT_OK && data !=null){
            list.add(createItem(data));
            itemAdapter.notifyDataSetChanged();
        } else if (resultCode == RESULT_OK && data !=null && requestCode == 3){
            int index = data.getIntExtra("position",0);
            list.set(index, createItem(data));
            itemAdapter.notifyDataSetChanged();
        }
    }

    public MenuItem createItem(@Nullable Intent data){
        MenuItem newItem = new MenuItem(
                data.getStringExtra("name"),
                Integer.parseInt(data.getStringExtra("weight")),
                data.getStringExtra("composition"),
                data.getIntExtra("image", 0));
        return newItem;
    }
}