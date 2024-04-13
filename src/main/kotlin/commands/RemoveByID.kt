package commands

import org.requestManager
import utilities.CommandResult

class RemoveByID: AbstractCommand("remove_by_id id","удалить элемент из коллекции по его id"){
    override fun execute(str: String): CommandResult {
        return requestManager.removeByIdRequest(str)
    }
}