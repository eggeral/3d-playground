package test.matrix

import spr5.matrix.Mat2d
import spr5.util.assertEquals
import spr5.util.assertFalse
import spr5.util.assertTrue
import test.annotations.*

class Mat2dTest {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun create() {
        val identity = Mat2d.identityDoubleArray()

        assertEquals(1.0, identity[0])
        assertEquals(0.0, identity[1])

        assertEquals(0.0, identity[2])
        assertEquals(1.0, identity[3])

        assertEquals(0.0, identity[4])
        assertEquals(0.0, identity[5])
    }

    @Test
    fun testEquals() {
        val m1 = Mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)
        val m2 = Mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)
        val m3 = Mat2d.fromValues(0.0, 1.0, 2.0, 3.0, 0.0, -1.0)

        assertTrue(Mat2d.equals(m1, m2))
        assertTrue(Mat2d.equals(m2, m1))
        assertFalse(Mat2d.equals(m2, m3))
        assertFalse(Mat2d.equals(m3, m2))
    }

    @Test
    fun testAddMatrix() {
        val m1 = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val m2 = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val expected = Mat2d(2.0, 4.0, 6.0, 8.0, 10.0, 12.0);

        assertTrue(Mat2d.equals(expected, m1 + m2));
        assertTrue(Mat2d.equals(expected, m2 + m1));

        assertTrue(Mat2d.equals(expected, Mat2d.add(m1, m2)));
        assertTrue(Mat2d.equals(expected, Mat2d.add(m2, m1)));

        assertTrue(Mat2d.equals(expected, m1.add(m2)));
        assertTrue(Mat2d.equals(expected, m1));
    }

    @Test
    fun testAddZero() {
        val m1 = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val m2 = Mat2d(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        val expected = Mat2d(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);

        assertTrue(Mat2d.equals(expected, m1 + m2));
        assertTrue(Mat2d.equals(expected, m2 + m1));

        assertTrue(Mat2d.equals(expected, Mat2d.add(m1, m2)));
        assertTrue(Mat2d.equals(expected, Mat2d.add(m2, m1)));

        assertTrue(Mat2d.equals(expected, m1.add(m2)));
        assertTrue(Mat2d.equals(expected, m1));
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat2d.identityDoubleArray()
        val m = Mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)

        // test m * I
        assertTrue(Mat2d.equals(m, Mat2d.multiply(m, identity)))
        // test I * m
        assertTrue(Mat2d.equals(m, Mat2d.multiply(identity, m)))
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0)
        val m2 = Mat2d.fromValues(3.0, 0.0, 1.0, 2.0, 4.0, -1.0)
        val prod1 = Mat2d.multiply(m2, m1)  // computes m1 * m2
        val prod2 = Mat2d.multiply(m1, m2)  // computes m2 * m1

        assertTrue(Mat2d.equals(prod1, Mat2d.fromValues(5.0, 4.0, 13.0, 8.0, 8.0, 1.0)))
        assertTrue(Mat2d.equals(prod2, Mat2d.fromValues(3.0, 6.0, 7.0, 10.0, 2.0, 5.0)))
    }
}
