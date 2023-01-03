package com.example.imdbproj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.databinding.FragmentSignupBinding
import java.util.regex.Pattern

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class signupFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSignupBinding
    private lateinit var user: User

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
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentSignupBinding.bind(view)

        binding.buttonCreatAcount.setOnClickListener {

            val username = binding.editTextUsername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val passwordRepeat = binding.editTextReEnterPassword.text.toString()

            if (isValidPassword(password) && isValidEmailAddress(email)) {

                if (password == (passwordRepeat)) {
                    val userNew = User(username, password, email)
                    val mainFragment = mainFragment()
                    MainActivity.USER_GLOBAl = userNew
                    replaceFragment(mainFragment)
                } else {
                    Toast.makeText(view.context, "passwords don't mach", Toast.LENGTH_LONG).show()
                }
            }

            else
                Toast.makeText(view.context,"invalid input",Toast.LENGTH_LONG).show()

        }

        binding.buttonGoSignIn.setOnClickListener {
            replaceFragment(loginFragment())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun isValidEmailAddress(email: String?): Boolean {
        val ePattern =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = Pattern.compile(ePattern)
        val m = p.matcher(email.toString())
        return m.matches()
    }

    private fun isValidPassword(password: String): Boolean {
        if(password.length < 8)
            return false
        return true
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = (this.context as AppCompatActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction.commit()
    }

}