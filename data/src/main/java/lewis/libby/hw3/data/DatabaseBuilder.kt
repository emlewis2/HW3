package lewis.libby.hw3.data

import android.content.Context
import androidx.room.Room

//Creating the DAO
fun createDao(context: Context) =
    Room.databaseBuilder(
        context,
        ContactDatabase::class.java,
        "CONTACTS"
    )
        .build()
        .dao
