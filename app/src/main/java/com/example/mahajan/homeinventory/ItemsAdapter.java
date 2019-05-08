package com.example.mahajan.homeinventory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mahajan on 7/5/2018.
 */

public class ItemsAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> list;
    Context con;


    public ItemsAdapter(Context context, ArrayList<Item> items)
    {
        super(context, 0, items);
        list = items;
        con = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.listitem, null, true);
        TextView txt = row.findViewById(R.id.txtItem);
        ImageView img = row.findViewById(R.id.imgItem);

        txt.setText(list.get(position).name);

        img.setImageBitmap(list.get(position).getIcon());

        return row;
    }
}
