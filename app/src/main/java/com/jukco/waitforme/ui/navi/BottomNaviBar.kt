package com.jukco.waitforme.ui.navi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

val BOTTOM_NAVI_ROUTE = listOf<BottomNaviItem>(
    BottomNaviItem.PopsManagement,
    BottomNaviItem.PopsList,
    BottomNaviItem.Bookmark,
    BottomNaviItem.WaitInfo,
    BottomNaviItem.MyInfo,
)

@Composable
fun BottomNaviBar(navController: NavHostController) {
    val navBAckStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBAckStackEntry?.destination

    when (currentDestination?.route) {
        BottomNaviItem.PopsManagement.route -> BottomNavi(navController, currentDestination)
        BottomNaviItem.PopsList.route -> BottomNavi(navController, currentDestination)
        BottomNaviItem.WaitInfo.route -> BottomNavi(navController, currentDestination)
        BottomNaviItem.Bookmark.route -> BottomNavi(navController, currentDestination)
        BottomNaviItem.MyInfo.route -> BottomNavi(navController, currentDestination)
        else -> Unit
    }
}

@Composable
fun BottomNavi(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        BOTTOM_NAVI_ROUTE.forEach { bottomNaviItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == bottomNaviItem.route } == true,
                onClick = {
                    navController.navigate(bottomNaviItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = bottomNaviItem.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottomNaviItem.title),
                        style = MaterialTheme.typography.labelMedium,
                        softWrap = false,
                    )
                },
            )
        }
    }
}

@Preview
@Composable
private fun BottomNaviBarPreview() {
    BottomNavi(
        navController = rememberNavController(),
        currentDestination = null,
    )
}
