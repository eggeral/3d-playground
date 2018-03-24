package spr5

import spr5.scene.*

class ApplicationPSEE {
    val renderer: WebGLRenderer = WebGLRenderer();

    fun run() {

        val center = Coordinate(0.0f, 0.0f, 0.0f);
        val center2 = Coordinate(1.0f, 0.0f, 0.0f);
        val center3 = Coordinate(4.0f, 0.0f, 0.0f);
        val cubeSize = 1.0f;
        val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f));

        renderer.add(createMulticolorCube(center, cubeSize, cubeFacesColors))
        renderer.add(createCube(center2, 1.0f, Rgba.Red))
        renderer.add(createCube(center3, 2.0f, Rgba.Blue))
        //renderer.add(createAttachedCube(center2, 1.0f, Rgba.Blue))
    }
}