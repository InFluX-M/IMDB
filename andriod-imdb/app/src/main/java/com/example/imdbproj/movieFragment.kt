package com.example.imdbproj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.Person
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.adaptor.*
import com.example.imdbproj.classes.mainClasses.Comment
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.databinding.FragmentMovieBinding
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import com.example.imdbproj_1.mainClasses.Rating
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class movieFragment(movie: Movie) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var movie: Movie

    private var actors: ArrayList<com.example.imdbproj.classes.mainClasses.Person> = java.util.ArrayList()
    private var directores: ArrayList<com.example.imdbproj.classes.mainClasses.Person> = ArrayList()

    private lateinit var binding: FragmentMovieBinding

    init {
        this.movie = movie
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentMovieBinding.bind(view)

        binding.textViewTitle.text = movie.getTitle()
        binding.textViewIsAdult.text = movie.getAdult()
        binding.textViewYear.text = movie.getYear()
        binding.textViewTime.text = movie.getTime()
        binding.textViewGenres.text = movie.getGenresList()
        binding.textViewAveRank.text = movie.getRank()


        getComments()
        getActors()
        getDirectors()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    private fun getActors() {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)


        apiService.getActors(movie.getTitleId()).enqueue(object : Callback<List<com.example.imdbproj.classes.mainClasses.Person>> {
            override fun onResponse(
                call: Call<List<com.example.imdbproj.classes.mainClasses.Person>>,
                response: Response<List<com.example.imdbproj.classes.mainClasses.Person>>
            ) {
                actors = response.body() as ArrayList<com.example.imdbproj.classes.mainClasses.Person>

                val recyclerViewActors: RecyclerView = binding.recyclrViewActors
                recyclerViewActors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewActors.adapter = PersonAdapter(actors)
            }

            override fun onFailure(
                call: Call<List<com.example.imdbproj.classes.mainClasses.Person>>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun getDirectors() {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getDirectors(movie.getTitleId()).enqueue(object : Callback<List<com.example.imdbproj.classes.mainClasses.Person>> {
            override fun onResponse(
                call: Call<List<com.example.imdbproj.classes.mainClasses.Person>>,
                response: Response<List<com.example.imdbproj.classes.mainClasses.Person>>
            ) {
                directores = response.body() as ArrayList<com.example.imdbproj.classes.mainClasses.Person>

                val recyclerViewDirectors: RecyclerView = binding.recycleViewDirectors
                recyclerViewDirectors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewDirectors.adapter = PersonAdapter(directores)
            }

            override fun onFailure(
                call: Call<List<com.example.imdbproj.classes.mainClasses.Person>>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun getComments() {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getComments(movie.getTitleId()).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {

                val comments = response.body() as ArrayList<Comment>

                val recyclerViewComment: RecyclerView = binding.recycleViewComment
                recyclerViewComment.layoutManager = LinearLayoutManager(context)
                recyclerViewComment.adapter = CommentAdapter(comments)
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
