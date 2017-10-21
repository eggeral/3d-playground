package test.matrix

import org.khronos.webgl.get
import spr5.matrix.mat2d
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
        val identity = mat2d.identity(mat2d.create());

        assertEquals(1.0, identity[0]);
        assertEquals(0.0, identity[1]);

        assertEquals(0.0, identity[2]);
        assertEquals(1.0, identity[3]);

        assertEquals(0.0, identity[4]);
        assertEquals(0.0, identity[5]);
    }

    @Test
    fun testEquals() {
        val m1 = mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0);
        val m2 = mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0);
        val m3 = mat2d.fromValues(0.0, 1.0, 2.0, 3.0, 0.0, -1.0);

        assertTrue(mat2d.equals(m1, m2));
        assertTrue(mat2d.equals(m2, m1));
        assertFalse(mat2d.equals(m2, m3));
        assertFalse(mat2d.equals(m3, m2));
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = mat2d.identity(mat2d.create());
        val m = mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0);

        // test m * I
        assertTrue(mat2d.equals(m, mat2d.multiply(mat2d.create(), m, identity)));
        // test I * m
        assertTrue(mat2d.equals(m, mat2d.multiply(mat2d.create(), identity, m)));
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = mat2d.fromValues(1.0, 2.0, 3.0, 4.0, 1.0, 1.0);
        val m2 = mat2d.fromValues(3.0, 0.0, 1.0, 2.0, 4.0, -1.0);
        val prod1 = mat2d.multiply(mat2d.create(), m2, m1);  // computes m1 * m2
        val prod2 = mat2d.multiply(mat2d.create(), m1, m2);  // computes m2 * m1

        assertTrue(mat2d.equals(prod1, mat2d.fromValues(5.0, 4.0, 13.0, 8.0, 8.0, 1.0)));
        assertTrue(mat2d.equals(prod2, mat2d.fromValues(3.0, 6.0, 7.0, 10.0, 2.0, 5.0)));
    }
}
