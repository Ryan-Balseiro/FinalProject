package com.example.funfactapp.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funfactapp.MainActivity
import com.example.funfactapp.model.DataItem
import com.example.funfactapp.rest.RetroService
import com.example.funfactapp.rest.RetroService2
import com.example.funfactapp.rest.RetrofitInstance
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "FactViewModel"
class FactViewModel: ViewModel() {

    //
    var fact = ""

    //live data
    private val _factList = MutableLiveData<String>()
    val factList: LiveData<String>
        get() = _factList

    var reqNumber = 0
    private fun updateReqNumber(input: Int){
        reqNumber = input
    }
    fun getRecyclerListObserver(): LiveData<String>{
        return factList
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
        Log.d(TAG, "onr coroutineExceptionHandler: error making api call")
    }
    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
//            val retrofitInstance = RetrofitInstance.getRetroInstance()
//                .create(RetroService::class.java)
//            val response = retrofitInstance.getDataFromApi(reqNumber.toString())
//            _factList.postValue(response.toString())
            /** this one works, but not in a coroutine
            RetroService2.getRetroInstance2().getDataFromApi2()
                .enqueue(object : Callback<List<String>> {
                    override fun onFailure(call: Call<List<String>>, t: Throwable) {
                        Log.d(TAG, "onFailure: fail to get data")
                        Log.d(TAG, "onFailure: ${t.message}")
                    }
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            Log.d(TAG, "onResponse: $data")
                        }
                    }
                })
            **/
        }
    }
    fun makeApiCall2() {
        RetroService2.getRetroInstance2().getDataFromApi4()
            .enqueue(object : Callback<DataItem> {
                override fun onFailure(call: Call<DataItem>, t: Throwable) {
                    Log.d(TAG, "onFailure: fail to get data")
                    Log.d(TAG, "onFailure: ${t.message}")
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<DataItem>, response: Response<DataItem>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        Log.d(TAG, "onResponse: $data")
                        _factList.postValue(data.toString())
                        fact = data.toString()
                    }
                }
            })
    }


}


