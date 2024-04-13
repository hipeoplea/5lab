package utilities


import exceptions.*
import java.util.*
import dragon_settings.*

import java.io.FileNotFoundException
import kotlin.system.exitProcess

/**
 * ConsoleRW
 *
 * @property scan
 * @constructor Create ConsoleRW
 */
class ConsoleRW(private var scan: Scanner) {
    private var scriptMode = false

    /**
     * Set scanner
     *
     * @param scan
     */
    fun setScan(scan: Scanner) {
        this.scan = scan
    }

    /**
     * Get scanner
     *
     * @return scanner of the fabrique
     */
    fun getScan(): Scanner {
        return scan
    }

    /**
     * Set script not in progress
     *
     */
    fun setScriptNotInProgress() {
        this.scriptMode = false
    }

    /**
     * Set script in progress
     *
     */
    fun setScriptInProgress() {
        this.scriptMode = true
    }


     fun askForDragonName(): String {
        var dragonName: String
        while (true) {
            try {
                if (!scriptMode) print("Введите имя дракона:\n>")
                dragonName = scan.nextLine().trim()
                if (dragonName == "") throw EmptyException()
                break
            } catch (e: EmptyException) {
                println("Название не может быть пустым!")
            }

        }
        return dragonName
    }


    private fun askForX(): Double {
        var strX: String
        var x: Double
        while (true) {
            try {
                if (!scriptMode) print("Введите координату x:\n>")
                strX = scan.nextLine().trim()
                if (strX == "") throw EmptyException()
                x = strX.toDouble()
                break
            } catch (e: NumberFormatException) {
                println("error: Координата x должна быть числом с плавающей точкой!")
            } catch (e: EmptyException) {
                println("error: Координата x не может быть пустой!")
            }
        }
        return x
    }

    private fun askForY(): Long {
        var strY: String
        var y: Long
        while (true) {
            try {
                if (!scriptMode) print("Введите координату Y:\n>")
                strY = scan.nextLine().trim()
                if (strY == "") throw EmptyException()
                y = strY.toLong()
                break
            } catch (e: NumberFormatException) {
                println("error: Координата Y должна быть числом!")
            } catch (e: EmptyException) {
                println("error: Координата Y не может быть пустой!")
            }
        }
        return y
    }

    private fun askForDepth(): Double {
        var strDepth: String
        var depth: Double
        while (true) {
            try {
                if (!scriptMode) print("Введите глубину пещеры:\n>")
                strDepth = scan.nextLine().trim()
                if (strDepth == "") throw EmptyException()
                depth = strDepth.toDouble()
                break
            } catch (e: NumberFormatException) {
                println("error: Глубина должна быть числом c плавающей точкой!")
            } catch (e: EmptyException) {
                println("error: Глубина не может быть незаполненной!")
            }
        }
        return depth
    }


    private fun askForAge(): Long {
        var strAge: String
        var age: Long
        while (true) {
            try {
                if (!scriptMode) print("Введите возраст Дракона:\n>")
                strAge = scan.nextLine().trim()
                if (strAge == "") throw EmptyException()
                age = strAge.toLong()
                if (age < 0) throw FalseFormatException()
                break
            } catch (e: EmptyException) {
                println("Возраст не может быть пустой строкой")
            } catch (e: NumberFormatException) {
                println("Возраст должен быть числом")
            } catch (e: FalseFormatException) {
                println("Возраст должен быть больше 0")
            }

        }
        return age
    }

    private fun askForSpeaking(): Boolean {
        var strSpeaking: String
        while (true) {
            try {
                if (!scriptMode) println("Умеет ли говорить дракон (yes/no)>")
                strSpeaking = scan.nextLine().trim()
                if (strSpeaking != "yes" && strSpeaking != "no") throw OutOfLimitException()
                break
            } catch (e: OutOfLimitException) {
                println("Неверное значение")

            }

        }
        return strSpeaking == "yes"
    }


    private fun askForColor(): Color {
        var colorStr: String
        var color: Color
        while (true) {
            try {
                if (!scriptMode) {
                    println("Список цветов - " + Color.getEnums())
                    println("Введите цвет дракона: ")
                }
                colorStr = scan.nextLine().trim()
                if (colorStr == "") throw EmptyException()
                color = Color.valueOf(colorStr.uppercase(Locale.getDefault()))
                break
            } catch (e: EmptyException) {
                println("Цвет не выбран")
            } catch (e: IllegalArgumentException) {
                println("error: Такого цвета нет в списке!")
            }
        }
        return color
    }

    private fun askForCharacter(): DragonCharacter {
        var characterStr: String
        var character: DragonCharacter
        while (true) {
            try {
                if (!scriptMode) {
                    println("Список характеров - " + DragonCharacter.getEnums())
                    println("Введите характер дракона: ")
                }
                characterStr = scan.nextLine().trim()
                if (characterStr == "") throw EmptyException()
                character = DragonCharacter.valueOf(characterStr.uppercase(Locale.getDefault()))
                break
            } catch (e: EmptyException) {
                println("Характер не выбран")
            } catch (e: IllegalArgumentException) {
                println("error: Такого Характера нет в списке!")
            }
        }
        return character
    }

    fun askForFileName(): String {
        var fileName: String
        while (true) {
            try {
                println("Введите название файла:")
                fileName = scan.nextLine().trim()
                if (fileName == "") throw EmptyException()
                if (fileName.contains(Regex("[^a-z^A-Z0-9]"))) throw FalseFormatException()
                fileName += ".xml"
                break
            } catch (e: EmptyException) {
                println("error: Название не может быть пустым!")
            } catch (e: FalseFormatException) {
                println("error: Название должно содержать только буквы!")
            } catch (e: FileNotFoundException) {
                println("error: Файла с таким названием нету!")
            }
        }
        return fileName
    }

    fun askForCommandArguments(str1: String): String {
        if (str1 in listOf("add", "add_if_min", "update")) {
            return askForDragonName() + " " + askForDepth() + " " + askForSpeaking() + " " + askForAge() + " " + askForX() + " " + askForY() + " " + askForCharacter() + " " + askForColor()
        }
        return ""
    }


    fun checkfile(rw: FileManager): PriorityQueue<Dragon> {
        while (true)
        try {
            val result = rw.readCollection()
            return result
        } catch (e: FalseFormatException) {
            println("В файле обнаружены ошибки")
            return PriorityQueue<Dragon>()
        }
    }

    fun askQuestion(argument: String): Boolean {
        val question = "$argument (yes/no):\n>"
        var answer: String
        while (true) {
            try {
                print(question)
                answer = scan.nextLine().trim()
                if (answer != "yes" && answer != "no") throw OutOfLimitException()
                break
            } catch (e: OutOfLimitException) {
                println("error: Ответ должен быть yes или no")
            }
        }
        return answer == "yes"
    }


}