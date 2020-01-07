package com.deepak.newsapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.deepak.newsapp.R
import com.deepak.newsapp.ui.adapter.NewsListAdapter
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsListActivityTest {

    private val BELOW_THE_FOLD = 10

    @get:Rule
    var activityTestRule = ActivityTestRule(NewsListActivity::class.java)


    @Test
    fun show_title() {
        onView(ViewMatchers.withId(R.id.toolbar_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.toolbar_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("NewsApp")))
    }

    @Test
    fun show_recyclerView_visible() {
        onView(ViewMatchers.withId(R.id.news_list))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityTestRule.activity.window.decorView)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun itemClick_recyclerview(){
        onView(ViewMatchers.withId(R.id.news_list))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityTestRule.activity.window.decorView)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.ViewHolder>(1, click()))
    }

    @Test
    fun scroll_lastitem(){
        val recyclerView = activityTestRule.activity.findViewById<RecyclerView>(R.id.news_list)
        val itemCount = recyclerView.adapter?.itemCount

        onView(ViewMatchers.withId(R.id.news_list))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityTestRule.activity.window.decorView)))
            .perform(RecyclerViewActions.scrollToPosition<NewsListAdapter.ViewHolder>(itemCount!!.minus(1)))


    }

    @Test
    fun check_ItemBelowFold() {
        onView(ViewMatchers.withId(R.id.news_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.ViewHolder>(
                    BELOW_THE_FOLD,
                    click()
                )
            )
    }

}