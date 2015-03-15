package ru.guar7387.practicelesson2;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testRestart() throws Exception {
        solo.finishOpenedActivities();
        setActivity(null);
        solo = new Solo(getInstrumentation(), getActivity());
        solo.assertCurrentActivity("error", MainActivity.class);
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}


