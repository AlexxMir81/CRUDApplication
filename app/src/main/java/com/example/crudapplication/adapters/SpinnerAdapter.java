package com.example.crudapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crudapplication.AddActivity;
import com.example.crudapplication.models.MenuItem;

import java.util.List;

public class SpinnerAdapter<Integer> extends ArrayAdapter<Integer> {
    Context context;
    private List<Integer> data;
    private int teplatelayout;
    private LayoutInflater inflater;

    public SpinnerAdapter(@NonNull Context context, int teplatelayout, @NonNull List<Integer> data) {
        super(context, teplatelayout, data);
        this.context = context;
        this.data = data;
        this.teplatelayout = teplatelayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource((int)data.get(position));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        return imageView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource((int)data.get(position));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        return imageView;
    }
}
