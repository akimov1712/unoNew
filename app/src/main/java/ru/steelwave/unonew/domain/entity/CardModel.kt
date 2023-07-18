package ru.steelwave.unonew.domain.entity

data class CardModel (
    val valuePoint: Int,
    val image: Int,
    var countTouch: Int = 0
){
    fun incrementCountTouch() {
        countTouch++
    }

    fun decrementCountTouch() {
        countTouch--
    }
}