package commands

import commands.AbstractCommand
import org.requestManager
import utilities.CommandResult

class CountByCharacter : AbstractCommand(
    "count_by_character character",
    "вывести количество элементов, значение поля character которых равно заданному"
) {
    override fun execute(str: String): CommandResult {
        return requestManager.countByCharacterRequest(str)
    }
}