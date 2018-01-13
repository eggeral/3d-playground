package example.projection

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.WebGLRenderingContext
import spr5.matrix.mat4
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.window

fun drawOrthographicVsPerspectiveCubes(gl: WebGLRenderingContext, projectionMatrix: Float32Array) {

    val cube = Cube()
    val vertices = cube.vertices
    val colors = cube.colors
    val indices = cube.indices
    val vertexShaderCode = cube.vertexShaderCode
    val fragmentShaderCode = cube.fragmentShaderCode

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
    gl.vertexAttribPointer(color, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(color)

    gl.useProgram(shaderProgram)

    // END ------- RotatingCubeExample -------

    val viewMatrix = mat4.translate(mat4.create(), mat4.create(), arrayOf(0.0, 0.0, -15.0))

    // every model matrix defines different locations of the same cube
    val modelMatrices = arrayOf<Float32Array>(
            mat4.translate(mat4.create(), mat4.create(), arrayOf(0.0, 2.0, 0.0)),
            mat4.create(),
            mat4.translate(mat4.create(), mat4.create(), arrayOf(0.0, -2.0, 0.0))
    )

    // draws the same cube with different model matrices
    fun drawObjects(modelMatrices: Array<Float32Array>) {
        for (model: Float32Array in modelMatrices) {
            gl.uniformMatrix4fv(modelMatrixUniform, false, model)
            gl.drawElements(WebGLRenderingContext.TRIANGLES, indices.size, WebGLRenderingContext.UNSIGNED_SHORT, 0)
        }
    }

    // transforms every model matrix to a rotation
    fun transformModelMatrix(delta:Float) {
        var m:Float32Array
        for (i in modelMatrices.indices) {
            m = modelMatrices[i]
            if (i % 2 == 0)
                mat4.rotateY(m, m, delta * 0.005)
            else
                mat4.rotateY(m, m, delta * -0.005)
            modelMatrices[i] = m
        }
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
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix)


        drawObjects(modelMatrices)

        window.requestAnimationFrame { t -> animate(t) }
    }
    window.requestAnimationFrame { time -> animate(time) }
}