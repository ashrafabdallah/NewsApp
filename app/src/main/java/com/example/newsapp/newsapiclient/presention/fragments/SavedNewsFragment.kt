package com.example.newsapp.newsapiclient.presention.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.newsapiclient.presention.MainActivity
import com.example.newsapp.newsapiclient.presention.adapter.NewsAdapter
import com.example.newsapp.newsapiclient.presention.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class SavedNewsFragment : Fragment() {
    lateinit var newsviewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentSavedNewsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        newsviewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        initRecyclerViewSaved()

        newsviewModel.getSavedNews().observe(viewLifecycleOwner, Observer {

            newsAdapter.differ.submitList(it)
        })
        newsAdapter.setItemClickListner {
            var bundle = Bundle().apply {
                putSerializable("selected_Item", it)
            }
            findNavController().navigate(R.id.action_savedNewsFragment2_to_infoFragment, bundle)

        }

        val itemSwapCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // adapterpostion
                val position = viewHolder.bindingAdapterPosition
                var articale = newsAdapter.differ.currentList[position]
                newsviewModel.deleteFromLocalDataBase(articale)
                Snackbar.make(view, "Delete Success ....", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("UnDo") {
                            newsviewModel.saveArticle(articale)

                        }
                        show()
                    }


            }

        }

        ItemTouchHelper(itemSwapCallBack).apply {
            attachToRecyclerView(binding.RecyclerViewSaved)
        }

    }

    private fun initRecyclerViewSaved() {
        binding.RecyclerViewSaved.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
    }
}