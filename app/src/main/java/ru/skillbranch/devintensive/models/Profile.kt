package ru.skillbranch.devintensive.models

data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val nickName: String="John Doe"
    val rank: String = "Junior Android Developer"


    fun toMap(): Map<String, Any> = mapOf( "nickname" to nickName,
    "firstname" to firstName,
    "rank" to rank,
    "lastname" to lastName,
    "about" to about,
    "repository" to repository,
    "rating" to rating,
    "respect" to respect)
}