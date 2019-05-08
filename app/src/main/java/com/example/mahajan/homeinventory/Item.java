package com.example.mahajan.homeinventory;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import java.util.*;

public class Item extends AppCompatActivity{
    public String name;
    private double cost;
    private ArrayList<Bitmap> bitmaps = new ArrayList<>(4);
    private Calendar calendar;
    private String description;

    public Item(String n, double c, ArrayList <Bitmap> b ,Calendar cn, String des)
    {
        name = n;
        cost = c;
        bitmaps = b;
        calendar = cn;
        description = des;
    }
    public double getCost()
    {
        return cost;
    }
    public ArrayList <Bitmap> getPhotos()
    {
        return bitmaps;
    }
    public Bitmap getIcon()
    {
        return bitmaps.get(0);
    }

    public String getDescription()
    {
        return description;
    }

    public Double toDouble()
    {
        Double d = cost;
        return d;
    }

    public Calendar getCal()
    {
        return calendar;
    }
}
