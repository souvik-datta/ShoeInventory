package com.souvik.shoeinventory.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.ArrayList

@Entity(tableName = "ShoeStore")
class Entity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
    var size: Double = 0.0
    var company: String = ""
    var description: String = ""
    var images: String = ""
}