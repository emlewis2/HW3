package lewis.libby.hw3.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lewis.libby.hw3.ContactList
//import lewis.libby.hw3.AddressList
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
    onResetDatabase: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            if (selectedItemCount == 0) {
                TopAppBar(
                    title = { SimpleText(text = title) },
                    actions = {
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
        },
        bottomBar = {
            BottomNavigation {
                ScreenSelectButton(
                    targetScreen = ContactList,
                    imageVector = Icons.Default.Person,
                    labelId = R.string.screen_title_contacts,
                    onSelectListScreen = onSelectListScreen
                )
//                ScreenSelectButton(
//                    targetScreen = AddressList,
//                    imageVector = Icons.Default.House,
//                    labelId = R.string.screen_title_addresses,
//                    onSelectListScreen = onSelectListScreen
//                )
            }
        }
    )
}

@Composable
fun RowScope.ScreenSelectButton(
    targetScreen: Screen,
    imageVector: ImageVector,
    @StringRes labelId: Int,
    onSelectListScreen: (Screen) -> Unit,
) =
    BottomNavigationItem(
        selected = false,
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(id = labelId)
            )
        },
        label = {
            Text(text = stringResource(id = labelId))
        },
        onClick = {
            onSelectListScreen(targetScreen)
        }
    )