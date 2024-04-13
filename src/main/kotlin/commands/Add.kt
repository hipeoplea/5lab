package commands

import utilities.CommandResult
import org.requestManager

class Add : AbstractCommand("add {element}", "добавить новый элемент в коллекцию") {
    override fun execute(str: String): CommandResult {
        return requestManager.addRequest(null, str)
    }
}