package glmatrix

import util.assertEquals
import util.assertFalse
import util.assertTrue
import kotlin.test.Test

class Mat4Test {
    @Test
    fun create() {
        val identity = Mat4().identity()

        assertEquals(1.0, identity[0])
        assertEquals(0.0, identity[1])
        assertEquals(0.0, identity[2])
        assertEquals(0.0, identity[3])

        assertEquals(0.0, identity[4])
        assertEquals(1.0, identity[5])
        assertEquals(0.0, identity[6])
        assertEquals(0.0, identity[7])

        assertEquals(0.0, identity[8])
        assertEquals(0.0, identity[9])
        assertEquals(1.0, identity[10])
        assertEquals(0.0, identity[11])

        assertEquals(0.0, identity[12])
        assertEquals(0.0, identity[13])
        assertEquals(0.0, identity[14])
        assertEquals(1.0, identity[15])

    }

    @Test
    fun testAddMatrix() {
        val m1 = Mat4(1.0, 2.0, 3.0, 4.0,
                4.0, 5.0, 6.0, 7.0,
                7.0, 8.0, 9.0, 10.0,
                11.0, 12.0, 13.0, 14.0)

        val m2 = Mat4(1.0, 2.0, 3.0, 4.0,
                4.0, 5.0, 6.0, 7.0,
                7.0, 8.0, 9.0, 10.0,
                11.0, 12.0, 13.0, 14.0)

        val expected = Mat4(2.0, 4.0, 6.0, 8.0,
                8.0, 10.0, 12.0, 14.0,
                14.0, 16.0, 18.0, 20.0,
                22.0, 24.0, 26.0, 28.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun testAddZero() {
        val m1 = Mat4(1.0, 2.0, 3.0, 4.0,
                4.0, 5.0, 6.0, 7.0,
                7.0, 8.0, 9.0, 10.0,
                11.0, 12.0, 13.0, 14.0)

        val m2 = Mat4(0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0)

        val expected = Mat4(1.0, 2.0, 3.0, 4.0,
                4.0, 5.0, 6.0, 7.0,
                7.0, 8.0, 9.0, 10.0,
                11.0, 12.0, 13.0, 14.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun testEquals() {
        val m1 = Mat4(1.0, 2.0, 3.0, 0.0,
                4.0, 1.0, 1.0, 0.5,
                3.0, 4.0, 5.0, 1.5,
                4.0, 1.0, 1.4, 2.0)

        val m2 = Mat4(1.0, 2.0, 3.0, 0.0,
                4.0, 1.0, 1.0, 0.5,
                3.0, 4.0, 5.0, 1.5,
                4.0, 1.0, 1.4, 2.0)

        val m3 = Mat4(0.0, 1.0, 2.0, 3.3,
                3.0, 0.0, -1.0, 1.0,
                5.0, 2.0, 5.0, 1.1,
                5.3, 1.0, 3.4, 1.4)

        assertTrue(m1.equals(m2))
        assertTrue(m2.equals(m1))
        assertFalse(m2.equals(m3))
        assertFalse(m3.equals(m2))
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat4().identity()
        val m = Mat4(
                1.0, 2.0, 3.0, 1.0,
                4.0, 1.0, 1.0, 3.0,
                5.0, 4.0, 2.4, 1.0,
                0.0, 1.0, 2.0, 3.0)

        // test m * I
        assertTrue(m.equals(m * identity))
        // test I * m
        assertTrue(m.equals(identity * m))
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat4(1.0, 2.0, 3.0, 4.0,
                4.0, 5.0, 6.0, 7.0,
                7.0, 8.0, 9.0, 10.0,
                9.0, 8.0, 7.0, 6.0)

        val m2 = Mat4(3.0, 0.0, 1.0, 9.0,
                2.0, 4.0, -1.0, 8.0,
                5.5, 3.2, 1.0, 7.0,
                6.0, 5.0, 4.0, 3.0)

        val prod1 = m2 * m1
        val prod2 = m1 * m2

        assertTrue(prod1.equals(
                Mat4(
                        47.5, 37.6, 18.0, 58.0,
                        97.0, 74.2, 33.0, 139.0,
                        146.5, 110.8, 48.0, 220.0,
                        117.5, 84.4, 32.0, 212.0)))

        assertTrue(prod2.equals(
                Mat4(
                        91.0, 86.0, 81.0, 76.0,
                        83.0, 80.0, 77.0, 74.0,
                        88.3, 91.0, 93.7, 96.4,
                        81.0, 93.0, 105.0, 117.0)))
    }
}
