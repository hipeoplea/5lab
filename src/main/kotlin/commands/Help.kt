package commands

import org.requestManager
import utilities.CommandResult

class Help : AbstractCommand("help", "вывести справку по доступным командам") {
    override fun execute(str: String): CommandResult {
        return requestManager.helpRequest(str)
    }
}