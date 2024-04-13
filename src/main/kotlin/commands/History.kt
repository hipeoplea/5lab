package commands

import commands.AbstractCommand
import org.requestManager
import utilities.CommandResult

class History : AbstractCommand("history", "вывести последние 6 команд (без их аргументов)") {
    override fun execute(str: String): CommandResult {
        return requestManager.historyRequest(str)
    }
}