package test.matrix

import spr5.matrix.Mat2
import spr5.matrix.Vec2
import spr5.util.assertEquals
import spr5.util.assertFalse
import spr5.util.assertTrue

import test.annotations.*

class Mat2Test {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun create() {
        val identity = Mat2.identity()

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

        assertTrue(Mat2.equals(m1, m2))
        assertTrue(Mat2.equals(m2, m1))
        assertFalse(Mat2.equals(m2, m3))
        assertFalse(Mat2.equals(m3, m2))
    }

    @Test
    fun testAddMatrix() {
        val m1 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m2 = Mat2(1.0, 2.0, 3.0, 4.0)
        val expected = Mat2(2.0, 4.0, 6.0, 8.0);

        assertTrue(Mat2.equals(expected, m1 + m2));
        assertTrue(Mat2.equals(expected, m2 + m1));

        assertTrue(Mat2.equals(expected, Mat2.add(m1, m2)));
        assertTrue(Mat2.equals(expected, Mat2.add(m2, m1)));

        assertTrue(Mat2.equals(expected, m1.add(m2)));
        assertTrue(Mat2.equals(expected, m1));
    }

    @Test
    fun testAddZero() {
        val m1 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m2 = Mat2(0.0, 0.0, 0.0, 0.0)
        val expected = Mat2(1.0, 2.0, 3.0, 4.0);

        assertTrue(Mat2.equals(expected, m1 + m2));
        assertTrue(Mat2.equals(expected, m2 + m1));

        assertTrue(Mat2.equals(expected, Mat2.add(m1, m2)));
        assertTrue(Mat2.equals(expected, Mat2.add(m2, m1)));

        assertTrue(Mat2.equals(expected, m1.add(m2)));
        assertTrue(Mat2.equals(expected, m1));
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat2.identityDoubleArray()
        val m = Mat2.fromValues(1.0, 2.0, 3.0, 4.0)

        // test m * I
        assertTrue(Mat2.equals(m, Mat2.multiply(m, identity)))
        // test I * m
        assertTrue(Mat2.equals(m, Mat2.multiply(identity, m)))
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat2.fromValues(1.0, 2.0, 3.0, 4.0)
        val m2 = Mat2.fromValues(3.0, 0.0, 1.0, 2.0)

        val prod1 = Mat2.multiply(m2, m1)  // computes m1 * m2
        val prod2 = Mat2.multiply(m1, m2)  // computes m2 * m1

        assertTrue(Mat2.equals(prod1, Mat2.fromValues(5.0, 4.0, 13.0, 8.0)))
        assertTrue(Mat2.equals(prod2, Mat2.fromValues(3.0, 6.0, 7.0, 10.0)))

        val m11 = Mat2(1.0, 2.0, 3.0, 4.0)
        val m22 = Mat2(3.0, 0.0, 1.0, 2.0)

        val prod11 = m11 * m22
        val prod22 = m22 * m11
        assertTrue(Mat2.equals(prod11, Mat2(5.0, 4.0, 13.0, 8.0)))
        assertTrue(Mat2.equals(prod22, Mat2(3.0, 6.0, 7.0, 10.0)))
    }
}
