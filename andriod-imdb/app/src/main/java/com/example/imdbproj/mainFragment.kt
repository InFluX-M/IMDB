package com.example.imdbproj

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.adaptor.MovieAdapter
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.databinding.FragmentMainBinding
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import kotlinx.android.synthetic.main.details_movie.view.*
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

        val sorting = arrayOf(" ", "rank", "year", "top 10")
        val spinnerSort = binding.spinnerSort
        spinnerSort.adapter = ArrayAdapter<String> (view.context, android.R.layout.simple_spinner_dropdown_item, sorting)

        spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when(position) {
                    1 -> sortByRank()
                    2 -> sortByYear()
                    3 -> show10Top()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        val searchView = binding.searchView2
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(searchView.query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(searchView.query.isEmpty())
                    getMovies()
                return true
            }

        })


        binding.buttonFilterFragment.setOnClickListener {
            var dialo = filterPopUpFragment({moviesList: ArrayList<Movie> -> updateList(moviesList)})
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


    fun updateList(movieList: ArrayList<Movie>) {
        recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
        recyleViewTopMovies.adapter = MovieAdapter(movieList,
            {movieItem: Movie -> movieLayoutClicked(movieItem)})

        movies = movieList
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

    private fun sortByRank() {

        var temp: Movie
        var sorted = false

        var list = ArrayList<Movie>()
        for (movie in movies)
            list.add(movie)

        while (!sorted) {
            sorted = true
            for (i in 0 until list.size - 1) {
                if (list.get(i).getRank().toFloat() < (list.get(i + 1).getRank().toFloat())) {
                    temp = list.get(i)
                    list.set(i, list.get(i + 1))
                    list.set(i + 1, temp)
                    sorted = false
                }
            }
        }

        recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
        recyleViewTopMovies.adapter = MovieAdapter(list,
            {movieItem: Movie -> movieLayoutClicked(movieItem)})

    }

    private fun sortByYear() {
        var temp: Movie
        var sorted = false

        var list = ArrayList<Movie>()
        for (movie in movies)
            list.add(movie)

        while (!sorted) {
            sorted = true
            for (i in 0 until list.size - 1) {
                if (list.get(i).getStartYear() < (list.get(i + 1).getStartYear())) {
                    temp = list.get(i)
                    list.set(i, list.get(i + 1))
                    list.set(i + 1, temp)
                    sorted = false
                }
            }
        }

        recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
        recyleViewTopMovies.adapter = MovieAdapter(list,
            {movieItem: Movie -> movieLayoutClicked(movieItem)})
    }

    private fun show10Top() {

        var temp: Movie
        var sorted = false

        var list = ArrayList<Movie>()
        for (movie in movies)
            list.add(movie)

        while (!sorted) {
            sorted = true
            for (i in 0 until list.size - 1) {
                if (list.get(i).getRank().toFloat() < (list.get(i + 1).getRank().toFloat())) {
                    temp = list.get(i)
                    list.set(i, list.get(i + 1))
                    list.set(i + 1, temp)
                    sorted = false
                }
            }
        }

        val list10 = ArrayList<Movie>()

        var size = 10
        if (movies.size < 10)
            size = movies.size

        for (i in 0 until size)
            list10.add(list.get(i))

        recyleViewTopMovies.layoutManager = LinearLayoutManager(context)
        recyleViewTopMovies.adapter = MovieAdapter(list10,
            {movieItem: Movie -> movieLayoutClicked(movieItem)})

    }

    fun search(name: String) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.filterName(name).enqueue(object : Callback<List<Movie>> {

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

}