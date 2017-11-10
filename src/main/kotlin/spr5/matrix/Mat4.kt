package spr5.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class Mat4() : glMatrix() {

    private val matrix: Array<Double> = arrayOf(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0)

    constructor(m00: Double, m01: Double, m02: Double, m03: Double, m10: Double, m11: Double, m12: Double, m13: Double, m20: Double, m21: Double, m22: Double, m23: Double, m30: Double, m31: Double, m32: Double, m33: Double) : this() {
        matrix[0] = m00
        matrix[1] = m01
        matrix[2] = m02
        matrix[3] = m03
        matrix[4] = m10
        matrix[5] = m11
        matrix[6] = m12
        matrix[7] = m13
        matrix[8] = m20
        matrix[9] = m21
        matrix[10] = m22
        matrix[11] = m23
        matrix[12] = m30
        matrix[13] = m31
        matrix[14] = m32
        matrix[15] = m33
    }

    operator fun get(index: Int): Double {
        return matrix[index]
    }

    operator fun set(index: Int, value: Double) {
        matrix[index] = value
    }


    /**
     * Adds two Mat4's
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} firstSummand the first operand
     * @param {Mat4} secondSummand the second operand
     * @returns {Mat4} inOut
     */
    fun add(summand: Mat4): Mat4 {
        this.matrix[0] += summand[0]
        this.matrix[1] += summand[1]
        this.matrix[2] += summand[2]
        this.matrix[3] += summand[3]
        this.matrix[4] += summand[4]
        this.matrix[5] += summand[5]
        this.matrix[6] += summand[6]
        this.matrix[7] += summand[7]
        this.matrix[8] += summand[8]
        this.matrix[9] += summand[9]
        this.matrix[10] += summand[10]
        this.matrix[11] += summand[11]
        this.matrix[12] += summand[12]
        this.matrix[13] += summand[13]
        this.matrix[14] += summand[14]
        this.matrix[15] += summand[15]
        return this
    }

    operator fun plus(summand: Mat4): Mat4 {
        return clone().add(summand);
    }

    /**
     * Subtracts matrix subtrahend from matrix minuend
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} minuend the first operand
     * @param {Mat4} subtrahend the second operand
     * @returns {Mat4} inOut
     */
    fun subtract(subtrahend: Mat4): Mat4 {
        this.matrix[0] -= subtrahend[0]
        this.matrix[1] -= subtrahend[1]
        this.matrix[2] -= subtrahend[2]
        this.matrix[3] -= subtrahend[3]
        this.matrix[4] -= subtrahend[4]
        this.matrix[5] -= subtrahend[5]
        this.matrix[6] -= subtrahend[6]
        this.matrix[7] -= subtrahend[7]
        this.matrix[8] -= subtrahend[8]
        this.matrix[9] -= subtrahend[9]
        this.matrix[10] -= subtrahend[10]
        this.matrix[11] -= subtrahend[11]
        this.matrix[12] -= subtrahend[12]
        this.matrix[13] -= subtrahend[13]
        this.matrix[14] -= subtrahend[14]
        this.matrix[15] -= subtrahend[15]
        return this
    }

    operator fun minus(subtrahend: Mat4): Mat4 {
        return clone().subtract(subtrahend);
    }


    /**
     * Multiplies two mat4s
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} multiplier the first operand
     * @param {Mat4} multiplicand the second operand
     * @returns {Mat4} inOut
     */
    fun multiply(multiplier: Mat4): Mat4 {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        val a30 = this.matrix[12]
        val a31 = this.matrix[13]
        val a32 = this.matrix[14]
        val a33 = this.matrix[15]
        // Cache only the current line of the second matrix
        var b0 = multiplier[0]
        var b1 = multiplier[1]
        var b2 = multiplier[2]
        var b3 = multiplier[3]
        this.matrix[0] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
        this.matrix[1] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
        this.matrix[2] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
        this.matrix[3] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
        b0 = multiplier[4]
        b1 = multiplier[5]
        b2 = multiplier[6]
        b3 = multiplier[7]
        this.matrix[4] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
        this.matrix[5] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
        this.matrix[6] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
        this.matrix[7] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
        b0 = multiplier[8]
        b1 = multiplier[9]
        b2 = multiplier[10]
        b3 = multiplier[11]
        this.matrix[8] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
        this.matrix[9] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
        this.matrix[10] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
        this.matrix[11] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
        b0 = multiplier[12]
        b1 = multiplier[13]
        b2 = multiplier[14]
        b3 = multiplier[15]
        this.matrix[12] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
        this.matrix[13] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
        this.matrix[14] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
        this.matrix[15] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
        return this
    }

    operator fun times(multiplier: Mat4): Mat4 {
        return multiplier.clone().multiply(this);
    }


    /**
     * Copy the values from one Mat4 to another
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} source the source matrix
     * @returns {Mat4} inOut
     */
    fun copy(source: Mat4): Mat4 {
        this.matrix[0] = source[0]
        this.matrix[1] = source[1]
        this.matrix[2] = source[2]
        this.matrix[3] = source[3]
        this.matrix[4] = source[4]
        this.matrix[5] = source[5]
        this.matrix[6] = source[6]
        this.matrix[7] = source[7]
        this.matrix[8] = source[8]
        this.matrix[9] = source[9]
        this.matrix[10] = source[10]
        this.matrix[11] = source[11]
        this.matrix[12] = source[12]
        this.matrix[13] = source[13]
        this.matrix[14] = source[14]
        this.matrix[15] = source[15]
        return this
    }

    /**
     * Creates matrixToClone new Mat4 initialized with values from an existing matrix
     *
     * @param {Mat4} matrixToClone matrix to clone
     * @returns {Mat4} matrixToClone new 4x4 matrix
     */
    fun clone(): Mat4 {
        return Mat4(this.matrix[0],
                this.matrix[1],
                this.matrix[2],
                this.matrix[3],
                this.matrix[4],
                this.matrix[5],
                this.matrix[6],
                this.matrix[7],
                this.matrix[8],
                this.matrix[9],
                this.matrix[10],
                this.matrix[11],
                this.matrix[12],
                this.matrix[13],
                this.matrix[14],
                this.matrix[15])
    }

    /**
     * Set a Mat4 to the identityDoubleArray matrix
     *
     * @param {Mat4} inOut the receiving matrix
     * @returns {Mat4} inOut
     */
    fun identity(): Mat4 {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = 1.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = 1.0
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Transpose the values of a Mat4
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} source the source matrix
     * @returns {Mat4} inOut
     */
    fun transpose(source: Mat4): Mat4 {
        // If we are transposing ourselves we can skip a few steps but have to cache some values
        if (this === source) {
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a12 = source[6]
            val a13 = source[7]
            val a23 = source[11]
            this.matrix[1] = source[4]
            this.matrix[2] = source[8]
            this.matrix[3] = source[12]
            this.matrix[4] = a01
            this.matrix[6] = source[9]
            this.matrix[7] = source[13]
            this.matrix[8] = a02
            this.matrix[9] = a12
            this.matrix[11] = source[14]
            this.matrix[12] = a03
            this.matrix[13] = a13
            this.matrix[14] = a23
        } else {
            this.matrix[0] = source[0]
            this.matrix[1] = source[4]
            this.matrix[2] = source[8]
            this.matrix[3] = source[12]
            this.matrix[4] = source[1]
            this.matrix[5] = source[5]
            this.matrix[6] = source[9]
            this.matrix[7] = source[13]
            this.matrix[8] = source[2]
            this.matrix[9] = source[6]
            this.matrix[10] = source[10]
            this.matrix[11] = source[14]
            this.matrix[12] = source[3]
            this.matrix[13] = source[7]
            this.matrix[14] = source[11]
            this.matrix[15] = source[15]
        }
        return this
    }

    /**
     * Calculates the adjugate of a Mat4
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} a the source matrix
     * @returns {Mat4} inOut
     */
    fun adjoint(): Mat4 {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        val a30 = this.matrix[12]
        val a31 = this.matrix[13]
        val a32 = this.matrix[14]
        val a33 = this.matrix[15]
        this.matrix[0] = (a11 * (a22 * a33 - a23 * a32) - a21 * (a12 * a33 - a13 * a32) + a31 * (a12 * a23 - a13 * a22))
        this.matrix[1] = -(a01 * (a22 * a33 - a23 * a32) - a21 * (a02 * a33 - a03 * a32) + a31 * (a02 * a23 - a03 * a22))
        this.matrix[2] = (a01 * (a12 * a33 - a13 * a32) - a11 * (a02 * a33 - a03 * a32) + a31 * (a02 * a13 - a03 * a12))
        this.matrix[3] = -(a01 * (a12 * a23 - a13 * a22) - a11 * (a02 * a23 - a03 * a22) + a21 * (a02 * a13 - a03 * a12))
        this.matrix[4] = -(a10 * (a22 * a33 - a23 * a32) - a20 * (a12 * a33 - a13 * a32) + a30 * (a12 * a23 - a13 * a22))
        this.matrix[5] = (a00 * (a22 * a33 - a23 * a32) - a20 * (a02 * a33 - a03 * a32) + a30 * (a02 * a23 - a03 * a22))
        this.matrix[6] = -(a00 * (a12 * a33 - a13 * a32) - a10 * (a02 * a33 - a03 * a32) + a30 * (a02 * a13 - a03 * a12))
        this.matrix[7] = (a00 * (a12 * a23 - a13 * a22) - a10 * (a02 * a23 - a03 * a22) + a20 * (a02 * a13 - a03 * a12))
        this.matrix[8] = (a10 * (a21 * a33 - a23 * a31) - a20 * (a11 * a33 - a13 * a31) + a30 * (a11 * a23 - a13 * a21))
        this.matrix[9] = -(a00 * (a21 * a33 - a23 * a31) - a20 * (a01 * a33 - a03 * a31) + a30 * (a01 * a23 - a03 * a21))
        this.matrix[10] = (a00 * (a11 * a33 - a13 * a31) - a10 * (a01 * a33 - a03 * a31) + a30 * (a01 * a13 - a03 * a11))
        this.matrix[11] = -(a00 * (a11 * a23 - a13 * a21) - a10 * (a01 * a23 - a03 * a21) + a20 * (a01 * a13 - a03 * a11))
        this.matrix[12] = -(a10 * (a21 * a32 - a22 * a31) - a20 * (a11 * a32 - a12 * a31) + a30 * (a11 * a22 - a12 * a21))
        this.matrix[13] = (a00 * (a21 * a32 - a22 * a31) - a20 * (a01 * a32 - a02 * a31) + a30 * (a01 * a22 - a02 * a21))
        this.matrix[14] = -(a00 * (a11 * a32 - a12 * a31) - a10 * (a01 * a32 - a02 * a31) + a30 * (a01 * a12 - a02 * a11))
        this.matrix[15] = (a00 * (a11 * a22 - a12 * a21) - a10 * (a01 * a22 - a02 * a21) + a20 * (a01 * a12 - a02 * a11))
        return this
    }

    /**
     * Calculates the determinant of source Mat4
     *
     * @param {Mat4} source the source matrix
     * @returns {Number} determinant of source
     */
    fun determinant(): Double {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        val a30 = this.matrix[12]
        val a31 = this.matrix[13]
        val a32 = this.matrix[14]
        val a33 = this.matrix[15]
        val b00 = a00 * a11 - a01 * a10
        val b01 = a00 * a12 - a02 * a10
        val b02 = a00 * a13 - a03 * a10
        val b03 = a01 * a12 - a02 * a11
        val b04 = a01 * a13 - a03 * a11
        val b05 = a02 * a13 - a03 * a12
        val b06 = a20 * a31 - a21 * a30
        val b07 = a20 * a32 - a22 * a30
        val b08 = a20 * a33 - a23 * a30
        val b09 = a21 * a32 - a22 * a31
        val b10 = a21 * a33 - a23 * a31
        val b11 = a22 * a33 - a23 * a32
        // Calculate the determinant
        return b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06
    }

    /**
     * Translate matrixToTranslate Mat4 by the given vector
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} matrixToTranslate the matrix to translate
     * @param {Vec3} vec3ToTranslateBy vector to translate by
     * @returns {Mat4} inOut
     */
    fun translate(vec3ToTranslateBy: Vec3): Mat4 {
        val x = vec3ToTranslateBy[0]
        val y = vec3ToTranslateBy[1]
        val z = vec3ToTranslateBy[2]
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        val a30 = this.matrix[12]
        val a31 = this.matrix[13]
        val a32 = this.matrix[14]
        val a33 = this.matrix[15]
        this.matrix[0] = a00
        this.matrix[1] = a01
        this.matrix[2] = a02
        this.matrix[3] = a03
        this.matrix[4] = a10
        this.matrix[5] = a11
        this.matrix[6] = a12
        this.matrix[7] = a13
        this.matrix[8] = a20
        this.matrix[9] = a21
        this.matrix[10] = a22
        this.matrix[11] = a23
        this.matrix[12] = a00 * x + a10 * y + a20 * z + a30
        this.matrix[13] = a01 * x + a11 * y + a21 * z + a31
        this.matrix[14] = a02 * x + a12 * y + a22 * z + a32
        this.matrix[15] = a03 * x + a13 * y + a23 * z + a33
        return this
    }

    fun translate(vec3ToTranslateBy: Array<Double>): Mat4 {
        val x = vec3ToTranslateBy[0]
        val y = vec3ToTranslateBy[1]
        val z = vec3ToTranslateBy[2]
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        val a30 = this.matrix[12]
        val a31 = this.matrix[13]
        val a32 = this.matrix[14]
        val a33 = this.matrix[15]
        this.matrix[0] = a00
        this.matrix[1] = a01
        this.matrix[2] = a02
        this.matrix[3] = a03
        this.matrix[4] = a10
        this.matrix[5] = a11
        this.matrix[6] = a12
        this.matrix[7] = a13
        this.matrix[8] = a20
        this.matrix[9] = a21
        this.matrix[10] = a22
        this.matrix[11] = a23
        this.matrix[12] = a00 * x + a10 * y + a20 * z + a30
        this.matrix[13] = a01 * x + a11 * y + a21 * z + a31
        this.matrix[14] = a02 * x + a12 * y + a22 * z + a32
        this.matrix[15] = a03 * x + a13 * y + a23 * z + a33
        return this
    }

    /**
     * Scales the Mat4 by the dimensions in the given Vec3 not using vectorization
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} matrixToScale the matrix to scale
     * @param {Vec3} vec3ToScaleBy the Vec3 to scale the matrix by
     * @returns {Mat4} inOut
     **/
    fun scale(vec3ToScaleBy: Vec3): Mat4 {
        val x = vec3ToScaleBy[0]
        val y = vec3ToScaleBy[1]
        val z = vec3ToScaleBy[2]
        this.matrix[0] *= x
        this.matrix[1] *= x
        this.matrix[2] *= x
        this.matrix[3] *= x
        this.matrix[4] *= y
        this.matrix[5] *= y
        this.matrix[6] *= y
        this.matrix[7] *= y
        this.matrix[8] *= z
        this.matrix[9] *= z
        this.matrix[10] *= z
        this.matrix[11] *= z
        return this
    }

    fun scale(vec3ToScaleBy: Array<Double>): Mat4 {
        val x = vec3ToScaleBy[0]
        val y = vec3ToScaleBy[1]
        val z = vec3ToScaleBy[2]
        this.matrix[0] *= x
        this.matrix[1] *= x
        this.matrix[2] *= x
        this.matrix[3] *= x
        this.matrix[4] *= y
        this.matrix[5] *= y
        this.matrix[6] *= y
        this.matrix[7] *= y
        this.matrix[8] *= z
        this.matrix[9] *= z
        this.matrix[10] *= z
        this.matrix[11] *= z
        return this
    }

    /**
     * Rotates matrixToRotate Mat4 by the given angle around the given axisToRotateAround
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} matrixToRotate the matrix to rotate
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @param {Vec3} axisToRotateAround the axisToRotateAround to rotate around
     * @returns {Mat4} inOut
     */
    fun rotate(angleInRad: Double, axisToRotateAround: Vec3): Mat4 {
        var x = axisToRotateAround[0]
        var y = axisToRotateAround[1]
        var z = axisToRotateAround[2]
        var len = Math.sqrt(x * x + y * y + z * z)

        if (Math.abs(len) < EPSILON) {
            return Mat4()
        }

        len = 1 / len
        x *= len
        y *= len
        z *= len
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        val t = 1 - c
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        // Construct the elements of the rotation matrix
        val b00 = x * x * t + c
        val b01 = y * x * t + z * s
        val b02 = z * x * t - y * s
        val b10 = x * y * t - z * s
        val b11 = y * y * t + c
        val b12 = z * y * t + x * s
        val b20 = x * z * t + y * s
        val b21 = y * z * t - x * s
        val b22 = z * z * t + c
        // Perform rotation-specific matrix multiplication
        this.matrix[0] = a00 * b00 + a10 * b01 + a20 * b02
        this.matrix[1] = a01 * b00 + a11 * b01 + a21 * b02
        this.matrix[2] = a02 * b00 + a12 * b01 + a22 * b02
        this.matrix[3] = a03 * b00 + a13 * b01 + a23 * b02
        this.matrix[4] = a00 * b10 + a10 * b11 + a20 * b12
        this.matrix[5] = a01 * b10 + a11 * b11 + a21 * b12
        this.matrix[6] = a02 * b10 + a12 * b11 + a22 * b12
        this.matrix[7] = a03 * b10 + a13 * b11 + a23 * b12
        this.matrix[8] = a00 * b20 + a10 * b21 + a20 * b22
        this.matrix[9] = a01 * b20 + a11 * b21 + a21 * b22
        this.matrix[10] = a02 * b20 + a12 * b21 + a22 * b22
        this.matrix[11] = a03 * b20 + a13 * b21 + a23 * b22
        return this
    }

    fun rotate(angleInRad: Double, axisToRotateAround: Array<Double>): Mat4 {
        var x = axisToRotateAround[0]
        var y = axisToRotateAround[1]
        var z = axisToRotateAround[2]
        var len = Math.sqrt(x * x + y * y + z * z)

        if (Math.abs(len) < EPSILON) {
            return Mat4()
        }

        len = 1 / len
        x *= len
        y *= len
        z *= len
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        val t = 1 - c
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        // Construct the elements of the rotation matrix
        val b00 = x * x * t + c
        val b01 = y * x * t + z * s
        val b02 = z * x * t - y * s
        val b10 = x * y * t - z * s
        val b11 = y * y * t + c
        val b12 = z * y * t + x * s
        val b20 = x * z * t + y * s
        val b21 = y * z * t - x * s
        val b22 = z * z * t + c
        // Perform rotation-specific matrix multiplication
        this.matrix[0] = a00 * b00 + a10 * b01 + a20 * b02
        this.matrix[1] = a01 * b00 + a11 * b01 + a21 * b02
        this.matrix[2] = a02 * b00 + a12 * b01 + a22 * b02
        this.matrix[3] = a03 * b00 + a13 * b01 + a23 * b02
        this.matrix[4] = a00 * b10 + a10 * b11 + a20 * b12
        this.matrix[5] = a01 * b10 + a11 * b11 + a21 * b12
        this.matrix[6] = a02 * b10 + a12 * b11 + a22 * b12
        this.matrix[7] = a03 * b10 + a13 * b11 + a23 * b12
        this.matrix[8] = a00 * b20 + a10 * b21 + a20 * b22
        this.matrix[9] = a01 * b20 + a11 * b21 + a21 * b22
        this.matrix[10] = a02 * b20 + a12 * b21 + a22 * b22
        this.matrix[11] = a03 * b20 + a13 * b21 + a23 * b22
        return this
    }

    /**
     * Rotates matrixToRotate matrix by the given angle around the X axis
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} matrixToRotate the matrix to rotate
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat4} inOut
     */
    fun rotateX(angleInRad: Double): Mat4 {
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        // Perform axis-specific matrix multiplication
        this.matrix[4] = a10 * c + a20 * s
        this.matrix[5] = a11 * c + a21 * s
        this.matrix[6] = a12 * c + a22 * s
        this.matrix[7] = a13 * c + a23 * s
        this.matrix[8] = a20 * c - a10 * s
        this.matrix[9] = a21 * c - a11 * s
        this.matrix[10] = a22 * c - a12 * s
        this.matrix[11] = a23 * c - a13 * s
        return this
    }

    /**
     * Rotates matrixToRotate matrix by the given angle around the Y axis
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} matrixToRotate the matrix to rotate
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat4} inOut
     */
    fun rotateY(angleInRad: Double): Mat4 {
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a20 = this.matrix[8]
        val a21 = this.matrix[9]
        val a22 = this.matrix[10]
        val a23 = this.matrix[11]
        // Perform axis-specific matrix multiplication
        this.matrix[0] = a00 * c - a20 * s
        this.matrix[1] = a01 * c - a21 * s
        this.matrix[2] = a02 * c - a22 * s
        this.matrix[3] = a03 * c - a23 * s
        this.matrix[8] = a00 * s + a20 * c
        this.matrix[9] = a01 * s + a21 * c
        this.matrix[10] = a02 * s + a22 * c
        this.matrix[11] = a03 * s + a23 * c
        return this
    }

    /**
     * Rotates matrixToRotate matrix by the given angle around the Z axis
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} matrixToRotate the matrix to rotate
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat4} inOut
     */
    fun rotateZ(angleInRad: Double): Mat4 {
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a03 = this.matrix[3]
        val a10 = this.matrix[4]
        val a11 = this.matrix[5]
        val a12 = this.matrix[6]
        val a13 = this.matrix[7]
        // Perform axis-specific matrix multiplication
        this.matrix[0] = a00 * c + a10 * s
        this.matrix[1] = a01 * c + a11 * s
        this.matrix[2] = a02 * c + a12 * s
        this.matrix[3] = a03 * c + a13 * s
        this.matrix[4] = a10 * c - a00 * s
        this.matrix[5] = a11 * c - a01 * s
        this.matrix[6] = a12 * c - a02 * s
        this.matrix[7] = a13 * c - a03 * s
        return this
    }

    /**
     * Creates a matrix from a vector translation
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.translate(dest, dest, vec);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {Vec3} translationVec3 Translation vector
     * @returns {Mat4} inOut
     */
    fun fromTranslation(translationVec3: Vec3): Mat4 {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = 1.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = 1.0
        this.matrix[11] = 0.0
        this.matrix[12] = translationVec3[0]
        this.matrix[13] = translationVec3[1]
        this.matrix[14] = translationVec3[2]
        this.matrix[15] = 1.0
        return this
    }

    fun fromTranslation(translationVec3: Array<Double>): Mat4 {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = 1.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = 1.0
        this.matrix[11] = 0.0
        this.matrix[12] = translationVec3[0]
        this.matrix[13] = translationVec3[1]
        this.matrix[14] = translationVec3[2]
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Creates a matrix from a vector scaling
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.scale(dest, dest, vec);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {Vec3} scalingVec3 Scaling vector
     * @returns {Mat4} inOut
     */
    fun fromScaling(scalingVec3: Vec3): Mat4 {
        this.matrix[0] = scalingVec3[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = scalingVec3[1]
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = scalingVec3[2]
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    fun fromScaling(scalingVec3: Array<Double>): Mat4 {
        this.matrix[0] = scalingVec3[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = scalingVec3[1]
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = scalingVec3[2]
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }


    /**
     * Creates a matrix from a given angle around a given axisToRotateAround
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.rotate(dest, dest, angleToRotateByInRad, axisToRotateAround);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
     * @param {Vec3} axisToRotateAround the axisToRotateAround to rotate around
     * @returns {Mat4} inOut
     */
    fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Vec3): Mat4 {
        var x = axisToRotateAround[0]
        var y = axisToRotateAround[1]
        var z = axisToRotateAround[2]
        var len = Math.sqrt(x * x + y * y + z * z)
        if (Math.abs(len) < EPSILON) {
            return Mat4()
        }
        len = 1 / len
        x *= len
        y *= len
        z *= len
        val s = Math.sin(angleToRotateByInRad)
        val c = Math.cos(angleToRotateByInRad)
        val t = 1 - c
        // Perform rotation-specific matrix multiplication
        this.matrix[0] = x * x * t + c
        this.matrix[1] = y * x * t + z * s
        this.matrix[2] = z * x * t - y * s
        this.matrix[3] = 0.0
        this.matrix[4] = x * y * t - z * s
        this.matrix[5] = y * y * t + c
        this.matrix[6] = z * y * t + x * s
        this.matrix[7] = 0.0
        this.matrix[8] = x * z * t + y * s
        this.matrix[9] = y * z * t - x * s
        this.matrix[10] = z * z * t + c
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Array<Double>): Mat4 {
        var x = axisToRotateAround[0]
        var y = axisToRotateAround[1]
        var z = axisToRotateAround[2]
        var len = Math.sqrt(x * x + y * y + z * z)
        if (Math.abs(len) < EPSILON) {
            return Mat4()
        }
        len = 1 / len
        x *= len
        y *= len
        z *= len
        val s = Math.sin(angleToRotateByInRad)
        val c = Math.cos(angleToRotateByInRad)
        val t = 1 - c
        // Perform rotation-specific matrix multiplication
        this.matrix[0] = x * x * t + c
        this.matrix[1] = y * x * t + z * s
        this.matrix[2] = z * x * t - y * s
        this.matrix[3] = 0.0
        this.matrix[4] = x * y * t - z * s
        this.matrix[5] = y * y * t + c
        this.matrix[6] = z * y * t + x * s
        this.matrix[7] = 0.0
        this.matrix[8] = x * z * t + y * s
        this.matrix[9] = y * z * t - x * s
        this.matrix[10] = z * z * t + c
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Creates a matrix from the given angle around the X axis
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.rotateX(dest, dest, angleInRad);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat4} inOut
     */
    fun fromXRotation(angleInRad: Double): Mat4 {
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        // Perform axis-specific matrix multiplication
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = c
        this.matrix[6] = s
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = -s
        this.matrix[10] = c
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Creates a matrix from the given angle around the Y axis
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.rotateY(dest, dest, angleInRad);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat4} inOut
     */
    fun fromYRotation(angleInRad: Double): Mat4 {
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        // Perform axis-specific matrix multiplication
        this.matrix[0] = c
        this.matrix[1] = 0.0
        this.matrix[2] = -s
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = 1.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = s
        this.matrix[9] = 0.0
        this.matrix[10] = c
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Creates a matrix from the given angle around the Z axis
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.rotateZ(dest, dest, angleInRad);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat4} inOut
     */
    fun fromZRotation(angleInRad: Double): Mat4 {
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        // Perform axis-specific matrix multiplication
        this.matrix[0] = c
        this.matrix[1] = s
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = -s
        this.matrix[5] = c
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = 1.0
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Creates a matrix from a quaternion rotation and vector translation
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.translate(dest, vec);
     *     let quatMat = Mat4.createDoubleArray();
     *     quat4.toMat4(Quat, quatMat);
     *     Mat4.multiply(dest, quatMat);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {quat4} rotationQuat Rotation quaternion
     * @param {Vec3} translationVec3 Translation vector
     * @returns {Mat4} inOut
     */
    fun fromRotationTranslation(rotationQuat: Quat, translationVec3: Vec3): Mat4 {
        // Quaternion math
        val x = rotationQuat[0]
        val y = rotationQuat[1]
        val z = rotationQuat[2]
        val w = rotationQuat[3]
        val x2 = x + x
        val y2 = y + y
        val z2 = z + z
        val xx = x * x2
        val xy = x * y2
        val xz = x * z2
        val yy = y * y2
        val yz = y * z2
        val zz = z * z2
        val wx = w * x2
        val wy = w * y2
        val wz = w * z2
        this.matrix[0] = 1 - (yy + zz)
        this.matrix[1] = xy + wz
        this.matrix[2] = xz - wy
        this.matrix[3] = 0.0
        this.matrix[4] = xy - wz
        this.matrix[5] = 1 - (xx + zz)
        this.matrix[6] = yz + wx
        this.matrix[7] = 0.0
        this.matrix[8] = xz + wy
        this.matrix[9] = yz - wx
        this.matrix[10] = 1 - (xx + yy)
        this.matrix[11] = 0.0
        this.matrix[12] = translationVec3[0]
        this.matrix[13] = translationVec3[1]
        this.matrix[14] = translationVec3[2]
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Returns the translation vector component of a transformation
     *  matrix. If a matrix is built with fromRotationTranslation,
     *  the returned vector will be the same as the translation vector
     *  originally supplied.
     * @param  {Vec3} inOut Vector to receive translation component
     * @param  {Mat4} matrixToBeDecomposed Matrix to be decomposed (input)
     * @return {Vec3} inOut
     */
    fun getTranslation(): Vec3 {
        return Vec3(this.matrix[12], this.matrix[13], this.matrix[14])
    }

    /**
     * Returns the scaling factor component of a transformation
     *  matrix. If a matrix is built with fromRotationTranslationScale
     *  with a normalized Quaternion paramter, the returned vector will be
     *  the same as the scaling vector
     *  originally supplied.
     * @param  {Vec3} inOut Vector to receive scaling factor component
     * @param  {Mat4} matrixToBeDecomposed Matrix to be decomposed (input)
     * @return {Vec3} inOut
     */
    fun getScaling(): Vec3 {
        val m11 = this.matrix[0]
        val m12 = this.matrix[1]
        val m13 = this.matrix[2]
        val m21 = this.matrix[4]
        val m22 = this.matrix[5]
        val m23 = this.matrix[6]
        val m31 = this.matrix[8]
        val m32 = this.matrix[9]
        val m33 = this.matrix[10]
        return Vec3(Math.sqrt(m11 * m11 + m12 * m12 + m13 * m13),
                Math.sqrt(m21 * m21 + m22 * m22 + m23 * m23),
                Math.sqrt(m31 * m31 + m32 * m32 + m33 * m33))
    }

    /**
     * Returns a quaternion representing the rotational component
     *  of a transformation matrix. If a matrix is built with
     *  fromRotationTranslation, the returned quaternion will be the
     *  same as the quaternion originally supplied.
     * @param {Quat} inOut Quaternion to receive the rotation component
     * @param {Mat4} matrixToBeDecomposed Matrix to be decomposed (input)
     * @return {Quat} inOut
     */
    fun getRotation(): Quat {
        // Algorithm taken from http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToQuaternion/index.htm
        val trace = this.matrix[0] + this.matrix[5] + this.matrix[10]
        val s: Double
        if (trace > 0) {
            s = Math.sqrt(trace + 1.0) * 2
            return Quat(
                    (this.matrix[6] - this.matrix[9]) / s,
                    (this.matrix[8] - this.matrix[2]) / s,
                    (this.matrix[1] - this.matrix[4]) / s,
                    0.25 * s)
        } else if ((this.matrix[0] > this.matrix[5]) && (this.matrix[0] > this.matrix[10])) {
            s = Math.sqrt(1.0 + this.matrix[0] - this.matrix[5] - this.matrix[10]) * 2
            return Quat(
                    0.25 * s,
                    (this.matrix[1] + this.matrix[4]) / s,
                    (this.matrix[8] + this.matrix[2]) / s,
                    (this.matrix[6] - this.matrix[9]) / s)
        } else if (this.matrix[5] > this.matrix[10]) {
            s = Math.sqrt(1.0 + this.matrix[5] - this.matrix[0] - this.matrix[10]) * 2
            return Quat(
                    (this.matrix[1] + this.matrix[4]) / s,
                    0.25 * s,
                    (this.matrix[6] + this.matrix[9]) / s,
                    (this.matrix[8] - this.matrix[2]) / s)
        } else {
            s = Math.sqrt(1.0 + this.matrix[10] - this.matrix[0] - this.matrix[5]) * 2
            return Quat(
                    (this.matrix[8] + this.matrix[2]) / s,
                    (this.matrix[6] + this.matrix[9]) / s,
                    0.25 * s,
                    (this.matrix[1] - this.matrix[4]) / s)
        }
    }

    /**
     * Creates a matrix from a quaternion rotation, vector translation and vector scale
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.translate(dest, vec);
     *     let quatMat = Mat4.createDoubleArray();
     *     quat4.toMat4(Quat, quatMat);
     *     Mat4.multiply(dest, quatMat);
     *     Mat4.scale(dest, scale)
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {quat4} rotationQuat Rotation quaternion
     * @param {Vec3} translationVec3 Translation vector
     * @param {Vec3} scalingVec3 Scaling vector
     * @returns {Mat4} inOut
     */
    fun fromRotationTranslationScale(rotationQuat: Quat, translationVec3: Vec3, scalingVec3: Vec3): Mat4 {
        // Quaternion math
        val x = rotationQuat[0]
        val y = rotationQuat[1]
        val z = rotationQuat[2]
        val w = rotationQuat[3]
        val x2 = x + x
        val y2 = y + y
        val z2 = z + z
        val xx = x * x2
        val xy = x * y2
        val xz = x * z2
        val yy = y * y2
        val yz = y * z2
        val zz = z * z2
        val wx = w * x2
        val wy = w * y2
        val wz = w * z2
        val sx = scalingVec3[0]
        val sy = scalingVec3[1]
        val sz = scalingVec3[2]
        this.matrix[0] = (1 - (yy + zz)) * sx
        this.matrix[1] = (xy + wz) * sx
        this.matrix[2] = (xz - wy) * sx
        this.matrix[3] = 0.0
        this.matrix[4] = (xy - wz) * sy
        this.matrix[5] = (1 - (xx + zz)) * sy
        this.matrix[6] = (yz + wx) * sy
        this.matrix[7] = 0.0
        this.matrix[8] = (xz + wy) * sz
        this.matrix[9] = (yz - wx) * sz
        this.matrix[10] = (1 - (xx + yy)) * sz
        this.matrix[11] = 0.0
        this.matrix[12] = translationVec3[0]
        this.matrix[13] = translationVec3[1]
        this.matrix[14] = translationVec3[2]
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Creates a matrix from a quaternion rotation, vector translation and vector scale, rotating and scaling around the given origin
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat4.identityDoubleArray(dest);
     *     Mat4.translate(dest, vec);
     *     Mat4.translate(dest, origin);
     *     let quatMat = Mat4.createDoubleArray();
     *     quat4.toMat4(Quat, quatMat);
     *     Mat4.multiply(dest, quatMat);
     *     Mat4.scale(dest, scale)
     *     Mat4.translate(dest, negativeOrigin);
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {quat4} rotationQuat Rotation quaternion
     * @param {Vec3} translationVec3 Translation vector
     * @param {Vec3} scalingVec3 Scaling vector
     * @param {Vec3} originVec3ToScaleRotateAround The origin vector around which to scale and rotate
     * @returns {Mat4} inOut
     */
    fun fromRotationTranslationScaleOrigin(rotationQuat: Quat, translationVec3: Vec3, scalingVec3: Vec3, originVec3ToScaleRotateAround: Vec3): Mat4 {
        // Quaternion math
        val x = rotationQuat[0]
        val y = rotationQuat[1]
        val z = rotationQuat[2]
        val w = rotationQuat[3]
        val x2 = x + x
        val y2 = y + y
        val z2 = z + z
        val xx = x * x2
        val xy = x * y2
        val xz = x * z2
        val yy = y * y2
        val yz = y * z2
        val zz = z * z2
        val wx = w * x2
        val wy = w * y2
        val wz = w * z2
        val sx = scalingVec3[0]
        val sy = scalingVec3[1]
        val sz = scalingVec3[2]
        val ox = originVec3ToScaleRotateAround[0]
        val oy = originVec3ToScaleRotateAround[1]
        val oz = originVec3ToScaleRotateAround[2]
        this.matrix[0] = (1 - (yy + zz)) * sx
        this.matrix[1] = (xy + wz) * sx
        this.matrix[2] = (xz - wy) * sx
        this.matrix[3] = 0.0
        this.matrix[4] = (xy - wz) * sy
        this.matrix[5] = (1 - (xx + zz)) * sy
        this.matrix[6] = (yz + wx) * sy
        this.matrix[7] = 0.0
        this.matrix[8] = (xz + wy) * sz
        this.matrix[9] = (yz - wx) * sz
        this.matrix[10] = (1 - (xx + yy)) * sz
        this.matrix[11] = 0.0
        this.matrix[12] = translationVec3[0] + ox - (this.matrix[0] * ox + this.matrix[4] * oy + this.matrix[8] * oz)
        this.matrix[13] = translationVec3[1] + oy - (this.matrix[1] * ox + this.matrix[5] * oy + this.matrix[9] * oz)
        this.matrix[14] = translationVec3[2] + oz - (this.matrix[2] * ox + this.matrix[6] * oy + this.matrix[10] * oz)
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Calculates a 4x4 matrix from the given quaternion
     *
     * @param {Mat4} inOut Mat4 receiving operation result
     * @param {Quat} quatToCreateMatrixFrom Quaternion to createDoubleArray matrix from
     *
     * @returns {Mat4} inOut
     */
    fun fromQuat(quatToCreateMatrixFrom: Quat): Mat4 {
        val x = quatToCreateMatrixFrom[0]
        val y = quatToCreateMatrixFrom[1]
        val z = quatToCreateMatrixFrom[2]
        val w = quatToCreateMatrixFrom[3]
        val x2 = x + x
        val y2 = y + y
        val z2 = z + z
        val xx = x * x2
        val yx = y * x2
        val yy = y * y2
        val zx = z * x2
        val zy = z * y2
        val zz = z * z2
        val wx = w * x2
        val wy = w * y2
        val wz = w * z2
        this.matrix[0] = 1 - yy - zz
        this.matrix[1] = yx + wz
        this.matrix[2] = zx - wy
        this.matrix[3] = 0.0
        this.matrix[4] = yx - wz
        this.matrix[5] = 1 - xx - zz
        this.matrix[6] = zy + wx
        this.matrix[7] = 0.0
        this.matrix[8] = zx + wy
        this.matrix[9] = zy - wx
        this.matrix[10] = 1 - xx - yy
        this.matrix[11] = 0.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = 0.0
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Generates a frustum matrix with the given bounds
     *
     * @param {Mat4} inOut Mat4 frustum matrix will be written into
     * @param {Number} left Left bound of the frustum
     * @param {Number} right Right bound of the frustum
     * @param {Number} bottom Bottom bound of the frustum
     * @param {Number} top Top bound of the frustum
     * @param {Number} near Near bound of the frustum
     * @param {Number} far Far bound of the frustum
     * @returns {Mat4} inOut
     */
    fun frustum(left: Double, right: Double, bottom: Double, top: Double, near: Double, far: Double): Mat4 {
        val rl = 1 / (right - left)
        val tb = 1 / (top - bottom)
        val nf = 1 / (near - far)
        this.matrix[0] = (near * 2) * rl
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = (near * 2) * tb
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = (right + left) * rl
        this.matrix[9] = (top + bottom) * tb
        this.matrix[10] = (far + near) * nf
        this.matrix[11] = -1.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = (far * near * 2) * nf
        this.matrix[15] = 0.0
        return this
    }

    /**
     * Generates a perspective projection matrix with the given bounds
     *
     * @param {Mat4} inOut Mat4 frustum matrix will be written into
     * @param {number} verticalFieldOfViewInRad Vertical field of view in radians
     * @param {number} aspectRatio Aspect ratio. typically viewport width/height
     * @param {number} nearBound Near bound of the frustum
     * @param {number} farBound Far bound of the frustum
     * @returns {Mat4} inOut
     */
    fun perspective(verticalFieldOfViewInRad: Double, aspectRatio: Double, nearBound: Double, farBound: Double): Mat4 {
        val f = 1.0 / Math.tan(verticalFieldOfViewInRad / 2)
        val nf = 1 / (nearBound - farBound)
        this.matrix[0] = f / aspectRatio
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = f
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = (farBound + nearBound) * nf
        this.matrix[11] = -1.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = (2 * farBound * nearBound) * nf
        this.matrix[15] = 0.0
        return this
    }

    /**
     * Generates a perspective projection matrix with the given field of view.
     * this.matrix is primarily useful for generating projection matrices to be used
     * with the still experiemental WebVR API.
     *
     * @param {Mat4} inOut Mat4 frustum matrix will be written into
     * @param {Object} fieldOfViewObject Object containing the following values: upDegrees, downDegrees, leftDegrees, rightDegrees
     * @param {number} nearBound Near bound of the frustum
     * @param {number} farBound Far bound of the frustum
     * @returns {Mat4} inOut
     */
    fun perspectiveFromFieldOfView(fieldOfViewObject: FieldOfView, nearBound: Double, farBound: Double): Mat4 {
        val upTan = Math.tan(fieldOfViewObject.upDegrees * Math.PI / 180.0)
        val downTan = Math.tan(fieldOfViewObject.downDegrees * Math.PI / 180.0)
        val leftTan = Math.tan(fieldOfViewObject.leftDegrees * Math.PI / 180.0)
        val rightTan = Math.tan(fieldOfViewObject.rightDegrees * Math.PI / 180.0)
        val xScale = 2.0 / (leftTan + rightTan)
        val yScale = 2.0 / (upTan + downTan)
        this.matrix[0] = xScale
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = yScale
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = -(leftTan - rightTan) * xScale * 0.5
        this.matrix[9] = (upTan - downTan) * yScale * 0.5
        this.matrix[10] = farBound / (nearBound - farBound)
        this.matrix[11] = -1.0
        this.matrix[12] = 0.0
        this.matrix[13] = 0.0
        this.matrix[14] = (farBound * nearBound) / (nearBound - farBound)
        this.matrix[15] = 0.0
        return this
    }

    /**
     * Generates a orthogonal projection matrix with the given bounds
     *
     * @param {Mat4} inOut Mat4 frustum matrix will be written into
     * @param {number} leftBound Left bound of the frustum
     * @param {number} rightBound Right bound of the frustum
     * @param {number} bottomBound Bottom bound of the frustum
     * @param {number} topBound Top bound of the frustum
     * @param {number} nearBound Near bound of the frustum
     * @param {number} farBound Far bound of the frustum
     * @returns {Mat4} inOut
     */
    fun ortho(leftBound: Double, rightBound: Double, bottomBound: Double, topBound: Double, nearBound: Double, farBound: Double): Mat4 {
        val lr = 1 / (leftBound - rightBound)
        val bt = 1 / (bottomBound - topBound)
        val nf = 1 / (nearBound - farBound)
        this.matrix[0] = -2 * lr
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 0.0
        this.matrix[5] = -2 * bt
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 0.0
        this.matrix[9] = 0.0
        this.matrix[10] = 2 * nf
        this.matrix[11] = 0.0
        this.matrix[12] = (leftBound + rightBound) * lr
        this.matrix[13] = (topBound + bottomBound) * bt
        this.matrix[14] = (farBound + nearBound) * nf
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Generates a look-at matrix with the given eye position, focal point, and up axis
     *
     * @param {Mat4} out Mat4 frustum matrix will be written into
     * @param {Vec3} eye Position of the viewer
     * @param {Vec3} center Point the viewer is looking at
     * @param {Vec3} up Vec3 pointing up
     * @returns {Mat4} out
     */
    fun lookAt(eye: Vec3, center: Vec3, up: Vec3): Mat4 {
        val eyex = eye[0]
        val eyey = eye[1]
        val eyez = eye[2]
        val upx = up[0]
        val upy = up[1]
        val upz = up[2]
        val centerx = center[0]
        val centery = center[1]
        val centerz = center[2]
        if (Math.abs(eyex - centerx) < EPSILON &&
                Math.abs(eyey - centery) < EPSILON &&
                Math.abs(eyez - centerz) < EPSILON) {

            this.identity()
        }
        var z0 = (eyex - centerx)
        var z1 = (eyey - centery)
        var z2 = (eyez - centerz)
        var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
        z0 *= len
        z1 *= len
        z2 *= len
        var x0 = upy * z2 - upz * z1
        var x1 = upz * z0 - upx * z2
        var x2 = upx * z1 - upy * z0
        len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
        if (len == 0.0) {
            x0 = 0.0
            x1 = 0.0
            x2 = 0.0
        } else {
            len = 1 / len
            x0 *= len
            x1 *= len
            x2 *= len
        }
        var y0 = z1 * x2 - z2 * x1
        var y1 = z2 * x0 - z0 * x2
        var y2 = z0 * x1 - z1 * x0
        len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
        if (len == 0.0) {
            y0 = 0.0
            y1 = 0.0
            y2 = 0.0
        } else {
            len = 1 / len
            y0 *= len
            y1 *= len
            y2 *= len
        }
        this.matrix[0] = x0
        this.matrix[1] = y0
        this.matrix[2] = z0
        this.matrix[3] = 0.0
        this.matrix[4] = x1
        this.matrix[5] = y1
        this.matrix[6] = z1
        this.matrix[7] = 0.0
        this.matrix[8] = x2
        this.matrix[9] = y2
        this.matrix[10] = z2
        this.matrix[11] = 0.0
        this.matrix[12] = -x0 * eyex + x1 * eyey + x2 * eyez
        this.matrix[13] = -y0 * eyex + y1 * eyey + y2 * eyez
        this.matrix[14] = -z0 * eyex + z1 * eyey + z2 * eyez
        this.matrix[15] = 1.0
        return this
    }

    fun lookAt(eye: Array<Int>, center: Array<Int>, up: Array<Int>): Mat4 {
        val eyex = eye[0].toDouble()
        val eyey = eye[1].toDouble()
        val eyez = eye[2].toDouble()
        val upx = up[0].toDouble()
        val upy = up[1].toDouble()
        val upz = up[2].toDouble()
        val centerx = center[0].toDouble()
        val centery = center[1].toDouble()
        val centerz = center[2].toDouble()
        if (Math.abs(eyex - centerx) < EPSILON &&
                Math.abs(eyey - centery) < EPSILON &&
                Math.abs(eyez - centerz) < EPSILON) {

            this.identity()
        }
        var z0 = (eyex - centerx)
        var z1 = (eyey - centery)
        var z2 = (eyez - centerz)
        var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
        z0 *= len
        z1 *= len
        z2 *= len
        var x0 = upy * z2 - upz * z1
        var x1 = upz * z0 - upx * z2
        var x2 = upx * z1 - upy * z0
        len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
        if (len == 0.0) {
            x0 = 0.0
            x1 = 0.0
            x2 = 0.0
        } else {
            len = 1 / len
            x0 *= len
            x1 *= len
            x2 *= len
        }
        var y0 = z1 * x2 - z2 * x1
        var y1 = z2 * x0 - z0 * x2
        var y2 = z0 * x1 - z1 * x0
        len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
        if (len == 0.0) {
            y0 = 0.0
            y1 = 0.0
            y2 = 0.0
        } else {
            len = 1 / len
            y0 *= len
            y1 *= len
            y2 *= len
        }
        this.matrix[0] = x0
        this.matrix[1] = y0
        this.matrix[2] = z0
        this.matrix[3] = 0.0
        this.matrix[4] = x1
        this.matrix[5] = y1
        this.matrix[6] = z1
        this.matrix[7] = 0.0
        this.matrix[8] = x2
        this.matrix[9] = y2
        this.matrix[10] = z2
        this.matrix[11] = 0.0
        this.matrix[12] = -x0 * eyex + x1 * eyey + x2 * eyez
        this.matrix[13] = -y0 * eyex + y1 * eyey + y2 * eyez
        this.matrix[14] = -z0 * eyex + z1 * eyey + z2 * eyez
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Generates a matrix that makes something look at something else.
     *
     * @param {Mat4} inOut Mat4 frustum matrix will be written into
     * @param {Vec3} eye Position of the viewer
     * @param {Vec3} center Point the viewer is looking at
     * @param {Vec3} up Vec3 pointing up
     * @returns {Mat4} inOut
     */
    fun targetTo(eye: Vec3, target: Vec3, up: Vec3): Mat4 {
        val eyex = eye[0]
        val eyey = eye[1]
        val eyez = eye[2]
        val upx = up[0]
        val upy = up[1]
        val upz = up[2]
        var z0 = eyex - target[0]
        var z1 = eyey - target[1]
        var z2 = eyez - target[2]
        var len = z0 * z0 + z1 * z1 + z2 * z2
        if (len > 0) {
            len = 1 / Math.sqrt(len)
            z0 *= len
            z1 *= len
            z2 *= len
        }
        val x0 = upy * z2 - upz * z1
        val x1 = upz * z0 - upx * z2
        val x2 = upx * z1 - upy * z0
        this.matrix[0] = x0
        this.matrix[1] = x1
        this.matrix[2] = x2
        this.matrix[3] = 0.0
        this.matrix[4] = z1 * x2 - z2 * x1
        this.matrix[5] = z2 * x0 - z0 * x2
        this.matrix[6] = z0 * x1 - z1 * x0
        this.matrix[7] = 0.0
        this.matrix[8] = z0
        this.matrix[9] = z1
        this.matrix[10] = z2
        this.matrix[11] = 0.0
        this.matrix[12] = eyex
        this.matrix[13] = eyey
        this.matrix[14] = eyez
        this.matrix[15] = 1.0
        return this
    }

    fun targetTo(eye: Array<Int>, target: Array<Int>, up: Array<Int>): Mat4 {
        val eyex = eye[0].toDouble()
        val eyey = eye[1].toDouble()
        val eyez = eye[2].toDouble()
        val upx = up[0].toDouble()
        val upy = up[1].toDouble()
        val upz = up[2].toDouble()
        var z0 = eyex - target[0].toDouble()
        var z1 = eyey - target[1].toDouble()
        var z2 = eyez - target[2].toDouble()
        var len = z0 * z0 + z1 * z1 + z2 * z2
        if (len > 0) {
            len = 1 / Math.sqrt(len)
            z0 *= len
            z1 *= len
            z2 *= len
        }
        val x0 = upy * z2 - upz * z1
        val x1 = upz * z0 - upx * z2
        val x2 = upx * z1 - upy * z0
        this.matrix[0] = x0
        this.matrix[1] = x1
        this.matrix[2] = x2
        this.matrix[3] = 0.0
        this.matrix[4] = z1 * x2 - z2 * x1
        this.matrix[5] = z2 * x0 - z0 * x2
        this.matrix[6] = z0 * x1 - z1 * x0
        this.matrix[7] = 0.0
        this.matrix[8] = z0
        this.matrix[9] = z1
        this.matrix[10] = z2
        this.matrix[11] = 0.0
        this.matrix[12] = eyex
        this.matrix[13] = eyey
        this.matrix[14] = eyez
        this.matrix[15] = 1.0
        return this
    }

    /**
     * Returns matrix string representation of matrix Mat4
     *
     * @param {Mat4} matrix matrix to represent as matrix string
     * @returns {String} string representation of the matrix
     */
    override fun toString(): String {
        return "Mat4(${this.matrix[0]}, ${this.matrix[1]}, ${this.matrix[2]}, ${this.matrix[3]}, ${this.matrix[4]}, ${this.matrix[5]}, ${this.matrix[6]}, ${this.matrix[7]}," +
                " ${this.matrix[8]}, ${this.matrix[9]}, ${this.matrix[10]}, ${this.matrix[11]}, ${this.matrix[12]}, ${this.matrix[13]}, ${this.matrix[14]}, ${this.matrix[15]})"
    }

    /**
     * Returns Frobenius norm of matrix Mat4
     *
     * @param {Mat4} matrix the matrix to calculate Frobenius norm of
     * @returns {Number} Frobenius norm
     */
    fun frob(): Double {
        return (Math.sqrt(Math.pow(this.matrix[0], 2.0) + Math.pow(this.matrix[1], 2.0)
                + Math.pow(this.matrix[2], 2.0) + Math.pow(this.matrix[3], 2.0) + Math.pow(this.matrix[4], 2.0)
                + Math.pow(this.matrix[5], 2.0) + Math.pow(this.matrix[6], 2.0) + Math.pow(this.matrix[7], 2.0)
                + Math.pow(this.matrix[8], 2.0) + Math.pow(this.matrix[9], 2.0) + Math.pow(this.matrix[10], 2.0)
                + Math.pow(this.matrix[11], 2.0) + Math.pow(this.matrix[12], 2.0) + Math.pow(this.matrix[13], 2.0)
                + Math.pow(this.matrix[14], 2.0) + Math.pow(this.matrix[15], 2.0)))
    }

    /**
     * Multiply each element of the matrix by matrixToScale scalar.
     *
     * @param {Mat4} inOut the receiving matrix
     * @param {Mat4} matrixToScale the matrix to scale
     * @param {Number} amountToScaleBy amount to scale the matrix's elements by
     * @returns {Mat4} inOut
     */
    fun multiplyScalar(amountToScaleBy: Double): Mat4 {
        this.matrix[0] *= amountToScaleBy
        this.matrix[1] *= amountToScaleBy
        this.matrix[2] *= amountToScaleBy
        this.matrix[3] *= amountToScaleBy
        this.matrix[4] *= amountToScaleBy
        this.matrix[5] *= amountToScaleBy
        this.matrix[6] *= amountToScaleBy
        this.matrix[7] *= amountToScaleBy
        this.matrix[8] *= amountToScaleBy
        this.matrix[9] *= amountToScaleBy
        this.matrix[10] *= amountToScaleBy
        this.matrix[11] *= amountToScaleBy
        this.matrix[12] *= amountToScaleBy
        this.matrix[13] *= amountToScaleBy
        this.matrix[14] *= amountToScaleBy
        this.matrix[15] *= amountToScaleBy
        return this
    }

    /**
     * Adds two Mat4's after multiplying each element of the second operand by firstSummand scalar value.
     *
     * @param {Mat4} inOut the receiving vector
     * @param {Mat4} firstSummand the first operand
     * @param {Mat4} secondSummand the second operand
     * @param {Number} amountToScale the amount to amountToScale secondSummand's elements by before adding
     * @returns {Mat4} inOut
     */
    fun multiplyScalarAndAdd(summand: Mat4, amountToScale: Double): Mat4 {
        this.matrix[0] += (summand[0] * amountToScale)
        this.matrix[1] += (summand[1] * amountToScale)
        this.matrix[2] += (summand[2] * amountToScale)
        this.matrix[3] += (summand[3] * amountToScale)
        this.matrix[4] += (summand[4] * amountToScale)
        this.matrix[5] += (summand[5] * amountToScale)
        this.matrix[6] += (summand[6] * amountToScale)
        this.matrix[7] += (summand[7] * amountToScale)
        this.matrix[8] += (summand[8] * amountToScale)
        this.matrix[9] += (summand[9] * amountToScale)
        this.matrix[10] += (summand[10] * amountToScale)
        this.matrix[11] += (summand[11] * amountToScale)
        this.matrix[12] += (summand[12] * amountToScale)
        this.matrix[13] += (summand[13] * amountToScale)
        this.matrix[14] += (summand[14] * amountToScale)
        this.matrix[15] += (summand[15] * amountToScale)
        return this
    }

    /**
     * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
     *
     * @param {Mat4} firstMatrix The first matrix.
     * @param {Mat4} secondMatrix The second matrix.
     * @returns {Boolean} True if the matrices are equal, false otherwise.
     */
    fun exactEquals(matrix: Mat4): Boolean {
        return this.matrix[0] == matrix[0]
                && this.matrix[1] == matrix[1]
                && this.matrix[2] == matrix[2]
                && this.matrix[3] == matrix[3]
                && this.matrix[4] == matrix[4]
                && this.matrix[5] == matrix[5]
                && this.matrix[6] == matrix[6]
                && this.matrix[7] == matrix[7]
                && this.matrix[8] == matrix[8]
                && this.matrix[9] == matrix[9]
                && this.matrix[10] == matrix[10]
                && this.matrix[11] == matrix[11]
                && this.matrix[12] == matrix[12]
                && this.matrix[13] == matrix[13]
                && this.matrix[14] == matrix[14]
                && this.matrix[15] == matrix[15]
    }

    /**
     * Returns whether or not the matrices have approximately the same elements in the same position.
     *
     * @param {Mat4} firstMatrix The first matrix.
     * @param {Mat4} secondMatrix The second matrix.
     * @returns {Boolean} True if the matrices are equal, false otherwise.
     */
    fun equals(matrix: Mat4): Boolean {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val a6 = this.matrix[6]
        val a7 = this.matrix[7]
        val a8 = this.matrix[8]
        val a9 = this.matrix[9]
        val a10 = this.matrix[10]
        val a11 = this.matrix[11]
        val a12 = this.matrix[12]
        val a13 = this.matrix[13]
        val a14 = this.matrix[14]
        val a15 = this.matrix[15]
        val b0 = matrix[0]
        val b1 = matrix[1]
        val b2 = matrix[2]
        val b3 = matrix[3]
        val b4 = matrix[4]
        val b5 = matrix[5]
        val b6 = matrix[6]
        val b7 = matrix[7]
        val b8 = matrix[8]
        val b9 = matrix[9]
        val b10 = matrix[10]
        val b11 = matrix[11]
        val b12 = matrix[12]
        val b13 = matrix[13]
        val b14 = matrix[14]
        val b15 = matrix[15]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)) &&
                Math.abs(a6 - b6) <= EPSILON * Math.max(1.0, Math.abs(a6), Math.abs(b6)) &&
                Math.abs(a7 - b7) <= EPSILON * Math.max(1.0, Math.abs(a7), Math.abs(b7)) &&
                Math.abs(a8 - b8) <= EPSILON * Math.max(1.0, Math.abs(a8), Math.abs(b8)) &&
                Math.abs(a9 - b9) <= EPSILON * Math.max(1.0, Math.abs(a9), Math.abs(b9)) &&
                Math.abs(a10 - b10) <= EPSILON * Math.max(1.0, Math.abs(a10), Math.abs(b10)) &&
                Math.abs(a11 - b11) <= EPSILON * Math.max(1.0, Math.abs(a11), Math.abs(b11)) &&
                Math.abs(a12 - b12) <= EPSILON * Math.max(1.0, Math.abs(a12), Math.abs(b12)) &&
                Math.abs(a13 - b13) <= EPSILON * Math.max(1.0, Math.abs(a13), Math.abs(b13)) &&
                Math.abs(a14 - b14) <= EPSILON * Math.max(1.0, Math.abs(a14), Math.abs(b14)) &&
                Math.abs(a15 - b15) <= EPSILON * Math.max(1.0, Math.abs(a15), Math.abs(b15)))
    }


    companion object {

        /**
         * Copy the values from one Mat4 to another
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} toCopy the source matrix
         * @returns {Mat4} inOut
         */
        fun copy(toCopy: Mat4, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            inOut[6] = toCopy[6]
            inOut[7] = toCopy[7]
            inOut[8] = toCopy[8]
            inOut[9] = toCopy[9]
            inOut[10] = toCopy[10]
            inOut[11] = toCopy[11]
            inOut[12] = toCopy[12]
            inOut[13] = toCopy[13]
            inOut[14] = toCopy[14]
            inOut[15] = toCopy[15]
            return inOut
        }

        fun copy(toCopy: DoubleArray, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            inOut[6] = toCopy[6]
            inOut[7] = toCopy[7]
            inOut[8] = toCopy[8]
            inOut[9] = toCopy[9]
            inOut[10] = toCopy[10]
            inOut[11] = toCopy[11]
            inOut[12] = toCopy[12]
            inOut[13] = toCopy[13]
            inOut[14] = toCopy[14]
            inOut[15] = toCopy[15]
            return inOut
        }

        fun copy(toCopy: Float32Array, inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            inOut[6] = toCopy[6]
            inOut[7] = toCopy[7]
            inOut[8] = toCopy[8]
            inOut[9] = toCopy[9]
            inOut[10] = toCopy[10]
            inOut[11] = toCopy[11]
            inOut[12] = toCopy[12]
            inOut[13] = toCopy[13]
            inOut[14] = toCopy[14]
            inOut[15] = toCopy[15]
            return inOut
        }

        /**
         * Create a new Mat4 with the given values
         *
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m02 Component in column 0, row 2 position (index 2)
         * @param {Number} m03 Component in column 0, row 3 position (index 3)
         * @param {Number} m10 Component in column 1, row 0 position (index 4)
         * @param {Number} m11 Component in column 1, row 1 position (index 5)
         * @param {Number} m12 Component in column 1, row 2 position (index 6)
         * @param {Number} m13 Component in column 1, row 3 position (index 7)
         * @param {Number} m20 Component in column 2, row 0 position (index 8)
         * @param {Number} m21 Component in column 2, row 1 position (index 9)
         * @param {Number} m22 Component in column 2, row 2 position (index 10)
         * @param {Number} m23 Component in column 2, row 3 position (index 11)
         * @param {Number} m30 Component in column 3, row 0 position (index 12)
         * @param {Number} m31 Component in column 3, row 1 position (index 13)
         * @param {Number} m32 Component in column 3, row 2 position (index 14)
         * @param {Number} m33 Component in column 3, row 3 position (index 15)
         * @returns {Mat4} A new Mat4
         */
        fun fromValues(m00: Float, m01: Float, m02: Float, m03: Float, m10: Float, m11: Float, m12: Float, m13: Float, m20: Float, m21: Float, m22: Float, m23: Float, m30: Float, m31: Float, m32: Float, m33: Float): Float32Array {
            val out = Float32Array(16)
            out[0] = m00
            out[1] = m01
            out[2] = m02
            out[3] = m03
            out[4] = m10
            out[5] = m11
            out[6] = m12
            out[7] = m13
            out[8] = m20
            out[9] = m21
            out[10] = m22
            out[11] = m23
            out[12] = m30
            out[13] = m31
            out[14] = m32
            out[15] = m33
            return out
        }

        fun fromValues(m00: Double, m01: Double, m02: Double, m03: Double, m10: Double, m11: Double, m12: Double, m13: Double, m20: Double, m21: Double, m22: Double, m23: Double, m30: Double, m31: Double, m32: Double, m33: Double): DoubleArray {
            val out = DoubleArray(16)
            out[0] = m00
            out[1] = m01
            out[2] = m02
            out[3] = m03
            out[4] = m10
            out[5] = m11
            out[6] = m12
            out[7] = m13
            out[8] = m20
            out[9] = m21
            out[10] = m22
            out[11] = m23
            out[12] = m30
            out[13] = m31
            out[14] = m32
            out[15] = m33
            return out
        }

        /**
         * Set the components of a Mat4 to the given values
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m02 Component in column 0, row 2 position (index 2)
         * @param {Number} m03 Component in column 0, row 3 position (index 3)
         * @param {Number} m10 Component in column 1, row 0 position (index 4)
         * @param {Number} m11 Component in column 1, row 1 position (index 5)
         * @param {Number} m12 Component in column 1, row 2 position (index 6)
         * @param {Number} m13 Component in column 1, row 3 position (index 7)
         * @param {Number} m20 Component in column 2, row 0 position (index 8)
         * @param {Number} m21 Component in column 2, row 1 position (index 9)
         * @param {Number} m22 Component in column 2, row 2 position (index 10)
         * @param {Number} m23 Component in column 2, row 3 position (index 11)
         * @param {Number} m30 Component in column 3, row 0 position (index 12)
         * @param {Number} m31 Component in column 3, row 1 position (index 13)
         * @param {Number} m32 Component in column 3, row 2 position (index 14)
         * @param {Number} m33 Component in column 3, row 3 position (index 15)
         * @returns {Mat4} inOut
         */
        fun set(inOut: Float32Array, m00: Float, m01: Float, m02: Float, m03: Float, m10: Float, m11: Float, m12: Float, m13: Float, m20: Float, m21: Float, m22: Float, m23: Float, m30: Float, m31: Float, m32: Float, m33: Float): Float32Array {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m02
            inOut[3] = m03
            inOut[4] = m10
            inOut[5] = m11
            inOut[6] = m12
            inOut[7] = m13
            inOut[8] = m20
            inOut[9] = m21
            inOut[10] = m22
            inOut[11] = m23
            inOut[12] = m30
            inOut[13] = m31
            inOut[14] = m32
            inOut[15] = m33
            return inOut
        }

        fun set(inOut: Float32Array, m00: Double, m01: Double, m02: Double, m03: Double, m10: Double, m11: Double, m12: Double, m13: Double, m20: Double, m21: Double, m22: Double, m23: Double, m30: Double, m31: Double, m32: Double, m33: Double): Float32Array {
            inOut[0] = m00.toFloat()
            inOut[1] = m01.toFloat()
            inOut[2] = m02.toFloat()
            inOut[3] = m03.toFloat()
            inOut[4] = m10.toFloat()
            inOut[5] = m11.toFloat()
            inOut[6] = m12.toFloat()
            inOut[7] = m13.toFloat()
            inOut[8] = m20.toFloat()
            inOut[9] = m21.toFloat()
            inOut[10] = m22.toFloat()
            inOut[11] = m23.toFloat()
            inOut[12] = m30.toFloat()
            inOut[13] = m31.toFloat()
            inOut[14] = m32.toFloat()
            inOut[15] = m33.toFloat()
            return inOut
        }

        /**
         * Set a Mat4 to the identityDoubleArray matrix
         *
         * @param {Mat4} inOut the receiving matrix
         * @returns {Mat4} inOut
         */
        fun identity(inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun identityDoubleArray(inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun identityFloat32Array(inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = 1.0f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = 1.0f
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Transpose the values of a Mat4
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} source the source matrix
         * @returns {Mat4} inOut
         */
        fun transpose(source: Mat4, inOut: Mat4 = Mat4()): Mat4 {
            // If we are transposing ourselves we can skip a few steps but have to cache some values
            if (inOut === source) {
                val a01 = source[1]
                val a02 = source[2]
                val a03 = source[3]
                val a12 = source[6]
                val a13 = source[7]
                val a23 = source[11]
                inOut[1] = source[4]
                inOut[2] = source[8]
                inOut[3] = source[12]
                inOut[4] = a01
                inOut[6] = source[9]
                inOut[7] = source[13]
                inOut[8] = a02
                inOut[9] = a12
                inOut[11] = source[14]
                inOut[12] = a03
                inOut[13] = a13
                inOut[14] = a23
            } else {
                inOut[0] = source[0]
                inOut[1] = source[4]
                inOut[2] = source[8]
                inOut[3] = source[12]
                inOut[4] = source[1]
                inOut[5] = source[5]
                inOut[6] = source[9]
                inOut[7] = source[13]
                inOut[8] = source[2]
                inOut[9] = source[6]
                inOut[10] = source[10]
                inOut[11] = source[14]
                inOut[12] = source[3]
                inOut[13] = source[7]
                inOut[14] = source[11]
                inOut[15] = source[15]
            }
            return inOut
        }

        fun transpose(source: DoubleArray, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            // If we are transposing ourselves we can skip a few steps but have to cache some values
            if (inOut === source) {
                val a01 = source[1]
                val a02 = source[2]
                val a03 = source[3]
                val a12 = source[6]
                val a13 = source[7]
                val a23 = source[11]
                inOut[1] = source[4]
                inOut[2] = source[8]
                inOut[3] = source[12]
                inOut[4] = a01
                inOut[6] = source[9]
                inOut[7] = source[13]
                inOut[8] = a02
                inOut[9] = a12
                inOut[11] = source[14]
                inOut[12] = a03
                inOut[13] = a13
                inOut[14] = a23
            } else {
                inOut[0] = source[0]
                inOut[1] = source[4]
                inOut[2] = source[8]
                inOut[3] = source[12]
                inOut[4] = source[1]
                inOut[5] = source[5]
                inOut[6] = source[9]
                inOut[7] = source[13]
                inOut[8] = source[2]
                inOut[9] = source[6]
                inOut[10] = source[10]
                inOut[11] = source[14]
                inOut[12] = source[3]
                inOut[13] = source[7]
                inOut[14] = source[11]
                inOut[15] = source[15]
            }
            return inOut
        }

        fun transpose(source: Float32Array, inOut: Float32Array = Float32Array(16)): Float32Array {
            // If we are transposing ourselves we can skip a few steps but have to cache some values
            if (inOut === source) {
                val a01 = source[1]
                val a02 = source[2]
                val a03 = source[3]
                val a12 = source[6]
                val a13 = source[7]
                val a23 = source[11]
                inOut[1] = source[4]
                inOut[2] = source[8]
                inOut[3] = source[12]
                inOut[4] = a01
                inOut[6] = source[9]
                inOut[7] = source[13]
                inOut[8] = a02
                inOut[9] = a12
                inOut[11] = source[14]
                inOut[12] = a03
                inOut[13] = a13
                inOut[14] = a23
            } else {
                inOut[0] = source[0]
                inOut[1] = source[4]
                inOut[2] = source[8]
                inOut[3] = source[12]
                inOut[4] = source[1]
                inOut[5] = source[5]
                inOut[6] = source[9]
                inOut[7] = source[13]
                inOut[8] = source[2]
                inOut[9] = source[6]
                inOut[10] = source[10]
                inOut[11] = source[14]
                inOut[12] = source[3]
                inOut[13] = source[7]
                inOut[14] = source[11]
                inOut[15] = source[15]
            }
            return inOut
        }

        /**
         * Inverts a Mat4
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} source the source matrix
         * @returns {Mat4} inOut
         */
        fun invert(source: Mat4, inOut: Mat4 = Mat4()): Mat4 {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            val b00 = a00 * a11 - a01 * a10
            val b01 = a00 * a12 - a02 * a10
            val b02 = a00 * a13 - a03 * a10
            val b03 = a01 * a12 - a02 * a11
            val b04 = a01 * a13 - a03 * a11
            val b05 = a02 * a13 - a03 * a12
            val b06 = a20 * a31 - a21 * a30
            val b07 = a20 * a32 - a22 * a30
            val b08 = a20 * a33 - a23 * a30
            val b09 = a21 * a32 - a22 * a31
            val b10 = a21 * a33 - a23 * a31
            val b11 = a22 * a33 - a23 * a32
            // Calculate the determinant
            var det = b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06
            if (det < 0) {
                return Mat4()
            }
            det = 1.0 / det
            inOut[0] = (a11 * b11 - a12 * b10 + a13 * b09) * det
            inOut[1] = (a02 * b10 - a01 * b11 - a03 * b09) * det
            inOut[2] = (a31 * b05 - a32 * b04 + a33 * b03) * det
            inOut[3] = (a22 * b04 - a21 * b05 - a23 * b03) * det
            inOut[4] = (a12 * b08 - a10 * b11 - a13 * b07) * det
            inOut[5] = (a00 * b11 - a02 * b08 + a03 * b07) * det
            inOut[6] = (a32 * b02 - a30 * b05 - a33 * b01) * det
            inOut[7] = (a20 * b05 - a22 * b02 + a23 * b01) * det
            inOut[8] = (a10 * b10 - a11 * b08 + a13 * b06) * det
            inOut[9] = (a01 * b08 - a00 * b10 - a03 * b06) * det
            inOut[10] = (a30 * b04 - a31 * b02 + a33 * b00) * det
            inOut[11] = (a21 * b02 - a20 * b04 - a23 * b00) * det
            inOut[12] = (a11 * b07 - a10 * b09 - a12 * b06) * det
            inOut[13] = (a00 * b09 - a01 * b07 + a02 * b06) * det
            inOut[14] = (a31 * b01 - a30 * b03 - a32 * b00) * det
            inOut[15] = (a20 * b03 - a21 * b01 + a22 * b00) * det
            return inOut
        }

        fun invert(source: DoubleArray, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            val b00 = a00 * a11 - a01 * a10
            val b01 = a00 * a12 - a02 * a10
            val b02 = a00 * a13 - a03 * a10
            val b03 = a01 * a12 - a02 * a11
            val b04 = a01 * a13 - a03 * a11
            val b05 = a02 * a13 - a03 * a12
            val b06 = a20 * a31 - a21 * a30
            val b07 = a20 * a32 - a22 * a30
            val b08 = a20 * a33 - a23 * a30
            val b09 = a21 * a32 - a22 * a31
            val b10 = a21 * a33 - a23 * a31
            val b11 = a22 * a33 - a23 * a32
            // Calculate the determinant
            var det = b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06
            if (det < 0) {
                return DoubleArray(16)
            }
            det = 1.0 / det
            inOut[0] = (a11 * b11 - a12 * b10 + a13 * b09) * det
            inOut[1] = (a02 * b10 - a01 * b11 - a03 * b09) * det
            inOut[2] = (a31 * b05 - a32 * b04 + a33 * b03) * det
            inOut[3] = (a22 * b04 - a21 * b05 - a23 * b03) * det
            inOut[4] = (a12 * b08 - a10 * b11 - a13 * b07) * det
            inOut[5] = (a00 * b11 - a02 * b08 + a03 * b07) * det
            inOut[6] = (a32 * b02 - a30 * b05 - a33 * b01) * det
            inOut[7] = (a20 * b05 - a22 * b02 + a23 * b01) * det
            inOut[8] = (a10 * b10 - a11 * b08 + a13 * b06) * det
            inOut[9] = (a01 * b08 - a00 * b10 - a03 * b06) * det
            inOut[10] = (a30 * b04 - a31 * b02 + a33 * b00) * det
            inOut[11] = (a21 * b02 - a20 * b04 - a23 * b00) * det
            inOut[12] = (a11 * b07 - a10 * b09 - a12 * b06) * det
            inOut[13] = (a00 * b09 - a01 * b07 + a02 * b06) * det
            inOut[14] = (a31 * b01 - a30 * b03 - a32 * b00) * det
            inOut[15] = (a20 * b03 - a21 * b01 + a22 * b00) * det
            return inOut
        }

        fun invert(source: Float32Array, inOut: Float32Array = Float32Array(16)): Float32Array {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            val b00 = a00 * a11 - a01 * a10
            val b01 = a00 * a12 - a02 * a10
            val b02 = a00 * a13 - a03 * a10
            val b03 = a01 * a12 - a02 * a11
            val b04 = a01 * a13 - a03 * a11
            val b05 = a02 * a13 - a03 * a12
            val b06 = a20 * a31 - a21 * a30
            val b07 = a20 * a32 - a22 * a30
            val b08 = a20 * a33 - a23 * a30
            val b09 = a21 * a32 - a22 * a31
            val b10 = a21 * a33 - a23 * a31
            val b11 = a22 * a33 - a23 * a32
            // Calculate the determinant
            var det = b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06
            if (det < 0) {
                return Float32Array(0)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = (a11 * b11 - a12 * b10 + a13 * b09) * det
            inOut[1] = (a02 * b10 - a01 * b11 - a03 * b09) * det
            inOut[2] = (a31 * b05 - a32 * b04 + a33 * b03) * det
            inOut[3] = (a22 * b04 - a21 * b05 - a23 * b03) * det
            inOut[4] = (a12 * b08 - a10 * b11 - a13 * b07) * det
            inOut[5] = (a00 * b11 - a02 * b08 + a03 * b07) * det
            inOut[6] = (a32 * b02 - a30 * b05 - a33 * b01) * det
            inOut[7] = (a20 * b05 - a22 * b02 + a23 * b01) * det
            inOut[8] = (a10 * b10 - a11 * b08 + a13 * b06) * det
            inOut[9] = (a01 * b08 - a00 * b10 - a03 * b06) * det
            inOut[10] = (a30 * b04 - a31 * b02 + a33 * b00) * det
            inOut[11] = (a21 * b02 - a20 * b04 - a23 * b00) * det
            inOut[12] = (a11 * b07 - a10 * b09 - a12 * b06) * det
            inOut[13] = (a00 * b09 - a01 * b07 + a02 * b06) * det
            inOut[14] = (a31 * b01 - a30 * b03 - a32 * b00) * det
            inOut[15] = (a20 * b03 - a21 * b01 + a22 * b00) * det
            return inOut
        }

        /**
         * Calculates the adjugate of a Mat4
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} a the source matrix
         * @returns {Mat4} inOut
         */
        fun adjoint(source: Mat4, inOut: Mat4 = Mat4()): Mat4 {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            inOut[0] = (a11 * (a22 * a33 - a23 * a32) - a21 * (a12 * a33 - a13 * a32) + a31 * (a12 * a23 - a13 * a22))
            inOut[1] = -(a01 * (a22 * a33 - a23 * a32) - a21 * (a02 * a33 - a03 * a32) + a31 * (a02 * a23 - a03 * a22))
            inOut[2] = (a01 * (a12 * a33 - a13 * a32) - a11 * (a02 * a33 - a03 * a32) + a31 * (a02 * a13 - a03 * a12))
            inOut[3] = -(a01 * (a12 * a23 - a13 * a22) - a11 * (a02 * a23 - a03 * a22) + a21 * (a02 * a13 - a03 * a12))
            inOut[4] = -(a10 * (a22 * a33 - a23 * a32) - a20 * (a12 * a33 - a13 * a32) + a30 * (a12 * a23 - a13 * a22))
            inOut[5] = (a00 * (a22 * a33 - a23 * a32) - a20 * (a02 * a33 - a03 * a32) + a30 * (a02 * a23 - a03 * a22))
            inOut[6] = -(a00 * (a12 * a33 - a13 * a32) - a10 * (a02 * a33 - a03 * a32) + a30 * (a02 * a13 - a03 * a12))
            inOut[7] = (a00 * (a12 * a23 - a13 * a22) - a10 * (a02 * a23 - a03 * a22) + a20 * (a02 * a13 - a03 * a12))
            inOut[8] = (a10 * (a21 * a33 - a23 * a31) - a20 * (a11 * a33 - a13 * a31) + a30 * (a11 * a23 - a13 * a21))
            inOut[9] = -(a00 * (a21 * a33 - a23 * a31) - a20 * (a01 * a33 - a03 * a31) + a30 * (a01 * a23 - a03 * a21))
            inOut[10] = (a00 * (a11 * a33 - a13 * a31) - a10 * (a01 * a33 - a03 * a31) + a30 * (a01 * a13 - a03 * a11))
            inOut[11] = -(a00 * (a11 * a23 - a13 * a21) - a10 * (a01 * a23 - a03 * a21) + a20 * (a01 * a13 - a03 * a11))
            inOut[12] = -(a10 * (a21 * a32 - a22 * a31) - a20 * (a11 * a32 - a12 * a31) + a30 * (a11 * a22 - a12 * a21))
            inOut[13] = (a00 * (a21 * a32 - a22 * a31) - a20 * (a01 * a32 - a02 * a31) + a30 * (a01 * a22 - a02 * a21))
            inOut[14] = -(a00 * (a11 * a32 - a12 * a31) - a10 * (a01 * a32 - a02 * a31) + a30 * (a01 * a12 - a02 * a11))
            inOut[15] = (a00 * (a11 * a22 - a12 * a21) - a10 * (a01 * a22 - a02 * a21) + a20 * (a01 * a12 - a02 * a11))
            return inOut
        }

        fun adjoint(source: DoubleArray, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            inOut[0] = (a11 * (a22 * a33 - a23 * a32) - a21 * (a12 * a33 - a13 * a32) + a31 * (a12 * a23 - a13 * a22))
            inOut[1] = -(a01 * (a22 * a33 - a23 * a32) - a21 * (a02 * a33 - a03 * a32) + a31 * (a02 * a23 - a03 * a22))
            inOut[2] = (a01 * (a12 * a33 - a13 * a32) - a11 * (a02 * a33 - a03 * a32) + a31 * (a02 * a13 - a03 * a12))
            inOut[3] = -(a01 * (a12 * a23 - a13 * a22) - a11 * (a02 * a23 - a03 * a22) + a21 * (a02 * a13 - a03 * a12))
            inOut[4] = -(a10 * (a22 * a33 - a23 * a32) - a20 * (a12 * a33 - a13 * a32) + a30 * (a12 * a23 - a13 * a22))
            inOut[5] = (a00 * (a22 * a33 - a23 * a32) - a20 * (a02 * a33 - a03 * a32) + a30 * (a02 * a23 - a03 * a22))
            inOut[6] = -(a00 * (a12 * a33 - a13 * a32) - a10 * (a02 * a33 - a03 * a32) + a30 * (a02 * a13 - a03 * a12))
            inOut[7] = (a00 * (a12 * a23 - a13 * a22) - a10 * (a02 * a23 - a03 * a22) + a20 * (a02 * a13 - a03 * a12))
            inOut[8] = (a10 * (a21 * a33 - a23 * a31) - a20 * (a11 * a33 - a13 * a31) + a30 * (a11 * a23 - a13 * a21))
            inOut[9] = -(a00 * (a21 * a33 - a23 * a31) - a20 * (a01 * a33 - a03 * a31) + a30 * (a01 * a23 - a03 * a21))
            inOut[10] = (a00 * (a11 * a33 - a13 * a31) - a10 * (a01 * a33 - a03 * a31) + a30 * (a01 * a13 - a03 * a11))
            inOut[11] = -(a00 * (a11 * a23 - a13 * a21) - a10 * (a01 * a23 - a03 * a21) + a20 * (a01 * a13 - a03 * a11))
            inOut[12] = -(a10 * (a21 * a32 - a22 * a31) - a20 * (a11 * a32 - a12 * a31) + a30 * (a11 * a22 - a12 * a21))
            inOut[13] = (a00 * (a21 * a32 - a22 * a31) - a20 * (a01 * a32 - a02 * a31) + a30 * (a01 * a22 - a02 * a21))
            inOut[14] = -(a00 * (a11 * a32 - a12 * a31) - a10 * (a01 * a32 - a02 * a31) + a30 * (a01 * a12 - a02 * a11))
            inOut[15] = (a00 * (a11 * a22 - a12 * a21) - a10 * (a01 * a22 - a02 * a21) + a20 * (a01 * a12 - a02 * a11))
            return inOut
        }

        fun adjoint(source: Float32Array, inOut: Float32Array = Float32Array(16)): Float32Array {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            inOut[0] = (a11 * (a22 * a33 - a23 * a32) - a21 * (a12 * a33 - a13 * a32) + a31 * (a12 * a23 - a13 * a22))
            inOut[1] = -(a01 * (a22 * a33 - a23 * a32) - a21 * (a02 * a33 - a03 * a32) + a31 * (a02 * a23 - a03 * a22))
            inOut[2] = (a01 * (a12 * a33 - a13 * a32) - a11 * (a02 * a33 - a03 * a32) + a31 * (a02 * a13 - a03 * a12))
            inOut[3] = -(a01 * (a12 * a23 - a13 * a22) - a11 * (a02 * a23 - a03 * a22) + a21 * (a02 * a13 - a03 * a12))
            inOut[4] = -(a10 * (a22 * a33 - a23 * a32) - a20 * (a12 * a33 - a13 * a32) + a30 * (a12 * a23 - a13 * a22))
            inOut[5] = (a00 * (a22 * a33 - a23 * a32) - a20 * (a02 * a33 - a03 * a32) + a30 * (a02 * a23 - a03 * a22))
            inOut[6] = -(a00 * (a12 * a33 - a13 * a32) - a10 * (a02 * a33 - a03 * a32) + a30 * (a02 * a13 - a03 * a12))
            inOut[7] = (a00 * (a12 * a23 - a13 * a22) - a10 * (a02 * a23 - a03 * a22) + a20 * (a02 * a13 - a03 * a12))
            inOut[8] = (a10 * (a21 * a33 - a23 * a31) - a20 * (a11 * a33 - a13 * a31) + a30 * (a11 * a23 - a13 * a21))
            inOut[9] = -(a00 * (a21 * a33 - a23 * a31) - a20 * (a01 * a33 - a03 * a31) + a30 * (a01 * a23 - a03 * a21))
            inOut[10] = (a00 * (a11 * a33 - a13 * a31) - a10 * (a01 * a33 - a03 * a31) + a30 * (a01 * a13 - a03 * a11))
            inOut[11] = -(a00 * (a11 * a23 - a13 * a21) - a10 * (a01 * a23 - a03 * a21) + a20 * (a01 * a13 - a03 * a11))
            inOut[12] = -(a10 * (a21 * a32 - a22 * a31) - a20 * (a11 * a32 - a12 * a31) + a30 * (a11 * a22 - a12 * a21))
            inOut[13] = (a00 * (a21 * a32 - a22 * a31) - a20 * (a01 * a32 - a02 * a31) + a30 * (a01 * a22 - a02 * a21))
            inOut[14] = -(a00 * (a11 * a32 - a12 * a31) - a10 * (a01 * a32 - a02 * a31) + a30 * (a01 * a12 - a02 * a11))
            inOut[15] = (a00 * (a11 * a22 - a12 * a21) - a10 * (a01 * a22 - a02 * a21) + a20 * (a01 * a12 - a02 * a11))
            return inOut
        }

        /**
         * Calculates the determinant of source Mat4
         *
         * @param {Mat4} source the source matrix
         * @returns {Number} determinant of source
         */
        fun determinant(source: Mat4): Double {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            val b00 = a00 * a11 - a01 * a10
            val b01 = a00 * a12 - a02 * a10
            val b02 = a00 * a13 - a03 * a10
            val b03 = a01 * a12 - a02 * a11
            val b04 = a01 * a13 - a03 * a11
            val b05 = a02 * a13 - a03 * a12
            val b06 = a20 * a31 - a21 * a30
            val b07 = a20 * a32 - a22 * a30
            val b08 = a20 * a33 - a23 * a30
            val b09 = a21 * a32 - a22 * a31
            val b10 = a21 * a33 - a23 * a31
            val b11 = a22 * a33 - a23 * a32
            // Calculate the determinant
            return b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06
        }

        fun determinant(source: DoubleArray): Double {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            val b00 = a00 * a11 - a01 * a10
            val b01 = a00 * a12 - a02 * a10
            val b02 = a00 * a13 - a03 * a10
            val b03 = a01 * a12 - a02 * a11
            val b04 = a01 * a13 - a03 * a11
            val b05 = a02 * a13 - a03 * a12
            val b06 = a20 * a31 - a21 * a30
            val b07 = a20 * a32 - a22 * a30
            val b08 = a20 * a33 - a23 * a30
            val b09 = a21 * a32 - a22 * a31
            val b10 = a21 * a33 - a23 * a31
            val b11 = a22 * a33 - a23 * a32
            // Calculate the determinant
            return b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06
        }

        fun determinant(source: Float32Array): Double {
            val a00 = source[0]
            val a01 = source[1]
            val a02 = source[2]
            val a03 = source[3]
            val a10 = source[4]
            val a11 = source[5]
            val a12 = source[6]
            val a13 = source[7]
            val a20 = source[8]
            val a21 = source[9]
            val a22 = source[10]
            val a23 = source[11]
            val a30 = source[12]
            val a31 = source[13]
            val a32 = source[14]
            val a33 = source[15]
            val b00 = a00 * a11 - a01 * a10
            val b01 = a00 * a12 - a02 * a10
            val b02 = a00 * a13 - a03 * a10
            val b03 = a01 * a12 - a02 * a11
            val b04 = a01 * a13 - a03 * a11
            val b05 = a02 * a13 - a03 * a12
            val b06 = a20 * a31 - a21 * a30
            val b07 = a20 * a32 - a22 * a30
            val b08 = a20 * a33 - a23 * a30
            val b09 = a21 * a32 - a22 * a31
            val b10 = a21 * a33 - a23 * a31
            val b11 = a22 * a33 - a23 * a32
            // Calculate the determinant
            return (b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06).toDouble()
        }

        /**
         * Multiplies two mat4s
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} multiplier the first operand
         * @param {Mat4} multiplicand the second operand
         * @returns {Mat4} inOut
         */
        fun multiply(multiplicand: Mat4, multiplier: Mat4, inOut: Mat4 = Mat4()): Mat4 {
            val a00 = multiplicand[0]
            val a01 = multiplicand[1]
            val a02 = multiplicand[2]
            val a03 = multiplicand[3]
            val a10 = multiplicand[4]
            val a11 = multiplicand[5]
            val a12 = multiplicand[6]
            val a13 = multiplicand[7]
            val a20 = multiplicand[8]
            val a21 = multiplicand[9]
            val a22 = multiplicand[10]
            val a23 = multiplicand[11]
            val a30 = multiplicand[12]
            val a31 = multiplicand[13]
            val a32 = multiplicand[14]
            val a33 = multiplicand[15]
            // Cache only the current line of the second matrix
            var b0 = multiplier[0]
            var b1 = multiplier[1]
            var b2 = multiplier[2]
            var b3 = multiplier[3]
            inOut[0] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[1] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[2] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[3] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[4]
            b1 = multiplier[5]
            b2 = multiplier[6]
            b3 = multiplier[7]
            inOut[4] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[5] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[6] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[7] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[8]
            b1 = multiplier[9]
            b2 = multiplier[10]
            b3 = multiplier[11]
            inOut[8] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[9] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[10] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[11] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[12]
            b1 = multiplier[13]
            b2 = multiplier[14]
            b3 = multiplier[15]
            inOut[12] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[13] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[14] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[15] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            return inOut
        }

        fun multiply(multiplicand: DoubleArray, multiplier: DoubleArray, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val a00 = multiplicand[0]
            val a01 = multiplicand[1]
            val a02 = multiplicand[2]
            val a03 = multiplicand[3]
            val a10 = multiplicand[4]
            val a11 = multiplicand[5]
            val a12 = multiplicand[6]
            val a13 = multiplicand[7]
            val a20 = multiplicand[8]
            val a21 = multiplicand[9]
            val a22 = multiplicand[10]
            val a23 = multiplicand[11]
            val a30 = multiplicand[12]
            val a31 = multiplicand[13]
            val a32 = multiplicand[14]
            val a33 = multiplicand[15]
            // Cache only the current line of the second matrix
            var b0 = multiplier[0]
            var b1 = multiplier[1]
            var b2 = multiplier[2]
            var b3 = multiplier[3]
            inOut[0] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[1] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[2] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[3] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[4]
            b1 = multiplier[5]
            b2 = multiplier[6]
            b3 = multiplier[7]
            inOut[4] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[5] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[6] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[7] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[8]
            b1 = multiplier[9]
            b2 = multiplier[10]
            b3 = multiplier[11]
            inOut[8] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[9] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[10] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[11] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[12]
            b1 = multiplier[13]
            b2 = multiplier[14]
            b3 = multiplier[15]
            inOut[12] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[13] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[14] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[15] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            return inOut
        }

        fun multiply(multiplicand: Float32Array, multiplier: Float32Array, inOut: Float32Array = Float32Array(16)): Float32Array {
            val a00 = multiplicand[0]
            val a01 = multiplicand[1]
            val a02 = multiplicand[2]
            val a03 = multiplicand[3]
            val a10 = multiplicand[4]
            val a11 = multiplicand[5]
            val a12 = multiplicand[6]
            val a13 = multiplicand[7]
            val a20 = multiplicand[8]
            val a21 = multiplicand[9]
            val a22 = multiplicand[10]
            val a23 = multiplicand[11]
            val a30 = multiplicand[12]
            val a31 = multiplicand[13]
            val a32 = multiplicand[14]
            val a33 = multiplicand[15]
            // Cache only the current line of the second matrix
            var b0 = multiplier[0]
            var b1 = multiplier[1]
            var b2 = multiplier[2]
            var b3 = multiplier[3]
            inOut[0] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[1] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[2] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[3] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[4]
            b1 = multiplier[5]
            b2 = multiplier[6]
            b3 = multiplier[7]
            inOut[4] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[5] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[6] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[7] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[8]
            b1 = multiplier[9]
            b2 = multiplier[10]
            b3 = multiplier[11]
            inOut[8] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[9] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[10] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[11] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            b0 = multiplier[12]
            b1 = multiplier[13]
            b2 = multiplier[14]
            b3 = multiplier[15]
            inOut[12] = b0 * a00 + b1 * a10 + b2 * a20 + b3 * a30
            inOut[13] = b0 * a01 + b1 * a11 + b2 * a21 + b3 * a31
            inOut[14] = b0 * a02 + b1 * a12 + b2 * a22 + b3 * a32
            inOut[15] = b0 * a03 + b1 * a13 + b2 * a23 + b3 * a33
            return inOut
        }

        /**
         * Translate matrixToTranslate Mat4 by the given vector
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} matrixToTranslate the matrix to translate
         * @param {Vec3} vec3ToTranslateBy vector to translate by
         * @returns {Mat4} inOut
         */
        fun translate(matrixToTranslate: Mat4, vec3ToTranslateBy: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            val x = vec3ToTranslateBy[0]
            val y = vec3ToTranslateBy[1]
            val z = vec3ToTranslateBy[2]
            if (matrixToTranslate === inOut) {
                inOut[12] = matrixToTranslate[0] * x + matrixToTranslate[4] * y + matrixToTranslate[8] * z + matrixToTranslate[12]
                inOut[13] = matrixToTranslate[1] * x + matrixToTranslate[5] * y + matrixToTranslate[9] * z + matrixToTranslate[13]
                inOut[14] = matrixToTranslate[2] * x + matrixToTranslate[6] * y + matrixToTranslate[10] * z + matrixToTranslate[14]
                inOut[15] = matrixToTranslate[3] * x + matrixToTranslate[7] * y + matrixToTranslate[11] * z + matrixToTranslate[15]
            } else {
                val a00 = matrixToTranslate[0]
                val a01 = matrixToTranslate[1]
                val a02 = matrixToTranslate[2]
                val a03 = matrixToTranslate[3]
                val a10 = matrixToTranslate[4]
                val a11 = matrixToTranslate[5]
                val a12 = matrixToTranslate[6]
                val a13 = matrixToTranslate[7]
                val a20 = matrixToTranslate[8]
                val a21 = matrixToTranslate[9]
                val a22 = matrixToTranslate[10]
                val a23 = matrixToTranslate[11]
                inOut[0] = a00
                inOut[1] = a01
                inOut[2] = a02
                inOut[3] = a03
                inOut[4] = a10
                inOut[5] = a11
                inOut[6] = a12
                inOut[7] = a13
                inOut[8] = a20
                inOut[9] = a21
                inOut[10] = a22
                inOut[11] = a23
                inOut[12] = a00 * x + a10 * y + a20 * z + matrixToTranslate[12]
                inOut[13] = a01 * x + a11 * y + a21 * z + matrixToTranslate[13]
                inOut[14] = a02 * x + a12 * y + a22 * z + matrixToTranslate[14]
                inOut[15] = a03 * x + a13 * y + a23 * z + matrixToTranslate[15]
            }
            return inOut
        }

        fun translate(matrixToTranslate: Mat4, vec3ToTranslateBy: Array<Double>, inOut: Mat4 = Mat4()): Mat4 {
            val x = vec3ToTranslateBy[0]
            val y = vec3ToTranslateBy[1]
            val z = vec3ToTranslateBy[2]
            if (matrixToTranslate === inOut) {
                inOut[12] = matrixToTranslate[0] * x + matrixToTranslate[4] * y + matrixToTranslate[8] * z + matrixToTranslate[12]
                inOut[13] = matrixToTranslate[1] * x + matrixToTranslate[5] * y + matrixToTranslate[9] * z + matrixToTranslate[13]
                inOut[14] = matrixToTranslate[2] * x + matrixToTranslate[6] * y + matrixToTranslate[10] * z + matrixToTranslate[14]
                inOut[15] = matrixToTranslate[3] * x + matrixToTranslate[7] * y + matrixToTranslate[11] * z + matrixToTranslate[15]
            } else {
                val a00 = matrixToTranslate[0]
                val a01 = matrixToTranslate[1]
                val a02 = matrixToTranslate[2]
                val a03 = matrixToTranslate[3]
                val a10 = matrixToTranslate[4]
                val a11 = matrixToTranslate[5]
                val a12 = matrixToTranslate[6]
                val a13 = matrixToTranslate[7]
                val a20 = matrixToTranslate[8]
                val a21 = matrixToTranslate[9]
                val a22 = matrixToTranslate[10]
                val a23 = matrixToTranslate[11]
                inOut[0] = a00
                inOut[1] = a01
                inOut[2] = a02
                inOut[3] = a03
                inOut[4] = a10
                inOut[5] = a11
                inOut[6] = a12
                inOut[7] = a13
                inOut[8] = a20
                inOut[9] = a21
                inOut[10] = a22
                inOut[11] = a23
                inOut[12] = a00 * x + a10 * y + a20 * z + matrixToTranslate[12]
                inOut[13] = a01 * x + a11 * y + a21 * z + matrixToTranslate[13]
                inOut[14] = a02 * x + a12 * y + a22 * z + matrixToTranslate[14]
                inOut[15] = a03 * x + a13 * y + a23 * z + matrixToTranslate[15]
            }
            return inOut
        }

        fun translate(matrixToTranslate: DoubleArray, vec3ToTranslateBy: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val x = vec3ToTranslateBy[0]
            val y = vec3ToTranslateBy[1]
            val z = vec3ToTranslateBy[2]
            if (matrixToTranslate === inOut) {
                inOut[12] = matrixToTranslate[0] * x + matrixToTranslate[4] * y + matrixToTranslate[8] * z + matrixToTranslate[12]
                inOut[13] = matrixToTranslate[1] * x + matrixToTranslate[5] * y + matrixToTranslate[9] * z + matrixToTranslate[13]
                inOut[14] = matrixToTranslate[2] * x + matrixToTranslate[6] * y + matrixToTranslate[10] * z + matrixToTranslate[14]
                inOut[15] = matrixToTranslate[3] * x + matrixToTranslate[7] * y + matrixToTranslate[11] * z + matrixToTranslate[15]
            } else {
                val a00 = matrixToTranslate[0]
                val a01 = matrixToTranslate[1]
                val a02 = matrixToTranslate[2]
                val a03 = matrixToTranslate[3]
                val a10 = matrixToTranslate[4]
                val a11 = matrixToTranslate[5]
                val a12 = matrixToTranslate[6]
                val a13 = matrixToTranslate[7]
                val a20 = matrixToTranslate[8]
                val a21 = matrixToTranslate[9]
                val a22 = matrixToTranslate[10]
                val a23 = matrixToTranslate[11]
                inOut[0] = a00
                inOut[1] = a01
                inOut[2] = a02
                inOut[3] = a03
                inOut[4] = a10
                inOut[5] = a11
                inOut[6] = a12
                inOut[7] = a13
                inOut[8] = a20
                inOut[9] = a21
                inOut[10] = a22
                inOut[11] = a23
                inOut[12] = a00 * x + a10 * y + a20 * z + matrixToTranslate[12]
                inOut[13] = a01 * x + a11 * y + a21 * z + matrixToTranslate[13]
                inOut[14] = a02 * x + a12 * y + a22 * z + matrixToTranslate[14]
                inOut[15] = a03 * x + a13 * y + a23 * z + matrixToTranslate[15]
            }
            return inOut
        }

        fun translate(matrixToTranslate: DoubleArray, vec3ToTranslateBy: Array<Double>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val x = vec3ToTranslateBy[0]
            val y = vec3ToTranslateBy[1]
            val z = vec3ToTranslateBy[2]
            if (matrixToTranslate === inOut) {
                inOut[12] = matrixToTranslate[0] * x + matrixToTranslate[4] * y + matrixToTranslate[8] * z + matrixToTranslate[12]
                inOut[13] = matrixToTranslate[1] * x + matrixToTranslate[5] * y + matrixToTranslate[9] * z + matrixToTranslate[13]
                inOut[14] = matrixToTranslate[2] * x + matrixToTranslate[6] * y + matrixToTranslate[10] * z + matrixToTranslate[14]
                inOut[15] = matrixToTranslate[3] * x + matrixToTranslate[7] * y + matrixToTranslate[11] * z + matrixToTranslate[15]
            } else {
                val a00 = matrixToTranslate[0]
                val a01 = matrixToTranslate[1]
                val a02 = matrixToTranslate[2]
                val a03 = matrixToTranslate[3]
                val a10 = matrixToTranslate[4]
                val a11 = matrixToTranslate[5]
                val a12 = matrixToTranslate[6]
                val a13 = matrixToTranslate[7]
                val a20 = matrixToTranslate[8]
                val a21 = matrixToTranslate[9]
                val a22 = matrixToTranslate[10]
                val a23 = matrixToTranslate[11]
                inOut[0] = a00
                inOut[1] = a01
                inOut[2] = a02
                inOut[3] = a03
                inOut[4] = a10
                inOut[5] = a11
                inOut[6] = a12
                inOut[7] = a13
                inOut[8] = a20
                inOut[9] = a21
                inOut[10] = a22
                inOut[11] = a23
                inOut[12] = a00 * x + a10 * y + a20 * z + matrixToTranslate[12]
                inOut[13] = a01 * x + a11 * y + a21 * z + matrixToTranslate[13]
                inOut[14] = a02 * x + a12 * y + a22 * z + matrixToTranslate[14]
                inOut[15] = a03 * x + a13 * y + a23 * z + matrixToTranslate[15]
            }
            return inOut
        }

        fun translate(matrixToTranslate: Float32Array, vec3ToTranslateBy: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            val x = vec3ToTranslateBy[0].toFloat()
            val y = vec3ToTranslateBy[1].toFloat()
            val z = vec3ToTranslateBy[2].toFloat()
            if (matrixToTranslate === inOut) {
                inOut[12] = matrixToTranslate[0] * x + matrixToTranslate[4] * y + matrixToTranslate[8] * z + matrixToTranslate[12]
                inOut[13] = matrixToTranslate[1] * x + matrixToTranslate[5] * y + matrixToTranslate[9] * z + matrixToTranslate[13]
                inOut[14] = matrixToTranslate[2] * x + matrixToTranslate[6] * y + matrixToTranslate[10] * z + matrixToTranslate[14]
                inOut[15] = matrixToTranslate[3] * x + matrixToTranslate[7] * y + matrixToTranslate[11] * z + matrixToTranslate[15]
            } else {
                val a00 = matrixToTranslate[0]
                val a01 = matrixToTranslate[1]
                val a02 = matrixToTranslate[2]
                val a03 = matrixToTranslate[3]
                val a10 = matrixToTranslate[4]
                val a11 = matrixToTranslate[5]
                val a12 = matrixToTranslate[6]
                val a13 = matrixToTranslate[7]
                val a20 = matrixToTranslate[8]
                val a21 = matrixToTranslate[9]
                val a22 = matrixToTranslate[10]
                val a23 = matrixToTranslate[11]
                inOut[0] = a00
                inOut[1] = a01
                inOut[2] = a02
                inOut[3] = a03
                inOut[4] = a10
                inOut[5] = a11
                inOut[6] = a12
                inOut[7] = a13
                inOut[8] = a20
                inOut[9] = a21
                inOut[10] = a22
                inOut[11] = a23
                inOut[12] = a00 * x + a10 * y + a20 * z + matrixToTranslate[12]
                inOut[13] = a01 * x + a11 * y + a21 * z + matrixToTranslate[13]
                inOut[14] = a02 * x + a12 * y + a22 * z + matrixToTranslate[14]
                inOut[15] = a03 * x + a13 * y + a23 * z + matrixToTranslate[15]
            }
            return inOut
        }

        fun translate(matrixToTranslate: Float32Array, vec3ToTranslateBy: Array<Double>, inOut: Float32Array = Float32Array(16)): Float32Array {
            val x = vec3ToTranslateBy[0].toFloat()
            val y = vec3ToTranslateBy[1].toFloat()
            val z = vec3ToTranslateBy[2].toFloat()
            if (matrixToTranslate === inOut) {
                inOut[12] = matrixToTranslate[0] * x + matrixToTranslate[4] * y + matrixToTranslate[8] * z + matrixToTranslate[12]
                inOut[13] = matrixToTranslate[1] * x + matrixToTranslate[5] * y + matrixToTranslate[9] * z + matrixToTranslate[13]
                inOut[14] = matrixToTranslate[2] * x + matrixToTranslate[6] * y + matrixToTranslate[10] * z + matrixToTranslate[14]
                inOut[15] = matrixToTranslate[3] * x + matrixToTranslate[7] * y + matrixToTranslate[11] * z + matrixToTranslate[15]
            } else {
                val a00 = matrixToTranslate[0]
                val a01 = matrixToTranslate[1]
                val a02 = matrixToTranslate[2]
                val a03 = matrixToTranslate[3]
                val a10 = matrixToTranslate[4]
                val a11 = matrixToTranslate[5]
                val a12 = matrixToTranslate[6]
                val a13 = matrixToTranslate[7]
                val a20 = matrixToTranslate[8]
                val a21 = matrixToTranslate[9]
                val a22 = matrixToTranslate[10]
                val a23 = matrixToTranslate[11]
                inOut[0] = a00
                inOut[1] = a01
                inOut[2] = a02
                inOut[3] = a03
                inOut[4] = a10
                inOut[5] = a11
                inOut[6] = a12
                inOut[7] = a13
                inOut[8] = a20
                inOut[9] = a21
                inOut[10] = a22
                inOut[11] = a23
                inOut[12] = a00 * x + a10 * y + a20 * z + matrixToTranslate[12]
                inOut[13] = a01 * x + a11 * y + a21 * z + matrixToTranslate[13]
                inOut[14] = a02 * x + a12 * y + a22 * z + matrixToTranslate[14]
                inOut[15] = a03 * x + a13 * y + a23 * z + matrixToTranslate[15]
            }
            return inOut
        }

        /**
         * Scales the Mat4 by the dimensions in the given Vec3 not using vectorization
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} matrixToScale the matrix to scale
         * @param {Vec3} vec3ToScaleBy the Vec3 to scale the matrix by
         * @returns {Mat4} inOut
         **/
        fun scale(matrixToScale: Mat4, vec3ToScaleBy: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            val x = vec3ToScaleBy[0].toFloat()
            val y = vec3ToScaleBy[1].toFloat()
            val z = vec3ToScaleBy[2].toFloat()
            inOut[0] = matrixToScale[0] * x
            inOut[1] = matrixToScale[1] * x
            inOut[2] = matrixToScale[2] * x
            inOut[3] = matrixToScale[3] * x
            inOut[4] = matrixToScale[4] * y
            inOut[5] = matrixToScale[5] * y
            inOut[6] = matrixToScale[6] * y
            inOut[7] = matrixToScale[7] * y
            inOut[8] = matrixToScale[8] * z
            inOut[9] = matrixToScale[9] * z
            inOut[10] = matrixToScale[10] * z
            inOut[11] = matrixToScale[11] * z
            inOut[12] = matrixToScale[12]
            inOut[13] = matrixToScale[13]
            inOut[14] = matrixToScale[14]
            inOut[15] = matrixToScale[15]
            return inOut
        }

        fun scale(matrixToScale: Mat4, vec3ToScaleBy: Array<Double>, inOut: Mat4 = Mat4()): Mat4 {
            val x = vec3ToScaleBy[0].toFloat()
            val y = vec3ToScaleBy[1].toFloat()
            val z = vec3ToScaleBy[2].toFloat()
            inOut[0] = matrixToScale[0] * x
            inOut[1] = matrixToScale[1] * x
            inOut[2] = matrixToScale[2] * x
            inOut[3] = matrixToScale[3] * x
            inOut[4] = matrixToScale[4] * y
            inOut[5] = matrixToScale[5] * y
            inOut[6] = matrixToScale[6] * y
            inOut[7] = matrixToScale[7] * y
            inOut[8] = matrixToScale[8] * z
            inOut[9] = matrixToScale[9] * z
            inOut[10] = matrixToScale[10] * z
            inOut[11] = matrixToScale[11] * z
            inOut[12] = matrixToScale[12]
            inOut[13] = matrixToScale[13]
            inOut[14] = matrixToScale[14]
            inOut[15] = matrixToScale[15]
            return inOut
        }

        fun scale(matrixToScale: DoubleArray, vec3ToScaleBy: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val x = vec3ToScaleBy[0].toFloat()
            val y = vec3ToScaleBy[1].toFloat()
            val z = vec3ToScaleBy[2].toFloat()
            inOut[0] = matrixToScale[0] * x
            inOut[1] = matrixToScale[1] * x
            inOut[2] = matrixToScale[2] * x
            inOut[3] = matrixToScale[3] * x
            inOut[4] = matrixToScale[4] * y
            inOut[5] = matrixToScale[5] * y
            inOut[6] = matrixToScale[6] * y
            inOut[7] = matrixToScale[7] * y
            inOut[8] = matrixToScale[8] * z
            inOut[9] = matrixToScale[9] * z
            inOut[10] = matrixToScale[10] * z
            inOut[11] = matrixToScale[11] * z
            inOut[12] = matrixToScale[12]
            inOut[13] = matrixToScale[13]
            inOut[14] = matrixToScale[14]
            inOut[15] = matrixToScale[15]
            return inOut
        }

        fun scale(matrixToScale: DoubleArray, vec3ToScaleBy: Array<Double>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val x = vec3ToScaleBy[0].toFloat()
            val y = vec3ToScaleBy[1].toFloat()
            val z = vec3ToScaleBy[2].toFloat()
            inOut[0] = matrixToScale[0] * x
            inOut[1] = matrixToScale[1] * x
            inOut[2] = matrixToScale[2] * x
            inOut[3] = matrixToScale[3] * x
            inOut[4] = matrixToScale[4] * y
            inOut[5] = matrixToScale[5] * y
            inOut[6] = matrixToScale[6] * y
            inOut[7] = matrixToScale[7] * y
            inOut[8] = matrixToScale[8] * z
            inOut[9] = matrixToScale[9] * z
            inOut[10] = matrixToScale[10] * z
            inOut[11] = matrixToScale[11] * z
            inOut[12] = matrixToScale[12]
            inOut[13] = matrixToScale[13]
            inOut[14] = matrixToScale[14]
            inOut[15] = matrixToScale[15]
            return inOut
        }

        fun scale(matrixToScale: Float32Array, vec3ToScaleBy: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            val x = vec3ToScaleBy[0].toFloat()
            val y = vec3ToScaleBy[1].toFloat()
            val z = vec3ToScaleBy[2].toFloat()
            inOut[0] = matrixToScale[0] * x
            inOut[1] = matrixToScale[1] * x
            inOut[2] = matrixToScale[2] * x
            inOut[3] = matrixToScale[3] * x
            inOut[4] = matrixToScale[4] * y
            inOut[5] = matrixToScale[5] * y
            inOut[6] = matrixToScale[6] * y
            inOut[7] = matrixToScale[7] * y
            inOut[8] = matrixToScale[8] * z
            inOut[9] = matrixToScale[9] * z
            inOut[10] = matrixToScale[10] * z
            inOut[11] = matrixToScale[11] * z
            inOut[12] = matrixToScale[12]
            inOut[13] = matrixToScale[13]
            inOut[14] = matrixToScale[14]
            inOut[15] = matrixToScale[15]
            return inOut
        }

        fun scale(matrixToScale: Float32Array, vec3ToScaleBy: Array<Double>, inOut: Float32Array = Float32Array(16)): Float32Array {
            val x = vec3ToScaleBy[0].toFloat()
            val y = vec3ToScaleBy[1].toFloat()
            val z = vec3ToScaleBy[2].toFloat()
            inOut[0] = matrixToScale[0] * x
            inOut[1] = matrixToScale[1] * x
            inOut[2] = matrixToScale[2] * x
            inOut[3] = matrixToScale[3] * x
            inOut[4] = matrixToScale[4] * y
            inOut[5] = matrixToScale[5] * y
            inOut[6] = matrixToScale[6] * y
            inOut[7] = matrixToScale[7] * y
            inOut[8] = matrixToScale[8] * z
            inOut[9] = matrixToScale[9] * z
            inOut[10] = matrixToScale[10] * z
            inOut[11] = matrixToScale[11] * z
            inOut[12] = matrixToScale[12]
            inOut[13] = matrixToScale[13]
            inOut[14] = matrixToScale[14]
            inOut[15] = matrixToScale[15]
            return inOut
        }

        /**
         * Rotates matrixToRotate Mat4 by the given angle around the given axisToRotateAround
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @param {Vec3} axisToRotateAround the axisToRotateAround to rotate around
         * @returns {Mat4} inOut
         */
        fun rotate(matrixToRotate: Mat4, angleInRad: Double, axisToRotateAround: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)

            if (Math.abs(len) < EPSILON) {
                return Mat4()
            }

            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val t = 1 - c
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            // Construct the elements of the rotation matrix
            val b00 = x * x * t + c
            val b01 = y * x * t + z * s
            val b02 = z * x * t - y * s
            val b10 = x * y * t - z * s
            val b11 = y * y * t + c
            val b12 = z * y * t + x * s
            val b20 = x * z * t + y * s
            val b21 = y * z * t - x * s
            val b22 = z * z * t + c
            // Perform rotation-specific matrix multiplication
            inOut[0] = a00 * b00 + a10 * b01 + a20 * b02
            inOut[1] = a01 * b00 + a11 * b01 + a21 * b02
            inOut[2] = a02 * b00 + a12 * b01 + a22 * b02
            inOut[3] = a03 * b00 + a13 * b01 + a23 * b02
            inOut[4] = a00 * b10 + a10 * b11 + a20 * b12
            inOut[5] = a01 * b10 + a11 * b11 + a21 * b12
            inOut[6] = a02 * b10 + a12 * b11 + a22 * b12
            inOut[7] = a03 * b10 + a13 * b11 + a23 * b12
            inOut[8] = a00 * b20 + a10 * b21 + a20 * b22
            inOut[9] = a01 * b20 + a11 * b21 + a21 * b22
            inOut[10] = a02 * b20 + a12 * b21 + a22 * b22
            inOut[11] = a03 * b20 + a13 * b21 + a23 * b22
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            return inOut
        }

        fun rotate(matrixToRotate: Mat4, angleInRad: Double, axisToRotateAround: Array<Int>, inOut: Mat4 = Mat4()): Mat4 {
            var x = axisToRotateAround[0].toDouble()
            var y = axisToRotateAround[1].toDouble()
            var z = axisToRotateAround[2].toDouble()
            var len = Math.sqrt(x * x + y * y + z * z)

            if (Math.abs(len) < EPSILON) {
                return Mat4()
            }

            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val t = 1 - c
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            // Construct the elements of the rotation matrix
            val b00 = x * x * t + c
            val b01 = y * x * t + z * s
            val b02 = z * x * t - y * s
            val b10 = x * y * t - z * s
            val b11 = y * y * t + c
            val b12 = z * y * t + x * s
            val b20 = x * z * t + y * s
            val b21 = y * z * t - x * s
            val b22 = z * z * t + c
            // Perform rotation-specific matrix multiplication
            inOut[0] = a00 * b00 + a10 * b01 + a20 * b02
            inOut[1] = a01 * b00 + a11 * b01 + a21 * b02
            inOut[2] = a02 * b00 + a12 * b01 + a22 * b02
            inOut[3] = a03 * b00 + a13 * b01 + a23 * b02
            inOut[4] = a00 * b10 + a10 * b11 + a20 * b12
            inOut[5] = a01 * b10 + a11 * b11 + a21 * b12
            inOut[6] = a02 * b10 + a12 * b11 + a22 * b12
            inOut[7] = a03 * b10 + a13 * b11 + a23 * b12
            inOut[8] = a00 * b20 + a10 * b21 + a20 * b22
            inOut[9] = a01 * b20 + a11 * b21 + a21 * b22
            inOut[10] = a02 * b20 + a12 * b21 + a22 * b22
            inOut[11] = a03 * b20 + a13 * b21 + a23 * b22
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            return inOut
        }

        fun rotate(matrixToRotate: DoubleArray, angleInRad: Double, axisToRotateAround: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)

            if (Math.abs(len) < EPSILON) {
                return DoubleArray(16)
            }

            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val t = 1 - c
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            // Construct the elements of the rotation matrix
            val b00 = x * x * t + c
            val b01 = y * x * t + z * s
            val b02 = z * x * t - y * s
            val b10 = x * y * t - z * s
            val b11 = y * y * t + c
            val b12 = z * y * t + x * s
            val b20 = x * z * t + y * s
            val b21 = y * z * t - x * s
            val b22 = z * z * t + c
            // Perform rotation-specific matrix multiplication
            inOut[0] = a00 * b00 + a10 * b01 + a20 * b02
            inOut[1] = a01 * b00 + a11 * b01 + a21 * b02
            inOut[2] = a02 * b00 + a12 * b01 + a22 * b02
            inOut[3] = a03 * b00 + a13 * b01 + a23 * b02
            inOut[4] = a00 * b10 + a10 * b11 + a20 * b12
            inOut[5] = a01 * b10 + a11 * b11 + a21 * b12
            inOut[6] = a02 * b10 + a12 * b11 + a22 * b12
            inOut[7] = a03 * b10 + a13 * b11 + a23 * b12
            inOut[8] = a00 * b20 + a10 * b21 + a20 * b22
            inOut[9] = a01 * b20 + a11 * b21 + a21 * b22
            inOut[10] = a02 * b20 + a12 * b21 + a22 * b22
            inOut[11] = a03 * b20 + a13 * b21 + a23 * b22
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            return inOut
        }

        fun rotate(matrixToRotate: DoubleArray, angleInRad: Double, axisToRotateAround: Array<Int>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            var x = axisToRotateAround[0].toDouble()
            var y = axisToRotateAround[1].toDouble()
            var z = axisToRotateAround[2].toDouble()
            var len = Math.sqrt(x * x + y * y + z * z)

            if (Math.abs(len) < EPSILON) {
                return DoubleArray(16)
            }

            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val t = 1 - c
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            // Construct the elements of the rotation matrix
            val b00 = x * x * t + c
            val b01 = y * x * t + z * s
            val b02 = z * x * t - y * s
            val b10 = x * y * t - z * s
            val b11 = y * y * t + c
            val b12 = z * y * t + x * s
            val b20 = x * z * t + y * s
            val b21 = y * z * t - x * s
            val b22 = z * z * t + c
            // Perform rotation-specific matrix multiplication
            inOut[0] = a00 * b00 + a10 * b01 + a20 * b02
            inOut[1] = a01 * b00 + a11 * b01 + a21 * b02
            inOut[2] = a02 * b00 + a12 * b01 + a22 * b02
            inOut[3] = a03 * b00 + a13 * b01 + a23 * b02
            inOut[4] = a00 * b10 + a10 * b11 + a20 * b12
            inOut[5] = a01 * b10 + a11 * b11 + a21 * b12
            inOut[6] = a02 * b10 + a12 * b11 + a22 * b12
            inOut[7] = a03 * b10 + a13 * b11 + a23 * b12
            inOut[8] = a00 * b20 + a10 * b21 + a20 * b22
            inOut[9] = a01 * b20 + a11 * b21 + a21 * b22
            inOut[10] = a02 * b20 + a12 * b21 + a22 * b22
            inOut[11] = a03 * b20 + a13 * b21 + a23 * b22
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            return inOut
        }

        fun rotate(matrixToRotate: Float32Array, angleInRad: Double, axisToRotateAround: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)

            if (Math.abs(len) < EPSILON) {
                return Float32Array(16)
            }

            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val t = 1 - c
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            // Construct the elements of the rotation matrix
            val b00 = (x * x * t + c).toFloat()
            val b01 = (y * x * t + z * s).toFloat()
            val b02 = (z * x * t - y * s).toFloat()
            val b10 = (x * y * t - z * s).toFloat()
            val b11 = (y * y * t + c).toFloat()
            val b12 = (z * y * t + x * s).toFloat()
            val b20 = (x * z * t + y * s).toFloat()
            val b21 = (y * z * t - x * s).toFloat()
            val b22 = (z * z * t + c).toFloat()
            // Perform rotation-specific matrix multiplication
            inOut[0] = a00 * b00 + a10 * b01 + a20 * b02
            inOut[1] = a01 * b00 + a11 * b01 + a21 * b02
            inOut[2] = a02 * b00 + a12 * b01 + a22 * b02
            inOut[3] = a03 * b00 + a13 * b01 + a23 * b02
            inOut[4] = a00 * b10 + a10 * b11 + a20 * b12
            inOut[5] = a01 * b10 + a11 * b11 + a21 * b12
            inOut[6] = a02 * b10 + a12 * b11 + a22 * b12
            inOut[7] = a03 * b10 + a13 * b11 + a23 * b12
            inOut[8] = a00 * b20 + a10 * b21 + a20 * b22
            inOut[9] = a01 * b20 + a11 * b21 + a21 * b22
            inOut[10] = a02 * b20 + a12 * b21 + a22 * b22
            inOut[11] = a03 * b20 + a13 * b21 + a23 * b22
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            return inOut
        }

        fun rotate(matrixToRotate: Float32Array, angleInRad: Double, axisToRotateAround: Array<Int>, inOut: Float32Array = Float32Array(16)): Float32Array {
            var x = axisToRotateAround[0].toDouble()
            var y = axisToRotateAround[1].toDouble()
            var z = axisToRotateAround[2].toDouble()
            var len = Math.sqrt(x * x + y * y + z * z)

            if (Math.abs(len) < EPSILON) {
                return Float32Array(16)
            }

            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val t = 1 - c
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            // Construct the elements of the rotation matrix
            val b00 = (x * x * t + c).toFloat()
            val b01 = (y * x * t + z * s).toFloat()
            val b02 = (z * x * t - y * s).toFloat()
            val b10 = (x * y * t - z * s).toFloat()
            val b11 = (y * y * t + c).toFloat()
            val b12 = (z * y * t + x * s).toFloat()
            val b20 = (x * z * t + y * s).toFloat()
            val b21 = (y * z * t - x * s).toFloat()
            val b22 = (z * z * t + c).toFloat()
            // Perform rotation-specific matrix multiplication
            inOut[0] = a00 * b00 + a10 * b01 + a20 * b02
            inOut[1] = a01 * b00 + a11 * b01 + a21 * b02
            inOut[2] = a02 * b00 + a12 * b01 + a22 * b02
            inOut[3] = a03 * b00 + a13 * b01 + a23 * b02
            inOut[4] = a00 * b10 + a10 * b11 + a20 * b12
            inOut[5] = a01 * b10 + a11 * b11 + a21 * b12
            inOut[6] = a02 * b10 + a12 * b11 + a22 * b12
            inOut[7] = a03 * b10 + a13 * b11 + a23 * b12
            inOut[8] = a00 * b20 + a10 * b21 + a20 * b22
            inOut[9] = a01 * b20 + a11 * b21 + a21 * b22
            inOut[10] = a02 * b20 + a12 * b21 + a22 * b22
            inOut[11] = a03 * b20 + a13 * b21 + a23 * b22
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            return inOut
        }

        /**
         * Rotates matrixToRotate matrix by the given angle around the X axis
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat4} inOut
         */
        fun rotateX(matrixToRotate: Mat4, angleInRad: Double, inOut: Mat4 = Mat4()): Mat4 {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged rows
                inOut[0] = matrixToRotate[0]
                inOut[1] = matrixToRotate[1]
                inOut[2] = matrixToRotate[2]
                inOut[3] = matrixToRotate[3]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[4] = a10 * c + a20 * s
            inOut[5] = a11 * c + a21 * s
            inOut[6] = a12 * c + a22 * s
            inOut[7] = a13 * c + a23 * s
            inOut[8] = a20 * c - a10 * s
            inOut[9] = a21 * c - a11 * s
            inOut[10] = a22 * c - a12 * s
            inOut[11] = a23 * c - a13 * s
            return inOut
        }

        fun rotateX(matrixToRotate: DoubleArray, angleInRad: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged rows
                inOut[0] = matrixToRotate[0]
                inOut[1] = matrixToRotate[1]
                inOut[2] = matrixToRotate[2]
                inOut[3] = matrixToRotate[3]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[4] = a10 * c + a20 * s
            inOut[5] = a11 * c + a21 * s
            inOut[6] = a12 * c + a22 * s
            inOut[7] = a13 * c + a23 * s
            inOut[8] = a20 * c - a10 * s
            inOut[9] = a21 * c - a11 * s
            inOut[10] = a22 * c - a12 * s
            inOut[11] = a23 * c - a13 * s
            return inOut
        }

        fun rotateX(matrixToRotate: Float32Array, angleInRad: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged rows
                inOut[0] = matrixToRotate[0]
                inOut[1] = matrixToRotate[1]
                inOut[2] = matrixToRotate[2]
                inOut[3] = matrixToRotate[3]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[4] = a10 * c + a20 * s
            inOut[5] = a11 * c + a21 * s
            inOut[6] = a12 * c + a22 * s
            inOut[7] = a13 * c + a23 * s
            inOut[8] = a20 * c - a10 * s
            inOut[9] = a21 * c - a11 * s
            inOut[10] = a22 * c - a12 * s
            inOut[11] = a23 * c - a13 * s
            return inOut
        }

        /**
         * Rotates matrixToRotate matrix by the given angle around the Y axis
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat4} inOut
         */
        fun rotateY(matrixToRotate: Mat4, angleInRad: Double, inOut: Mat4 = Mat4()): Mat4 {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged rows
                inOut[4] = matrixToRotate[4]
                inOut[5] = matrixToRotate[5]
                inOut[6] = matrixToRotate[6]
                inOut[7] = matrixToRotate[7]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[0] = a00 * c - a20 * s
            inOut[1] = a01 * c - a21 * s
            inOut[2] = a02 * c - a22 * s
            inOut[3] = a03 * c - a23 * s
            inOut[8] = a00 * s + a20 * c
            inOut[9] = a01 * s + a21 * c
            inOut[10] = a02 * s + a22 * c
            inOut[11] = a03 * s + a23 * c
            return inOut
        }

        fun rotateY(matrixToRotate: DoubleArray, angleInRad: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged rows
                inOut[4] = matrixToRotate[4]
                inOut[5] = matrixToRotate[5]
                inOut[6] = matrixToRotate[6]
                inOut[7] = matrixToRotate[7]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[0] = a00 * c - a20 * s
            inOut[1] = a01 * c - a21 * s
            inOut[2] = a02 * c - a22 * s
            inOut[3] = a03 * c - a23 * s
            inOut[8] = a00 * s + a20 * c
            inOut[9] = a01 * s + a21 * c
            inOut[10] = a02 * s + a22 * c
            inOut[11] = a03 * s + a23 * c
            return inOut
        }

        fun rotateY(matrixToRotate: Float32Array, angleInRad: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a20 = matrixToRotate[8]
            val a21 = matrixToRotate[9]
            val a22 = matrixToRotate[10]
            val a23 = matrixToRotate[11]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged rows
                inOut[4] = matrixToRotate[4]
                inOut[5] = matrixToRotate[5]
                inOut[6] = matrixToRotate[6]
                inOut[7] = matrixToRotate[7]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[0] = a00 * c - a20 * s
            inOut[1] = a01 * c - a21 * s
            inOut[2] = a02 * c - a22 * s
            inOut[3] = a03 * c - a23 * s
            inOut[8] = a00 * s + a20 * c
            inOut[9] = a01 * s + a21 * c
            inOut[10] = a02 * s + a22 * c
            inOut[11] = a03 * s + a23 * c
            return inOut
        }

        /**
         * Rotates matrixToRotate matrix by the given angle around the Z axis
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat4} inOut
         */
        fun rotateZ(matrixToRotate: Mat4, angleInRad: Double, inOut: Mat4 = Mat4()): Mat4 {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[8] = matrixToRotate[8]
                inOut[9] = matrixToRotate[9]
                inOut[10] = matrixToRotate[10]
                inOut[11] = matrixToRotate[11]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[0] = a00 * c + a10 * s
            inOut[1] = a01 * c + a11 * s
            inOut[2] = a02 * c + a12 * s
            inOut[3] = a03 * c + a13 * s
            inOut[4] = a10 * c - a00 * s
            inOut[5] = a11 * c - a01 * s
            inOut[6] = a12 * c - a02 * s
            inOut[7] = a13 * c - a03 * s
            return inOut
        }

        fun rotateZ(matrixToRotate: DoubleArray, angleInRad: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[8] = matrixToRotate[8]
                inOut[9] = matrixToRotate[9]
                inOut[10] = matrixToRotate[10]
                inOut[11] = matrixToRotate[11]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[0] = a00 * c + a10 * s
            inOut[1] = a01 * c + a11 * s
            inOut[2] = a02 * c + a12 * s
            inOut[3] = a03 * c + a13 * s
            inOut[4] = a10 * c - a00 * s
            inOut[5] = a11 * c - a01 * s
            inOut[6] = a12 * c - a02 * s
            inOut[7] = a13 * c - a03 * s
            return inOut
        }

        fun rotateZ(matrixToRotate: Float32Array, angleInRad: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            val a00 = matrixToRotate[0]
            val a01 = matrixToRotate[1]
            val a02 = matrixToRotate[2]
            val a03 = matrixToRotate[3]
            val a10 = matrixToRotate[4]
            val a11 = matrixToRotate[5]
            val a12 = matrixToRotate[6]
            val a13 = matrixToRotate[7]
            if (matrixToRotate !== inOut) { // If the source and destination differ, copy the unchanged last row
                inOut[8] = matrixToRotate[8]
                inOut[9] = matrixToRotate[9]
                inOut[10] = matrixToRotate[10]
                inOut[11] = matrixToRotate[11]
                inOut[12] = matrixToRotate[12]
                inOut[13] = matrixToRotate[13]
                inOut[14] = matrixToRotate[14]
                inOut[15] = matrixToRotate[15]
            }
            // Perform axis-specific matrix multiplication
            inOut[0] = a00 * c + a10 * s
            inOut[1] = a01 * c + a11 * s
            inOut[2] = a02 * c + a12 * s
            inOut[3] = a03 * c + a13 * s
            inOut[4] = a10 * c - a00 * s
            inOut[5] = a11 * c - a01 * s
            inOut[6] = a12 * c - a02 * s
            inOut[7] = a13 * c - a03 * s
            return inOut
        }

        /**
         * Creates a matrix from a vector translation
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.translate(dest, dest, vec);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {Vec3} translationVec3 Translation vector
         * @returns {Mat4} inOut
         */
        fun fromTranslation(translationVec3: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromTranslation(translationVec3: Array<Double>, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromTranslation(translationVec3: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromTranslation(translationVec3: Array<Double>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromTranslation(translationVec3: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = 1.0f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = 1.0f
            inOut[11] = 0.0f
            inOut[12] = translationVec3[0].toFloat()
            inOut[13] = translationVec3[1].toFloat()
            inOut[14] = translationVec3[2].toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        fun fromTranslation(translationVec3: Array<Double>, inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = 1.0f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = 1.0f
            inOut[11] = 0.0f
            inOut[12] = translationVec3[0].toFloat()
            inOut[13] = translationVec3[1].toFloat()
            inOut[14] = translationVec3[2].toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from a vector scaling
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.scale(dest, dest, vec);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {Vec3} scalingVec3 Scaling vector
         * @returns {Mat4} inOut
         */
        fun fromScaling(scalingVec3: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = scalingVec3[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = scalingVec3[1]
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = scalingVec3[2]
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromScaling(scalingVec3: Array<Double>, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = scalingVec3[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = scalingVec3[1]
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = scalingVec3[2]
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromScaling(scalingVec3: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = scalingVec3[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = scalingVec3[1]
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = scalingVec3[2]
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromScaling(scalingVec3: Array<Double>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = scalingVec3[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = scalingVec3[1]
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = scalingVec3[2]
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromScaling(scalingVec3: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = scalingVec3[0].toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = scalingVec3[1].toFloat()
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = scalingVec3[2].toFloat()
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        fun fromScaling(scalingVec3: Array<Double>, inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = scalingVec3[0].toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = scalingVec3[1].toFloat()
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = scalingVec3[2].toFloat()
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from a given angle around a given axisToRotateAround
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.rotate(dest, dest, angleToRotateByInRad, axisToRotateAround);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
         * @param {Vec3} axisToRotateAround the axisToRotateAround to rotate around
         * @returns {Mat4} inOut
         */
        fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)
            if (Math.abs(len) < EPSILON) {
                return Mat4()
            }
            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            val t = 1 - c
            // Perform rotation-specific matrix multiplication
            inOut[0] = x * x * t + c
            inOut[1] = y * x * t + z * s
            inOut[2] = z * x * t - y * s
            inOut[3] = 0.0
            inOut[4] = x * y * t - z * s
            inOut[5] = y * y * t + c
            inOut[6] = z * y * t + x * s
            inOut[7] = 0.0
            inOut[8] = x * z * t + y * s
            inOut[9] = y * z * t - x * s
            inOut[10] = z * z * t + c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Array<Double>, inOut: Mat4 = Mat4()): Mat4 {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)
            if (Math.abs(len) < EPSILON) {
                return Mat4()
            }
            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            val t = 1 - c
            // Perform rotation-specific matrix multiplication
            inOut[0] = x * x * t + c
            inOut[1] = y * x * t + z * s
            inOut[2] = z * x * t - y * s
            inOut[3] = 0.0
            inOut[4] = x * y * t - z * s
            inOut[5] = y * y * t + c
            inOut[6] = z * y * t + x * s
            inOut[7] = 0.0
            inOut[8] = x * z * t + y * s
            inOut[9] = y * z * t - x * s
            inOut[10] = z * z * t + c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)
            if (Math.abs(len) < EPSILON) {
                return DoubleArray(16)
            }
            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            val t = 1 - c
            // Perform rotation-specific matrix multiplication
            inOut[0] = x * x * t + c
            inOut[1] = y * x * t + z * s
            inOut[2] = z * x * t - y * s
            inOut[3] = 0.0
            inOut[4] = x * y * t - z * s
            inOut[5] = y * y * t + c
            inOut[6] = z * y * t + x * s
            inOut[7] = 0.0
            inOut[8] = x * z * t + y * s
            inOut[9] = y * z * t - x * s
            inOut[10] = z * z * t + c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Array<Double>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)
            if (Math.abs(len) < EPSILON) {
                return DoubleArray(16)
            }
            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            val t = 1 - c
            // Perform rotation-specific matrix multiplication
            inOut[0] = x * x * t + c
            inOut[1] = y * x * t + z * s
            inOut[2] = z * x * t - y * s
            inOut[3] = 0.0
            inOut[4] = x * y * t - z * s
            inOut[5] = y * y * t + c
            inOut[6] = z * y * t + x * s
            inOut[7] = 0.0
            inOut[8] = x * z * t + y * s
            inOut[9] = y * z * t - x * s
            inOut[10] = z * z * t + c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)
            if (Math.abs(len) < EPSILON) {
                return Float32Array(0)
            }
            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            val t = 1 - c
            // Perform rotation-specific matrix multiplication
            inOut[0] = (x * x * t + c).toFloat()
            inOut[1] = (y * x * t + z * s).toFloat()
            inOut[2] = (z * x * t - y * s).toFloat()
            inOut[3] = 0.0f
            inOut[4] = (x * y * t - z * s).toFloat()
            inOut[5] = (y * y * t + c).toFloat()
            inOut[6] = (z * y * t + x * s).toFloat()
            inOut[7] = 0.0f
            inOut[8] = (x * z * t + y * s).toFloat()
            inOut[9] = (y * z * t - x * s).toFloat()
            inOut[10] = (z * z * t + c).toFloat()
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, axisToRotateAround: Array<Double>, inOut: Float32Array = Float32Array(16)): Float32Array {
            var x = axisToRotateAround[0]
            var y = axisToRotateAround[1]
            var z = axisToRotateAround[2]
            var len = Math.sqrt(x * x + y * y + z * z)
            if (Math.abs(len) < EPSILON) {
                return Float32Array(0)
            }
            len = 1 / len
            x *= len
            y *= len
            z *= len
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            val t = 1 - c
            // Perform rotation-specific matrix multiplication
            inOut[0] = (x * x * t + c).toFloat()
            inOut[1] = (y * x * t + z * s).toFloat()
            inOut[2] = (z * x * t - y * s).toFloat()
            inOut[3] = 0.0f
            inOut[4] = (x * y * t - z * s).toFloat()
            inOut[5] = (y * y * t + c).toFloat()
            inOut[6] = (z * y * t + x * s).toFloat()
            inOut[7] = 0.0f
            inOut[8] = (x * z * t + y * s).toFloat()
            inOut[9] = (y * z * t - x * s).toFloat()
            inOut[10] = (z * z * t + c).toFloat()
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from the given angle around the X axis
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.rotateX(dest, dest, angleInRad);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat4} inOut
         */
        fun fromXRotation(angleInRad: Double, inOut: Mat4 = Mat4()): Mat4 {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            // Perform axis-specific matrix multiplication
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = c
            inOut[6] = s
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = -s
            inOut[10] = c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromXRotation(angleInRad: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            // Perform axis-specific matrix multiplication
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = c
            inOut[6] = s
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = -s
            inOut[10] = c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromXRotation(angleInRad: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            // Perform axis-specific matrix multiplication
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = c
            inOut[6] = s
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = -s
            inOut[10] = c
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from the given angle around the Y axis
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.rotateY(dest, dest, angleInRad);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat4} inOut
         */
        fun fromYRotation(angleInRad: Double, inOut: Mat4 = Mat4()): Mat4 {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            // Perform axis-specific matrix multiplication
            inOut[0] = c
            inOut[1] = 0.0
            inOut[2] = -s
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = s
            inOut[9] = 0.0
            inOut[10] = c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromYRotation(angleInRad: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            // Perform axis-specific matrix multiplication
            inOut[0] = c
            inOut[1] = 0.0
            inOut[2] = -s
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = 1.0
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = s
            inOut[9] = 0.0
            inOut[10] = c
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromYRotation(angleInRad: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            // Perform axis-specific matrix multiplication
            inOut[0] = c
            inOut[1] = 0.0f
            inOut[2] = -s
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = 1.0f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = s
            inOut[9] = 0.0f
            inOut[10] = c
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from the given angle around the Z axis
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.rotateZ(dest, dest, angleInRad);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat4} inOut
         */
        fun fromZRotation(angleInRad: Double, inOut: Mat4 = Mat4()): Mat4 {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            // Perform axis-specific matrix multiplication
            inOut[0] = c
            inOut[1] = s
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = -s
            inOut[5] = c
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromZRotation(angleInRad: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            // Perform axis-specific matrix multiplication
            inOut[0] = c
            inOut[1] = s
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = -s
            inOut[5] = c
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 1.0
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromZRotation(angleInRad: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            // Perform axis-specific matrix multiplication
            inOut[0] = c
            inOut[1] = s
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = -s
            inOut[5] = c
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = 1.0f
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from a quaternion rotation and vector translation
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.translate(dest, vec);
         *     let quatMat = Mat4.createDoubleArray();
         *     quat4.toMat4(Quat, quatMat);
         *     Mat4.multiply(dest, quatMat);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {quat4} rotationQuat Rotation quaternion
         * @param {Vec3} translationVec3 Translation vector
         * @returns {Mat4} inOut
         */
        fun fromRotationTranslation(rotationQuat: Quat, translationVec3: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            inOut[0] = 1 - (yy + zz)
            inOut[1] = xy + wz
            inOut[2] = xz - wy
            inOut[3] = 0.0
            inOut[4] = xy - wz
            inOut[5] = 1 - (xx + zz)
            inOut[6] = yz + wx
            inOut[7] = 0.0
            inOut[8] = xz + wy
            inOut[9] = yz - wx
            inOut[10] = 1 - (xx + yy)
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotationTranslation(rotationQuat: Quat, translationVec3: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            inOut[0] = 1 - (yy + zz)
            inOut[1] = xy + wz
            inOut[2] = xz - wy
            inOut[3] = 0.0
            inOut[4] = xy - wz
            inOut[5] = 1 - (xx + zz)
            inOut[6] = yz + wx
            inOut[7] = 0.0
            inOut[8] = xz + wy
            inOut[9] = yz - wx
            inOut[10] = 1 - (xx + yy)
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotationTranslation(rotationQuat: Quat, translationVec3: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            inOut[0] = (1 - (yy + zz)).toFloat()
            inOut[1] = (xy + wz).toFloat()
            inOut[2] = (xz - wy).toFloat()
            inOut[3] = 0.0f
            inOut[4] = (xy - wz).toFloat()
            inOut[5] = (1 - (xx + zz)).toFloat()
            inOut[6] = (yz + wx).toFloat()
            inOut[7] = 0.0f
            inOut[8] = (xz + wy).toFloat()
            inOut[9] = (yz - wx).toFloat()
            inOut[10] = (1 - (xx + yy)).toFloat()
            inOut[11] = 0.0f
            inOut[12] = translationVec3[0].toFloat()
            inOut[13] = translationVec3[1].toFloat()
            inOut[14] = translationVec3[2].toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Returns the translation vector component of a transformation
         *  matrix. If a matrix is built with fromRotationTranslation,
         *  the returned vector will be the same as the translation vector
         *  originally supplied.
         * @param  {Vec3} inOut Vector to receive translation component
         * @param  {Mat4} matrixToBeDecomposed Matrix to be decomposed (input)
         * @return {Vec3} inOut
         */
        fun getTranslation(matrixToBeDecomposed: Mat4, inOut: Vec3 = Vec3()): Vec3 {
            inOut[0] = matrixToBeDecomposed[12]
            inOut[1] = matrixToBeDecomposed[13]
            inOut[2] = matrixToBeDecomposed[14]
            return inOut
        }

        fun getTranslation(matrixToBeDecomposed: DoubleArray, inOut: Vec3 = Vec3()): Vec3 {
            inOut[0] = matrixToBeDecomposed[12]
            inOut[1] = matrixToBeDecomposed[13]
            inOut[2] = matrixToBeDecomposed[14]
            return inOut
        }

        fun getTranslation(matrixToBeDecomposed: Float32Array, inOut: Vec3 = Vec3()): Vec3 {
            inOut[0] = matrixToBeDecomposed[12].toDouble()
            inOut[1] = matrixToBeDecomposed[13].toDouble()
            inOut[2] = matrixToBeDecomposed[14].toDouble()
            return inOut
        }

        /**
         * Returns the scaling factor component of a transformation
         *  matrix. If a matrix is built with fromRotationTranslationScale
         *  with a normalized Quaternion paramter, the returned vector will be
         *  the same as the scaling vector
         *  originally supplied.
         * @param  {Vec3} inOut Vector to receive scaling factor component
         * @param  {Mat4} matrixToBeDecomposed Matrix to be decomposed (input)
         * @return {Vec3} inOut
         */
        fun getScaling(matrixToBeDecomposed: Mat4, inOut: Vec3 = Vec3()): Vec3 {
            val m11 = matrixToBeDecomposed[0]
            val m12 = matrixToBeDecomposed[1]
            val m13 = matrixToBeDecomposed[2]
            val m21 = matrixToBeDecomposed[4]
            val m22 = matrixToBeDecomposed[5]
            val m23 = matrixToBeDecomposed[6]
            val m31 = matrixToBeDecomposed[8]
            val m32 = matrixToBeDecomposed[9]
            val m33 = matrixToBeDecomposed[10]
            inOut[0] = Math.sqrt(m11 * m11 + m12 * m12 + m13 * m13)
            inOut[1] = Math.sqrt(m21 * m21 + m22 * m22 + m23 * m23)
            inOut[2] = Math.sqrt(m31 * m31 + m32 * m32 + m33 * m33)
            return inOut
        }

        fun getScaling(matrixToBeDecomposed: DoubleArray, inOut: Vec3 = Vec3()): Vec3 {
            val m11 = matrixToBeDecomposed[0]
            val m12 = matrixToBeDecomposed[1]
            val m13 = matrixToBeDecomposed[2]
            val m21 = matrixToBeDecomposed[4]
            val m22 = matrixToBeDecomposed[5]
            val m23 = matrixToBeDecomposed[6]
            val m31 = matrixToBeDecomposed[8]
            val m32 = matrixToBeDecomposed[9]
            val m33 = matrixToBeDecomposed[10]
            inOut[0] = Math.sqrt(m11 * m11 + m12 * m12 + m13 * m13)
            inOut[1] = Math.sqrt(m21 * m21 + m22 * m22 + m23 * m23)
            inOut[2] = Math.sqrt(m31 * m31 + m32 * m32 + m33 * m33)
            return inOut
        }

        fun getScaling(matrixToBeDecomposed: Float32Array, inOut: Vec3 = Vec3()): Vec3 {
            val m11 = matrixToBeDecomposed[0].toDouble()
            val m12 = matrixToBeDecomposed[1].toDouble()
            val m13 = matrixToBeDecomposed[2].toDouble()
            val m21 = matrixToBeDecomposed[4].toDouble()
            val m22 = matrixToBeDecomposed[5].toDouble()
            val m23 = matrixToBeDecomposed[6].toDouble()
            val m31 = matrixToBeDecomposed[8].toDouble()
            val m32 = matrixToBeDecomposed[9].toDouble()
            val m33 = matrixToBeDecomposed[10].toDouble()
            inOut[0] = Math.sqrt(m11 * m11 + m12 * m12 + m13 * m13)
            inOut[1] = Math.sqrt(m21 * m21 + m22 * m22 + m23 * m23)
            inOut[2] = Math.sqrt(m31 * m31 + m32 * m32 + m33 * m33)
            return inOut
        }

        /**
         * Returns a quaternion representing the rotational component
         *  of a transformation matrix. If a matrix is built with
         *  fromRotationTranslation, the returned quaternion will be the
         *  same as the quaternion originally supplied.
         * @param {Quat} inOut Quaternion to receive the rotation component
         * @param {Mat4} matrixToBeDecomposed Matrix to be decomposed (input)
         * @return {Quat} inOut
         */
        fun getRotation(matrixToBeDecomposed: Mat4, inOut: Quat = Quat()): Quat {
            // Algorithm taken from http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToQuaternion/index.htm
            val trace = matrixToBeDecomposed[0] + matrixToBeDecomposed[5] + matrixToBeDecomposed[10]
            val s: Double
            if (trace > 0) {
                s = Math.sqrt(trace + 1.0) * 2
                inOut[3] = 0.25 * s
                inOut[0] = (matrixToBeDecomposed[6] - matrixToBeDecomposed[9]) / s
                inOut[1] = (matrixToBeDecomposed[8] - matrixToBeDecomposed[2]) / s
                inOut[2] = (matrixToBeDecomposed[1] - matrixToBeDecomposed[4]) / s
            } else if ((matrixToBeDecomposed[0] > matrixToBeDecomposed[5]) && (matrixToBeDecomposed[0] > matrixToBeDecomposed[10])) {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[0] - matrixToBeDecomposed[5] - matrixToBeDecomposed[10]) * 2
                inOut[3] = (matrixToBeDecomposed[6] - matrixToBeDecomposed[9]) / s
                inOut[0] = 0.25 * s
                inOut[1] = (matrixToBeDecomposed[1] + matrixToBeDecomposed[4]) / s
                inOut[2] = (matrixToBeDecomposed[8] + matrixToBeDecomposed[2]) / s
            } else if (matrixToBeDecomposed[5] > matrixToBeDecomposed[10]) {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[5] - matrixToBeDecomposed[0] - matrixToBeDecomposed[10]) * 2
                inOut[3] = (matrixToBeDecomposed[8] - matrixToBeDecomposed[2]) / s
                inOut[0] = (matrixToBeDecomposed[1] + matrixToBeDecomposed[4]) / s
                inOut[1] = 0.25 * s
                inOut[2] = (matrixToBeDecomposed[6] + matrixToBeDecomposed[9]) / s
            } else {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[10] - matrixToBeDecomposed[0] - matrixToBeDecomposed[5]) * 2
                inOut[3] = (matrixToBeDecomposed[1] - matrixToBeDecomposed[4]) / s
                inOut[0] = (matrixToBeDecomposed[8] + matrixToBeDecomposed[2]) / s
                inOut[1] = (matrixToBeDecomposed[6] + matrixToBeDecomposed[9]) / s
                inOut[2] = 0.25 * s
            }
            return inOut
        }

        fun getRotation(matrixToBeDecomposed: DoubleArray, inOut: Quat = Quat()): Quat {
            // Algorithm taken from http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToQuaternion/index.htm
            val trace = matrixToBeDecomposed[0] + matrixToBeDecomposed[5] + matrixToBeDecomposed[10]
            val s: Double
            if (trace > 0) {
                s = Math.sqrt(trace + 1.0) * 2
                inOut[3] = 0.25 * s
                inOut[0] = (matrixToBeDecomposed[6] - matrixToBeDecomposed[9]) / s
                inOut[1] = (matrixToBeDecomposed[8] - matrixToBeDecomposed[2]) / s
                inOut[2] = (matrixToBeDecomposed[1] - matrixToBeDecomposed[4]) / s
            } else if ((matrixToBeDecomposed[0] > matrixToBeDecomposed[5]) && (matrixToBeDecomposed[0] > matrixToBeDecomposed[10])) {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[0] - matrixToBeDecomposed[5] - matrixToBeDecomposed[10]) * 2
                inOut[3] = (matrixToBeDecomposed[6] - matrixToBeDecomposed[9]) / s
                inOut[0] = 0.25 * s
                inOut[1] = (matrixToBeDecomposed[1] + matrixToBeDecomposed[4]) / s
                inOut[2] = (matrixToBeDecomposed[8] + matrixToBeDecomposed[2]) / s
            } else if (matrixToBeDecomposed[5] > matrixToBeDecomposed[10]) {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[5] - matrixToBeDecomposed[0] - matrixToBeDecomposed[10]) * 2
                inOut[3] = (matrixToBeDecomposed[8] - matrixToBeDecomposed[2]) / s
                inOut[0] = (matrixToBeDecomposed[1] + matrixToBeDecomposed[4]) / s
                inOut[1] = 0.25 * s
                inOut[2] = (matrixToBeDecomposed[6] + matrixToBeDecomposed[9]) / s
            } else {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[10] - matrixToBeDecomposed[0] - matrixToBeDecomposed[5]) * 2
                inOut[3] = (matrixToBeDecomposed[1] - matrixToBeDecomposed[4]) / s
                inOut[0] = (matrixToBeDecomposed[8] + matrixToBeDecomposed[2]) / s
                inOut[1] = (matrixToBeDecomposed[6] + matrixToBeDecomposed[9]) / s
                inOut[2] = 0.25 * s
            }
            return inOut
        }

        fun getRotation(matrixToBeDecomposed: Float32Array, inOut: Quat = Quat()): Quat {
            // Algorithm taken from http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToQuaternion/index.htm
            val trace = matrixToBeDecomposed[0] + matrixToBeDecomposed[5] + matrixToBeDecomposed[10]
            val s: Double
            if (trace > 0) {
                s = Math.sqrt(trace + 1.0) * 2
                inOut[3] = 0.25 * s
                inOut[0] = (matrixToBeDecomposed[6] - matrixToBeDecomposed[9]) / s
                inOut[1] = (matrixToBeDecomposed[8] - matrixToBeDecomposed[2]) / s
                inOut[2] = (matrixToBeDecomposed[1] - matrixToBeDecomposed[4]) / s
            } else if ((matrixToBeDecomposed[0] > matrixToBeDecomposed[5]) && (matrixToBeDecomposed[0] > matrixToBeDecomposed[10])) {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[0] - matrixToBeDecomposed[5] - matrixToBeDecomposed[10]) * 2
                inOut[3] = (matrixToBeDecomposed[6] - matrixToBeDecomposed[9]) / s
                inOut[0] = 0.25 * s
                inOut[1] = (matrixToBeDecomposed[1] + matrixToBeDecomposed[4]) / s
                inOut[2] = (matrixToBeDecomposed[8] + matrixToBeDecomposed[2]) / s
            } else if (matrixToBeDecomposed[5] > matrixToBeDecomposed[10]) {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[5] - matrixToBeDecomposed[0] - matrixToBeDecomposed[10]) * 2
                inOut[3] = (matrixToBeDecomposed[8] - matrixToBeDecomposed[2]) / s
                inOut[0] = (matrixToBeDecomposed[1] + matrixToBeDecomposed[4]) / s
                inOut[1] = 0.25 * s
                inOut[2] = (matrixToBeDecomposed[6] + matrixToBeDecomposed[9]) / s
            } else {
                s = Math.sqrt(1.0 + matrixToBeDecomposed[10] - matrixToBeDecomposed[0] - matrixToBeDecomposed[5]) * 2
                inOut[3] = (matrixToBeDecomposed[1] - matrixToBeDecomposed[4]) / s
                inOut[0] = (matrixToBeDecomposed[8] + matrixToBeDecomposed[2]) / s
                inOut[1] = (matrixToBeDecomposed[6] + matrixToBeDecomposed[9]) / s
                inOut[2] = 0.25 * s
            }
            return inOut
        }


        /**
         * Creates a matrix from a quaternion rotation, vector translation and vector scale
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.translate(dest, vec);
         *     let quatMat = Mat4.createDoubleArray();
         *     quat4.toMat4(Quat, quatMat);
         *     Mat4.multiply(dest, quatMat);
         *     Mat4.scale(dest, scale)
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {quat4} rotationQuat Rotation quaternion
         * @param {Vec3} translationVec3 Translation vector
         * @param {Vec3} scalingVec3 Scaling vector
         * @returns {Mat4} inOut
         */
        fun fromRotationTranslationScale(rotationQuat: Array<Double>, translationVec3: Vec3, scalingVec3: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            val sx = scalingVec3[0]
            val sy = scalingVec3[1]
            val sz = scalingVec3[2]
            inOut[0] = (1 - (yy + zz)) * sx
            inOut[1] = (xy + wz) * sx
            inOut[2] = (xz - wy) * sx
            inOut[3] = 0.0
            inOut[4] = (xy - wz) * sy
            inOut[5] = (1 - (xx + zz)) * sy
            inOut[6] = (yz + wx) * sy
            inOut[7] = 0.0
            inOut[8] = (xz + wy) * sz
            inOut[9] = (yz - wx) * sz
            inOut[10] = (1 - (xx + yy)) * sz
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotationTranslationScale(rotationQuat: Array<Double>, translationVec3: Vec3, scalingVec3: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            val sx = scalingVec3[0]
            val sy = scalingVec3[1]
            val sz = scalingVec3[2]
            inOut[0] = (1 - (yy + zz)) * sx
            inOut[1] = (xy + wz) * sx
            inOut[2] = (xz - wy) * sx
            inOut[3] = 0.0
            inOut[4] = (xy - wz) * sy
            inOut[5] = (1 - (xx + zz)) * sy
            inOut[6] = (yz + wx) * sy
            inOut[7] = 0.0
            inOut[8] = (xz + wy) * sz
            inOut[9] = (yz - wx) * sz
            inOut[10] = (1 - (xx + yy)) * sz
            inOut[11] = 0.0
            inOut[12] = translationVec3[0]
            inOut[13] = translationVec3[1]
            inOut[14] = translationVec3[2]
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotationTranslationScale(rotationQuat: Array<Double>, translationVec3: Vec3, scalingVec3: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            val sx = scalingVec3[0]
            val sy = scalingVec3[1]
            val sz = scalingVec3[2]
            inOut[0] = ((1 - (yy + zz)) * sx).toFloat()
            inOut[1] = ((xy + wz) * sx).toFloat()
            inOut[2] = ((xz - wy) * sx).toFloat()
            inOut[3] = 0.0f
            inOut[4] = ((xy - wz) * sy).toFloat()
            inOut[5] = ((1 - (xx + zz)) * sy).toFloat()
            inOut[6] = ((yz + wx) * sy).toFloat()
            inOut[7] = 0.0f
            inOut[8] = ((xz + wy) * sz).toFloat()
            inOut[9] = ((yz - wx) * sz).toFloat()
            inOut[10] = ((1 - (xx + yy)) * sz).toFloat()
            inOut[11] = 0.0f
            inOut[12] = translationVec3[0].toFloat()
            inOut[13] = translationVec3[1].toFloat()
            inOut[14] = translationVec3[2].toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Creates a matrix from a quaternion rotation, vector translation and vector scale, rotating and scaling around the given origin
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat4.identityDoubleArray(dest);
         *     Mat4.translate(dest, vec);
         *     Mat4.translate(dest, origin);
         *     let quatMat = Mat4.createDoubleArray();
         *     quat4.toMat4(Quat, quatMat);
         *     Mat4.multiply(dest, quatMat);
         *     Mat4.scale(dest, scale)
         *     Mat4.translate(dest, negativeOrigin);
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {quat4} rotationQuat Rotation quaternion
         * @param {Vec3} translationVec3 Translation vector
         * @param {Vec3} scalingVec3 Scaling vector
         * @param {Vec3} originVec3ToScaleRotateAround The origin vector around which to scale and rotate
         * @returns {Mat4} inOut
         */
        fun fromRotationTranslationScaleOrigin(rotationQuat: Quat, translationVec3: Vec3, scalingVec3: Vec3, originVec3ToScaleRotateAround: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            val sx = scalingVec3[0]
            val sy = scalingVec3[1]
            val sz = scalingVec3[2]
            val ox = originVec3ToScaleRotateAround[0]
            val oy = originVec3ToScaleRotateAround[1]
            val oz = originVec3ToScaleRotateAround[2]
            inOut[0] = (1 - (yy + zz)) * sx
            inOut[1] = (xy + wz) * sx
            inOut[2] = (xz - wy) * sx
            inOut[3] = 0.0
            inOut[4] = (xy - wz) * sy
            inOut[5] = (1 - (xx + zz)) * sy
            inOut[6] = (yz + wx) * sy
            inOut[7] = 0.0
            inOut[8] = (xz + wy) * sz
            inOut[9] = (yz - wx) * sz
            inOut[10] = (1 - (xx + yy)) * sz
            inOut[11] = 0.0
            inOut[12] = translationVec3[0] + ox - (inOut[0] * ox + inOut[4] * oy + inOut[8] * oz)
            inOut[13] = translationVec3[1] + oy - (inOut[1] * ox + inOut[5] * oy + inOut[9] * oz)
            inOut[14] = translationVec3[2] + oz - (inOut[2] * ox + inOut[6] * oy + inOut[10] * oz)
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotationTranslationScaleOrigin(rotationQuat: Quat, translationVec3: Vec3, scalingVec3: Vec3, originVec3ToScaleRotateAround: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            val sx = scalingVec3[0]
            val sy = scalingVec3[1]
            val sz = scalingVec3[2]
            val ox = originVec3ToScaleRotateAround[0]
            val oy = originVec3ToScaleRotateAround[1]
            val oz = originVec3ToScaleRotateAround[2]
            inOut[0] = (1 - (yy + zz)) * sx
            inOut[1] = (xy + wz) * sx
            inOut[2] = (xz - wy) * sx
            inOut[3] = 0.0
            inOut[4] = (xy - wz) * sy
            inOut[5] = (1 - (xx + zz)) * sy
            inOut[6] = (yz + wx) * sy
            inOut[7] = 0.0
            inOut[8] = (xz + wy) * sz
            inOut[9] = (yz - wx) * sz
            inOut[10] = (1 - (xx + yy)) * sz
            inOut[11] = 0.0
            inOut[12] = translationVec3[0] + ox - (inOut[0] * ox + inOut[4] * oy + inOut[8] * oz)
            inOut[13] = translationVec3[1] + oy - (inOut[1] * ox + inOut[5] * oy + inOut[9] * oz)
            inOut[14] = translationVec3[2] + oz - (inOut[2] * ox + inOut[6] * oy + inOut[10] * oz)
            inOut[15] = 1.0
            return inOut
        }

        fun fromRotationTranslationScaleOrigin(rotationQuat: Quat, translationVec3: Vec3, scalingVec3: Vec3, originVec3ToScaleRotateAround: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            // Quaternion math
            val x = rotationQuat[0]
            val y = rotationQuat[1]
            val z = rotationQuat[2]
            val w = rotationQuat[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            val sx = scalingVec3[0]
            val sy = scalingVec3[1]
            val sz = scalingVec3[2]
            val ox = originVec3ToScaleRotateAround[0]
            val oy = originVec3ToScaleRotateAround[1]
            val oz = originVec3ToScaleRotateAround[2]
            inOut[0] = ((1 - (yy + zz)) * sx).toFloat()
            inOut[1] = ((xy + wz) * sx).toFloat()
            inOut[2] = ((xz - wy) * sx).toFloat()
            inOut[3] = 0.0f
            inOut[4] = ((xy - wz) * sy).toFloat()
            inOut[5] = ((1 - (xx + zz)) * sy).toFloat()
            inOut[6] = ((yz + wx) * sy).toFloat()
            inOut[7] = 0.0f
            inOut[8] = ((xz + wy) * sz).toFloat()
            inOut[9] = ((yz - wx) * sz).toFloat()
            inOut[10] = ((1 - (xx + yy)) * sz).toFloat()
            inOut[11] = 0.0f
            inOut[12] = (translationVec3[0] + ox - (inOut[0] * ox + inOut[4] * oy + inOut[8] * oz)).toFloat()
            inOut[13] = (translationVec3[1] + oy - (inOut[1] * ox + inOut[5] * oy + inOut[9] * oz)).toFloat()
            inOut[14] = (translationVec3[2] + oz - (inOut[2] * ox + inOut[6] * oy + inOut[10] * oz)).toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Calculates a 4x4 matrix from the given quaternion
         *
         * @param {Mat4} inOut Mat4 receiving operation result
         * @param {Quat} quatToCreateMatrixFrom Quaternion to createDoubleArray matrix from
         *
         * @returns {Mat4} inOut
         */
        fun fromQuat(quatToCreateMatrixFrom: Quat, inOut: Mat4 = Mat4()): Mat4 {
            val x = quatToCreateMatrixFrom[0]
            val y = quatToCreateMatrixFrom[1]
            val z = quatToCreateMatrixFrom[2]
            val w = quatToCreateMatrixFrom[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val yx = y * x2
            val yy = y * y2
            val zx = z * x2
            val zy = z * y2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            inOut[0] = 1 - yy - zz
            inOut[1] = yx + wz
            inOut[2] = zx - wy
            inOut[3] = 0.0
            inOut[4] = yx - wz
            inOut[5] = 1 - xx - zz
            inOut[6] = zy + wx
            inOut[7] = 0.0
            inOut[8] = zx + wy
            inOut[9] = zy - wx
            inOut[10] = 1 - xx - yy
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromQuat(quatToCreateMatrixFrom: Quat, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val x = quatToCreateMatrixFrom[0]
            val y = quatToCreateMatrixFrom[1]
            val z = quatToCreateMatrixFrom[2]
            val w = quatToCreateMatrixFrom[3]
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val yx = y * x2
            val yy = y * y2
            val zx = z * x2
            val zy = z * y2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            inOut[0] = 1 - yy - zz
            inOut[1] = yx + wz
            inOut[2] = zx - wy
            inOut[3] = 0.0
            inOut[4] = yx - wz
            inOut[5] = 1 - xx - zz
            inOut[6] = zy + wx
            inOut[7] = 0.0
            inOut[8] = zx + wy
            inOut[9] = zy - wx
            inOut[10] = 1 - xx - yy
            inOut[11] = 0.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = 0.0
            inOut[15] = 1.0
            return inOut
        }

        fun fromQuat(quatToCreateMatrixFrom: Quat, inOut: Float32Array = Float32Array(16)): Float32Array {
            val x = quatToCreateMatrixFrom[0].toFloat()
            val y = quatToCreateMatrixFrom[1].toFloat()
            val z = quatToCreateMatrixFrom[2].toFloat()
            val w = quatToCreateMatrixFrom[3].toFloat()
            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val yx = y * x2
            val yy = y * y2
            val zx = z * x2
            val zy = z * y2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            inOut[0] = 1 - yy - zz
            inOut[1] = yx + wz
            inOut[2] = zx - wy
            inOut[3] = 0.0f
            inOut[4] = yx - wz
            inOut[5] = 1 - xx - zz
            inOut[6] = zy + wx
            inOut[7] = 0.0f
            inOut[8] = zx + wy
            inOut[9] = zy - wx
            inOut[10] = 1 - xx - yy
            inOut[11] = 0.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = 0.0f
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Generates a frustum matrix with the given bounds
         *
         * @param {Mat4} inOut Mat4 frustum matrix will be written into
         * @param {Number} left Left bound of the frustum
         * @param {Number} right Right bound of the frustum
         * @param {Number} bottom Bottom bound of the frustum
         * @param {Number} top Top bound of the frustum
         * @param {Number} near Near bound of the frustum
         * @param {Number} far Far bound of the frustum
         * @returns {Mat4} inOut
         */
        fun frustum(left: Double, right: Double, bottom: Double, top: Double, near: Double, far: Double, inOut: Mat4 = Mat4()): Mat4 {
            val rl = 1 / (right - left)
            val tb = 1 / (top - bottom)
            val nf = 1 / (near - far)
            inOut[0] = (near * 2) * rl
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = (near * 2) * tb
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = (right + left) * rl
            inOut[9] = (top + bottom) * tb
            inOut[10] = (far + near) * nf
            inOut[11] = -1.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = (far * near * 2) * nf
            inOut[15] = 0.0
            return inOut
        }

        fun frustum(left: Double, right: Double, bottom: Double, top: Double, near: Double, far: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val rl = 1 / (right - left)
            val tb = 1 / (top - bottom)
            val nf = 1 / (near - far)
            inOut[0] = (near * 2) * rl
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = (near * 2) * tb
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = (right + left) * rl
            inOut[9] = (top + bottom) * tb
            inOut[10] = (far + near) * nf
            inOut[11] = -1.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = (far * near * 2) * nf
            inOut[15] = 0.0
            return inOut
        }

        fun frustum(left: Double, right: Double, bottom: Double, top: Double, near: Double, far: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val rl = 1 / (right - left)
            val tb = 1 / (top - bottom)
            val nf = 1 / (near - far)
            inOut[0] = ((near * 2) * rl).toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = ((near * 2) * tb).toFloat()
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = ((right + left) * rl).toFloat()
            inOut[9] = ((top + bottom) * tb).toFloat()
            inOut[10] = ((far + near) * nf).toFloat()
            inOut[11] = -1.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = ((far * near * 2) * nf).toFloat()
            inOut[15] = 0.0f
            return inOut
        }

        /**
         * Generates a perspective projection matrix with the given bounds
         *
         * @param {Mat4} inOut Mat4 frustum matrix will be written into
         * @param {number} verticalFieldOfViewInRad Vertical field of view in radians
         * @param {number} aspectRatio Aspect ratio. typically viewport width/height
         * @param {number} nearBound Near bound of the frustum
         * @param {number} farBound Far bound of the frustum
         * @returns {Mat4} inOut
         */
        fun perspective(verticalFieldOfViewInRad: Double, aspectRatio: Double, nearBound: Double, farBound: Double, inOut: Mat4 = Mat4()): Mat4 {
            val f = 1.0 / Math.tan(verticalFieldOfViewInRad / 2)
            val nf = 1 / (nearBound - farBound)
            inOut[0] = f / aspectRatio
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = f
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = (farBound + nearBound) * nf
            inOut[11] = -1.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = (2 * farBound * nearBound) * nf
            inOut[15] = 0.0
            return inOut
        }

        fun perspective(verticalFieldOfViewInRad: Double, aspectRatio: Double, nearBound: Double, farBound: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val f = 1.0 / Math.tan(verticalFieldOfViewInRad / 2)
            val nf = 1 / (nearBound - farBound)
            inOut[0] = f / aspectRatio
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = f
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = (farBound + nearBound) * nf
            inOut[11] = -1.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = (2 * farBound * nearBound) * nf
            inOut[15] = 0.0
            return inOut
        }

        fun perspective(verticalFieldOfViewInRad: Double, aspectRatio: Double, nearBound: Double, farBound: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val f = (1.0 / Math.tan(verticalFieldOfViewInRad / 2)).toFloat()
            val nf = 1 / (nearBound - farBound)
            inOut[0] = (f / aspectRatio).toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = f
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = ((farBound + nearBound) * nf).toFloat()
            inOut[11] = -1.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = ((2 * farBound * nearBound) * nf).toFloat()
            inOut[15] = 0.0f
            return inOut
        }

        /**
         * Generates a perspective projection matrix with the given field of view.
         * this.matrix is primarily useful for generating projection matrices to be used
         * with the still experiemental WebVR API.
         *
         * @param {Mat4} inOut Mat4 frustum matrix will be written into
         * @param {Object} fieldOfViewObject Object containing the following values: upDegrees, downDegrees, leftDegrees, rightDegrees
         * @param {number} nearBound Near bound of the frustum
         * @param {number} farBound Far bound of the frustum
         * @returns {Mat4} inOut
         */
        fun perspectiveFromFieldOfView(fieldOfViewObject: FieldOfView, nearBound: Double, farBound: Double, inOut: Mat4 = Mat4()): Mat4 {
            val upTan = Math.tan(fieldOfViewObject.upDegrees * Math.PI / 180.0)
            val downTan = Math.tan(fieldOfViewObject.downDegrees * Math.PI / 180.0)
            val leftTan = Math.tan(fieldOfViewObject.leftDegrees * Math.PI / 180.0)
            val rightTan = Math.tan(fieldOfViewObject.rightDegrees * Math.PI / 180.0)
            val xScale = 2.0 / (leftTan + rightTan)
            val yScale = 2.0 / (upTan + downTan)
            inOut[0] = xScale
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = yScale
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = -(leftTan - rightTan) * xScale * 0.5
            inOut[9] = (upTan - downTan) * yScale * 0.5
            inOut[10] = farBound / (nearBound - farBound)
            inOut[11] = -1.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = (farBound * nearBound) / (nearBound - farBound)
            inOut[15] = 0.0
            return inOut
        }

        fun perspectiveFromFieldOfView(fieldOfViewObject: FieldOfView, nearBound: Double, farBound: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val upTan = Math.tan(fieldOfViewObject.upDegrees * Math.PI / 180.0)
            val downTan = Math.tan(fieldOfViewObject.downDegrees * Math.PI / 180.0)
            val leftTan = Math.tan(fieldOfViewObject.leftDegrees * Math.PI / 180.0)
            val rightTan = Math.tan(fieldOfViewObject.rightDegrees * Math.PI / 180.0)
            val xScale = 2.0 / (leftTan + rightTan)
            val yScale = 2.0 / (upTan + downTan)
            inOut[0] = xScale
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = yScale
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = -(leftTan - rightTan) * xScale * 0.5
            inOut[9] = (upTan - downTan) * yScale * 0.5
            inOut[10] = farBound / (nearBound - farBound)
            inOut[11] = -1.0
            inOut[12] = 0.0
            inOut[13] = 0.0
            inOut[14] = (farBound * nearBound) / (nearBound - farBound)
            inOut[15] = 0.0
            return inOut
        }

        fun perspectiveFromFieldOfView(fieldOfViewObject: FieldOfView, nearBound: Double, farBound: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val upTan = Math.tan(fieldOfViewObject.upDegrees * Math.PI / 180.0)
            val downTan = Math.tan(fieldOfViewObject.downDegrees * Math.PI / 180.0)
            val leftTan = Math.tan(fieldOfViewObject.leftDegrees * Math.PI / 180.0)
            val rightTan = Math.tan(fieldOfViewObject.rightDegrees * Math.PI / 180.0)
            val xScale = 2.0 / (leftTan + rightTan)
            val yScale = 2.0 / (upTan + downTan)
            inOut[0] = xScale.toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = yScale.toFloat()
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = -((leftTan - rightTan) * xScale * 0.5).toFloat()
            inOut[9] = ((upTan - downTan) * yScale * 0.5).toFloat()
            inOut[10] = (farBound / (nearBound - farBound)).toFloat()
            inOut[11] = -1.0f
            inOut[12] = 0.0f
            inOut[13] = 0.0f
            inOut[14] = ((farBound * nearBound) / (nearBound - farBound)).toFloat()
            inOut[15] = 0.0f
            return inOut
        }

        /**
         * Generates a orthogonal projection matrix with the given bounds
         *
         * @param {Mat4} inOut Mat4 frustum matrix will be written into
         * @param {number} leftBound Left bound of the frustum
         * @param {number} rightBound Right bound of the frustum
         * @param {number} bottomBound Bottom bound of the frustum
         * @param {number} topBound Top bound of the frustum
         * @param {number} nearBound Near bound of the frustum
         * @param {number} farBound Far bound of the frustum
         * @returns {Mat4} inOut
         */
        fun ortho(leftBound: Double, rightBound: Double, bottomBound: Double, topBound: Double, nearBound: Double, farBound: Double, inOut: Mat4 = Mat4()): Mat4 {
            val lr = 1 / (leftBound - rightBound)
            val bt = 1 / (bottomBound - topBound)
            val nf = 1 / (nearBound - farBound)
            inOut[0] = -2 * lr
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = -2 * bt
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 2 * nf
            inOut[11] = 0.0
            inOut[12] = (leftBound + rightBound) * lr
            inOut[13] = (topBound + bottomBound) * bt
            inOut[14] = (farBound + nearBound) * nf
            inOut[15] = 1.0
            return inOut
        }

        fun ortho(leftBound: Double, rightBound: Double, bottomBound: Double, topBound: Double, nearBound: Double, farBound: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val lr = 1 / (leftBound - rightBound)
            val bt = 1 / (bottomBound - topBound)
            val nf = 1 / (nearBound - farBound)
            inOut[0] = -2 * lr
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 0.0
            inOut[4] = 0.0
            inOut[5] = -2 * bt
            inOut[6] = 0.0
            inOut[7] = 0.0
            inOut[8] = 0.0
            inOut[9] = 0.0
            inOut[10] = 2 * nf
            inOut[11] = 0.0
            inOut[12] = (leftBound + rightBound) * lr
            inOut[13] = (topBound + bottomBound) * bt
            inOut[14] = (farBound + nearBound) * nf
            inOut[15] = 1.0
            return inOut
        }

        fun ortho(leftBound: Double, rightBound: Double, bottomBound: Double, topBound: Double, nearBound: Double, farBound: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val lr = 1 / (leftBound - rightBound)
            val bt = 1 / (bottomBound - topBound)
            val nf = 1 / (nearBound - farBound)
            inOut[0] = (-2 * lr).toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 0.0f
            inOut[4] = 0.0f
            inOut[5] = (-2 * bt).toFloat()
            inOut[6] = 0.0f
            inOut[7] = 0.0f
            inOut[8] = 0.0f
            inOut[9] = 0.0f
            inOut[10] = (2 * nf).toFloat()
            inOut[11] = 0.0f
            inOut[12] = ((leftBound + rightBound) * lr).toFloat()
            inOut[13] = ((topBound + bottomBound) * bt).toFloat()
            inOut[14] = ((farBound + nearBound) * nf).toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Generates a look-at matrix with the given eye position, focal point, and up axis
         *
         * @param {Mat4} out Mat4 frustum matrix will be written into
         * @param {Vec3} eye Position of the viewer
         * @param {Vec3} center Point the viewer is looking at
         * @param {Vec3} up Vec3 pointing up
         * @returns {Mat4} out
         */
        fun lookAt(eye: Vec3, center: Vec3, up: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            val eyex = eye[0]
            val eyey = eye[1]
            val eyez = eye[2]
            val upx = up[0]
            val upy = up[1]
            val upz = up[2]
            val centerx = center[0]
            val centery = center[1]
            val centerz = center[2]
            if (Math.abs(eyex - centerx) < EPSILON &&
                    Math.abs(eyey - centery) < EPSILON &&
                    Math.abs(eyez - centerz) < EPSILON) {

                identity(inOut)
            }
            var z0 = (eyex - centerx)
            var z1 = (eyey - centery)
            var z2 = (eyez - centerz)
            var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
            z0 *= len
            z1 *= len
            z2 *= len
            var x0 = upy * z2 - upz * z1
            var x1 = upz * z0 - upx * z2
            var x2 = upx * z1 - upy * z0
            len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
            if (len == 0.0) {
                x0 = 0.0
                x1 = 0.0
                x2 = 0.0
            } else {
                len = 1 / len
                x0 *= len
                x1 *= len
                x2 *= len
            }
            var y0 = z1 * x2 - z2 * x1
            var y1 = z2 * x0 - z0 * x2
            var y2 = z0 * x1 - z1 * x0
            len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
            if (len == 0.0) {
                y0 = 0.0
                y1 = 0.0
                y2 = 0.0
            } else {
                len = 1 / len
                y0 *= len
                y1 *= len
                y2 *= len
            }
            inOut[0] = x0
            inOut[1] = y0
            inOut[2] = z0
            inOut[3] = 0.0
            inOut[4] = x1
            inOut[5] = y1
            inOut[6] = z1
            inOut[7] = 0.0
            inOut[8] = x2
            inOut[9] = y2
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = -x0 * eyex + x1 * eyey + x2 * eyez
            inOut[13] = -y0 * eyex + y1 * eyey + y2 * eyez
            inOut[14] = -z0 * eyex + z1 * eyey + z2 * eyez
            inOut[15] = 1.0
            return inOut
        }

        fun lookAt(eye: Array<Int>, center: Array<Int>, up: Array<Int>, inOut: Mat4 = Mat4()): Mat4 {
            val eyex = eye[0].toDouble()
            val eyey = eye[1].toDouble()
            val eyez = eye[2].toDouble()
            val upx = up[0].toDouble()
            val upy = up[1].toDouble()
            val upz = up[2].toDouble()
            val centerx = center[0].toDouble()
            val centery = center[1].toDouble()
            val centerz = center[2].toDouble()
            if (Math.abs(eyex - centerx) < EPSILON &&
                    Math.abs(eyey - centery) < EPSILON &&
                    Math.abs(eyez - centerz) < EPSILON) {

                identity(inOut)
            }
            var z0 = (eyex - centerx)
            var z1 = (eyey - centery)
            var z2 = (eyez - centerz)
            var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
            z0 *= len
            z1 *= len
            z2 *= len
            var x0 = upy * z2 - upz * z1
            var x1 = upz * z0 - upx * z2
            var x2 = upx * z1 - upy * z0
            len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
            if (len == 0.0) {
                x0 = 0.0
                x1 = 0.0
                x2 = 0.0
            } else {
                len = 1 / len
                x0 *= len
                x1 *= len
                x2 *= len
            }
            var y0 = z1 * x2 - z2 * x1
            var y1 = z2 * x0 - z0 * x2
            var y2 = z0 * x1 - z1 * x0
            len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
            if (len == 0.0) {
                y0 = 0.0
                y1 = 0.0
                y2 = 0.0
            } else {
                len = 1 / len
                y0 *= len
                y1 *= len
                y2 *= len
            }
            inOut[0] = x0
            inOut[1] = y0
            inOut[2] = z0
            inOut[3] = 0.0
            inOut[4] = x1
            inOut[5] = y1
            inOut[6] = z1
            inOut[7] = 0.0
            inOut[8] = x2
            inOut[9] = y2
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = -x0 * eyex + x1 * eyey + x2 * eyez
            inOut[13] = -y0 * eyex + y1 * eyey + y2 * eyez
            inOut[14] = -z0 * eyex + z1 * eyey + z2 * eyez
            inOut[15] = 1.0
            return inOut
        }

        fun lookAt(eye: Vec3, center: Vec3, up: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val eyex = eye[0]
            val eyey = eye[1]
            val eyez = eye[2]
            val upx = up[0]
            val upy = up[1]
            val upz = up[2]
            val centerx = center[0]
            val centery = center[1]
            val centerz = center[2]
            if (Math.abs(eyex - centerx) < EPSILON &&
                    Math.abs(eyey - centery) < EPSILON &&
                    Math.abs(eyez - centerz) < EPSILON) {

                identityDoubleArray(inOut)
            }
            var z0 = (eyex - centerx)
            var z1 = (eyey - centery)
            var z2 = (eyez - centerz)
            var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
            z0 *= len
            z1 *= len
            z2 *= len
            var x0 = upy * z2 - upz * z1
            var x1 = upz * z0 - upx * z2
            var x2 = upx * z1 - upy * z0
            len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
            if (len == 0.0) {
                x0 = 0.0
                x1 = 0.0
                x2 = 0.0
            } else {
                len = 1 / len
                x0 *= len
                x1 *= len
                x2 *= len
            }
            var y0 = z1 * x2 - z2 * x1
            var y1 = z2 * x0 - z0 * x2
            var y2 = z0 * x1 - z1 * x0
            len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
            if (len == 0.0) {
                y0 = 0.0
                y1 = 0.0
                y2 = 0.0
            } else {
                len = 1 / len
                y0 *= len
                y1 *= len
                y2 *= len
            }
            inOut[0] = x0
            inOut[1] = y0
            inOut[2] = z0
            inOut[3] = 0.0
            inOut[4] = x1
            inOut[5] = y1
            inOut[6] = z1
            inOut[7] = 0.0
            inOut[8] = x2
            inOut[9] = y2
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = -x0 * eyex + x1 * eyey + x2 * eyez
            inOut[13] = -y0 * eyex + y1 * eyey + y2 * eyez
            inOut[14] = -z0 * eyex + z1 * eyey + z2 * eyez
            inOut[15] = 1.0
            return inOut
        }

        fun lookAt(eye: Array<Int>, center: Array<Int>, up: Array<Int>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val eyex = eye[0].toDouble()
            val eyey = eye[1].toDouble()
            val eyez = eye[2].toDouble()
            val upx = up[0].toDouble()
            val upy = up[1].toDouble()
            val upz = up[2].toDouble()
            val centerx = center[0].toDouble()
            val centery = center[1].toDouble()
            val centerz = center[2].toDouble()
            if (Math.abs(eyex - centerx) < EPSILON &&
                    Math.abs(eyey - centery) < EPSILON &&
                    Math.abs(eyez - centerz) < EPSILON) {

                identityDoubleArray(inOut)
            }
            var z0 = (eyex - centerx)
            var z1 = (eyey - centery)
            var z2 = (eyez - centerz)
            var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
            z0 *= len
            z1 *= len
            z2 *= len
            var x0 = upy * z2 - upz * z1
            var x1 = upz * z0 - upx * z2
            var x2 = upx * z1 - upy * z0
            len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
            if (len == 0.0) {
                x0 = 0.0
                x1 = 0.0
                x2 = 0.0
            } else {
                len = 1 / len
                x0 *= len
                x1 *= len
                x2 *= len
            }
            var y0 = z1 * x2 - z2 * x1
            var y1 = z2 * x0 - z0 * x2
            var y2 = z0 * x1 - z1 * x0
            len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
            if (len == 0.0) {
                y0 = 0.0
                y1 = 0.0
                y2 = 0.0
            } else {
                len = 1 / len
                y0 *= len
                y1 *= len
                y2 *= len
            }
            inOut[0] = x0
            inOut[1] = y0
            inOut[2] = z0
            inOut[3] = 0.0
            inOut[4] = x1
            inOut[5] = y1
            inOut[6] = z1
            inOut[7] = 0.0
            inOut[8] = x2
            inOut[9] = y2
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = -x0 * eyex + x1 * eyey + x2 * eyez
            inOut[13] = -y0 * eyex + y1 * eyey + y2 * eyez
            inOut[14] = -z0 * eyex + z1 * eyey + z2 * eyez
            inOut[15] = 1.0
            return inOut
        }

        fun lookAt(eye: Vec3, center: Vec3, up: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            val eyex = eye[0]
            val eyey = eye[1]
            val eyez = eye[2]
            val upx = up[0]
            val upy = up[1]
            val upz = up[2]
            val centerx = center[0]
            val centery = center[1]
            val centerz = center[2]
            if (Math.abs(eyex - centerx) < EPSILON &&
                    Math.abs(eyey - centery) < EPSILON &&
                    Math.abs(eyez - centerz) < EPSILON) {

                identityFloat32Array(inOut)
            }
            var z0 = (eyex - centerx)
            var z1 = (eyey - centery)
            var z2 = (eyez - centerz)
            var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
            z0 *= len
            z1 *= len
            z2 *= len
            var x0 = upy * z2 - upz * z1
            var x1 = upz * z0 - upx * z2
            var x2 = upx * z1 - upy * z0
            len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
            if (len == 0.0) {
                x0 = 0.0
                x1 = 0.0
                x2 = 0.0
            } else {
                len = 1 / len
                x0 *= len
                x1 *= len
                x2 *= len
            }
            var y0 = z1 * x2 - z2 * x1
            var y1 = z2 * x0 - z0 * x2
            var y2 = z0 * x1 - z1 * x0
            len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
            if (len == 0.0) {
                y0 = 0.0
                y1 = 0.0
                y2 = 0.0
            } else {
                len = 1 / len
                y0 *= len
                y1 *= len
                y2 *= len
            }
            inOut[0] = x0.toFloat()
            inOut[1] = y0.toFloat()
            inOut[2] = z0.toFloat()
            inOut[3] = 0.0f
            inOut[4] = x1.toFloat()
            inOut[5] = y1.toFloat()
            inOut[6] = z1.toFloat()
            inOut[7] = 0.0f
            inOut[8] = x2.toFloat()
            inOut[9] = y2.toFloat()
            inOut[10] = z2.toFloat()
            inOut[11] = 0.0f
            inOut[12] = -(x0 * eyex + x1 * eyey + x2 * eyez).toFloat()
            inOut[13] = -(y0 * eyex + y1 * eyey + y2 * eyez).toFloat()
            inOut[14] = -(z0 * eyex + z1 * eyey + z2 * eyez).toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        fun lookAt(eye: Array<Int>, center: Array<Int>, up: Array<Int>, inOut: Float32Array = Float32Array(16)): Float32Array {
            val eyex = eye[0].toDouble()
            val eyey = eye[1].toDouble()
            val eyez = eye[2].toDouble()
            val upx = up[0].toDouble()
            val upy = up[1].toDouble()
            val upz = up[2].toDouble()
            val centerx = center[0].toDouble()
            val centery = center[1].toDouble()
            val centerz = center[2].toDouble()
            if (Math.abs(eyex - centerx) < EPSILON &&
                    Math.abs(eyey - centery) < EPSILON &&
                    Math.abs(eyez - centerz) < EPSILON) {

                identityFloat32Array(inOut)
            }
            var z0 = (eyex - centerx)
            var z1 = (eyey - centery)
            var z2 = (eyez - centerz)
            var len = 1 / Math.sqrt(z0 * z0 + z1 * z1 + z2 * z2)
            z0 *= len
            z1 *= len
            z2 *= len
            var x0 = upy * z2 - upz * z1
            var x1 = upz * z0 - upx * z2
            var x2 = upx * z1 - upy * z0
            len = Math.sqrt(x0 * x0 + x1 * x1 + x2 * x2)
            if (len == 0.0) {
                x0 = 0.0
                x1 = 0.0
                x2 = 0.0
            } else {
                len = 1 / len
                x0 *= len
                x1 *= len
                x2 *= len
            }
            var y0 = z1 * x2 - z2 * x1
            var y1 = z2 * x0 - z0 * x2
            var y2 = z0 * x1 - z1 * x0
            len = Math.sqrt(y0 * y0 + y1 * y1 + y2 * y2)
            if (len == 0.0) {
                y0 = 0.0
                y1 = 0.0
                y2 = 0.0
            } else {
                len = 1 / len
                y0 *= len
                y1 *= len
                y2 *= len
            }
            inOut[0] = x0.toFloat()
            inOut[1] = y0.toFloat()
            inOut[2] = z0.toFloat()
            inOut[3] = 0.0f
            inOut[4] = x1.toFloat()
            inOut[5] = y1.toFloat()
            inOut[6] = z1.toFloat()
            inOut[7] = 0.0f
            inOut[8] = x2.toFloat()
            inOut[9] = y2.toFloat()
            inOut[10] = z2.toFloat()
            inOut[11] = 0.0f
            inOut[12] = -(x0 * eyex + x1 * eyey + x2 * eyez).toFloat()
            inOut[13] = -(y0 * eyex + y1 * eyey + y2 * eyez).toFloat()
            inOut[14] = -(z0 * eyex + z1 * eyey + z2 * eyez).toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Generates a matrix that makes something look at something else.
         *
         * @param {Mat4} inOut Mat4 frustum matrix will be written into
         * @param {Vec3} eye Position of the viewer
         * @param {Vec3} center Point the viewer is looking at
         * @param {Vec3} up Vec3 pointing up
         * @returns {Mat4} inOut
         */
        fun targetTo(eye: Vec3, target: Vec3, up: Vec3, inOut: Mat4 = Mat4()): Mat4 {
            val eyex = eye[0]
            val eyey = eye[1]
            val eyez = eye[2]
            val upx = up[0]
            val upy = up[1]
            val upz = up[2]
            var z0 = eyex - target[0]
            var z1 = eyey - target[1]
            var z2 = eyez - target[2]
            var len = z0 * z0 + z1 * z1 + z2 * z2
            if (len > 0) {
                len = 1 / Math.sqrt(len)
                z0 *= len
                z1 *= len
                z2 *= len
            }
            val x0 = upy * z2 - upz * z1
            val x1 = upz * z0 - upx * z2
            val x2 = upx * z1 - upy * z0
            inOut[0] = x0
            inOut[1] = x1
            inOut[2] = x2
            inOut[3] = 0.0
            inOut[4] = z1 * x2 - z2 * x1
            inOut[5] = z2 * x0 - z0 * x2
            inOut[6] = z0 * x1 - z1 * x0
            inOut[7] = 0.0
            inOut[8] = z0
            inOut[9] = z1
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = eyex
            inOut[13] = eyey
            inOut[14] = eyez
            inOut[15] = 1.0
            return inOut
        }

        fun targetTo(eye: Array<Int>, target: Array<Int>, up: Array<Int>, inOut: Mat4 = Mat4()): Mat4 {
            val eyex = eye[0].toDouble()
            val eyey = eye[1].toDouble()
            val eyez = eye[2].toDouble()
            val upx = up[0].toDouble()
            val upy = up[1].toDouble()
            val upz = up[2].toDouble()
            var z0 = eyex - target[0].toDouble()
            var z1 = eyey - target[1].toDouble()
            var z2 = eyez - target[2].toDouble()
            var len = z0 * z0 + z1 * z1 + z2 * z2
            if (len > 0) {
                len = 1 / Math.sqrt(len)
                z0 *= len
                z1 *= len
                z2 *= len
            }
            val x0 = upy * z2 - upz * z1
            val x1 = upz * z0 - upx * z2
            val x2 = upx * z1 - upy * z0
            inOut[0] = x0
            inOut[1] = x1
            inOut[2] = x2
            inOut[3] = 0.0
            inOut[4] = z1 * x2 - z2 * x1
            inOut[5] = z2 * x0 - z0 * x2
            inOut[6] = z0 * x1 - z1 * x0
            inOut[7] = 0.0
            inOut[8] = z0
            inOut[9] = z1
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = eyex
            inOut[13] = eyey
            inOut[14] = eyez
            inOut[15] = 1.0
            return inOut
        }

        fun targetTo(eye: Vec3, target: Vec3, up: Vec3, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val eyex = eye[0]
            val eyey = eye[1]
            val eyez = eye[2]
            val upx = up[0]
            val upy = up[1]
            val upz = up[2]
            var z0 = eyex - target[0]
            var z1 = eyey - target[1]
            var z2 = eyez - target[2]
            var len = z0 * z0 + z1 * z1 + z2 * z2
            if (len > 0) {
                len = 1 / Math.sqrt(len)
                z0 *= len
                z1 *= len
                z2 *= len
            }
            val x0 = upy * z2 - upz * z1
            val x1 = upz * z0 - upx * z2
            val x2 = upx * z1 - upy * z0
            inOut[0] = x0
            inOut[1] = x1
            inOut[2] = x2
            inOut[3] = 0.0
            inOut[4] = z1 * x2 - z2 * x1
            inOut[5] = z2 * x0 - z0 * x2
            inOut[6] = z0 * x1 - z1 * x0
            inOut[7] = 0.0
            inOut[8] = z0
            inOut[9] = z1
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = eyex
            inOut[13] = eyey
            inOut[14] = eyez
            inOut[15] = 1.0
            return inOut
        }

        fun targetTo(eye: Array<Int>, target: Array<Int>, up: Array<Int>, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            val eyex = eye[0].toDouble()
            val eyey = eye[1].toDouble()
            val eyez = eye[2].toDouble()
            val upx = up[0].toDouble()
            val upy = up[1].toDouble()
            val upz = up[2].toDouble()
            var z0 = eyex - target[0].toDouble()
            var z1 = eyey - target[1].toDouble()
            var z2 = eyez - target[2].toDouble()
            var len = z0 * z0 + z1 * z1 + z2 * z2
            if (len > 0) {
                len = 1 / Math.sqrt(len)
                z0 *= len
                z1 *= len
                z2 *= len
            }
            val x0 = upy * z2 - upz * z1
            val x1 = upz * z0 - upx * z2
            val x2 = upx * z1 - upy * z0
            inOut[0] = x0
            inOut[1] = x1
            inOut[2] = x2
            inOut[3] = 0.0
            inOut[4] = z1 * x2 - z2 * x1
            inOut[5] = z2 * x0 - z0 * x2
            inOut[6] = z0 * x1 - z1 * x0
            inOut[7] = 0.0
            inOut[8] = z0
            inOut[9] = z1
            inOut[10] = z2
            inOut[11] = 0.0
            inOut[12] = eyex
            inOut[13] = eyey
            inOut[14] = eyez
            inOut[15] = 1.0
            return inOut
        }

        fun targetTo(eye: Vec3, target: Vec3, up: Vec3, inOut: Float32Array = Float32Array(16)): Float32Array {
            val eyex = eye[0]
            val eyey = eye[1]
            val eyez = eye[2]
            val upx = up[0]
            val upy = up[1]
            val upz = up[2]
            var z0 = eyex - target[0]
            var z1 = eyey - target[1]
            var z2 = eyez - target[2]
            var len = z0 * z0 + z1 * z1 + z2 * z2
            if (len > 0) {
                len = 1 / Math.sqrt(len)
                z0 *= len
                z1 *= len
                z2 *= len
            }
            val x0 = upy * z2 - upz * z1
            val x1 = upz * z0 - upx * z2
            val x2 = upx * z1 - upy * z0
            inOut[0] = x0.toFloat()
            inOut[1] = x1.toFloat()
            inOut[2] = x2.toFloat()
            inOut[3] = 0.0f
            inOut[4] = (z1 * x2 - z2 * x1).toFloat()
            inOut[5] = (z2 * x0 - z0 * x2).toFloat()
            inOut[6] = (z0 * x1 - z1 * x0).toFloat()
            inOut[7] = 0.0f
            inOut[8] = z0.toFloat()
            inOut[9] = z1.toFloat()
            inOut[10] = z2.toFloat()
            inOut[11] = 0.0f
            inOut[12] = eyex.toFloat()
            inOut[13] = eyey.toFloat()
            inOut[14] = eyez.toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        fun targetTo(eye: Array<Int>, target: Array<Int>, up: Array<Int>, inOut: Float32Array = Float32Array(16)): Float32Array {
            val eyex = eye[0].toDouble()
            val eyey = eye[1].toDouble()
            val eyez = eye[2].toDouble()
            val upx = up[0].toDouble()
            val upy = up[1].toDouble()
            val upz = up[2].toDouble()
            var z0 = eyex - target[0]
            var z1 = eyey - target[1]
            var z2 = eyez - target[2]
            var len = z0 * z0 + z1 * z1 + z2 * z2
            if (len > 0) {
                len = 1 / Math.sqrt(len)
                z0 *= len
                z1 *= len
                z2 *= len
            }
            val x0 = upy * z2 - upz * z1
            val x1 = upz * z0 - upx * z2
            val x2 = upx * z1 - upy * z0
            inOut[0] = x0.toFloat()
            inOut[1] = x1.toFloat()
            inOut[2] = x2.toFloat()
            inOut[3] = 0.0f
            inOut[4] = (z1 * x2 - z2 * x1).toFloat()
            inOut[5] = (z2 * x0 - z0 * x2).toFloat()
            inOut[6] = (z0 * x1 - z1 * x0).toFloat()
            inOut[7] = 0.0f
            inOut[8] = z0.toFloat()
            inOut[9] = z1.toFloat()
            inOut[10] = z2.toFloat()
            inOut[11] = 0.0f
            inOut[12] = eyex.toFloat()
            inOut[13] = eyey.toFloat()
            inOut[14] = eyez.toFloat()
            inOut[15] = 1.0f
            return inOut
        }

        /**
         * Returns matrix string representation of matrix Mat4
         *
         * @param {Mat4} matrix matrix to represent as matrix string
         * @returns {String} string representation of the matrix
         */
        fun toString(matrix: Mat4): String {
            return "Mat4(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]}, ${matrix[4]}, ${matrix[5]}, ${matrix[6]}, ${matrix[7]}," +
                    " ${matrix[8]}, ${matrix[9]}, ${matrix[10]}, ${matrix[11]}, ${matrix[12]}, ${matrix[13]}, ${matrix[14]}, ${matrix[15]})"
        }

        fun toString(matrix: DoubleArray): String {
            return "Mat4(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]}, ${matrix[4]}, ${matrix[5]}, ${matrix[6]}, ${matrix[7]}," +
                    " ${matrix[8]}, ${matrix[9]}, ${matrix[10]}, ${matrix[11]}, ${matrix[12]}, ${matrix[13]}, ${matrix[14]}, ${matrix[15]})"
        }

        fun toString(matrix: Float32Array): String {
            return "Mat4(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]}, ${matrix[4]}, ${matrix[5]}, ${matrix[6]}, ${matrix[7]}," +
                    " ${matrix[8]}, ${matrix[9]}, ${matrix[10]}, ${matrix[11]}, ${matrix[12]}, ${matrix[13]}, ${matrix[14]}, ${matrix[15]})"
        }

        /**
         * Returns Frobenius norm of matrix Mat4
         *
         * @param {Mat4} matrix the matrix to calculate Frobenius norm of
         * @returns {Number} Frobenius norm
         */
        fun frob(matrix: Mat4): Double {
            return (Math.sqrt(Math.pow(matrix[0], 2.0) + Math.pow(matrix[1], 2.0)
                    + Math.pow(matrix[2], 2.0) + Math.pow(matrix[3], 2.0) + Math.pow(matrix[4], 2.0)
                    + Math.pow(matrix[5], 2.0) + Math.pow(matrix[6], 2.0) + Math.pow(matrix[7], 2.0)
                    + Math.pow(matrix[8], 2.0) + Math.pow(matrix[9], 2.0) + Math.pow(matrix[10], 2.0)
                    + Math.pow(matrix[11], 2.0) + Math.pow(matrix[12], 2.0) + Math.pow(matrix[13], 2.0)
                    + Math.pow(matrix[14], 2.0) + Math.pow(matrix[15], 2.0)))
        }

        fun frob(matrix: DoubleArray): Double {
            return (Math.sqrt(Math.pow(matrix[0], 2.0) + Math.pow(matrix[1], 2.0)
                    + Math.pow(matrix[2], 2.0) + Math.pow(matrix[3], 2.0) + Math.pow(matrix[4], 2.0)
                    + Math.pow(matrix[5], 2.0) + Math.pow(matrix[6], 2.0) + Math.pow(matrix[7], 2.0)
                    + Math.pow(matrix[8], 2.0) + Math.pow(matrix[9], 2.0) + Math.pow(matrix[10], 2.0)
                    + Math.pow(matrix[11], 2.0) + Math.pow(matrix[12], 2.0) + Math.pow(matrix[13], 2.0)
                    + Math.pow(matrix[14], 2.0) + Math.pow(matrix[15], 2.0)))
        }

        fun frob(matrix: Float32Array): Double {
            return (Math.sqrt(Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(matrix[1].toDouble(), 2.0)
                    + Math.pow(matrix[2].toDouble(), 2.0) + Math.pow(matrix[3].toDouble(), 2.0) + Math.pow(matrix[4].toDouble(), 2.0)
                    + Math.pow(matrix[5].toDouble(), 2.0) + Math.pow(matrix[6].toDouble(), 2.0) + Math.pow(matrix[7].toDouble(), 2.0)
                    + Math.pow(matrix[8].toDouble(), 2.0) + Math.pow(matrix[9].toDouble(), 2.0) + Math.pow(matrix[10].toDouble(), 2.0)
                    + Math.pow(matrix[11].toDouble(), 2.0) + Math.pow(matrix[12].toDouble(), 2.0) + Math.pow(matrix[13].toDouble(), 2.0)
                    + Math.pow(matrix[14].toDouble(), 2.0) + Math.pow(matrix[15].toDouble(), 2.0)))
        }

        /**
         * Adds two Mat4's
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} firstSummand the first operand
         * @param {Mat4} secondSummand the second operand
         * @returns {Mat4} inOut
         */
        fun add(firstSummand: Mat4, secondSummand: Mat4, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            inOut[6] = firstSummand[6] + secondSummand[6]
            inOut[7] = firstSummand[7] + secondSummand[7]
            inOut[8] = firstSummand[8] + secondSummand[8]
            inOut[9] = firstSummand[9] + secondSummand[9]
            inOut[10] = firstSummand[10] + secondSummand[10]
            inOut[11] = firstSummand[11] + secondSummand[11]
            inOut[12] = firstSummand[12] + secondSummand[12]
            inOut[13] = firstSummand[13] + secondSummand[13]
            inOut[14] = firstSummand[14] + secondSummand[14]
            inOut[15] = firstSummand[15] + secondSummand[15]
            return inOut
        }

        fun add(firstSummand: Mat4, secondSummand: DoubleArray, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            inOut[6] = firstSummand[6] + secondSummand[6]
            inOut[7] = firstSummand[7] + secondSummand[7]
            inOut[8] = firstSummand[8] + secondSummand[8]
            inOut[9] = firstSummand[9] + secondSummand[9]
            inOut[10] = firstSummand[10] + secondSummand[10]
            inOut[11] = firstSummand[11] + secondSummand[11]
            inOut[12] = firstSummand[12] + secondSummand[12]
            inOut[13] = firstSummand[13] + secondSummand[13]
            inOut[14] = firstSummand[14] + secondSummand[14]
            inOut[15] = firstSummand[15] + secondSummand[15]
            return inOut
        }

        fun add(firstSummand: Float32Array, secondSummand: Float32Array, inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            inOut[6] = firstSummand[6] + secondSummand[6]
            inOut[7] = firstSummand[7] + secondSummand[7]
            inOut[8] = firstSummand[8] + secondSummand[8]
            inOut[9] = firstSummand[9] + secondSummand[9]
            inOut[10] = firstSummand[10] + secondSummand[10]
            inOut[11] = firstSummand[11] + secondSummand[11]
            inOut[12] = firstSummand[12] + secondSummand[12]
            inOut[13] = firstSummand[13] + secondSummand[13]
            inOut[14] = firstSummand[14] + secondSummand[14]
            inOut[15] = firstSummand[15] + secondSummand[15]
            return inOut
        }

        /**
         * Subtracts matrix subtrahend from matrix minuend
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} minuend the first operand
         * @param {Mat4} subtrahend the second operand
         * @returns {Mat4} inOut
         */
        fun subtract(minuend: Mat4, subtrahend: Mat4, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            inOut[6] = minuend[6] - subtrahend[6]
            inOut[7] = minuend[7] - subtrahend[7]
            inOut[8] = minuend[8] - subtrahend[8]
            inOut[9] = minuend[9] - subtrahend[9]
            inOut[10] = minuend[10] - subtrahend[10]
            inOut[11] = minuend[11] - subtrahend[11]
            inOut[12] = minuend[12] - subtrahend[12]
            inOut[13] = minuend[13] - subtrahend[13]
            inOut[14] = minuend[14] - subtrahend[14]
            inOut[15] = minuend[15] - subtrahend[15]
            return inOut
        }

        fun subtract(minuend: DoubleArray, subtrahend: DoubleArray, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            inOut[6] = minuend[6] - subtrahend[6]
            inOut[7] = minuend[7] - subtrahend[7]
            inOut[8] = minuend[8] - subtrahend[8]
            inOut[9] = minuend[9] - subtrahend[9]
            inOut[10] = minuend[10] - subtrahend[10]
            inOut[11] = minuend[11] - subtrahend[11]
            inOut[12] = minuend[12] - subtrahend[12]
            inOut[13] = minuend[13] - subtrahend[13]
            inOut[14] = minuend[14] - subtrahend[14]
            inOut[15] = minuend[15] - subtrahend[15]
            return inOut
        }

        fun subtract(minuend: Float32Array, subtrahend: Float32Array, inOut: Float32Array = Float32Array(16)): Float32Array {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            inOut[6] = minuend[6] - subtrahend[6]
            inOut[7] = minuend[7] - subtrahend[7]
            inOut[8] = minuend[8] - subtrahend[8]
            inOut[9] = minuend[9] - subtrahend[9]
            inOut[10] = minuend[10] - subtrahend[10]
            inOut[11] = minuend[11] - subtrahend[11]
            inOut[12] = minuend[12] - subtrahend[12]
            inOut[13] = minuend[13] - subtrahend[13]
            inOut[14] = minuend[14] - subtrahend[14]
            inOut[15] = minuend[15] - subtrahend[15]
            return inOut
        }

        /**
         * Multiply each element of the matrix by matrixToScale scalar.
         *
         * @param {Mat4} inOut the receiving matrix
         * @param {Mat4} matrixToScale the matrix to scale
         * @param {Number} amountToScaleBy amount to scale the matrix's elements by
         * @returns {Mat4} inOut
         */
        fun multiplyScalar(matrixToScale: Mat4, amountToScaleBy: Double, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = matrixToScale[0] * amountToScaleBy
            inOut[1] = matrixToScale[1] * amountToScaleBy
            inOut[2] = matrixToScale[2] * amountToScaleBy
            inOut[3] = matrixToScale[3] * amountToScaleBy
            inOut[4] = matrixToScale[4] * amountToScaleBy
            inOut[5] = matrixToScale[5] * amountToScaleBy
            inOut[6] = matrixToScale[6] * amountToScaleBy
            inOut[7] = matrixToScale[7] * amountToScaleBy
            inOut[8] = matrixToScale[8] * amountToScaleBy
            inOut[9] = matrixToScale[9] * amountToScaleBy
            inOut[10] = matrixToScale[10] * amountToScaleBy
            inOut[11] = matrixToScale[11] * amountToScaleBy
            inOut[12] = matrixToScale[12] * amountToScaleBy
            inOut[13] = matrixToScale[13] * amountToScaleBy
            inOut[14] = matrixToScale[14] * amountToScaleBy
            inOut[15] = matrixToScale[15] * amountToScaleBy
            return inOut
        }

        fun multiplyScalar(matrixToScale: DoubleArray, amountToScaleBy: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = matrixToScale[0] * amountToScaleBy
            inOut[1] = matrixToScale[1] * amountToScaleBy
            inOut[2] = matrixToScale[2] * amountToScaleBy
            inOut[3] = matrixToScale[3] * amountToScaleBy
            inOut[4] = matrixToScale[4] * amountToScaleBy
            inOut[5] = matrixToScale[5] * amountToScaleBy
            inOut[6] = matrixToScale[6] * amountToScaleBy
            inOut[7] = matrixToScale[7] * amountToScaleBy
            inOut[8] = matrixToScale[8] * amountToScaleBy
            inOut[9] = matrixToScale[9] * amountToScaleBy
            inOut[10] = matrixToScale[10] * amountToScaleBy
            inOut[11] = matrixToScale[11] * amountToScaleBy
            inOut[12] = matrixToScale[12] * amountToScaleBy
            inOut[13] = matrixToScale[13] * amountToScaleBy
            inOut[14] = matrixToScale[14] * amountToScaleBy
            inOut[15] = matrixToScale[15] * amountToScaleBy
            return inOut
        }

        fun multiplyScalar(matrixToScale: Float32Array, amountToScaleBy: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val amountToScaleTheMatrix = amountToScaleBy.toFloat()
            inOut[0] = matrixToScale[0] * amountToScaleTheMatrix
            inOut[1] = matrixToScale[1] * amountToScaleTheMatrix
            inOut[2] = matrixToScale[2] * amountToScaleTheMatrix
            inOut[3] = matrixToScale[3] * amountToScaleTheMatrix
            inOut[4] = matrixToScale[4] * amountToScaleTheMatrix
            inOut[5] = matrixToScale[5] * amountToScaleTheMatrix
            inOut[6] = matrixToScale[6] * amountToScaleTheMatrix
            inOut[7] = matrixToScale[7] * amountToScaleTheMatrix
            inOut[8] = matrixToScale[8] * amountToScaleTheMatrix
            inOut[9] = matrixToScale[9] * amountToScaleTheMatrix
            inOut[10] = matrixToScale[10] * amountToScaleTheMatrix
            inOut[11] = matrixToScale[11] * amountToScaleTheMatrix
            inOut[12] = matrixToScale[12] * amountToScaleTheMatrix
            inOut[13] = matrixToScale[13] * amountToScaleTheMatrix
            inOut[14] = matrixToScale[14] * amountToScaleTheMatrix
            inOut[15] = matrixToScale[15] * amountToScaleTheMatrix
            return inOut
        }

        /**
         * Adds two Mat4's after multiplying each element of the second operand by firstSummand scalar value.
         *
         * @param {Mat4} inOut the receiving vector
         * @param {Mat4} firstSummand the first operand
         * @param {Mat4} secondSummand the second operand
         * @param {Number} amountToScale the amount to amountToScale secondSummand's elements by before adding
         * @returns {Mat4} inOut
         */
        fun multiplyScalarAndAdd(firstSummand: Mat4, secondSummand: Mat4, amountToScale: Double, inOut: Mat4 = Mat4()): Mat4 {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScale)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScale)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScale)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScale)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScale)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScale)
            inOut[6] = firstSummand[6] + (secondSummand[6] * amountToScale)
            inOut[7] = firstSummand[7] + (secondSummand[7] * amountToScale)
            inOut[8] = firstSummand[8] + (secondSummand[8] * amountToScale)
            inOut[9] = firstSummand[9] + (secondSummand[9] * amountToScale)
            inOut[10] = firstSummand[10] + (secondSummand[10] * amountToScale)
            inOut[11] = firstSummand[11] + (secondSummand[11] * amountToScale)
            inOut[12] = firstSummand[12] + (secondSummand[12] * amountToScale)
            inOut[13] = firstSummand[13] + (secondSummand[13] * amountToScale)
            inOut[14] = firstSummand[14] + (secondSummand[14] * amountToScale)
            inOut[15] = firstSummand[15] + (secondSummand[15] * amountToScale)
            return inOut
        }

        fun multiplyScalarAndAdd(firstSummand: DoubleArray, secondSummand: DoubleArray, amountToScale: Double, inOut: DoubleArray = DoubleArray(16)): DoubleArray {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScale)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScale)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScale)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScale)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScale)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScale)
            inOut[6] = firstSummand[6] + (secondSummand[6] * amountToScale)
            inOut[7] = firstSummand[7] + (secondSummand[7] * amountToScale)
            inOut[8] = firstSummand[8] + (secondSummand[8] * amountToScale)
            inOut[9] = firstSummand[9] + (secondSummand[9] * amountToScale)
            inOut[10] = firstSummand[10] + (secondSummand[10] * amountToScale)
            inOut[11] = firstSummand[11] + (secondSummand[11] * amountToScale)
            inOut[12] = firstSummand[12] + (secondSummand[12] * amountToScale)
            inOut[13] = firstSummand[13] + (secondSummand[13] * amountToScale)
            inOut[14] = firstSummand[14] + (secondSummand[14] * amountToScale)
            inOut[15] = firstSummand[15] + (secondSummand[15] * amountToScale)
            return inOut
        }

        fun multiplyScalarAndAdd(firstSummand: Float32Array, secondSummand: Float32Array, amountToScale: Double, inOut: Float32Array = Float32Array(16)): Float32Array {
            val amountToScaleTheMatrix = amountToScale.toFloat()
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleTheMatrix)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleTheMatrix)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleTheMatrix)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleTheMatrix)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScaleTheMatrix)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScaleTheMatrix)
            inOut[6] = firstSummand[6] + (secondSummand[6] * amountToScaleTheMatrix)
            inOut[7] = firstSummand[7] + (secondSummand[7] * amountToScaleTheMatrix)
            inOut[8] = firstSummand[8] + (secondSummand[8] * amountToScaleTheMatrix)
            inOut[9] = firstSummand[9] + (secondSummand[9] * amountToScaleTheMatrix)
            inOut[10] = firstSummand[10] + (secondSummand[10] * amountToScaleTheMatrix)
            inOut[11] = firstSummand[11] + (secondSummand[11] * amountToScaleTheMatrix)
            inOut[12] = firstSummand[12] + (secondSummand[12] * amountToScaleTheMatrix)
            inOut[13] = firstSummand[13] + (secondSummand[13] * amountToScaleTheMatrix)
            inOut[14] = firstSummand[14] + (secondSummand[14] * amountToScaleTheMatrix)
            inOut[15] = firstSummand[15] + (secondSummand[15] * amountToScaleTheMatrix)
            return inOut
        }

        /**
         * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
         *
         * @param {Mat4} firstMatrix The first matrix.
         * @param {Mat4} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun exactEquals(firstMatrix: Mat4, secondMatrix: Mat4): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3] &&
                    firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5] && firstMatrix[6] == secondMatrix[6] && firstMatrix[7] == secondMatrix[7] &&
                    firstMatrix[8] == secondMatrix[8] && firstMatrix[9] == secondMatrix[9] && firstMatrix[10] == secondMatrix[10] && firstMatrix[11] == secondMatrix[11] &&
                    firstMatrix[12] == secondMatrix[12] && firstMatrix[13] == secondMatrix[13] && firstMatrix[14] == secondMatrix[14] && firstMatrix[15] == secondMatrix[15]
        }

        fun exactEquals(firstMatrix: DoubleArray, secondMatrix: DoubleArray): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3] &&
                    firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5] && firstMatrix[6] == secondMatrix[6] && firstMatrix[7] == secondMatrix[7] &&
                    firstMatrix[8] == secondMatrix[8] && firstMatrix[9] == secondMatrix[9] && firstMatrix[10] == secondMatrix[10] && firstMatrix[11] == secondMatrix[11] &&
                    firstMatrix[12] == secondMatrix[12] && firstMatrix[13] == secondMatrix[13] && firstMatrix[14] == secondMatrix[14] && firstMatrix[15] == secondMatrix[15]
        }

        fun exactEquals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3] &&
                    firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5] && firstMatrix[6] == secondMatrix[6] && firstMatrix[7] == secondMatrix[7] &&
                    firstMatrix[8] == secondMatrix[8] && firstMatrix[9] == secondMatrix[9] && firstMatrix[10] == secondMatrix[10] && firstMatrix[11] == secondMatrix[11] &&
                    firstMatrix[12] == secondMatrix[12] && firstMatrix[13] == secondMatrix[13] && firstMatrix[14] == secondMatrix[14] && firstMatrix[15] == secondMatrix[15]
        }

        /**
         * Returns whether or not the matrices have approximately the same elements in the same position.
         *
         * @param {Mat4} firstMatrix The first matrix.
         * @param {Mat4} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun equals(firstMatrix: Mat4, secondMatrix: Mat4): Boolean {
            val a0 = firstMatrix[0]
            val a1 = firstMatrix[1]
            val a2 = firstMatrix[2]
            val a3 = firstMatrix[3]
            val a4 = firstMatrix[4]
            val a5 = firstMatrix[5]
            val a6 = firstMatrix[6]
            val a7 = firstMatrix[7]
            val a8 = firstMatrix[8]
            val a9 = firstMatrix[9]
            val a10 = firstMatrix[10]
            val a11 = firstMatrix[11]
            val a12 = firstMatrix[12]
            val a13 = firstMatrix[13]
            val a14 = firstMatrix[14]
            val a15 = firstMatrix[15]
            val b0 = secondMatrix[0]
            val b1 = secondMatrix[1]
            val b2 = secondMatrix[2]
            val b3 = secondMatrix[3]
            val b4 = secondMatrix[4]
            val b5 = secondMatrix[5]
            val b6 = secondMatrix[6]
            val b7 = secondMatrix[7]
            val b8 = secondMatrix[8]
            val b9 = secondMatrix[9]
            val b10 = secondMatrix[10]
            val b11 = secondMatrix[11]
            val b12 = secondMatrix[12]
            val b13 = secondMatrix[13]
            val b14 = secondMatrix[14]
            val b15 = secondMatrix[15]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)) &&
                    Math.abs(a6 - b6) <= EPSILON * Math.max(1.0, Math.abs(a6), Math.abs(b6)) &&
                    Math.abs(a7 - b7) <= EPSILON * Math.max(1.0, Math.abs(a7), Math.abs(b7)) &&
                    Math.abs(a8 - b8) <= EPSILON * Math.max(1.0, Math.abs(a8), Math.abs(b8)) &&
                    Math.abs(a9 - b9) <= EPSILON * Math.max(1.0, Math.abs(a9), Math.abs(b9)) &&
                    Math.abs(a10 - b10) <= EPSILON * Math.max(1.0, Math.abs(a10), Math.abs(b10)) &&
                    Math.abs(a11 - b11) <= EPSILON * Math.max(1.0, Math.abs(a11), Math.abs(b11)) &&
                    Math.abs(a12 - b12) <= EPSILON * Math.max(1.0, Math.abs(a12), Math.abs(b12)) &&
                    Math.abs(a13 - b13) <= EPSILON * Math.max(1.0, Math.abs(a13), Math.abs(b13)) &&
                    Math.abs(a14 - b14) <= EPSILON * Math.max(1.0, Math.abs(a14), Math.abs(b14)) &&
                    Math.abs(a15 - b15) <= EPSILON * Math.max(1.0, Math.abs(a15), Math.abs(b15)))
        }

        fun equals(firstMatrix: DoubleArray, secondMatrix: DoubleArray): Boolean {
            val a0 = firstMatrix[0]
            val a1 = firstMatrix[1]
            val a2 = firstMatrix[2]
            val a3 = firstMatrix[3]
            val a4 = firstMatrix[4]
            val a5 = firstMatrix[5]
            val a6 = firstMatrix[6]
            val a7 = firstMatrix[7]
            val a8 = firstMatrix[8]
            val a9 = firstMatrix[9]
            val a10 = firstMatrix[10]
            val a11 = firstMatrix[11]
            val a12 = firstMatrix[12]
            val a13 = firstMatrix[13]
            val a14 = firstMatrix[14]
            val a15 = firstMatrix[15]
            val b0 = secondMatrix[0]
            val b1 = secondMatrix[1]
            val b2 = secondMatrix[2]
            val b3 = secondMatrix[3]
            val b4 = secondMatrix[4]
            val b5 = secondMatrix[5]
            val b6 = secondMatrix[6]
            val b7 = secondMatrix[7]
            val b8 = secondMatrix[8]
            val b9 = secondMatrix[9]
            val b10 = secondMatrix[10]
            val b11 = secondMatrix[11]
            val b12 = secondMatrix[12]
            val b13 = secondMatrix[13]
            val b14 = secondMatrix[14]
            val b15 = secondMatrix[15]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)) &&
                    Math.abs(a6 - b6) <= EPSILON * Math.max(1.0, Math.abs(a6), Math.abs(b6)) &&
                    Math.abs(a7 - b7) <= EPSILON * Math.max(1.0, Math.abs(a7), Math.abs(b7)) &&
                    Math.abs(a8 - b8) <= EPSILON * Math.max(1.0, Math.abs(a8), Math.abs(b8)) &&
                    Math.abs(a9 - b9) <= EPSILON * Math.max(1.0, Math.abs(a9), Math.abs(b9)) &&
                    Math.abs(a10 - b10) <= EPSILON * Math.max(1.0, Math.abs(a10), Math.abs(b10)) &&
                    Math.abs(a11 - b11) <= EPSILON * Math.max(1.0, Math.abs(a11), Math.abs(b11)) &&
                    Math.abs(a12 - b12) <= EPSILON * Math.max(1.0, Math.abs(a12), Math.abs(b12)) &&
                    Math.abs(a13 - b13) <= EPSILON * Math.max(1.0, Math.abs(a13), Math.abs(b13)) &&
                    Math.abs(a14 - b14) <= EPSILON * Math.max(1.0, Math.abs(a14), Math.abs(b14)) &&
                    Math.abs(a15 - b15) <= EPSILON * Math.max(1.0, Math.abs(a15), Math.abs(b15)))
        }

        fun equals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            val a0 = firstMatrix[0].toDouble()
            val a1 = firstMatrix[1].toDouble()
            val a2 = firstMatrix[2].toDouble()
            val a3 = firstMatrix[3].toDouble()
            val a4 = firstMatrix[4].toDouble()
            val a5 = firstMatrix[5].toDouble()
            val a6 = firstMatrix[6].toDouble()
            val a7 = firstMatrix[7].toDouble()
            val a8 = firstMatrix[8].toDouble()
            val a9 = firstMatrix[9].toDouble()
            val a10 = firstMatrix[10].toDouble()
            val a11 = firstMatrix[11].toDouble()
            val a12 = firstMatrix[12].toDouble()
            val a13 = firstMatrix[13].toDouble()
            val a14 = firstMatrix[14].toDouble()
            val a15 = firstMatrix[15].toDouble()
            val b0 = secondMatrix[0].toDouble()
            val b1 = secondMatrix[1].toDouble()
            val b2 = secondMatrix[2].toDouble()
            val b3 = secondMatrix[3].toDouble()
            val b4 = secondMatrix[4].toDouble()
            val b5 = secondMatrix[5].toDouble()
            val b6 = secondMatrix[6].toDouble()
            val b7 = secondMatrix[7].toDouble()
            val b8 = secondMatrix[8].toDouble()
            val b9 = secondMatrix[9].toDouble()
            val b10 = secondMatrix[10].toDouble()
            val b11 = secondMatrix[11].toDouble()
            val b12 = secondMatrix[12].toDouble()
            val b13 = secondMatrix[13].toDouble()
            val b14 = secondMatrix[14].toDouble()
            val b15 = secondMatrix[15].toDouble()
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)) &&
                    Math.abs(a6 - b6) <= EPSILON * Math.max(1.0, Math.abs(a6), Math.abs(b6)) &&
                    Math.abs(a7 - b7) <= EPSILON * Math.max(1.0, Math.abs(a7), Math.abs(b7)) &&
                    Math.abs(a8 - b8) <= EPSILON * Math.max(1.0, Math.abs(a8), Math.abs(b8)) &&
                    Math.abs(a9 - b9) <= EPSILON * Math.max(1.0, Math.abs(a9), Math.abs(b9)) &&
                    Math.abs(a10 - b10) <= EPSILON * Math.max(1.0, Math.abs(a10), Math.abs(b10)) &&
                    Math.abs(a11 - b11) <= EPSILON * Math.max(1.0, Math.abs(a11), Math.abs(b11)) &&
                    Math.abs(a12 - b12) <= EPSILON * Math.max(1.0, Math.abs(a12), Math.abs(b12)) &&
                    Math.abs(a13 - b13) <= EPSILON * Math.max(1.0, Math.abs(a13), Math.abs(b13)) &&
                    Math.abs(a14 - b14) <= EPSILON * Math.max(1.0, Math.abs(a14), Math.abs(b14)) &&
                    Math.abs(a15 - b15) <= EPSILON * Math.max(1.0, Math.abs(a15), Math.abs(b15)))
        }
    }
}