package spr5.util

import spr5.matrix.Vec3
import spr5.scene.SceneTriangle

class Ray(val origin: Vec3, val direction: Vec3) {
    override fun toString(): String {
        return "Ray [origin: ${origin.toString()}, direction: ${direction.toString()}]";
    }

    /**
     * Get a Vec3 that is a given distance along this Ray.
     * @param {Double} the distance along the Ray to retrieve a position for.
     */
    fun at(t: Double): Vec3 {
        return direction.scaleAndAdd(origin, t);
    }

    /**
     * Intersect this Ray with a triangle, returning the intersection point or null if there is no intersection.
     *
     * https://github.com/mrdoob/three.js/blob/r91/src/math/Ray.js#L445
     */
    fun intersectTriangle(triangle: Triangle): Vec3? {
        val a = triangle.a;
        val b = triangle.b;
        val c = triangle.c;

        val edge1: Vec3 = b - a;
        val edge2: Vec3 = c - a;

        val normal = triangle.normal;

        var DdN = this.direction.dot(normal);
        var sign = 1;

        if (DdN > 0) {
            sign = 1;
        } else if (DdN < 0) {
            sign = -1;
            DdN = -DdN;
        } else {
            return null;
        }

        val diff: Vec3 = origin - a;

        var DdQxE2 = sign * direction.dot(diff.cross(edge2));

        if (DdQxE2 < 0) {
            return null;
        }

        var DdE1xQ = sign * direction.dot(edge1.cross(diff));

        if (DdE1xQ < 0) {
            return null;
        }

        if (DdQxE2 + DdE1xQ > DdN) {
            return null;
        }

        var QdN = -sign * diff.dot(normal)

        if (QdN < 0) {
            return null;
        }

        return this.at(QdN / DdN);
    }
}