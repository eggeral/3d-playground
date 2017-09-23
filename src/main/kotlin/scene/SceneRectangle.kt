package spr5.scene;

import spr5.util.assert

class SceneRectangle(var faces: Array<SceneTriangle>, override var color: Rgba) : WebGLDrawable {
    init {
        assert(faces.size == 2, "faces.size == 2");
    }

    override fun getVertices(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColors(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}