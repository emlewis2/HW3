package lewis.libby.hw3.screens

// Entire code taken and adapted from Movie Ui 2 example project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lewis.libby.hw3.R
import lewis.libby.hw3.components.SimpleText

@Composable
fun About(
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { SimpleText(text = stringResource(id = R.string.about)) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                SimpleText(text = stringResource(id = R.string.about_text))
            }
        }
    )
}