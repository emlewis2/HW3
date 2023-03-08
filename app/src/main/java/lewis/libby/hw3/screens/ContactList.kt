package lewis.libby.hw3.screens

//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
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
//    comparator: Comparator<ContactDto>,
) = ListScaffold(
    titleId = R.string.screen_title_contacts,
    items = contacts,
    getId = { it.id },
    onSelectListScreen = onSelectListScreen,
    onResetDatabase = onResetDatabase,
    onItemClick = onContactClick,
    itemIcon = Icons.Default.Person,
    itemIconContentDescriptionId = R.string.tap_to_toggle_selection,
    selectedItemIds = selectedItemIds,
    onClearSelections = onClearSelections,
    onToggleSelection = onToggleSelection,
    onDeleteSelectedItems = onDeleteSelectedItems
) { contact ->
    SimpleText(text = "${contact.lastName}, ${contact.firstName}\n${contact.mobilePhone}")
}
//) { paddingValues ->
//    LazyColumn(
//        modifier = Modifier.padding(paddingValues)
//    ) {
//        items(
//            items = contacts,
//            key = { it.id },
//        ) {
//            Card(
//                elevation = 4.dp,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//            ) {
//                if (contacts.isEmpty()) {
//                    SimpleText(text = stringResource(id = R.string.no_contacts_found))
//                } else {
//                    SimpleText(
//                        text = "${it.lastName}, ${it.firstName}\n${it.mobilePhone}",
//                        modifier = Modifier.clickable {
//                            onContactClick(it.id)
//                        }
//                    )
//                }
////                SimpleText(text = "${it.firstName} ${it.lastName}") {
////                    onContactClick(it.id)
////                }
//            }
//        }
//    }
//}