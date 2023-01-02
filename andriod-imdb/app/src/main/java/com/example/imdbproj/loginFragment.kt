package com.example.imdbproj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.databinding.FragmentLoginBinding
import com.example.imdbproj.databinding.FragmentMainBinding
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [loginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNREACHABLE_CODE")
class loginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentLoginBinding

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
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentLoginBinding.bind(view)

        binding.buttonLogin.setOnClickListener{

            binding.buttonCreateAccount.setOnClickListener {
                Log.i("why","base")
                Toast.makeText(this.context,"create",Toast.LENGTH_LONG).show()
            }

            val username = binding.editTextUserName
            val password = binding.editTextTextPassword

            //getUser(username.toString(), password.toString(), view)

        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun getUser(username: String, password: String, view: View) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getUser(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {


                Navigation.findNavController(view)
                    .navigate(loginFragmentDirections.actionLoginFragmentToMainFragment()
                        .setUser(binding.user))
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}