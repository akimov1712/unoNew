package ru.steelwave.unonew.presentaion.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
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
        viewModelScope.launch {
            val user = getUserUseCase(userId).copy()
            user.roundsWon++
            user.totalPoints += score
            if (user.maxPoints < score){
                user.maxPoints = score
            }
            if (user.minPoints > score || user.minPoints == 0){
                user.minPoints = score
            }

            val copyGame = getGameUseCase(gameId).copy()
            val roundListGame = getRoundListUseCase(gameId).value
            Log.d("sis", roundListGame.toString())

            if (roundListGame?.isNotEmpty() == true){
                val newRound = roundListGame.last().copy()
                val newScore = newRound.scores.toList()

                var leadingUser = newScore[0]

                for (i in newScore){
                    if (i.user == user){
                        i.countPoints += score
                        if (i.countPoints >= leadingUser.countPoints){
                            copyGame.leadingUser = i
                            leadingUser = i
                        }
                        if (i.countPoints >= copyGame.targetPoints){
                            copyGame.isFinished = true
                            i.user.wins++
                        }
                    }
                }
                newRound.roundId++
                addRoundUseCase(newRound)
            } else {
                val newScoreList = mutableListOf<ScoreModel>()
                for (user in copyGame.participants){
                    newScoreList.add(ScoreModel(user, 0))
                }
                newScoreList.map {
                    if (it.user == user){
                        it.countPoints = score
                    }
                }
                val newRound = RoundModel(gameId = copyGame.gameId, numRoundInGame = 1, scores = newScoreList)
                addRoundUseCase(newRound)
            }

            addGameUseCase(copyGame)
            addUserUseCase(user)
            closeFragment()
        }
    }

    private fun closeFragment(){
        _closeFragment.value = Any()
    }

}