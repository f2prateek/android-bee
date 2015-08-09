package com.f2prateek.bee;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.f2prateek.bee.Matchers.childWithText;
import static java.lang.Thread.sleep;

@RunWith(AndroidJUnit4.class)
public final class MainActivityTest {
  @Rule public final ActivityTestRule<MainActivity> main =
      new ActivityTestRule<>(MainActivity.class);

  @Test public void display() throws Exception {
    onView(withId(R.id.editor)).perform(typeText("Foo Bar"));
    sleep(400); // Account for debouncing.
    onView(withId(R.id.display)) //
        .check(matches(childWithText("Foxtrot Oscar Oscar\nBravo Alfa Romeo")));
  }
}
