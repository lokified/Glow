package com.loki.glow.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(modifier: Modifier = Modifier, title: String) {

    TopAppBar(
        modifier = modifier.height(70.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {

//            Surface(
//                color = MaterialTheme.colors.surface,
//                modifier = Modifier.clickable {  },
//                shape = RoundedCornerShape(30.dp)
//            ) {
//
//                Image(
//                    imageVector = Icons.Default.KeyboardArrowLeft,
//                    contentDescription = null,
//                )
//            }

            Text(
                text = title,
                style = MaterialTheme.typography.h2
            )
        }
    }
}