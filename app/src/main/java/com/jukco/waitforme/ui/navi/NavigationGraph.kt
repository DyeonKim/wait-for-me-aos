package com.jukco.waitforme.ui.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jukco.waitforme.ui.BookmarkPage
import com.jukco.waitforme.ui.MyPage
import com.jukco.waitforme.ui.PopsListPage
import com.jukco.waitforme.ui.PopsManagementPage
import com.jukco.waitforme.ui.WaitPage

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
        composable(BottomNaviItem.PopsManagement.route) {
            PopsManagementPage()
        }
        composable(BottomNaviItem.PopsList.route) {
            PopsListPage()
        }
        composable(BottomNaviItem.WaitInfo.route) {
            WaitPage()
        }
        composable(BottomNaviItem.Bookmark.route) {
            BookmarkPage()
        }
        composable(BottomNaviItem.MyInfo.route) {
            MyPage()
        }
    }
}