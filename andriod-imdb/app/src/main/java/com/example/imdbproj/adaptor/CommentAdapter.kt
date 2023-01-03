package com.example.imdbproj.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.Comment
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentAdapter(val comments: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewComment = itemView.findViewById<TextView>(R.id.textViewCommentText)
        val recyclerViewReply = itemView.findViewById<RecyclerView>(R.id.recycleViewReplyes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewComment.text = comments[position].getBody()
        getReplies(comments[position], holder.recyclerViewReply, holder.itemView)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    private fun getReplies(comment: Comment, recyclerView: RecyclerView, view: View) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getReplies(comment.getId()).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {

                val replies = response.body() as ArrayList<Comment>

                recyclerView.layoutManager = LinearLayoutManager(view.context)
                recyclerView.adapter = ReplyAdapter(replies)

            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}