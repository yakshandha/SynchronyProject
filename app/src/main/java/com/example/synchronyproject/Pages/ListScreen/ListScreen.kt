package com.example.synchronyproject.Pages.ListScreen

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synchronyproject.Helpers.SingleTon
import com.example.synchronyproject.R

@Composable
fun ListScreen(navController:NavHostController, vm: ListScreenVM = hiltViewModel()){
    LaunchedEffect(key1 = true ){
        vm.getAllData()
    }

    var showShimmer by remember { mutableStateOf(true) }
    val tempList = vm.masterList.observeAsState()
    Column() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Text(
                    text = "List Of Meals",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(tempList.value ?: emptyList()) {
                    Card(
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                SingleTon.meal = it
                                navController.navigate("DetailScreen")
                            }) {
                        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
                            AsyncImage(
                                model = it.strMealThumb,
                                placeholder = painterResource(id = R.drawable.staticfood),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        shimmerBrush(
                                            targetValue = 1300f,
                                            showShimmer = showShimmer
                                        )
                                    ),
                                contentScale = ContentScale.Crop,
                                contentDescription = "The delasign logo",
                                onSuccess = { showShimmer = false }
                            )
                            Text(
                                it.strMeal,
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.W800
                            )
                        }
                    }
                }
            }
        }
        if(vm.showList) {
            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Blue,
                    strokeWidth = 5.dp
                )
            }
        }
    }
}

@Composable
fun shimmerBrush(showShimmer: Boolean = true,targetValue:Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            )
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent,Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}