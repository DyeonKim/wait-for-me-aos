package com.jukco.waitforme.ui.navi

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.jukco.waitforme.R

sealed class BottomNaviItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    object PopsManagement : BottomNaviItem(R.string.navi_pops_management, Icons.Filled.Favorite, Route.PopsManagement.name)
    object PopsList : BottomNaviItem(R.string.navi_pops_list, Icons.Filled.Favorite, Route.PopsList.name)
    object Bookmark : BottomNaviItem(R.string.navi_bookmark, Icons.Filled.Favorite, Route.Bookmark.name)
    object WaitInfo : BottomNaviItem(R.string.navi_wait_info, Icons.Filled.Favorite, Route.WaitInfo.name)
    object MyInfo : BottomNaviItem(R.string.navi_my_info, Icons.Filled.Favorite, Route.MyInfo.name)
}
