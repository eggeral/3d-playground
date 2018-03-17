package spr5.util

import spr5.matrix.Vec3

class Ray(val origin: Vec3, val direction: Vec3) {
    override fun toString(): String {
        return "Ray [origin: ${origin.toString()}, direction: ${direction.toString()}]";
    }
}