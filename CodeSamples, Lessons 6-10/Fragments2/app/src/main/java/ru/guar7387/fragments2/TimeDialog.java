package ru.guar7387.fragments2;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimeDialog extends DialogFragment
        //custom callback
        implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "TimeDialog";

    /**
     * our callback for connection between fragment and activity
     */
    public static interface OnTimePickedListener {
        public void onTimePicked(Calendar time);
    }

    private OnTimePickedListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //calls when the activity create and put fragment somewhere with fragment manager
        try {
            mListener = (OnTimePickedListener) activity;
        } catch (ClassCastException e) {
            Logger.i(TAG, "Activity is not instance of DatePickedListener");
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        mListener.onTimePicked(c);
    }
}
