package com.example.synchronyproject.BOs

data class Record(
    val meals: List<Meal>
)

data class Meal(
    val strMeal : String,
    val strCategory : String,
    val strMealThumb: String,
    val strTags:String,
    val strIngredient1:String,
    val strIngredient2:String,
    val strIngredient6 : String,
    val strIngredient3:String,
    val strIngredient4:String,
    val strIngredient5:String,
    val strInstructions : String,
    val strYoutube :String
)