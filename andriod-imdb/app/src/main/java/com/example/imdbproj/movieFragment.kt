package com.example.imdbproj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.adaptor.MovieAdapter
import com.example.imdbproj.adaptor.MovieDetailAdapter
import com.example.imdbproj.adaptor.PersonAdapter
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.databinding.FragmentMainBinding
import com.example.imdbproj.databinding.FragmentMovieBinding
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class movieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var movie: Movie

    private lateinit var actors: List<Person>
    private lateinit var directores: List<Person>

    private lateinit var binding: FragmentMovieBinding


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
        recyclerViewActors.adapter = PersonAdapter(actors)


        val recyclerViewDirectors: RecyclerView = binding.recycleViewDirectors

        recyclerViewDirectors.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewDirectors.adapter = PersonAdapter(directores)


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment movieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            movieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}