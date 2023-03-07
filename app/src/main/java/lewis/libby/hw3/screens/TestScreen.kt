package lewis.libby.hw3.screens
//
//import androidx.activity.compose.BackHandler
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.lifecycle.viewmodel.compose.viewModel
//import lewis.libby.hw3.ContactList
//import lewis.libby.hw3.AddressList
//import lewis.libby.hw3.AddressDisplay
//import lewis.libby.hw3.ContactDisplay
//import lewis.libby.hw3.ContactViewModel
//import lewis.libby.hw3.components.SimpleButton
//
////Setting up the test screen
//@Composable
//fun TestScreen(
//    viewModel: ContactViewModel = viewModel(),
//    onExit: () -> Unit,
//) {
//    BackHandler {
//        viewModel.popScreen()
//    }
//    //Buttons for Reset, Contacts, and Addresses
//    Column {
//        Row {
//            SimpleButton("Reset") {
//                viewModel.resetDatabase()
//            }
//            SimpleButton("Contacts") {
//                viewModel.setScreenStack(ContactList)
//            }
//        }
//        Row {
//            SimpleButton("Addresses") {
//                viewModel.setScreenStack(AddressList)
//            }
//        }
//
//        //Collect flows
//        val contacts by viewModel.contactsFlow.collectAsState(initial = emptyList())
//        val addresses by viewModel.addressesFlow.collectAsState(initial = emptyList())
//
//        //Switching screens
//        when(val screen = viewModel.screen) {
//            null -> onExit()
//            //Screen for all contacts
//            ContactList -> ContactList(contacts = contacts) { id ->
//                viewModel.pushScreen(ContactDisplay(id))
//            }
//            //Screen for all addresses
//            AddressList -> AddressList(addresses = addresses) { id ->
//                viewModel.pushScreen(AddressDisplay(id))
//            }
//            //Screen for individual contact
//            is ContactDisplay -> ContactDisplay(
//                contactId = screen.id,
//                fetchContactWithAddresses = { id ->
//                    viewModel.getContactWithAddresses(id)
//                },
//                onContactClick = { id -> viewModel.pushScreen(AddressDisplay(id))}
//            )
//            //Screen for individual address
//            is AddressDisplay -> AddressDisplay(
//                id = screen.id,
//                fetchAddress = { id ->
//                    viewModel.getAddress(id)
//                }
//            )
//        }
//    }
//}