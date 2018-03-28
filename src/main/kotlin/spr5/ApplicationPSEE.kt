package spr5

import spr5.scene.*

class ApplicationPSEE {
    private val renderer: WebGLRenderer = WebGLRenderer()

    fun run() {

        val center1 = Coordinate(0.0f, 0.0f, 0.0f);
        val center2 = Coordinate(1.0f, 0.0f, 0.0f);
        val center3 = Coordinate(3.0f, 0.0f, 0.0f);
        val center4 = Coordinate(5.0f, 0.0f, 0.0f);
        val cubeSize = 1.0f;
        val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f));


        val cube1 = createCube(center2, 1.0f, Rgba.Red)
        val cube2 = createCube(center4, 1.0f, Rgba.Blue)

        val attachedCubes = SceneNodesAttached()
        attachedCubes.addChild(cube1)
        attachedCubes.addChild(cube2)

//        renderer.add(createMulticolorCube(center1, cubeSize, cubeFacesColors));
//        renderer.add(createCube(center2, 1.0f, Rgba.Red));
//        renderer.add(createCube(center3, 1.0f, Rgba.Blue));
//
//
//
//        renderer.add(createSceneObjectsAttached(center4));
        renderer.add(attachedCubes)

    }
}