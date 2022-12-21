package com.example.imdbproj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.adaptor.MovieAdapter
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.databinding.FragmentMainBinding
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


class mainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var movies: ArrayList<Movie> = ArrayList<Movie> ()

    lateinit var binding: FragmentMainBinding

    private companion object {
        lateinit var recycleView: RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentMainBinding.bind(view)

       recycleView = binding.recyleViewTopMovies

        getMovies()



/*
        val list = ArrayList<String>()
        list.add("comedy")
        val movie1 = Movie("1", TitleType.MOVIE, "movie1", true, 2021, 2021, 120, list)
        val movie2 = Movie("2", TitleType.MOVIE, "movie2", true, 2021, 2021, 120, list)
        val movie3 = Movie("3", TitleType.MOVIE, "movie3", true, 2021, 2021, 120, list)
        val movie4 = Movie("4", TitleType.MOVIE, "movie4", true, 2021, 2021, 120, list)
        val movie5 = Movie("5", TitleType.MOVIE, "movie5", true, 2021, 2021, 120, list)

        movies.add(movie1)
        movies.add(movie2)
        movies.add(movie3)
        movies.add(movie4)
        movies.add(movie5)

 */

        recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
        recyleViewTopMovies.adapter = MovieAdapter(movies)


        super.onViewCreated(view, savedInstanceState)

    }

    private fun movieLayoutClicked(movie: Movie) {
        Toast.makeText(this.context,"movie show",Toast.LENGTH_LONG).show()
    }


    private fun getMovies() {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getAllMovies().enqueue(object : Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                   movies = response.body() as ArrayList<Movie>
                } else {
                    println(response.body())
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }


        })

    }


    override fun onResume() {
        super.onResume()
        this.getMovies()
    }
}