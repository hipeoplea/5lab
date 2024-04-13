package commands

import org.requestManager
import utilities.CommandResult

class Update: AbstractCommand("update id {element}",
    "обновить значение элемента коллекции, id которого равен заданному") {
    override fun execute(str: String): CommandResult {
        return requestManager.updateRequest(str)
    }
}