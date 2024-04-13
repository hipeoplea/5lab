package utilities

import commands.AbstractCommand
import commands.*
import commands.RemoveByID
import dragon_settings.DragonCharacter
import java.io.File
import java.util.*


class LimitDeque<T>(private val limitSize: Int): ArrayDeque<T>() {
    override fun push(p0: T) {
        if (this.size >= limitSize) pollLast()
        super.push(p0!!)
    }
}

class Executer(private var consoleRW: ConsoleRW) {
    private var commandList = LinkedHashMap<String, AbstractCommand>()
    private var history: Deque<String> = LimitDeque(6)


    init {
        makeListOfCommands()
    }

    /**
     * make the list of the commands
     *
     */
    private fun makeListOfCommands() {
        commandList["help"] = Help()
        commandList["info"] = Info()
        commandList["show"] = Show()
        commandList["add"] = Add()
        commandList["update"] = Update()
        commandList["remove_by_id"] = RemoveByID()
        commandList["clear"] = Clear()
        commandList["save"] = Save()
        commandList["execute_script"] = ExecuteScript()
        commandList["exit"] = Exit()
        commandList["count_by_character"] = CountByCharacter()
        commandList["count_less_than_character"] = CountLessThanCharacter()
        commandList["add_if_min"] = AddIfMin()
        commandList["history"] = History()
        commandList["print_descending"] = PrintDescending()
        commandList["remove_greater"] = RemoveGreater()
    }


    fun executeCommand(str1: String, str2: String): Boolean? {
        if (checkCommand(str1)) {
            if (checkSymbols(str1, str2)) {
                println("Используется команда $str1")
                addToHistory(str1)
                commandList[str1]?.execute((str2 + " " + consoleRW.askForCommandArguments(str1)).trim()).let {
                    if (it?.message != null) println(it.message)
                    return it?.commandComplicated
                }
            }
            else{
                return false
            }
        } else {
            println("error: Неправильно введена команда!")
            return false
        }
    }

    fun checkCommand(str: String): Boolean {
        return commandList.containsKey(str)
    }


    fun addToHistory(command: String) {
        history.push(command)
    }

    fun getHistory(): String{
        return if (history.isNotEmpty()){
            history.toString()
        } else "Команды ещё не были введены"
    }


    fun checkSymbols(str1: String, str2: String): Boolean {
        if (str1 in listOf(
                "help",
                "info",
                "show",
                "add",
                "clear",
                "save",
                "exit",
                "history",
                "add_if_min",
                "remove_greater",
                "print_descending"
            )
        ) {
            return if (str2.isEmpty()) {
                true
            } else {
                println("error: После команды $str1 ничего не должно быть!")
                false
            }
        }
        if (str1 in listOf("update", "remove_by_id")) {
            return try {
                str2.toInt()
                true
            } catch (e: NumberFormatException) {
                println("error: id это число!")
                false
            }
        }
        if (str1 in listOf("count_by_character", "count_less_than_character")) {
            return try {
                DragonCharacter.valueOf(str2.uppercase(Locale.getDefault()))
                true
            } catch (e: IllegalArgumentException) {
                println("error: Нет такого характера!")
                false
            }
        }
        if (str1 in listOf("execute_script")) {
            return if (File(str2).exists()) {
                true
            } else {
                println("error: Файла $str2 не существует!")
                false
            }
        }
        return false
    }


    fun help(): String {
        var listOfCommands = ""
        for (command in commandList) {
            listOfCommands += command.value.getName() + ": " + command.value.getDescription() + "\n"
        }
        return listOfCommands.substring(0, listOfCommands.length - 2)
    }

}