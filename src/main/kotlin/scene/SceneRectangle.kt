package scene;

import glmatrix.Mat4
import util.assert

class SceneRectangle(override var position: Coordinate, var width: Float, var height: Float, override var color: Rgba) : WebGLDrawable {

    var hit: Boolean = false
    var highlightColor: Rgba = Rgba.Blue

    override var model: Mat4 = Mat4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    override var rotationSpeedX: Double = 0.0
    override var rotationSpeedY: Double = 0.0
    override var rotationSpeedZ: Double = 0.0

    init {
        assert(width > 0, "Width must be greater than 0!")
        assert(height > 0, "Height must be greater than 0!")
    }

    override fun getVertices(): Array<Float> {
        return arrayOf(position.x - width / 2, position.y + height / 2, position.z    //left-top
                , position.x + width / 2, position.y + height / 2, position.z          //right-top
                , position.x - width / 2, position.y - height / 2, position.z          //left-bottom
                , position.x + width / 2, position.y - height / 2, position.z          //right-bottom
        )
    }

    override fun getColors(): Array<Float> {
        if(hit){
            return arrayOf(highlightColor.red, highlightColor.green, highlightColor.blue, highlightColor.alpha)
        }else{
            return arrayOf(
                    color.red, color.green, color.blue, color.alpha
                    ,color.red, color.green, color.blue, color.alpha
                    ,color.red, color.green, color.blue, color.alpha
                    ,color.red, color.green, color.blue, color.alpha)
        }
    }

    override fun setColors(color: Rgba){
        this.color = color;
    }

    override fun setColors(colors:Array<Float>){
        color = Rgba(
                colors[0], colors[1], colors[2], colors[3]
        )
    }

    override fun getIndices(): Array<Short> {
        return arrayOf(0, 1, 2
                , 2, 1, 3)
    }
    override fun isHit(): Boolean{
        return hit
    }
    override fun setHit(hit: Boolean){
        this.hit = hit;
    }
}

fun createSquare(center: Coordinate, size: Float, color: Rgba): SceneRectangle {
    return SceneRectangle(center, size, size, color)
}
