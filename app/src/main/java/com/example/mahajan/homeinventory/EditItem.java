package com.example.mahajan.homeinventory;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

public class EditItem extends AppCompatActivity {

    private Item item;
    private ArrayList<Bitmap> b = new ArrayList<>(4);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        setItem();
        setTitle("Edit Item: " + item.name);
        EditText name = findViewById(R.id.editName);
        EditText cost = findViewById(R.id.editCost);
        EditText des = findViewById(R.id.editDes);
        Button date = findViewById(R.id.buttonDate);
        ImageView pic = findViewById(R.id.image);

        name.setText(item.name);
        cost.setText(item.toDouble().toString());
        des.setText(item.getDescription());
        String builder = DateFormat.getDateInstance(DateFormat.FULL).format(item.getCal().getTime());
        date.setText(builder);
        setImages(item.getPhotos());

    }
    private void setItem()
    {
       item =  DisplayItems.list.get(getIntent().getIntExtra("position", 0));
    }

    public void onDeleteButton(View v)
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        DisplayItems.list.remove((getIntent().getIntExtra("position", 0)));
                        setResult(100);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete: " + item.name).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    private void setImages(ArrayList<Bitmap> bitmaps)
    {
        for (int i = 0; i < 4; i++)
        {
            b.add(bitmaps.get(i));
        }
        //work on this
    }
}
