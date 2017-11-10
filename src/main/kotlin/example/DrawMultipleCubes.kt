package example

import webgl.fitDrawingBufferIntoCanvas
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.WebGLRenderingContext.Companion.ARRAY_BUFFER
import org.khronos.webgl.WebGLRenderingContext.Companion.COLOR_BUFFER_BIT
import org.khronos.webgl.WebGLRenderingContext.Companion.DEPTH_BUFFER_BIT
import org.khronos.webgl.WebGLRenderingContext.Companion.DEPTH_TEST
import org.khronos.webgl.WebGLRenderingContext.Companion.ELEMENT_ARRAY_BUFFER
import org.khronos.webgl.WebGLRenderingContext.Companion.FLOAT
import org.khronos.webgl.WebGLRenderingContext.Companion.FRAGMENT_SHADER
import org.khronos.webgl.WebGLRenderingContext.Companion.LEQUAL
import org.khronos.webgl.WebGLRenderingContext.Companion.STATIC_DRAW
import org.khronos.webgl.WebGLRenderingContext.Companion.TRIANGLES
import org.khronos.webgl.WebGLRenderingContext.Companion.UNSIGNED_SHORT
import org.khronos.webgl.WebGLRenderingContext.Companion.VERTEX_SHADER
import threed.*
import kotlin.browser.window


fun drawMultipleCubes(gl: WebGLRenderingContext) {

    // BEGIN ------- same as RotatingCubeExample -------
    val vertexShaderCode =
            """
            uniform mat4 projectionMatrix;
            uniform mat4 viewMatrix;
            uniform mat4 modelMatrix;

            attribute vec3 vertices;
            attribute vec3 color;
            varying vec3 vColor;

            void main(void) {
                gl_Position = projectionMatrix*viewMatrix*modelMatrix*vec4(vertices, 1.);
                vColor = color;
            }
            """

    val fragmentShaderCode =
            """
            precision mediump float;
            varying vec3 vColor;
            void main(void) {
                gl_FragColor = vec4(vColor, 1.);
            }
            """

    val vertices = arrayOf(
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,

            -1.0f, 1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,

            1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,

            -1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,

            1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,
            1.0f, 1.0f, 1.0f,

            1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,

            1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,

            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, -1.0f
    )

    val colors = arrayOf(
            5.0f, 3.0f, 7.0f, 5.0f, 3.0f, 7.0f, 5.0f, 3.0f, 7.0f, 5.0f, 3.0f, 7.0f,
            1.0f, 1.0f, 3.0f, 1.0f, 1.0f, 3.0f, 1.0f, 1.0f, 3.0f, 1.0f, 1.0f, 3.0f,
            0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f
    )

    val indices = arrayOf<Short>(
            0, 1, 2,
            0, 2, 3,

            4, 5, 6,
            4, 6, 7,

            8, 9, 10,
            8, 10, 11,

            12, 13, 14,
            12, 14, 15,

            16, 17, 18,
            16, 18, 19,

            20, 21, 22,
            20, 22, 23
    )

    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(ARRAY_BUFFER, Float32Array(vertices), STATIC_DRAW)

    val colorBuffer = gl.createBuffer()
    gl.bindBuffer(ARRAY_BUFFER, colorBuffer)
    gl.bufferData(ARRAY_BUFFER, Float32Array(colors), STATIC_DRAW)

    val indexBuffer = gl.createBuffer()
    gl.bindBuffer(ELEMENT_ARRAY_BUFFER, indexBuffer)
    gl.bufferData(ELEMENT_ARRAY_BUFFER, Uint16Array(indices), STATIC_DRAW)


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

    val projectionMatrixUniform = gl.getUniformLocation(shaderProgram, "projectionMatrix")
    val viewMatrixUniform = gl.getUniformLocation(shaderProgram, "viewMatrix")
    val modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix")

    gl.bindBuffer(ARRAY_BUFFER, vertexBuffer)
    val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices")
    gl.vertexAttribPointer(verticesAttribute, 3, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(verticesAttribute)

    gl.bindBuffer(ARRAY_BUFFER, colorBuffer)
    val color = gl.getAttribLocation(shaderProgram, "color")
    gl.vertexAttribPointer(color, 3, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(color)

    gl.useProgram(shaderProgram)

    val projectionMatrix = perspectiveProjectionMatrix(40.0.asRad.toFloat(), (gl.canvas.width.toFloat() / gl.canvas.height.toFloat()), 1.0f, 100.0f)

    // END ------- RotatingCubeExample -------

    val viewMatrix = translateMatrix(0.0f, 0.0f, -15.0f) // z:-15f = more distance to the objects

    // every model matrix defines different locations of the same cube
    val modelMatrices = arrayOf<Array<Float>>(
            translateMatrix(-2.0f, 1.0f, 0.0f),  // cube at location 1
            translateMatrix(2.0f, 1.0f, 0.0f),   // cube at location 2
            translateMatrix(-2.0f, -2.0f, 0.0f), // cube at location 3
            translateMatrix(2.0f, -2.0f, 0.0f)   // cube at location 4
    )

    // draws the same cube with different model matrices
    fun drawObjects(modelMatrices: Array<Array<Float>>) {
        for (model: Array<Float> in modelMatrices) {
            gl.uniformMatrix4fv(modelMatrixUniform, false, model)
            gl.drawElements(TRIANGLES, indices.size, UNSIGNED_SHORT, 0)
        }
    }

    // transforms every model matrix to a rotation
    fun transformModelMatrix(delta:Float) {
        var m:Array<Float>
        for (i in modelMatrices.indices) {
            // Warning: This also creates a lot of objects. In place matrix operations might be more efficient
            m = modelMatrices[i]
            m = m * rotateXMatrix(delta * 0.005f) *
                    rotateYMatrix(delta * 0.005f) *
                    rotateZMatrix(delta * 0.005f)
            modelMatrices[i] = m
        }
    }

    var timeOld = 0.0
    fun animate(time: Double) {

        gl.fitDrawingBufferIntoCanvas()

        val deltaTime = ((time - timeOld) / 10.0).toFloat()

        transformModelMatrix(deltaTime)

        timeOld = time

        gl.enable(DEPTH_TEST)
        gl.depthFunc(LEQUAL)
        gl.clearColor(0.5f, 0.5f, 0.5f, 0.9f)
        gl.clearDepth(1.0f)

        gl.bindBuffer(ELEMENT_ARRAY_BUFFER, indexBuffer)
        gl.clear(COLOR_BUFFER_BIT.or(DEPTH_BUFFER_BIT))
        gl.uniformMatrix4fv(projectionMatrixUniform, false, projectionMatrix)
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix)


        drawObjects(modelMatrices)

        window.requestAnimationFrame { t -> animate(t) }
    }
    window.requestAnimationFrame { time -> animate(time) }
}
