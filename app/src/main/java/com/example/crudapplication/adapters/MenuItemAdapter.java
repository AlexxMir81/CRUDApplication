package com.example.crudapplication.adapters;

import static androidx.core.app.ActivityCompat.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.crudapplication.AddActivity;
import com.example.crudapplication.EditActivity;
import com.example.crudapplication.R;
import com.example.crudapplication.models.MenuItem;

import java.util.List;

public class MenuItemAdapter extends BaseAdapter {
    private Context context;
    private List<MenuItem> data;
    private int teplatelayout;
    private LayoutInflater inflater;

    private Activity activity;
    public MenuItemAdapter(Context context, List<MenuItem> data, int teplatelayout) {
        this.context = context;
        this.data = data;
        this.teplatelayout = teplatelayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        activity= (Activity)context;
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
        //View rowView = inflater.inflate(teplatelayout, parent, false);
        ViewHolder holder;
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(teplatelayout, parent, false);
            holder = new ViewHolder();
            holder.delButton = convertView.findViewById(R.id.delete_button);
            holder.editButton = convertView.findViewById(R.id.edit_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        TextView name = convertView.findViewById(R.id.name_text);
        TextView weight = convertView.findViewById((R.id.weight_text));
        TextView composition = convertView.findViewById(R.id.composition_text);
        ImageView menuImage = convertView.findViewById(R.id.img_view);

        MenuItem menuItem = data.get(position);
        name.setText(menuItem.getName());
        weight.setText(String.valueOf(menuItem.getWeight()));
        composition.setText(menuItem.getComposition());
        menuImage.setImageResource(menuItem.getMenuImage());


        convertView.setTag(holder);
        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name",menuItem.getName());
                intent.putExtra("weight",menuItem.getWeight());
                intent.putExtra("image", menuItem.getMenuImage());
                intent.putExtra("composition",menuItem.getComposition());
                activity.startActivityForResult(intent, 3);
            }
        });
        holder.delButton.setTag(position);
        holder.editButton.setTag(position);
        return convertView;
    }
    private static class ViewHolder {
        Button delButton;
        Button editButton;
    }
}
