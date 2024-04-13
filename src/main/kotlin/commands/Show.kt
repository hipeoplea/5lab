package commands

import org.requestManager
import utilities.CommandResult

class Show :
    AbstractCommand(
        "show",
        "вывести в стандартный поток вывода все элементы коллекции в строковом представлении"
    ) {
    override fun execute(str: String): CommandResult {
        return requestManager.showRequest(str)
    }
}