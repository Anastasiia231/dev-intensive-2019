package ru.skillbranch.devintensive.models


class Bender(var status : Status = Status.NORMAL, var question: Question = Question.NAME)
{
    fun askQuestion() : String
    {
        return question.question
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>>
    {
        var validation = isAnswerValid(answer)
        if(validation == "" )
        {
            return if (question.answers.contains(answer.toLowerCase()))
            {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            }
            else if (question == Question.IDLE)
            {
                question.question to status.color
            }
            else
            {
                if (status == Status.CRITICAL)
                {
                    status = status.nextStatus()
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                }
                else
                {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                }
            }
        }
        else return "$validation\n${question.question}" to status.color
    }

    private fun isAnswerValid(answer: String) : String
    {
        var error : String = ""
        when(question)
        {
            Question.NAME->
            {
                if(answer.isNullOrBlank() || answer[0].isLowerCase())
                    error = "Имя должно начинаться с заглавной буквы"
            }
            Question.PROFESSION->
            {
                if(answer.isNullOrBlank() || answer[0].isUpperCase())
                    error = "Профессия должна начинаться со строчной буквы"
            }
            Question.MATERIAL->
            {
                if(answer.matches(Regex(".*\\d.*")))
                    error = "Материал не должен содержать цифр"
            }
            Question.BDAY->
            {
                if(!answer.matches(Regex("-?\\d+(\\.\\d+)?")))
                    error = "Год моего рождения должен содержать только цифры"
            }
            Question.SERIAL->
            {
                if(!answer.matches(Regex("-?\\d+(\\.\\d+)?")) || answer.length != 7 )
                    error = "Серийный номер содержит только цифры, и их 7"
            }
            Question.IDLE->
            {
                error = ""
            }
        }
        return error
    }

    enum class Status(val color: Triple<Int, Int, Int>)
    {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus() : Status
        {
            return if(this.ordinal < values().lastIndex)
                values()[this.ordinal + 1]
            else
                values().first()
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion() = PROFESSION
            override fun validate(answer: String): String? =
                if (!answer[0].isUpperCase())
                    "Имя должно начинаться с заглавной буквы\n"
                else null
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion() = MATERIAL
            override fun validate(answer: String): String? =
                if (!answer[0].isLowerCase())
                    "Профессия должна начинаться со строчной буквы\n"
                else null
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion() = BDAY
            override fun validate(answer: String): String? =
                if (answer.contains(Regex("\\d")))
                    "Материал не должен содержать цифр\n"
                else null
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion() = SERIAL
            override fun validate(answer: String): String? =
                if (answer.contains(Regex("\\D")))
                    "Год моего рождения должен содержать только цифры\n"
                else null
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion() = IDLE
            override fun validate(answer: String): String? =
                if (!Regex("\\d{7}").matches(answer))
                    "Серийный номер содержит только цифры, и их 7\n"
                else null
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion() = IDLE
            override fun validate(answer: String): String? = ""
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String): String?
    }

}



/*
package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion() = when (question) {

        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        return if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            "Это неправильный ответ\n${question.question}" to status.color
        }

    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]

            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}*/
