package lewis.libby.hw3.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID


//Creating a Contact class using Room annotations
@Entity
data class Contact(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var firstName: String,
    var lastName: String,
    var homePhone: String,
    var workPhone: String,
    var mobilePhone: String,
    var email: String,
)

data class ContactWithAddresses(
    @Embedded
    val contact: Contact,

    //Relation to include foreign key
    @Relation(
        parentColumn = "id",
        entityColumn = "contactId",
    )
    val addresses: List<Address>
)