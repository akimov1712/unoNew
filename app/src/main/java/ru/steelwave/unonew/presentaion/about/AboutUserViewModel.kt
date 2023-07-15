package ru.steelwave.unonew.presentaion.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.repository.UserRepositoryImpl
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.domain.useCase.user.GetUserUseCase

class AboutUserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepositoryImpl(application)
    private val getUserUseCase = GetUserUseCase(userRepository)

    private val _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> get() = _user

    fun getUser(userId: Int){
        viewModelScope.launch {
            val user = getUserUseCase(userId)
            _user.value = user
        }
    }

}