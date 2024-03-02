package com.example.synchronyproject.Pages.ListScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.synchronyproject.BOs.Meal

open class ListScreenModel : ViewModel() {
    val masterList : MutableLiveData<List<Meal>> = MutableLiveData<List<Meal>>()
    var showList by mutableStateOf(true)
}