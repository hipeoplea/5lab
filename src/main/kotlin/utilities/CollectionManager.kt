package utilities

import dragon_settings.Dragon
import dragon_settings.DragonCharacter
import java.time.LocalDateTime
import java.util.PriorityQueue

class CollectionManager(private var dragonPriorityQueue: PriorityQueue<Dragon>) {
    private var lastSaveTime: LocalDateTime? = null

    fun getDragonCollection(): PriorityQueue<Dragon> {
        return dragonPriorityQueue
    }

    fun getfirst(): String{
        return dragonPriorityQueue.peek().getName()
    }


    fun getLastSaveTime(): String{
        return if (lastSaveTime == null) " сохранение ещё не производилось"
        else lastSaveTime.toString()
    }

    fun getCollectionType(): String{
        return dragonPriorityQueue.javaClass.typeName
    }

    fun getCollectionSize(): Int{
        return dragonPriorityQueue.size
    }

    fun getElementById(id: Int): Dragon?{
        return dragonPriorityQueue.find { dragon -> dragon.getId() == id }
    }

    fun generateId(): Int{
        return if (dragonPriorityQueue.isEmpty()) 1
        else dragonPriorityQueue.last().getId() + 1
    }


    fun setLastSaveTime(data: LocalDateTime) {
        this.lastSaveTime = data
    }

    fun addDragon(data: Dragon){
        this.dragonPriorityQueue.add(data)
    }

    fun removeDragon(data: Dragon){
        this.dragonPriorityQueue.remove(data)
    }

    fun clearDragon(){
        this.dragonPriorityQueue.clear()
    }

    fun removeGreater(name: String): Int{
        val oldSize = dragonPriorityQueue.size
        this.dragonPriorityQueue.removeIf { dragon -> dragon.getName() > name }
        return oldSize - this.dragonPriorityQueue.size
    }

    fun countByCharacter(dragonCharacter: DragonCharacter): Int{
        return this.dragonPriorityQueue.count { dragon -> dragon.getCharacter() == dragonCharacter }
    }

    fun countLessThanCharacter(dragonCharacter: DragonCharacter): Int{
        return this.dragonPriorityQueue.count { dragon -> dragon.getCharacter() > dragonCharacter }
    }

    fun getDescending(): String{
        return this.dragonPriorityQueue.sortedByDescending { dragon -> dragon.getId() }.toString()

    }


    override fun toString(): String {
        if (dragonPriorityQueue.isEmpty()) return "Коллекция пустая"

        var dragonCollection = ""
        for(dragon in dragonPriorityQueue) {
            dragonCollection += dragon
            if(dragon != dragonPriorityQueue.last()) dragonCollection += "\n"
        }
        return dragonCollection
    }



}