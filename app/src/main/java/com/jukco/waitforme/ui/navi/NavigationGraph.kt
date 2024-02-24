package com.jukco.waitforme.ui.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.jukco.waitforme.ui.BookmarkScreen
import com.jukco.waitforme.ui.MyInfoScreen
import com.jukco.waitforme.ui.NoticeScreen
import com.jukco.waitforme.ui.StoreManagementScreen
import com.jukco.waitforme.ui.SearchScreen
import com.jukco.waitforme.ui.sign.SignInScreen
import com.jukco.waitforme.ui.sign.SignUpScreen
import com.jukco.waitforme.ui.WaitScreen
import com.jukco.waitforme.ui.components.BottomNaviItem
import com.jukco.waitforme.ui.store_detail.PopupStoreScreen
import com.jukco.waitforme.ui.store_list.StoreListScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDescription: String = BottomNaviItem.StoreList.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDescription,
    ) {
        composable(Route.StoreManagement.name) {
            StoreManagementScreen(
                onPopItemClicked = {
                    /* TODO: id 전달 */
                    navController.navigate(Route.PopupStore.name)
                },
            )
        }
        composable(Route.StoreList.name) {
            StoreListScreen(
                onNoticeButtonClicked = { navController.navigate(Route.Notice.name) },
                onSearchingClicked = { navController.navigate(Route.Searching.name) },
                onPopItemClicked = {
                    navController.navigate("${Route.PopupStore.name}/$it")
                },
            )
        }
        composable(Route.WaitInfo.name) {
            WaitScreen()
        }
        composable(Route.Bookmark.name) {
            BookmarkScreen()
        }
        composable(Route.MyInfo.name) {
            MyInfoScreen(
                onSignInButtonClicked = { navController.navigate(Route.SignIn.name) },
                onSignUpButtonClicked = { navController.navigate(Route.SignUp.name) },
            )
        }
        composable(Route.Notice.name) {
            NoticeScreen(
                onCloseButtonClicked = { navController.popBackStack() },
            )
        }
        composable(Route.Searching.name) {
            SearchScreen(
                onCloseButtonClicked = { navController.popBackStack() },
            )
        }
        composable(
            route = "${Route.PopupStore.name}/{storeId}",
            arguments = listOf(navArgument("storeId") { type = NavType.IntType }),
        ) {
            PopupStoreScreen(
                onBackButtonClicked = { navController.popBackStack() },
            )
        }

        navigation(startDestination = Route.SignIn.name, route = Route.SignProgress.name) {
            composable(Route.SignIn.name) {
                SignInScreen(
                    onSignInClicked = {},
                    onSignUpClicked = { navController.navigate(Route.SignUp.name) },
                    goMain = {
                        navController.navigate(Route.StoreList.name) {
                            popUpTo(Route.SignProgress.name) { inclusive = true }
                        }
                    },
                )
            }
            composable(Route.SignUp.name) {
                SignUpScreen(
                    onCancelButtonClicked = {
                        /*TODO: 모든 회원가입 단계를 초기화하고 회원가입 버튼 누른 페이지로 돌아가기 */
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
