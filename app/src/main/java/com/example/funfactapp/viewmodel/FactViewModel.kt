package com.example.funfactapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funfactapp.MainActivity
import com.example.funfactapp.rest.RetroService
import com.example.funfactapp.rest.RetrofitInstance
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "FactViewModel"
class FactViewModel: ViewModel() {

    //live data
    private val _factList = MutableLiveData<String>()
    val factList: LiveData<String>
        get() = _factList

    var reqNumber = 0
    private fun updateReqNumber(input: Int){
        reqNumber = input
    }
    fun returnReqNum(): Int {
        return reqNumber
    }
    fun getRecyclerListObserver(): LiveData<String>{
        return factList
    }

    fun showToast(mainActivity: MainActivity) {
        Toast.makeText(mainActivity, "from VM: $reqNumber", Toast.LENGTH_SHORT).show()
    }

    fun checkInput(input: String) {
        if (input.isEmpty() || input.toInt() == 0){
            updateReqNumber(1)
        }
        else{
            updateReqNumber(input.toInt())
        }
    }

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
        Log.d(TAG, "coroutineExceptionHandler: error making api call")
    }
    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val retrofitInstance = RetrofitInstance.getRetroInstance()
                .create(RetroService::class.java)
            val response = retrofitInstance.getDataFromApi("num")
            _factList.postValue(response.toString())
        }
    }


}


