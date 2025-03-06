package com.example.compose.ui.add

import android.text.Editable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.model.TaskResource
import com.example.compose.utils.getItemById

@Composable
fun AddScreen(modifier: Modifier = Modifier, id: Int) {
    Box(modifier = modifier.padding(12.dp)) {
        Column {
            Text("Enter Title")
            Spacer(modifier = modifier.height(12.dp))
            AddItemTitleTextField(getItemById(id), onValueChanged = { it -> })
            Text("Enter Description")

        }
    }
}

@Composable
fun AddItemTitleTextField(resource: TaskResource, onValueChanged: (String) -> Unit) {
    OutlinedTextField(resource.title, onValueChange = onValueChanged)
}

@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    AddScreen(id=10)
}