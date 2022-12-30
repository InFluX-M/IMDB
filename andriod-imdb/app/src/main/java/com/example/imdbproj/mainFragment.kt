package com.example.imdbproj

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.adaptor.MovieAdapter
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.TitleType
import com.example.imdbproj.databinding.FragmentMainBinding
import com.example.imdbproj.repository.Repository
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiServiceMovie
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

        //recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
        //recyleViewTopMovies.adapter = MovieAdapter(movies)

        super.onViewCreated(view, savedInstanceState)

    }

    private fun movieLayoutClicked(movie: Movie) {
        Toast.makeText(this.context,"movie show",Toast.LENGTH_LONG).show()
    }


    private fun getMovies() {

        val apiClient = ApiClient()
        val apiService: ApiServiceMovie = apiClient.getRetrofit().create(ApiServiceMovie::class.java)

        apiService.getMovies().enqueue(object : Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                   movies = response.body() as ArrayList<Movie>
                   // (recycleView.adapter as MovieAdapter).movies = movies
                    recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
                    recyleViewTopMovies.adapter = MovieAdapter(movies)
                } else {
                    println(response.body())
                }
                Log.d("TAGGG", "gmvhmhgmm")
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("TAGGG", t.message.toString())
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