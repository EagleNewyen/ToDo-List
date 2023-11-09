package com.example.todopt2.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.filters.MediumTest
import com.example.todopt2.R
import com.example.todopt2.launchFragmentInHiltContainer
import com.example.todopt2.ui.add_edit_screen.AddScreenFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*



@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi

class AddScreenFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun enteringTextIntoField_clickSaveButton_navigateToFirstFragment() {

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddScreenFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.textInput)).perform(typeText("Eat"))

        onView(withId(R.id.saveButton)).perform(click())

        verify(navController).navigate(
            AddScreenFragmentDirections.actionAddScreenFragmentToFirstScreenTaskFragment()
        )


    }






}