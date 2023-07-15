package ru.steelwave.unonew.data.mapper

import ru.steelwave.unonew.data.database.model.RoundDbModel
import ru.steelwave.unonew.data.database.model.ScoreDbModel
import ru.steelwave.unonew.domain.entity.RoundModel

object RoundMapper {
    fun mapToDbModel(roundModel: RoundModel) =
        ScoreMapper.mapListEntityToDbModel(roundModel.scores)

    fun mapToModel(roundDbModel: RoundDbModel) =
        ScoreMapper.mapListDbModelToEntity(roundDbModel.scores)
}