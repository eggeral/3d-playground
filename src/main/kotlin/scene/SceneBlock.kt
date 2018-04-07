package scene

import glmatrix.Mat4
import util.assert


class SceneBlock(override var position: Coordinate, var width: Float, var height: Float, var depth: Float, override var colors: Array<Rgba>) : WebGLDrawableMulticolored {


    override var model: Mat4 = Mat4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    override var rotationSpeedX: Double = 0.0
    override var rotationSpeedY: Double = 0.0
    override var rotationSpeedZ: Double = 0.0

    init {
        assert(width > 0, "Width must be greater than 0!")
        assert(height > 0, "Height must be greater than 0!")
        assert(depth > 0, "Depth must be greater than 0!")
        assert(colors.size == 6, "You have to specify six colors!")
    }

    override fun getVertices(): Array<Float> {
        return arrayOf(
                position.x - width / 2, position.y - height / 2, position.z - depth / 2           //left-bottom-back
                , position.x + width / 2, position.y - height / 2, position.z - depth / 2          //right-bottom-back
                , position.x + width / 2, position.y + height / 2, position.z - depth / 2          //right-top-back

                , position.x - width / 2, position.y + height / 2, position.z - depth / 2          //left-top-back
                , position.x - width / 2, position.y - height / 2, position.z + depth / 2          //left-bottom-front
                , position.x + width / 2, position.y - height / 2, position.z + depth / 2          //right-bottom-front

                , position.x + width / 2, position.y + height / 2, position.z + depth / 2          //right-top-front
                , position.x - width / 2, position.y + height / 2, position.z + depth / 2          //left-top-front
                , position.x - width / 2, position.y - height / 2, position.z - depth / 2          //left-bottom-back

                , position.x - width / 2, position.y + height / 2, position.z - depth / 2          //left-top-back
                , position.x - width / 2, position.y + height / 2, position.z + depth / 2          //left-top-front
                , position.x - width / 2, position.y - height / 2, position.z + depth / 2          //left-bottom-front

                , position.x + width / 2, position.y - height / 2, position.z - depth / 2          //right-bottom-back
                , position.x + width / 2, position.y + height / 2, position.z - depth / 2          //right-top-back
                , position.x + width / 2, position.y + height / 2, position.z + depth / 2          //right-top-front

                , position.x + width / 2, position.y - height / 2, position.z + depth / 2          //right-bottom-front
                , position.x - width / 2, position.y - height / 2, position.z - depth / 2          //left-bottom-back
                , position.x - width / 2, position.y - height / 2, position.z + depth / 2          //left-bottom-front

                , position.x + width / 2, position.y - height / 2, position.z + depth / 2          //right-bottom-front
                , position.x + width / 2, position.y - height / 2, position.z - depth / 2          //right-bottom-back
                , position.x - width / 2, position.y + height / 2, position.z - depth / 2          //left-top-back

                , position.x - width / 2, position.y + height / 2, position.z + depth / 2          //left-top-front
                , position.x + width / 2, position.y + height / 2, position.z + depth / 2          //right-top-front
                , position.x + width / 2, position.y + height / 2, position.z - depth / 2          //right-top-back
        )
    }

    override fun getColors(): Array<Float> {
        return arrayOf(
                colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                , colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                , colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                , colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                , colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                , colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                , colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                , colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                , colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                , colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                , colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                , colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                , colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                , colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                , colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                , colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                , colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                , colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                , colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                , colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                , colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                , colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                , colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                , colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha)
    }

    override fun getIndices(): Array<Short> {
        return arrayOf(0, 1, 2,
                0, 2, 3,

                4, 5, 6,
                4, 6, 7,

                8, 9, 10,
                8, 10, 11,

                12, 13, 14,
                12, 14, 15,

                16, 17, 18,
                16, 18, 19,

                20, 21, 22,
                20, 22, 23)
    }
}

fun createCube(center: Coordinate, size: Float, color: Rgba): SceneBlock {
    return SceneBlock(center, size, size, size, arrayOf(color, color, color, color, color, color))
}

fun createMulticolorCube(center: Coordinate, size: Float, colors: Array<Rgba>): SceneBlock {
    return SceneBlock(center, size, size, size, colors)
}
