package example

import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import spr5.scene.Coordinate
import spr5.scene.Rgba
import spr5.scene.SceneTriangle

fun drawSceneTriangle(gl: WebGLRenderingContext) {

    // prepare triangle
    val coords = arrayOf(
            Coordinate(0.0f, 0.5f, 0.0f),
            Coordinate(-0.5f, -0.5f, 0.0f),
            Coordinate(0.5f, -0.5f, 0.0f))

    val rgba = Rgba(1.0f, 0.4f, 0.0f, 1.0f) // orange

    val triangle = SceneTriangle(coords, rgba)

    // Create and store data into vertex buffer
    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(
            WebGLRenderingContext.ARRAY_BUFFER,
            vertexBuffer)
    gl.bufferData(
            WebGLRenderingContext.ARRAY_BUFFER,
            Float32Array(triangle.getVertices()),
            WebGLRenderingContext.STATIC_DRAW)

    val vertexShaderCode =
            """
            attribute vec3 vertices;

            void main() {
                gl_Position = vec4(vertices, 1.0);
            }
            """

    val fragmentShaderCode =
            """
            precision mediump float;
            uniform vec4 vColor;
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

    // set uniform vColor attribute from fragment shader to triangle rgba
    gl.uniform4fv(color, triangle.getColors())

    gl.drawArrays(WebGLRenderingContext.TRIANGLES, 0, 3)
}
