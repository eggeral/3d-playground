package test.matrix

import org.khronos.webgl.Float32Array
import spr5.matrix.mat2
import spr5.util.assertEquals
import spr5.util.assertTrue
import test.TestClass

class Mat2Test: TestClass() {
    private var m1: Float32Array? = null;
    private var m2: Float32Array? = null;

    @Before
    fun setUp() {
        m1 = mat2.create();
        m2 = mat2.create();
    }

    @After
    fun tearDown() {
        m1 = null;
        m2 = null;
    }

    @Test
    fun create() {
        val identity = Float32Array(4);
        mat2.identity(identity);

        assertTrue(m1 != null && mat2.equals(identity, m1 as Float32Array));
        assertTrue(m2 != null && mat2.equals(identity, m2 as Float32Array));
    }

    @Test
    fun multiply() {

    }
}
