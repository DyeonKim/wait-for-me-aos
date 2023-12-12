package com.jukco.waitforme.ui.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jukco.waitforme.ui.BookmarkScreen
import com.jukco.waitforme.ui.MyInfoScreen
import com.jukco.waitforme.ui.NoticeScreen
import com.jukco.waitforme.ui.PopsListScreen
import com.jukco.waitforme.ui.PopsManagementScreen
import com.jukco.waitforme.ui.PopupStoreScreen
import com.jukco.waitforme.ui.SearchScreen
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
            PopsManagementScreen(
                onPopItemClicked = {
                    /* TODO: id 전달 */
                    navController.navigate(Route.PopupStore.name)
                }
            )
        }
        composable(Route.PopsList.name) {
            PopsListScreen(
                onNoticeButtonClicked = { navController.navigate(Route.Notice.name) },
                onSearchingButtonClicked = { navController.navigate(Route.Searching.name) },
                onPopItemClicked = {
                    /* TODO: id 전달 */
                    navController.navigate(Route.PopupStore.name)
                }
            )
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
        composable(Route.Notice.name) {
            NoticeScreen(
                onCloseButtonClicked = { navController.popBackStack() }
            )
        }
        composable(Route.Searching.name) {
            SearchScreen (
                onCloseButtonClicked = { navController.popBackStack() }
            )
        }
        composable(Route.PopupStore.name) {
            PopupStoreScreen(
                onCloseButtonClicked = { navController.popBackStack() }
            )
        }
    }
}