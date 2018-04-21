package spr5.util

import spr5.matrix.Vec3

class Triangle(val a: Vec3, val b: Vec3, val c: Vec3) {
    val normal: Vec3;

    init {
        val v1 = a - b;
        val v2 = a - c;
        normal = v1.cross(v2).normalize();
    }

    override fun toString(): String {
        return "Triangle($a, $b, $c)"
    }
}