package example
/*
import org.khronos.webgl.Float32Array
import org.khronos.webgl.WebGLRenderingContext
import spr5.scene.Coordinate
import spr5.scene.Rgba
import spr5.scene.createCube

fun drawSceneCubeVarying(gl: WebGLRenderingContext) {

    val leftTop = Coordinate(0.0f, 1.0f, 0.0f)
    val cubeSize = 1.0f
    val cubeColor = Rgba(1.0f, 0.0f, 0.0f, 1.0f)
    val cube = createCube(leftTop, cubeSize, cubeColor)
    val cubeColors = cube.getVaryingColors()

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

    val vertexShaderCode =
            """
                attribute vec3 vertices;
                attribute vec4 color;
                varying vec4 vColor;

                void main(void) {
                    gl_Position = vec4(vertices, 1.0);
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

    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, colorBuffer)
    val colors = gl.getAttribLocation(shaderProgram, "color")
    gl.vertexAttribPointer(colors, 4, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(colors)

    // Bind vertex buffer to shader program
    gl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexBuffer)
    val verticesAttribute = gl.getAttribLocation(shaderProgram, "vertices")
    gl.vertexAttribPointer(verticesAttribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0)
    gl.enableVertexAttribArray(verticesAttribute)

    // get uniform vColor agttribute from fragment shader
    val color = gl.getUniformLocation(shaderProgram, "vColor")

    // set background color
    gl.clearColor(0.9f, 0.9f, 0.9f, 1.0f)
    gl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT)

    gl.useProgram(shaderProgram)

    gl.drawArrays(WebGLRenderingContext.TRIANGLES, 0, 36)
}
        */