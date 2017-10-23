package spr5.scene;

import spr5.util.assert

class SceneRectangle(var faces: Array<SceneTriangle>, override var color: Rgba) : WebGLDrawable {
    init {
        assert(faces.size == 2, "faces.size == 2");
    }

    override fun getVertices(): Array<Float> {
        return faces.fold(emptyArray()) {
            vertices, face -> vertices + face.getVertices()
        };
    }

    override fun getColors(): Array<Float> {
        return faces.fold(emptyArray()) {
            colors, face -> colors + face.getColors()
        }
    }
}

fun createSquare(direction: Direction, leftTop: Coordinate, size: Float, color: Rgba): SceneRectangle {
    // First build a list of coordinates for this square depending on square orientation
    // Then use the coordinates to make two triangles
    // Coordinate list:
    //    left top, right top
    //    left bottom, right bottom
    val coordinates: Array<Coordinate>;

    // a square oriented in X-Z plane
    if (direction == Direction.Horizontal) {
        coordinates = arrayOf(
                leftTop,
                Coordinate(leftTop.x + size, leftTop.y, leftTop.z), // right top
                Coordinate(leftTop.x, leftTop.y, leftTop.z + size),    // left bottom
                Coordinate(leftTop.x + size, leftTop.y, leftTop.z + size) // right bottom
        );
    // a square oriented in X-Y plane
    } else if (direction == Direction.Vertical) {
        coordinates = arrayOf(
                leftTop,
                Coordinate(leftTop.x + size, leftTop.y, leftTop.z),
                Coordinate(leftTop.x, leftTop.y - size, leftTop.z),
                Coordinate(leftTop.x + size, leftTop.y - size, leftTop.z)
        );
    } else {
        throw IllegalArgumentException("Unsupported direction: " + direction);
    }

    val faces = arrayOf(
            SceneTriangle(arrayOf(coordinates[0], coordinates[1], coordinates[2]), color),
            SceneTriangle(arrayOf(coordinates[1], coordinates[2], coordinates[3]), color)
    );

    return SceneRectangle(faces, color);
}