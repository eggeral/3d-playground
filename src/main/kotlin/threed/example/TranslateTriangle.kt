package threed.example

import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLRenderingContext.Companion.ARRAY_BUFFER
import org.khronos.webgl.WebGLRenderingContext.Companion.COLOR_BUFFER_BIT
import org.khronos.webgl.WebGLRenderingContext.Companion.FLOAT
import org.khronos.webgl.WebGLRenderingContext.Companion.FRAGMENT_SHADER
import org.khronos.webgl.WebGLRenderingContext.Companion.STATIC_DRAW
import org.khronos.webgl.WebGLRenderingContext.Companion.TRIANGLES
import org.khronos.webgl.WebGLRenderingContext.Companion.VERTEX_SHADER

fun translateTriangle(gl: WebGLRenderingContext) {

    val vertices = arrayOf(
            0.0f, 0.25f, 0.0f,
            0.25f, -0.25f, 0.0f,
            -0.25f, -0.25f, 0.0f
    )

    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(ARRAY_BUFFER, Float32Array(vertices), STATIC_DRAW)

    val vertexShaderCode =
            """
            attribute vec3 position;
            uniform mat4 translationMatrix;

            void main(void) {
                gl_Position = translationMatrix*vec4(position, 1.0);
            }
            """

    val fragmentShaderCode =
            """
            precision mediump float;
            void main(void) {
                gl_FragColor = vec4(0.9, 0.2, 0.2, 1.0);
            }
            """

    val vertexShader = gl.createShader(VERTEX_SHADER)
    gl.shaderSource(vertexShader, vertexShaderCode)
    gl.compileShader(vertexShader)

    val fragmentShader = gl.createShader(FRAGMENT_SHADER)
    gl.shaderSource(fragmentShader, fragmentShaderCode)
    gl.compileShader(fragmentShader)

    val shaderProgram = gl.createProgram()
    gl.attachShader(shaderProgram, vertexShader)
    gl.attachShader(shaderProgram, fragmentShader)
    gl.linkProgram(shaderProgram)

    gl.useProgram(shaderProgram)

    gl.clearColor(0.5f, 0.5f, 0.5f, 0.9f)
    gl.clear(COLOR_BUFFER_BIT)

    val position = gl.getAttribLocation(shaderProgram, "position")
    gl.vertexAttribPointer(position, 3, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(position)

    // The actual translation
    val translationMatrix = arrayOf(
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.25f, 0.25f, 0.0f, 1.0f) // tx, ty, tz
    val translationMatrixUniform = gl.getUniformLocation(shaderProgram, "translationMatrix")
    gl.uniformMatrix4fv(translationMatrixUniform, false, translationMatrix)

    gl.drawArrays(TRIANGLES, 0, 3)

}

