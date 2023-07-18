package ru.steelwave.unonew.presentaion.add

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.steelwave.unonew.presentaion.table.TableViewModel

class AddViewModelFactory (private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(application) as T
        }
        throw RuntimeException("Unknown view model class: $modelClass")
    }
}