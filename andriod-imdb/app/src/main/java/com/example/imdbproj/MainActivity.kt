package com.example.imdbproj

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.imdbproj.classes.mainClasses.Movie


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var toolbar: Toolbar

    lateinit var movies: ArrayList<Movie>

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

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {


        val view = super.onCreateView(parent, name, context, attrs)

        // getData()


        return view
    }



}