package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        //TODO FIX ME!
        val parts: List<String>? = fullName?.split(" ")?.filter { !it.isBlank() }

        val firstName = parts?.getOrNull(0)

        val lastName = parts?.getOrNull(1)

        return (if (firstName.isNullOrEmpty()) null else firstName) to

                (if (lastName.isNullOrEmpty()) null else lastName)

    }

    fun transliteration(payLoad: String,divider:String=" "): String {
        TODO("not implemented")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("not implemented")
    }
}