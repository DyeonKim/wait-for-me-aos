package com.jukco.waitforme.ui.navi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.jukco.waitforme.ui.sign.sign_in.SignInScreen
import com.jukco.waitforme.ui.sign.sign_up.InputCredentialsScreen
import com.jukco.waitforme.ui.WaitScreen
import com.jukco.waitforme.ui.components.BottomNaviItem
import com.jukco.waitforme.ui.sign.SignViewModel
import com.jukco.waitforme.ui.sign.sign_up.CompleteScreen
import com.jukco.waitforme.ui.sign.sign_up.InputNameScreen
import com.jukco.waitforme.ui.sign.sign_up.SelectCustomerOwnerScreen
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
                onSignUpButtonClicked = { navController.navigate(Route.SignUpInputCredentials.name) },
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
            composable(Route.SignIn.name) { navBackStackEntry ->
                val signBackStackEntry = remember(navBackStackEntry) { navController.getBackStackEntry(Route.SignProgress.name) }
                val signViewModel: SignViewModel = viewModel(viewModelStoreOwner = signBackStackEntry, factory = SignViewModel.Factory)

                SignInScreen(
                    form = signViewModel.signInform,
                    isLoading = signViewModel.isLoading,
                    socialSignIn = signViewModel::getSocialSign,
                    onEvent = signViewModel::onSignInEvent,
                    goMain = {
                        navController.navigate(Route.StoreList.name) {
                            popUpTo(Route.SignProgress.name) { inclusive = true }
                        }
                    },
                    goSignUp = { navController.navigate(Route.SignUpInputCredentials.name) },
                )
            }
            composable(Route.SignUpInputCredentials.name) { navBackStackEntry ->
                val signBackStackEntry = remember(navBackStackEntry) { navController.getBackStackEntry(Route.SignProgress.name) }
                val signViewModel: SignViewModel = viewModel(viewModelStoreOwner = signBackStackEntry, factory = SignViewModel.Factory)

                InputCredentialsScreen(
                    onNextButtonClicked = { navController.navigate(Route.SignUpInputName.name) },
                    dto = signViewModel.signUpDto,
                    form = signViewModel.signUpForm,
                    errorMessage = signViewModel.errorMessage,
                    onEvent = signViewModel::onSignUpEvent,
                )
            }
            composable(Route.SignUpInputName.name) { navBackStackEntry ->
                val signBackStackEntry = remember(navBackStackEntry) { navController.getBackStackEntry(Route.SignProgress.name) }
                val signViewModel: SignViewModel = viewModel(viewModelStoreOwner = signBackStackEntry, factory = SignViewModel.Factory)

                InputNameScreen(
                    onNextButtonClicked = { navController.navigate(Route.SignUpSelectCustomerOwner.name) },
                    dto = signViewModel.signUpDto,
                    form = signViewModel.signUpForm,
                    errorMessage = signViewModel.errorMessage,
                    isLoading = signViewModel.isLoading,
                    onEvent = signViewModel::onSignUpEvent,
                )
            }
            composable(Route.SignUpSelectCustomerOwner.name) { navBackStackEntry ->
                val signBackStackEntry = remember(navBackStackEntry) { navController.getBackStackEntry(Route.SignProgress.name) }
                val signViewModel: SignViewModel = viewModel(viewModelStoreOwner = signBackStackEntry, factory = SignViewModel.Factory)

                SelectCustomerOwnerScreen(
                    onSignUpButtonClicked = {
                        navController.navigate(Route.SignUpComplete.name) {
                            popUpTo(Route.SignUpInputCredentials.name) { inclusive = true }
                        }
                    },
                    dto = signViewModel.signUpDto,
                    customerOwner = SignViewModel.CUSTOMER_OWNER,
                    isLoading = signViewModel.isLoading,
                    onEvent = signViewModel::onSignUpEvent,
                )
            }
            composable(Route.SignUpComplete.name) {
                CompleteScreen(
                    onStartButtonClicked = {
                        navController.navigate(Route.StoreList.name) {
                            popUpTo(Route.SignProgress.name) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
