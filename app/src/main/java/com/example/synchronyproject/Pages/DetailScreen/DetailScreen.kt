package com.example.synchronyproject.Pages.DetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synchronyproject.Helpers.SingleTon
import com.example.synchronyproject.R

@Composable
fun DetailScreen(navController: NavHostController){
    val meal = SingleTon.meal
    val ingredients = listOf(meal?.strIngredient1,meal?.strIngredient2,meal?.strIngredient3,meal?.strIngredient4)
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="Left_Arrow",
            modifier = Modifier.clickable {
                navController.popBackStack()
            })
            Text("Detail Screen", modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center,fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        AsyncImage(
            model = meal?.strMealThumb,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(10.dp))
                .size(200.dp),
            placeholder = painterResource(id = R.drawable.staticfood),
            error = painterResource(id = R.drawable.staticfood),
            contentScale = ContentScale.Crop,
            contentDescription = "The design logo",
        )
        val category = buildAnnotatedString{
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append("Category: ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.W400)){
                append(meal?.strCategory?:"")
            }
        }
        
        Text(text = category, modifier = Modifier.padding(top = 10.dp))
        val link = "Click here"
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append("YouTube Link: ")
            }
            withStyle(
                style = SpanStyle(
                textDecoration = TextDecoration.Underline,
                    color = Color.Blue
            )){
                    append(link)
            addStringAnnotation("URL", annotation = meal?.strYoutube?:"", start = link.length,end = length)
            }

        }
        Text(text = annotatedString, modifier = Modifier.padding(top = 10.dp))
        Text("Instructions: ",  modifier = Modifier.padding(top = 10.dp),fontWeight = FontWeight.Bold)
            Text(text = meal?.strInstructions ?: "", maxLines = 5, overflow = TextOverflow.Ellipsis)
        Text("Ingredients: ", modifier = Modifier.padding(top = 10.dp), fontWeight = FontWeight.Bold)
        LazyColumn(){
            items(ingredients){it->
                Column( modifier = Modifier.padding(top = 10.dp)) {
                    if(it!=null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 8.dp)
                                    .size(8.dp)
                                    .background(Color.Black, shape = RectangleShape),
                            )
                            Text(it, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
        
    }
}