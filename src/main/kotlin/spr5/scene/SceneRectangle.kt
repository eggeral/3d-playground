package spr5.scene;

import spr5.util.assert

class SceneRectangle(var center: Coordinate, var width: Float, var heigth: Float, override var color: Rgba) : WebGLDrawable {
    init {
        assert(width > 0, "Width must be greater than 0!")
        assert(heigth > 0, "Height must be greater than 0!")
    }

    override fun getVertices(): Array<Float> {
        return arrayOf(center.x-width/2, center.y+heigth/2, center.z    //left-top
                ,center.x+width/2, center.y+heigth/2, center.z          //right-top
                ,center.x-width/2, center.y-heigth/2, center.z          //left-bottom
                ,center.x+width/2, center.y-heigth/2, center.z          //right-bottom
        )
    }

    override fun getColors(): Array<Float> {
        return arrayOf(
                color.red, color.green, color.blue, color.alpha
                ,color.red, color.green, color.blue, color.alpha
                ,color.red, color.green, color.blue, color.alpha
                ,color.red, color.green, color.blue, color.alpha)
    }

    override fun getIndices(): Array<Short> {
        return arrayOf(  0,1,2
                        ,2,1,3)
    }
}

fun createSquare(center: Coordinate, size: Float, color: Rgba) :SceneRectangle {
    return SceneRectangle(center, size, size, color)
}

/*
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

    override fun getIndices(): Array<Short> {
        throw NotImplementedError()
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
    } else if (direction == Direction.Sideways) {
        coordinates = arrayOf(
                leftTop,    // back top
                Coordinate(leftTop.x, leftTop.y - size, leftTop.z),     // back bottom
                Coordinate(leftTop.x, leftTop.y, leftTop.z + size),     // front top
                Coordinate(leftTop.x, leftTop.y - size, leftTop.z + size)   // front bottom
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
*/