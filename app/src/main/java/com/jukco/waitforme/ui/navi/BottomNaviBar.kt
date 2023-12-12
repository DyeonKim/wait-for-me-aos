package com.jukco.waitforme.ui.navi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jukco.waitforme.ui.theme.Pink40
import com.jukco.waitforme.ui.theme.Pink80

@Composable
fun BottomNaviBar(navController: NavHostController) {
    val navBAckStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBAckStackEntry?.destination

    val items = listOf<BottomNaviItem>(
        BottomNaviItem.PopsManagement,
        BottomNaviItem.PopsList,
        BottomNaviItem.WaitInfo,
        BottomNaviItem.Bookmark,
        BottomNaviItem.MyInfo
    )

    when (currentDestination?.route) {
        BottomNaviItem.PopsManagement.route -> BottomNaviTool(navController, currentDestination, items)
        BottomNaviItem.PopsList.route -> BottomNaviTool(navController, currentDestination, items)
        BottomNaviItem.WaitInfo.route -> BottomNaviTool(navController, currentDestination, items)
        BottomNaviItem.Bookmark.route -> BottomNaviTool(navController, currentDestination, items)
        BottomNaviItem.MyInfo.route -> BottomNaviTool(navController, currentDestination, items)
        else -> Unit
    }
}

@Composable
fun BottomNaviTool(
    navController: NavHostController,
    currentDestination: NavDestination?,
    items: List<BottomNaviItem>
) {
    BottomNavigation (
        backgroundColor = Pink80,
        modifier = Modifier
            .fillMaxWidth()
    ){
        items.forEach { naviItem ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == naviItem.route } == true,
                onClick = {
                    navController.navigate(naviItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Pink40,
                unselectedContentColor = Color.Gray,
                icon = { Icon(naviItem.icon, contentDescription = null) },
                label = {
                    Text(
                        text = stringResource(naviItem.title),
                        style = MaterialTheme.typography.labelMedium,
                        softWrap = false
                    )
                }
            )
        }
    }
}