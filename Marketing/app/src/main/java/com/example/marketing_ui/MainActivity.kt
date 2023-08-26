package com.example.marketing_ui

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketing_ui.ui.theme.Marketing_UiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Marketing_UiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    val processList = listOf(
        "Verification process with team",
        "Launch process with collegues"
    )
    val flows = listOf("document verification", "newbie onboarding")
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(text = "welcom back", fontSize = 16.sp, color = Color.Gray)
                    Text(text = "jacob Ebrahimi", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "image profile",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(BorderStroke(3.dp, color = Color.Black))
                            .background(color = Color.Black)
                            .align(alignment = Alignment.BottomStart)
                    )
                    {
                        Text(
                            text = "2",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(3.dp)
                        )
                    }

                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = colorResource(id = R.color.wheat)
            ) {
                BottomNavigationItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "image icon"
                        )
                    }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AccountBox,
                            contentDescription = "image icon"
                        )
                    }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "image icon"
                        )
                    }
                )


            }
        },
        backgroundColor = colorResource(id = R.color.wheat)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 16.dp)
        ) {

            // ========> Search Bar
            SearchBar()

            // ========> Spacer
            Spacer(modifier = Modifier.padding(30.dp))

            // ========> Header
            Text(
                text = "Task-based \nExplanation Process ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, bottom = 20.dp)
            )
            // ========> Recycler View
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    LeadingRowItem()
                }
                items(processList) { process ->
                    ProcessItem(process)

                }
            }

            //Bottom Section Or Flow Section
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                //Heading Section
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Flow List",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "ArrowDropDown Icon",
                            tint = Color.Black
                        )
                    }
                }

                //Sea All text
                Text(text = "See all", color = Color.Gray, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                itemsIndexed(flows) { index, flow ->
                    FlowItem(flow)
                    if (index<flows.lastIndex){
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp)
                        )
                    }
                }

            }

        }
    }

}

@Composable
fun FlowItem(flow: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //left column
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = flow.capitalizeWords(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text(text = "3 mins ago", color = Color.Gray, fontSize = 15.sp)
        }

        //Right Section
        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(CircleShape)
                .background(
                    colorResource(id = R.color.green)
                )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Icon",
                tint = Color.Black,
                modifier = Modifier.padding(7.dp)
            )

        }


    }
}

@Composable
fun ProcessItem(process: String) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
    )
    {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(colorResource(id = R.color.wheat)),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "Review",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = process, fontSize = 18.sp, color = Color.Black)


        }
    }
}

@Composable
fun LeadingRowItem() {
    Box(
        modifier = Modifier
            .width(190.dp)
            .padding(start = 16.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Gray,
                    cornerRadius = CornerRadius(8.dp.toPx()),
                    style = Stroke(
                        width = 2f,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(10f, 10f), 0f
                        )
                    )
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.textButtonColors(backgroundColor = colorResource(id = R.color.green)),
                modifier = Modifier.clip(RoundedCornerShape(15.dp))
            ) {
                Text(
                    text = "Add Task",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Creating for branding", fontSize = 18.sp, color = Color.Black)

        }
    }
}


@Composable
fun SearchBar() {
    val textstate = remember {
        mutableStateOf(TextFieldValue())
    }
    TextField(
        value = textstate.value,
        onValueChange = { textstate.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp)),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(text = "Try to find ... ", color = Color.Gray, fontSize = 16.sp)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Marketing_UiTheme {
        Content()
    }
}

fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")
