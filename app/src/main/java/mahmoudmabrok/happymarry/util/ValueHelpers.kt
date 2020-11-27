package mahmoudmabrok.happymarry.util

object ValueHelpers {

    fun formatTime(total: Long): String {
        val minutes = total / 60
        val seconds = (total % 60).toString().padStart(2, '0')
        return "$minutes:$seconds"
    }
}