package spr5.scene;

enum class Direction {
    Horizontal,
    Vertical
};

interface WebGLDrawable {
    fun getVertices(): Array<Float>;
    fun getColors(): Array<Float>;

    var color: Rgba;
}