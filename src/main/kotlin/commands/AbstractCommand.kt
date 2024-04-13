package commands

import utilities.CommandResult

/**
 * Absctract Command of the another commands
 *
 * @property name
 * @property description
 * @constructor Create Absctract command
 */
abstract class AbstractCommand(
    private val name: String,
    private val description: String
) {
    /**
     * Get name
     *
     * @return name of the command
     */
    fun getName(): String {
        return name;
    }

    /**
     * Get description of command
     *
     * @return description of the command
     */
    fun getDescription(): String {
        return description;
    }


    abstract fun execute(str: String): CommandResult


}