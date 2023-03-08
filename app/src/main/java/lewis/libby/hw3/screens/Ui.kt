package lewis.libby.hw3.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
//import lewis.libby.hw3.AddressList
import lewis.libby.hw3.AddressDisplay
import lewis.libby.hw3.ContactList
import lewis.libby.hw3.ContactDisplay
import lewis.libby.hw3.ContactViewModel

@Composable
fun Ui(
    viewModel: ContactViewModel = viewModel(),
    onExit: () -> Unit,
) {
    BackHandler {
        viewModel.popScreen()
    }

    val contacts by viewModel.contactsFlow.collectAsState(initial = emptyList())
    val addresses by viewModel.addressesFlow.collectAsState(initial = emptyList())

    when(val screen = viewModel.screen) {
        null -> onExit()

        ContactList -> ContactList(
            contacts = contacts,
            onSelectListScreen = viewModel::setScreenStack,
            onResetDatabase = viewModel::resetDatabase,
            onContactClick = { id ->
                viewModel.pushScreen(ContactDisplay(id))
            },
            selectedItemIds = viewModel.selectedItemIds,
            onClearSelections = viewModel::clearSelections,
            onToggleSelection = viewModel::toggleSelection,
            onDeleteSelectedItems = viewModel::deleteSelectedContacts,
        )
//        AddressList -> AddressList(
//            addresses = addresses,
//            onSelectListScreen = viewModel::setScreenStack,
//            onResetDatabase = viewModel::resetDatabase,
//            onAddressClick = { id ->
//                viewModel.pushScreen(AddressDisplay(id))
//            }
//        )

        is ContactDisplay -> ContactDisplay(
            contactId = screen.id,
            fetchContactWithAddresses = { id ->
                viewModel.getContactWithAddresses(id)
            },
            onSelectListScreen = viewModel::setScreenStack,
            onResetDatabase = viewModel::resetDatabase,
            onContactClick = { id ->
                viewModel.pushScreen(AddressDisplay(id))
            }
        )
        is AddressDisplay -> AddressDisplay(
            addressId = screen.id,
            fetchAddress = viewModel::getAddress,
            onSelectListScreen = viewModel::setScreenStack,
            onResetDatabase = viewModel::resetDatabase,
//            onAddressClick = { id ->
//                viewModel.pushScreen(AddressDisplay(id))
//            }
        )
    }
}