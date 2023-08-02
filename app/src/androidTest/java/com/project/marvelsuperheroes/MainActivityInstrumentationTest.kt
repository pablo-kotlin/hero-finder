package com.project.marvelsuperheroes

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.project.marvelsuperheroes.ui.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    // This rule launches the MainActivity
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isSuperheroListVisible_onAppLaunch() {
        // Checks if the Superhero list is visible when the app is launched
        onView(withId(R.id.rvSuperheroList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isProgressBarVisible_onAppLaunch() {
        // Checks if the progress bar is not visible when the app is launched
        onView(withId(R.id.loading))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun test_isSearchInputVisible_onAppLaunch() {
        // Checks if the search input box is visible when the app is launched
        onView(withId(R.id.etSearchSuperheroes))
            .check(matches(isDisplayed()))
    }

}