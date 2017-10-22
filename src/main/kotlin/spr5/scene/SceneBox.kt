package spr5.scene;

import spr5.util.assert
/*
class SceneBox(var faces: Array<SceneRectangle>, override var color: Rgba) : WebGLDrawable {
    private val _vertices: Array<Float>
    init {
        assert(faces.size == 6, "faces.size == 6")

        _vertices = faces[0].getVertices() + faces[1].getVertices() + faces[2].getVertices() + faces[3].getVertices() + faces[4].getVertices() + faces[5].getVertices()
    }

    override fun getVertices(): Array<Float> {
        return _vertices
    }

    override fun getColors(): Array<Float> {
        return faces.fold(emptyArray()) {
            colors, face -> colors + face.getColors()
        }
    }

    fun getUniformColors(): Array<Float> {
        return arrayOf(
                color.red, color.green, color.blue, color.alpha)
    }

    fun getVaryingColors(): Array<Float> {
        return faces.fold(emptyArray()) {
            colors, face -> colors + face.getColors()+ face.getColors()+ face.getColors()
        }
    }

    override fun toString(): String {
        return "SceneBox[faces = ${faces.size}]";
    }
}

fun createCube(leftTop: Coordinate, size: Float, color: Rgba) : SceneBox{
    val coordinates: Array<Coordinate> = arrayOf(
            leftTop,  // back left top
            Coordinate(leftTop.x, leftTop.y, leftTop.z + size), // front left top
            Coordinate(leftTop.x + size, leftTop.y, leftTop.z), // back right top
            Coordinate(leftTop.x, leftTop.y - size, leftTop.z)  // back left bottom
    )

    val faces = arrayOf(
            createSquare(Direction.Horizontal, coordinates[0], size, color),    // back side
            createSquare(Direction.Vertical, coordinates[0], size, color),      // top side
            createSquare(Direction.Sideways, coordinates[0], size, color),      // left side
            createSquare(Direction.Vertical, coordinates[1], size, color),      // front side
            createSquare(Direction.Sideways, coordinates[2], size, color),      // right side
            createSquare(Direction.Horizontal, coordinates[3], size, color)     // bottom side
    );

    return SceneBox(faces,color)
}

fun createCube(leftTop: Coordinate, size: Float, colors: Array<Rgba>) : SceneBox{
    val coordinates: Array<Coordinate> = arrayOf(
            leftTop,  // back left top
            Coordinate(leftTop.x, leftTop.y, leftTop.z + size), // front left top
            Coordinate(leftTop.x + size, leftTop.y, leftTop.z), // back right top
            Coordinate(leftTop.x, leftTop.y - size, leftTop.z)  // back left bottom
    )

    val faces = arrayOf(
            createSquare(Direction.Horizontal, coordinates[0], size, colors[0]),    // back side
            createSquare(Direction.Vertical, coordinates[0], size, colors[1]),      // top side
            createSquare(Direction.Sideways, coordinates[0], size, colors[2]),      // left side
            createSquare(Direction.Vertical, coordinates[1], size, colors[3]),      // front side
            createSquare(Direction.Sideways, coordinates[2], size, colors[4]),      // right side
            createSquare(Direction.Horizontal, coordinates[3], size, colors[5])     // bottom side
    );

    return SceneBox(faces,colors[0])
}
        */