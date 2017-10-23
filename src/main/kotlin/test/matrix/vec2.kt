package test.matrix

import spr5.matrix.vec2;
import spr5.util.assert;

import test.annotations.*

class Vec2Test {
    private var v1: Array<Double> = emptyArray();
    private var v2: Array<Double> = emptyArray();

    @Before
    fun setUp() {
        v1 = vec2().create();
        v2 = vec2().create();
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
