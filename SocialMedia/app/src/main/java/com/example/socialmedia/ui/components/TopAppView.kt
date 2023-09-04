package com.example.socialmedia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fortest.ui.theme.RedColor
import com.example.fortest.ui.theme.VioletColor

@Preview
@Composable
fun TopAppView() {
    Row(Modifier.padding(10.dp,0.dp)) {
        Text(
            text = "Social Media",
            color = if (isSystemInDarkTheme()) Color.White else VioletColor,
            fontFamily = FontFamily.Cursive,
            fontSize = 21.sp,
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f)

        )
        IconButton(onClick = { /*TODO*/ }) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Icon(Icons.Filled.Send, contentDescription = "")
                Card(shape = RoundedCornerShape(50), modifier =Modifier.size(14.dp)) {
                    Text(
                        text = "+9", Modifier.background(RedColor),
                        color = Color.White,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }

}