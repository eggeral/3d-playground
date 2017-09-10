package threed.example

import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import kotlin.js.Math


// We want a square to be square.

fun projection(gl: WebGLRenderingContext) {

    val vertices = arrayOf(

            -0.25f, -0.25f, 0.0f,
            0.25f, -0.25f, 0.0f,
            -0.25f, 0.25f, 0.0f,

            -0.25f, 0.25f, 0.0f,
            0.25f, -0.25f, 0.0f,
            0.25f, 0.25f, 0.0f

    )

    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, Float32Array(vertices), WebGLRenderingContext.STATIC_DRAW)

    val vertexShaderCode =
            """
            attribute vec3 position;
            uniform mat4 projectionMatrix;
            uniform mat4 viewMatrix;
            uniform mat4 modelMatrix;

            void main(void) {
                gl_Position = projectionMatrix*viewMatrix*modelMatrix*vec4(position, 1.0);
            }
            """

    val fragmentShaderCode =
            """
            precision mediump float;
            void main(void) {
                gl_FragColor = vec4(0.9, 0.2, 0.2, 1.0);
            }
            """

    val vertexShader = gl.createShader(WebGLRenderingContext.VERTEX_SHADER)
    gl.shaderSource(vertexShader, vertexShaderCode)
    gl.compileShader(vertexShader)

    val fragmentShader = gl.createShader(WebGLRenderingContext.FRAGMENT_SHADER)
    gl.shaderSource(fragmentShader, fragmentShaderCode)
    gl.compileShader(fragmentShader)

    val shaderProgram = gl.createProgram()
    gl.attachShader(shaderProgram, vertexShader)
    gl.attachShader(shaderProgram, fragmentShader)
    gl.linkProgram(shaderProgram)

    gl.useProgram(shaderProgram)

    gl.clearColor(0.5f, 0.5f, 0.5f, 0.9f)
    gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT)

    val position = gl.getAttribLocation(shaderProgram, "position")
    gl.vertexAttribPointer(position, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(position)

    val viewMatrix = arrayOf(
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 6.0f, 1.0f) // tx, ty, tz

    val translationMatrixUniform = gl.getUniformLocation(shaderProgram, "viewMatrix")
    gl.uniformMatrix4fv(translationMatrixUniform, false, viewMatrix)

    fun createProjectionMatrix(angle: Float, aspectRatio: Float, zMin: Float, zMax: Float): Array<Float> {
        val ang = Math.tan((angle * .5) * Math.PI / 180).toFloat()//angle*.5
        return arrayOf(
                0.5f / ang, 0.0f, 0.0f, 0.0f,
                0.0f, 0.5f * aspectRatio / ang, 0.0f, 0.0f,
                0.0f, 0.0f, -(zMax + zMin) / (zMax - zMin), -1.0f,
                0.0f, 0.0f, (-2 * zMax * zMin) / (zMax - zMin), 0.0f
        )
    }

    val projectionMatrix = createProjectionMatrix(40.0f, (gl.canvas.width.toFloat() / gl.canvas.height.toFloat()), 1.0f, 100.0f)
    val projectionMatrixUniform = gl.getUniformLocation(shaderProgram, "projectionMatrix")
    gl.uniformMatrix4fv(projectionMatrixUniform, false, projectionMatrix)


    val modelMatrix = arrayOf(
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    )

    val modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix")
    gl.uniformMatrix4fv(modelMatrixUniform, false, modelMatrix)



    gl.drawArrays(WebGLRenderingContext.TRIANGLES, 0, 6)

}