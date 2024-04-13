package utilities

import dragon_settings.Dragon
import exceptions.FalseFormatException
import nl.adaptivity.xmlutil.core.impl.multiplatform.IOException
import nl.adaptivity.xmlutil.serialization.XML
import org.execute
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.util.PriorityQueue

class FileManager(
    private var name: String,
    private var fabrique: ConsoleRW
) {



    /**
     * Write collection on the file
     *
     * @param collection
     */
    fun writeCollection(collection: PriorityQueue<Dragon>) {
        try {
            val list = ArrayList(collection)
            FileWriter(name).use { it.write(XML.encodeToString(list)) }
        } catch (e: IOException) {
            println("Невозможно сохранить!")
        }
    }

    /**
     * Read collection from the file
     *
     * @return collection
     */
    fun readCollection(): PriorityQueue<Dragon> {
        var fileReader: FileReader
        while (true) {
            try {
                fileReader = FileReader(name)
                break
            } catch (e: FileNotFoundException) {
                println("Файла с таким названием нету!")
                setName(fabrique.askForFileName())
            }
        }
        var char: Int
        var input = ""
        do {
            char = fileReader.read()
            if (char == -1) {
                break
            }
            input += char.toChar()
        } while (true)
        try {
            return PriorityQueue(XML.decodeFromString<List<Dragon>>(input))
        } catch (e: Exception){
            throw FalseFormatException()
        }

    }

    /**
     * Set name
     *
     * @param name
     */
    private fun setName(name: String) {
        this.name = name
    }
}