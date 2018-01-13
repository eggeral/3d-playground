package spr5.scene

interface SceneObject
{
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun getIndices(): Array<Short>
}