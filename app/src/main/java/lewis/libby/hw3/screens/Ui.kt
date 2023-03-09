package lewis.libby.hw3.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import lewis.libby.hw3.*
import java.util.*

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
            onAbout = { viewModel.pushScreen(About) },
            onContactClick = { id ->
                viewModel.pushScreen(ContactDisplay(id))
            },
            selectedItemIds = viewModel.selectedItemIds,
            onClearSelections = viewModel::clearSelections,
            onToggleSelection = viewModel::toggleSelection,
            onDeleteSelectedItems = viewModel::deleteSelectedContacts,
            onAdd = {
                val newId = UUID.randomUUID().toString()
                viewModel.addContact(newId)
                viewModel.pushScreen(ContactEdit(newId))
            },
            comparator = viewModel.comparator,
        )
        is ContactDisplay -> ContactDisplay(
            contactId = screen.id,
            fetchContactWithAddresses = { id ->
                viewModel.getContactWithAddresses(id)
            },
            onSelectListScreen = viewModel::setScreenStack,
            onResetDatabase = viewModel::resetDatabase,
            onAbout = { viewModel.pushScreen(About) },
            onEdit = { id ->
                viewModel.pushScreen(ContactEdit(id))
            },
            onAdd = {
                val newId = UUID.randomUUID().toString()
                viewModel.addContact(newId)
                viewModel.pushScreen(ContactEdit(newId))
            }
        )
        is ContactEdit -> ContactEdit(
            contactId = screen.id,
            fetchContact = viewModel::getContact,
            fetchContactWithAddresses = viewModel::getContactWithAddresses,
            addresses = viewModel.addresses,
            setAddresses = viewModel::settingAddresses,
            onSelectListScreen = viewModel::setScreenStack,
            onResetDatabase = viewModel::resetDatabase,
            onAddressClick = { id -> viewModel.pushScreen(AddressEdit(id)) },
            onContactUpdate = viewModel::updateContact,
            onDeleteAddress = viewModel::deleteAddress,
            onAddAddress = {
                val newAddressId = UUID.randomUUID().toString()
                viewModel.addAddress(screen.id, newAddressId)
                viewModel.pushScreen(AddressEdit(newAddressId))
            },
            onAbout = { viewModel.pushScreen(About) }
        )
        is AddressEdit -> AddressEdit(
            addressId = screen.id,
            fetchAddress = viewModel::getAddress,
            onSelectListScreen = viewModel::setScreenStack,
            onResetDatabase = viewModel::resetDatabase,
            onAddressUpdate = viewModel::updateAddress,
            onAbout = { viewModel.pushScreen(About) }
        )
        is About -> About()
    }
}