package test.matrix

import org.khronos.webgl.get
import spr5.matrix.Mat4
import spr5.util.assertEquals
import spr5.util.assertFalse
import spr5.util.assertTrue
import test.TestClass

class Mat4Test: TestClass() {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun create() {
        val identity = Mat4.identity(Mat4.create());

        assertEquals(1.0, identity[0]);
        assertEquals(0.0, identity[1]);
        assertEquals(0.0, identity[2]);
        assertEquals(0.0, identity[3]);

        assertEquals(0.0, identity[4]);
        assertEquals(1.0, identity[5]);
        assertEquals(0.0, identity[6]);
        assertEquals(0.0, identity[7]);

        assertEquals(0.0, identity[8]);
        assertEquals(0.0, identity[9]);
        assertEquals(1.0, identity[10]);
        assertEquals(0.0, identity[11]);

        assertEquals(0.0, identity[12]);
        assertEquals(0.0, identity[13]);
        assertEquals(0.0, identity[14]);
        assertEquals(1.0, identity[15]);

    }

    @Test
    fun testEquals() {
        val m1 = Mat4.fromValues(1.0, 2.0, 3.0, 0.0,
                4.0, 1.0, 1.0, 0.5,
                3.0, 4.0, 5.0, 1.5,
                4.0, 1.0, 1.4, 2.0);

        val m2 = Mat4.fromValues(1.0, 2.0, 3.0, 0.0,
                4.0, 1.0, 1.0, 0.5,
                3.0, 4.0, 5.0, 1.5,
                4.0, 1.0, 1.4, 2.0);

        val m3 = Mat4.fromValues(0.0, 1.0, 2.0, 3.3,
                3.0, 0.0, -1.0, 1.0,
                5.0, 2.0, 5.0, 1.1,
                5.3, 1.0, 3.4, 1.4);

        assertTrue(Mat4.equals(m1, m2));
        assertTrue(Mat4.equals(m2, m1));
        assertFalse(Mat4.equals(m2, m3));
        assertFalse(Mat4.equals(m3, m2));
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = Mat4.identity(Mat4.create());
        val m = Mat4.fromValues(
                1.0, 2.0, 3.0, 1.0,
                4.0, 1.0, 1.0, 3.0,
                5.0, 4.0, 2.4, 1.0,
                0.0, 1.0, 2.0, 3.0);

        // test m * I
        assertTrue(Mat4.equals(m, Mat4.multiply(Mat4.create(), m, identity)));
        // test I * m
        assertTrue(Mat4.equals(m, Mat4.multiply(Mat4.create(), identity, m)));
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = Mat4.fromValues(1.0, 2.0, 3.0, 4.0,
                4.0, 5.0, 6.0, 7.0,
                7.0, 8.0, 9.0, 10.0,
                9.0, 8.0, 7.0, 6.0);

        val m2 = Mat4.fromValues(3.0, 0.0, 1.0, 9.0,
                2.0, 4.0, -1.0, 8.0,
                5.5, 3.2, 1.0, 7.0,
                6.0, 5.0, 4.0, 3.0);

        val prod1 = Mat4.multiply(Mat4.create(), m2, m1);  // computes m1 * m2
        val prod2 = Mat4.multiply(Mat4.create(), m1, m2);  // computes m2 * m1

        assertTrue(Mat4.equals(prod1,
                Mat4.fromValues(
                        47.5,  37.6,  18.0, 58.0,
                        97.0,  74.2,  33.0, 139.0,
                        146.5, 110.8, 48.0, 220.0,
                        117.5, 84.4,  32.0, 212.0)));

        assertTrue(Mat4.equals(prod2,
                Mat4.fromValues(
                        91.0, 86.0, 81.0,  76.0,
                        83.0, 80.0, 77.0,  74.0,
                        88.3, 91.0, 93.7,  96.4,
                        81.0, 93.0, 105.0, 117.0)));
    }
}
