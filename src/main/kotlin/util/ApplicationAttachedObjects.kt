package webgl

import scene.*
import util.WebGLRenderer

class ApplicationAttachedObjects {
    private val renderer: WebGLRenderer = WebGLRenderer()

    fun run() {
        val case = 3    //0..3

        val centerSoloCube = Coordinate(5.0f, -2.0f, 0.0f)
        val centerFootLeft = Coordinate(-1.0f, -2.0f, 0.0f)
        val centerFootRight = Coordinate(1.0f, -2.0f, 0.0f)
        val centerThighLeft = Coordinate(-1.0f, -1.0f, 0.0f)
        val centerThighRight = Coordinate(1.0f, -1.0f, 0.0f)
        val centerChest = Coordinate(0.0f, 1.0f, 0.0f)
        val centerUpperArmRight = Coordinate(1.25f, 1.75f, 0.0f)
        val centerLowerArmLeft = Coordinate(-2.0f, 1.75f, 0.0f)
        val centerUpperArmLeft = Coordinate(-1.25f, 1.75f, 0.0f)
        val centerLowerArmRight = Coordinate(2.0f, 1.75f, 0.0f)
        val centerThroat = Coordinate(0.0f, 2.75f, 0.0f)
        val centerFace = Coordinate(0.0f, 3.75f, 0.0f)
        val centerLeftEye = Coordinate(-0.45f, 3.9f,0.751f)
        val centerRightEye = Coordinate(0.45f, 3.9f,0.751f)
        val centerNose = Coordinate(0.0f, 3.7f,0.751f)
        val centerMouth = Coordinate(0.0f, 3.35f,0.751f)
        val cubeSize0p5 = 0.5f
        val cubeSize1 = 1.0f
        val cubeSize1p5 = 1.5f
        val cubeSize3 = 3.0f

        val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
                , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
                , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
                , Rgba(0.5f, 0.0f, 0.5f, 1.0f))


        val soloCube = createMulticolorCube(centerSoloCube, cubeSize1, cubeFacesColors)
        soloCube.rotationSpeedX = 0.003
        soloCube.rotationSpeedY = 0.007

        val footLeft = createCube(centerFootLeft, cubeSize1, Rgba.Blue)
        val footRight = createCube(centerFootRight, cubeSize1, Rgba.Blue)
        val thighLeft = createCube(centerThighLeft, cubeSize1, Rgba.Blue)
        val thighRight = createCube(centerThighRight, cubeSize1, Rgba.Blue)
        val chest = createBlock(centerChest, cubeSize1p5, cubeSize3, cubeSize1p5, Rgba.Black)
        val upperArmRight = createCube(centerUpperArmRight, cubeSize1, Rgba.Red)
        val lowerArmLeft = createCube(centerLowerArmLeft, cubeSize0p5, Rgba.Green)
        val upperArmLeft = createCube(centerUpperArmLeft, cubeSize1, Rgba.Red)
        val lowerArmRight = createCube(centerLowerArmRight, cubeSize0p5, Rgba.Green)
        val throat = createCube(centerThroat, cubeSize0p5, Rgba.Red)
        val face = createMulticolorCube(centerFace, cubeSize1p5, cubeFacesColors)
        val nose = createSquare(centerNose, 0.2f, Rgba.Black)
        val leftEye = createSquare(centerLeftEye, 0.3f, Rgba.Black)
        val rightEye = createSquare(centerRightEye, 0.3f, Rgba.Black)
        val mouth = createRect(centerMouth, 1.0f, 0.20f, Rgba.Black)

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
            robot.rotationSpeedX = 0.006
            robot.rotationSpeedY = -0.004
            robot.rotationSpeedZ = 0.003
            robot.addChild(head)
        } else {
            robot.addChild(head)
            head.rotationSpeedY = -0.003
        }
        robot.addChild(body)
        robot.addChild(leftArm)
        robot.addChild(rightArm)
        robot.addChild(legs)

        head.addChild(face)
        head.addChild(leftEye)
        head.addChild(rightEye)
        head.addChild(nose)
        head.addChild(throat)
        head.addChild(mouth)
        if (case == 1)
            throat.rotationSpeedY = 1.0

        body.addChild(chest)

        rightArm.addChild(upperArmRight)
        rightArm.addChild(lowerArmRight)

        leftArm.addChild(upperArmLeft)
        leftArm.addChild(lowerArmLeft)

        if (case < 3) {
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