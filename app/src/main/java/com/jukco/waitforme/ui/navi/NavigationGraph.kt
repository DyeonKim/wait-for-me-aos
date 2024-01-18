package com.jukco.waitforme.ui.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jukco.waitforme.ui.BookmarkScreen
import com.jukco.waitforme.ui.MyInfoScreen
import com.jukco.waitforme.ui.NoticeScreen
import com.jukco.waitforme.ui.PopsManagementScreen
import com.jukco.waitforme.ui.storedetail.PopupStoreScreen
import com.jukco.waitforme.ui.SearchScreen
import com.jukco.waitforme.ui.SignInScreen
import com.jukco.waitforme.ui.SignUpScreen
import com.jukco.waitforme.ui.WaitScreen
import com.jukco.waitforme.ui.components.BottomNaviItem
import com.jukco.waitforme.ui.poplist.PopsListScreen
import com.jukco.waitforme.ui.poplist.StoreListViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDescription: String = BottomNaviItem.PopsList.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDescription,
    ) {
        composable(Route.PopsManagement.name) {
            PopsManagementScreen(
                onPopItemClicked = {
                    /* TODO: id 전달 */
                    navController.navigate(Route.PopupStore.name)
                },
            )
        }
        composable(Route.PopsList.name) {
            val viewModel: StoreListViewModel = viewModel(factory = StoreListViewModel.Factory)

            PopsListScreen(
                uiState = viewModel.storeListUiState,
                refreshAction = viewModel::refresh,
                onNoticeButtonClicked = { navController.navigate(Route.Notice.name) },
                onSearchingClicked = { navController.navigate(Route.Searching.name) },
                onPopItemClicked = {
                    /* TODO: id 전달 */
                    navController.navigate(Route.PopupStore.name)
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
        composable(Route.PopupStore.name) {
            PopupStoreScreen(
                onCloseButtonClicked = { navController.popBackStack() },
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
        composable(Route.SignIn.name) {
            SignInScreen(
                onCancelButtonClicked = {
                    /*TODO: 모든 로그인 단계를 초기화하고 로그인 버튼 누른 페이지로 돌아가기 */
                    navController.popBackStack()
                },
            )
        }
    }
}
