package com.mekonetolrom.homework_week1;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class ThankyouActivityTest {

    @Rule
    public ActivityTestRule<ThankyouActivity> activityTestRule
            = new ActivityTestRule<ThankyouActivity>(ThankyouActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra("name", R.string.str_name);
            testIntent.putExtra("age", R.string.str_age);
            testIntent.putExtra("email", R.string.str_email);
            testIntent.putExtra("username", R.string.str_username);
            testIntent.putExtra("occupation", R.string.str_occupation);
            testIntent.putExtra("description", R.string.str_description);
            return testIntent;
        }
    };

    @Test
    public void setsRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.tv_greeting))
                .check(matches(withText(R.string.r_name)));
        onView(withId(R.id.tv_username))
                .check(matches(withText(R.string.r_username)));
        onView(withId(R.id.tv_email))
                .check(matches(withText(R.string.r_email)));
        onView(withId(R.id.tv_age))
                .check(matches(withText(R.string.r_age)));
        onView(withId(R.id.tv_occupation))
                .check(matches(withText(R.string.r_occupation)));
        onView(withId(R.id.tv_description))
                .check(matches(withText(R.string.r_description)));
    }

    @Test
    public void goBackToMainPage(){
        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.edit_name))
                .check(matches(withText("")));
        onView(withId(R.id.edit_username))
                .check(matches(withText("")));
        onView(withId(R.id.edit_email))
                .check(matches(withText("")));
        onView(withId(R.id.edit_age))
                .check(matches(withText("")));
        onView(withId(R.id.edit_occupation))
                .check(matches(withText("")));
        onView(withId(R.id.edit_description))
                .check(matches(withText("")));
    }
}
