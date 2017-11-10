package threed.example

import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import threed.*

fun scaleAndRotateTriangle(gl: WebGLRenderingContext) {

    val vertices = arrayOf(
            0.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f
    )


    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, Float32Array(vertices), WebGLRenderingContext.STATIC_DRAW)

    val vertexShaderCode =
            """
            attribute vec3 vertices;
            uniform mat4 modelMatrix;

            void main(void) {
                gl_Position = modelMatrix*vec4(vertices, 1.0);
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

    val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices")
    gl.vertexAttribPointer(verticesAttribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(verticesAttribute)

    // Translate * Rotate * Scale -> First scale, then rotate, then translate
    val modeMatrix =
            translateMatrix(0.5f, 0.5f, 0.0f) *
                    rotateXMatrix(0.0.asRad.toFloat()) *
                    rotateYMatrix(0.0.asRad.toFloat()) *
                    rotateZMatrix(10.0.asRad.toFloat()) *
                    scaleMatrix(0.5f, 0.5f, 1.0f)


    val modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix")
    gl.uniformMatrix4fv(modelMatrixUniform, false, modeMatrix)

    gl.drawArrays(WebGLRenderingContext.TRIANGLES, 0, 3)

}
