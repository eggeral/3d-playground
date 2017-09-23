
package spr5.scene;

import spr5.util.assert


class SceneTriangle(var vertices: Array<Coordinate>, override var color: Rgba) : WebGLDrawable {
    init {
        assert(vertices.size == 3, "vertices.size == 3");
    }

    override fun getColors(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getVertices(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}