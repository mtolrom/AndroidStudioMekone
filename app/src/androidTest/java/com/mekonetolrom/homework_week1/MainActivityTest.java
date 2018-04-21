package com.mekonetolrom.homework_week1;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getTargetContext();
        assertEquals("com.mekonetolrom.homework_week1", appContext.getPackageName());
    }

    @Test
    public void testFormArtifacts() {

        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom"), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText(String.valueOf(R.integer.my_age)), closeSoftKeyboard());

        onView(withId(R.id.edit_name))
               .check(matches(withText(R.string.full_name)));
        onView(withId(R.id.edit_age))
                .check(matches(withText(Integer.toString(R.integer.my_age))));
    }

    @Test
    public void canRotate() {
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom"), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText(String.valueOf(R.integer.my_age)), closeSoftKeyboard());

        TestGoodies.rotateScreen(activityTestRule.getActivity());

        onView(withId(R.id.edit_name))
                .check(matches(withText(R.string.full_name)));
        onView(withId(R.id.edit_age))
                .check(matches(withText(Integer.toString(R.integer.my_age))));
    }

    @Test
    public void enterThankyouActivityWithMessage() {
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom"), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText(String.valueOf(R.integer.my_age)), closeSoftKeyboard());

        Intent resultData = new Intent();
        resultData.putExtra("name", R.string.full_name);
        resultData.putExtra("age", R.integer.my_age);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }
}
