package threed

import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLRenderingContext.Companion.ARRAY_BUFFER
import org.khronos.webgl.WebGLRenderingContext.Companion.COLOR_BUFFER_BIT
import org.khronos.webgl.WebGLRenderingContext.Companion.FLOAT
import org.khronos.webgl.WebGLRenderingContext.Companion.FRAGMENT_SHADER
import org.khronos.webgl.WebGLRenderingContext.Companion.STATIC_DRAW
import org.khronos.webgl.WebGLRenderingContext.Companion.TRIANGLES
import org.khronos.webgl.WebGLRenderingContext.Companion.VERTEX_SHADER


fun drawTriangle(gl: WebGLRenderingContext) {

    val webGLProgramObject = gl.createProgram() // GL needs a shader program in order to display something
    val vertexShaderCode = // A vertex shader calculates the gl position of the input vertices
            """
            attribute vec4 pos;
            void main() {
                gl_Position = pos;
            }
            """

    val vertexShader = gl.createShader(VERTEX_SHADER) // Create the actual vertex shader
    gl.shaderSource(vertexShader, vertexShaderCode)
    gl.compileShader(vertexShader)
    gl.attachShader(webGLProgramObject, vertexShader)

    val fragmentShaderCode = // Fragment shaders calculate the pixel color
            """
            precision mediump float;
            void main() {
                gl_FragColor = vec4(0.9, 0.2, 0.2, 1.0);
            }
            """
    val fragmentShader = gl.createShader(FRAGMENT_SHADER)
    gl.shaderSource(fragmentShader, fragmentShaderCode)
    gl.compileShader(fragmentShader)
    gl.attachShader(webGLProgramObject, fragmentShader)

    gl.linkProgram(webGLProgramObject)

    gl.useProgram(webGLProgramObject)
    gl.clearColor(0.9f, 0.9f, 0.9f, 1.0f)
    gl.clear(COLOR_BUFFER_BIT)

    val attributeLocation = gl.getAttribLocation(webGLProgramObject, "pos") // position is the name of the attribute in the vertex shader code

    val vertices = Float32Array(arrayOf(// 3D coordinates of a triangle. Vertex space is -1.0 to +1.0 and will be mapped to the viewport size
            0.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f))

    val vertexBuffer = gl.createBuffer()

    gl.bindBuffer(ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(ARRAY_BUFFER, vertices, STATIC_DRAW) // but the triangle coordinates into the current array buffer
    gl.vertexAttribPointer(attributeLocation, 3, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(attributeLocation)

    gl.drawArrays(TRIANGLES, 0, 3)

}


