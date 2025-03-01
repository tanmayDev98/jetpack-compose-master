package com.example.compose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.compose.data.model.TaskResource
import com.example.compose.ui.theme.ComposeMasterTheme

@Composable
fun NotesCard(taskData: TaskResource,
             onEditClicked: () -> Unit,
             onClick: () -> Unit,
             modifier: Modifier = Modifier) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.padding(12.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CardTitleText(taskData.title, modifier = Modifier.fillMaxWidth((.8f)))
                    Spacer(modifier = Modifier.weight(1f))
                    EditButton(onEditClicked)
                }
                Spacer(modifier = Modifier.height(8.dp))
                CardDescriptionText(taskData.description)
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}

@Composable
fun EditButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit Icon")
    }
}

@Composable
fun CardTitleText(titleText: String, modifier: Modifier = Modifier) {
    Text(titleText, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}

@Composable
fun CardDescriptionText(description: String) {
    Text(description, style = MaterialTheme.typography.bodyLarge,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis)
}

@Preview
@Composable
fun NotesCardPreview() {
    ComposeMasterTheme {
        NotesCard(taskData = TaskResource(0,"Buy a book", "But this year"), onEditClicked = {}, onClick = {})
    }
}