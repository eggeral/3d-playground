package glmatrix

import util.assertEquals
import util.assertFalse
import util.assertTrue
import kotlin.test.Test

class Mat3Test {

    @Test
    fun create() {
        val identity = Mat3().identity()

        assertEquals(1.0, identity[0])
        assertEquals(0.0, identity[1])
        assertEquals(0.0, identity[2])

        assertEquals(0.0, identity[3])
        assertEquals(1.0, identity[4])
        assertEquals(0.0, identity[5])

        assertEquals(0.0, identity[6])
        assertEquals(0.0, identity[7])
        assertEquals(1.0, identity[8])
    }

    @Test
    fun testEquals() {
        val m1 = Mat3(1.0, 2.0, 3.0,
                4.0, 1.0, 1.0,
                3.0, 4.0, 5.0)

        val m2 = Mat3(1.0, 2.0, 3.0,
                4.0, 1.0, 1.0,
                3.0, 4.0, 5.0)

        val m3 = Mat3(0.0, 1.0, 2.0,
                3.0, 0.0, -1.0,
                5.0, 2.0, 5.0)

        assertTrue(m1.equals(m2))
        assertTrue(m2.equals(m1))
        assertFalse(m2.equals(m3))
        assertFalse(m3.equals(m2))
    }

    @Test
    fun testAddMatrix() {
        val m1 = Mat3(1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 8.0, 9.0)

        val m2 = Mat3(1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 8.0, 9.0)

        val expected = Mat3(2.0, 4.0, 6.0,
                8.0, 10.0, 12.0,
                14.0, 16.0, 18.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun testAddZero() {
        val m1 = Mat3(1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 8.0, 9.0)

        val m2 = Mat3(0.0, 0.0, 0.0,
                0.0, 0.0, 0.0,
                0.0, 0.0, 0.0)

        val expected = Mat3(1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 8.0, 9.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat3().identity()
        val m = Mat3(
                1.0, 2.0, 3.0,
                4.0, 1.0, 1.0,
                5.0, 4.0, 2.4)

        // test m * I
        assertTrue(m.equals(m * identity))
        // test I * m
        assertTrue(m.equals(identity * m))
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat3(1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 8.0, 9.0)

        val m2 = Mat3(3.0, 0.0, 1.0,
                2.0, 4.0, -1.0,
                5.5, 3.2, 1.0)

        val prod1 = m2 * m1
        val prod2 = m1 * m2

        assertTrue(prod1.equals(
                Mat3(23.5, 17.6, 2.0,
                        55.0, 39.2, 5.0,
                        86.5, 60.8, 8.0)))

        assertTrue(prod2.equals(
                Mat3(10.0, 14.0, 18.0,
                        11.0, 16.0, 21.0,
                        25.3, 35.0, 44.7)))
    }
}
