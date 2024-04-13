package commands

import commands.AbstractCommand
import org.requestManager
import utilities.CommandResult

class PrintDescending : AbstractCommand(
    "print_descending",
    "вывести элементы коллекции в порядке убывания"
) {
    override fun execute(str: String): CommandResult {
        return requestManager.printDescendingRequest(str)
    }
}