package spr5.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class Mat2d() : glMatrix() {

    private val matrix: Array<Double> = arrayOf(1.0, 0.0, 0.0, 1.0, 0.0, 0.0)

    constructor(a: Double, b: Double, c: Double, d: Double, tx: Double, ty: Double) : this() {
        matrix[0] = a
        matrix[1] = b
        matrix[2] = c
        matrix[3] = d
        matrix[4] = tx
        matrix[5] = ty
    }

    operator fun get(index: Int): Double {
        return matrix[index]
    }

    operator fun set(index: Int, value: Double) {
        matrix[index] = value
    }

    /**
     * Copy the values from one Mat2d to another
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} source the source matrix
     * @returns {Mat2d} inOut
     */
    fun copy(source: Mat2d): Mat2d {
        this.matrix[0] = source[0]
        this.matrix[1] = source[1]
        this.matrix[2] = source[2]
        this.matrix[3] = source[3]
        this.matrix[4] = source[4]
        this.matrix[5] = source[5]
        return this
    }

    /**
     * Creates matrixToClone new Mat2d initialized with values from an existing matrix
     *
     * @param {Mat2d} matrixToClone matrix to clone
     * @returns {Mat2d} matrixToClone new 2x3 matrix
     */
    fun clone(): Mat2d {
        return Mat2d(this.matrix[0],
                this.matrix[1],
                this.matrix[2],
                this.matrix[3],
                this.matrix[4],
                this.matrix[5])
    }

    /**
     * Set a Mat2d to the identityDoubleArray matrix
     *
     * @param {Mat2d} inOut the receiving matrix
     * @returns {Mat2d} inOut
     */
    fun identity(): Mat2d {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 1.0
        this.matrix[4] = 0.0
        this.matrix[5] = 0.0
        return this
    }

    /**
     * Inverts source Mat2d
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} source the source matrix
     * @returns {Mat2d} inOut
     */
    fun invert(): Mat2d {
        val aa = this.matrix[0]
        val ab = this.matrix[1]
        val ac = this.matrix[2]
        val ad = this.matrix[3]
        val atx = this.matrix[4]
        val aty = this.matrix[5]
        var det = aa * ad - ab * ac
        if (det < 0) {
            return Mat2d()
        }
        det = 1.0 / det
        this.matrix[0] = ad * det
        this.matrix[1] = -ab * det
        this.matrix[2] = -ac * det
        this.matrix[3] = aa * det
        this.matrix[4] = (ac * aty - ad * atx) * det
        this.matrix[5] = (ab * atx - aa * aty) * det
        return this
    }

    /**
     * Calculates the determinant of source Mat2d
     *
     * @param {Mat2d} source the source matrix
     * @returns {Number} determinant of source
     */
    fun determinant(): Double {
        return this.matrix[0] * this.matrix[3] - this.matrix[1] * this.matrix[2]
    }

    /**
     * Multiplies two Mat2d's
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} multiplicand the first operand
     * @param {Mat2d} multiplicand the second operand
     * @returns {Mat2d} inOut
     */
    fun multiply(multiplier: Mat2d): Mat2d {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val b0 = multiplier[0]
        val b1 = multiplier[1]
        val b2 = multiplier[2]
        val b3 = multiplier[3]
        val b4 = multiplier[4]
        val b5 = multiplier[5]
        this.matrix[0] = a0 * b0 + a2 * b1
        this.matrix[1] = a1 * b0 + a3 * b1
        this.matrix[2] = a0 * b2 + a2 * b3
        this.matrix[3] = a1 * b2 + a3 * b3
        this.matrix[4] = a0 * b4 + a2 * b5 + a4
        this.matrix[5] = a1 * b4 + a3 * b5 + a5
        return this
    }

    operator fun times(multiplier: Mat2d): Mat2d {
        return multiplier.clone().multiply(this);
    }

    /**
     * Adds two Mat2d's
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} firstSummand the first operand
     * @param {Mat2d} secondSummand the second operand
     * @returns {Mat2d} inOut
     */
    fun add(summand: Mat2d): Mat2d {
        this.matrix[0] += summand[0]
        this.matrix[1] += summand[1]
        this.matrix[2] += summand[2]
        this.matrix[3] += summand[3]
        this.matrix[4] += summand[4]
        this.matrix[5] += summand[5]
        return this
    }

    operator fun plus(summand: Mat2d): Mat2d {
        return clone().add(summand);
    }

    /**
     * Subtracts matrix subtrahend from matrix minuend
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} minuend the first operand
     * @param {Mat2d} subtrahend the second operand
     * @returns {Mat2d} inOut
     */
    fun subtract(subtrahend: Mat2d): Mat2d {
        this.matrix[0] -= subtrahend[0]
        this.matrix[1] -= subtrahend[1]
        this.matrix[2] -= subtrahend[2]
        this.matrix[3] -= subtrahend[3]
        this.matrix[4] -= subtrahend[4]
        this.matrix[5] -= subtrahend[5]
        return this
    }

    operator fun minus(subtrahend: Mat2d): Mat2d {
        return clone().subtract(subtrahend);
    }

    /**
     * Rotates matrixToRotate Mat2d by the given angle
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} matrixToRotate the matrix to rotate
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat2d} inOut
     */
    fun rotate(angleInRad: Double): Mat2d {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        this.matrix[0] = a0 * c + a2 * s
        this.matrix[1] = a1 * c + a3 * s
        this.matrix[2] = a0 * -s + a2 * c
        this.matrix[3] = a1 * -s + a3 * c
        this.matrix[4] = a4
        this.matrix[5] = a5
        return this
    }


    /**
     * Scales the Mat2d by the dimensions in the given Vec2
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} matrixToRotate the matrix to rotate
     * @param {Vec2} vec2ToScaleBy the Vec2 to scale the matrix by
     * @returns {Mat2d} inOut
     **/
    fun scale(vec2ToScaleBy: Vec2): Mat2d {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val v0 = vec2ToScaleBy[0]
        val v1 = vec2ToScaleBy[1]
        this.matrix[0] = a0 * v0
        this.matrix[1] = a1 * v0
        this.matrix[2] = a2 * v1
        this.matrix[3] = a3 * v1
        this.matrix[4] = a4
        this.matrix[5] = a5
        return this
    }

    fun scale(vec2ToScaleBy: Array<Double>): Mat2d {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val v0 = vec2ToScaleBy[0]
        val v1 = vec2ToScaleBy[1]
        this.matrix[0] = a0 * v0
        this.matrix[1] = a1 * v0
        this.matrix[2] = a2 * v1
        this.matrix[3] = a3 * v1
        this.matrix[4] = a4
        this.matrix[5] = a5
        return this
    }

    /**
     * Translates the Mat2d by the dimensions in the given Vec2
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} matrixToTranslate the matrix to translate
     * @param {Vec2} vec2ToTranslateBy the Vec2 to translate the matrix by
     * @returns {Mat2d} inOut
     **/
    fun translate(vec2ToTranslateBy: Vec2): Mat2d {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val v0 = vec2ToTranslateBy[0]
        val v1 = vec2ToTranslateBy[1]
        this.matrix[0] = a0
        this.matrix[1] = a1
        this.matrix[2] = a2
        this.matrix[3] = a3
        this.matrix[4] = a0 * v0 + a2 * v1 + a4
        this.matrix[5] = a1 * v0 + a3 * v1 + a5
        return this
    }

    fun translate(vec2ToTranslateBy: Array<Double>): Mat2d {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val v0 = vec2ToTranslateBy[0]
        val v1 = vec2ToTranslateBy[1]
        this.matrix[0] = a0
        this.matrix[1] = a1
        this.matrix[2] = a2
        this.matrix[3] = a3
        this.matrix[4] = a0 * v0 + a2 * v1 + a4
        this.matrix[5] = a1 * v0 + a3 * v1 + a5
        return this
    }

    /**
     * Creates a matrix from a given angle
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat2d.identityDoubleArray(dest);
     *     Mat2d.rotate(dest, dest, angleToRotateByInRad);
     *
     * @param {Mat2d} inOut Mat2d receiving operation result
     * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
     * @returns {Mat2d} inOut
     */
    fun fromRotation(angleToRotateByInRad: Double): Mat2d {
        val s = Math.sin(angleToRotateByInRad)
        val c = Math.cos(angleToRotateByInRad)
        this.matrix[0] = c
        this.matrix[1] = s
        this.matrix[2] = -s
        this.matrix[3] = c
        this.matrix[4] = 0.0
        this.matrix[5] = 0.0
        return this
    }

    /**
     * Creates a matrix from a vector scaling
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat2d.identityDoubleArray(dest);
     *     Mat2d.scale(dest, dest, vec);
     *
     * @param {Mat2d} inOut Mat2d receiving operation result
     * @param {Vec2} scalingVec2 Scaling vector
     * @returns {Mat2d} inOut
     */
    fun fromScaling(scalingVec2: Vec2): Mat2d {
        this.matrix[0] = scalingVec2[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = scalingVec2[1]
        this.matrix[4] = 0.0
        this.matrix[5] = 0.0
        return this
    }

    fun fromScaling(scalingVec2: Array<Double>): Mat2d {
        this.matrix[0] = scalingVec2[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = scalingVec2[1]
        this.matrix[4] = 0.0
        this.matrix[5] = 0.0
        return this
    }

    /**
     * Creates a matrix from a vector translation
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat2d.identityDoubleArray(dest);
     *     Mat2d.translate(dest, dest, vec);
     *
     * @param {Mat2d} inOut Mat2d receiving operation result
     * @param {Vec2} translationVec2 Translation vector
     * @returns {Mat2d} inOut
     */
    fun fromTranslation(translationVec2: Vec2): Mat2d {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 1.0
        this.matrix[4] = translationVec2[0]
        this.matrix[5] = translationVec2[1]
        return this
    }

    fun fromTranslation(translationVec2: Array<Double>): Mat2d {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 1.0
        this.matrix[4] = translationVec2[0]
        this.matrix[5] = translationVec2[1]
        return this
    }


    /**
     * Returns matrix string representation of matrix Mat2d
     *
     * @param {Mat2d} matrix matrix to represent as matrix string
     * @returns {String} string representation of the matrix
     */
    override fun toString(): String {
        return "Mat2d(${this.matrix[0]}, ${this.matrix[1]}, ${this.matrix[2]}, ${this.matrix[3]})"
    }

    /**
     * Returns Frobenius norm of matrix Mat2d
     *
     * @param {Mat2d} matrix the matrix to calculate Frobenius norm of
     * @returns {Number} Frobenius norm
     */
    fun frob(): Double {
        return (Math.sqrt(Math.pow(this.matrix[0], 2.0)
                + Math.pow(this.matrix[1], 2.0)
                + Math.pow(this.matrix[2], 2.0)
                + Math.pow(this.matrix[3], 2.0)
                + Math.pow(this.matrix[4], 2.0)
                + Math.pow(this.matrix[5], 2.0) + 1))
    }

    /**
     * Multiply each element of the matrix by matrixToScale scalar.
     *
     * @param {Mat2d} inOut the receiving matrix
     * @param {Mat2d} matrixToScale the matrix to scale
     * @param {Number} amountToScaleBy amount to scale the matrix's elements by
     * @returns {Mat2d} inOut
     */
    fun multiplyScalar(amountToScaleBy: Double): Mat2d {
        this.matrix[0] *= amountToScaleBy
        this.matrix[1] *= amountToScaleBy
        this.matrix[2] *= amountToScaleBy
        this.matrix[3] *= amountToScaleBy
        this.matrix[4] *= amountToScaleBy
        this.matrix[5] *= amountToScaleBy
        return this
    }

    /**
     * Adds two Mat2d's after multiplying each element of the second operand by firstSummand scalar value.
     *
     * @param {Mat2d} inOut the receiving vector
     * @param {Mat2d} firstSummand the first operand
     * @param {Mat2d} secondSummand the second operand
     * @param {Number} amountToScale the amount to amountToScale secondSummand's elements by before adding
     * @returns {Mat2d} inOut
     */
    fun multiplyScalarAndAdd(summand: Mat2d, amountToScale: Double): Mat2d {
        this.matrix[0] += (summand[0] * amountToScale)
        this.matrix[1] += (summand[1] * amountToScale)
        this.matrix[2] += (summand[2] * amountToScale)
        this.matrix[3] += (summand[3] * amountToScale)
        this.matrix[4] += (summand[4] * amountToScale)
        this.matrix[5] += (summand[5] * amountToScale)
        return this
    }

    /**
     * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
     *
     * @param {Mat2d} firstMatrix The first matrix.
     * @param {Mat2d} secondMatrix The second matrix.
     * @returns {Boolean} True if the matrices are equal, false otherwise.
     */
    fun exactEquals(matrix: Mat2d): Boolean {
        return this.matrix[0] == matrix[0]
                && this.matrix[1] == matrix[1]
                && this.matrix[2] == matrix[2]
                && this.matrix[3] == matrix[3]
                && this.matrix[4] == matrix[4]
                && this.matrix[5] == matrix[5]
    }

    /**
     * Returns whether or not the matrices have approximately the same elements in the same position.
     *
     * @param {Mat2d} firstMatrix The first matrix.
     * @param {Mat2d} secondMatrix The second matrix.
     * @returns {Boolean} True if the matrices are equal, false otherwise.
     */
    fun equals(matrix: Mat2d): Boolean {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val a4 = this.matrix[4]
        val a5 = this.matrix[5]
        val b0 = matrix[0]
        val b1 = matrix[1]
        val b2 = matrix[2]
        val b3 = matrix[3]
        val b4 = matrix[4]
        val b5 = matrix[5]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)))
    }


    companion object {

        /**
         * Copy the values from one Mat2d to another
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} toCopy the toCopy matrix
         * @returns {Mat2d} inOut
         */
        fun copy(toCopy: Mat2d, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            return inOut
        }

        fun copy(toCopy: DoubleArray, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            return inOut
        }

        fun copy(toCopy: Float32Array, inOut: Float32Array = Float32Array(6)): Float32Array {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            inOut[4] = toCopy[4]
            inOut[5] = toCopy[5]
            return inOut
        }

        /**
         * Creates matrixToClone new Mat2d initialized with values from an existing matrix
         *
         * @param {Mat2d} matrixToClone matrix to clone
         * @returns {Mat2d} matrixToClone new 2x3 matrix
         */
        fun clone(matrixToClone: Mat2d, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = matrixToClone[0]
            inOut[1] = matrixToClone[1]
            inOut[2] = matrixToClone[2]
            inOut[3] = matrixToClone[3]
            inOut[4] = matrixToClone[4]
            inOut[5] = matrixToClone[5]
            return inOut
        }

        fun clone(matrixToClone: DoubleArray, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = matrixToClone[0]
            inOut[1] = matrixToClone[1]
            inOut[2] = matrixToClone[2]
            inOut[3] = matrixToClone[3]
            inOut[4] = matrixToClone[4]
            inOut[5] = matrixToClone[5]
            return inOut
        }

        fun clone(matrixToClone: Float32Array, inOut: Float32Array = Float32Array(6)): Float32Array {
            inOut[0] = matrixToClone[0]
            inOut[1] = matrixToClone[1]
            inOut[2] = matrixToClone[2]
            inOut[3] = matrixToClone[3]
            inOut[4] = matrixToClone[4]
            inOut[5] = matrixToClone[5]
            return inOut
        }

        /**
         * Set a Mat2d to the identityDoubleArray matrix
         *
         * @param {Mat2d} inOut the receiving matrix
         * @returns {Mat2d} inOut
         */
        fun identity(inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            inOut[4] = 0.0
            inOut[5] = 0.0
            return inOut
        }

        fun identityDoubleArray(inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            inOut[4] = 0.0
            inOut[5] = 0.0
            return inOut
        }

        fun identityFloat32Array(inOut: Float32Array = Float32Array(6)): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 1.0f
            inOut[4] = 0.0f
            inOut[5] = 0.0f
            return inOut
        }

        /**
         * Create a new Mat2d with the given values
         *
         * @param {Number} a Component A (index 0)
         * @param {Number} b Component B (index 1)
         * @param {Number} c Component C (index 2)
         * @param {Number} d Component D (index 3)
         * @param {Number} tx Component TX (index 4)
         * @param {Number} ty Component TY (index 5)
         * @returns {Mat2d} A new Mat2d
         */
        fun fromValues(a: Double, b: Double, c: Double, d: Double, tx: Double, ty: Double): DoubleArray {
            val out = DoubleArray(6)
            out[0] = a
            out[1] = b
            out[2] = c
            out[3] = d
            out[4] = tx
            out[5] = ty
            return out
        }

        fun fromValues(a: Float, b: Float, c: Float, d: Float, tx: Float, ty: Float): Float32Array {
            val out = Float32Array(6)
            out[0] = a
            out[1] = b
            out[2] = c
            out[3] = d
            out[4] = tx
            out[5] = ty
            return out
        }

        /**
         * Set the components of a Mat2d to the given values
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Number} a Component A (index 0)
         * @param {Number} b Component B (index 1)
         * @param {Number} c Component C (index 2)
         * @param {Number} d Component D (index 3)
         * @param {Number} tx Component TX (index 4)
         * @param {Number} ty Component TY (index 5)
         * @returns {Mat2d} inOut
         */
        fun set(a: Double, b: Double, c: Double, d: Double, tx: Double, ty: Double, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = a
            inOut[1] = b
            inOut[2] = c
            inOut[3] = d
            inOut[4] = tx
            inOut[5] = ty
            return inOut
        }

        fun set(a: Float, b: Float, c: Float, d: Float, tx: Float, ty: Float, inOut: Float32Array = Float32Array(6)): Float32Array {
            inOut[0] = a
            inOut[1] = b
            inOut[2] = c
            inOut[3] = d
            inOut[4] = tx
            inOut[5] = ty
            return inOut
        }

        /**
         * Inverts source Mat2d
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} source the source matrix
         * @returns {Mat2d} inOut
         */
        fun invert(source: Mat2d, inOut: Mat2d = Mat2d()): Mat2d {
            val aa = source[0]
            val ab = source[1]
            val ac = source[2]
            val ad = source[3]
            val atx = source[4]
            val aty = source[5]
            var det = aa * ad - ab * ac
            if (det < 0) {
                return Mat2d()
            }
            det = 1.0 / det
            inOut[0] = ad * det
            inOut[1] = -ab * det
            inOut[2] = -ac * det
            inOut[3] = aa * det
            inOut[4] = (ac * aty - ad * atx) * det
            inOut[5] = (ab * atx - aa * aty) * det
            return inOut
        }

        fun invert(source: DoubleArray, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val aa = source[0]
            val ab = source[1]
            val ac = source[2]
            val ad = source[3]
            val atx = source[4]
            val aty = source[5]
            var det = aa * ad - ab * ac
            if (det < 0) {
                return DoubleArray(6)
            }
            det = 1.0 / det
            inOut[0] = ad * det
            inOut[1] = -ab * det
            inOut[2] = -ac * det
            inOut[3] = aa * det
            inOut[4] = (ac * aty - ad * atx) * det
            inOut[5] = (ab * atx - aa * aty) * det
            return inOut
        }

        fun invert(source: Float32Array, inOut: Float32Array = Float32Array(6)): Float32Array {
            val aa = source[0]
            val ab = source[1]
            val ac = source[2]
            val ad = source[3]
            val atx = source[4]
            val aty = source[5]
            var det = aa * ad - ab * ac
            if (det < 0) {
                return Float32Array(6)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = ad * det
            inOut[1] = -ab * det
            inOut[2] = -ac * det
            inOut[3] = aa * det
            inOut[4] = (ac * aty - ad * atx) * det
            inOut[5] = (ab * atx - aa * aty) * det
            return inOut
        }

        /**
         * Calculates the determinant of source Mat2d
         *
         * @param {Mat2d} source the source matrix
         * @returns {Number} determinant of source
         */
        fun determinant(source: Mat2d): Double {
            return source[0] * source[3] - source[1] * source[2]
        }

        fun determinant(source: DoubleArray): Double {
            return source[0] * source[3] - source[1] * source[2]
        }

        fun determinant(source: Float32Array): Float {
            return source[0] * source[3] - source[1] * source[2]
        }

        /**
         * Multiplies two Mat2d's
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} multiplicand the first operand
         * @param {Mat2d} multiplicand the second operand
         * @returns {Mat2d} inOut
         */
        fun multiply(multiplicand: Mat2d, multiplier: Mat2d, inOut: Mat2d = Mat2d()): Mat2d {
            val a0 = multiplicand[0]
            val a1 = multiplicand[1]
            val a2 = multiplicand[2]
            val a3 = multiplicand[3]
            val a4 = multiplicand[4]
            val a5 = multiplicand[5]
            val b0 = multiplier[0]
            val b1 = multiplier[1]
            val b2 = multiplier[2]
            val b3 = multiplier[3]
            val b4 = multiplier[4]
            val b5 = multiplier[5]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            inOut[4] = a0 * b4 + a2 * b5 + a4
            inOut[5] = a1 * b4 + a3 * b5 + a5
            return inOut
        }

        fun multiply(multiplicand: DoubleArray, multiplier: DoubleArray, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val a0 = multiplicand[0]
            val a1 = multiplicand[1]
            val a2 = multiplicand[2]
            val a3 = multiplicand[3]
            val a4 = multiplicand[4]
            val a5 = multiplicand[5]
            val b0 = multiplier[0]
            val b1 = multiplier[1]
            val b2 = multiplier[2]
            val b3 = multiplier[3]
            val b4 = multiplier[4]
            val b5 = multiplier[5]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            inOut[4] = a0 * b4 + a2 * b5 + a4
            inOut[5] = a1 * b4 + a3 * b5 + a5
            return inOut
        }

        fun multiply(multiplicand: Float32Array, multiplier: Float32Array, inOut: Float32Array = Float32Array(6)): Float32Array {
            val a0 = multiplicand[0]
            val a1 = multiplicand[1]
            val a2 = multiplicand[2]
            val a3 = multiplicand[3]
            val a4 = multiplicand[4]
            val a5 = multiplicand[5]
            val b0 = multiplier[0]
            val b1 = multiplier[1]
            val b2 = multiplier[2]
            val b3 = multiplier[3]
            val b4 = multiplier[4]
            val b5 = multiplier[5]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            inOut[4] = a0 * b4 + a2 * b5 + a4
            inOut[5] = a1 * b4 + a3 * b5 + a5
            return inOut
        }

        /**
         * Rotates matrixToRotate Mat2d by the given angle
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat2d} inOut
         */
        fun rotate(matrixToRotate: Mat2d, angleInRad: Double, inOut: Mat2d = Mat2d()): Mat2d {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        fun rotate(matrixToRotate: DoubleArray, angleInRad: Double, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        fun rotate(matrixToRotate: Float32Array, angleInRad: Double, inOut: Float32Array = Float32Array(6)): Float32Array {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        /**
         * Scales the Mat2d by the dimensions in the given Vec2
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} matrixToRotate the matrix to rotate
         * @param {Vec2} vec2ToScaleBy the Vec2 to scale the matrix by
         * @returns {Mat2d} inOut
         **/
        fun scale(matrixToRotate: Mat2d, vec2ToScaleBy: Vec2, inOut: Mat2d = Mat2d()): Mat2d {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val v0 = vec2ToScaleBy[0]
            val v1 = vec2ToScaleBy[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        fun scale(matrixToRotate: Mat2d, vec2ToScaleBy: Array<Double>, inOut: Mat2d = Mat2d()): Mat2d {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val v0 = vec2ToScaleBy[0]
            val v1 = vec2ToScaleBy[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        fun scale(matrixToRotate: DoubleArray, vec2ToScaleBy: Vec2, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val v0 = vec2ToScaleBy[0]
            val v1 = vec2ToScaleBy[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        fun scale(matrixToRotate: DoubleArray, vec2ToScaleBy: Array<Double>, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val a4 = matrixToRotate[4]
            val a5 = matrixToRotate[5]
            val v0 = vec2ToScaleBy[0]
            val v1 = vec2ToScaleBy[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            inOut[4] = a4
            inOut[5] = a5
            return inOut
        }

        /**
         * Translates the Mat2d by the dimensions in the given Vec2
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} matrixToTranslate the matrix to translate
         * @param {Vec2} vec2ToTranslateBy the Vec2 to translate the matrix by
         * @returns {Mat2d} inOut
         **/
        fun translate(matrixToTranslate: Mat2d, vec2ToTranslateBy: Vec2, inOut: Mat2d = Mat2d()): Mat2d {
            val a0 = matrixToTranslate[0]
            val a1 = matrixToTranslate[1]
            val a2 = matrixToTranslate[2]
            val a3 = matrixToTranslate[3]
            val a4 = matrixToTranslate[4]
            val a5 = matrixToTranslate[5]
            val v0 = vec2ToTranslateBy[0]
            val v1 = vec2ToTranslateBy[1]
            inOut[0] = a0
            inOut[1] = a1
            inOut[2] = a2
            inOut[3] = a3
            inOut[4] = a0 * v0 + a2 * v1 + a4
            inOut[5] = a1 * v0 + a3 * v1 + a5
            return inOut
        }

        fun translate(matrixToTranslate: Mat2d, vec2ToTranslateBy: Array<Double>, inOut: Mat2d = Mat2d()): Mat2d {
            val a0 = matrixToTranslate[0]
            val a1 = matrixToTranslate[1]
            val a2 = matrixToTranslate[2]
            val a3 = matrixToTranslate[3]
            val a4 = matrixToTranslate[4]
            val a5 = matrixToTranslate[5]
            val v0 = vec2ToTranslateBy[0]
            val v1 = vec2ToTranslateBy[1]
            inOut[0] = a0
            inOut[1] = a1
            inOut[2] = a2
            inOut[3] = a3
            inOut[4] = a0 * v0 + a2 * v1 + a4
            inOut[5] = a1 * v0 + a3 * v1 + a5
            return inOut
        }

        fun translate(matrixToTranslate: DoubleArray, vec2ToTranslateBy: Vec2, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val a0 = matrixToTranslate[0]
            val a1 = matrixToTranslate[1]
            val a2 = matrixToTranslate[2]
            val a3 = matrixToTranslate[3]
            val a4 = matrixToTranslate[4]
            val a5 = matrixToTranslate[5]
            val v0 = vec2ToTranslateBy[0]
            val v1 = vec2ToTranslateBy[1]
            inOut[0] = a0
            inOut[1] = a1
            inOut[2] = a2
            inOut[3] = a3
            inOut[4] = a0 * v0 + a2 * v1 + a4
            inOut[5] = a1 * v0 + a3 * v1 + a5
            return inOut
        }

        fun translate(matrixToTranslate: DoubleArray, vec2ToTranslateBy: Array<Double>, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val a0 = matrixToTranslate[0]
            val a1 = matrixToTranslate[1]
            val a2 = matrixToTranslate[2]
            val a3 = matrixToTranslate[3]
            val a4 = matrixToTranslate[4]
            val a5 = matrixToTranslate[5]
            val v0 = vec2ToTranslateBy[0]
            val v1 = vec2ToTranslateBy[1]
            inOut[0] = a0
            inOut[1] = a1
            inOut[2] = a2
            inOut[3] = a3
            inOut[4] = a0 * v0 + a2 * v1 + a4
            inOut[5] = a1 * v0 + a3 * v1 + a5
            return inOut
        }


        /**
         * Creates a matrix from a given angle
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat2d.identityDoubleArray(dest);
         *     Mat2d.rotate(dest, dest, angleToRotateByInRad);
         *
         * @param {Mat2d} inOut Mat2d receiving operation result
         * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
         * @returns {Mat2d} inOut
         */
        fun fromRotation(angleToRotateByInRad: Double, inOut: Mat2d = Mat2d()): Mat2d {
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            inOut[4] = 0.0
            inOut[5] = 0.0
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            inOut[4] = 0.0
            inOut[5] = 0.0
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, inOut: Float32Array = Float32Array(6)): Float32Array {
            val s = (Math.sin(angleToRotateByInRad)).toFloat()
            val c = (Math.cos(angleToRotateByInRad)).toFloat()
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            inOut[4] = 0.0f
            inOut[5] = 0.0f
            return inOut
        }

        /**
         * Creates a matrix from a vector scaling
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat2d.identityDoubleArray(dest);
         *     Mat2d.scale(dest, dest, vec);
         *
         * @param {Mat2d} inOut Mat2d receiving operation result
         * @param {Vec2} scalingVec2 Scaling vector
         * @returns {Mat2d} inOut
         */
        fun fromScaling(scalingVec2: Vec2, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = scalingVec2[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = scalingVec2[1]
            inOut[4] = 0.0
            inOut[5] = 0.0
            return inOut
        }

        fun fromScaling(scalingVec2: Vec2, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = scalingVec2[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = scalingVec2[1]
            inOut[4] = 0.0
            inOut[5] = 0.0
            return inOut
        }

        fun fromScaling(scalingVec2: Array<Double>, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = scalingVec2[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = scalingVec2[1]
            inOut[4] = 0.0
            inOut[5] = 0.0
            return inOut
        }

        /**
         * Creates a matrix from a vector translation
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat2d.identityDoubleArray(dest);
         *     Mat2d.translate(dest, dest, vec);
         *
         * @param {Mat2d} inOut Mat2d receiving operation result
         * @param {Vec2} translationVec2 Translation vector
         * @returns {Mat2d} inOut
         */
        fun fromTranslation(translationVec2: Vec2, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            inOut[4] = translationVec2[0]
            inOut[5] = translationVec2[1]
            return inOut
        }

        fun fromTranslation(translationVec2: Array<Double>, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            inOut[4] = translationVec2[0]
            inOut[5] = translationVec2[1]
            return inOut
        }

        fun fromTranslation(translationVec2: Vec2, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            inOut[4] = translationVec2[0]
            inOut[5] = translationVec2[1]
            return inOut
        }

        fun fromTranslation(translationVec2: Array<Double>, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            inOut[4] = translationVec2[0]
            inOut[5] = translationVec2[1]
            return inOut
        }

        /**
         * Returns matrix string representation of matrix Mat2d
         *
         * @param {Mat2d} matrix matrix to represent as matrix string
         * @returns {String} string representation of the matrix
         */
        fun toString(matrix: Mat2d): String {
            return "Mat2d(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        fun toString(matrix: DoubleArray): String {
            return "Mat2d(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        fun toString(matrix: Float32Array): String {
            return "Mat2d(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        /**
         * Returns Frobenius norm of matrix Mat2d
         *
         * @param {Mat2d} matrix the matrix to calculate Frobenius norm of
         * @returns {Number} Frobenius norm
         */
        fun frob(matrix: Mat2d): Double {
            return (Math.sqrt(Math.pow(matrix[0], 2.0) + Math.pow(matrix[1], 2.0)
                    + Math.pow(matrix[2], 2.0) + Math.pow(matrix[3], 2.0)
                    + Math.pow(matrix[4], 2.0) + Math.pow(matrix[5], 2.0) + 1))
        }

        fun frob(matrix: DoubleArray): Double {
            return (Math.sqrt(Math.pow(matrix[0], 2.0) + Math.pow(matrix[1], 2.0)
                    + Math.pow(matrix[2], 2.0) + Math.pow(matrix[3], 2.0)
                    + Math.pow(matrix[4], 2.0) + Math.pow(matrix[5], 2.0) + 1))
        }

        fun frob(matrix: Float32Array): Double {
            return (Math.sqrt(Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(matrix[1].toDouble(), 2.0)
                    + Math.pow(matrix[2].toDouble(), 2.0) + Math.pow(matrix[3].toDouble(), 2.0)
                    + Math.pow(matrix[4].toDouble(), 2.0) + Math.pow(matrix[5].toDouble(), 2.0) + 1))
        }

        /**
         * Adds two Mat2d's
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} firstSummand the first operand
         * @param {Mat2d} secondSummand the second operand
         * @returns {Mat2d} inOut
         */
        fun add(firstSummand: Mat2d, secondSummand: Mat2d, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            return inOut
        }

        fun add(firstSummand: DoubleArray, secondSummand: DoubleArray, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            return inOut
        }

        fun add(firstSummand: Float32Array, secondSummand: Float32Array, inOut: Float32Array = Float32Array(6)): Float32Array {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            inOut[4] = firstSummand[4] + secondSummand[4]
            inOut[5] = firstSummand[5] + secondSummand[5]
            return inOut
        }

        /**
         * Subtracts matrix subtrahend from matrix minuend
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} minuend the first operand
         * @param {Mat2d} subtrahend the second operand
         * @returns {Mat2d} inOut
         */
        fun subtract(minuend: Mat2d, subtrahend: Mat2d, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            return inOut
        }

        fun subtract(minuend: DoubleArray, subtrahend: DoubleArray, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            return inOut
        }

        fun subtract(inOut: Float32Array, minuend: Float32Array, subtrahend: Float32Array): Float32Array {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            inOut[4] = minuend[4] - subtrahend[4]
            inOut[5] = minuend[5] - subtrahend[5]
            return inOut
        }

        /**
         * Multiply each element of the matrix by matrixToScale scalar.
         *
         * @param {Mat2d} inOut the receiving matrix
         * @param {Mat2d} matrixToScale the matrix to scale
         * @param {Number} amountToScaleBy amount to scale the matrix's elements by
         * @returns {Mat2d} inOut
         */
        fun multiplyScalar(matrixToScale: Mat2d, amountToScaleBy: Double, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = matrixToScale[0] * amountToScaleBy
            inOut[1] = matrixToScale[1] * amountToScaleBy
            inOut[2] = matrixToScale[2] * amountToScaleBy
            inOut[3] = matrixToScale[3] * amountToScaleBy
            inOut[4] = matrixToScale[4] * amountToScaleBy
            inOut[5] = matrixToScale[5] * amountToScaleBy
            return inOut
        }

        fun multiplyScalar(matrixToScale: DoubleArray, amountToScaleBy: Double, inOut: DoubleArray = DoubleArray(6)): DoubleArray {
            inOut[0] = matrixToScale[0] * amountToScaleBy
            inOut[1] = matrixToScale[1] * amountToScaleBy
            inOut[2] = matrixToScale[2] * amountToScaleBy
            inOut[3] = matrixToScale[3] * amountToScaleBy
            inOut[4] = matrixToScale[4] * amountToScaleBy
            inOut[5] = matrixToScale[5] * amountToScaleBy
            return inOut
        }

        fun multiplyScalar(matrixToScale: Float32Array, amountToScaleBy: Double, inOut: Float32Array = Float32Array(6)): Float32Array {
            val amountToScaleTheMatrix = amountToScaleBy.toFloat()
            inOut[0] = matrixToScale[0] * amountToScaleTheMatrix
            inOut[1] = matrixToScale[1] * amountToScaleTheMatrix
            inOut[2] = matrixToScale[2] * amountToScaleTheMatrix
            inOut[3] = matrixToScale[3] * amountToScaleTheMatrix
            inOut[4] = matrixToScale[4] * amountToScaleTheMatrix
            inOut[5] = matrixToScale[5] * amountToScaleTheMatrix
            return inOut
        }

        /**
         * Adds two Mat2d's after multiplying each element of the second operand by firstSummand scalar value.
         *
         * @param {Mat2d} inOut the receiving vector
         * @param {Mat2d} firstSummand the first operand
         * @param {Mat2d} secondSummand the second operand
         * @param {Number} amountToScale the amount to amountToScale secondSummand's elements by before adding
         * @returns {Mat2d} inOut
         */
        fun multiplyScalarAndAdd(firstSummand: Mat2d, secondSummand: Mat2d, amountToScale: Double, inOut: Mat2d = Mat2d()): Mat2d {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScale)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScale)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScale)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScale)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScale)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScale)
            return inOut
        }

        fun multiplyScalarAndAdd(firstSummand: DoubleArray, secondSummand: DoubleArray, amountToScale: Double, inOut: DoubleArray = DoubleArray((6))): DoubleArray {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScale)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScale)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScale)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScale)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScale)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScale)
            return inOut
        }

        fun multiplyScalarAndAdd(firstSummand: Float32Array, secondSummand: Float32Array, amountToScale: Double, inOut: Float32Array = Float32Array((6))): Float32Array {
            val amountToScaleTheMatrix = amountToScale.toFloat()
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleTheMatrix)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleTheMatrix)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleTheMatrix)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleTheMatrix)
            inOut[4] = firstSummand[4] + (secondSummand[4] * amountToScaleTheMatrix)
            inOut[5] = firstSummand[5] + (secondSummand[5] * amountToScaleTheMatrix)
            return inOut
        }

        /**
         * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
         *
         * @param {Mat2d} firstMatrix The first matrix.
         * @param {Mat2d} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun exactEquals(firstMatrix: Mat2d, secondMatrix: Mat2d): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1]
                    && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
                    && firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5]
        }

        fun exactEquals(firstMatrix: DoubleArray, secondMatrix: DoubleArray): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1]
                    && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
                    && firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5]
        }

        fun exactEquals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1]
                    && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
                    && firstMatrix[4] == secondMatrix[4] && firstMatrix[5] == secondMatrix[5]
        }

        /**
         * Returns whether or not the matrices have approximately the same elements in the same position.
         *
         * @param {Mat2d} firstMatrix The first matrix.
         * @param {Mat2d} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun equals(firstMatrix: Mat2d, secondMatrix: Mat2d): Boolean {
            val a0 = firstMatrix[0]
            val a1 = firstMatrix[1]
            val a2 = firstMatrix[2]
            val a3 = firstMatrix[3]
            val a4 = firstMatrix[4]
            val a5 = firstMatrix[5]
            val b0 = secondMatrix[0]
            val b1 = secondMatrix[1]
            val b2 = secondMatrix[2]
            val b3 = secondMatrix[3]
            val b4 = secondMatrix[4]
            val b5 = secondMatrix[5]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)))
        }

        fun equals(firstMatrix: DoubleArray, secondMatrix: DoubleArray): Boolean {
            val a0 = firstMatrix[0]
            val a1 = firstMatrix[1]
            val a2 = firstMatrix[2]
            val a3 = firstMatrix[3]
            val a4 = firstMatrix[4]
            val a5 = firstMatrix[5]
            val b0 = secondMatrix[0]
            val b1 = secondMatrix[1]
            val b2 = secondMatrix[2]
            val b3 = secondMatrix[3]
            val b4 = secondMatrix[4]
            val b5 = secondMatrix[5]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)))
        }

        fun equals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            val a0 = firstMatrix[0].toDouble()
            val a1 = firstMatrix[1].toDouble()
            val a2 = firstMatrix[2].toDouble()
            val a3 = firstMatrix[3].toDouble()
            val a4 = firstMatrix[4].toDouble()
            val a5 = firstMatrix[5].toDouble()
            val b0 = secondMatrix[0].toDouble()
            val b1 = secondMatrix[1].toDouble()
            val b2 = secondMatrix[2].toDouble()
            val b3 = secondMatrix[3].toDouble()
            val b4 = secondMatrix[4].toDouble()
            val b5 = secondMatrix[5].toDouble()
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)) &&
                    Math.abs(a4 - b4) <= EPSILON * Math.max(1.0, Math.abs(a4), Math.abs(b4)) &&
                    Math.abs(a5 - b5) <= EPSILON * Math.max(1.0, Math.abs(a5), Math.abs(b5)))
        }

        /**
         * Returns a Float32Array of a Mat2
         *
         * @param {Mat2} a matrix to represent as a Float32Array
         * @returns {Mat2} Float32Array
         */
        fun toFloat32Array(matrix: DoubleArray): Float32Array {
            val output = Float32Array(6)
            output[0] = matrix[0].toFloat()
            output[1] = matrix[1].toFloat()
            output[2] = matrix[2].toFloat()
            output[3] = matrix[3].toFloat()
            output[4] = matrix[3].toFloat()
            output[5] = matrix[3].toFloat()
            return output
        }

        /**
         * Returns a DoubleArray of a Mat2
         *
         * @param {Mat2} a matrix to represent as a DoubleArray
         * @returns {Mat2} DoubleArray
         */
        fun toDoubleArray(matrix: Float32Array): DoubleArray {
            val output = DoubleArray(6)
            output[0] = matrix[0].toDouble()
            output[1] = matrix[1].toDouble()
            output[2] = matrix[2].toDouble()
            output[3] = matrix[3].toDouble()
            output[4] = matrix[3].toDouble()
            output[5] = matrix[3].toDouble()
            return output
        }
    }
}