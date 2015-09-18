package com.f2prateek.bee;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.squareup.spoon.Spoon;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.f2prateek.bee.Matchers.childWithText;

@RunWith(AndroidJUnit4.class)
public final class MainActivityTest {
  @Rule public final ActivityTestRule<MainActivity> main =
      new ActivityTestRule<>(MainActivity.class);

  @BeforeClass public static void setUpOnce() throws Exception {
    SchedulersHook.setComputationScheduler(Schedulers.immediate());
  }

  @Test public void display() throws Exception {
    Spoon.screenshot(main.getActivity(), "empty_text");
    onView(withId(R.id.editor)).perform(typeText("Foo Bar"));
    onView(withId(R.id.display)) //
        .check(matches(childWithText("Foxtrot Oscar Oscar\nBravo Alfa Romeo")));
    Spoon.screenshot(main.getActivity(), "typed_text");
  }
}
