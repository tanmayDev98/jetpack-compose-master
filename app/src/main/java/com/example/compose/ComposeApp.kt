package com.example.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.theme.ComposeMasterTheme

@Composable
fun ComposeApp(modifier: Modifier) {
    ComposeMasterTheme  {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(topLevelDestinations, navController, currentDestination)
            }
        ) { innerPadding -> ComposeAppGraph(modifier.padding(innerPadding).fillMaxSize(), navController)}
    }
}

//Preview
@Preview
@Composable
fun ComposeAppPreview() {
    ComposeMasterTheme {
        ComposeApp(modifier = Modifier.fillMaxSize())
    }
}
