package com.example.synchronyproject.Pages.ProfileScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.synchronyproject.R


@Composable
fun ProfileScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Profile Screen", modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), textAlign = TextAlign.Center, 
            fontSize = 20.sp,fontWeight = FontWeight.W800)
        ProfileHeader()
        Spacer(modifier = Modifier.height(16.dp))
        EducationSection()
        Spacer(modifier = Modifier.height(16.dp))
        WorkExperienceSection()
        Button(onClick = {
              navController.navigate("ListScreen")
        }, modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "Go To List Screen")
        }
    }
}

@Composable
fun ProfileHeader() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
//                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.surface)
                    .padding(8.dp)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "John Dick",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colors.onPrimary
                )
                Row() {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        modifier = Modifier.size(15.dp),
                        contentDescription = "location",
                        tint = Color.White
                    )
                    Text(
                        "Chennai",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 5.dp),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                Row() {
                    Icon(
                        imageVector = Icons.Default.Email,
                        modifier = Modifier.size(15.dp),
                        contentDescription = "location",
                        tint = Color.White
                    )
                    Text(
                        "john.dick@gmail.com",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 5.dp),
                        color = MaterialTheme.colors.onPrimary
                    )
                }

            }
        }
        
        Text(text = "About", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        
        Text(text = "Highly motivated Mobile Application Developer with over 9 plus years of experience in Android. Extensive knowledge and hands-on experience working on Smartphone Applications. Proven ability to interface with customers, understand product requirements, and design proof of concept prototypes.",
        modifier = Modifier.padding(top = 10.dp,end = 10.dp), fontSize = 15.sp)
    }
}

@Composable
fun EducationSection(vm : ProfileScreenVM = hiltViewModel()) {
    Column() {
        Section(
            title = "Education",
            icon = ImageVector.vectorResource(id = R.drawable.education),
        )
        ExpandableCard(
            title = "School", items = listOf(
                "Pachamuthu Matric Hr Sec School",
                "HSC",
                "Percentage : 93%"
            ),
            cardClick = {id->
                vm.expandedItem = if(vm.expandedItem == id) {
                    ""
                }
                else{
                    id
                }
            },
            isExpanded = vm.expandedItem == "School"
        )
        ExpandableCard(
            title = "College", items = listOf(
                "Bachelor's Degree in Computer Science",
                "University of Compose",
                "Graduated in 2021"
            ),
            cardClick = {id->
                vm.expandedItem = if(vm.expandedItem == id) {
                    ""
                }
                else{
                    id
                }
            },
            isExpanded = vm.expandedItem == "College"
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(title:String, isExpanded : Boolean,items: List<String>, cardClick : (id:String) -> Unit, responsibilities : List<String> = emptyList(), vm : ProfileScreenVM = hiltViewModel()){
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(2.dp,Color.LightGray, RoundedCornerShape(10.dp))
            .padding(5.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        elevation = 5.dp,
        onClick = {
            cardClick(title)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(0.2f)
                        .rotate(rotationState),
                    onClick = {
                        cardClick(title)
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            val enterTransition = remember {
                expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = tween(500)
                )
            }

            val exitTransition = remember {
                shrinkVertically(
                    shrinkTowards = Alignment.Top,
                    animationSpec = tween(500)
                )
            }
            AnimatedVisibility(
                visible = isExpanded, enter = enterTransition,
                exit = exitTransition
            ) {
                Column() {
                    items.forEach {
                        Column() {
                            AnimatedItem(text = it)
                        }
                    }
                    if (responsibilities.isNotEmpty()) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Responsibilities", fontWeight = FontWeight.Bold)
                            responsibilities.forEach() {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 16.dp, end = 8.dp)
                                            .size(8.dp)
                                            .background(Color.Black, shape = RectangleShape),
                                    )
                                    Text(it, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WorkExperienceSection(vm : ProfileScreenVM = hiltViewModel()) {
    Section(
        title = "Work Experience",
        icon = ImageVector.vectorResource(id = R.drawable.work),
    )

    ExpandableCard(title = "Xamarin Developer", items = listOf(
        "Awesome Company",
        "2021 - 2022",
        "Tech Stack : Xamarin.Forms, c#, MVVM, Asp.Net"
    ) ,
        responsibilities = listOf(
            "Developed the Major Feature Enhancement and delivered the project\n" +
             "which exceeded the Client Expectations.\n",
            "Managed Project timelines and deliverables and mentored the junior\n" +
                    "developers",
            "Engaged in the creation of technical designs to streamline the\n" +
            "development process for enhanced efficiency.",
            "Integrated multiple third-party healthcare systems together to bring a\n" +
                    "seamless unified app experience for the user.\n"
        ),
        cardClick = {id->
            vm.expandedItem = if(vm.expandedItem == id) {
                ""
            }
            else{
                id
            }
        },
    isExpanded = vm.expandedItem =="Xamarin Developer")

    ExpandableCard(title = "Android Developer", items = listOf(
        "Awesome Company",
        "2022 - Present",
        "Description :Involved in the Feature Enhancement",
        "Tech Stack : Kotlin, MVVM, Jetpack Compose, Android Studio"
    ),
        responsibilities = listOf(
            "Developed the major features in the application such as Login feature and Rides feature.",
            "Created a custom middleware using Node.js to seamlessly interface with\n"+
                    "Firebase, effectively harnessing its endpoints for frontend integration.\n"+
    "Acquired proficiency in Node.js to execute this project successfully.",
    "Responsible for setting up a staging and development environment to\n"+
            "effectively plan for testing and deployment and ensured high-quality standards in app development",
    "Collaborated with the testing team to analyze the test results and identify the core causes of failures.",
    "Conducted thorough testing to identify and rectify issues",
    "Collaborated with other team members to introduce the Push Notification feature in the application."
    ),
        cardClick = {id->
            vm.expandedItem = if(vm.expandedItem == id) {
                ""
            }
            else{
                id
            }
        },
        isExpanded = vm.expandedItem =="Android Developer")
}

@Composable
fun Section(title: String, icon: ImageVector) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun AnimatedItem(text: String) {
    var offsetY by remember { mutableStateOf(0.dp) }
    val bb = animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    ).value.dp
    LaunchedEffect(key1 = text){
        offsetY = bb
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offsetY)
            .padding(6.dp)
            .background(MaterialTheme.colors.surface)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(text = text)
    }
}

