package com.example.funfactapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funfactapp.adapter.RVAdapter
import com.example.funfactapp.databinding.FragmentListLayoutBinding
import com.example.funfactapp.model.RecyclerList
import com.example.funfactapp.viewmodel.FactViewModel

class FragmentList: Fragment() {
    private lateinit var binding: FragmentListLayoutBinding
    private lateinit var recyclerAdapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentListLayoutBinding.inflate(
            layoutInflater
        )
        //binding
        initViewModel(binding)
        initViewModel()
        return binding.root
    }

    private fun initViewModel(binding: FragmentListLayoutBinding){
        binding.rvList.layoutManager = LinearLayoutManager(activity)
        binding.rvList.adapter = RVAdapter()
    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this)[FactViewModel::class.java]
//        viewModel.getRecyclerListObserver().observe(this, Observer<RecyclerList>{
//            if(it != null){
//                recyclerAdapter.setUpdatedData(it.items)
//            }
//            else{
//                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
//            }
//        })
        //viewModel.makeApiCall()
    }

    companion object{
        @JvmStatic
        fun newInstance()=
            FragmentList().apply {

            }
    }
}