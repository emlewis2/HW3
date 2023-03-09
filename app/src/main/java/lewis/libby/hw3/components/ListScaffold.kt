package lewis.libby.hw3.components

// Entire code from the Module 5 Example Project Movie Ui 2

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lewis.libby.hw3.Screen
import lewis.libby.hw3.R

@Composable
fun <T> ListScaffold(
    @StringRes titleId: Int,
    items: List<T>,
    selectedItemIds: Set<String>,
    onClearSelections: () -> Unit,
    onToggleSelection: (String) -> Unit,
    onDeleteSelectedItems: () -> Unit,
    getId: (T) -> String,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onAbout: () -> Unit,
    onItemClick: (String) -> Unit,
    itemIcon: ImageVector,
    @StringRes itemIconContentDescriptionId: Int,
    onAdd: () -> Unit,
    itemContent: @Composable RowScope.(T) -> Unit,
//    onAdd: () -> Unit,
) = if (items.isEmpty()) {
    ContactScaffold(
        title = stringResource(id = titleId),
        onSelectListScreen = onSelectListScreen,
        selectedItemCount = selectedItemIds.size,
        onResetDatabase = onResetDatabase,
        onDeleteSelectedItems = onDeleteSelectedItems,
        onClearSelections = onClearSelections,
        onAdd = onAdd,
        onAbout = onAbout
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            SimpleText(text = stringResource(id = R.string.no_contacts_found))
        }
    }
} else {
    ContactScaffold(
    title = stringResource(id = titleId),
    onSelectListScreen = onSelectListScreen,
    selectedItemCount = selectedItemIds.size,
    onResetDatabase = onResetDatabase,
    onDeleteSelectedItems = onDeleteSelectedItems,
    onClearSelections = onClearSelections,
    onAdd = onAdd,
    onAbout = onAbout
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(
                items = items,
                key = { getId(it) },
            ) { item ->
                // Taken from Movie Ui 2 example project
                val id = getId(item)

                val backgroundColor =
                    if (id in selectedItemIds) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.surface
                    }

                val contentColor = MaterialTheme.colors.contentColorFor(backgroundColor)
                val selectedIds by rememberUpdatedState(newValue = selectedItemIds)

                Card(
                    elevation = 4.dp,
                    backgroundColor = backgroundColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .pointerInput(true) {
                            detectTapGestures(
                                //                            onLongPress = {
                                //                                onToggleSelection(id)
                                //                            },
                                onTap = {
                                    if (selectedIds.isNotEmpty()) {
                                        onToggleSelection(id)
                                    } else {
                                        onItemClick(id)
                                    }
                                }
                            )
                        },
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
    //                        .clickable { onItemClick(getId(item)) }
                    ) {
                        Icon(
                            imageVector = itemIcon,
    //                        tint = MaterialTheme.colors.surface,
                            tint = backgroundColor,
                            contentDescription = stringResource(id = itemIconContentDescriptionId),
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    //                                color = MaterialTheme.colors.primary,
                                    color = contentColor,
                                    shape = CircleShape
                                )
                                .clickable {
                                    onToggleSelection(id)
                                }
                        )
                        itemContent(item)
                    }
                }
            }
        }
    }
}