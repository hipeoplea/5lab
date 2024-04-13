package org
import utilities.*
import java.util.*

val scan = Scanner(System.`in`)
val consoleRW: ConsoleRW = ConsoleRW(scan)
val fileManager: FileManager = FileManager(consoleRW.askForFileName(), consoleRW)
val collectionManager = CollectionManager(consoleRW.checkfile(fileManager))
val execute = Executer(consoleRW)
val requestManager = Requests(collectionManager, fileManager)


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val test = ConsoleManager(scan, execute, consoleRW)
    test.run()
}