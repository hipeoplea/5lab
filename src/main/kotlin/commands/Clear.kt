package commands

import org.requestManager
import utilities.CommandResult

class Clear : AbstractCommand("clear", "очистить коллекцию") {
    override fun execute(str: String): CommandResult {
        return requestManager.clearRequest(str)
    }
}