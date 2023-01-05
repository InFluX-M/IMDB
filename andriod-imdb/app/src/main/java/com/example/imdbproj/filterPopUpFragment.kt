package com.example.imdbproj

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.adaptor.PersonAdapter
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import kotlinx.android.synthetic.main.fragment_filter_pop_up.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class filterPopUpFragment(val updateMovies: (ArrayList<Movie>) -> Unit) : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var moviesFilters : ArrayList<ArrayList<Movie>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        spinnerGenre(view)


        view.findViewById<Button>(R.id.buttonFilterMovies).setOnClickListener {

            val actorName = view.findViewById<EditText>(R.id.editTextActor).text.toString()
            if (actorName.isNotEmpty()) {
                filterByActor(actorName)
                Log.i("afAAA" , moviesFilters.size.toString())

            }

            Log.i("af" , moviesFilters.size.toString())

            updateMovies(filterAll())
            dismiss()
        }


        view.findViewById<Button>(R.id.buttonClose).setOnClickListener {
            dismiss()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun spinnerGenre(view: View) {

        val genres = arrayOf("All", "Comedy", "Drama", "Family", "Action", "Romance", "Adventure", "Sci-Fi",
            "Crime", "Mystery", "Thriller", "War", "Musical", "Film-Noir", "Fantasy", "Biography",
            "Western", "Horror", "Sport", "Animation", "History")

        val genresSpinner = view.spinnerGeners
        val genresAdapter = ArrayAdapter<String> (view.context, android.R.layout.simple_spinner_dropdown_item, genres)
        genresSpinner.adapter = genresAdapter

        genresSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val genre = genres[position]
                if (!genre.equals("All"))
                    filterByGenre(genre)
                else
                    getAllMovies()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        Log.i("b" , moviesFilters.size.toString())

    }

    private fun filterAll(): ArrayList<Movie> {

        val list = ArrayList<Movie>()

        getAllMovies()

        var add: Boolean = true

        for (movie in moviesFilters.get(moviesFilters.size - 1)) {

            add = true

            for (i in 0 .. moviesFilters.size - 1) {

                add = false

                for (movie2 in moviesFilters.get(i)) {
                    if (movie.getTitleId() == movie2.getTitleId()) {
                        add = true

                    }
                }

            }

            if (add) {
                var repeated: Boolean = false
                for (m in list) {
                    if (m.getTitleId().equals(movie.getTitleId()))
                        repeated = true
                }
                if (!repeated)
                    list.add(movie)
            }

        }

        return list
    }


    private fun getAllMovies() {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getMovies().enqueue(object : Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val list = response.body() as ArrayList<Movie>
                moviesFilters.add(ArrayList())
                val array = moviesFilters.get(moviesFilters.size - 1)
                for (movie in list)
                    array.add(movie)
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("TAGGG", t.message.toString())
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun filterByGenre(genre: String) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.filterGenre(genre).enqueue(object : Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val list = response.body() as ArrayList<Movie>
                moviesFilters.add(ArrayList())
                val array = moviesFilters.get(moviesFilters.size - 1)
                for (movie in list)
                    array.add(movie)
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun filterByActor(name: String) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getMovies().enqueue(object : Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val list = response.body() as ArrayList<Movie>
                moviesFilters.add(ArrayList())
                val array = moviesFilters.get(moviesFilters.size - 1)

                for (movie in list)
                    getActors(movie, name, array)
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("TAGGG", t.message.toString())
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }

    private fun getActors(movie: Movie, name: String, array: ArrayList<Movie>) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getActors(movie.getTitleId()).enqueue(object : Callback<List<com.example.imdbproj.classes.mainClasses.Person>> {
            override fun onResponse(
                call: Call<List<com.example.imdbproj.classes.mainClasses.Person>>,
                response: Response<List<com.example.imdbproj.classes.mainClasses.Person>>
            ) {
                val actors = response.body() as ArrayList<com.example.imdbproj.classes.mainClasses.Person>

                for (person in actors)
                    if (person.getName().equals(name))
                        array.add(movie)
            }

            override fun onFailure(
                call: Call<List<com.example.imdbproj.classes.mainClasses.Person>>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })

    }

}