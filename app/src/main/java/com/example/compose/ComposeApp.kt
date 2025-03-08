package com.example.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.BottomNavigationBar
import com.example.compose.ui.ComposeAppGraph
import com.example.compose.ui.ComposeAppTopLevelDestinations
import com.example.compose.ui.components.TopAppBar
import com.example.compose.ui.theme.ComposeMasterTheme
import com.example.compose.ui.topLevelDestinations

@Composable
fun ComposeApp(modifier: Modifier) {
    ComposeMasterTheme  {
        val navController = rememberNavController()
        val previousDestination = remember { mutableStateOf<NavDestination?>(null) }
        val currentEntry = navController.currentBackStackEntryFlow.collectAsState(initial = null)

        val currentDestination: NavDestination? =
            currentEntry.value?.destination.also { destination ->
                if(destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value

        val currentTopLevelDestination: ComposeAppTopLevelDestinations? =
            topLevelDestinations.firstOrNull { destination ->
                currentDestination?.hasRoute(route = destination.route::class) == true
            }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                if(currentTopLevelDestination != null) {
                    TopAppBar(
                        title = currentTopLevelDestination.label,
                        navigationIcon = Icons.Filled.Search,
                        navigationIconContentDescription = "Search",
                        actionIcon = Icons.Filled.Menu,
                        actionContentDescription = "Menu",
                        onActionClick = {},
                        onNavigationClick = {}
                    )
                }
            },
            bottomBar = {
                BottomNavigationBar(topLevelDestinations, navController)
            }
        ) { innerPadding -> ComposeAppGraph(modifier.padding(innerPadding).fillMaxSize(), navController) }
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
