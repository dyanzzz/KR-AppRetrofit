package com.kotlin.appretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.appretrofit.databinding.ItemPostBinding

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val listComment = ArrayList<CommentResponse>()

    fun setListComment(comment: ArrayList<CommentResponse>) {
        listComment.clear()
        listComment.addAll(comment)
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(commentResponse: CommentResponse){
            binding.apply {
                val text = "ID : ${commentResponse.id}\n" +
                        "NAME : ${commentResponse.name}\n" +
                        "EMAIL : ${commentResponse.email}\n" +
                        "BODY : ${commentResponse.body}\n" +
                        "POST_ID : ${commentResponse.postId}"
                tvText.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        val view = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        // sebelum setup fungsi onBindViewHolder, buat dulu fungsi bindnya
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int {
        return listComment.size
    }
}