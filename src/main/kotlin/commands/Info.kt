package commands

import org.requestManager
import utilities.CommandResult

class Info : AbstractCommand(
    "info",
    "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)"
) {
    override fun execute(str: String): CommandResult {
        return requestManager.infoRequest(str)
    }
}