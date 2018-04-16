package com.mekonetolrom.homework_week1;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.mekonetolrom.homework_week1", appContext.getPackageName());
    }

    @Test
    public void testTranslation() {
        //get TextView Id
        onView(withId(R.id.hello_id))
                .check(matches(withText(R.string.hello_world)));
        //click button
        onView(withText(R.string.translate_in)).perform(click());
        //assert
        onView(withId(R.id.hello_id))
                .check(matches(withText(R.string.french)));
    }
}
