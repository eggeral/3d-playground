package shadercode

enum class ShaderCode(val value: String) {
    VERTEX_MULTI_COLOR_CUBE(
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
    ),

    FRAGMENT_MULTI_COLOR_CUBE(
            """
            precision mediump float;

            uniform bool uPicking;
            uniform vec4 uMaterialDiffuse;

            varying vec4 vColor;

            void main(void) {
                if (uPicking) {
                    gl_FragColor = uMaterialDiffuse;
                } else {
                    gl_FragColor = vColor;
                }
            }
            """
    )
}