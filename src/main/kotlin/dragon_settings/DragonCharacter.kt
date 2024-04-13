package dragon_settings

enum class DragonCharacter {
    EVIL,
    GOOD,
    FICKLE;


    companion object {
        fun getEnums(): String {
            var strEnums = ""
            for (genre in DragonCharacter.entries) {
                strEnums += genre.name + ", "
            }
            return strEnums.substring(0..strEnums.length - 3)

        }
    }
}