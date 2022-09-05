package com.loki.glow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.loki.glow.ui.home.Home
import com.loki.glow.ui.theme.GlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlowTheme {
               Home()
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun AppBarPreview() {

    GlowTheme {
        Home()
    }
}