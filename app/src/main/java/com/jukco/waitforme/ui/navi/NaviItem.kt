package com.jukco.waitforme.ui.navi

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.jukco.waitforme.R


const val POPS_MANAGEMENT = "POPS_MANAGEMENT"
const val POPS_LIST = "POPS_LIST"
const val WAIT_INFO = "WAIT_INFO"
const val BOOKMARK = "BOOKMARK"
const val MY_INFO = "MY_INFO"

sealed class NaviItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    object PopsManagement : NaviItem(R.string.navi_pops_management, Icons.Filled.Favorite, POPS_MANAGEMENT)
    object PopsList : NaviItem(R.string.navi_pops_list, Icons.Filled.Favorite, POPS_LIST)
    object WaitInfo : NaviItem(R.string.navi_wait_info, Icons.Filled.Favorite, WAIT_INFO)
    object Bookmark : NaviItem(R.string.navi_bookmark, Icons.Filled.Favorite, BOOKMARK)
    object MyInfo : NaviItem(R.string.navi_my_info, Icons.Filled.Favorite, MY_INFO)
}