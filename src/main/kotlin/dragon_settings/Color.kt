package dragon_settings

enum class Color {
    RED,
    YELLOW,
    WHITE,
    ORANGE;

    companion object {
        fun getEnums(): String {
            var strEnums = ""
            for (genre in entries) {
                strEnums += genre.name + ", "
            }
            return strEnums.substring(0..strEnums.length - 3)

        }
    }
}