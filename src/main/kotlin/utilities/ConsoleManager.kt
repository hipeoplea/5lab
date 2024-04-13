package utilities

import exceptions.EmptyException
import exceptions.FalseFormatException
import java.util.Scanner
import java.io.File
import java.io.FileNotFoundException

class ConsoleManager(
    private var scan: Scanner,
    private var executer: Executer,
    private var consoleRW: ConsoleRW
) {


    fun run() {
        println("Начало работы программы, для справки по командам вызовите help")
        interactiveMode()

    }

    private fun interactiveMode() {
        var newInput: Array<String>
        while (true) {
            newInput = (scan.nextLine().trim() + " ").split(Regex(" ")).toTypedArray()
            executer.executeCommand(newInput[0], newInput[1])

        }
    }

    fun scriptMode(str: String) {
        try {
            val file = File(str)
            val scanFile = Scanner(file)
            if (!scanFile.hasNext()) throw EmptyException()
            val oldScan = consoleRW.getScan()
            consoleRW.setScan(scanFile)
            consoleRW.setScriptInProgress()
            var newInput: Array<String>

            while (scanFile.hasNextLine()) {
                newInput = (scanFile.nextLine().trim() + " ").split(" ").toTypedArray()
                executer.executeCommand(newInput[0], newInput[1])

            }

            consoleRW.setScan(oldScan)
            consoleRW.setScriptNotInProgress()
        } catch (e: FileNotFoundException) {
            println("error: Файл с таким именем не найден!")
        } catch (e: EmptyException) {
            println("error: Файл пустой!")
        }
    }

    fun checkScript(str: String) {
        try {
            var exitIsThere = false
            var recursionIsThere = false
            var continueDoingScriptCounter = 0

            val file = File(str)
            val scanFile = Scanner(file)
            if (!scanFile.hasNext()) throw EmptyException()
            consoleRW.setScan(Scanner(System.`in`))
            var newInput: Array<String>

            while (scanFile.hasNextLine() && continueDoingScriptCounter >= 0) {
                newInput = (scanFile.nextLine().trim() + " ").split(" ").toTypedArray()
                if (executer.checkCommand(newInput[0])) {
                    if (executer.checkSymbols(newInput[0], newInput[1])) {
                        when (newInput[0]) {
                            "exit" -> {
                                exitIsThere = true
                            }

                            "execute_script" -> {
                                recursionIsThere = true
                            }

                            "add" -> {
                                continueDoingScriptCounter += 12
                            }
                        }
                    } else continueDoingScriptCounter -= 1
                } else continueDoingScriptCounter -= 1
            }
            if (exitIsThere) {
                if (!consoleRW.askQuestion("В скрипте есть выход из программы, выполнить скрипт?")) continueDoingScriptCounter -= 1
            }

            if (recursionIsThere) {
                if (!consoleRW.askQuestion("В скрипте есть рекурсия, выполнить скрипт?")) continueDoingScriptCounter -= 1
            }

            if (continueDoingScriptCounter == 0) {
                scriptMode(str)
            } else println("скрипт отменен")

        } catch (e: FileNotFoundException) {
            println("error: Файл с таким именем не найден!")
        } catch (e: EmptyException) {
            println("error: Файл пустой!")
        }
    }

}
