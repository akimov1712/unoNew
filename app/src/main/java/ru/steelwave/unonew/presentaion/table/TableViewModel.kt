package ru.steelwave.unonew.presentaion.table

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.repository.GameRepositoryImpl
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.useCase.game.GetGameUseCase

class TableViewModel(application: Application): AndroidViewModel(application) {

    private val repository = GameRepositoryImpl(application)
    private val getGameUseCase = GetGameUseCase(repository)

    private val _game: MutableLiveData<GameModel> = MutableLiveData()
    val game: LiveData<GameModel> get() = _game

    fun getGame(gameId: Int){
        viewModelScope.launch {
            val game = getGameUseCase(gameId)
            _game.value = game
        }
    }

}