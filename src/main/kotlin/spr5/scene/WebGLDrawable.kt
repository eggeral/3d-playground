package spr5.scene;

enum class Direction {
    Horizontal,
    Vertical,
    Sideways
};

interface WebGLDrawable {
    fun getVertices(): Array<Float>;
    fun getColors(): Array<Float>;

    var color: Rgba;
}