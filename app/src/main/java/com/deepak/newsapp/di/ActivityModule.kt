package com.deepak.newsapp.di


import com.deepak.newsapp.ui.NewsDetailActivity
import com.deepak.newsapp.ui.NewsDetailFragment
import com.deepak.newsapp.ui.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeNewsListActivity(): NewsListActivity

    @ContributesAndroidInjector
    internal abstract fun contributeNewsDetailActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    internal abstract fun contributeNewsDetailFragment(): NewsDetailFragment
}