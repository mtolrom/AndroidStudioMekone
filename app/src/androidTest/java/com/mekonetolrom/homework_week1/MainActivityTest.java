package com.mekonetolrom.homework_week1;

import android.os.IBinder;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import android.support.test.espresso.matcher.ViewMatchers;
import static org.hamcrest.Matchers.allOf;
import android.view.WindowManager;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testProfileFragment() {
        ProfileFragment fragment = new ProfileFragment();
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.viewpager, fragment).commit();
        onView(withIndex(withId(R.id.button2), 0)).perform(click());

        onView(withId(R.id.edit_name))
              .check(matches(withText("")));
        onView(withId(R.id.edit_username))
                .check(matches(withText("")));
        onView(withId(R.id.edit_age))
                .check(matches(withText("")));
        onView(withId(R.id.edit_email))
                .check(matches(withText("")));
        onView(withId(R.id.edit_description))
                .check(matches(withText("")));
        onView(withId(R.id.edit_occupation))
                .check(matches(withText("")));
    }

    @Test
    public void testMatchesFragment() {
        ProfileFragment fragment = new ProfileFragment();
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.viewpager, fragment).commit();
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withIndex(withId(R.id.card_image), 0)).check(matches(isDisplayed()));
    }

    @Test
    public void testSettingsFragment() {
        ProfileFragment fragment = new ProfileFragment();
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.viewpager, fragment).commit();
        onView(withId(R.id.viewpager)).perform(swipeLeft());

        onView(withIndex(withId(R.id.favorite_button), 0)).perform(click());
        //onView(withText("You liked")).inRoot(new ToastMatcher())
                //.check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        /*onView(withId(R.id.edit_st_email))
                .check(matches(withText("")));
        onView(withId(R.id.st_malefemale))
                .check(matches(withText("")));
        onView(withId(R.id.st_publicprivate))
                .check(matches(withText("")));*/
        onView(withId(R.id.edit_st_email)).perform(typeText("mekone@yahoo.ca"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.st_malefemale)).perform(typeText("M"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.st_publicprivate)).perform(typeText("Public"), ViewActions.closeSoftKeyboard());
        onView(withIndex(withId(R.id.btnSettings), 0)).perform(click());
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("You liked");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    //means this window isn't contained by any other windows.
                }
            }
            return false;
        }
    }
}
