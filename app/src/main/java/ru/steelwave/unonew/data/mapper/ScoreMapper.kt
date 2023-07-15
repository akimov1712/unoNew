package ru.steelwave.unonew.data.mapper

import ru.steelwave.unonew.data.database.model.ScoreDbModel
import ru.steelwave.unonew.domain.entity.ScoreModel
import ru.steelwave.unonew.domain.entity.UserModel

object ScoreMapper {

    fun mapDbModelToEntity(score: ScoreDbModel) = ScoreModel(
        user = UserMapper.mapDbModelToEntity(score.user),
        countPoints = score.countPoints
    )

    fun mapEntityToDbModel(score: ScoreModel) = ScoreDbModel(
        user = UserMapper.mapEntityToDbModel(score.user),
        countPoints = score.countPoints
    )

    fun mapListDbModelToEntity(scoreList: List<ScoreDbModel>) = scoreList.map {
        mapDbModelToEntity(it)
    }

    fun mapListEntityToDbModel(scoreList: List<ScoreModel>)= scoreList.map {
        mapEntityToDbModel(it)
    }

}