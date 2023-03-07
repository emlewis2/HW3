package lewis.libby.hw3.screens

import androidx.compose.runtime.Composable
import lewis.libby.hw3.components.SimpleText
import lewis.libby.hw3.repository.ContactDto

//Screen setup for list of all contacts
@Composable
fun ContactList(
    contacts: List<ContactDto>,
    onContactClick: (String) -> Unit,
) {
    SimpleText(text = "Contacts")
    contacts.forEach {
        SimpleText(text = "${it.firstName} ${it.lastName}") {
            onContactClick(it.id)
        }
    }
}