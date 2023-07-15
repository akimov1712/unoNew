package ru.steelwave.unonew.presentaion.table

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TableViewModelFactory (private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TableViewModel::class.java)){
            return TableViewModel(application) as T
        }
        throw RuntimeException("Unknown view model class: $modelClass")
    }
}