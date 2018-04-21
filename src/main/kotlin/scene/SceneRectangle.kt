package scene;

import glmatrix.Mat4
import glmatrix.Vec3
import util.assert

class SceneRectangle(override var absoluteCoordinate: Coordinate, var width: Float, var height: Float, override var color: Rgba) : WebGLDrawable {

    var hit: Boolean = false
    var highlightColor: Rgba = Rgba.Blue

    override var model: Mat4 = Mat4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    override var rotationSpeedX: Double = 0.0
    override var rotationSpeedY: Double = 0.0
    override var rotationSpeedZ: Double = 0.0
    override var rotationAngleX: Double = 0.0
    override var rotationAngleY: Double = 0.0
    override var rotationAngleZ: Double = 0.0
    override var speedX: Double = 0.0
    override var speedY: Double = 0.0
    override var speedZ: Double = 0.0
    override var center: Coordinate = Coordinate()
    override var isChildOf: SceneNode? = null

    init {
        assert(width > 0, "Width must be greater than 0!")
        assert(height > 0, "Height must be greater than 0!")
    }

    override fun getVertices(): Array<Float> {
        return arrayOf(center.x - width / 2, center.y + height / 2, center.z    //left-top
                , center.x + width / 2, center.y + height / 2, center.z          //right-top
                , center.x - width / 2, center.y - height / 2, center.z          //left-bottom
                , center.x + width / 2, center.y - height / 2, center.z          //right-bottom
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

    override fun getCoordinate(): Coordinate {
        return center
    }

    override fun setCenter(c: Coordinate) {
        center = c
    }

    override fun getAbsoluteCoordinate(): Coordinate {
        return absoluteCoordinate
    }

    override fun setAbsoluteCoordinate(c: Coordinate) {
        absoluteCoordinate = c
    }

    override  fun setAbsoluteCoordinate(x: Float, y: Float, z: Float) {
        absoluteCoordinate = Coordinate(x, y, z)
    }

    override fun addAbsoluteCoordinate(c: Coordinate) {
        absoluteCoordinate = absoluteCoordinate + c
    }

    override fun addAbsoluteCoordinate(x: Float, y: Float, z: Float) {
        absoluteCoordinate = absoluteCoordinate + Coordinate(x, y, z)
    }

    override fun copyProperties(sceneNode: SceneNode) {
        var absoluteCoordinate : Coordinate
        if (sceneNode is SceneObject)
            absoluteCoordinate = sceneNode.getAbsoluteCoordinate()
        else
            absoluteCoordinate = Coordinate(0.0f,0.0f,0.0f)
        speedX = sceneNode.speedX
        speedY = sceneNode.speedY
        speedZ = sceneNode.speedZ
        rotationSpeedX = sceneNode.rotationSpeedX
        rotationSpeedY = sceneNode.rotationSpeedY
        rotationSpeedZ = sceneNode.rotationSpeedZ
        model = model.translate(Vec3(-getAbsoluteCoordinate().x.toDouble()-absoluteCoordinate.x, -getAbsoluteCoordinate().y.toDouble()-absoluteCoordinate.y, -getAbsoluteCoordinate().z.toDouble()-absoluteCoordinate.z))
        setCenter(Coordinate(getAbsoluteCoordinate().x-absoluteCoordinate.x,getAbsoluteCoordinate().y-absoluteCoordinate.y,getAbsoluteCoordinate().z-absoluteCoordinate.z))
        model = model.rotateX(-rotationAngleX+sceneNode.rotationAngleX)
        model = model.rotateY(-rotationAngleY+sceneNode.rotationAngleY)
        model = model.rotateZ(-rotationAngleZ+sceneNode.rotationAngleZ)
    }
}

fun createSquare(center: Coordinate, size: Float, color: Rgba): SceneRectangle {
    return SceneRectangle(center, size, size, color)
}
