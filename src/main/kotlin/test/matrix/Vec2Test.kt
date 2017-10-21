package test.matrix

import spr5.matrix.Vec2;
import spr5.util.assert;

import test.TestClass

class Vec2Test: TestClass() {
    private var v1: Array<Double> = emptyArray();
    private var v2: Array<Double> = emptyArray();

    @Before
    fun setUp() {
        v1 = Vec2().create();
        v2 = Vec2().create();
    }

    @After
    fun tearDown() {
        v1 = emptyArray();
        v2 = emptyArray();
    }

    @Test
    fun create() {
        assert(v1[0] == 0.0 &&
                v1[1] == 0.0 &&
                v1.size == 2);

        assert(v2[0] == 0.0 &&
                v2[1] == 0.0 &&
                v2.size == 2);
    }
}
