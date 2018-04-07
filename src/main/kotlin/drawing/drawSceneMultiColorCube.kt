package drawing

import glmatrix.Mat4
import glmatrix.Vec3
import glmatrix.glMatrix
import glmatrix.glMatrix.Companion.perspectiveProjectionMatrix
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.WebGLRenderingContext
import scene.Coordinate
import scene.Rgba
import scene.createMulticolorCube
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.window

fun drawSceneMultiColorCube(gl: WebGLRenderingContext) {

    val center = Coordinate(0.0f, 0.0f, 0.0f)
    val cubeSize = 2.0f
    val cubeFacesColors = arrayOf(Rgba(1.0f, 0.0f, 0.0f, 1.0f)
            , Rgba(0.0f, 1.0f, 0.0f, 1.0f)
            , Rgba(0.0f, 0.0f, 1.0f, 1.0f)
            , Rgba(0.5f, 0.5f, 0.0f, 1.0f)
            , Rgba(0.0f, 0.5f, 0.5f, 1.0f)
            , Rgba(0.5f, 0.0f, 0.5f, 1.0f))
    val cube = createMulticolorCube(center, cubeSize, cubeFacesColors)
    val cubeColors = cube.getColors()

    // Create and store data into vertex buffer
    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(
            WebGLRenderingContext.ARRAY_BUFFER,
            vertexBuffer)
    gl.bufferData(
            WebGLRenderingContext.ARRAY_BUFFER,
            Float32Array(cube.getVertices()),
            WebGLRenderingContext.STATIC_DRAW)

    val colorBuffer = gl.createBuffer()
    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer)
    gl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, Float32Array(cubeColors), WebGLRenderingContext.STATIC_DRAW)

    //Index-Buffer???
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
    val indexBuffer = gl.createBuffer()
    gl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, indexBuffer)
    gl.bufferData(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, Uint16Array(indices), WebGLRenderingContext.STATIC_DRAW)

    val vertexShaderCode =
            """
            uniform mat4 projectionMatrix;
            uniform mat4 viewMatrix;
            uniform mat4 modelMatrix;

            attribute vec3 vertices;
            attribute vec4 color;
            varying vec4 vColor;

            void main(void) {
                gl_Position = projectionMatrix*viewMatrix*modelMatrix*vec4(vertices, 1.0);
                vColor = color;
            }
            """
    val fragmentShaderCode = // Fragment shaders calculate the pixel color
            """
            precision mediump float;
            varying vec4 vColor;
            void main(void) {
                gl_FragColor = vColor;
            }
            """

    // Create vertex shader
    val vertexShader = gl.createShader(WebGLRenderingContext.VERTEX_SHADER)
    gl.shaderSource(vertexShader, vertexShaderCode)
    gl.compileShader(vertexShader)

    // Create fragment shader
    val fragmentShader = gl.createShader(WebGLRenderingContext.FRAGMENT_SHADER)
    gl.shaderSource(fragmentShader, fragmentShaderCode)
    gl.compileShader(fragmentShader)

    // Create shader program and link it
    val shaderProgram = gl.createProgram()
    gl.attachShader(shaderProgram, vertexShader)
    gl.attachShader(shaderProgram, fragmentShader)
    gl.linkProgram(shaderProgram)

    val projectionMatrixUniform = gl.getUniformLocation(shaderProgram, "projectionMatrix")
    val viewMatrixUniform = gl.getUniformLocation(shaderProgram, "viewMatrix")
    val modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix")

    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer)
    val colors = gl.getAttribLocation(shaderProgram, "color")
    gl.vertexAttribPointer(colors, 4, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(colors)

    // Bind vertex buffer to shader program
    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
    val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices")
    gl.vertexAttribPointer(verticesAttribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(verticesAttribute)

    // set background color
    gl.clearColor(0.9f, 0.9f, 0.9f, 1.0f)
    gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT)

    gl.useProgram(shaderProgram)

    val projectionMatrix = perspectiveProjectionMatrix(glMatrix.toRad(40.0).toFloat(), (gl.canvas.width.toFloat() / gl.canvas.height.toFloat()), 1.0f, 100.0f)

    val viewMatrix: Mat4 = Mat4().translate(Vec3(0.0, 0.0, -15.0)) // z:-15f = more distance to the objects

    var modelMatrix: Mat4 = Mat4().translate(Vec3(-2.0, 1.0, 0.0))

    fun drawObject(modelMatrix: Mat4) {
        gl.uniformMatrix4fv(modelMatrixUniform, false, modelMatrix.toFloat32Array())
        gl.drawElements(WebGLRenderingContext.TRIANGLES, indices.size, WebGLRenderingContext.UNSIGNED_SHORT, 0)
    }

    // transforms every model matrix to a rotation
    fun transformModelMatrix(delta: Float) {
        modelMatrix = modelMatrix * modelMatrix.rotateX(delta * 0.005) * modelMatrix.rotateY(delta * 0.005) * modelMatrix.rotateZ(delta * 0.005)

    }

    var timeOld = 0.0
    fun animate(time: Double) {

        gl.fitDrawingBufferIntoCanvas()

        val deltaTime = ((time - timeOld) / 10.0).toFloat()

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


        drawObject(modelMatrix)

        window.requestAnimationFrame { t -> animate(t) }
    }
    window.requestAnimationFrame { time -> animate(time) }
    //gl.drawArrays(WebGLRenderingContext.TRIANGLES, 0, 36)
}