package scene

import glmatrix.Mat4

interface SceneObject : SceneNode {
    var absoluteCoordinate: Coordinate
    var model: Mat4
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun setColors(color: Rgba)
    fun setColors(colors:Array<Float>)
    fun getIndices(): Array<Short>
    fun getAbsoluteCoordinate(): Coordinate
    fun setAbsoluteCoordinate(c: Coordinate)
    fun setAbsoluteCoordinate(x: Float, y: Float, z: Float)
    fun addAbsoluteCoordinate(c: Coordinate)
    fun addAbsoluteCoordinate(x: Float, y: Float, z: Float)
}
