package com.jukco.waitforme.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jukco.waitforme.ui.navi.BottomNaviBar
import com.jukco.waitforme.ui.navi.BottomNaviItem
import com.jukco.waitforme.ui.navi.NavigationGraph
import com.jukco.waitforme.ui.theme.WaitForMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaitForMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MainView() {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = { BottomNaviBar(navController) }
    ){ innerPadding ->
        NavigationGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDescription = BottomNaviItem.PopsList.route
        )
    }
}