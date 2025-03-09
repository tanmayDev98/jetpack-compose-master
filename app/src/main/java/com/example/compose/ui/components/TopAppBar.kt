package com.example.compose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.R
import com.example.compose.ui.theme.ComposeMasterTheme

data class TopBarActions(
    val id: String,
    val icon: ImageVector? = null,
    val iconResource: Int? = null,
    val contentDescription: String,
    var onClick: (String) -> Unit = {}
)

//top bar actions
val actions = listOf(
    TopBarActions(
        id = "menu",
        icon = Icons.Filled.Menu,
        contentDescription = "Menu",
    )
)

//top bar add screen actions
val addActions = listOf(
    TopBarActions(
        id = "save",
        iconResource = R.drawable.ic_save_outlined,
        contentDescription = "Save",
    ),
    TopBarActions(
        id = "menu",
        icon = Icons.Filled.Menu,
        contentDescription = "Menu",
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String,
    actions: List<TopBarActions> = emptyList(),
    onActionClicked: (String) -> Unit,
    onNavigationClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription
                )
            }
        },
        actions = {TopBarActions(
            actionsList = actions,
            onActionClicked = onActionClicked
        )},
        modifier = modifier
    )
}

@Composable
fun RowScope.TopBarActions(actionsList: List<TopBarActions>, onActionClicked:(String) -> Unit) {
    actionsList.forEach { action ->
        IconButton(onClick = { onActionClicked(action.id)}) {
            if (action.icon != null) {
                Icon(imageVector = action.icon,
                    contentDescription = action.contentDescription)
            } else if (action.iconResource != null) {
                Icon(
                    painter = painterResource(id = action.iconResource),
                    contentDescription = action.contentDescription)
            }
        }
    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    ComposeMasterTheme {
        val actions = listOf(
            TopBarActions(
                id = "menu",
                icon = Icons.Filled.Menu,
                contentDescription = "Menu",
                onClick = { println("Menu clicked") }
            ),
            TopBarActions(
                id = "save",
                iconResource = R.drawable.ic_save_outlined,
                contentDescription = "Save",
                onClick = { println("Save clicked") }
            )
        )
        TopAppBar(
            title = "App Bar",
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationIconContentDescription = "Navigation Icon",
            actions = actions,
            onActionClicked = {}
        )
    }
}