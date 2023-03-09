package lewis.libby.hw3.repository

// Entire code was taken and adapted from the Movie example project

import android.content.Context
import lewis.libby.hw3.data.createDao
import kotlinx.coroutines.flow.map

//ContactDatabaseRepository which exposes all functions from the DAO
class ContactDatabaseRepository(context: Context): ContactRepository {
    private val dao = createDao(context)

    //All contacts flow
    override val contactsFlow =
        dao.getContactsFlow()
            .map { contacts ->
                contacts.map { it.toDto() }
            }
    //All addresses flow
    override val addressesFlow =
        dao.getAddressesFlow()
            .map { addresses ->
                addresses.map { it.toDto() }
            }

    //Get Contact with Addresses
    override suspend fun getContactWithAddresses(id: String): ContactWithAddressesDto =
        dao.getContactWithAddresses(id).toDto()

    //Get Contact
    override suspend fun getContact(id: String): ContactDto =
        dao.getContact(id).toDto()

    //Get Address
    override suspend fun getAddress(id: String): AddressDto =
        dao.getAddress(id).toDto()

    //Insert functions
    override suspend fun insert(address: AddressDto) = dao.insert(address.toEntity())
    override suspend fun insert(contact: ContactDto) = dao.insert(contact.toEntity())

    //Update functions
    override suspend fun update(address: AddressDto) = dao.update(address.toEntity())
    override suspend fun update(contact: ContactDto) = dao.update(contact.toEntity())

    //Delete functions
    override suspend fun delete(address: AddressDto) = dao.delete(address.toEntity())
    override suspend fun delete(contact: ContactDto) = dao.delete(contact.toEntity())

    override suspend fun deleteContactsById(ids: Set<String>) = dao.deleteContactsById(ids)

    //Reset database
    override suspend fun resetDatabase() = dao.resetDatabase()

    override suspend fun addContact(newId: String) = dao.addContact(newId)

    override suspend fun deleteAddressById(id: String) = dao.deleteAddressById(id)

    override suspend fun addAddress(contactId: String, newAddressId: String) =
        dao.addAddress(contactId, newAddressId)
}