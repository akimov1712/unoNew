package ru.steelwave.unonew.data.mapper

import ru.steelwave.unonew.data.database.model.GameDbModel
import ru.steelwave.unonew.data.database.model.RoundDbModel
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.entity.RoundModel

object GameMapper {

    fun mapDbModelToEntity(game: GameDbModel) = GameModel(
        gameId = game.gameId,
        participants = UserMapper.mapListDbModelToListEntity(game.participants),
        leadingUser = ScoreMapper.mapDbModelToEntity(game.leadingUser),
        targetPoints = game.targetPoints,
        creationDate = game.creationDate,
        isFinished = game.isFinished
    )

    fun mapEntityToDbModel(game: GameModel) = GameDbModel(
        gameId = game.gameId,
        participants = UserMapper.mapListEntityToListDbModel(game.participants),
        leadingUser = ScoreMapper.mapEntityToDbModel(game.leadingUser),
        targetPoints = game.targetPoints,
        creationDate = game.creationDate,
        isFinished = game.isFinished
    )

    fun mapListDbModelToListEntity(gameList: List<GameDbModel>) = gameList.map {
        mapDbModelToEntity(it)
    }

}