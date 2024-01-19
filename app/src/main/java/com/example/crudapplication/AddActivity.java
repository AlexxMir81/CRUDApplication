package com.example.crudapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    private List<Integer> data;
    private TextView name;
    private TextView weight;
    private TextView composition;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name =findViewById(R.id.name_input);
        weight = findViewById(R.id.weight_input);
        composition = findViewById(R.id.comp_input);
        data = new ArrayList<>();
        data.add(R.drawable.pizza1);
        data.add(R.drawable.pizza2);
        data.add(R.drawable.pizza3);
        data.add(R.drawable.sushi1);
        data.add(R.drawable.sushi2);
        spinner = findViewById(R.id.image_select);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(AddActivity.this, android.R.layout.simple_spinner_item, data){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                ImageView imageView = new ImageView(AddActivity.this);
                imageView.setImageResource(data.get(position));
                imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
                return imageView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                ImageView imageView = new ImageView(AddActivity.this);
                imageView.setImageResource(data.get(position));
                imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
                return imageView;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackMainActivity(v);
            }
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAddActivity(v);
            }
        });
    }
    public void BackMainActivity(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }
    public void SaveAddActivity(View view) {
       if(name.getText().toString().isEmpty()||weight.getText().toString().isEmpty()||composition.getText().toString().isEmpty()){
           Toast.makeText(AddActivity.this, "Введите все значения!", Toast.LENGTH_LONG).show();
       }else{
           Intent intent = new Intent();
           intent.putExtra("name", name.getText().toString());
           intent.putExtra("weight", weight.getText().toString());
           intent.putExtra("composition", composition.getText().toString());
           intent.putExtra("image", data.get(spinner.getSelectedItemPosition()));
           setResult(RESULT_OK,intent);
           finish();
       }
    }
}