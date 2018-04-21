package webgl

import scene.*
import util.WebGLRenderer

class ApplicationAttachedObjects {
    private val renderer: WebGLRenderer = WebGLRenderer()

    fun run() {

        val center1 = Coordinate(5.0f, -2.0f, 0.0f)
        val center2 = Coordinate(-1.0f, -2.0f, 0.0f)
        val center3 = Coordinate(1.0f, -2.0f, 0.0f)
        val center4 = Coordinate(-1.0f, -1.0f, 0.0f)
        val center5 = Coordinate(1.0f, -1.0f, 0.0f)
        val center6 = Coordinate(0.0f, 0.25f, 0.0f)
        val center7 = Coordinate(0.0f, 1.75f, 0.0f)
        val center7a = Coordinate(1.25f, 1.75f, 0.0f)
        val center7aa = Coordinate(-2.0f, 1.75f, 0.0f)
        val center7b = Coordinate(-1.25f, 1.75f, 0.0f)
        val center7bb = Coordinate(2.0f, 1.75f, 0.0f)
        val center8 = Coordinate(0.0f, 2.75f, 0.0f)
        val center9 = Coordinate(0.0f, 3.75f, 0.0f)
        val cubeSize1 = 1.0f
        val cubeSize1_5 = 1.5f
        val cubeSize0_5 = 0.5f

        val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f))


        val cube1 = createMulticolorCube(center1, cubeSize1, cubeFacesColors)
        cube1.rotationSpeedX = 0.003
        //cube1.rotationSpeedY = 0.005
        //cube1.rotationSpeedZ = 0.005


        val cube2 = createCube(center2, cubeSize1, Rgba.Blue)
        val cube3 = createCube(center3, cubeSize1, Rgba.Blue)
        val cube4 = createCube(center4, cubeSize1, Rgba.Blue)
        val cube5 = createCube(center5, cubeSize1, Rgba.Blue)
        val cube6 = createCube(center6, cubeSize1_5, Rgba.Blue)
        val cube7 = createCube(center7, cubeSize1_5, Rgba.Red)
        val cube7a = createCube(center7a, cubeSize1, Rgba.Red)
        val cube7aa = createCube(center7aa, cubeSize0_5, Rgba.Green)
        val cube7b = createCube(center7b, cubeSize1, Rgba.Red)
        val cube7bb = createCube(center7bb, cubeSize0_5, Rgba.Green)
        val cube8 = createCube(center8, cubeSize0_5, Rgba.Red)
        val cube9 = createMulticolorCube(center9, cubeSize1_5, cubeFacesColors)
        cube9.rotationSpeedY = 0.007



        val attachedCubes = SceneNodesAttached()
        attachedCubes.addChild(cube2)
        attachedCubes.addChild(cube3)
        attachedCubes.addChild(cube4)
        attachedCubes.addChild(cube5)
        attachedCubes.addChild(cube6)
        attachedCubes.addChild(cube7)
        attachedCubes.addChild(cube7a)
        attachedCubes.addChild(cube7aa)
        attachedCubes.addChild(cube7b)
        attachedCubes.addChild(cube7bb)
        attachedCubes.addChild(cube8)
        attachedCubes.addChild(cube9)

        //attachedCubes.rotationSpeedX = 0.005
        //attachedCubes.rotationSpeedY = 0.003
        //attachedCubes.rotationSpeedZ = 0.003


        renderer.add(cube1)
        renderer.add(attachedCubes)
    }
}