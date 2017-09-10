package threed.example

import org.khronos.webgl.WebGLRenderingContext
import threed.fitDrawingBufferIntoCanvas


fun drawExample(gl: WebGLRenderingContext) {
    // This fun only exists because we want to keep track of the order of examples. There is no other reason!


    gl.fitDrawingBufferIntoCanvas()
    //drawTriangle(gl)
    //translateTriangle(gl)
    //scaleAndRotateTriangle(gl)
    rotateCube(gl)
}

