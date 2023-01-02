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
import com.example.imdbproj.adaptor.MovieAdapter
import com.example.imdbproj.adaptor.MovieDetailAdapter
import com.example.imdbproj.adaptor.PersonAdapter
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.databinding.FragmentMainBinding
import com.example.imdbproj.databinding.FragmentMovieBinding
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
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

    private lateinit var actors: List<Person>
    private lateinit var directores: List<Person>

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

        // get movie

        binding = FragmentMovieBinding.bind(view)
        val recyclerViewMovie: RecyclerView = binding.recyclerViewDetalMovie

        recyclerViewMovie.layoutManager = LinearLayoutManager(this.context)
        recyclerViewMovie.adapter = MovieDetailAdapter(movie)


        val recyclerViewActors: RecyclerView = binding.recycleViewActors

        recyclerViewActors.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
       // recyclerViewActors.adapter = PersonAdapter(actors)


        val recyclerViewDirectors: RecyclerView = binding.recycleViewDirectors

        recyclerViewDirectors.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
       // recyclerViewDirectors.adapter = PersonAdapter(directores)

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

        /*
        apiService.getActors(movie.getTitleId()).enqueue(object : Callback<List<Person>> {

            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {

            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                Log.d("TAGGG", t.message.toString())
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        })

         */

    }

}
