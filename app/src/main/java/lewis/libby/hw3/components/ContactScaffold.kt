package lewis.libby.hw3.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lewis.libby.hw3.R
import lewis.libby.hw3.Screen

// The following code was taken and adapted from the Module 4 Example project

@Composable
fun ContactScaffold(
    title: String,
    selectedItemCount: Int = 0,
    onClearSelections: () -> Unit = {},
    onDeleteSelectedItems: () -> Unit = {},
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: (() -> Unit)? = null,
    onEdit: (() -> Unit)? = null,
    onAdd: (() -> Unit)? = null,
    onAbout: (() -> Unit)? = null,
    onDeleteAddress: () -> Unit = {},
    onAddAddress: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            val displayName = if (title == "") {
                stringResource(id = R.string.no_name)
            } else {
                title
            }
            if (selectedItemCount == 0) {
                TopAppBar(
                    title = { SimpleText(text = displayName) },
                    actions = {
                        // Taken from Movie Ui 2 example project
                        onEdit?.let { onEdit ->
                            IconButton(
                                onClick = onEdit,
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(id = R.string.edit),
                                )
                            }
                        }
                        onAdd?.let { onAdd ->
                            IconButton(
                                onClick = onAdd,
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = stringResource(id = R.string.add_contact),
                                )
                            }
                        }
                        onResetDatabase?.let { onResetDatabase ->
                            IconButton(
                                onClick = onResetDatabase,
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Autorenew,
                                    contentDescription = stringResource(id = R.string.reset_database)
                                )
                            }
                        }
                        onAbout?.let { onAbout ->
                            IconButton(
                                onClick = onAbout,
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.QuestionMark,
                                    contentDescription = stringResource(id = R.string.about)
                                )
                            }
                        }
                    }
                )
            } else {
                // Taken from Movie Ui 2 example project
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = onClearSelections,
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.clear_selections),
                            )
                        }
                    },
                    title = { SimpleText(text = selectedItemCount.toString()) },
                    actions = {
                        IconButton(
                            onClick = onDeleteSelectedItems,
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(id = R.string.delete_selected_items)
                            )
                        }
                    }
                )
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        }
    )
}