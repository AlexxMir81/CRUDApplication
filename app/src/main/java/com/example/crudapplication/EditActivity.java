package com.example.crudapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crudapplication.adapters.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private EditText name;
    private EditText weight;
    private EditText composition;
    private List<Integer> data;
    private int dbId;
    Spinner spinner;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = findViewById(R.id.name_input);
        weight = findViewById(R.id.weight_input);
        composition = findViewById(R.id.comp_input);
        data = new ArrayList<>();
        data.add(R.drawable.pizza1);
        data.add(R.drawable.pizza2);
        data.add(R.drawable.pizza3);
        data.add(R.drawable.sushi1);
        data.add(R.drawable.sushi2);
        spinner = findViewById(R.id.image_select);

        Intent intent = getIntent();
        dbId = intent.getIntExtra("dbId", 0);
        name.setText(intent.getStringExtra("name"));
        weight.setText(String.valueOf(intent.getIntExtra("weight",0)));
        composition.setText(intent.getStringExtra("composition"));

        position = intent.getIntExtra("position",0);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter<>(EditActivity.this, android.R.layout.simple_spinner_item, data);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(data.indexOf(intent.getIntExtra("image",0)));

        Button cancelButton = findViewById(R.id.cancel_button);
        Button saveButton = findViewById(R.id.save_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackMainActivity(v);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEditActivity(v);
            }
        });
    }
    public void BackMainActivity(View view) {
        Intent intent = new Intent();
        // intent.putExtra("count",count);
        setResult(RESULT_CANCELED,intent);
        finish();
    }

public void SaveEditActivity(View view) {
    if(name.getText().toString().isEmpty()||weight.getText().toString().isEmpty()||composition.getText().toString().isEmpty()){
        Toast.makeText(EditActivity.this, "Введите все значения!", Toast.LENGTH_LONG).show();
    }else{
        Intent intent = new Intent();
        intent.putExtra("dbId", dbId);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("weight", weight.getText().toString());
        intent.putExtra("composition", composition.getText().toString());
        intent.putExtra("image", data.get(spinner.getSelectedItemPosition()));
        intent.putExtra("position", position);
        setResult(RESULT_OK,intent);
        finish();
    }
}
}