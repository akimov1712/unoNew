package ru.steelwave.unonew.data.mapper

import ru.steelwave.unonew.data.database.model.RoundDbModel
import ru.steelwave.unonew.domain.entity.RoundModel

object RoundMapper {
    fun mapEntityToDbModel(roundModel: RoundModel) = RoundDbModel(
        roundId = roundModel.roundId,
        gameId = roundModel.gameId,
        numRoundInGame = roundModel.numRoundInGame,
        scores = roundModel.scores.map {
            ScoreMapper.mapEntityToDbModel(it)
        }
    )

    fun mapDbModelToEntity(roundDbModel: RoundDbModel) = RoundModel(
        roundId = roundDbModel.roundId,
        gameId = roundDbModel.gameId,
        numRoundInGame = roundDbModel.numRoundInGame,
        scores = roundDbModel.scores.map {
            ScoreMapper.mapDbModelToEntity(it)
        }
    )

    fun mapEntityListToDbModelList(roundModelList: List<RoundModel>) = roundModelList.map{
        mapEntityToDbModel(it)
    }

    fun mapDbModelListToEntityList(roundDbModelList: List<RoundDbModel>) = roundDbModelList.map{
        mapDbModelToEntity(it)
    }

}