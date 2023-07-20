package ru.steelwave.unonew.presentaion.table

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.repository.GameRepositoryImpl
import ru.steelwave.unonew.data.repository.RoundRepositoryImpl
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.entity.RoundModel
import ru.steelwave.unonew.domain.useCase.game.GetGameUseCase
import ru.steelwave.unonew.domain.useCase.round.GetRoundListUseCase

class TableViewModel(application: Application): AndroidViewModel(application) {

    private val gameRepository = GameRepositoryImpl(application)
    private val roundRepository = RoundRepositoryImpl(application)

    private val getGameUseCase = GetGameUseCase(gameRepository)
    private val getRoundListUseCase = GetRoundListUseCase(roundRepository)

    private val _game: MutableLiveData<GameModel> = MutableLiveData()
    val game: LiveData<GameModel> get() = _game

    private val _roundList: MutableLiveData<List<RoundModel>> = MutableLiveData()
    val roundList: LiveData<List<RoundModel>> get() = _roundList

    fun getGame(gameId: Int) {
        viewModelScope.launch {
            val game = getGameUseCase(gameId)
            _game.value = game
            getRoundList(gameId)
        }
    }

    private fun getRoundList(gameId: Int) {
        getRoundListUseCase(gameId).observeForever { roundList ->
            _roundList.value = roundList ?: emptyList()
        }
    }


}