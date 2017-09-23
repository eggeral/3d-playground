package spr5.scene;

interface WebGLDrawable {
    fun getVertices(): Array<Float>;
    fun getColors(): Array<Float>;

    var color: Rgba;
}