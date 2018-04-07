package main

import example.*
import org.khronos.webgl.WebGLRenderingContext
import webgl.fitDrawingBufferIntoCanvas


fun drawExample(gl: WebGLRenderingContext) {
    // This fun only exists because we want to keep track of the order of examples. There is no other reason!

    gl.fitDrawingBufferIntoCanvas()
    //drawTriangle(gl)
    //translateTriangle(gl)
    //scaleAndRotateTriangle(gl)
    //rotateCube(gl)

    //drawSceneTriangle(gl)
    //drawMultipleCubes(gl)
    //moveCameraViewX(gl)
    //moveCameraViewY(gl)
    attachObjects(gl)
    //moveCameraByMouse(gl)
    //mouseHandling(gl)
}

