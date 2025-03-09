package com.example.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.AddRoute
import com.example.compose.ui.BottomNavigationBar
import com.example.compose.ui.ComposeAppGraph
import com.example.compose.ui.ComposeAppTopLevelDestinations
import com.example.compose.ui.components.TopAppBar
import com.example.compose.ui.components.actions
import com.example.compose.ui.components.addActions
import com.example.compose.ui.theme.ComposeMasterTheme
import com.example.compose.ui.topLevelDestinations
import com.example.compose.utils.handleSave

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

        var saveTriggered by remember { mutableStateOf(false) }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                if(currentTopLevelDestination != null) {
                    TopAppBar(
                        title = currentTopLevelDestination.label,
                        navigationIcon = Icons.Filled.Search,
                        navigationIconContentDescription = "Search",
                        onNavigationClick = {},
                        actions = if (currentTopLevelDestination.route == AddRoute)
                            addActions else actions,
                        onActionClicked = {actionId ->
                            when(actionId) {
                                "save" -> {
                                   saveTriggered = true
                                }
                                "menu" -> {
                                    println(actionId)
                                }
                            }
                        }
                    )
                }
            },
            bottomBar = {
                BottomNavigationBar(topLevelDestinations, navController)
            }
        ) { innerPadding -> ComposeAppGraph(modifier.padding(innerPadding).fillMaxSize(),
            navController, saveTask = saveTriggered, onSaveClick = {saveTriggered = it}) }
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
