package com.example.compose.ui.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.components.ShowToast
import com.example.compose.utils.saveItem

@Composable
fun AddScreen(modifier: Modifier = Modifier,
              saveTask: Boolean) {

    var titleState by rememberSaveable { mutableStateOf("") }
    var descriptionState by rememberSaveable { mutableStateOf("") }
    var showToastState by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(saveTask) {
        if (saveTask) {
            saveItem(titleState, descriptionState)
            showToastState = true
        }
    }

    Box(modifier = modifier.padding(12.dp).fillMaxSize()) {
        AddScreen(
            titleState = titleState,
            descriptionState = descriptionState,
            onTitleChange = {titleState = it},
            onDescriptionChange = {descriptionState = it},
        )
        ShowToastMessage("Item has been added",showToastState) {
            showToastState = false
        }
    }
}

@Composable
internal fun AddScreen(titleState: String,
                       descriptionState: String,
                       onTitleChange:(String) -> Unit,
                       onDescriptionChange:(String) -> Unit) {
    Column {
        Text("Enter Title")
        AddTitleItemInput(titleState, onNameChange = onTitleChange)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Enter Description")
        AddDescriptionItemInput(
            modifier = Modifier.weight(1f),
            descriptionState,
            onNameChange = onDescriptionChange
        )
    }
}

@Composable
fun AddTitleItemInput(name: String, onNameChange:(String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = name,
        onValueChange = onNameChange
    )
}

@Composable
fun AddDescriptionItemInput(modifier: Modifier, name: String, onNameChange:(String) -> Unit) {
    OutlinedTextField(
        modifier =  modifier.fillMaxWidth(),
        value = name,
        onValueChange = onNameChange
    )
}

@Composable
fun ShowToastMessage(text: String, show: Boolean, onToast: () -> Unit) {
   ShowToast(text, show, onToast)
}

@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    //AddScreen(modifier = Modifier.fillMaxWidth())
}