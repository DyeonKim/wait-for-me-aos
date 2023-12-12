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
    startDescription: String = NaviItem.PopsList.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDescription
    ) {
        composable(NaviItem.PopsManagement.route) {
            PopsManagementPage()
        }
        composable(NaviItem.PopsList.route) {
            PopsListPage()
        }
        composable(NaviItem.WaitInfo.route) {
            WaitPage()
        }
        composable(NaviItem.Bookmark.route) {
            BookmarkPage()
        }
        composable(NaviItem.MyInfo.route) {
            MyPage()
        }
    }
}