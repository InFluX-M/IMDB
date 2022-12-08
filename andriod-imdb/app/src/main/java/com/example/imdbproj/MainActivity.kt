package com.example.imdbproj

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import com.example.imdbproj.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.jvm.javaClass


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var toolbar: Toolbar

    lateinit var users: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout,toolbar,
                                           R.string.open,R.string.close)
        toggle.syncState()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.optionExit -> finishAffinity()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {

        val view = super.onCreateView(parent, name, context, attrs)

        // getData()

        return view
    }



    fun getData() {

        val apiClient = ApiClient()

        val service: ApiService = apiClient.getClient().create(ApiService::class.java)

        val call: Call<List<User?>?>? = service.getAllUsers()


        call?.enqueue(object : Callback<List<User?>?> {
            override fun onResponse(call: Call<List<User?>?>, response: Response<List<User?>?>) {
                users = response.body() as List<User>
            }

            override fun onFailure(call: Call<List<User?>?>, t: Throwable) {
                Toast.makeText(baseContext, "error getting info" , Toast.LENGTH_SHORT).show()
            }
        })

    }


}