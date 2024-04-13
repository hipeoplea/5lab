package commands

import commands.AbstractCommand
import org.requestManager
import utilities.CommandResult

class AddIfMin : AbstractCommand(
    "add_if_min {element}",
    "добавить новый элемент в коллекцию, если его значение name меньше, " +
            "чем у наименьшего элемента этой коллекции удалить из коллекции все элементы, превышающие заданный"
) {
    override fun execute(str: String): CommandResult {
        return requestManager.addIfMin(str)

    }
}