package com.mekonetolrom.homework_week1;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
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
            testIntent.putExtra("name", "Mekone Tolrom");
            testIntent.putExtra("age", R.integer.my_age);
            testIntent.putExtra("email", R.string.s_email);
            testIntent.putExtra("username", R.string.alias);
            testIntent.putExtra("dob", R.string.d_date);
            return testIntent;
        }
    };

    @Test
    public void setsRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.tv_greeting))
                .check(matches(withText("Welcome: Mekone Tolrom ")));
    }
}
