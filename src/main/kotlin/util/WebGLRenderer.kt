package util

import glmatrix.*
import org.khronos.webgl.*
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.KeyboardEvent
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
import kotlin.js.Math


class WebGLRenderer : SceneRenderer {
    private var gl: WebGLRenderingContext
    private var lastRender: Double = 0.0
    private var firstRender: Boolean = true

    private var vertexBuffer: WebGLBuffer
    private var colorBuffer: WebGLBuffer
    private var indexBuffer: WebGLBuffer

    private var projectionMatrixUniform: WebGLUniformLocation
    private var viewMatrixUniform: WebGLUniformLocation
    private var modelMatrixUniform: WebGLUniformLocation
    private var pickingUniform: WebGLUniformLocation
    private var diffuseUniform: WebGLUniformLocation
    private var viewMatrix = Mat4().translate(arrayOf(0.0, 0.0, -15.0))

    private var nodes: List<SceneNode> = listOf()

    private var projectionMatrix: Mat4

    private var clickPosX = 950
    private var clickPosY = 600
    private var moveCam = false
    private var dragging = false

    private val MOUSE_BUTTON_LEFT: Short = 0
    private val MOUSE_BUTTON_MIDDLE: Short = 1
    private val MOUSE_BUTTON_RIGHT: Short = 2

    init {
        val canvas = document.getElementById("canvas") as HTMLCanvasElement

        document.addEventListener("mousedown", { e -> mouseDown(e) })
        document.addEventListener("mousemove", { e -> mouseMove(e) })
        document.addEventListener("mouseup", { e -> mouseUp(e) })
        document.addEventListener("mousewheel", { e -> zoomCam(e) })
        document.addEventListener("touchstart", { console.log("touch started") })
        document.addEventListener("touchmove", { console.log("touch moved") })
        document.addEventListener("touchend", { console.log("touch ended") })
        document.addEventListener("contextmenu", { e -> e.preventDefault() })
        document.addEventListener("keydown", {e -> keyDown(e)})
        document.addEventListener("keyup", {e -> keyUp(e)})

        gl = createWebGLRenderingContext(canvas)


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
        pickingUniform = gl.getUniformLocation(shaderProgram, "uPicking") as WebGLUniformLocation
        diffuseUniform = gl.getUniformLocation(shaderProgram, "uMaterialDiffuse") as WebGLUniformLocation

        gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer)
        val colors = gl.getAttribLocation(shaderProgram, "color")
        gl.vertexAttribPointer(colors, 4, WebGLRenderingContext.FLOAT, false, 0, 0)
        gl.enableVertexAttribArray(colors)

        // Bind vertex buffer to shader program
        gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
        val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices")
        gl.vertexAttribPointer(verticesAttribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
        gl.enableVertexAttribArray(verticesAttribute)

        // get uniform vColor attribute from fragment shader
        val color = gl.getUniformLocation(shaderProgram, "vColor")

        // set background color
        gl.clearColor(0.9f, 0.9f, 0.9f, 1.0f)
        gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT)

        gl.useProgram(shaderProgram)

        projectionMatrix = Mat4().perspective(
                GlMatrix.toRadian(40.0),
                gl.canvas.width.toDouble() / gl.canvas.height.toDouble(),
                1.0,
                100.0)

        window.requestAnimationFrame { t -> renderFrame(t, false) }
        window.addEventListener("resize", {
            projectionMatrix = Mat4().perspective(
                    GlMatrix.toRadian(40.0),
                    gl.canvas.width.toDouble() / gl.canvas.height.toDouble(),
                    1.0,
                    100.0)
            renderFrame(lastRender, false)
        })
    }

    private fun drawObjects(nodes: List<SceneNode>, picking: Boolean, parentIdx: Int = -1) {
        //gl.uniformMatrix4fv(modelMatrixUniform, false, modelMatrix.toFloat32Array())

        var idx = if (parentIdx == -1) 1 else parentIdx

        nodes.forEach { node ->

            when (node) {
                is SceneObject -> {
                    if (picking) {
                        gl.uniform4fv(diffuseUniform, Vec4(1.0, 1.0, 1.0, idx / 255.0).toFloat32Array())

                        if (parentIdx == -1)
                            idx += 1
                    }

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
                    drawObjects(node.children, picking, idx)
                }
                else -> throw IllegalStateException("Unknown node type")
            }

        }
    }

    private fun renderFrameForEach(nodes: List<SceneNode>, deltaTime: Double) {
        nodes.forEach { node ->
            when (node) {
                is SceneObject -> {
                    if (firstRender) {
                        val startPoint = node.getAbsoluteCoordinate()
                        node.model.translate(Vec3(startPoint.x.toDouble(), startPoint.y.toDouble(), startPoint.z.toDouble()))
                    }
                    if (node.isChildOf != null) {
                        val parentNode: SceneNode? = node.isChildOf
                        if (parentNode != null) {
                            node.rotationAngleX = (deltaTime * parentNode.rotationSpeedX + node.rotationAngleX) % (2 * Math.PI)
                            node.rotationAngleY = (deltaTime * parentNode.rotationSpeedY + node.rotationAngleY) % (2 * Math.PI)
                            node.rotationAngleZ = (deltaTime * parentNode.rotationSpeedZ + node.rotationAngleZ) % (2 * Math.PI)
                            node.model.rotateX(deltaTime * parentNode.rotationSpeedX)
                            node.model.rotateY(deltaTime * parentNode.rotationSpeedY)
                            node.model.rotateZ(deltaTime * parentNode.rotationSpeedZ)
                        }
                    }
                    node.rotationAngleX = (deltaTime * node.rotationSpeedX + node.rotationAngleX) % (2 * Math.PI)
                    node.rotationAngleY = (deltaTime * node.rotationSpeedY + node.rotationAngleY) % (2 * Math.PI)
                    node.rotationAngleZ = (deltaTime * node.rotationSpeedZ + node.rotationAngleZ) % (2 * Math.PI)
                    node.model.rotateX(deltaTime * node.rotationSpeedX)
                    node.model.rotateY(deltaTime * node.rotationSpeedY)
                    node.model.rotateZ(deltaTime * node.rotationSpeedZ)

                    //for absolute movement
                    run {
                        var X = 0.0
                        var Y = 0.0
                        var Z = 0.0
                        var returnDirs: Triple<Double, Double, Double>
                        returnDirs = calcDirectionVectors3D(node, X, Y, Z)
                        X = returnDirs.first
                        Y = returnDirs.second
                        Z = returnDirs.third

                        node.model.translate(Vec3(X, Y, Z))
                    }
                }
                is SceneNodesAttached -> {
                    renderFrameForEach(node.children, deltaTime)
                }
                else -> throw IllegalStateException("Unknown node type")
            }
        }
    }

    private fun renderFrame(time: Double, picking: Boolean) {
        gl.fitDrawingBufferIntoCanvas()
        val deltaTime = ((time - lastRender) / 10.0).toFloat()
        lastRender = time

        renderFrameForEach(nodes, deltaTime.toDouble())

        gl.enable(WebGLRenderingContext.DEPTH_TEST)
        gl.depthFunc(WebGLRenderingContext.LEQUAL)
        gl.clearDepth(1.0f)

        gl.clearColor(0.5f, 0.5f, 0.5f, 1.0f)

        gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT.or(WebGLRenderingContext.DEPTH_BUFFER_BIT))

        gl.uniformMatrix4fv(projectionMatrixUniform, false, projectionMatrix.toFloat32Array())
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix.toFloat32Array())

        if (picking)
            gl.uniform1i(pickingUniform, 1)
        else
            gl.uniform1i(pickingUniform, 0)

        drawObjects(nodes, picking, -1)

        if (!picking) {
            window.requestAnimationFrame { t -> renderFrame(t, false) }
        }

        if (firstRender)
            firstRender = false

    }

    private fun calcDirectionVectors3D(o: SceneNode, X: Double, Y: Double, Z: Double) : Triple<Double, Double, Double> {
        //doesn't work for all combinations -> better use Eulerwinkel
        var returnDirs : Pair<Double, Double>
        var lX : Double = X
        var lY : Double = Y
        var lZ : Double = Z
        returnDirs = calcDirectionVectors2D(o.speedX, o.rotationAngleZ, lX, lY)
        lX += returnDirs.first
        lY += returnDirs.second
        returnDirs = calcDirectionVectors2D(o.speedY, o.rotationAngleX, lY, lZ)
        lY += returnDirs.first
        lZ += returnDirs.second
        returnDirs = calcDirectionVectors2D(o.speedZ, o.rotationAngleY, lZ, lX)
        lZ += returnDirs.first
        lX += returnDirs.second

        return Triple(lX, lY, lZ)
    }

    private fun calcDirectionVectors2D(speed: Double, angle: Double, direction1: Double, direction2: Double) : Pair<Double, Double> {
        if ((speed == 0.0) || (angle == 0.0))
            return Pair(speed, 0.0)
        var Dir1 = direction1
        var Dir2 = direction2
        if (angle == 0.0) {
            Dir1 = speed
        }
        else if (angle == Math.PI/2) {
            Dir2 = speed
        }
        else if (angle == Math.PI) {
            Dir1 = - speed
        }
        else if (angle == Math.PI * 1.5) {
            Dir2 = -speed
        } else if (angle < Math.PI/2) {
            var TempY = speed * Math.sin(angle)
            Dir1 = TempY * Math.sin(Math.PI/2 - angle) / Math.sin(angle)
            Dir2 = -TempY
        } else if (angle < Math.PI) {
            var rotationZ = 2* Math.PI - angle
            var TempY = speed * Math.sin(rotationZ)
            Dir1 = TempY * Math.sin(Math.PI/2 - rotationZ) / Math.sin(rotationZ)
            Dir2 = TempY
        } else if (angle < 3*Math.PI/2) {
            var rotationZ = angle - Math.PI
            var TempY = speed * Math.sin(rotationZ)
            Dir1 = - TempY * Math.sin(Math.PI/2 - rotationZ) / Math.sin(rotationZ)
            Dir2 = TempY
        } else if (angle < 2* Math.PI) {
            var rotationZ = -angle
            var TempY = speed * Math.sin(rotationZ)
            Dir1 = TempY * Math.sin(Math.PI/2 - rotationZ) / Math.sin(rotationZ)
            Dir2 = TempY
        }
        return Pair(Dir1, Dir2)
    }

    override fun add(sceneNode: SceneNode) {
        if (sceneNode in nodes) {
            remove(sceneNode)
        }
        if (sceneNode is SceneObject)
            sceneNode.model = Mat4().translate(arrayOf(0.0, 0.0, 0.0))
        nodes += sceneNode
    }

    override fun remove(sceneNode: SceneNode) {
        nodes -= sceneNode
    }

    fun attachToNode(nodeList: SceneNodesAttached, node: SceneNode) {
        nodes -= node
        var nl = nodeList
        nl.addChild(node)
    }

    fun removeFromNode(nodeList: SceneNodesAttached, node: SceneNode, resetDefault: Boolean) {
        var nl = nodeList
        nl.removeChild(node, resetDefault)
        nodes += node
    }

    private fun getPositionInCanvas(x: Int, y: Int): Array<Int> {
        val rect = gl.canvas.getBoundingClientRect()
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
                viewMatrix = viewMatrix.rotateX(GlMatrix.toRadian(deltaX.toDouble()))
                viewMatrix = viewMatrix.rotateY(GlMatrix.toRadian(deltaY.toDouble()))

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

                renderFrame(lastRender, true)
                val readout = Uint8Array(4)
                val coords = get2dCoords(e)
                gl.readPixels(coords.x.toInt(), coords.y.toInt(), 1, 1, WebGLRenderingContext.RGBA, WebGLRenderingContext.UNSIGNED_BYTE, readout)

                console.log(readout)

                val idx = readout[3] - 1

                if (idx >= 0 && idx < nodes.size) {
                    if (ctrlPressed)
                        nodes[idx].setHit(!nodes[idx].isHit())
                    else {
                        releaseHighlightedObjects()
                        nodes[idx].setHit(true)
                    }
                } else { // nothing hit
                    if (!ctrlPressed) releaseHighlightedObjects()
                }
            }
            if (e.button == MOUSE_BUTTON_RIGHT) {
                moveCam = false
            }
            if (e.button == MOUSE_BUTTON_MIDDLE) {
                // do nothing
            }
        }
    }

    private fun releaseHighlightedObjects() {
        for (node in nodes) {
            node.setHit(false)
        }
    }

    private var ctrlPressed: Boolean = false

    private fun keyDown(e: Any) {
        // check for CTRL pressed
        if (e is KeyboardEvent && e.ctrlKey && !ctrlPressed) {
             ctrlPressed = true
        }
    }

    private fun keyUp(e: Any) {
        // check for CTRL release (keyCode 17 = ctrlKey)
        if (e is KeyboardEvent && e.keyCode == 17) {
            ctrlPressed = false
        }
    }

    private fun zoomCam(e: Any) { // zoom in or out camera
        if (e is WheelEvent) {
            viewMatrix[14] = viewMatrix[14] + e.deltaY / 300
        }
    }

    private fun get2dCoords(ev: MouseEvent): Vec2 {
        var top = 0
        var left = 0
        var obj: HTMLElement? = gl.canvas

        while (obj is HTMLElement && obj.tagName !== "BODY") {
            top += obj.offsetTop
            left += obj.offsetLeft
            obj = obj.offsetParent as HTMLElement?
        }

        left += window.pageXOffset.toInt()
        top -= window.pageYOffset.toInt()

        // return relative mouse position
        val x = ev.clientX - left
        val y = gl.canvas.height - (ev.clientY - top)
        //console.info('x='+x+', y='+y);
        return Vec2(x.toDouble(), y.toDouble())
    }
}
