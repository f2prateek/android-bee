package com.f2prateek.bee;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class Matchers {
  private Matchers() {
    throw new AssertionError("No instances");
  }

  /** Matches that a {@link ViewGroup} contains a child {@link TextView} with the given text. */
  public static Matcher<View> childWithText(String text) {
    return childWithText(org.hamcrest.Matchers.is(text));
  }

  /**
   * Matches that a {@link ViewGroup} contains a child {@link TextView} that matches the given
   * {@code matcher}.
   */
  public static Matcher<View> childWithText(final Matcher<? extends CharSequence> matcher) {
    return new BoundedMatcher<View, ViewGroup>(ViewGroup.class) {
      public void describeTo(Description description) {
        description.appendText("with text: ");
        matcher.describeTo(description);
      }

      public boolean matchesSafely(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
          try {
            TextView textView = (TextView) viewGroup.getChildAt(i);
            if (matcher.matches(textView.getText())) {
              return true;
            }
          } catch (ClassCastException ignored) {
          }
        }
        return false;
      }
    };
  }
}
