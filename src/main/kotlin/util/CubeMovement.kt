package util

import scene.*

class CubeMovement {
    val renderer: WebGLRenderer = WebGLRenderer();

    fun run() {

        val center1 = Coordinate(0.0f, 0.0f, 0.0f)
        val center2 = Coordinate(2.0f, 0.0f, 0.0f)
        val center3 = Coordinate(0.0f, 2.0f, 0.0f)
        val center4 = Coordinate(2.0f, 2.0f, 0.0f)
        val cubeSize = 1.0f
        val cubeFacesColors1 = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f))
        val cubeFacesColors2 = arrayOf(Rgba(1.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.5f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 1.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f))

        var cube1 = createMulticolorCube(center1, cubeSize, cubeFacesColors1)
        cube1.speedX = -0.02
        cube1.rotationSpeedZ = 0.01
        renderer.add(cube1)

        var cube2 = createMulticolorCube(center2, cubeSize, cubeFacesColors2)
        cube2.speedY = -0.01
        cube2.rotationSpeedX = 0.01
        renderer.add(cube2)

        var cube3 = createMulticolorCube(center3, cubeSize, cubeFacesColors1)
        cube3.speedZ = -0.01
        cube3.rotationSpeedY = 0.01
        renderer.add(cube3)

        var cube4 = createMulticolorCube(center4, cubeSize, cubeFacesColors2)
        cube4.speedX = -0.03
        //cube4.rotationSpeedX = 0.01
        cube4.rotationSpeedY = 0.01
        //cube4.rotationSpeedZ = 0.01
        renderer.add(cube4)
    }
}