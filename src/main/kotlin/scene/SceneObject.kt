package scene

interface SceneObject : SceneNode {
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun setColors(color: Rgba)
    fun setColors(colors:Array<Float>)
    fun getIndices(): Array<Short>
}
