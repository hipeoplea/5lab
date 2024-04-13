package commands

import org.requestManager
import utilities.CommandResult

class Save : AbstractCommand("save", "сохранить коллекцию в файл") {
    override fun execute(str: String): CommandResult {
        return requestManager.saveRequest(str)
    }
}