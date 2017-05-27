package com.f2prateek.bee

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.f2prateek.bee.Matchers.Companion.childWithText
import com.squareup.spoon.Spoon
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rx.plugins.RxJavaHooks
import rx.schedulers.Schedulers

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule @JvmField
    val main = ActivityTestRule(MainActivity::class.java)

    @Test @Throws(Exception::class)
    fun display() {
        Spoon.screenshot(main.activity, "start")
        onView(withId(R.id.editor)).perform(typeText("Foo Bar"))
        Spoon.screenshot(main.activity, "typed_text")
        onView(withId(R.id.display)) //
                .check(matches(childWithText("Foxtrot Oscar Oscar\nBravo Alfa Romeo")))
        Spoon.screenshot(main.activity, "end")
    }

    companion object {
        @BeforeClass @JvmStatic @Throws(Exception::class)
        fun setUpOnce() {
            RxJavaHooks.setOnComputationScheduler { Schedulers.immediate() }
        }
    }
}
