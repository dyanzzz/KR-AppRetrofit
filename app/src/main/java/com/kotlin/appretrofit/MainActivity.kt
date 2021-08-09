package com.kotlin.appretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.appretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PostAdapter
    private lateinit var adapterComment: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        //showPost(binding)

        //createPost(binding)

        showComments(binding)
    }

    private fun showComments(binding: ActivityMainBinding) {
        viewModel.showComment()
        adapterComment = CommentAdapter()
        adapterComment.notifyDataSetChanged()

        binding.apply {
            rvPost.setHasFixedSize(true)
            rvPost.layoutManager = LinearLayoutManager(this@MainActivity)
            rvPost.adapter = adapterComment
            tvResponseCode.text = "Comment Response code JSON Placeholder : ${viewModel.getResponseCode()}"
        }

        viewModel.getComment().observe(this, {
            if (it != null) {
                adapterComment.setListComment(it)
            }
        })
    }

    private fun createPost(binding: ActivityMainBinding) {
        // adapter untuk CreatePOstRequest belum dibuat
        viewModel.createPostData()

        viewModel.getCreatePostData().observe(this, {
            if (it != null) {
                binding.apply {
                    val responseText = "Response : ${viewModel.getResponseCode()}\n" +
                            "Title : ${it.title}\n" +
                            "Body : ${it.text}\n" +
                            "UserId : ${it.userId}\n" +
                            "Id Generate From System : ${it.id}\n"

                    tvResponseCode.text = responseText
                }
            }
        })
    }

    private fun showPost(binding: ActivityMainBinding) {
        viewModel.setPostData()
        adapter = PostAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvPost.setHasFixedSize(true)
            rvPost.layoutManager = LinearLayoutManager(this@MainActivity)
            rvPost.adapter = adapter
            tvResponseCode.text = "Response Code JSON Placeholder : ${viewModel.getResponseCode()}"
        }

        viewModel.getPostData().observe(this, {
            if (it != null) {
                adapter.setList(it)
            }
        })
    }
}