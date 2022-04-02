package com.example.newsapp.newsapiclient.presention.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.newsapiclient.data.util.Resource
import com.example.newsapp.newsapiclient.presention.MainActivity
import com.example.newsapp.newsapiclient.presention.adapter.NewsAdapter
import com.example.newsapp.newsapiclient.presention.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var fragmenNewsBinding: FragmentNewsBinding
    lateinit var newsAdapter: NewsAdapter
    private var isScroling = false
    private var isLoading = false
    private var isLastPage = false
    private var country = "us"
    private var page = 1



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmenNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        initRecyclerView()
        viewNewsList()
        setSearchView()
        newsAdapter.setItemClickListner {
            var bundle = Bundle().apply {
                putSerializable("selected_Item", it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment,bundle)

        }


    }

    private fun viewNewsList() {


        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        var pages = 0
                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages


                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }

    private fun initRecyclerView() {
        // newsAdapter = NewsAdapter()
        fragmenNewsBinding.newsRcycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(onScrollinsnerLisner)
        }
    }

    private fun showProgressBar() {
        isLoading = true
        fragmenNewsBinding.progressBarNewsHeadLines.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        fragmenNewsBinding.progressBarNewsHeadLines.visibility = View.INVISIBLE
    }



    // search
    private fun setSearchView(){
        fragmenNewsBinding.serachView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.getSearchNews(country,p0.toString(),page)
                displaySearchNews()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.getSearchNews(country,p0.toString(),page)
                    displaySearchNews()
                }
                return false
            }


        })

        fragmenNewsBinding.serachView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRecyclerView()
                viewNewsList()
                return false
            }

        })
    }

    private fun displaySearchNews(){
        //viewModel.getNewsHeadLines(country, page)
        viewModel.searchNewsHeadLines.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        var pages = 0
                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages


                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }


    private val onScrollinsnerLisner = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            var layoutManger = fragmenNewsBinding.newsRcycler.layoutManager as LinearLayoutManager
            // using layoutManger manger instance we are  going to 3 properties of the current recyleView
            // 1 ..  Size of the current list
            // 2 ..  visible ItemCount
            // 3..  starting Position of visible Item
            val sizeOfListItem = layoutManger.itemCount
            val visibleItemcount = layoutManger.childCount
            val startingPostionof_visible_Item =
                layoutManger.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd =
                startingPostionof_visible_Item + visibleItemcount >= sizeOfListItem
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScroling
            if (shouldPaginate) {
                page++
                viewModel.getNewsHeadLines(country, page)
                isScroling = false
            }
            Log.i("TAGG",sizeOfListItem.toString())
            Log.i("PAGE",page.toString())


        }
    }


}