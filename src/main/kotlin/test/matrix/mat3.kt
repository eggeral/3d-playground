package test.matrix

import org.khronos.webgl.get
import spr5.matrix.mat3
import spr5.util.assertEquals
import spr5.util.assertFalse
import spr5.util.assertTrue
import test.annotations.*

class Mat3Test {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun create() {
        val identity = mat3.identity(mat3.create());

        assertEquals(1.0, identity[0]);
        assertEquals(0.0, identity[1]);
        assertEquals(0.0, identity[2]);

        assertEquals(0.0, identity[3]);
        assertEquals(1.0, identity[4]);
        assertEquals(0.0, identity[5]);

        assertEquals(0.0, identity[6]);
        assertEquals(0.0, identity[7]);
        assertEquals(1.0, identity[8]);
    }

    @Test
    fun testEquals() {
        val m1 = mat3.fromValues(1.0, 2.0, 3.0,
                                 4.0, 1.0, 1.0,
                                 3.0, 4.0, 5.0);

        val m2 = mat3.fromValues(1.0, 2.0, 3.0,
                                 4.0, 1.0, 1.0,
                                 3.0, 4.0, 5.0);

        val m3 = mat3.fromValues(0.0, 1.0, 2.0,
                                 3.0, 0.0, -1.0,
                                 5.0, 2.0, 5.0);

        assertTrue(mat3.equals(m1, m2));
        assertTrue(mat3.equals(m2, m1));
        assertFalse(mat3.equals(m2, m3));
        assertFalse(mat3.equals(m3, m2));
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = mat3.identity(mat3.create());
        val m = mat3.fromValues(
                1.0, 2.0, 3.0,
                4.0, 1.0, 1.0,
                5.0, 4.0, 2.4);

        // test m * I
        assertTrue(mat3.equals(m, mat3.multiply(mat3.create(), m, identity)));
        // test I * m
        assertTrue(mat3.equals(m, mat3.multiply(mat3.create(), identity, m)));
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = mat3.fromValues(1.0, 2.0, 3.0,
                                 4.0, 5.0, 6.0,
                                 7.0, 8.0, 9.0);

        val m2 = mat3.fromValues(3.0, 0.0, 1.0,
                                 2.0, 4.0, -1.0,
                                 5.5, 3.2, 1.0);

        val prod1 = mat3.multiply(mat3.create(), m2, m1);  // computes m1 * m2
        val prod2 = mat3.multiply(mat3.create(), m1, m2);  // computes m2 * m1

        assertTrue(mat3.equals(prod1,
                               mat3.fromValues(23.5, 17.6, 2.0,
                                               55.0, 39.2, 5.0,
                                               86.5, 60.8, 8.0)));

        assertTrue(mat3.equals(prod2,
                               mat3.fromValues(10.0, 14.0, 18.0,
                                               11.0, 16.0, 21.0,
                                               25.3, 35.0, 44.7)));
    }
}
