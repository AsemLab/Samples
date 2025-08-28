package com.asemlab.inventory

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val s = buildSet {
            var r = "p"+(1000..9999).random()
            while (size != 1000)
                if (!contains(r))
                    add(r)
                else
                    r = "p"+(1000..9999).random()
        }

        print(s.joinToString(",\n"))
        assertEquals(1000, s.size)
    }
}