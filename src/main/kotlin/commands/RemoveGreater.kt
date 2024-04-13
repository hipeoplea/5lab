package commands

import commands.AbstractCommand
import org.requestManager
import utilities.CommandResult

class RemoveGreater : AbstractCommand("remove_greater {element}",
    "удалить из коллекции все элементы, превышающие заданный") {
    override fun execute(str: String): CommandResult {
        return requestManager.removeGreaterRequest(str)
    }
}