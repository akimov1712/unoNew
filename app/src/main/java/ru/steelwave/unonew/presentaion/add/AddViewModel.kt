package ru.steelwave.unonew.presentaion.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.repository.GameRepositoryImpl
import ru.steelwave.unonew.data.repository.RoundRepositoryImpl
import ru.steelwave.unonew.data.repository.UserRepositoryImpl
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.entity.RoundModel
import ru.steelwave.unonew.domain.entity.ScoreModel
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.domain.useCase.game.AddGameUseCase
import ru.steelwave.unonew.domain.useCase.game.GetGameUseCase
import ru.steelwave.unonew.domain.useCase.round.AddRoundUseCase
import ru.steelwave.unonew.domain.useCase.round.GetRoundListUseCase
import ru.steelwave.unonew.domain.useCase.user.AddUserUseCase
import ru.steelwave.unonew.domain.useCase.user.GetUserUseCase

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepositoryImpl(application)
    private val gameRepository = GameRepositoryImpl(application)
    private val roundRepository = RoundRepositoryImpl(application)

    private val getUserUseCase = GetUserUseCase(userRepository)
    private val getGameUseCase = GetGameUseCase(gameRepository)
    private val addUserUseCase = AddUserUseCase(userRepository)
    private val addGameUseCase = AddGameUseCase(gameRepository)
    private val addRoundUseCase = AddRoundUseCase(roundRepository)
    private val getRoundListUseCase = GetRoundListUseCase(roundRepository)

    private val _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> get() = _user

    private val _game: MutableLiveData<GameModel> = MutableLiveData()
    val game: LiveData<GameModel> get() = _game

    private val _closeFragment: MutableLiveData<Any> = MutableLiveData()
    val closeFragment: LiveData<Any> get() = _closeFragment

    private val _finishGame: MutableLiveData<Any> = MutableLiveData()
    val finishGame: LiveData<Any> get() = _finishGame

    fun getUser(userId: Int) {
        viewModelScope.launch {
            val user = getUserUseCase(userId)
            _user.value = user
        }
    }

    fun getGame(gameId: Int) {
        viewModelScope.launch {
            val game = getGameUseCase(gameId)
            _game.value = game
        }
    }

    fun updateData(score: Int, userId: Int, gameId: Int) {
        getRoundListUseCase(gameId).observeForever { roundList ->
            viewModelScope.launch {
                val copyUser = getUserUseCase(userId).copy()
                copyUser.roundsWon++
                copyUser.totalPoints += score
                if (copyUser.maxPoints < score) {
                    copyUser.maxPoints = score
                }
                if (copyUser.minPoints > score || copyUser.minPoints == 0) {
                    copyUser.minPoints = score
                }

                val copyGame = getGameUseCase(gameId).copy()

                if (roundList?.isNotEmpty() == true) {
                    val lastRound = roundList.last()
                    val newScoreList = lastRound.scores
                    newScoreList.map {
                        if (it.user.userId == copyUser.userId) {
                            it.countPoints += score
                            if (it.countPoints >= copyGame.targetPoints){
                                copyUser.wins++
                                copyGame.isFinished = true
                                finishGame()
                            }
                            if (it.countPoints > copyGame.leadingUser.countPoints){
                                copyGame.leadingUser = it
                            }
                        }
                    }
                    val newRound = RoundModel(
                        numRoundInGame = lastRound.numRoundInGame++,
                        scores = newScoreList,
                        gameId = copyGame.gameId
                    )
                    addRoundUseCase(newRound)
                } else {
                    val newScoreList = mutableListOf<ScoreModel>()
                    for (user in copyGame.participants) {
                        newScoreList.add(ScoreModel(user, 0))
                    }
                    newScoreList.map {
                        if (it.user.userId == copyUser.userId) {
                            it.countPoints = score
                        }
                        if (it.countPoints >= copyGame.targetPoints){
                            copyUser.wins++
                            copyGame.isFinished = true
                            finishGame()
                        }
                        if (it.countPoints > copyGame.leadingUser.countPoints){
                            copyGame.leadingUser = it
                        }
                    }
                    val newRound = RoundModel(
                        gameId = copyGame.gameId,
                        numRoundInGame = 0,
                        scores = newScoreList
                    )
                    addRoundUseCase(newRound)
                }

                addGameUseCase(copyGame)
                addUserUseCase(copyUser)
                closeFragment()
            }
        }
    }

    private fun closeFragment() {
        _closeFragment.value = Any()
    }

    private fun finishGame() {
        _finishGame.value = Any()
    }

}