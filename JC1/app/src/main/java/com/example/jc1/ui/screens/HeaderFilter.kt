package com.example.jc1.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.jc1.ui.theme.AppDarkGray
import com.example.jc1.ui.theme.AppLightGray
import com.example.jc1.ui.theme.AppRed
import com.example.jc1.ui.theme.AppWhite

@Composable
fun HeaderFilter() {
    val filterList = listOf<String>("All", "Cruise", "Ship", "Car", "Train", "Flight")
    var selectItem: Int by remember { mutableStateOf(0) }
    LazyRow(Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)) {
        items(filterList.size) { index ->
            TextButton(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                onClick = { selectItem = index },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (selectItem == index) AppRed else AppLightGray,
                    contentColor = if (selectItem == index) AppWhite else AppDarkGray,
                )
            ) {
                Text(text = filterList[index])
            }
        }

    }
}