package com.loki.glow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.loki.glow.ui.components.BottomNav
import com.loki.glow.ui.components.Navigation
import com.loki.glow.ui.home.Home
import com.loki.glow.ui.theme.GlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlowTheme {

                val navController = rememberNavController()

               Scaffold(
                   bottomBar = {
                       BottomNav(
                           navController = navController,
                           onItemClick = { navController.navigate(it.route) }
                       )
                   }
               ) {
                   Navigation(navHostController = navController)
               }
            }
        }
    }
}