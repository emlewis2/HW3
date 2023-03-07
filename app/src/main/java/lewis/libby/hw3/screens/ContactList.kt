package lewis.libby.hw3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lewis.libby.hw3.R
import lewis.libby.hw3.Screen
import lewis.libby.hw3.components.ContactScaffold
import lewis.libby.hw3.components.SimpleText
import lewis.libby.hw3.repository.ContactDto

//Screen setup for list of all contacts
@Composable
fun ContactList(
    contacts: List<ContactDto>,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onContactClick: (String) -> Unit,
) = ContactScaffold(
    title = stringResource(id = R.string.screen_title_contacts),
    onSelectListScreen = onSelectListScreen,
    onResetDatabase = onResetDatabase,
) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
        contacts.forEach {
            SimpleText(text = "${it.firstName} ${it.lastName}") {
                onContactClick(it.id)
            }
        }
    }
}