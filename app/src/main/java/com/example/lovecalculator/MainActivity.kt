package com.example.lovecalculator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var pref:Pref
    private lateinit    var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref.saveState()
        if (pref.isShown()){
            navController.navigate(R.id.onBoardingFragment)
        }
    }
}