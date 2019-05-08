package com.example.mahajan.homeinventory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.sql.*;


public class MainActivity extends AppCompatActivity {


    private ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome!");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        p = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        p.setTitle("Loading");
        p.setMessage("Logging in... Please wait");
        p.setCancelable(false);

    }
    public void onButtonLogin(View v) throws ClassNotFoundException {
        p.show();
        Thread t = new Thread()
        {
            public void run()
            {
                EditText username = findViewById(R.id.EditUser);
                EditText password = findViewById(R.id.editpass);
                String user = "";
                if (username.getText().toString().equals(null) || password.getText().toString().equals(null))
                {
                    sendInvalidAlert();
                    return;
                }
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@jarus.ga:1521:xe", "admin", "admin");
                    Statement statement = conn.createStatement();
                    ResultSet r = statement.executeQuery("SELECT * FROM USERS");
                    while (r.next())
                    {
                        if (username.getText().toString().equals(r.getString("USERNAME")) && password.getText().toString().equals(r.getString("PASSWORD"))) {
                            //Log.wtf("YES", "Connection Success");
                            Intent i = new Intent(MainActivity.this, DisplayItems.class);
                            user = username.getText().toString();
                            i.putExtra("username", username.getText().toString());
                            startActivity(i);
                            conn.close();
                            p.dismiss();
                            break;
                        }
                    }
                    if (!(user.equals(username.getText().toString())))
                    {
                        p.dismiss();
                        sendInvalidAlert();
                        return;
                    }

                }
                catch(Exception e)
                {
                    SQLException s = new SQLException();
                    if (e.equals(s))
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Unable to connected to server");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                    e.printStackTrace();
                }
            }
        };
        t.start();



    }
    private void sendInvalidAlert() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Invalid username/password");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }
    public void onCreateButton(View v)
    {
        Intent i = new Intent(this, CreateNewUser.class);
        startActivity(i);
    }

}
