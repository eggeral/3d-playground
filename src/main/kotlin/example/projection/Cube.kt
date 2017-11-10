package example.projection

class Cube(

        val vertexShaderCode: String =
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
            """,

        val fragmentShaderCode: String =
        """
            precision mediump float;
            varying vec3 vColor;
            void main(void) {
                gl_FragColor = vec4(vColor, 1.);
            }
            """,

        val vertices: Array<Float> = arrayOf(
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
        ),

        val colors: Array<Float> = arrayOf(
                5.0f, 3.0f, 7.0f, 5.0f, 3.0f, 7.0f, 5.0f, 3.0f, 7.0f, 5.0f, 3.0f, 7.0f,
                1.0f, 1.0f, 3.0f, 1.0f, 1.0f, 3.0f, 1.0f, 1.0f, 3.0f, 1.0f, 1.0f, 3.0f,
                0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f
        ),

        val indices: Array<Short> = arrayOf<Short>(
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
)