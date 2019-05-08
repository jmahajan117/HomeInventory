package com.example.mahajan.homeinventory;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class New_Item extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap b1, b2, b3, b4;
    private int track;
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__item);
        setTitle("Add Item");
        Button b = findViewById(R.id.SetDate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");


            }
        });
    }

    public void onButtonClick(View v) {
        EditText name = findViewById(R.id.EditName);
        EditText cost = findViewById(R.id.EditCost);
        EditText description = findViewById(R.id.EditDescription);
        String product = name.getText().toString();
        String d = description.getText().toString();
        boolean passed = true;
        double price = 0;
        try {
            price = Double.parseDouble(cost.getText().toString());
        } catch (Exception p) {
            passed = false;
            sendInvalidAlert("Cost (in USD)");
        }
        if (product.equals(null))
        {
            passed = false;
            sendInvalidAlert("Name");
        }
        if (b1 == null)
        {
            passed = false;
            sendInvalidAlert("No image added");
        }
        if (passed)
        {
            ArrayList <Bitmap> b = new ArrayList<>();
            b.add(b1);
            b.add(b2);
            b.add(b3);
            b.add(b4);
            Item i = new Item(product, price, b, calendar, d);
            DisplayItems.list.add(i);
            setResult(100);
            finish();
        }
    }

    public void onClickPic(View v) {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        track = 1;
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void onClickPic2(View v) {
        if (b1 == null)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please select the upper right photo box before adding additional images!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return;
        }
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        track = 2;
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void onClickPic3(View v) {
        if (b1 == null)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please select the upper right photo box before adding additional images!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return;
        }
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        track = 3;
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void onClickPic4(View v) {
        if (b1 == null)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please select the upper right photo box before adding additional images!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return;
        }
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        track = 4;
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                ImageView imageView;
                switch (track)
                {
                    case 1:
                        imageView = findViewById(R.id.imageViewMain);
                        imageView.setImageBitmap(bitmap);
                        b1 = bitmap;
                        break;
                    case 2:
                        imageView = findViewById(R.id.imageViewSec);
                        imageView.setImageBitmap(bitmap);
                        b2 = bitmap;
                        break;
                    case 3:
                        imageView = findViewById(R.id.imageViewThird);
                        imageView.setImageBitmap(bitmap);
                        b3 = bitmap;
                        break;
                    case 4:
                        imageView = findViewById(R.id.imageViewFour);
                        imageView.setImageBitmap(bitmap);
                        b4 = bitmap;
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendInvalidAlert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Invalid input/No Input for: " + message + "!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfmonth);
        calendar = c;
        String builder = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Button b = findViewById(R.id.SetDate);
        b.setText(builder);
    }

}
