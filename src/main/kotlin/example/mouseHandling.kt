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
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import spr5.matrix.Mat4
import spr5.matrix.Vec3
import threed.*
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Math


fun mouseHandling(gl: WebGLRenderingContext) {
    var viewMatrixV3 = Vec3(0.0, 0.0, -15.0)

    var clickPosX = 0
    var clickPosY = 0
    var moveCam = false
    var dragging = false

    val MOUSE_BUTTON_LEFT: Short = 0
    val MOUSE_BUTTON_MIDDLE: Short = 1
    val MOUSE_BUTTON_RIGHT: Short = 2

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
    fun transformModelMatrix(delta:Float) {
        var m:Float32Array
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
    var animate = true
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
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix.toFloat32Array())


        drawObjects(modelMatrices)
        if (animate)
            window.requestAnimationFrame { t -> animate(t) }
    }
    window.requestAnimationFrame { time -> animate(time) }

    fun getPositionInCanvas(x: Int, y: Int): Array<Int> {
        val rect = gl.canvas.getBoundingClientRect()
        console.log("Left: " + rect.left + ", Top: " + rect.top)
        var cx = x - rect.left.toInt()
        var cy = y - rect.top.toInt()
        if (cx < 0)
            cx = 0
        if (cx > rect.right.toInt() - rect.left.toInt())
            cx = rect.right.toInt() - rect.left.toInt()
        if (cy < 0)
            cy = 0
        if (cy > rect.bottom.toInt() - rect.top.toInt())
            cy = rect.bottom.toInt() - rect.top.toInt()
        return arrayOf(cx, cy)
    }

    fun mouseDown(e: Any) {
        if (e is MouseEvent) {
            clickPosX = e.clientX
            clickPosY = e.clientY
            if(e.button == MOUSE_BUTTON_RIGHT) { // right mouse button to move cam
                moveCam = true
            } else if (e.button == MOUSE_BUTTON_LEFT) { // left mouse button to select an object
                val (cx, cy) = getPositionInCanvas(clickPosX, clickPosY)
                console.log("X: " + cx + ", Y: " + cy)
                dragging = true
                // 2do: select right object
                animate = false // just stop animation for now
            } else if (e.button == MOUSE_BUTTON_MIDDLE) {
                // do nothing
            }
        }
    }

    fun mouseMove(e: Any) {
        if (e is MouseEvent) {
            if(dragging == true) {
                // 2do: move selected object
            } else if (moveCam == true) {
                if(e.clientX > clickPosX)
                    viewMatrixV3.set(0, viewMatrixV3.get(0) + 0.1f)
                if(e.clientY > clickPosY)
                    viewMatrixV3.set(1, viewMatrixV3.get(1) - 0.1f)
                if(e.clientX < clickPosX)
                    viewMatrixV3.set(0, viewMatrixV3.get(0) - 0.1f)
                if(e.clientY < clickPosY)
                    viewMatrixV3.set(1, viewMatrixV3.get(1) + 0.1f)
                clickPosX = e.clientX
                clickPosY = e.clientY
                viewMatrix = Mat4().translate(viewMatrixV3) // z:-15f = more distance to the objects
            }
        }
    }

    fun mouseUp(e: Any) {
        if (e is MouseEvent) {
            if(e.button == MOUSE_BUTTON_LEFT) {
                dragging = false
                animate = true
                window.requestAnimationFrame { time -> animate(time) }
            }
            if(e.button == MOUSE_BUTTON_RIGHT) {
                moveCam = false
            }
            if(e.button == MOUSE_BUTTON_MIDDLE) {
                // do nothing
            }
        }
    }

    fun zoomCam(e: Any) { // zoom in or out camera
        if (e is WheelEvent) {
            console.log("Wheel event!" + e.deltaY)
            viewMatrixV3.set(2, viewMatrixV3.get(2) + (e.deltaY / 100).toFloat())
            viewMatrix = Mat4().translate(viewMatrixV3)
        }
    }

    document.addEventListener("mousedown", { e -> mouseDown(e) })
    document.addEventListener("mousemove", { e -> mouseMove(e) })
    document.addEventListener("mouseup", { e -> mouseUp(e) })
    document.addEventListener("mousewheel", { e -> zoomCam(e)})
    document.addEventListener("touchstart", { console.log("touch started" )})
    document.addEventListener("touchmove", { console.log("touch moved" )})
    document.addEventListener("touchend", { console.log("touch ended" )})
}