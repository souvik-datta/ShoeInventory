package com.souvik.shoeinventory

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.souvik.shoeinventory.local.AppDatabase
import com.souvik.shoeinventory.local.Entity

class ShoeListFragmentViewModel(val context: Application) : AndroidViewModel(context) {

    private val _list = MutableLiveData<List<Entity>>()
    val list: LiveData<List<Entity>>
        get() = _list

    fun getAllData(){
        Log.d("TAG", "getAllData: ${AppDatabase.getDatabase(context)?.getAll()}")
        _list.value = AppDatabase.getDatabase(context)?.getAll()
    }
}