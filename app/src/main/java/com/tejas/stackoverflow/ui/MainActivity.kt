package com.tejas.stackoverflow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.tejas.stackoverflow.R
import com.tejas.stackoverflow.databinding.ActivityMainBinding
import com.tejas.stackoverflow.StackOverflowApp
import com.tejas.stackoverflow.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    val viewBindingMainActivity get() = binding!!
    lateinit var navController: NavController


    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as StackOverflowApp).onCreate()

         mainViewModel = MainViewModel(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBindingMainActivity.root)

        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController
        navController.navigate(R.id.navigation_splash)
        setSupportActionBar(viewBindingMainActivity.toolbar)
        NavigationUI.setupWithNavController(viewBindingMainActivity.toolbar, navController)

    }
}