package com.example.mahajan.homeinventory;

import android.content.Intent;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayItems extends AppCompatActivity {

    public static ArrayList<Item> list = new ArrayList<>();
    private ListView lstMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);
        lstMain= findViewById(R.id.lstMain);
        for (int i = 0; i < list.size(); i++)
        {
            //get SQL code
        }
        ItemsAdapter adapter = new ItemsAdapter(this, list);
        lstMain.setAdapter(adapter);
        lstMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), EditItem.class);
                intent.putExtra("position", i);
                startActivityForResult(intent, 100);

            }
        });

    }
    public void onAddButtonClick(View v)
    {
        Intent i = new Intent(this, New_Item.class);
        startActivityForResult(i, 100);
        return;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode == 100)
        {
            recreateActivity();
        }
    }
    public void onAddCat(View v)
    {

    }
    private void recreateActivity() {
        //Delaying activity recreate by 1 millisecond. If the recreate is not delayed and is done
        // immediately in onResume() you will get RuntimeException: Performing pause of activity that is not resumed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recreate();
            }
        }, 1);
    }

}
