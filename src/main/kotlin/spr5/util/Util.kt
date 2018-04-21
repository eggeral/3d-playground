package spr5.util;

import spr5.matrix.Vec3
import spr5.scene.SceneObject
import kotlin.js.Math;

const val EPS = 0.00001;

fun assert(stmt: Boolean, message: String = "Assertion failed") {
    if (!stmt)
        throw AssertionError(message);
}

fun assertEquals(expected: Double, actual: Double, message: String = "Values are not equal") {
    assert(Math.abs(expected - actual) <= EPS, message);
}

fun<T> assertEquals(expected: T, actual: T, message: String = "Values are not equal") {
    assert(expected == actual, message);
}

fun<T> assertNull(v: T, message: String = "Value is not null") {
    assert(v == null, message);
}

fun<T> assertNotNull(v: T, message: String = "Value is null") {
    assert(v != null, message);
}

fun assertTrue(stmt: Boolean, message: String = "Value is not true") {
    assert(stmt, message);
}

fun assertFalse(stmt: Boolean, message: String = "Value is not false") {
    assert(!stmt, message);
}

fun getMesh(o: SceneObject): Array<Triangle> {
    val vertices = o.getVertices();
    var points = arrayOf<Vec3>();

    for (i in 0 until vertices.size step 3) {
        points += Vec3(vertices[i].toDouble(),
                       vertices[i + 1].toDouble(),
                       vertices[i + 2].toDouble());
    }

    val indices = o.getIndices();
    var faces = arrayOf<Triangle>();

    for (i in 0 until indices.size step 3) {
        faces += Triangle(points[indices[i].toInt()],
                          points[indices[i + 1].toInt()],
                          points[indices[i + 2].toInt()]);
    }

    return faces;
}