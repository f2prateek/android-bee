package com.f2prateek.bee

import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher

class Matchers private constructor() {
    init {
        throw AssertionError("no instances.")
    }

    companion object {
        /** Matches that a [ViewGroup] contains a child [TextView] with the given text. */
        fun childWithText(text: String): Matcher<View> {
            val matcher = org.hamcrest.Matchers.`is`(text)
            return childWithMatcher(object : BoundedMatcher<View, TextView>(TextView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("with text: ")
                    matcher.describeTo(description)
                }

                override fun matchesSafely(textView: TextView): Boolean {
                    return matcher.matches(textView.text)
                }
            })
        }

        /**
         * Matches that a [ViewGroup] contains a child [View] that matches the given
         * `matcher`.
         */
        private fun childWithMatcher(matcher: Matcher<*>): Matcher<View> {
            return object : BoundedMatcher<View, ViewGroup>(ViewGroup::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("child with matcher: ")
                    matcher.describeTo(description)
                }

                public override fun matchesSafely(viewGroup: ViewGroup): Boolean {
                    for (i in 0..viewGroup.childCount - 1) {
                        if (matcher.matches(viewGroup.getChildAt(i))) {
                            return true
                        }
                    }
                    return false
                }
            }
        }
    }
}
