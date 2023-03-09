package lewis.libby.hw3.screens

// List Scaffold function was taken from the Movie Ui 2 example project

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import lewis.libby.hw3.R
import lewis.libby.hw3.Screen
import lewis.libby.hw3.components.ListScaffold
import lewis.libby.hw3.components.SimpleText
import lewis.libby.hw3.repository.ContactDto

//Screen setup for list of all contacts
@Composable
fun ContactList(
    contacts: List<ContactDto>,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    selectedItemIds: Set<String>,
    onClearSelections: () -> Unit,
    onToggleSelection: (String) -> Unit,
    onDeleteSelectedItems: () -> Unit,
    onContactClick: (String) -> Unit,
    onAbout: () -> Unit,
    onAdd: () -> Unit,
    comparator: Comparator<ContactDto>,
) = ListScaffold(
    titleId = R.string.screen_title_contacts,
    items = contacts.sortedWith(comparator),
    getId = { it.id },
    onSelectListScreen = onSelectListScreen,
    onResetDatabase = onResetDatabase,
    onItemClick = onContactClick,
    itemIcon = Icons.Default.Person,
    itemIconContentDescriptionId = R.string.tap_to_toggle_selection,
    selectedItemIds = selectedItemIds,
    onClearSelections = onClearSelections,
    onToggleSelection = onToggleSelection,
    onDeleteSelectedItems = onDeleteSelectedItems,
    onAdd = onAdd,
    onAbout = onAbout
) {
    if (it.lastName == "" && it.firstName == "") {
        SimpleText(text = "${stringResource(R.string.no_name)}\n${it.mobilePhone}")
    } else if (it.lastName == "") {
        SimpleText(
            text = "${stringResource(R.string.no_last_name)}, ${it.firstName}" +
                    "\n${it.mobilePhone}"
        )
    } else if (it.firstName == "") {
        SimpleText(
            text = "${it.lastName}, ${stringResource(R.string.no_first_name)}" +
                    "\n${it.mobilePhone}"
        )
    } else {
        SimpleText(text = "${it.lastName}, ${it.firstName}\n${it.mobilePhone}")
    }
}