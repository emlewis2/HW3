package lewis.libby.hw3.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.coroutineScope
import lewis.libby.hw3.*
import lewis.libby.hw3.screens.AddressEdit
//import lewis.libby.hw3.AddressList
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
            onAbout = { viewModel.pushScreen(About) },
//            onContactClick = { id ->
//                viewModel.pushScreen(AddressDisplay(id))
//            },
            onEdit = { id ->
                viewModel.pushScreen(ContactEdit(id))
            },
            onAdd = {
                val newId = UUID.randomUUID().toString()
                viewModel.addContact(newId)
                viewModel.pushScreen(ContactEdit(newId))
//                    newId ->
//                viewModel.pushScreen(ContactEdit(newId)) }
            }
//                val newId = viewModel.addContact()
//                Log.d("CREATION", "before")
//                viewModel.pushScreen(ContactEdit(newId))
//                Log.d("CREATION", "after")

//            onAdd = { id -> viewModel.pushScreen(ContactEdit(id)) }
        )
        is ContactEdit -> ContactEdit(
            contactId = screen.id,
            fetchContact = viewModel::getContact,
            fetchContactWithAddresses = viewModel::getContactWithAddresses,
            addresses = viewModel.addresses,
            setAddresses = viewModel::settingAddresses,
//            fetchAddresses = viewModel::getAddresses,
            onSelectListScreen = viewModel::setScreenStack,
            onResetDatabase = viewModel::resetDatabase,
            onAddressClick = { id -> viewModel.pushScreen(AddressEdit(id)) },
            onContactUpdate = viewModel::updateContact,
            onDeleteAddress = viewModel::deleteAddress,
//            deletedAddress = viewModel.deletedAddress,
            onAddAddress = {
                val newAddressId = UUID.randomUUID().toString()
                viewModel.addAddress(screen.id, newAddressId)
                viewModel.pushScreen(AddressEdit(newAddressId))
            },
            onAbout = { viewModel.pushScreen(About) }
        )
//        is AddressDisplay -> AddressDisplay(
//            addressId = screen.id,
//            fetchAddress = viewModel::getAddress,
//            onSelectListScreen = viewModel::setScreenStack,
//            onResetDatabase = viewModel::resetDatabase,
////            onAddressClick = { id ->
////                viewModel.pushScreen(AddressDisplay(id))
////            }
//        )
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