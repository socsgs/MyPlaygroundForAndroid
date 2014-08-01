package com.socs.myplayground.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.socs.myplayground.ui.MainActivity;

/**
 * Created by Deepak on 31-07-2014.
 */
public class TestClass extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo soloObj;

    public TestClass() {
        super("com.socs.myplayground.ui", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        soloObj = new Solo(getInstrumentation(), getActivity());
    }

    public void testCase() {
        soloObj.assertCurrentActivity("currentActivity", MainActivity.class);
        soloObj.enterText(0,"test1@test.com");
        soloObj.enterText(1,"test123");
        soloObj.clickOnButton(0);
        assertTrue(soloObj.searchText("User not found"));
        soloObj.clickOnButton("OK");
    }
}
