package com.example.newsapp.newsapiclient.presention.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentInfoBinding
import com.example.newsapp.newsapiclient.presention.MainActivity
import com.example.newsapp.newsapiclient.presention.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {

    lateinit var binding: FragmentInfoBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        val args: com.example.newsapp.InfoFragmentArgs by navArgs()
        val artical = args.selectedItem
        viewModel = (activity as MainActivity).newsViewModel

//        binding.webView.apply {
//            webViewClient = WebViewClient()
//            try {
//                if(artical.url!=""){
//                    loadUrl(artical.url)
//                }else{
//                    Toast.makeText(activity,"No url...",Toast.LENGTH_LONG).show()
//                }
//            }catch (e:Exception){
//                Toast.makeText(activity,"No url...",Toast.LENGTH_LONG).show()
//
//            }
//
//        }

        binding.tvDescription.text = artical.description
        binding.tvTitle.text = artical.title
        binding.tvPublishedAt.text = artical.publishedAt
        binding.tvSource.text = artical.source?.name
        Glide.with(view)
            .load(artical.urlToImage)
            .into(binding.ivArticleImage)

        binding.floatingActionSave.setOnClickListener(View.OnClickListener {
            viewModel.saveArticle(artical)
            Snackbar.make(view,"Data Saved ....",Snackbar.LENGTH_LONG).show()

        })
    }
}