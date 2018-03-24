package spr5

import spr5.scene.*

class Application {
    val renderer: WebGLRenderer = WebGLRenderer();

    fun run() {

        val center = Coordinate(0.0f, 0.0f, 0.0f);
        val cubeSize = 1.0f;
        val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f));

        renderer.add(createMulticolorCube(center, cubeSize, cubeFacesColors));
        renderer.add(createCube(center + 3.0f, 1.5f, Rgba.Red));
    }
}