package spr5.scene;

import spr5.matrix.Vec3
import spr5.util.Ray
import spr5.util.Triangle
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

    override fun getMesh(): Array<Triangle> {
        return spr5.util.getMesh(this);
    }

    override fun getNormals(): Array<Vec3> {
        return getMesh().map { t -> t.normal }.toTypedArray();
    }

    override fun intersect(ray: Ray): Triangle? {
        return getMesh().firstOrNull { tri -> ray.intersectTriangle(tri) != null };
    }
}

fun createSquare(center: Coordinate, size: Float, color: Rgba) :SceneRectangle {
    return SceneRectangle(center, size, size, color)
}
