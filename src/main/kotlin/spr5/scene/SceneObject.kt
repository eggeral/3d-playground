package spr5.scene

interface SceneObject : SceneNode
{
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun getIndices(): Array<Short>

}