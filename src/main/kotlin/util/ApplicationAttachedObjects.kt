package webgl

import scene.*
import util.WebGLRenderer

class ApplicationAttachedObjects {
    private val renderer: WebGLRenderer = WebGLRenderer()

    fun run() {

        val center1 = Coordinate(0.0f, 0.0f, 0.0f)
        val center2 = Coordinate(2.0f, 0.0f, 0.0f)
        val center3 = Coordinate(5.0f, 0.0f, 0.0f)
        val cubeSize = 1.0f

        val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f))


        val cube1 = createMulticolorCube(center1, 1.0f, cubeFacesColors)
        //cube1.rotationSpeedY = 0.005
        //cube1.rotationSpeedZ = 0.005
        val cube2 = createCube(center2, 1.0f, Rgba.Blue)
        cube2.rotationSpeedX = 0.005
        //cube2.rotationSpeedY = 0.005


        val attachedCubes = SceneNodesAttached()
        attachedCubes.addChild(cube1)
        attachedCubes.addChild(cube2)

        attachedCubes.position = Coordinate(0.0f, 0.0f, 0.0f)
        //attachedCubes.rotationSpeedX = 0.005
        //attachedCubes.rotationSpeedY = 0.005
        // attachedCubes.position = Coordinate(-5.0f, 2.0f, 0.0f)

        var cube3 = createMulticolorCube(center3, cubeSize, cubeFacesColors)
        cube3.rotationSpeedX = 0.015
        //cube3.rotationSpeedY = 0.015
        renderer.add(cube3)
        renderer.add(attachedCubes)
    }
}