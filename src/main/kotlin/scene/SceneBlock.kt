package scene

import glmatrix.Mat4
import util.assert


class SceneBlock(override var position: Coordinate, var width: Float, var height: Float, var depth: Float, override var colors: Array<Rgba>) : WebGLDrawableMulticolored {

    var hit: Boolean = false
    var highlightColor: Rgba = Rgba.Red

    override fun isHit(): Boolean {
        return hit;
    }

    override fun setHit(hit: Boolean) {
        this.hit = hit;
    }

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
        if( hit ){
            console.log("Object hit " + highlightColor);
            return arrayOf(
                    highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha
                    ,highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha)
        }else{
            return arrayOf(
                    colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                    ,colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                    ,colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                    ,colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                    ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                    ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                    ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                    ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                    ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                    ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                    ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                    ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                    ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                    ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                    ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                    ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                    ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                    ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                    ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                    ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                    ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                    ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                    ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                    ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha)
        }
    }

    override fun setColors(color: Rgba){
        this.colors = arrayOf(color,color,color,color,color,color);
    }

    override fun setColors(colors:Array<Float>){
        this.colors = arrayOf(
                Rgba(colors[0], colors[1], colors[2], colors[3]),
                Rgba(colors[16], colors[17], colors[18], colors[19]),
                Rgba(colors[32], colors[33], colors[34], colors[35]),
                Rgba(colors[48], colors[49], colors[50], colors[51]),
                Rgba(colors[64], colors[65], colors[66], colors[67]),
                Rgba(colors[80], colors[81], colors[82], colors[83]))
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
