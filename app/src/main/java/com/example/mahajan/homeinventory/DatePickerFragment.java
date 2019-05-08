package com.example.mahajan.homeinventory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * Created by Mahajan on 7/2/2018.
 */

public class DatePickerFragment extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog d = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Dialog,(DatePickerDialog.OnDateSetListener)getActivity(), year, month, day);
        return d;
    }
}
