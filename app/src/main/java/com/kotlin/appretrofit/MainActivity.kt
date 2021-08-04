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

        adapter = PostAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PostViewModel::class.java)

        binding.apply {
            tvResponseCode.text = "JSON Placeholder"
            rvPost.setHasFixedSize(true)
            rvPost.layoutManager = LinearLayoutManager(this@MainActivity)
            rvPost.adapter = adapter
            viewModel.setPostData()
        }

        viewModel.getPostData().observe(this, {
            if (it != null) {
                adapter.setList(it)
            }
        })
    }
}