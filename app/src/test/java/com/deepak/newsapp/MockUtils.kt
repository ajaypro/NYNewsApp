package com.deepak.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.deepak.newsapp.data.model.NewsData
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object MockUtils {

    fun mockRepositories(): List<NewsData> {
        val newsList = ArrayList<NewsData>()
        val news = NewsData(
            byline = "By DAVE PHILIPPS",
            title = "Anguish and Anger From the Navy SEALs Who Turned In Edward Gallagher",
            url = "https://www.nytimes.com/2019/12/27/us/navy-seals-edward-gallagher-video.html",
            type = "Article",
            published_date = "2019-12-27",
            source = "The New York Times",
            abstract = "Video interviews and group texts obtained by The Times show men describing their platoon leader in grim terms.",
            media = emptyList(),
            createdAt = System.currentTimeMillis()
        )
        newsList.add(news)
        return newsList
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return data[0] as T
    }
}