package mahmoudmabrok.happymarry.util

import org.junit.Assert.assertEquals
import org.junit.Test

class ValueHelpersTest {

    @Test
    fun formatTime() {
        val input = 60
        val actual = ValueHelpers.formatTime(input)
        val expected = "1:00"
        assertEquals(expected, actual)
    }

    @Test
    fun `formatTime another test `() {
        val input = 62
        val actual = ValueHelpers.formatTime(input)
        val expected = "1:02"
        assertEquals(expected, actual)
    }
}