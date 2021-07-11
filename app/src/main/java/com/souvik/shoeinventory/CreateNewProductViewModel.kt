package com.souvik.shoeinventory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.souvik.shoeinventory.local.AppDatabase
import com.souvik.shoeinventory.local.Entity

class CreateNewProductViewModel(val context:Application) : AndroidViewModel(context) {

    var _status = MutableLiveData<Boolean>()
    val status : LiveData<Boolean>
        get() = _status

    fun insertToDb(data: Entity){
        AppDatabase.getDatabase(context)?.insert(data)
        _status.value = true
    }
}