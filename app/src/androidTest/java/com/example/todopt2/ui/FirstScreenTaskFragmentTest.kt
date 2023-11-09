package com.example.todopt2.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.todopt2.R
import com.example.todopt2.launchFragmentInHiltContainer
import com.example.todopt2.ui.task_screen.FirstScreenTaskFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class FirstScreenTaskFragmentTest {



    @get:Rule
    var hiltRule = HiltAndroidRule(this)



    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun clickAddTaskButton_navigateToAddFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<FirstScreenTaskFragment>{
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.addButton)).perform(click())

        verify(navController).navigate(
            FirstScreenTaskFragmentDirections.actionFirstScreenTaskFragmentToAddScreenFragment()
        )

    }







}