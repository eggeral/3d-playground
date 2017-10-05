package threed.example

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLRenderingContext.Companion.ARRAY_BUFFER
import org.khronos.webgl.WebGLRenderingContext.Companion.COLOR_BUFFER_BIT
import org.khronos.webgl.WebGLRenderingContext.Companion.ELEMENT_ARRAY_BUFFER
import org.khronos.webgl.WebGLRenderingContext.Companion.FLOAT
import org.khronos.webgl.WebGLRenderingContext.Companion.FRAGMENT_SHADER
import org.khronos.webgl.WebGLRenderingContext.Companion.STATIC_DRAW
import org.khronos.webgl.WebGLRenderingContext.Companion.TRIANGLES
import org.khronos.webgl.WebGLRenderingContext.Companion.UNSIGNED_SHORT
import org.khronos.webgl.WebGLRenderingContext.Companion.VERTEX_SHADER
import scene.Coordinate
import scene.Rgba
import threed.*


private val rectangleVertexShaderCode =
        """
            attribute vec3 vertices;
            attribute vec4 color;

            uniform mat4 modelMatrix;

            varying vec4 vColor;

            void main() {
                gl_Position = modelMatrix*vec4(vertices, 1.0);
                vColor = color;
            }
            """

private val rectangleFragmentShaderCode =
        """
            precision mediump float;
            varying vec4 vColor;

            void main() {
                gl_FragColor = vColor;
            }
            """

private fun createRectangleShaderProgram(gl: WebGLRenderingContext): WebGLProgram {

    val vertexShader = gl.createShader(VERTEX_SHADER) // Create the actual vertex shader
    gl.shaderSource(vertexShader, rectangleVertexShaderCode)
    gl.compileShader(vertexShader)

    val fragmentShader = gl.createShader(FRAGMENT_SHADER)
    gl.shaderSource(fragmentShader, rectangleFragmentShaderCode)
    gl.compileShader(fragmentShader)

    val shaderProgram = gl.createProgram() // GL needs a shader program in order to display something
    gl.attachShader(shaderProgram, vertexShader)
    gl.attachShader(shaderProgram, fragmentShader)
    gl.linkProgram(shaderProgram)

    return shaderProgram!!
}

fun drawRectangle(gl: WebGLRenderingContext, shaderProgram: WebGLProgram, color: Rgba, transform: Coordinate, scale: Coordinate) {

    // 3D coordinates of a rectangle. Vertex space is -1.0 to +1.0 and will be mapped to the viewport size
    val vertices = arrayOf(
            -1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f
    )

    //
    val indices = arrayOf<Short>(
            0, 1, 2,
            2, 3, 0
    )

    val colors = arrayOf(
            color.red, color.green, color.blue, color.alpha,
            color.red, color.green, color.blue, color.alpha,
            color.red, color.green, color.blue, color.alpha,
            color.red, color.green, color.blue, color.alpha,
            color.red, color.green, color.blue, color.alpha,
            color.red, color.green, color.blue, color.alpha
    )

    val indexBuffer = gl.createBuffer()
    gl.bindBuffer(ELEMENT_ARRAY_BUFFER, indexBuffer)
    gl.bufferData(ELEMENT_ARRAY_BUFFER, Uint16Array(indices), STATIC_DRAW)

    val colorBuffer = gl.createBuffer()
    gl.bindBuffer(ARRAY_BUFFER, colorBuffer)
    gl.bufferData(ARRAY_BUFFER, Float32Array(colors), STATIC_DRAW)

    // Create and store data into vertex buffer
    val vertexBufferRectangle = gl.createBuffer()
    gl.bindBuffer(ARRAY_BUFFER, vertexBufferRectangle)
    gl.bufferData(ARRAY_BUFFER, Float32Array(vertices), STATIC_DRAW) // put the rectangle coordinates into the current array buffer


    gl.bindBuffer(ARRAY_BUFFER, vertexBufferRectangle)
    val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices") // position is the name of the attribute in the vertex shader code
    gl.vertexAttribPointer(verticesAttribute, 3, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(verticesAttribute)

    gl.bindBuffer(ARRAY_BUFFER, colorBuffer)
    val colorAttribute = gl.getAttribLocation(shaderProgram, "color")
    gl.vertexAttribPointer(colorAttribute, 4, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(colorAttribute)

    gl.useProgram(shaderProgram)

    val modeMatrix =
            translateMatrix(transform.x, transform.y, transform.z) *
                    rotateXMatrix(0.0.asRad.toFloat()) *
                    rotateYMatrix(0.0.asRad.toFloat()) *
                    rotateZMatrix(0.0.asRad.toFloat()) *
                    scaleMatrix(scale.x, scale.y, scale.z)


    val modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix")
    gl.uniformMatrix4fv(modelMatrixUniform, false, modeMatrix)

    gl.bindBuffer(ELEMENT_ARRAY_BUFFER, indexBuffer)
    gl.drawElements(TRIANGLES, indices.size, UNSIGNED_SHORT, 0)


}

fun drawTwoRectangles(gl: WebGLRenderingContext) {

    val rectangleShaderProgram = createRectangleShaderProgram(gl)

    gl.clearColor(0.9f, 0.9f, 0.9f, 1.0f)
    gl.clear(COLOR_BUFFER_BIT)

    drawRectangle(gl, rectangleShaderProgram,
            color = Rgba(1.0f, 0.0f, 0.0f),
            transform = Coordinate(-0.5f, -0.5f, 0.0f),
            scale = Coordinate(0.5f, 0.5f, 0.0f)
    )

    drawRectangle(gl, rectangleShaderProgram,
            color = Rgba(0.0f, 1.0f, 0.0f),
            transform = Coordinate(0.0f, 0.0f, 0.0f),
            scale = Coordinate(0.5f, 0.5f, 0.0f)
    )

}




