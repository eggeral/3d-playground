package spr5

import org.khronos.webgl.*
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement
import spr5.matrix.Mat4
import spr5.scene.SceneObject
import spr5.scene.SceneRenderer
import threed.asRad
import webgl.createWebGLRenderingContext
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.document
import kotlin.browser.window


class WebGLRenderer : SceneRenderer {
    private var gl: WebGLRenderingContext;
    private var lastRender: Double = 0.0;

    private var vertexBuffer: WebGLBuffer;
    private var colorBuffer: WebGLBuffer;
    private var indexBuffer: WebGLBuffer;

    private var projectionMatrixUniform: WebGLUniformLocation
    private var viewMatrixUniform: WebGLUniformLocation
    private var modelMatrixUniform: WebGLUniformLocation

    private var objects: List<SceneObject> = listOf();

    private var modelMatrix: Mat4;
    private var projectionMatrix: Mat4;
    private var viewMatrix: Mat4

    init {
        val container = document.getElementById("container") as HTMLDivElement;
        val canvas = document.createElement("canvas") as HTMLCanvasElement;
        canvas.style.height = "100%";

        gl = createWebGLRenderingContext(canvas);

        container.appendChild(canvas);

        // Create buffers
        vertexBuffer = gl.createBuffer() as WebGLBuffer;
        colorBuffer = gl.createBuffer() as WebGLBuffer;
        indexBuffer = gl.createBuffer() as WebGLBuffer;


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

        projectionMatrixUniform = gl.getUniformLocation(shaderProgram, "projectionMatrix") as WebGLUniformLocation;
        viewMatrixUniform = gl.getUniformLocation(shaderProgram, "viewMatrix") as WebGLUniformLocation;
        modelMatrixUniform = gl.getUniformLocation(shaderProgram, "modelMatrix") as WebGLUniformLocation;

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
                40.0.asRad,
                gl.canvas.width.toDouble() / gl.canvas.height.toDouble(),
                1.0,
                100.0);
        viewMatrix = Mat4().translate(arrayOf(0.0, 0.0, -15.0));
        modelMatrix = Mat4().translate(arrayOf(-2.0, 1.0, 0.0));

        window.requestAnimationFrame { t -> renderFrame(t) }
    }

    private fun drawObjects() {
        gl.uniformMatrix4fv(modelMatrixUniform, false, modelMatrix.toFloat32Array());

        objects.forEach { o ->
            gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer);
            gl.bufferData(
                    WebGLRenderingContext.ARRAY_BUFFER,
                    Float32Array(o.getVertices()),
                    WebGLRenderingContext.STATIC_DRAW);

            gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer);
            gl.bufferData(
                    WebGLRenderingContext.ARRAY_BUFFER,
                    Float32Array(o.getColors()),
                    WebGLRenderingContext.STATIC_DRAW);

            gl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, indexBuffer);
            gl.bufferData(
                    WebGLRenderingContext.ELEMENT_ARRAY_BUFFER,
                    Uint16Array(o.getIndices()),
                    WebGLRenderingContext.STATIC_DRAW);

            gl.drawElements(
                    WebGLRenderingContext.TRIANGLES,
                    o.getIndices().size,
                    WebGLRenderingContext.UNSIGNED_SHORT,
                    0);
        }
    }

    private fun renderFrame(time: Double) {
        gl.fitDrawingBufferIntoCanvas();

        val deltaTime = ((time - lastRender) / 10.0).toFloat()
        lastRender = time;

        rotateModel(deltaTime * 0.005);

        gl.enable(WebGLRenderingContext.DEPTH_TEST)
        gl.depthFunc(WebGLRenderingContext.LEQUAL)
        gl.clearDepth(1.0f)

        gl.clearColor(0.5f, 0.5f, 0.5f, 0.9f)

        gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT.or(WebGLRenderingContext.DEPTH_BUFFER_BIT))

        gl.uniformMatrix4fv(projectionMatrixUniform, false, projectionMatrix.toFloat32Array())
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix.toFloat32Array())

        drawObjects();

        window.requestAnimationFrame { t -> renderFrame(t) }

    }

    override fun add(sceneObject: SceneObject) {
        objects += sceneObject;
    }

    override fun remove(sceneObject: SceneObject) {
        objects -= sceneObject;
    }

    override fun rotateModel(rotateRad: Double) {
        return rotateModel(rotateRad, rotateRad, rotateRad);
    }

    override fun rotateModel(rotateXRad: Double, rotateYRad: Double, rotateZRad: Double) {
        modelMatrix = modelMatrix * Mat4().rotateX(rotateXRad) *
                Mat4().rotateY(rotateYRad) *
                Mat4().rotateZ(rotateZRad);
    }
}