package ru.guar7387.todosample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ru.guar7387.todosample.fab.FabGenerator;
import ru.guar7387.todosample.fab.FloatingActionButton;

public class TodoCreationFragment extends Fragment implements View.OnClickListener {

    private EditText text;

    private EditText dateEdit;

    private EditText timeEdit;

    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_todo, container, false);

        init(root);

        calendar = Calendar.getInstance();

        return root;
    }

    private void init(View root) {
        text = (EditText) root.findViewById(R.id.textTodo);
        dateEdit = (EditText) root.findViewById(R.id.date);
        timeEdit = (EditText) root.findViewById(R.id.time);

        dateEdit.setOnClickListener(this);
        timeEdit.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
        FabGenerator.createFab(getActivity().getApplicationContext(), fab, R.drawable.fab_ok, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date:
                DateFragmentDialog dateFragmentDialog = new DateFragmentDialog();
                dateFragmentDialog.setDatePickedListener(new DateFragmentDialog.OnDatePickedListener() {
                    @Override
                    public void onDatePicked(Calendar date) {
                        calendar.set(Calendar.YEAR, date.get(Calendar.YEAR));
                        calendar.set(Calendar.MONTH, date.get(Calendar.MONTH));
                        calendar.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
                        dateEdit.setText(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
                    }
                });
                dateFragmentDialog.show(getFragmentManager(), "");
                break;

            case R.id.time:
                TimeFragmentDialog timeFragmentDialog = new TimeFragmentDialog();
                timeFragmentDialog.setTimePickedListener(new TimeFragmentDialog.OnTimePickedListener() {
                    @Override
                    public void onTimePicked(Calendar time) {
                        calendar.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
                        calendar.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
                        timeEdit.setText(new SimpleDateFormat("hh:mm a").format(calendar.getTime()));
                    }
                });
                timeFragmentDialog.show(getFragmentManager(), "");
                break;

            case R.id.fab:
                if (text.getText() == null || text.getText().toString().isEmpty()) {
                    text.setBackgroundColor(Color.RED);
                    return;
                }
                else {
                    text.setBackgroundColor(Color.TRANSPARENT);
                }
                if (dateEdit.getText() == null || dateEdit.getText().toString().isEmpty()) {
                    dateEdit.setBackgroundColor(Color.RED);
                    return;
                }
                else {
                    dateEdit.setBackgroundColor(Color.TRANSPARENT);
                }
                if (timeEdit.getText() == null || timeEdit.getText().toString().isEmpty()) {
                    timeEdit.setBackgroundColor(Color.RED);
                    return;
                }
                else {
                    timeEdit.setBackgroundColor(Color.TRANSPARENT);
                }
                ((OnTodoCreated) getActivity()).onTodoItemCreated(new TodoItem(text.getText().toString(), calendar.getTimeInMillis()));
                break;
        }
    }

    public static class DateFragmentDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public static interface OnDatePickedListener {
            public void onDatePicked(Calendar date);
        }

        private OnDatePickedListener mListener;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void setDatePickedListener(OnDatePickedListener listener) {
            this.mListener = listener;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);

            mListener.onDatePicked(c);
        }
    }

    public static class TimeFragmentDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        public static interface OnTimePickedListener {
            public void onTimePicked(Calendar time);
        }

        private OnTimePickedListener mListener;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, !DateFormat.is24HourFormat(getActivity().getApplicationContext()));
        }

        public void setTimePickedListener(OnTimePickedListener listener) {
            this.mListener = listener;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);

            mListener.onTimePicked(c);
        }
    }
}
