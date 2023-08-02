package com.project.marvelsuperheroes
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.project.marvelsuperheroes.ui.view.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLogin() {
        // Introduce an email
        onView(withId(R.id.emailInputEditText)).perform(typeText("testuser@test.com"), closeSoftKeyboard())

        // Introduce a password
        onView(withId(R.id.passwordInputEditText)).perform(typeText("testpassword"), closeSoftKeyboard())

        // Click the "Sign in" button
        onView(withId(R.id.btnContinueAction)).perform(click())

    }
}