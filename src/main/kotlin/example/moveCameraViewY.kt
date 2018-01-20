package example

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.WebGLRenderingContext
import spr5.matrix.Mat4
import spr5.matrix.Vec3
import spr5.scene.Coordinate
import spr5.scene.Rgba
import spr5.scene.createMulticolorCube
import threed.asRad
import threed.perspectiveProjectionMatrix
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.window

fun moveCameraViewY(gl: WebGLRenderingContext) {

    val viewMatrixV3 = Vec3(0.0, 0.0, -15.0)

    val cubeSize = 2.0f
    val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
            , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
            , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
            , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
            , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
            , Rgba(0.5f, 0.0f, 0.5f, 1.0f))

    val cube = createMulticolorCube(Coordinate(0.0f, 0.0f, 0.0f), cubeSize, cubeFacesColors)
    val vertices = cube.getVertices()
    val colors = cube.getColors()
    val indices = cube.getIndices()

    // BEGIN ------- same as RotatingCubeExample -------
    val vertexShaderCode =
            """
            uniform mat4 projectionMatrix;
            uniform mat4 viewMatrix;
            uniform mat4 modelMatrix;
            attribute vec3 vertices;
            attribute vec4 color;
            varying vec4 vColor;
            void main(void) {
                gl_Position = projectionMatrix*viewMatrix*modelMatrix*vec4(vertices, 1.);
                vColor = color;
            }
            """

    val fragmentShaderCode =
            """
            precision mediump float;
            varying vec4 vColor;
            void main(void) {
                gl_FragColor = vColor;
            }
            """

    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, Float32Array(vertices), WebGLRenderingContext.STATIC_DRAW)

    val colorBuffer = gl.createBuffer()
    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer)
    gl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, Float32Array(colors), WebGLRenderingContext.STATIC_DRAW)

    val indexBuffer = gl.createBuffer()
    gl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, indexBuffer)
    gl.bufferData(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, Uint16Array(indices), WebGLRenderingContext.STATIC_DRAW)


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

    val projectionMatrixUniform = gl.getUniformLocation(shaderProgram, "projectionMatrix")
    val viewMatrixUniform = gl.getUniformLocation(shaderProgram, "viewMatrix")
    val modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix")

    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
    val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices")
    gl.vertexAttribPointer(verticesAttribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(verticesAttribute)

    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer)
    val color = gl.getAttribLocation(shaderProgram, "color")
    gl.vertexAttribPointer(color, 4, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(color)

    gl.useProgram(shaderProgram)

    val projectionMatrix = perspectiveProjectionMatrix(40.0.asRad.toFloat(), (gl.canvas.width.toFloat() / gl.canvas.height.toFloat()), 1.0f, 100.0f)

    // END ------- RotatingCubeExample -------

    //var viewMatrix = translateMatrix(viewMatrixX, viewMatrixY, viewMatrixZ) // z:-15f = more distance to the objects
    var viewMatrix = Mat4().translate(viewMatrixV3)

    // every model matrix defines different locations of the same cube
    val modelMatrices = arrayOf<Float32Array>(
            Mat4().translate(arrayOf(-2.0, 1.0, 0.0)).toFloat32Array(),
            Mat4().translate(arrayOf(2.0, 1.0, 0.0)).toFloat32Array(),
            Mat4().translate(arrayOf(-2.0, -2.0, 0.0)).toFloat32Array(),
            Mat4().translate(arrayOf(2.0, -2.0, 0.0)).toFloat32Array()
    )

    // draws the same cube with different model matrices
    fun drawObjects(modelMatrices: Array<Float32Array>) {
        for (model: Float32Array in modelMatrices) {
            gl.uniformMatrix4fv(modelMatrixUniform, false, model)
            gl.drawElements(WebGLRenderingContext.TRIANGLES, indices.size, WebGLRenderingContext.UNSIGNED_SHORT, 0)
        }
    }

    // transforms every model matrix to a rotation
    fun transformModelMatrix(delta: Float) {
        var m: Float32Array
        for (i in modelMatrices.indices) {
            m = modelMatrices[i]
            if (i % 2 == 0)
                m = Mat4.rotateY(m, delta * 0.005)
            else
                m = Mat4.rotateY(m, delta * -0.005)
            modelMatrices[i] = m
        }
    }

    var timeOld = 0.0
    fun animate(time: Double) {

        gl.fitDrawingBufferIntoCanvas()

        val deltaTime = ((time - timeOld) / 10.0).toFloat()
        viewMatrix = viewMatrix.rotateY(deltaTime.toDouble().asRad)

        transformModelMatrix(deltaTime)

        timeOld = time

        gl.enable(WebGLRenderingContext.DEPTH_TEST)
        gl.depthFunc(WebGLRenderingContext.LEQUAL)
        gl.clearColor(0.5f, 0.5f, 0.5f, 0.9f)
        gl.clearDepth(1.0f)

        gl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, indexBuffer)
        gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT.or(WebGLRenderingContext.DEPTH_BUFFER_BIT))
        gl.uniformMatrix4fv(projectionMatrixUniform, false, projectionMatrix)
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix.toFloat32Array())


        drawObjects(modelMatrices)
        window.requestAnimationFrame { time -> animate(time) }
    }
    window.requestAnimationFrame { time -> animate(time) }
}