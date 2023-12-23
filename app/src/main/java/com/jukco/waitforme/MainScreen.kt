package com.jukco.waitforme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jukco.waitforme.ui.navi.BottomNaviBar
import com.jukco.waitforme.ui.navi.BottomNaviItem
import com.jukco.waitforme.ui.navi.NavigationGraph

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
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