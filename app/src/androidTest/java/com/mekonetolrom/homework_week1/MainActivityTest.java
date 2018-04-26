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
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasTextColor;
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

        onView(withId(R.id.edit_age)).perform(clearText());

        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom"), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText("25"), closeSoftKeyboard());

        onView(withId(R.id.edit_name))
               .check(matches(withText(R.string.full_name)));
        onView(withId(R.id.edit_age))
                .check(matches(withText("25")));
    }

    @Test
    public void canRotate() {
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom"), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText("25"), closeSoftKeyboard());

        TestGoodies.rotateScreen(activityTestRule.getActivity());

        onView(withId(R.id.edit_name))
                .check(matches(withText(R.string.full_name)));
        onView(withId(R.id.edit_age))
                .check(matches(withText("25")));
    }

    @Test
    public void enterThankyouActivityWithMessage() {
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom"), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText("25"), closeSoftKeyboard());

        Intent resultData = new Intent();
        resultData.putExtra("name", R.string.full_name);
        resultData.putExtra("age", R.integer.my_age);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

    @Test
    public void testClickButton(){
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom".trim()), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText("2/2/1975"), closeSoftKeyboard());
        onView(withId(R.id.edit_username)).perform(typeText("mtolrom".trim()), closeSoftKeyboard());
        onView(withId(R.id.edit_email)).perform(typeText("mtolrom@outlook.com"), closeSoftKeyboard());
        onView(withId(R.id.edit_occupation)).perform(typeText("Engineer".trim()), closeSoftKeyboard());
        onView(withId(R.id.edit_description)).perform(typeText("Cloud Computing Engineer"), closeSoftKeyboard());

        onView(withId(R.id.id_submit)).perform(click());

        onView(withId(R.id.tv_greeting))
                .check(matches(withText("Name : Mekone Tolrom")));
        onView(withId(R.id.tv_username))
                .check(matches(withText("Username : mtolrom")));
        onView(withId(R.id.tv_email))
                .check(matches(withText("Email : mtolrom@outlook.com")));
        onView(withId(R.id.tv_age))
                .check(matches(withText("Age : 43")));
        onView(withId(R.id.tv_occupation))
                .check(matches(withText("Occupation : Engineer")));
        onView(withId(R.id.tv_description))
                .check(matches(withText("Description : Cloud Computing Engineer")));
    }

    @Test
    public void testErrorMessage(){
        onView(withId(R.id.id_submit)).perform(click());
        onView(withId(R.id.error_status))
                .check(matches(withText(R.string.oops_errors)));
        onView(withId(R.id.error_status))
                .check(matches(hasTextColor(R.color.colorAccent)));
    }

    @Test
    public void testAgeNull(){
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom".trim()), closeSoftKeyboard());
        onView(withId(R.id.id_submit)).perform(click());
        onView(withId(R.id.error_status))
                .check(matches(withText(R.string.oops_errors)));
        onView(withId(R.id.error_status))
                .check(matches(hasTextColor(R.color.colorAccent)));
    }

    @Test
    public void testAgeLessThanEighteen(){
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom".trim()), closeSoftKeyboard());
        String s = Integer.toString(R.integer.under_age);
        onView(withId(R.id.edit_age)).perform(typeText("2/2/2010"), closeSoftKeyboard());
        onView(withId(R.id.id_submit)).perform(click());
        onView(withId(R.id.error_status))
                .check(matches(withText(R.string.fix_errors)));
    }

    @Test
    public void testWrongDateFormat(){
        onView(withId(R.id.edit_name)).perform(typeText("Mekone Tolrom".trim()), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText("2010"), closeSoftKeyboard());
        onView(withId(R.id.id_submit)).perform(click());
        onView(withId(R.id.error_status))
                .check(matches(withText(R.string.oops_errors)));
    }
}
