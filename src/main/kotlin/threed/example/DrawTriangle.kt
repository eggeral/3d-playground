package threed.example

import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLRenderingContext.Companion.ARRAY_BUFFER
import org.khronos.webgl.WebGLRenderingContext.Companion.COLOR_BUFFER_BIT
import org.khronos.webgl.WebGLRenderingContext.Companion.FLOAT
import org.khronos.webgl.WebGLRenderingContext.Companion.FRAGMENT_SHADER
import org.khronos.webgl.WebGLRenderingContext.Companion.STATIC_DRAW
import org.khronos.webgl.WebGLRenderingContext.Companion.TRIANGLES
import org.khronos.webgl.WebGLRenderingContext.Companion.VERTEX_SHADER


fun drawTriangle(gl: WebGLRenderingContext) {

    // 3D coordinates of a triangle. Vertex space is -1.0 to +1.0 and will be mapped to the viewport size
    val vertices = arrayOf(
            0.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f
    )

    // Create and store data into vertex buffer
    val vertexBuffer = gl.createBuffer()
    gl.bindBuffer(ARRAY_BUFFER, vertexBuffer)
    gl.bufferData(ARRAY_BUFFER, Float32Array(vertices), STATIC_DRAW) // but the triangle coordinates into the current array buffer

    // A vertex shader calculates the gl position of the input vertices ( in this case just copy the position)
    // The result is a Vec4. Why? -> https://developer.mozilla.org/docs/Web/API/WebGL_API/WebGL_model_view_projection
    val vertexShaderCode =
            """
            attribute Vec3 vertices;
            void main() {
                gl_Position = Vec4(vertices, 1.0);
            }
            """

    val fragmentShaderCode = // Fragment shaders calculate the pixel color
            """
            precision mediump float;
            void main() {
                gl_FragColor = Vec4(0.9, 0.2, 0.2, 1.0);
            }
            """

    val vertexShader = gl.createShader(VERTEX_SHADER) // Create the actual vertex shader
    gl.shaderSource(vertexShader, vertexShaderCode)
    gl.compileShader(vertexShader)

    val fragmentShader = gl.createShader(FRAGMENT_SHADER)
    gl.shaderSource(fragmentShader, fragmentShaderCode)
    gl.compileShader(fragmentShader)

    val shaderProgram = gl.createProgram() // GL needs a shader program in order to display something
    gl.attachShader(shaderProgram, vertexShader)
    gl.attachShader(shaderProgram, fragmentShader)
    gl.linkProgram(shaderProgram)

    gl.useProgram(shaderProgram)

    gl.clearColor(0.9f, 0.9f, 0.9f, 1.0f)
    gl.clear(COLOR_BUFFER_BIT)

    val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices") // position is the name of the attribute in the vertex shader code
    gl.vertexAttribPointer(verticesAttribute, 3, FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(verticesAttribute)

    gl.drawArrays(TRIANGLES, 0, 3)

}


