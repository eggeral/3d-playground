package webgl

import scene.*
import util.WebGLRenderer

class ApplicationAttachedObjects {
    private val renderer: WebGLRenderer = WebGLRenderer()

    fun run() {
        val case = 1    //1..3

        val center1 = Coordinate(5.0f, -2.0f, 0.0f)
        val center2 = Coordinate(-1.0f, -2.0f, 0.0f)
        val center3 = Coordinate(1.0f, -2.0f, 0.0f)
        val center4 = Coordinate(-1.0f, -1.0f, 0.0f)
        val center5 = Coordinate(1.0f, -1.0f, 0.0f)
        val center6 = Coordinate(0.0f, 1.0f, 0.0f)
        val center7a = Coordinate(1.25f, 1.75f, 0.0f)
        val center7aa = Coordinate(-2.0f, 1.75f, 0.0f)
        val center7b = Coordinate(-1.25f, 1.75f, 0.0f)
        val center7bb = Coordinate(2.0f, 1.75f, 0.0f)
        val center8 = Coordinate(0.0f, 2.75f, 0.0f)
        val center9 = Coordinate(0.0f, 3.75f, 0.0f)
        val cubeSize1 = 1.0f
        val cubeSize1p5 = 1.5f
        val cubeSize0p = 0.5f
        val cubeSize3 = 3.0f

        val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f))


        val soloCube = createMulticolorCube(center1, cubeSize1, cubeFacesColors)
        soloCube.rotationSpeedX = 0.003
        soloCube.rotationSpeedY = 0.007

        val footLeft = createCube(center2, cubeSize1, Rgba.Blue)
        val footRight = createCube(center3, cubeSize1, Rgba.Blue)
        val thighLeft = createCube(center4, cubeSize1, Rgba.Blue)
        val thighRight = createCube(center5, cubeSize1, Rgba.Blue)
        val chest = createBlock(center6, cubeSize1p5, cubeSize3, cubeSize1p5, Rgba.Black)
        val upperArmRight = createCube(center7a, cubeSize1, Rgba.Red)
        val lowerArmLeft = createCube(center7aa, cubeSize0p, Rgba.Green)
        val upperArmLeft = createCube(center7b, cubeSize1, Rgba.Red)
        val lowerArmRight = createCube(center7bb, cubeSize0p, Rgba.Green)
        val throat = createCube(center8, cubeSize0p, Rgba.Red)
        val face = createMulticolorCube(center9, cubeSize1p5, cubeFacesColors)

        val robot = SceneNodesAttached()
        val head = SceneNodesAttached()
        val body = SceneNodesAttached()
        val leftArm = SceneNodesAttached()
        val rightArm = SceneNodesAttached()
        val legs = SceneNodesAttached()
        renderer.add(robot)
        if ((case == 1) || (case == 3)) {
            robot.rotationSpeedY = 0.01
            robot.addChild(head)
            head.rotationSpeedY = -0.003
        } else if (case == 2) {
            robot.rotationSpeedX = 0.01
            robot.rotationSpeedY = 0.005
            robot.rotationSpeedZ = 0.007
            robot.addChild(head)
        }
        robot.addChild(body)
        robot.addChild(leftArm)
        robot.addChild(rightArm)
        robot.addChild(legs)

        head.addChild(face)
        head.addChild(throat)

        body.addChild(chest)

        rightArm.addChild(upperArmRight)
        rightArm.addChild(lowerArmRight)

        leftArm.addChild(upperArmLeft)
        leftArm.addChild(lowerArmLeft)

        if ((case == 1) || (case == 2)) {
            legs.addChild(footLeft)
            legs.addChild(thighRight)
            legs.addChild(footRight)
            legs.addChild(thighLeft)
        } else if (case == 3) {
            renderer.add(thighLeft)
            renderer.add(footRight)
            renderer.add(thighRight)
            renderer.add(footLeft)
            // delay code below somehow
            renderer.attachToNode(legs, thighLeft)
            renderer.attachToNode(legs, footLeft)
            renderer.attachToNode(legs, thighRight)
            renderer.attachToNode(legs, footRight)
            // delay code below somehow
            renderer.removeFromNode(legs, thighRight, true)
            renderer.removeFromNode(legs, footRight, true)
        }

        renderer.add(soloCube)
    }
}