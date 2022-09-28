package com.loki.glow.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@Composable
fun TransactScreen(
    modifier: Modifier = Modifier,
    cryptoName: String,
    buttonColor: Color,
    actionText: String
) {

    Scaffold(topBar = { TransactAppBar(cryptoName = cryptoName) }) { padding->

        Column(modifier = modifier) {

            AmountSection()

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp, horizontal = 16.dp)
                    .height(50.dp)
            ) {

                Text(text = actionText, color = Color.White)
            }
        }
    }
}

@Composable
fun TransactAppBar(cryptoName: String) {

    TopAppBar(
        modifier = Modifier.height(70.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.clickable {  },
                shape = RoundedCornerShape(30.dp)
            ) {

                Image(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = cryptoName,
                style = MaterialTheme.typography.h1
            )
        }
    }
}

@Composable
fun AmountSection(
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier.padding(16.dp)) {

        var amount by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }

        val color = Color(0xFF518F88)

        Text(text = "Amount")

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = color,
                focusedBorderColor = color
            )
        )

        Text(text = "Price", modifier = Modifier.padding(top = 16.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = color,
                focusedBorderColor = color
            )
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoinIcon(coinUrl: String) {

    val url = coinUrl.replace(".svg", ".png")
    val painter = rememberImagePainter(
        data = url,
        builder = {

        }
    )
    //val painterState = painter.state
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
    )
//    if (painterState is ImagePainter.State.Loading) {
//        CircularProgressIndicator()
//    }
}
