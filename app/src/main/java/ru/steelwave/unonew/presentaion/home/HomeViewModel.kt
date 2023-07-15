package ru.steelwave.unonew.presentaion.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.repository.GameRepositoryImpl
import ru.steelwave.unonew.data.repository.UserRepositoryImpl
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.entity.ScoreModel
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.domain.useCase.game.AddGameUseCase
import ru.steelwave.unonew.domain.useCase.game.GetAllGamesUseCase
import ru.steelwave.unonew.domain.useCase.game.GetGameUseCase
import ru.steelwave.unonew.domain.useCase.user.AddUserUseCase
import ru.steelwave.unonew.domain.useCase.user.DeleteUserUseCase
import ru.steelwave.unonew.domain.useCase.user.GetAllUserUseCase
import ru.steelwave.unonew.domain.useCase.user.GetUserUseCase

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepositoryImpl(application)
    private val gameRepository = GameRepositoryImpl(application)

    private val addUserUseCase = AddUserUseCase(userRepository)
    private val getAllUsersUseCase = GetAllUserUseCase(userRepository)
    private val getUserUseCase = GetUserUseCase(userRepository)
    private val deleteUserUseCase = DeleteUserUseCase(userRepository)

    private val addGameUseCase = AddGameUseCase(gameRepository)
    private val getAllGamesUseCase = GetAllGamesUseCase(gameRepository)

    private val _closeModalAddUser = MutableLiveData<Unit>()
    val closeModalUser: LiveData<Unit>
        get() = _closeModalAddUser

    private val _closeModalAddGame = MutableLiveData<Unit>()
    val closeModalGame: LiveData<Unit>
        get() = _closeModalAddGame

    val userList = getAllUsersUseCase()
    val gameList = getAllGamesUseCase()

    fun deleteUser(user: UserModel){
        viewModelScope.launch {
            deleteUserUseCase(user)
        }
    }

    fun addGame(inputTarget: String?) {
        viewModelScope.launch {
            if (userList.value?.size == 0){
                finishModalAddGame()
            } else{
                userList.value?.let { participants ->
                    val targetPoints = parseTarget(inputTarget)
                    val leadingUser = ScoreModel(participants[0], 0)
                    val creationDate = System.currentTimeMillis()
                    val game = GameModel(
                        participants = participants,
                        targetPoints = targetPoints,
                        leadingUser = leadingUser,
                        creationDate = creationDate
                    )
                    addGameUseCase(game)
                } ?: finishModalAddGame()
            }
        }
    }

    fun addUser(inputName: String?) {
        viewModelScope.launch {
            val name = parseName(inputName)
            if (name == DEFAULT_NAME) {
                finishModalAddUser()
            } else {
                val user = UserModel(name = name)
                addUserUseCase(user)
            }
        }
    }

    private fun parseTarget(inputTarget: String?): Int {
        return try {
            inputTarget?.trim()?.toInt() ?: DEFAULT_TARGET
        } catch (ex: Exception) {
            DEFAULT_TARGET
        }
    }

    private fun parseName(inputName: String?): String {
        return try {
            inputName?.trim() ?: DEFAULT_NAME
        } catch (ex: java.lang.Exception) {
            DEFAULT_NAME
        }
    }

    private fun findMaxScore(scores: List<ScoreModel>): ScoreModel {
        var maxScoreModel: ScoreModel = scores[0]
        var maxPoints: Int = maxScoreModel.countPoints

        for (score in scores) {
            if (score.countPoints > maxPoints) {
                maxScoreModel = score
                maxPoints = score.countPoints
            }
        }
        return maxScoreModel
    }

    private fun finishModalAddUser() {
        _closeModalAddUser.value = Unit
    }

    private fun finishModalAddGame() {
        _closeModalAddGame.value = Unit
    }

    companion object {
        private const val DEFAULT_TARGET = 500
        private const val DEFAULT_NAME = ""
    }

}