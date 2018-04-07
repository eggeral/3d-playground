package glmatrix

import util.assert
import kotlin.test.Test

class Vec2Test {

    @Test
    fun create() {

        val v1 = Vec2()
        val v2 = Vec2()


        assert(v1[0] == 0.0 &&
                v1[1] == 0.0 &&
                v1.size() == 2)

        assert(v2[0] == 0.0 &&
                v2[1] == 0.0 &&
                v2.size() == 2)
    }
}
