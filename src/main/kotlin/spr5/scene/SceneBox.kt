package spr5.scene;

import spr5.util.assert

class SceneBlock(var center: Coordinate, var width: Float, var heigth: Float, var depth: Float, override var colors: Array<Rgba>) : WebGLDrawableMulticolored {
    init {
        assert(width > 0, "Width must be greater than 0!")
        assert(heigth > 0, "Height must be greater than 0!")
        assert(depth > 0, "Depth must be greater than 0!")
        assert(colors.size == 6, "You have to specify six colors!")
    }

    override fun getVertices(): Array<Float> {
        return arrayOf(
                center.x-width/2, center.y-heigth/2, center.z-depth/2           //left-bottom-back
                ,center.x+width/2, center.y-heigth/2, center.z-depth/2          //right-bottom-back
                ,center.x+width/2, center.y+heigth/2, center.z-depth/2          //right-top-back

                ,center.x-width/2, center.y+heigth/2, center.z-depth/2          //left-top-back
                ,center.x-width/2, center.y-heigth/2, center.z+depth/2          //left-bottom-front
                ,center.x+width/2, center.y-heigth/2, center.z+depth/2          //right-bottom-front

                ,center.x+width/2, center.y+heigth/2, center.z+depth/2          //right-top-front
                ,center.x-width/2, center.y+heigth/2, center.z+depth/2          //left-top-front
                ,center.x-width/2, center.y-heigth/2, center.z-depth/2          //left-bottom-back

                ,center.x-width/2, center.y+heigth/2, center.z-depth/2          //left-top-back
                ,center.x-width/2, center.y+heigth/2, center.z+depth/2          //left-top-front
                ,center.x-width/2, center.y-heigth/2, center.z+depth/2          //left-bottom-front

                ,center.x+width/2, center.y-heigth/2, center.z-depth/2          //right-bottom-back
                ,center.x+width/2, center.y+heigth/2, center.z-depth/2          //right-top-back
                ,center.x+width/2, center.y+heigth/2, center.z+depth/2          //right-top-front

                ,center.x+width/2, center.y-heigth/2, center.z+depth/2          //right-bottom-front
                ,center.x-width/2, center.y-heigth/2, center.z-depth/2          //left-bottom-back
                ,center.x-width/2, center.y-heigth/2, center.z+depth/2          //left-bottom-front

                ,center.x+width/2, center.y-heigth/2, center.z+depth/2          //right-bottom-front
                ,center.x+width/2, center.y-heigth/2, center.z-depth/2          //right-bottom-back
                ,center.x-width/2, center.y+heigth/2, center.z-depth/2          //left-top-back

                ,center.x-width/2, center.y+heigth/2, center.z+depth/2          //left-top-front
                ,center.x+width/2, center.y+heigth/2, center.z+depth/2          //right-top-front
                ,center.x+width/2, center.y+heigth/2, center.z-depth/2          //right-top-back
        )
    }

    override fun getColors(): Array<Float> {
        return arrayOf(
                colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                ,colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                ,colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                ,colors[0].red, colors[0].green, colors[0].blue, colors[0].alpha
                ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                ,colors[1].red, colors[1].green, colors[1].blue, colors[1].alpha
                ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                ,colors[2].red, colors[2].green, colors[2].blue, colors[2].alpha
                ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                ,colors[3].red, colors[3].green, colors[3].blue, colors[3].alpha
                ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                ,colors[4].red, colors[4].green, colors[4].blue, colors[4].alpha
                ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha
                ,colors[5].red, colors[5].green, colors[5].blue, colors[5].alpha)
    }

    override fun getIndices(): Array<Short> {
        return arrayOf( 0, 1, 2,
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
                        20, 22, 23)
    }
}

fun createCube(center: Coordinate, size: Float, color: Rgba) :SceneBlock {
    return SceneBlock(center, size, size, size, arrayOf(color,color,color,color))
}

fun createMulticolorCube(center: Coordinate, size: Float, colors: Array<Rgba>) :SceneBlock {
    return SceneBlock(center, size, size, size, colors)
}

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