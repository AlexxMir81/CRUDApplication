package com.example.crudapplication.adapters;

import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crudapplication.R;
import com.example.crudapplication.models.MenuItem;

import java.util.List;

public class MenuItemAdapter extends BaseAdapter {
    private Context context;
    private List<MenuItem> data;
    private int teplatelayout;
    private LayoutInflater inflater;

    public MenuItemAdapter(Context context, List<MenuItem> data, int teplatelayout) {
        this.context = context;
        this.data = data;
        this.teplatelayout = teplatelayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(teplatelayout, parent, false);

        TextView name = rowView.findViewById(R.id.name_text);
        TextView weight = rowView.findViewById((R.id.weight_text));
        TextView composition = rowView.findViewById(R.id.composition_text);
        ImageView menuImage = rowView.findViewById(R.id.img_view);

        MenuItem menuItem = data.get(position);
        name.setText(menuItem.getName());
        weight.setText(String.valueOf(menuItem.getWeight()));
        composition.setText(menuItem.getComposition());
        menuImage.setImageResource(menuItem.getMenuImage());
        return rowView;
    }
}
