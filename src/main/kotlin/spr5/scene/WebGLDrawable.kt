package spr5.scene;

enum class Direction {
    Horizontal,
    Vertical,
    Sideways
};

interface WebGLDrawable {
    fun getVertices(): Array<Float>
    fun getColors(): Array<Float>
    fun getIndices(): Array<Short>

    var color: Rgba
}