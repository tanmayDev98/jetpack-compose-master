package com.example.compose.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeMasterTheme
import com.example.compose.utils.getItems

@Composable
fun DetailsScreen(modifier: Modifier, id: Int) {
    val item = getItems(id)
    Column(modifier = modifier.fillMaxSize().padding(12.dp)

    ) {
        DetailsTitleText(
            item.title,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(12.dp))
        DetailsDescriptionText(
            item.description,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DetailsTitleText(titleText: String, modifier: Modifier = Modifier) {
    Text(titleText, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}

@Composable
fun DetailsDescriptionText(titleText: String, modifier: Modifier = Modifier) {
    Text(titleText, style = MaterialTheme.typography.bodyMedium, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    ComposeMasterTheme {
        DetailsScreen(modifier = Modifier.fillMaxSize(), id = 10)
    }
}