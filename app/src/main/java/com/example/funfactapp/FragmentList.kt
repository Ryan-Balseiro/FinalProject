package com.example.funfactapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funfactapp.adapter.RVAdapter
import com.example.funfactapp.databinding.FragmentListLayoutBinding
import com.example.funfactapp.viewmodel.FactViewModel

class FragmentList: Fragment() {
    private lateinit var binding: FragmentListLayoutBinding
    private lateinit var recyclerAdapter: RVAdapter
    lateinit var viewModel:FactViewModel
//    by lazy{
//        ViewModelProvider(this)[FactViewModel::class.java]
//    }

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
        //initViewModel()
        return binding.root
    }

    private fun initViewModel(binding: FragmentListLayoutBinding){
//        binding.rvList.layoutManager = LinearLayoutManager(activity)
//        binding.rvList.adapter = RVAdapter()
        displayFact()
    }
    fun displayFact(){
        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            binding.tvFactStatic.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.factList.observe(viewLifecycleOwner){
            binding.tvFactStatic.text = it
        }
        //binding.tvFactStatic.text = viewModel.factList.toString()
    }

//    private fun initViewModel(){
//        val viewModel = ViewModelProvider(this)[FactViewModel::class.java]
////        viewModel.getRecyclerListObserver().observe(this, Observer<RecyclerList>{
////            if(it != null){
////                recyclerAdapter.setUpdatedData(it.items)
////            }
////            else{
////                Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
////            }
////        })
////        viewModel.makeApiCall()
//    }

    companion object{
        @JvmStatic
        fun newInstance(viewModel: FactViewModel)=
            FragmentList().apply {
                this.viewModel = viewModel

            }
    }
}