package com.example.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose.ui.add.AddScreen
import com.example.compose.ui.details.DetailsScreen
import com.example.compose.ui.home.HomeScreen
import com.example.compose.ui.share.ShareScreen

//Top Level bottom navigation graph
@Composable
fun ComposeAppGraph(modifier: Modifier = Modifier,
                    navController: NavHostController) {
    NavHost(navController =  navController, startDestination = HomeBaseRoute) {
        navigation<HomeBaseRoute>(startDestination = HomeRoute) {
            composable<HomeRoute> {
                HomeScreen(modifier, navController::navigateToDetails )
            }
            composable<DetailsRoute> {
                DetailsScreen()
            }
        }
        composable<AddRoute> {
            AddScreen()
        }
        composable<ShareRoute> {
           ShareScreen()
        }
    }
}