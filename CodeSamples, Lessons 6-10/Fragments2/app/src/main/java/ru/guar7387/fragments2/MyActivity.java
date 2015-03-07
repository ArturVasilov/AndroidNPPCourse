package ru.guar7387.fragments2;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;


public class MyActivity extends Activity implements CallbackFragment, TimeDialog.OnTimePickedListener {

    public static final int DESTROY_CODE = 20;

    public static final int SHOW_TIME_DIALOG_CODE = 50;

    private Fragment fragment1;
    private Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment1, fragment1);
        fragmentTransaction.add(R.id.fragment2, fragment2);
        fragmentTransaction.commit();
        //testArrayListWrapper();
    }

    @Override
    public void onAction(int code) {
        switch (code) {
            case DESTROY_CODE:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.remove(fragment1);
                fragmentTransaction.remove(fragment2);
                fragmentTransaction.commit();
                break;

            case SHOW_TIME_DIALOG_CODE:
                DialogFragment timeDialog = new TimeDialog();
                timeDialog.show(getFragmentManager(), "");
                break;
        }
    }

    @Override
    public void onTimePicked(Calendar time) {
        Toast.makeText(getApplicationContext(), "Chosen time - " + time.toString(), Toast.LENGTH_LONG).show();
    }

    public static class Fragment1 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment1, container, false);
            root.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CallbackFragment) getActivity()).onAction(DESTROY_CODE);
                }
            });
            root.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CallbackFragment) getActivity()).onAction(SHOW_TIME_DIALOG_CODE);
                }
            });
            return root;
        }
    }

    public static class Fragment2 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment2, container, false);
        }
    }

    private void testArrayListWrapper() {
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayListWrapper<Integer> list = new ArrayListWrapper<Integer>();
                list.add(5);
                list.add(10);
                list.add(12);
                list.add(15);
                list.remove(2);
            }
        });
    }


}
