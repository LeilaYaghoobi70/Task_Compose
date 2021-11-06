package com.example.tvapp.ui.homeScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.tvapp.R
import com.example.tvapp.data.model.Task
import com.example.tvapp.utils.Destination
import com.example.tvapp.utils.EMPTY_SCREEN
import com.example.tvapp.utils.GetResult


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier,
    navController: NavHostController,

    ) {
    val state by viewModel.state.collectAsState()

        Box(
            modifier = modifier
                .fillMaxWidth().fillMaxHeight()
        ) {

              state.getTasks?.let { getResultTask ->
                when (getResultTask) {
                    is GetResult.Success -> {
                        Tasks(
                            content = { ItemTask(getResultTask) }
                        )
                    }
                    is GetResult.Error -> {
                    }
                    else -> Unit
                }
            }

            Image(
                painterResource(R.drawable.ic_menu),
                "content description",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(15.dp)
            )

            Image(
                painterResource(R.drawable.ic_menu_dot),
                "content description",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 15.dp, top = 80.dp)

            )

            Text(
                text = "Task",
                color = colorResource(id = R.color.text_color),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 15.dp, top = 80.dp)
            )

            FloatingActionButton(
                onClick = {
                    navController.navigate(Destination.NEW_TASK_SCREEN)
                },
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.TopEnd)
                    .size(25.dp),
                backgroundColor = colorResource(R.color.red),
                contentColor = colorResource(R.color.background),
            ) {
                Icon(Icons.Filled.Add, EMPTY_SCREEN)
            }

            Divider(
                Modifier
                    .background(colorResource(id = R.color.background_divider_color))
                    .height(1.dp).fillMaxHeight()
                    .padding(top = 200.dp).align(Alignment.Center)
            )


        }




}

@Composable
private fun AddNewTaskView() {
    //  TextField(value =, onValueChange =)
}

@Composable
private fun ItemTask(getResultTask: GetResult<List<Task>>) {
    getResultTask.data?.map { task ->
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight())
        {
            val (descriptionText, titleText) = createRefs()
            Text(
                text = task.title,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(titleText) {
                    linkTo(parent.top, bottom = parent.bottom)
                    start.linkTo(parent.start)
                }
            )
            Text(
                text = task.description,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(descriptionText) {
                    linkTo(parent.top, bottom = parent.bottom)
                    start.linkTo(titleText.end, margin = 12.dp)
                }
            )
        }
    }
}

@Composable
private fun Tasks(
    content: @Composable () -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        item {
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(horizontal = 20.dp)
            ) {
                content()
            }
        }
    }
}



