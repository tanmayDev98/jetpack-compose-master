package com.example.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

//Route for home option
@Serializable data object HomeRoute
//Route for add option
@Serializable data object AddRoute
//Route for share option
@Serializable data object ShareRoute

//enum class for compose Top Level Destinations
enum class ComposeAppTopLevelDestinations(
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val label: String,
    val route: KClass<*>
) {
    HOME(
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        label = "Home",
        route = HomeRoute::class
    ),
    ADD(
        selectedIcon = Icons.Filled.Add,
        unSelectedIcon = Icons.Outlined.Add,
        label = "Add",
        route = AddRoute::class
    ),
    Share(
        selectedIcon = Icons.Filled.Share,
        unSelectedIcon = Icons.Outlined.Share,
        label = "Share",
        route = ShareRoute::class
    )
}