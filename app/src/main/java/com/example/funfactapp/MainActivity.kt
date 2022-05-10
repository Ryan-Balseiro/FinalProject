package com.example.funfactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.funfactapp.databinding.ActivityMainBinding
import com.example.funfactapp.viewmodel.FactViewModel

private const val TAG = "MainActivity"
//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[FactViewModel::class.java]

        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        binding.btnGetFacts.setOnClickListener{
            viewModel.checkInput(binding.etInput.text.toString())
            binding.etInput.setText(viewModel.reqNumber.toString())
            //viewModel.makeApiCall()
            viewModel.makeApiCall2()
        }
        setupFragment()
    }

    private fun setupFragment() {
        val fragment = FragmentList.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}