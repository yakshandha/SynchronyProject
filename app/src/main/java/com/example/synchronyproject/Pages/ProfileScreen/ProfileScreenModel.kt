package com.example.synchronyproject.Pages.ProfileScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

open class ProfileScreenModel : ViewModel(){
    var expandedState by mutableStateOf(false)
    var expandedItem by mutableStateOf("")

}