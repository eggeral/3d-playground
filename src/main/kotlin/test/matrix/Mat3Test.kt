package test.matrix

import spr5.matrix.Mat3
import spr5.util.assertEquals
import spr5.util.assertFalse
import spr5.util.assertTrue
import test.annotations.After
import test.annotations.Before
import test.annotations.Test

class Mat3Test {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun create() {
        val identity = Mat3.identityDoubleArray()

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
        val m1 = Mat3.fromValues(1.0, 2.0, 3.0,
                4.0, 1.0, 1.0,
                3.0, 4.0, 5.0)

        val m2 = Mat3.fromValues(1.0, 2.0, 3.0,
                4.0, 1.0, 1.0,
                3.0, 4.0, 5.0)

        val m3 = Mat3.fromValues(0.0, 1.0, 2.0,
                3.0, 0.0, -1.0,
                5.0, 2.0, 5.0)

        assertTrue(Mat3.equals(m1, m2))
        assertTrue(Mat3.equals(m2, m1))
        assertFalse(Mat3.equals(m2, m3))
        assertFalse(Mat3.equals(m3, m2))
    }

    @Test
    fun testAddMatrix() {
        val m1 = Mat3(1.0, 2.0, 3.0,
                      4.0, 5.0, 6.0,
                      7.0, 8.0, 9.0)

        val m2 = Mat3(1.0, 2.0, 3.0,
                      4.0, 5.0, 6.0,
                      7.0, 8.0, 9.0)
        1
        val expected = Mat3(2.0, 4.0, 6.0,
                            8.0, 10.0, 12.0,
                            14.0, 16.0, 18.0);

        assertTrue(Mat3.equals(expected, m1 + m2));
        assertTrue(Mat3.equals(expected, m2 + m1));

        assertTrue(Mat3.equals(expected, Mat3.add(m1, m2)));
        assertTrue(Mat3.equals(expected, Mat3.add(m2, m1)));

        assertTrue(Mat3.equals(expected, m1.add(m2)));
        assertTrue(Mat3.equals(expected, m1));
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
                            7.0, 8.0, 9.0);

        assertTrue(Mat3.equals(expected, m1 + m2));
        assertTrue(Mat3.equals(expected, m2 + m1));

        assertTrue(Mat3.equals(expected, Mat3.add(m1, m2)));
        assertTrue(Mat3.equals(expected, Mat3.add(m2, m1)));

        assertTrue(Mat3.equals(expected, m1.add(m2)));
        assertTrue(Mat3.equals(expected, m1));
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat3.identityDoubleArray()
        val m = Mat3.fromValues(
                1.0, 2.0, 3.0,
                4.0, 1.0, 1.0,
                5.0, 4.0, 2.4)

        // test m * I
        assertTrue(Mat3.equals(m, Mat3.multiply(m, identity)))
        // test I * m
        assertTrue(Mat3.equals(m, Mat3.multiply(identity, m)))
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat3.fromValues(1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 8.0, 9.0)

        val m2 = Mat3.fromValues(3.0, 0.0, 1.0,
                2.0, 4.0, -1.0,
                5.5, 3.2, 1.0)

        val prod1 = Mat3.multiply(m2, m1)  // computes m1 * m2
        val prod2 = Mat3.multiply(m1, m2)  // computes m2 * m1

        assertTrue(Mat3.equals(prod1,
                Mat3.fromValues(23.5, 17.6, 2.0,
                        55.0, 39.2, 5.0,
                        86.5, 60.8, 8.0)))

        assertTrue(Mat3.equals(prod2,
                Mat3.fromValues(10.0, 14.0, 18.0,
                        11.0, 16.0, 21.0,
                        25.3, 35.0, 44.7)))
    }
}
