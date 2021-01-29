package com.emilpana.directoryapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.emilpana.directoryapp.ui.fragment.PeopleFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class UiTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testProgressView() {
        val scenario = launchFragmentInHiltContainer<PeopleFragment>()

        onView(
            allOf(
                withParent(withId(R.id.peopleLayoutContainer)),
                withId(R.id.peopleProgressBar),
                isCompletelyDisplayed()
            )
        ).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
