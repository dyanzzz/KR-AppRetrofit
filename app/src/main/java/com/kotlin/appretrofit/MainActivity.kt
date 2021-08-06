package com.kotlin.appretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.appretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PostViewModel::class.java)

        showPost(binding)

        //createPost(binding)
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