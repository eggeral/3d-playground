package threed

import kotlin.js.Math
import kotlin.js.Math.PI
import kotlin.js.Math.cos
import kotlin.js.Math.sin

operator fun Array<Float>.times(b: Array<Float>): Array<Float> {

    val result = arrayOf<Float>()

    val a00 = this[0]
    val a01 = this[1]
    val a02 = this[2]
    val a03 = this[3]
    val a10 = this[4]
    val a11 = this[5]
    val a12 = this[6]
    val a13 = this[7]
    val a20 = this[8]
    val a21 = this[9]
    val a22 = this[10]
    val a23 = this[11]
    val a30 = this[12]
    val a31 = this[13]
    val a32 = this[14]
    val a33 = this[15]

    // Cache only the current line of the second matrix
    var b0 = b[0]
    var b1 = b[1]
    var b2 = b[2]
    var b3 = b[3]

    result[0] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    result[1] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    result[2] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    result[3] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33

    b0 = b[4]
    b1 = b[5]
    b2 = b[6]
    b3 = b[7]

    result[4] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    result[5] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    result[6] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    result[7] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33

    b0 = b[8]
    b1 = b[9]
    b2 = b[10]
    b3 = b[11]

    result[8] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    result[9] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    result[10] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    result[11] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33

    b0 = b[12]
    b1 = b[13]
    b2 = b[14]
    b3 = b[15]

    result[12] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
    result[13] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
    result[14] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
    result[15] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33

    return result
}


fun rotateXMatrix(rad: Float): Array<Float> {

    val sinValue = sin(rad.toDouble()).toFloat()
    val cosValue = cos(rad.toDouble()).toFloat()

    return arrayOf(
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, cosValue, -sinValue, 0.0f,
            0.0f, sinValue, cosValue, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    )
}


fun rotateYMatrix(rad: Float): Array<Float> {

    val sinValue = sin(rad.toDouble()).toFloat()
    val cosValue = cos(rad.toDouble()).toFloat()

    return arrayOf(
            cosValue, 0.0f, sinValue, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            -sinValue, 0.0f, cosValue, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    )
}


fun rotateZMatrix(rad: Float): Array<Float> {

    val sinValue = sin(rad.toDouble()).toFloat()
    val cosValue = cos(rad.toDouble()).toFloat()

    return arrayOf(
            cosValue, -sinValue, 0.0f, 0.0f,
            sinValue, cosValue, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    )
}

fun translateMatrix(x: Float, y: Float, z: Float): Array<Float> {
    return arrayOf(
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            x, y, z, 1.0f
    )
}

fun scaleMatrix(w: Float, h: Float, d: Float): Array<Float> {
    return arrayOf(
            w, 0.0f, 0.0f, 0.0f,
            0.0f, h, 0.0f, 0.0f,
            0.0f, 0.0f, d, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    )
}

fun perspectiveProjectionMatrix(fieldOfViewInRadians: Float, aspectRatio: Float, near: Float, far: Float): Array<Float> {

    val f = (1.0 / Math.tan(fieldOfViewInRadians / 2.0)).toFloat()
    val rangeInv = 1 / (near - far)

    return arrayOf(
            f / aspectRatio, 0.0f, 0.0f, 0.0f,
            0.0f, f, 0.0f, 0.0f,
            0.0f, 0.0f, (near + far) * rangeInv, -1.0f,
            0.0f, 0.0f, near * far * rangeInv * 2, 0.0f
    )

}

fun orthographicProjectionMatrix(left: Float, right: Float, bottom: Float, top: Float, near: Float, far: Float): Array<Float> {

    return arrayOf(
            2.0f / (right - left), 0.0f, 0.0f, 0.0f,
            0.0f, 2.0f / (top - bottom), 0.0f, 0.0f,
            0.0f, 0.0f, 2.0f / (near - far), 0.0f,
            (left + right) / (left - right), (bottom + top) / (bottom - top), (near + far) / (near - far), 1.0f
    )

}


val Double.asRad: Double get() = this * PI / 180.0
