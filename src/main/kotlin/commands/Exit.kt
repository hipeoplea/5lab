package commands

import commands.AbstractCommand
import org.requestManager
import utilities.CommandResult

class Exit : AbstractCommand("exit", "завершить программу (без сохранения в файл)") {
    override fun execute(str: String): CommandResult {
        return requestManager.exitRequest(str)
    }
}