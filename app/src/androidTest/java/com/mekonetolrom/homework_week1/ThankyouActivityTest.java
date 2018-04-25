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
            testIntent.putExtra("name", "Name : Mekone Tolrom");
            testIntent.putExtra("age", "Age : 25");
            testIntent.putExtra("email", "Email : sde@outlook.com");
            testIntent.putExtra("username", "Username : mtolrom");
            testIntent.putExtra("occupation", "Occupation : Engineer");
            testIntent.putExtra("description", "Description : Cloud Computing Engineer");
            return testIntent;
        }
    };

    @Test
    public void setsRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.tv_greeting))
                .check(matches(withText("Name : Mekone Tolrom")));
        onView(withId(R.id.tv_username))
                .check(matches(withText("Username : mtolrom")));
        onView(withId(R.id.tv_email))
                .check(matches(withText("Email : sde@outlook.com")));
        onView(withId(R.id.tv_age))
                .check(matches(withText("Age : 25")));
        onView(withId(R.id.tv_occupation))
                .check(matches(withText("Occupation : Engineer")));
        onView(withId(R.id.tv_description))
                .check(matches(withText("Description : Cloud Computing Engineer")));
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
                .check(matches(withText("mm/dd/yyyy")));
        onView(withId(R.id.edit_occupation))
                .check(matches(withText("")));
        onView(withId(R.id.edit_description))
                .check(matches(withText("")));
    }
}
