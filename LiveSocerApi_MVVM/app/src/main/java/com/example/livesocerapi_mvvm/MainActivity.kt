package com.example.livesocerapi_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.livesocerapi_mvvm.ui.theme.LiveSocerApi_MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveSocerApi_MVVMTheme {
             Column(modifier = Modifier
                 .padding(10.dp)
             ) {
                TopAppBar()
             }
            }
        }
    }
}

@Composable
fun TopAppBar(){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}){
            Icon(imageVector = Icons.Default.Refresh, contentDescription ="Refresh icon" )
        }
            Text(text = "Live Scores", style = MaterialTheme.typography.h4)

        IconButton(onClick = {}){
            Icon(imageVector =ImageVector.vectorResource(id = R.drawable.moodicon),contentDescription ="mood icon")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LiveSocerApi_MVVMTheme {
        TopAppBar()
    }
}