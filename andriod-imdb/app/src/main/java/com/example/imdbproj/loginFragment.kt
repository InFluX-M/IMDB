package com.example.imdbproj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.databinding.FragmentLoginBinding
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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
            val username = binding.editTextUserName
            val password = binding.editTextTextPassword

            getUser(username.toString(), password.toString(), view)

        }

        binding.buttonCreateAccount.setOnClickListener {
            replaceFragment(signupFragment())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = (this.context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction.commit()
    }


    private fun getUser(username: String, password: String, view: View) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        val params: MutableMap<String, String> = HashMap()
        params["Username"] = username
        params["Password"] = password


        apiService.findUser(params).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body() != null) {
                    var mainFragment = mainFragment()
                    MainActivity.USER_GLOBAl = response.body() as User
                    replaceFragment(mainFragment)
                }else
                    Toast.makeText(view.context, "user: not found",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}