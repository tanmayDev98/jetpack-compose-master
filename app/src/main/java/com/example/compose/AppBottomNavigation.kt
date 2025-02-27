package com.example.compose

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy

//Wrapper around Navigation bar item
@Composable
fun RowScope.ComposeNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel
    )
}

//Navigation Bottom bar
@Composable
fun BottomNavigationBar(topLevelDestinations: List<ComposeAppTopLevelDestinations>,
                        navController: NavController,
                        currentDestination:  NavDestination?) {
    NavigationBar  {
        topLevelDestinations.forEach { destination ->
            ComposeNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(destination.route::class) } == true,
                onClick = { navigateToActions(navController, destination) },
                modifier = Modifier,
                icon = { Icon(imageVector = destination.unSelectedIcon, contentDescription = destination.label) },
                selectedIcon = { Icon(imageVector = destination.selectedIcon, contentDescription = destination.label) },
                label = { Text(destination.label) }
            )
        }
    }
}

//Bottom Navigation Bar Preview
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    NavigationBar {
        topLevelDestinations.forEachIndexed { index, destination ->
            ComposeNavigationItem(
                //Manually set the first item as selected
                selected = index == 0,
                onClick = { },
                modifier = Modifier,
                icon = { Icon(imageVector = destination.unSelectedIcon, contentDescription = destination.label) },
                selectedIcon = { Icon(imageVector = destination.selectedIcon, contentDescription = destination.label) },
                label = { Text(destination.label) }
            )
        }
    }
}