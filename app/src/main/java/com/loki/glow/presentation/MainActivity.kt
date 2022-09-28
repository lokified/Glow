package com.loki.glow.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.loki.glow.presentation.components.BottomNav
import com.loki.glow.presentation.components.HomeNavGraph
import com.loki.glow.presentation.theme.GlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
               ) { padding ->
                   HomeNavGraph(
                       modifier = Modifier.padding(padding),
                       navHostController = navController
                   )
               }
            }
        }
    }
}