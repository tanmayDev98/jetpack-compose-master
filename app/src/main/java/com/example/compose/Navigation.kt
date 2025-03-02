package com.example.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import kotlinx.serialization.Serializable

//Base route for home
@Serializable data object HomeBaseRoute
//Route for home option
@Serializable data object HomeRoute
//Route for add option
@Serializable data object AddRoute
//Route for share option
@Serializable data object ShareRoute
//Details route
@Serializable data object DetailsRoute

//enum class for compose Top Level Destinations
enum class ComposeAppTopLevelDestinations(
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val label: String,
    val route: Any,
    val baseRoute: Any = route
) {
    HOME(
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        label = "Home",
        route = HomeRoute,
        baseRoute = HomeBaseRoute
    ),
    ADD(
        selectedIcon = Icons.Filled.Add,
        unSelectedIcon = Icons.Outlined.Add,
        label = "Add",
        route = AddRoute
    ),
    SHARE(
        selectedIcon = Icons.Filled.Share,
        unSelectedIcon = Icons.Outlined.Share,
        label = "Share",
        route = ShareRoute
    )
}

//List of all destinations of bottom navigation
val topLevelDestinations: List<ComposeAppTopLevelDestinations> = ComposeAppTopLevelDestinations.entries

//Navigation Actions to each top level destinations
fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = ComposeAppTopLevelDestinations.HOME.route, navOptions)
fun NavController.navigateToAdd(navOptions: NavOptions) = navigate(route = ComposeAppTopLevelDestinations.ADD.route, navOptions)
fun NavController.navigateToShare(navOptions: NavOptions) = navigate(route = ComposeAppTopLevelDestinations.SHARE.route, navOptions)

//Navigation to card details page
fun NavController.navigateToDetails() = navigate(route = DetailsRoute)

//Common function to call on onClick of navigation
fun navigateToActions(navController: NavController, destinations: ComposeAppTopLevelDestinations) {
    val navOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    when(destinations) {
        ComposeAppTopLevelDestinations.HOME -> navController.navigateToHome(navOptions)
        ComposeAppTopLevelDestinations.ADD -> navController.navigateToAdd(navOptions)
        ComposeAppTopLevelDestinations.SHARE -> navController.navigateToShare(navOptions)
    }
}