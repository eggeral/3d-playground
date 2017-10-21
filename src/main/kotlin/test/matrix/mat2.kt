package test.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import spr5.matrix.mat2
import spr5.util.assertEquals
import spr5.util.assertFalse
import spr5.util.assertTrue
import test.TestClass

class Mat2Test: TestClass() {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun create() {
        val identity = mat2.identity(mat2.create());

        assertEquals(1.0, identity[0]);
        assertEquals(0.0, identity[1]);
        assertEquals(0.0, identity[2]);
        assertEquals(1.0, identity[3]);
    }

    @Test
    fun testEquals() {
        val m1 = mat2.fromValues(1.0, 2.0, 3.0, 4.0);
        val m2 = mat2.fromValues(1.0, 2.0, 3.0, 4.0);
        val m3 = mat2.fromValues(0.0, 1.0, 2.0, 3.0);

        assertTrue(mat2.equals(m1, m2));
        assertTrue(mat2.equals(m2, m1));
        assertFalse(mat2.equals(m2, m3));
        assertFalse(mat2.equals(m3, m2));
    }

    @Test
    fun multiplyWithIdentity() {
        val identity = mat2.identity(Float32Array(4));
        val m = mat2.fromValues(1.0, 2.0, 3.0, 4.0);

        // test m * I
        assertTrue(mat2.equals(m, mat2.multiply(mat2.create(), m, identity)));
        // test I * m
        assertTrue(mat2.equals(m, mat2.multiply(mat2.create(), identity, m)));
    }

    @Test
    fun multiplyWithMatrix() {
        val m1 = mat2.fromValues(1.0, 2.0, 3.0, 4.0);
        val m2 = mat2.fromValues(3.0, 0.0, 1.0, 2.0);
        val prod1 = mat2.multiply(mat2.create(), m2, m1);  // computes m1 * m2
        val prod2 = mat2.multiply(mat2.create(), m1, m2);  // computes m2 * m1

        assertTrue(mat2.equals(prod1, mat2.fromValues(5.0, 4.0, 13.0, 8.0)));
        assertTrue(mat2.equals(prod2, mat2.fromValues(3.0, 6.0, 7.0, 10.0)));
    }
}
