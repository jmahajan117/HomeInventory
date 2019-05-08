package com.example.mahajan.homeinventory;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.sql.*;

import java.util.ArrayList;

public class CreateNewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
        setTitle("Create New User");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void onButtonClick(View v) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        EditText user = findViewById(R.id.editUser);
        EditText pass = findViewById(R.id.editpass);
        EditText confirm = findViewById(R.id.editConfirm);
        EditText email = findViewById(R.id.editEmail);

        ArrayList<EditText> a = new ArrayList<>();

        a.add(user);
        a.add(pass);
        a.add(confirm);
        a.add(email);

        if (!isEmpty(a))
        {
            return;
        }

        if (!(pass.getText().toString().equals(confirm.getText().toString())))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Passwords are not the same!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return;
        }
            if(!(email.getText().toString().contains("@")))
            {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Invalid Email");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return;
        }
        try  {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@jarus.ga:1521:xe", "admin", "admin");
            Statement statement = connection.createStatement();
            ResultSet r = statement.executeQuery("SELECT * FROM USERS");
            while(r.next())
            {
                String result = r.getString("USERNAME");
                if (result.equals(user.getText().toString()))
                {
                    connection.close();
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Username already taken!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }
            }
            statement.executeUpdate("INSERT INTO USERS " + "VALUES ('"
                    + user.getText().toString() + "', '" + pass.getText().toString()
                    +"', '" + email.getText().toString() + "')");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        String SQL = "";
        /*
            while (user.getText().toString().equals(SQL)) //check if username is taken? and email is used
            {
                //insert SQL command to get use
            {
         */

        //SQL command to create new user!
        finish();
    }
    private boolean isEmpty(ArrayList<EditText> e)
    {
        for (int i = 0; i < e.size(); i++)
        {
            String s = e.get(i).getText().toString();
            Log.wtf("C", s);
            if (s.isEmpty())
            {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Missing Fields");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
            }
        }
        return true;
    }

}
