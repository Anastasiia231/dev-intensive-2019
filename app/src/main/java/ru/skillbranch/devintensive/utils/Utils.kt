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

    fun transliteration(payLoad: String, divider: String = " "): String {
        val mapping = payLoad.map {

            when (it) {

                ' ' -> divider

                'а' -> 'a'

                'б' -> 'b'

                'в' -> 'v'

                'г' -> 'g'

                'д' -> 'd'

                'е' -> 'e'

                'ё' -> 'e'

                'ж' -> "zh"

                'з' -> 'z'

                'и' -> 'i'

                'й' -> 'i'

                'к' -> 'k'

                'л' -> 'l'

                'м' -> 'm'

                'н' -> 'n'

                'о' -> 'o'

                'п' -> 'p'

                'р' -> 'r'

                'с' -> 's'

                'т' -> 't'

                'у' -> 'u'

                'ф' -> 'f'

                'х' -> 'h'

                'ц' -> 'c'

                'ч' -> "ch"

                'ш' -> "sh"

                'щ' -> "sh'"

                'ъ' -> ""

                'ы' -> 'i'

                'ь' -> ""

                'э' -> 'e'

                'ю' -> "yu"

                'я' -> "ya"

                'А' -> 'A'

                'Б' -> 'B'

                'В' -> 'V'

                'Г' -> 'G'

                'Д' -> 'D'

                'Е' -> 'E'

                'Ё' -> 'E'

                'Ж' -> "Zh"

                'З' -> 'Z'

                'И' -> 'I'

                'Й' -> 'I'

                'К' -> 'K'

                'Л' -> 'L'

                'М' -> 'M'

                'Н' -> 'N'

                'О' -> 'O'

                'П' -> 'P'

                'Р' -> 'R'

                'С' -> 'S'

                'Т' -> 'T'

                'У' -> 'U'

                'Ф' -> 'F'

                'Х' -> 'H'

                'Ц' -> 'C'

                'Ч' -> "Ch"

                'Ш' -> "Sh"

                'Щ' -> "Sh'"

                'Ъ' -> ""

                'Ы' -> 'I'

                'Ь' -> ""

                'Э' -> 'E'

                'Ю' -> "Yu"

                'Я' -> "Ya"

                else -> it

            }

        }
        return mapping.joinToString(separator = "")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var strf = firstName
        var strl = lastName
        return (if (firstName.isNullOrEmpty() && lastName.isNullOrEmpty()) null
        else if (firstName.isNullOrEmpty()) strl!![0].toString().toUpperCase()
        else if (lastName.isNullOrEmpty()) strf!![0].toString().toUpperCase()
        else
            strf!![0].toString().toUpperCase() + strl!![0].toString().toUpperCase())
        /*val first = firstName?.trimStart()?.firstOrNull()
        val second = lastName?.trimStart()?.firstOrNull()

        return listOfNotNull(first, second)
            .joinToString("")
            .toUpperCase()
            .takeIf { it.isNotEmpty() }*/

    }
    fun getPluralForm(pluralForms: String, count: Int): String {

        val forms = pluralForms.split(";")

        when (count % 10) {

            1 -> if (count % 100 != 11)
                return forms[0]

            in 2..4 -> if (count % 100 !in 12..14) {
                return forms[1]
            }
        }
        return forms[2]

    }
}