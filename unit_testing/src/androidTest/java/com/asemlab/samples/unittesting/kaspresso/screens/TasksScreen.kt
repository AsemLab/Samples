package com.asemlab.samples.unittesting.kaspresso.screens

import android.view.View
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.tasks.TasksActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object TasksScreen : KScreen<TasksScreen>() {

    override val layoutId: Int = R.layout.activity_tasks
    override val viewClass: Class<*> = TasksActivity::class.java


    val tasksRV = KRecyclerView(
        builder = { withId(R.id.tasksRV) },
        itemTypeBuilder = { itemType(::TaskItemScreen) }
    )

    class TaskItemScreen(matcher: Matcher<View>) : KRecyclerItem<TaskItemScreen>(matcher) {

        val taskContainer = KView(matcher) { withId(R.id.taskContainer) }
        val taskId = KTextView(matcher) { withId(R.id.taskID) }
        val taskTitle = KTextView(matcher) { withId(R.id.taskTitle) }
    }

}