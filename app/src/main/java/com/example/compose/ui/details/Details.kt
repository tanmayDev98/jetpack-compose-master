package com.example.compose.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeMasterTheme
import com.example.compose.utils.getItemById

@Composable
fun DetailsScreen(modifier: Modifier = Modifier,
                  id: Int,
                  onBackClick: () -> Unit) {
    val item = getItemById(id)
    Column(modifier = modifier.fillMaxSize()) {
        DetailsScreen(
            item.title,
            item.description,
            onBackClick = onBackClick)
    }
}

@Composable
internal fun DetailsScreen(titleText: String,
                           detailsText: String,
                           onBackClick: () -> Unit) {
    DetailsToolBar(
        modifier = Modifier.height(56.dp),
        titleText = titleText,
        onBackClick = onBackClick
    )
    Spacer(modifier = Modifier.padding(12.dp))
    DetailsDescriptionText(
        detailsText,
        modifier = Modifier.padding(horizontal = 12.dp)
    )

}

@Composable
fun DetailsTitleText(titleText: String, modifier: Modifier = Modifier) {
    Text(
        titleText,
        style = MaterialTheme.typography.headlineSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun DetailsDescriptionText(titleText: String, modifier: Modifier = Modifier) {
    Text(titleText, style = MaterialTheme.typography.bodyMedium, modifier = modifier)
}

@Composable
fun DetailsToolBar(
    modifier: Modifier = Modifier,
    showBackButton:Boolean = true,
    titleText: String,
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(showBackButton) {
            BackButton(onBackClick = onBackClick)
        } else {
            Spacer(modifier = Modifier.width(1.dp))
        }
        DetailsTitleText(
           titleText = titleText
        )
        EditButton(onEditClick =  onEditClick)
    }
}

@Composable
fun BackButton(onBackClick: () -> Unit) {
    IconButton(onClick = onBackClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}

@Composable
fun EditButton(onEditClick: () -> Unit) {
    IconButton(onClick = onEditClick) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsToolbarPreview() {
    ComposeMasterTheme {
        DetailsToolBar(modifier = Modifier.fillMaxWidth(), titleText = "Details ToolBar")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    ComposeMasterTheme {
        DetailsScreen(modifier = Modifier.fillMaxSize(), id = 10, onBackClick = {})
    }
}