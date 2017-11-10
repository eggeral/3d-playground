package spr5.scene

interface WebGLDrawableMulticolored {
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun getIndices(): Array<Short>

    var colors: Array<Rgba>
}