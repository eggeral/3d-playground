package scene

import scene.Rgba

interface WebGLDrawable {
    fun getVertices(): Array<Float>;
    fun getColors(): Array<Float>;

    var color: Rgba;
}