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
    final Matcher<String> matcher = org.hamcrest.Matchers.is(text);
    return childWithMatcher(new BoundedMatcher<View, TextView>(TextView.class) {
      @Override public void describeTo(Description description) {
        description.appendText("with text: ");
        matcher.describeTo(description);
      }

      @Override protected boolean matchesSafely(TextView textView) {
        return matcher.matches(textView.getText());
      }
    });
  }

  /**
   * Matches that a {@link ViewGroup} contains a child {@link View} that matches the given
   * {@code matcher}.
   */
  static Matcher<View> childWithMatcher(final Matcher<?> matcher) {
    return new BoundedMatcher<View, ViewGroup>(ViewGroup.class) {
      public void describeTo(Description description) {
        description.appendText("child with matcher: ");
        matcher.describeTo(description);
      }

      public boolean matchesSafely(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
          if (matcher.matches(viewGroup.getChildAt(i))) {
            return true;
          }
        }
        return false;
      }
    };
  }
}
