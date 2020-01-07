package com.deepak.newsapp.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.deepak.newsapp.R
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class NewsDetailActivityTest {


    @get:Rule
    var activityTestRule = ActivityTestRule(NewsDetailActivity::class.java)

    @Test
    fun show_toolbar_withText(){

        onView(ViewMatchers.withId(R.id.head_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.head_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("News Detail")))
    }

    @Test
    fun show_news_title(){
        onView(ViewMatchers.withId(R.id.item_detail))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityTestRule.activity.window.decorView)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

