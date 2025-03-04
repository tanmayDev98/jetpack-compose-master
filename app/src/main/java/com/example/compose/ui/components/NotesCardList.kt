package com.example.compose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.model.TaskResource
import com.example.compose.utils.taskList

fun LazyStaggeredGridScope.notes(
    onCardClick: (Int) -> Unit,
    onEditClick: () -> Unit,
    taskItemsList: List<TaskResource> = taskList
) {
    items(
        items = taskItemsList.toList(),
        key = {it.id},
        contentType = {"taskItem"}
    ) { taskResource ->
        NotesCard(
            taskData = taskResource,
            onClick = onCardClick,
            onEditClicked = onEditClick,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
    }
}