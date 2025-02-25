package com.example.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ComposeAppGraph(modifier: Modifier = Modifier,
                    navController: NavHostController = rememberNavController(),
                    appStartDestination: HomeRoute) {
    NavHost(navController =  navController, startDestination = appStartDestination, modifier = modifier) {
        composable<HomeRoute> {navBackStackEntry ->
            navController.navigate(HomeScreen())
        }
        composable<AddRoute> {
            navController.navigate(AddScreen())
        }
        composable<ShareRoute> {
            /*ToDo()*/
        }
    }
}

