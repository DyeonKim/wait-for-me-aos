package com.jukco.waitforme.ui.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jukco.waitforme.ui.BookmarkScreen
import com.jukco.waitforme.ui.MyInfoScreen
import com.jukco.waitforme.ui.PopsListScreen
import com.jukco.waitforme.ui.PopsManagementScreen
import com.jukco.waitforme.ui.WaitScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDescription: String = BottomNaviItem.PopsList.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDescription
    ) {
        composable(Route.PopsManagement.name) {
            PopsManagementScreen()
        }
        composable(Route.PopsList.name) {
            PopsListScreen()
        }
        composable(Route.WaitInfo.name) {
            WaitScreen()
        }
        composable(Route.Bookmark.name) {
            BookmarkScreen()
        }
        composable(Route.MyInfo.name) {
            MyInfoScreen()
        }
    }
}