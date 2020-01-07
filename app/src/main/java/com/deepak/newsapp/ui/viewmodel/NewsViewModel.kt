package com.deepak.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.deepak.newsapp.data.model.NewsData
import com.deepak.newsapp.data.repository.NewsRepository
import com.deepak.newsapp.utils.network.Resource
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val dataRepository: NewsRepository) :
    ViewModel() {

    private val newsListData = MutableLiveData<Boolean>()


    /**
     * Livedata for newsdata with status to display list of news in  NewsListActivity
     * Using switchmap so that we get the latest data while currently fetching
     */
    private val newsListLiveData: LiveData<Resource<List<NewsData>?>> =
        Transformations.switchMap(newsListData)
        { input -> dataRepository.getData(input) }

    private val newsDataId = MutableLiveData<Long>()

    /**
     * Livedata for single newsdata to display in NewsDetailfragment.
     */
    private val newsLiveData: LiveData<NewsData> = Transformations.switchMap(newsDataId)
    { input -> dataRepository.getNewsById(input) }

    fun getNewsLiveData() = newsListLiveData

    fun fetchUpdatedData(forceRefresh: Boolean) {
        newsListData.value = forceRefresh
    }

    fun getNewsData() = newsLiveData

    fun fetchNewsById(id: Long) {
        newsDataId.value = id
    }
}
