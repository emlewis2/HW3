package lewis.libby.hw3

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import lewis.libby.hw3.repository.ContactDatabaseRepository
import lewis.libby.hw3.repository.ContactDto
import lewis.libby.hw3.repository.AddressDto
import lewis.libby.hw3.repository.ContactRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed interface Screen             //Sealed interface
object ContactList: Screen          //Screen for all contacts
object About: Screen          //Screen for about page
data class ContactDisplay(           //Screen for individual contact
    val id: String
): Screen
data class ContactEdit(
    val id: String
): Screen
data class AddressEdit(
    val id: String
): Screen

//View model for Contact
class ContactViewModel(application: Application) : AndroidViewModel(application) {
    //Private instance of repository
    private val repository: ContactRepository = ContactDatabaseRepository(application)

    private var screenStack: List<Screen> = listOf(ContactList)
        set(value) {
            field = value
            screen = value.lastOrNull()
            clearSelections()
        }

    //Next section on selection taken from Movie Ui 2 example project

    var selectedItemIds by mutableStateOf<Set<String>>(emptySet())
        private set

    var addresses by mutableStateOf<List<AddressDto>?>(emptyList())
        private set

    class ContactComparator: Comparator<ContactDto> {
        override fun compare(c1: ContactDto?, c2: ContactDto?): Int {
            if (c1?.lastName == null || c2?.lastName == null) {
                return 0
            }
            return c1.lastName.compareTo(c2.lastName)
        }
    }

    val comparator = ContactComparator()

    fun settingAddresses(addressList: List<AddressDto>?) {
        addresses = addressList
    }

    fun clearSelections() {
        selectedItemIds = emptySet()
    }

    fun toggleSelection(id: String) {
        selectedItemIds =
            if (id in selectedItemIds) {
                selectedItemIds - id
            } else {
                selectedItemIds + id
            }
    }
    fun deleteSelectedContacts() {
        viewModelScope.launch {
            repository.deleteContactsById(selectedItemIds)
            clearSelections()
        }
    }

    fun pushScreen(screen: Screen) {
        screenStack = screenStack + screen
    }

    fun popScreen() {
        if (screenStack.isNotEmpty()) {
            screenStack = screenStack.dropLast(1)
        }
    }

    fun setScreenStack(screen: Screen) {
        screenStack = listOf(screen)
    }

    var screen by mutableStateOf<Screen?>(ContactList)
        private set

    //Flow properties for all contacts and all addresses
    val contactsFlow = repository.contactsFlow
    val addressesFlow = repository.addressesFlow

    //Get Contact with Addresses
    suspend fun getContactWithAddresses(id: String) =
        repository.getContactWithAddresses(id)

    //Get Contact
    suspend fun getContact(id: String) =
        repository.getContact(id)

    //Get Address
    suspend fun getAddress(id: String) =
        repository.getAddress(id)

    private var contactUpdateJob: Job? = null

    fun updateContact(contactDto: ContactDto) {
        contactUpdateJob?.cancel()
        contactUpdateJob = viewModelScope.launch {
            delay(500)
            repository.update(contactDto)
            contactUpdateJob = null
        }
    }

    //Reset database
    fun resetDatabase() {
        viewModelScope.launch {
            repository.resetDatabase()
        }
    }

    fun addContact(newId: String) {
//        var newId = UUID.randomUUID().toString()
        viewModelScope.launch {
            repository.addContact(newId)
        }
    }

    fun deleteAddress(addressId: String) {
        viewModelScope.launch {
            repository.deleteAddressById(addressId)
        }
    }

    fun addAddress(contactId: String, newAddressId: String) {
        viewModelScope.launch {
            repository.addAddress(contactId, newAddressId)
        }
    }

    private var addressUpdateJob: Job? = null
    fun updateAddress(addressDto: AddressDto) {
        addressUpdateJob?.cancel()
        addressUpdateJob = viewModelScope.launch {
            delay(500)
            repository.update(addressDto)
            addressUpdateJob = null
        }
    }
}