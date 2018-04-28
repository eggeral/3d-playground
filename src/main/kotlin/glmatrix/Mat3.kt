package glmatrix

import kotlin.js.Math

class Mat3() : GlMatrix() {

    private val matrix: Array<Double> = arrayOf(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0)

    constructor(m00: Double, m01: Double, m02: Double, m10: Double, m11: Double, m12: Double, m20: Double, m21: Double, m22: Double) : this() {
        matrix[0] = m00
        matrix[1] = m01
        matrix[2] = m02
        matrix[3] = m10
        matrix[4] = m11
        matrix[5] = m12
        matrix[6] = m20
        matrix[7] = m21
        matrix[8] = m22
    }

    operator fun get(index: Int): Double {
        return matrix[index]
    }

    operator fun set(index: Int, value: Double) {
        matrix[index] = value
    }


    /**
     * Adds summand matrix to this
     */
    fun add(summand: Mat3): Mat3 {
        this.matrix[0] += summand[0]
        this.matrix[1] += summand[1]
        this.matrix[2] += summand[2]
        this.matrix[3] += summand[3]
        this.matrix[4] += summand[4]
        this.matrix[5] += summand[5]
        this.matrix[6] += summand[6]
        this.matrix[7] += summand[7]
        this.matrix[8] += summand[8]
        return this
    }

    /**
     * Adds summand matrix with + operator
     */
    operator fun plus(summand: Mat3): Mat3 {
        return clone().add(summand)
    }

    /**
     * Subtracts subtrahend matrix from this
     */
    fun subtract(subtrahend: Mat3): Mat3 {
        this.matrix[0] -= subtrahend[0]
        this.matrix[1] -= subtrahend[1]
        this.matrix[2] -= subtrahend[2]
        this.matrix[3] -= subtrahend[3]
        this.matrix[4] -= subtrahend[4]
        this.matrix[5] -= subtrahend[5]
        this.matrix[6] -= subtrahend[6]
        this.matrix[7] -= subtrahend[7]
        this.matrix[8] -= subtrahend[8]
        return this
    }

    /**
     * Subtracts subtrahend matrix with - operator
     */
    operator fun minus(subtrahend: Mat3): Mat3 {
        return clone().subtract(subtrahend)
    }

    /**
     * Multiplies multiplier matrix with this
     */
    fun multiply(multiplier: Mat3): Mat3 {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a10 = this.matrix[3]
        val a11 = this.matrix[4]
        val a12 = this.matrix[5]
        val a20 = this.matrix[6]
        val a21 = this.matrix[7]
        val a22 = this.matrix[8]
        val b00 = multiplier[0]
        val b01 = multiplier[1]
        val b02 = multiplier[2]
        val b10 = multiplier[3]
        val b11 = multiplier[4]
        val b12 = multiplier[5]
        val b20 = multiplier[6]
        val b21 = multiplier[7]
        val b22 = multiplier[8]
        this.matrix[0] = b00 * a00 + b01 * a10 + b02 * a20
        this.matrix[1] = b00 * a01 + b01 * a11 + b02 * a21
        this.matrix[2] = b00 * a02 + b01 * a12 + b02 * a22
        this.matrix[3] = b10 * a00 + b11 * a10 + b12 * a20
        this.matrix[4] = b10 * a01 + b11 * a11 + b12 * a21
        this.matrix[5] = b10 * a02 + b11 * a12 + b12 * a22
        this.matrix[6] = b20 * a00 + b21 * a10 + b22 * a20
        this.matrix[7] = b20 * a01 + b21 * a11 + b22 * a21
        this.matrix[8] = b20 * a02 + b21 * a12 + b22 * a22
        return this
    }

    /**
     * Multiplies multiplier matrix with * operator
     */
    operator fun times(multiplier: Mat3): Mat3 {
        return clone().multiply(this)
    }

    /**
     * Copies the upper-left 3x3 values into the given Mat3.
     *
     * @param {Mat4} source  The source 4x4 matrix
     */
    fun fromMat4(source: Mat4): Mat3 {
        this.matrix[0] = source[0]
        this.matrix[1] = source[1]
        this.matrix[2] = source[2]
        this.matrix[3] = source[4]
        this.matrix[4] = source[5]
        this.matrix[5] = source[6]
        this.matrix[6] = source[8]
        this.matrix[7] = source[9]
        this.matrix[8] = source[10]
        return this
    }

    /**
     * Copies the upper-left 3x3 values into the given Mat3.
     *
     * @param {Array<Double>} source  The source 4x4 matrix
     */
    fun fromMat4(source: Array<Double>): Mat3 {
        this.matrix[0] = source[0]
        this.matrix[1] = source[1]
        this.matrix[2] = source[2]
        this.matrix[3] = source[4]
        this.matrix[4] = source[5]
        this.matrix[5] = source[6]
        this.matrix[6] = source[8]
        this.matrix[7] = source[9]
        this.matrix[8] = source[10]
        return this
    }

    /**
     * Creates a new mat3 initialized with values from an existing matrix
     */
    fun clone(): Mat3 {
        return Mat3(this.matrix[0],
                this.matrix[1],
                this.matrix[2],
                this.matrix[3],
                this.matrix[4],
                this.matrix[5],
                this.matrix[6],
                this.matrix[7],
                this.matrix[8])
    }

    /**
     * Set a mat3 to the identity matrix
     */
    fun identity(): Mat3 {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 1.0
        this.matrix[5] = 0.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Transpose the values of source Mat3 to this
     *
     * @param {Mat3} source the source matrix
     */
    fun transpose(source: Mat3): Mat3 {
        // If we are transposing ourselves we can skip source few steps but have to cache some values
        if (this === source) {
            val a01 = source[1]
            val a02 = source[2]
            val a12 = source[5]
            this.matrix[1] = source[3]
            this.matrix[2] = source[6]
            this.matrix[3] = a01
            this.matrix[5] = source[7]
            this.matrix[6] = a02
            this.matrix[7] = a12
        } else {
            this.matrix[0] = source[0]
            this.matrix[1] = source[3]
            this.matrix[2] = source[6]
            this.matrix[3] = source[1]
            this.matrix[4] = source[4]
            this.matrix[5] = source[7]
            this.matrix[6] = source[2]
            this.matrix[7] = source[5]
            this.matrix[8] = source[8]
        }
        return this
    }

    /**
     * Inverts Mat3
     */
    fun invert(): Mat3 {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a10 = this.matrix[3]
        val a11 = this.matrix[4]
        val a12 = this.matrix[5]
        val a20 = this.matrix[6]
        val a21 = this.matrix[7]
        val a22 = this.matrix[8]
        val b01 = a22 * a11 - a12 * a21
        val b11 = -a22 * a10 + a12 * a20
        val b21 = a21 * a10 - a11 * a20
        // Calculate the determinant
        var det = a00 * b01 + a01 * b11 + a02 * b21
        if (det < 0) {
            return Mat3()
        }
        det = 1.0 / det
        this.matrix[0] = b01 * det
        this.matrix[1] = (-a22 * a01 + a02 * a21) * det
        this.matrix[2] = (a12 * a01 - a02 * a11) * det
        this.matrix[3] = b11 * det
        this.matrix[4] = (a22 * a00 - a02 * a20) * det
        this.matrix[5] = (-a12 * a00 + a02 * a10) * det
        this.matrix[6] = b21 * det
        this.matrix[7] = (-a21 * a00 + a01 * a20) * det
        this.matrix[8] = (a11 * a00 - a01 * a10) * det
        return this
    }

    /**
     * Calculates the adjugate of Mat3
     */
    fun adjoint(): Mat3 {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a10 = this.matrix[3]
        val a11 = this.matrix[4]
        val a12 = this.matrix[5]
        val a20 = this.matrix[6]
        val a21 = this.matrix[7]
        val a22 = this.matrix[8]
        this.matrix[0] = (a11 * a22 - a12 * a21)
        this.matrix[1] = (a02 * a21 - a01 * a22)
        this.matrix[2] = (a01 * a12 - a02 * a11)
        this.matrix[3] = (a12 * a20 - a10 * a22)
        this.matrix[4] = (a00 * a22 - a02 * a20)
        this.matrix[5] = (a02 * a10 - a00 * a12)
        this.matrix[6] = (a10 * a21 - a11 * a20)
        this.matrix[7] = (a01 * a20 - a00 * a21)
        this.matrix[8] = (a00 * a11 - a01 * a10)
        return this
    }

    /**
     * Calculates the determinant of Mat3
     */
    fun determinant(): Double {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a10 = this.matrix[3]
        val a11 = this.matrix[4]
        val a12 = this.matrix[5]
        val a20 = this.matrix[6]
        val a21 = this.matrix[7]
        val a22 = this.matrix[8]
        return a00 * (a22 * a11 - a12 * a21) + a01 * (-a22 * a10 + a12 * a20) + a02 * (a21 * a10 - a11 * a20)
    }

    /**
     * Translate Mat3 by the given vector
     *
     * @param {Vec2} vec2ToTranslateBy vector to translate by
     */
    fun translate(vec2ToTranslateBy: Vec2): Mat3 {
        val x = vec2ToTranslateBy[0]
        val y = vec2ToTranslateBy[1]
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a10 = this.matrix[3]
        val a11 = this.matrix[4]
        val a12 = this.matrix[5]
        val a20 = this.matrix[6]
        val a21 = this.matrix[7]
        val a22 = this.matrix[8]
        this.matrix[0] = a00
        this.matrix[1] = a01
        this.matrix[2] = a02
        this.matrix[3] = a10
        this.matrix[4] = a11
        this.matrix[5] = a12
        this.matrix[6] = x * a00 + y * a10 + a20
        this.matrix[7] = x * a01 + y * a11 + a21
        this.matrix[8] = x * a02 + y * a12 + a22
        return this
    }

    /**
     * Translate Mat3 by the given vector
     *
     * @param {Array<Double>} vec2ToTranslateBy vector to translate by
     */
    fun translate(vec2ToTranslateBy: Array<Double>): Mat3 {
        val x = vec2ToTranslateBy[0]
        val y = vec2ToTranslateBy[1]
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a10 = this.matrix[3]
        val a11 = this.matrix[4]
        val a12 = this.matrix[5]
        val a20 = this.matrix[6]
        val a21 = this.matrix[7]
        val a22 = this.matrix[8]
        this.matrix[0] = a00
        this.matrix[1] = a01
        this.matrix[2] = a02
        this.matrix[3] = a10
        this.matrix[4] = a11
        this.matrix[5] = a12
        this.matrix[6] = x * a00 + y * a10 + a20
        this.matrix[7] = x * a01 + y * a11 + a21
        this.matrix[8] = x * a02 + y * a12 + a22
        return this
    }

    /**
     * Rotates Mat3 by the given angle
     *
     * @param {Double} angleInRad the angle to rotate the matrix by
     */
    fun rotate(angleInRad: Double): Mat3 {
        val a00 = this.matrix[0]
        val a01 = this.matrix[1]
        val a02 = this.matrix[2]
        val a10 = this.matrix[3]
        val a11 = this.matrix[4]
        val a12 = this.matrix[5]
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        this.matrix[0] = c * a00 + s * a10
        this.matrix[1] = c * a01 + s * a11
        this.matrix[2] = c * a02 + s * a12
        this.matrix[3] = c * a10 - s * a00
        this.matrix[4] = c * a11 - s * a01
        this.matrix[5] = c * a12 - s * a02
        return this
    }

    /**
     * Scales the Mat3 by the dimensions in the given Vec2
     *
     * @param {Vec2} vec2ToScaleBy the Vec2 to scale the matrix by
     **/
    fun scale(vec2ToScaleBy: Vec2): Mat3 {
        val x = vec2ToScaleBy[0]
        val y = vec2ToScaleBy[1]
        this.matrix[0] = x * this.matrix[0]
        this.matrix[1] = x * this.matrix[1]
        this.matrix[2] = x * this.matrix[2]
        this.matrix[3] = y * this.matrix[3]
        this.matrix[4] = y * this.matrix[4]
        this.matrix[5] = y * this.matrix[5]
        return this
    }

    /**
     * Scales the Mat3 by the dimensions in the given Vec2
     *
     * @param {Array<Double>} vec2ToScaleBy the Vec2 to scale the matrix by
     **/
    fun scale(vec2ToScaleBy: Array<Double>): Mat3 {
        val x = vec2ToScaleBy[0]
        val y = vec2ToScaleBy[1]
        this.matrix[0] = x * this.matrix[0]
        this.matrix[1] = x * this.matrix[1]
        this.matrix[2] = x * this.matrix[2]
        this.matrix[3] = y * this.matrix[3]
        this.matrix[4] = y * this.matrix[4]
        this.matrix[5] = y * this.matrix[5]
        return this
    }

    /**
     * Creates a matrix from a vector translation
     *
     * @param {Vec2} translationVec2 Translation vector
     */
    fun fromTranslation(translationVec2: Vec2): Mat3 {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 1.0
        this.matrix[5] = 0.0
        this.matrix[6] = translationVec2[0]
        this.matrix[7] = translationVec2[1]
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Creates a matrix from a vector translation
     *
     * @param {Array<Double>} translationVec2 Translation vector
     */
    fun fromTranslation(translationVec2: Array<Double>): Mat3 {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = 1.0
        this.matrix[5] = 0.0
        this.matrix[6] = translationVec2[0]
        this.matrix[7] = translationVec2[1]
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Creates a matrix from a given angle
     *
     * @param {Double} angleToRotateByInRad the angle to rotate the matrix by
     */
    fun fromRotation(angleToRotateByInRad: Double): Mat3 {
        val s = Math.sin(angleToRotateByInRad)
        val c = Math.cos(angleToRotateByInRad)
        this.matrix[0] = c
        this.matrix[1] = s
        this.matrix[2] = 0.0
        this.matrix[3] = -s
        this.matrix[4] = c
        this.matrix[5] = 0.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Creates a matrix from a vector scaling
     *
     * @param {Vec2} scalingVec2 Scaling vector
     */
    fun fromScaling(scalingVec2: Vec2): Mat3 {
        this.matrix[0] = scalingVec2[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = scalingVec2[1]
        this.matrix[5] = 0.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Creates a matrix from a vector scaling
     *
     * @param {Vec2} scalingVec2 Scaling vector
     */
    fun fromScaling(scalingVec2: Array<Double>): Mat3 {
        this.matrix[0] = scalingVec2[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = scalingVec2[1]
        this.matrix[5] = 0.0
        this.matrix[6] = 0.0
        this.matrix[7] = 0.0
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Copies the values from Mat2d into Mat3
     *
     * @param {Mat2d} mat2dToCreateMatrixFrom the matrix to copy
     **/
    fun fromMat2d(mat2dToCreateMatrixFrom: Mat2d): Mat3 {
        this.matrix[0] = mat2dToCreateMatrixFrom[0]
        this.matrix[1] = mat2dToCreateMatrixFrom[1]
        this.matrix[2] = 0.0
        this.matrix[3] = mat2dToCreateMatrixFrom[2]
        this.matrix[4] = mat2dToCreateMatrixFrom[3]
        this.matrix[5] = 0.0
        this.matrix[6] = mat2dToCreateMatrixFrom[4]
        this.matrix[7] = mat2dToCreateMatrixFrom[5]
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Copies the values from Mat2d into Mat3
     *
     * @param {Array<Double>} mat2dToCreateMatrixFrom the matrix to copy
     **/
    fun fromMat2d(mat2dToCreateMatrixFrom: Array<Double>): Mat3 {
        this.matrix[0] = mat2dToCreateMatrixFrom[0]
        this.matrix[1] = mat2dToCreateMatrixFrom[1]
        this.matrix[2] = 0.0
        this.matrix[3] = mat2dToCreateMatrixFrom[2]
        this.matrix[4] = mat2dToCreateMatrixFrom[3]
        this.matrix[5] = 0.0
        this.matrix[6] = mat2dToCreateMatrixFrom[4]
        this.matrix[7] = mat2dToCreateMatrixFrom[5]
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Calculates a 3x3 matrix from the given quaternion
     *
     * @param {Quat} quatToCreateMatrixFrom Quaternion to create matrix from
     */
    fun fromQuat(quatToCreateMatrixFrom: Quat): Mat3 {
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
        this.matrix[3] = yx - wz
        this.matrix[6] = zx + wy
        this.matrix[1] = yx + wz
        this.matrix[4] = 1 - xx - zz
        this.matrix[7] = zy - wx
        this.matrix[2] = zx - wy
        this.matrix[5] = zy + wx
        this.matrix[8] = 1 - xx - yy
        return this
    }

    /**
     * Calculates a 3x3 matrix from the given quaternion
     *
     * @param {Array<Double>} quatToCreateMatrixFrom Quaternion to create matrix from
     */
    fun fromQuat(quatToCreateMatrixFrom: Array<Double>): Mat3 {
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
        this.matrix[3] = yx - wz
        this.matrix[6] = zx + wy
        this.matrix[1] = yx + wz
        this.matrix[4] = 1 - xx - zz
        this.matrix[7] = zy - wx
        this.matrix[2] = zx - wy
        this.matrix[5] = zy + wx
        this.matrix[8] = 1 - xx - yy
        return this
    }

    /**
     * Calculates mat4ToDeriveNormalMatrix 3x3 normal matrix (transpose inverse) from the 4x4 matrix
     *
     * @param {Mat4} mat4ToDeriveNormalMatrix Mat4 to derive the normal matrix from
     */
    fun normalFromMat4(mat4ToDeriveNormalMatrix: Mat4): Mat3 {
        val a00 = mat4ToDeriveNormalMatrix[0]
        val a01 = mat4ToDeriveNormalMatrix[1]
        val a02 = mat4ToDeriveNormalMatrix[2]
        val a03 = mat4ToDeriveNormalMatrix[3]
        val a10 = mat4ToDeriveNormalMatrix[4]
        val a11 = mat4ToDeriveNormalMatrix[5]
        val a12 = mat4ToDeriveNormalMatrix[6]
        val a13 = mat4ToDeriveNormalMatrix[7]
        val a20 = mat4ToDeriveNormalMatrix[8]
        val a21 = mat4ToDeriveNormalMatrix[9]
        val a22 = mat4ToDeriveNormalMatrix[10]
        val a23 = mat4ToDeriveNormalMatrix[11]
        val a30 = mat4ToDeriveNormalMatrix[12]
        val a31 = mat4ToDeriveNormalMatrix[13]
        val a32 = mat4ToDeriveNormalMatrix[14]
        val a33 = mat4ToDeriveNormalMatrix[15]
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
            return Mat3()
        }
        det = 1.0 / det
        this.matrix[0] = (a11 * b11 - a12 * b10 + a13 * b09) * det
        this.matrix[1] = (a12 * b08 - a10 * b11 - a13 * b07) * det
        this.matrix[2] = (a10 * b10 - a11 * b08 + a13 * b06) * det
        this.matrix[3] = (a02 * b10 - a01 * b11 - a03 * b09) * det
        this.matrix[4] = (a00 * b11 - a02 * b08 + a03 * b07) * det
        this.matrix[5] = (a01 * b08 - a00 * b10 - a03 * b06) * det
        this.matrix[6] = (a31 * b05 - a32 * b04 + a33 * b03) * det
        this.matrix[7] = (a32 * b02 - a30 * b05 - a33 * b01) * det
        this.matrix[8] = (a30 * b04 - a31 * b02 + a33 * b00) * det
        return this
    }

    /**
     * Calculates mat4ToDeriveNormalMatrix 3x3 normal matrix (transpose inverse) from the 4x4 matrix
     *
     * @param {Array<Double>} mat4ToDeriveNormalMatrix Mat4 to derive the normal matrix from
     */
    fun normalFromMat4(mat4ToDeriveNormalMatrix: Array<Double>): Mat3 {
        val a00 = mat4ToDeriveNormalMatrix[0]
        val a01 = mat4ToDeriveNormalMatrix[1]
        val a02 = mat4ToDeriveNormalMatrix[2]
        val a03 = mat4ToDeriveNormalMatrix[3]
        val a10 = mat4ToDeriveNormalMatrix[4]
        val a11 = mat4ToDeriveNormalMatrix[5]
        val a12 = mat4ToDeriveNormalMatrix[6]
        val a13 = mat4ToDeriveNormalMatrix[7]
        val a20 = mat4ToDeriveNormalMatrix[8]
        val a21 = mat4ToDeriveNormalMatrix[9]
        val a22 = mat4ToDeriveNormalMatrix[10]
        val a23 = mat4ToDeriveNormalMatrix[11]
        val a30 = mat4ToDeriveNormalMatrix[12]
        val a31 = mat4ToDeriveNormalMatrix[13]
        val a32 = mat4ToDeriveNormalMatrix[14]
        val a33 = mat4ToDeriveNormalMatrix[15]
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
            return Mat3()
        }
        det = 1.0 / det
        this.matrix[0] = (a11 * b11 - a12 * b10 + a13 * b09) * det
        this.matrix[1] = (a12 * b08 - a10 * b11 - a13 * b07) * det
        this.matrix[2] = (a10 * b10 - a11 * b08 + a13 * b06) * det
        this.matrix[3] = (a02 * b10 - a01 * b11 - a03 * b09) * det
        this.matrix[4] = (a00 * b11 - a02 * b08 + a03 * b07) * det
        this.matrix[5] = (a01 * b08 - a00 * b10 - a03 * b06) * det
        this.matrix[6] = (a31 * b05 - a32 * b04 + a33 * b03) * det
        this.matrix[7] = (a32 * b02 - a30 * b05 - a33 * b01) * det
        this.matrix[8] = (a30 * b04 - a31 * b02 + a33 * b00) * det
        return this
    }

    /**
     * Generates a 2D projection matrix with the given bounds
     *
     * @param {Double} glContextWith Width of your gl context
     * @param {Double} glContextHeight Height of gl context
     */
    fun projection(glContextWith: Double, glContextHeight: Double): Mat3 {
        this.matrix[0] = 2 / glContextWith
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 0.0
        this.matrix[4] = -2 / glContextHeight
        this.matrix[5] = 0.0
        this.matrix[6] = -1.0
        this.matrix[7] = 1.0
        this.matrix[8] = 1.0
        return this
    }

    /**
     * Returns a string representation of a Mat3
     */
    override fun toString(): String {
        return "Mat3(${this.matrix[0]}, ${this.matrix[1]}, ${this.matrix[2]}, " +
                "${this.matrix[3]}, ${this.matrix[4]}, ${this.matrix[5]}," +
                " ${this.matrix[6]}, ${this.matrix[7]}, ${this.matrix[8]})"
    }

    /**
     * Returns Frobenius norm of a Mat3
     */
    fun frob(): Double {
        return (Math.sqrt(Math.pow(this.matrix[0], 2.0) + Math.pow(this.matrix[1], 2.0) + Math.pow(this.matrix[2], 2.0)
                + Math.pow(this.matrix[3], 2.0) + Math.pow(this.matrix[4], 2.0) + Math.pow(this.matrix[5], 2.0)
                + Math.pow(this.matrix[6], 2.0) + Math.pow(this.matrix[7], 2.0) + Math.pow(this.matrix[8], 2.0)))
    }

    /**
     * Multiply each element of the matrix by matrixToScale scalar.
     */
    fun multiplyScalarAndAdd(summand: Mat3, amountToScale: Double): Mat3 {
        this.matrix[0] += (summand[0] * amountToScale)
        this.matrix[1] += (summand[1] * amountToScale)
        this.matrix[2] += (summand[2] * amountToScale)
        this.matrix[3] += (summand[3] * amountToScale)
        this.matrix[4] += (summand[4] * amountToScale)
        this.matrix[5] += (summand[5] * amountToScale)
        this.matrix[6] += (summand[6] * amountToScale)
        this.matrix[7] += (summand[7] * amountToScale)
        this.matrix[8] += (summand[8] * amountToScale)
        return this
    }

    /**
     * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
     */
    fun exactEquals(matrix: Mat3): Boolean {
        return this.matrix[0] == matrix[0]
                && this.matrix[1] == matrix[1]
                && this.matrix[2] == matrix[2]
                && this.matrix[3] == matrix[3]
                && this.matrix[4] == matrix[4]
                && this.matrix[5] == matrix[5]
                && this.matrix[6] == matrix[6]
                && this.matrix[7] == matrix[7]
                && this.matrix[8] == matrix[8]
    }

    /**
     * Returns whether or not the matrices have approximately the same elements in the same position.
     */
    fun equals(matrix: Mat3): Boolean {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val a6 = this.matrix[6]
        val a7 = this.matrix[7]
        val a8 = this.matrix[8]
        val b0 = matrix[0]
        val b1 = matrix[1]
        val b2 = matrix[2]
        val b3 = matrix[3]
        val b4 = matrix[4]
        val b5 = matrix[5]
        val b6 = matrix[6]
        val b7 = matrix[7]
        val b8 = matrix[8]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)) &&
                Math.abs(a6 - b6) <= EPSILON * Math.max(1.0, Math.abs(a6), Math.abs(b6)) &&
                Math.abs(a7 - b7) <= EPSILON * Math.max(1.0, Math.abs(a7), Math.abs(b7)) &&
                Math.abs(a8 - b8) <= EPSILON * Math.max(1.0, Math.abs(a8), Math.abs(b8)))
    }
}