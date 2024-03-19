package com.asemlab.samples.unittesting.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.asemlab.samples.unittesting.kaspresso.screens.TasksScreen
import com.asemlab.samples.unittesting.ui.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RecyclerViewTest : TestCase() {

    @get:Rule
    val tasksActivity = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun checkTasksRV_have_5_tasks() {

        TasksScreen {
            Assert.assertEquals(5, tasksRV.getSize())
        }

    }

    @Test
    fun checkTasksRV_elements_visibility() = run {

        step("Check elements visibility") {
            TasksScreen {
                tasksRV {
                    children<TasksScreen.TaskItemScreen> {
                        taskId.isVisible()
                        taskTitle.isVisible()
                        taskContainer.isVisible()

                        taskId.hasAnyText()
                        taskTitle.hasAnyText()
                    }
                }
            }
        }
    }

    @Test
    fun checkTasksRV_elements_content() = run {
        step("Check elements content") {
            TasksScreen {
                tasksRV {
                    childAt<TasksScreen.TaskItemScreen>(0) {
                        taskId.hasText("1")
                        taskTitle.hasText("Shopping")
                    }

                    lastChild<TasksScreen.TaskItemScreen> {
                        taskId.hasText("5")
                        taskTitle.hasText("Workout")
                    }
                }
            }
        }
    }

}