package spr5.scene;

import spr5.util.assert

class SceneRectangle(override var position: Coordinate, var width: Float, var height: Float, override var color: Rgba) : WebGLDrawable {
    init {
        assert(width > 0, "Width must be greater than 0!")
        assert(height > 0, "Height must be greater than 0!")
    }

    override fun getVertices(): Array<Float> {
        return arrayOf(position.x-width/2, position.y+ height /2, position.z    //left-top
                , position.x+width/2, position.y+ height /2, position.z          //right-top
                , position.x-width/2, position.y- height /2, position.z          //left-bottom
                , position.x+width/2, position.y- height /2, position.z          //right-bottom
        )
    }

    override fun getColors(): Array<Float> {
        return arrayOf(
                color.red, color.green, color.blue, color.alpha
                ,color.red, color.green, color.blue, color.alpha
                ,color.red, color.green, color.blue, color.alpha
                ,color.red, color.green, color.blue, color.alpha)
    }

    override fun getIndices(): Array<Short> {
        return arrayOf(  0,1,2
                        ,2,1,3)
    }
}

fun createSquare(center: Coordinate, size: Float, color: Rgba) :SceneRectangle {
    return SceneRectangle(center, size, size, color)
}
