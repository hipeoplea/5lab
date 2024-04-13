package commands

import commands.AbstractCommand
import org.requestManager
import utilities.CommandResult

class CountLessThanCharacter : AbstractCommand(
    "count_less_than_character character",
    "вывести количество элементов, значение поля character которых меньше заданного"
) {
    override fun execute(str: String): CommandResult {
        return requestManager.countLessThanCharacterRequest(str)
    }
}