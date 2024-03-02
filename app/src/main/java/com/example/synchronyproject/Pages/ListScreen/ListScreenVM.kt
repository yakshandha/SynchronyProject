package com.example.synchronyproject.Pages.ListScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.Service.ApiHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenVM @Inject constructor(
    private val apiHelper : ApiHelper
): ListScreenModel() {

    fun getAllData(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = apiHelper.getData()
                if(response.isSuccessful && response.body()!=null){
                    showList = false
                    masterList.postValue(response.body()?.meals)
                    Log.d("listtt",masterList.value.toString())
                    Log.d("responsee",response.body()?.meals.toString())
                }
            }
            catch(ex:Exception){
                Log.d("Exception",ex.toString())
            }
        }
    }
}