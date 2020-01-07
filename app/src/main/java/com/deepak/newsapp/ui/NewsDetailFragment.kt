package com.deepak.newsapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.deepak.newsapp.R
import com.deepak.newsapp.databinding.NewsDetailBinding
import com.deepak.newsapp.ui.viewmodel.NewsViewModel
import com.deepak.newsapp.ui.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_news_detail.*
import javax.inject.Inject

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [NewsListActivity]
 * in two-pane mode (on tablets) or a [NewsDetailActivity]
 * on handsets.
 */
class NewsDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: NewsDetailBinding
    private lateinit var viewModel: NewsViewModel

    companion object {
        const val ARG_NEWS_ID = "news_id"
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_detail, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        arguments?.let {
            if (it.containsKey(ARG_NEWS_ID)) {
                viewModel.fetchNewsById(it.getLong(ARG_NEWS_ID))
                viewModel.getNewsData().observe(viewLifecycleOwner, Observer {news ->
                    activity?.header?.let {header ->
                        news?.media?.first {
                            it.type.equals("image")
                        }?.mediaList?.first {
                            it.format.equals("mediumThreeByTwo440")
                        }?.url?.apply {
                            Glide.with(this@NewsDetailFragment).load(this).into(header)
                        }
                    }
                    binding.itemDetail.text = news?.title
                })
            }
        }
    }


}
