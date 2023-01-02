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
import androidx.appcompat.widget.ButtonBarLayout
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = FragmentMainBinding.inflate(layoutInflater)

        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout,toolbar,
                                           R.string.open,R.string.close)
        toggle.syncState()


        binding.user = User("user1","password1","email1")

        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationMenu.selectedItemId = R.id.itemHome
        bottomNavigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.itemHome ->{
                    val mainFragment = mainFragment()
                    mainFragment.user = binding.user as User
                    replaceFragment(mainFragment)}
                R.id.itemAccount -> replaceFragment(loginFragment())
                R.id.itemFavorite -> replaceFragment(favoriteListFragment(binding.user as User))
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction.commit()
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