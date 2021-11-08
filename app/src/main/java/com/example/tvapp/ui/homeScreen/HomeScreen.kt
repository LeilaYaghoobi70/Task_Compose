package com.example.tvapp.ui.homeScreen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.tvapp.R
import com.example.tvapp.data.model.Task
import com.example.tvapp.utils.EMPTY_SCREEN
import com.example.tvapp.utils.GetResult


@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsState()
    var visible by remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue()) }

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (
            menuImageView,
            floatingActionButton,
            dotMenuImageView,
            titleTextView,
            newTaskTextField,
            tasks,
            divider,
        ) = createRefs()

        state.getTasks?.let { getResultTask ->
            when (getResultTask) {
                is GetResult.Success -> {
                    Tasks(
                        content = {
                            ItemTask(getResultTask)
                        }, Modifier.constrainAs(tasks) {
                            top.linkTo(divider.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }
                is GetResult.Error -> {
                }
                else -> Unit
            }
        }
        FloatingActionButton(
            onClick = {
                visible = true
            },
            modifier = Modifier
                .constrainAs(floatingActionButton) {
                    top.linkTo(parent.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                }
                .size(25.dp),
            backgroundColor = colorResource(R.color.red),
            contentColor = colorResource(R.color.background),
        ) {
            Icon(Icons.Filled.Add, EMPTY_SCREEN)
        }

        Image(
            painterResource(R.drawable.ic_menu),
            stringResource(id = R.string.empty_string),
            modifier = Modifier
                .constrainAs(menuImageView) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }
        )

        Image(
            painterResource(R.drawable.ic_menu_dot),
            stringResource(id = R.string.empty_string),
            modifier = Modifier
                .constrainAs(dotMenuImageView) {
                    top.linkTo(floatingActionButton.bottom, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                }

        )

        Text(
            text = stringResource(id = R.string.tasks),
            color = colorResource(id = R.color.text_color),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(titleTextView) {
                    top.linkTo(menuImageView.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }

        )
        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(dotMenuImageView.bottom, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(1.dp)
                .padding(horizontal = 15.dp)
                .background(colorResource(id = R.color.background_divider_color))
        )
        AnimatedVisibility(visible = visible,
            modifier = Modifier.constrainAs(newTaskTextField) {
                top.linkTo(tasks.bottom, 12.dp)
                start.linkTo(parent.start, 12.dp)
                end.linkTo(parent.end, 12.dp)
            }) {
            AddNewTaskView(
                visibility = visible, {
                    viewModel.addedNewTaskView(it)
                }) {
                visible = false
            }
        }


    }

}

@Composable
private fun AddNewTaskView(
    visibility: Boolean,
    onClickAddNewTask: (String) -> Unit,
    onClickCancel: () -> Unit,
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    if (visibility)
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (textFieldTask, textViewAddNewTask, textViewCancel) = createRefs()
            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(textFieldTask) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .padding(horizontal = 12.dp)
                    .border(border = BorderStroke(1.dp,
                        colorResource(R.color.background_divider_color))),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.background_textField),
                    cursorColor = colorResource(id = R.color.background_divider_color),
                    disabledLabelColor = colorResource(id = R.color.background_textField),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = colorResource(id = R.color.background_divider_color),
                ),
            )

            Text(
                text = stringResource(id = R.string.add_task),
                modifier = Modifier
                    .clickable(enabled = true, onClick = {
                        onClickAddNewTask.invoke(textState.value.text)
                    })
                    .constrainAs(textViewAddNewTask) {
                        end.linkTo(parent.end, margin = 12.dp)
                        top.linkTo(textFieldTask.bottom, margin = 10.dp)
                    },
                color = colorResource(id = R.color.red),
                fontSize = 12.sp,

                )
            Text(text = stringResource(id = R.string.cancel),
                modifier = Modifier
                    .constrainAs(textViewCancel) {
                        end.linkTo(textViewAddNewTask.start, margin = 10.dp)
                        top.linkTo(textViewAddNewTask.top)
                        bottom.linkTo(textViewAddNewTask.bottom)
                    }
                    .clickable(
                        onClick = {
                            onClickCancel.invoke()
                        },
                    ),
                color = colorResource(id = R.color.background_divider_color),
                fontSize = 12.sp
            )
        }
}

@Composable
private fun ItemTask(getResultTask: GetResult<List<Task>>) {
    getResultTask.data?.map { task ->
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
        )
        {
            val (descriptionText, checkBox, divider) = createRefs()
            val checkedState = remember { mutableStateOf(false) }

            Checkbox(
                modifier = Modifier.constrainAs(checkBox) {
                    linkTo(parent.top, bottom = parent.bottom)
                    start.linkTo(parent.start)
                },
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.background_divider_color),
                    checkmarkColor = colorResource(id = R.color.text_color),
                    uncheckedColor = colorResource(id = R.color.background_divider_color)
                )
            )

            Text(
                text = task.description,
                color = colorResource(id = R.color.text_color),
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(descriptionText) {
                    linkTo(parent.top, bottom = parent.bottom)
                    start.linkTo(checkBox.end, margin = 12.dp)
                },
                textDecoration = if (checkedState.value) TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough
                    )
                ) else null
            )

            Divider(
                modifier = Modifier
                    .constrainAs(divider) {
                        top.linkTo(checkBox.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(1.dp)
                    .background(colorResource(id = R.color.background_divider_color))
            )
        }
    }
}

@Composable
private fun Tasks(
    content: @Composable () -> Unit,
    modifier: Modifier,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                content()
            }
        }
    }
}



