package com.souvik.shoeinventory

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.souvik.shoeinventory.local.AppDatabase
import com.souvik.shoeinventory.local.Entity
import kotlinx.coroutines.Dispatchers

class ShoeListFragmentViewModel(val context: Application) : AndroidViewModel(context) {

    private val _list = MutableLiveData<List<Entity>>()
    val list: LiveData<List<Entity>>
        get() = _list

    fun getAllData(){
        with(Dispatchers.IO){
            Log.d("TAG", "getAllData: ${AppDatabase.getDatabase(context)?.getAll()}")
            _list.postValue(AppDatabase.getDatabase(context)?.getAll())
        }
    }
}