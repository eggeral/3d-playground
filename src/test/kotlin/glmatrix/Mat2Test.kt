package glmatrix

import util.assertEquals
import util.assertFalse
import util.assertTrue
import kotlin.test.Test


class Mat2Test {

    @Test
    fun create() {
        val identity = Mat2().identity()

        assertEquals(1.0, identity[0])
        assertEquals(0.0, identity[1])
        assertEquals(0.0, identity[2])
        assertEquals(1.0, identity[3])
    }

    @Test
    fun testEquals() {
        val m1 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m2 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m3 = Mat2(0.0, 1.0, 2.0, 3.0)

        assertTrue(m1.equals(m2))
        assertTrue(m2.equals(m1))
        assertFalse(m2.equals(m3))
        assertFalse(m3.equals(m2))
    }

    @Test
    fun testAddMatrix() {
        val m1 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m2 = Mat2(1.0, 2.0, 3.0, 4.0)
        val expected = Mat2(2.0, 4.0, 6.0, 8.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun testAddZero() {
        val m1 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m2 = Mat2(0.0, 0.0, 0.0, 0.0)
        val expected = Mat2(1.0, 2.0, 3.0, 4.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat2().identity()
        val m = Mat2(1.0, 2.0, 3.0, 4.0)

        // test m * I
        assertTrue(m.equals(m * identity))
        // test I * m
        assertTrue(m.equals(identity * m))
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m2 = Mat2(3.0, 0.0, 1.0, 2.0)

        val prod1 = m2 * m1
        val prod2 = m1 * m2

        assertTrue(prod1.equals(Mat2(5.0, 4.0, 13.0, 8.0)))
        assertTrue(prod2.equals(Mat2(3.0, 6.0, 7.0, 10.0)))

        val m11 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m22 = Mat2(3.0, 0.0, 1.0, 2.0)

        val prod11 = m11 * m22
        val prod22 = m22 * m11
        assertTrue(prod11.equals(Mat2(5.0, 4.0, 13.0, 8.0)))
        assertTrue(prod22.equals(Mat2(3.0, 6.0, 7.0, 10.0)))
    }
}
