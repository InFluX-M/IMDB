package com.example.imdbproj.adaptor

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.User

class ImdbAdaptor(context: Context, users: List<User>): RecyclerView.Adapter<ImdbAdaptor.ImdbHolder>() {

    private var context: Context
        get() {
            TODO()
        }
        set(value) {}
    private var users: List<User>
        get() {
            TODO()
        }
        set(value) {}

    init {
        this.context = context
        this.users = users
    }


    public class ImdbHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.imageMovie)
        var text: TextView = itemView.findViewById(R.id.nameMovie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImdbHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ImdbHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}