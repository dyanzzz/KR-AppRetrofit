package com.kotlin.appretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.appretrofit.databinding.ItemPostBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val list = ArrayList<PostResponse>()

    fun setList(posts: ArrayList<PostResponse>){
        list.clear()
        list.addAll(posts)
        notifyDataSetChanged()
    }

    // merubah dari kotlin syntetic ke viewbinding
    //inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    inner class PostViewHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root){
        // bind, untuk mengirim data ke layout
        fun bind(postResponse: PostResponse){
            // with(itemView) {
                println("### testing : ${postResponse.title}")
                binding.apply {
                    val text = "ID : ${postResponse.id}\n" +
                            "TITLE : ${postResponse.title}\n" +
                            "TEXT : ${postResponse.text}\n" +
                            "USER_ID : ${postResponse.userId}"

                    tvText.text = text
                }
            // }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // merubah dari kotlin syntetic ke viewbinding
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        val view = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}