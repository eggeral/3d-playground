package spr5

import org.khronos.webgl.Uint16Array
import org.khronos.webgl.WebGLBuffer
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLUniformLocation
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLDivElement
import spr5.matrix.Mat4
import threed.*
import webgl.createWebGLRenderingContext
import webgl.fitDrawingBufferIntoCanvas
import kotlin.browser.document
import kotlin.browser.window

class WebGLRenderer {
    private var gl: WebGLRenderingContext;
    private var lastRender: Double = 0.0;
    private var indexBuffer: WebGLBuffer;

    private var projectionMatrixUniform: WebGLUniformLocation
    private var viewMatrixUniform: WebGLUniformLocation
    private var modelMatrixUniform: WebGLUniformLocation

    private var projectionMatrix: Mat4
    private var viewMatrix: Mat4
    private var modelMatrix: Mat4

    constructor() {
        var container = document.getElementById("container") as HTMLDivElement;
        var canvas = document.createElement("canvas") as HTMLCanvasElement;
        canvas.style.height = "100%"

        gl = createWebGLRenderingContext(canvas)

        container.appendChild(canvas)

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
        );

        indexBuffer = gl.createBuffer() as WebGLBuffer;
        gl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, indexBuffer);
        gl.bufferData(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, Uint16Array(indices), WebGLRenderingContext.STATIC_DRAW);

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

        projectionMatrix = Mat4().perspective(
                40.0.asRad,
                gl.canvas.width.toDouble() / gl.canvas.height.toDouble(),
                1.0,
                100.0);
        viewMatrix = Mat4().translate(arrayOf(0.0, 0.0, -15.0));
        modelMatrix = Mat4().translate(arrayOf(-2.0, 1.0, 0.0));

        window.requestAnimationFrame { t -> renderFrame(t) }
    }

    fun renderFrame(time: Double) {
        console.log("request animation frame");

        gl.fitDrawingBufferIntoCanvas();

        val deltaTime = ((time - lastRender) / 10.0).toFloat()
        lastRender = time;

        rotateModelMatrix(deltaTime * 0.005);

        gl.enable(WebGLRenderingContext.DEPTH_TEST)
        gl.depthFunc(WebGLRenderingContext.LEQUAL)
        gl.clearColor(0.5f, 0.5f, 0.5f, 0.9f)
        gl.clearDepth(1.0f)

        gl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, indexBuffer)
        gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT.or(WebGLRenderingContext.DEPTH_BUFFER_BIT))
        gl.uniformMatrix4fv(projectionMatrixUniform, false, projectionMatrix.toFloat32Array())
        gl.uniformMatrix4fv(viewMatrixUniform, false, viewMatrix.toFloat32Array())


        window.requestAnimationFrame { t -> renderFrame(t) }

    }

    fun rotateModelMatrix(rotateRad: Double) {
        return rotateModelMatrix(rotateRad, rotateRad, rotateRad);
    }

    fun rotateModelMatrix(rotateXRad: Double, rotateYRad: Double, rotateZRad: Double) {
        modelMatrix = modelMatrix * Mat4().rotateX(rotateXRad) *
                Mat4().rotateY(rotateYRad) *
                Mat4().rotateZ(rotateZRad);
    }
}