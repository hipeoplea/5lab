package utilities

import dragon_settings.*
import org.consoleRW
import org.execute
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*
import utilities.ConsoleRW
import kotlin.system.exitProcess

class Requests(
    private var collectionManager: CollectionManager,
    private var fileManager: FileManager,
) {

    fun helpRequest(str: String): CommandResult {
        return if (str.isEmpty()) CommandResult(true, execute.help())
        else CommandResult(false, "error: После команды help не должно быть аргументов")
    }

    fun exitRequest(str: String): CommandResult {
        if (str.isEmpty()) exitProcess(0)
        else return CommandResult(false, "error: После команды exit не должно быть аргументов")
    }

    fun infoRequest(str: String): CommandResult {
        return if (str.isEmpty())
            CommandResult(
                true, "Информация о коллекции:\n" +
                        "Тип коллекции:" + collectionManager.getCollectionType() + "\n" +
                        "Размер коллекции:" + collectionManager.getCollectionSize() + "\n" +
                        "Дата и время последнего сохранения" + collectionManager.getLastSaveTime()
            )
        else CommandResult(false, "error: После команды info не должно быть аргументов")
    }


    fun addRequest(id: Int?, str: String): CommandResult {
        val listOfArgs = str.split(Regex(" ")).toTypedArray()
        return if (listOfArgs.size == 8) {
            collectionManager.addDragon(
                Dragon(
                    id ?: collectionManager.generateId(),
                    listOfArgs[0],
                    ZonedDateTime.now(),
                    DragonCave(listOfArgs[1].toDouble()),
                    listOfArgs[2].toBoolean(),
                    listOfArgs[3].toLong(),
                    Coordinates(listOfArgs[4].toDouble(), listOfArgs[5].toLong()),
                    DragonCharacter.valueOf(listOfArgs[6].uppercase(Locale.getDefault())),
                    Color.valueOf(listOfArgs[7].uppercase(Locale.getDefault()))
                )
            )
            return CommandResult(true, "Дракон создан и добавлен в коллекцию")
        } else CommandResult(false, "error: Неверные аргументы")
    }

    fun addIfMin(str: String): CommandResult{
        var listOfArgs = str.split(Regex(" ")).toTypedArray()
        return if (listOfArgs.size == 8){
            if (listOfArgs[0] != "" && listOfArgs[0] < collectionManager.getfirst()){
                val list = listOfArgs.toMutableList()
                list.removeAt(0)
                listOfArgs = list.toTypedArray()

                return addRequest(null, listOfArgs.joinToString(separator = " "))
            }
            else CommandResult (false, "элемент не минимален")

        } else CommandResult(false, "error: Неверные аргументы")
    }

    fun clearRequest(str: String): CommandResult {
        if (str.isEmpty()) {
            if (collectionManager.getCollectionSize() == 0) return CommandResult(false, "error: коллекция пустая")
            else {
                collectionManager.clearDragon()
                return CommandResult(true, "коллекция очищена")
            }
        } else return CommandResult(false, "error: После команды clear не должно быть аргументов")
    }

    fun countByCharacterRequest(str: String): CommandResult {
        return if (str != "") {
            if (collectionManager.getCollectionSize() == 0){
                 CommandResult(true, "коллекция пустая")
            }
            else{
                try {
                    val character = DragonCharacter.valueOf(str.uppercase(Locale.getDefault()))
                    CommandResult(true, collectionManager.countByCharacter(character).toString())
                }
                catch (e: IllegalArgumentException){
                    CommandResult(false, "Такого характера не существует")
                }
                 }
        } else CommandResult(false, "Не указан характер по которому нужно совершить отбор")
    }

    fun countLessThanCharacterRequest(str: String): CommandResult{
        return if (str != "") {
            if (collectionManager.getCollectionSize() == 0){
                CommandResult(true, "коллекция пустая")
            }
            else{
                try {
                    val character = DragonCharacter.valueOf(str.uppercase(Locale.getDefault()))
                    CommandResult(true, collectionManager.countLessThanCharacter(character).toString())
                }
                catch (e: IllegalArgumentException){
                    CommandResult(false, "Такого характера не существует")
                }
            }
        } else CommandResult(false, "Не указан характер по которому нужно совершить отбор")
    }

    fun historyRequest(str: String): CommandResult {
        return if (str.isEmpty()) CommandResult(true, execute.getHistory())
        else CommandResult(false)
    }

    fun printDescendingRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            if (collectionManager.getCollectionSize() == 0) CommandResult(true, "Коллекция пустая")
            else CommandResult(true, collectionManager.getDescending())
        } else CommandResult(false, "После команды print_descending не должно быть аргументов")
    }

    fun removeGreaterRequest(str: String): CommandResult{
        return if (str.split(Regex(" ")).toTypedArray().size == 1) {
            if (collectionManager.getCollectionSize() == 0) {
                CommandResult(true, "Коллекция пустая")
            } else {
                CommandResult(true, "Было удалено " + collectionManager.removeGreater(consoleRW.askForDragonName()) + " драконов, у которых больше имя")
            }
        } else CommandResult(false, "error: После команды remove_greater не должно быть аргументов")
    }


    fun removeByIdRequest(str: String): CommandResult {
        if (str.isNotEmpty()) {
            try {
                if (collectionManager.getCollectionSize() == 0) {
                    return CommandResult(true, "Коллекция пустая")
                } else {
                    val dragon = collectionManager.getElementById(str.toInt()) ?: return CommandResult(true,"Нет дракона с таким id")
                    collectionManager.removeDragon(dragon)
                    return CommandResult(true, "Дракон удален")
                }
            } catch (e: NumberFormatException) { return CommandResult(false, "error: Неправильный ввод данных")
            }
        } else return CommandResult(false, "error: После команды remove_by_id должно быть указано id")
    }

    fun saveRequest(str: String): CommandResult {
        return if (str.isEmpty()) {
            fileManager.writeCollection(collectionManager.getDragonCollection())
            collectionManager.setLastSaveTime(LocalDateTime.now())
            return CommandResult(true, "коллекция успешно сохранена")
        } else CommandResult(false, "После команды save не должно быть аргументов")
    }

    fun showRequest(str: String): CommandResult {
        return if (str.isEmpty()) CommandResult(true, collectionManager.toString())
        else CommandResult(false, "После команды show не должно быть аргументов")
    }

    fun updateRequest(str: String): CommandResult {
        if (str.split(Regex(" ")).toTypedArray().size == 9) {
            try {
                if (collectionManager.getCollectionSize() == 0) {
                    return CommandResult(false, "Коллекция пустая")
                } else {
                    var listOfArgs = str.split(Regex(" ")).toTypedArray()
                    val dragon = collectionManager.getElementById(listOfArgs[0].toInt()) ?: return CommandResult(
                        false,
                        "Нет элемента с таким id"
                    )
                    val id = listOfArgs[0].toInt()

                    collectionManager.removeDragon(dragon)
                    val list = listOfArgs.toMutableList()
                    list.removeAt(0)
                    listOfArgs = list.toTypedArray()

                    return addRequest(id, listOfArgs.joinToString(separator = " "))

                }
            } catch (e: NumberFormatException) {
                return CommandResult(false, "error: Неправильный ввод данных")
            }
        } else return CommandResult(false, "error: неверный ввод аргументов")
    }

}