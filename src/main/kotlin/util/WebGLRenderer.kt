package util

import glmatrix.GlMatrix
import glmatrix.Mat4
import glmatrix.Vec3
import org.khronos.webgl.*
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import scene.SceneNode
import scene.SceneNodesAttached
import scene.SceneObject
import scene.SceneRenderer
import shadercode.ShaderCode
import webgl.createWebGLRenderingContext
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.document
import kotlin.browser.window


class WebGLRenderer : SceneRenderer {
    private var gl: WebGLRenderingContext
    private var lastRender: Double = 0.0

    private var vertexBuffer: WebGLBuffer
    private var colorBuffer: WebGLBuffer
    private var indexBuffer: WebGLBuffer

    private var projectionMatrixUniform: WebGLUniformLocation
    private var viewMatrixUniform: WebGLUniformLocation
    private var modelMatrixUniform: WebGLUniformLocation

    private var nodes: List<SceneNode> = listOf()

    //private var modelMatrix: Mat4
    private var projectionMatrix: Mat4
    private var viewMatrixV3 = Vec3(0.0, 0.0, -15.0)
    private var viewMatrix = Mat4().translate(viewMatrixV3)

    private var clickPosX = 950
    private var clickPosY = 600
    private var moveCam = false
    private var dragging = false

    private val MOUSE_BUTTON_LEFT: Short = 0
    private val MOUSE_BUTTON_MIDDLE: Short = 1
    private val MOUSE_BUTTON_RIGHT: Short = 2

    init {
        val container = document.getElementById("container") as HTMLDivElement
        val canvas = document.createElement("canvas") as HTMLCanvasElement
        canvas.style.height = "100%"

        document.addEventListener("mousedown", { e -> mouseDown(e) })
        document.addEventListener("mousemove", { e -> mouseMove(e) })
        document.addEventListener("mouseup", { e -> mouseUp(e) })
        document.addEventListener("mousewheel", { e -> zoomCam(e) })
        document.addEventListener("touchstart", { console.log("touch started") })
        document.addEventListener("touchmove", { console.log("touch moved") })
        document.addEventListener("touchend", { console.log("touch ended") })
        document.addEventListener("contextmenu", { e -> e.preventDefault() })

        gl = createWebGLRenderingContext(canvas)

        container.appendChild(canvas)

        // Create buffers
        vertexBuffer = gl.createBuffer() as WebGLBuffer
        colorBuffer = gl.createBuffer() as WebGLBuffer
        indexBuffer = gl.createBuffer() as WebGLBuffer

        val vertexShaderCode = ShaderCode.VERTEX_MULTI_COLOR_CUBE.value
        // Fragment shaders calculate the pixel color
        val fragmentShaderCode = ShaderCode.FRAGMENT_MULTI_COLOR_CUBE.value

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

        projectionMatrixUniform = gl.getUniformLocation(shaderProgram, "projectionMatrix") as WebGLUniformLocation
        viewMatrixUniform = gl.getUniformLocation(shaderProgram, "viewMatrix") as WebGLUniformLocation
        modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix") as WebGLUniformLocation

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

        projectionMatrix = Mat4().perspective(
                GlMatrix.toRad(40.0),
                gl.canvas.width.toDouble() / gl.canvas.height.toDouble(),
                1.0,
                100.0)
        viewMatrix = Mat4().translate(arrayOf(0.0, 0.0, -15.0))
        //modelMatrix = Mat4().translate(arrayOf(-2.0, 1.0, 0.0))

        window.requestAnimationFrame { t -> renderFrame(t) }
    }

    private fun drawObjects(nodes: List<SceneNode>) {
        //gl.uniformMatrix4fv(modelMatrixUniform, false, modelMatrix.toFloat32Array())


        nodes.forEach { node ->
            when (node) {
                is SceneObject -> {
                    gl.uniformMatrix4fv(modelMatrixUniform, false, node.model.toFloat32Array())
                    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
                    gl.bufferData(
                            WebGLRenderingContext.ARRAY_BUFFER,
                            Float32Array(node.getVertices()),
                            WebGLRenderingContext.STATIC_DRAW)

                    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer)
                    gl.bufferData(
                            WebGLRenderingContext.ARRAY_BUFFER,
                            Float32Array(node.getColors()),
                            WebGLRenderingContext.STATIC_DRAW)

                    gl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, indexBuffer)
                    gl.bufferData(
                            WebGLRenderingContext.ELEMENT_ARRAY_BUFFER,
                            Uint16Array(node.getIndices()),
                            WebGLRenderingContext.STATIC_DRAW)

                    gl.drawElements(
                            WebGLRenderingContext.TRIANGLES,
                            node.getIndices().size,
                            WebGLRenderingContext.UNSIGNED_SHORT,
                            0)

                }
                is SceneNodesAttached -> {
                    drawObjects(node.children)
                }
                else -> throw IllegalStateException("Unknown node type")
            }

        }
    }

    private fun renderFrameForEach(nodes: List<SceneNode>, deltaTime: Double) {
        nodes.forEach { node ->
            when (node) {
                is SceneObject -> {
                    node.model = Mat4.rotateX(node.model, deltaTime * node.rotationSpeedX)
                    node.model = Mat4.rotateY(node.model, deltaTime * node.rotationSpeedY)
                    node.model = Mat4.rotateZ(node.model, deltaTime * node.rotationSpeedZ)
                }
                is SceneNodesAttached -> {
                    renderFrameForEach(node.children, deltaTime)
                }
                else -> throw IllegalStateException("Unknown node type")
            }

        }
    }

    private fun renderFrame(time: Double) {
        gl.fitDrawingBufferIntoCanvas()
        val timeTemp = time
        val deltaTime = ((timeTemp - lastRender) / 10.0).toFloat()
        lastRender = timeTemp

        renderFrameForEach(nodes, deltaTime.toDouble())

        gl.enable(WebGLRenderingContext.DEPTH_TEST)
        gl.depthFunc(WebGLRenderingContext.LEQUAL)
        gl.clearDepth(1.0f)

        gl.clearColor(0.5f, 0.5f, 0.5f, 0.9f)

        gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT.or(WebGLRenderingContext.DEPTH_BUFFER_BIT))

        gl.uniformMatrix4fv(projectionMatrixUniform, false, projectionMatrix.toFloat32Array())
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix.toFloat32Array())

        drawObjects(nodes)

        window.requestAnimationFrame { t -> renderFrame(t) }


    }

    override fun add(sceneNode: SceneNode) {
        if (sceneNode is SceneObject)
            sceneNode.model = Mat4().translate(arrayOf(-2.0, 1.0, 0.0))
        nodes += sceneNode
    }

    override fun remove(sceneNode: SceneNode) {
        nodes -= sceneNode
    }

    override fun rotateModel(rotateRad: Double) {
        return rotateModel(rotateRad, rotateRad, rotateRad)
    }


    override fun rotateModel(rotateXRad: Double, rotateYRad: Double, rotateZRad: Double) {
        //modelMatrix = modelMatrix * Mat4().rotateX(rotateXRad) *
        //        Mat4().rotateY(rotateYRad) *
        //        Mat4().rotateZ(rotateZRad)
    }


    private fun getPositionInCanvas(x: Int, y: Int): Array<Int> {
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

    private fun mouseDown(e: Any) {
        if (e is MouseEvent) {
            clickPosX = e.clientX
            clickPosY = e.clientY
            when {
                e.button == MOUSE_BUTTON_RIGHT -> // right mouse button to move cam
                    moveCam = true
                e.button == MOUSE_BUTTON_LEFT -> { // left mouse button to select an object
                    val (cx, cy) = getPositionInCanvas(clickPosX, clickPosY)
                    console.log("X: $cx, Y: $cy")
                    dragging = true
                    // 2do: select right object
                }
                e.button == MOUSE_BUTTON_MIDDLE -> {
                    // do nothing
                }
            }
        }
    }

    private fun mouseMove(e: Any) {
        if (e is MouseEvent) {
            if (moveCam) {
                val newX = e.clientX
                val newY = e.clientY
                val deltaX = newX - clickPosX
                val deltaY = newY - clickPosY
                viewMatrix = viewMatrix.rotateX(GlMatrix.toRad(deltaX.toDouble()))
                viewMatrix = viewMatrix.rotateY(GlMatrix.toRad(deltaY.toDouble()))

                clickPosX = newX
                clickPosY = newY
            } else if (dragging) {
                //TODO: 0.1f should be replaced by perspective factor. Otherwise there are different
                // speeds when moving in X and Y-direction)
                if (e.clientX > clickPosX) {
                    viewMatrix[12] = viewMatrix[12] + 0.1
                }
                if (e.clientY > clickPosY)
                    viewMatrix[13] = viewMatrix[13] - 0.1
                if (e.clientX < clickPosX)
                    viewMatrix[12] = viewMatrix[12] - 0.1
                if (e.clientY < clickPosY)
                    viewMatrix[13] = viewMatrix[13] + 0.1
                clickPosX = e.clientX
                clickPosY = e.clientY
            }
        }
    }

    private fun mouseUp(e: Any) {
        if (e is MouseEvent) {
            if (e.button == MOUSE_BUTTON_LEFT) {
                dragging = false
                window.requestAnimationFrame { time -> renderFrame(time) }
            }
            if (e.button == MOUSE_BUTTON_RIGHT) {
                moveCam = false
            }
            if (e.button == MOUSE_BUTTON_MIDDLE) {
                // do nothing
            }
        }
    }

    private fun zoomCam(e: Any) { // zoom in or out camera
        if (e is WheelEvent) {
            viewMatrix[14] = viewMatrix[14] + e.deltaY / 300
        }
    }
}