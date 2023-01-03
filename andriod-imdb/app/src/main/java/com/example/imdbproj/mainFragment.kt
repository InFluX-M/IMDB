package com.example.imdbproj

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.adaptor.MovieAdapter
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.databinding.FragmentMainBinding
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    private lateinit var binding: FragmentMainBinding
    private var user = MainActivity.USER_GLOBAl


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

        binding.buttonPopUp.setOnClickListener {
            var dialo = filterPopUpFragment()
            dialo.show((this.context as AppCompatActivity).supportFragmentManager,"my pop up fragment")
        }

       recycleView = binding.recyleViewTopMovies

        getMovies()

        super.onViewCreated(view, savedInstanceState)

    }

    fun movieLayoutClicked(movie: Movie) {
        replaceFragment(movieFragment(movie))
    }


    private fun getMovies() {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getMovies().enqueue(object : Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                movies = response.body() as ArrayList<Movie>
                recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
                recyleViewTopMovies.adapter = MovieAdapter(movies,
                    {movieItem: Movie -> movieLayoutClicked(movieItem)})
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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = (this.context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction.commit()
    }
}