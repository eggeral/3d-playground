package scene

interface SceneObject : SceneNode {
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun setColors(color: Rgba)
    fun setColors(colors:Array<Float>)
    fun getIndices(): Array<Short>
    fun getCoordinate(): Coordinate
    fun setCenter(c: Coordinate)
    fun getAbsoluteCoordinate(): Coordinate
    fun setAbsoluteCoordinate(c: Coordinate)
    fun setAbsoluteCoordinate(x: Float, y: Float, z: Float)
    fun addAbsoluteCoordinate(c: Coordinate)
    fun addAbsoluteCoordinate(x: Float, y: Float, z: Float)
    fun copyProperties(sceneObject: SceneObject)
}
