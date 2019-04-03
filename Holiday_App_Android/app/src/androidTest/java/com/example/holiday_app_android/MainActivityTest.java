package com.example.holiday_app_android;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction textView = onView(
                allOf(withText("Irish Holiday App"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Irish Holiday App")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.allHolidays), withText("All Holidays"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("All Holidays")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.value_title_name), withText("Easter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_holidays),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Easter")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.value_title_name), withText("Father's Day"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_holidays),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Father's Day")));

        ViewInteraction textView5 = onView(
                allOf(withText("Bank Holidays"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        textView5.check(matches(withText("Bank Holidays")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.value_title_name), withText("May Bank Holiday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_bank_holidays),
                                        0),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("May Bank Holiday")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.label_title_name), withText("Name"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_holidays),
                                        0),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("Name")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.label_title_date), withText("Date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_holidays),
                                        0),
                                2),
                        isDisplayed()));
        textView8.check(matches(withText("Date")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
