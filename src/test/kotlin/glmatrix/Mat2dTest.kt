package glmatrix

import util.assertEquals
import util.assertFalse
import kotlin.test.Test
import kotlin.test.assertTrue

class Mat2dTest {

    @Test
    fun create() {
        val identity = Mat2d().identity()

        assertEquals(1.0, identity[0])
        assertEquals(0.0, identity[1])

        assertEquals(0.0, identity[2])
        assertEquals(1.0, identity[3])

        assertEquals(0.0, identity[4])
        assertEquals(0.0, identity[5])
    }

    @Test
    fun testEquals() {
        val m1 = Mat2d(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)
        val m2 = Mat2d(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)
        val m3 = Mat2d(0.0, 1.0, 2.0, 3.0, 0.0, -1.0)

        assertTrue(m1.equals(m2))
        assertTrue(m2.equals(m1))
        assertFalse(m2.equals(m3))
        assertFalse(m3.equals(m2))
    }

    @Test
    fun testAddMatrix() {
        val m1 = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val m2 = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val expected = Mat2d(2.0, 4.0, 6.0, 8.0, 10.0, 12.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun testAddZero() {
        val m1 = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val m2 = Mat2d(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        val expected = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)

        assertTrue(expected.equals(m1 + m2))
        assertTrue(expected.equals(m2 + m1))
        assertTrue(expected.equals(m1))
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat2d().identity()
        val m = Mat2d(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)

        // test m * I
        assertTrue(m.equals(m * identity))
        // test I * m
        assertTrue(m.equals(identity * m))
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat2d(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)
        val m2 = Mat2d(3.0, 0.0, 1.0, 2.0, 4.0, -1.0)
        val prod1 = m2 * m1
        val prod2 = m1 * m2

        assertTrue(prod1.equals(Mat2d(5.0, 4.0, 13.0, 8.0, 8.0, 1.0)))
        assertTrue(prod2.equals(Mat2d(3.0, 6.0, 7.0, 10.0, 2.0, 5.0)))
    }
}
