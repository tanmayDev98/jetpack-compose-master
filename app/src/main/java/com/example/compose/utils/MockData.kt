package com.example.compose.utils

import com.example.compose.model.TaskResource

val taskList = listOf(
    TaskResource(id = 0, title = "Buy a book", description = "Books to buy \nAnarchy\n Sherlock Holmes\n"),
    TaskResource(id = 1, title = "Change table orientation", description = "Urgent need more sunlight!"),
    TaskResource(id = 2, title = "Study DSA for an hour", description = "Need to do in C and Kotlin"),
    TaskResource(id = 3, title = "Study Kotlin development", description = "Build Something!"),
    TaskResource(id = 4, title = "Play badminton", description = "Need to check and book a court"),
    TaskResource(id = 5, title = "Play badminton", description = "Need to check and book a court"),
    TaskResource(id = 6, title = "Play Volleyball", description = "Need to check and book a court"),
    TaskResource(id = 7, title = "Play TT", description = "Need to check and book a court"),
    TaskResource(id = 8, title = "Play Football", description = "Need to check and book a court"),
    TaskResource(id = 9, title = "Play Cricket", description = "Need to check and book a court"),
    TaskResource(id = 10, title = "Play Hockey", description = "Need to check and book a court")
).associateByTo(mutableMapOf()) { it.id }

fun getTaskList() = taskList.values.toList()

fun getItemById(id: Int): TaskResource {
  return getTaskList()[id]
}

private fun addItem(taskResource: TaskResource) {
  taskList[taskResource.id] = taskResource
}

//To save item in the list
fun handleSave(title: String, description: String) {
    if (title.isNotBlank()) {
        val taskResource = TaskResource(
            id = taskList.size,
            title = title,
            description = description
        )
        addItem(taskResource)
    }
}

//for list
//fun saveItem(taskResource: TaskResource) {
//   taskList = if(taskList.any {it.id == taskResource.id}) {
//       taskList.map { if(it.id == taskResource.id) taskResource else it }
//   } else {
//       taskList + taskResource
//   }
//}