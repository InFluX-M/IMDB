package com.example.imdbproj

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = FragmentMainBinding.inflate(layoutInflater)
        //binding.frameLayout.bottomNav.setSelectedItemId()

        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout,toolbar,
                                           R.string.open,R.string.close)
        toggle.syncState()

        val nav = findViewById<ButtonBarLayout>(R.id.bottomNav)

        //TODO("setNavigationSelectedItem")

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater = menuInflater

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
        return view
    }


}