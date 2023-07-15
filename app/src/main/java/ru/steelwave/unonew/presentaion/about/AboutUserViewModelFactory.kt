package ru.steelwave.unonew.presentaion.about

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.steelwave.unonew.presentaion.home.HomeViewModel

class AboutUserViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AboutUserViewModel::class.java)){
            return AboutUserViewModel(application) as T
        }
        throw RuntimeException("Unknown view model class: $modelClass")
    }
}