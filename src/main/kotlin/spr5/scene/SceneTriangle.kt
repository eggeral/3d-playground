package spr5.scene

import spr5.matrix.Vec3
import spr5.util.Ray
import spr5.util.Triangle
import spr5.util.assert


class SceneTriangle(var vertices: Array<Coordinate>, override var color: Rgba) : WebGLDrawable {
    private val _vertices: Array<Float>
    private val _color: Array<Float>
    private val _indices: Array<Short>

    init {
        assert(vertices.size == 3, "SceneTriangle expects 3 coordinates")

        _vertices = arrayOf(
                vertices[0].x, vertices[0].y, vertices[0].z,
                vertices[1].x, vertices[1].y, vertices[1].z,
                vertices[2].x, vertices[2].y, vertices[2].z
        )

        _color = arrayOf(
                color.red, color.green, color.blue, color.alpha
        )

        _indices = arrayOf(0,1,2)
    }

    override fun getColors(): Array<Float> {
        return _color
    }

    override fun getVertices(): Array<Float> {
        return _vertices
    }

    override fun getIndices(): Array<Short> {
        return _indices
    }

    override fun getMesh(): Array<Triangle> {
        return arrayOf(Triangle(
                Vec3(vertices[0].x.toDouble(), vertices[0].y.toDouble(), vertices[0].z.toDouble()),
                Vec3(vertices[1].x.toDouble(), vertices[1].y.toDouble(), vertices[1].z.toDouble()),
                Vec3(vertices[2].x.toDouble(), vertices[2].y.toDouble(), vertices[2].z.toDouble())
        ));
    }

    override fun getNormals(): Array<Vec3> {
        return getMesh().map { tri -> tri.normal }.toTypedArray();
    }

    override fun intersect(ray: Ray): Triangle? {
        return getMesh().firstOrNull { tri -> ray.intersectTriangle(tri) != null }
    }

}