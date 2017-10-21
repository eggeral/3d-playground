package spr5.scene;

import spr5.util.assert

class SceneBox(var faces: Array<SceneRectangle>, override var color: Rgba) : WebGLDrawable {
    init {
        assert(faces.size == 6, "faces.size == 6");
    }

    override fun getVertices(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColors(): Array<Float> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString(): String {
        return "SceneBox[faces = ${faces.size}]";
    }
}