package com.example.simpleparadox.listycity;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private Solo solo_MainActivity, solo_secondActivity;
    @Rule
    public ActivityTestRule<MainActivity> rule_MainActivity =
            new ActivityTestRule<>(MainActivity.class, true, true);
    public ActivityTestRule<secondActivity> rule_secondActivity =
            new ActivityTestRule<>(secondActivity.class, true, true);



    @Before
    public void setUp() throws Exception {
        solo_MainActivity = new Solo(InstrumentationRegistry.getInstrumentation(), rule_MainActivity.getActivity());
        solo_secondActivity = new Solo(InstrumentationRegistry.getInstrumentation(), rule_secondActivity.getActivity());
    }



    @Test
    public void start() throws Exception {
        Activity activity_MainActivity = rule_MainActivity.getActivity();
        Activity activity_secondActivity = rule_secondActivity.getActivity();
    }


    @Test
    public void testing() {
        solo_MainActivity.assertCurrentActivity("this is not MainActivity", MainActivity.class);

        solo_MainActivity.clickOnButton("ADD CITY");
        solo_MainActivity.enterText((EditText) solo_MainActivity.getView(R.id.editText_name), "1707109");
        solo_MainActivity.clickOnButton("CONFIRM");
        solo_MainActivity.waitForText("Edmonton", 1, 2000);

        solo_MainActivity.clickOnButton("ADD CITY");
        solo_MainActivity.enterText((EditText) solo_MainActivity.getView(R.id.editText_name), "Sabbir");
        solo_MainActivity.clickOnButton("CONFIRM");
        solo_MainActivity.waitForText("Edmonton", 1, 2000);


        MainActivity activity = (MainActivity) solo_MainActivity.getCurrentActivity();
        final ListView cityList = activity.cityList;
        String city = (String) cityList.getItemAtPosition(0);
        assertEquals("1707109", city);


        solo_MainActivity.clickInList(0);


        solo_secondActivity.assertCurrentActivity("this is not secondActivity", secondActivity.class);

        solo_secondActivity.clickOnButton("BACK");

        solo_MainActivity.assertCurrentActivity("this is not MainActivity", MainActivity.class);

        solo_MainActivity.clickOnButton("ClEAR ALL");
    }

    /**
     * Close activity after each test
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        solo_MainActivity.finishOpenedActivities();
    }
}