package commands

import org.scan
import org.execute
import org.consoleRW
import utilities.CommandResult
import utilities.ConsoleManager

class ExecuteScript : AbstractCommand(
    "execute_script file_name",
    "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, " +
            "в котором их вводит пользователь в интерактивном режиме."
) {
    override fun execute(str: String): CommandResult {
        val consoleManager = ConsoleManager(scan, execute, consoleRW)
        consoleManager.checkScript(str)
        return CommandResult(true)
    }
}