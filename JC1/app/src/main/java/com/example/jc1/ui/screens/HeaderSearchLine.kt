package com.example.jc1.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.jc1.ui.theme.AppLightGray

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HeaderSearchLine() {
    var search by remember { mutableStateOf("") }
    OutlinedTextField(
        value = search, onValueChange = { search = it },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .padding(25.dp, 0.dp, 25.dp, 0.dp),
        placeholder = { Text(text = "Search...") },
        trailingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = AppLightGray,
            focusedBorderColor = AppLightGray,
            unfocusedBorderColor = AppLightGray
        ),
        shape = RoundedCornerShape(15.dp),
        singleLine = true
    )
}