package spr5.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class Mat2() : glMatrix() {

    private val matrix: Array<Double> = arrayOf(1.0, 0.0, 0.0, 1.0)

    constructor(m00: Double, m01: Double, m10: Double, m11: Double) : this() {
        matrix[0] = m00
        matrix[1] = m01
        matrix[2] = m10
        matrix[3] = m11
    }

    operator fun get(index: Int): Double {
        return matrix[index]
    }

    operator fun set(index: Int, value: Double) {
        matrix[index] = value
    }

    /**
     * Copy the values from one Mat2 to another
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} toCopy the source matrix
     * @returns {Mat2} out
     */
    fun copy(source: Mat2): Mat2 {
        this.matrix[0] = source[0]
        this.matrix[1] = source[1]
        this.matrix[2] = source[2]
        this.matrix[3] = source[3]
        return this
    }

    /**
     * Creates matrixToClone new Mat2 initialized with values from an existing matrix
     *
     * @param {Mat2} matrixToClone matrix to clone
     * @returns {Mat2} matrixToClone new 2x2 matrix
     */
    fun clone(): Mat2 {
        return Mat2(this.matrix[0],
                this.matrix[1],
                this.matrix[2],
                this.matrix[3])
    }

    /**
     * Set a Mat2 to the identityDoubleArray matrix
     *
     * @param {Mat2} out the receiving matrix
     * @returns {Mat2} out
     */
    fun identity(): Mat2 {
        this.matrix[0] = 1.0
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = 1.0
        return this
    }


    /**
     * Adds two Mat2's
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} firstSummand the first operand
     * @param {Mat2} secondSummand the second operand
     * @returns {Mat2} out
     */
    fun add(summand: Mat2): Mat2 {
        this.matrix[0] += summand[0]
        this.matrix[1] += summand[1]
        this.matrix[2] += summand[2]
        this.matrix[3] += summand[3]
        return this
    }

    operator fun plus(summand: Mat2): Mat2 {
        return clone().add(summand);
    }

    /**
     * Subtracts matrix subtrahend from matrix minuend
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} minuend the first operand
     * @param {Mat2} subtrahend the second operand
     * @returns {Mat2} out
     */
    fun subtract(subtrahend: Mat2): Mat2 {
        this.matrix[0] -= subtrahend[0]
        this.matrix[1] -= subtrahend[1]
        this.matrix[2] -= subtrahend[2]
        this.matrix[3] -= subtrahend[3]
        return this
    }

    operator fun minus(subtrahend: Mat2): Mat2 {
        return clone().subtract(subtrahend);
    }

    /**
     * Multiplies two Mat2's
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} multiplier the first operand
     * @param {Mat2} multiplicand the second operand
     * @returns {Mat2} out
     */
    fun multiply(multiplier: Mat2): Mat2 {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        this.matrix[0] = a0 * multiplier[0] + a2 * multiplier[1]
        this.matrix[1] = a1 * multiplier[0] + a3 * multiplier[1]
        this.matrix[2] = a0 * multiplier[2] + a2 * multiplier[3]
        this.matrix[3] = a1 * multiplier[2] + a3 * multiplier[3]
        return this
    }

    operator fun times(multiplier: Mat2): Mat2 {
        return multiplier.clone().multiply(this);
    }

    /**
     * Transpose the values of source Mat2
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} source the source matrix
     * @returns {Mat2} out
     */
    fun transpose(source: Mat2): Mat2 {
        // If we are transposing ourselves we can skip source few steps but have to cache
        // some values
        if (this === source) {
            val a1 = source[1]
            this.matrix[1] = source[2]
            this.matrix[2] = a1
        } else {
            this.matrix[0] = source[0]
            this.matrix[1] = source[2]
            this.matrix[2] = source[1]
            this.matrix[3] = source[3]
        }
        return this
    }

    /**
     * Inverts source Mat2
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} source the source matrix
     * @returns {Mat2} out
     */
    fun invert(): Mat2 {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        // Calculate the determinant
        var det = a0 * a3 - a2 * a1
        if (det < 0) {
            return Mat2()
        }
        det = 1.0 / det
        this.matrix[0] = a3 * det
        this.matrix[1] = -a1 * det
        this.matrix[2] = -a2 * det
        this.matrix[3] = a0 * det
        return this
    }

    /**
     * Calculates the adjugate of source Mat2
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} source the source matrix
     * @returns {Mat2} out
     */
    fun adjoint(): Mat2 {
        // Caching this.matrix value is nessecary if out == source
        val a0 = this.matrix[0]
        this.matrix[0] = this.matrix[3]
        this.matrix[1] = -this.matrix[1]
        this.matrix[2] = -this.matrix[2]
        this.matrix[3] = a0
        return this
    }

    /**
     * Calculates the determinant of source Mat2
     *
     * @param {Mat2} source the source matrix
     * @returns {Number} determinant of source
     */
    fun determinant(): Double {
        return this.matrix[0] * this.matrix[3] - this.matrix[2] * this.matrix[1]
    }

    /**
     * Rotates matrixToRotate Mat2 by the given angle
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} matrixToRotate the matrix to rotate
     * @param {Number} angleInRad the angle to rotate the matrix by
     * @returns {Mat2} out
     */
    fun rotate(angleInRad: Double): Mat2 {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val s = Math.sin(angleInRad)
        val c = Math.cos(angleInRad)
        this.matrix[0] = a0 * c + a2 * s
        this.matrix[1] = a1 * c + a3 * s
        this.matrix[2] = a0 * -s + a2 * c
        this.matrix[3] = a1 * -s + a3 * c
        return this
    }

    /**
     * Scales the Mat2 by the dimensions in the given Vec2
     *
     * @param {Mat2} out the receiving matrix
     * @param {Mat2} matrixToRotate the matrix to rotate
     * @param {Vec2} vec2ToScaleBy the Vec2 to scale the matrix by
     * @returns {Mat2} out
     **/
    fun scale(vec2ToScaleBy: Vec2): Mat2 {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val v0 = vec2ToScaleBy[0]
        val v1 = vec2ToScaleBy[1]
        this.matrix[0] = a0 * v0
        this.matrix[1] = a1 * v0
        this.matrix[2] = a2 * v1
        this.matrix[3] = a3 * v1
        return this
    }

    fun scale(vec2ToScaleBy: Array<Double>): Mat2 {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val v0 = vec2ToScaleBy[0]
        val v1 = vec2ToScaleBy[1]
        this.matrix[0] = a0 * v0
        this.matrix[1] = a1 * v0
        this.matrix[2] = a2 * v1
        this.matrix[3] = a3 * v1
        return this
    }

    /**
     * Creates a matrix from a given angle
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat2.identityDoubleArray(dest);
     *     Mat2.rotate(dest, dest, angleToRotateByInRad);
     *
     * @param {Mat2} out Mat2 receiving operation result
     * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
     * @returns {Mat2} out
     */
    fun fromRotation(angleToRotateByInRad: Double): Mat2 {
        val s = Math.sin(angleToRotateByInRad)
        val c = Math.cos(angleToRotateByInRad)
        this.matrix[0] = c
        this.matrix[1] = s
        this.matrix[2] = -s
        this.matrix[3] = c
        return this
    }

    /**
     * Creates a matrix from a vector scaling
     * this.matrix is equivalent to (but much faster than):
     *
     *     Mat2.identityDoubleArray(dest);
     *     Mat2.scale(dest, dest, vec);
     *
     * @param {Mat2} out Mat2 receiving operation result
     * @param {Vec2} scalingVec2 Scaling vector
     * @returns {Mat2} out
     */
    fun fromScaling(scalingVec2: Vec2): Mat2 {
        this.matrix[0] = scalingVec2[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = scalingVec2[1]
        return this
    }

    fun fromScaling(scalingVec2: Array<Double>): Mat2 {
        this.matrix[0] = scalingVec2[0]
        this.matrix[1] = 0.0
        this.matrix[2] = 0.0
        this.matrix[3] = scalingVec2[1]
        return this
    }

    /**
     * Returns L, D and U matrices (Lower triangular, Diagonal and Upper triangular) by factorizing the input matrix
     * @param {Mat2} L the lower triangular matrix
     * @param {Mat2} D the diagonal matrix
     * @param {Mat2} U the upper triangular matrix
     * @param {Mat2} a the input matrix to factorize
     */
    fun LDU(lowerTriangularMatrix: Mat2, diagonalMatrix: Mat2, upperTriangularMatrix: Mat2, matrixToFactorize: Mat2): Triple<Mat2, Mat2, Mat2> {
        lowerTriangularMatrix[2] = matrixToFactorize[2] / matrixToFactorize[0]
        upperTriangularMatrix[0] = matrixToFactorize[0]
        upperTriangularMatrix[1] = matrixToFactorize[1]
        upperTriangularMatrix[3] = matrixToFactorize[3] - lowerTriangularMatrix[2] * upperTriangularMatrix[1]
        return Triple(lowerTriangularMatrix, diagonalMatrix, upperTriangularMatrix)
    }

    /**
     * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
     *
     * @param {Mat2} firstMatrix The first matrix.
     * @param {Mat2} secondMatrix The second matrix.
     * @returns {Boolean} True if the matrices are equal, false otherwise.
     */
    fun exactEquals(matrix: Mat2): Boolean {
        return this.matrix[0] == matrix[0] && this.matrix[1] == matrix[1] && this.matrix[2] == matrix[2] && this.matrix[3] == matrix[3]
    }

    /**
     * Returns whether or not the matrices have approximately the same elements in the same position.
     *
     * @param {Mat2} firstMatrix The first matrix.
     * @param {Mat2} secondMatrix The second matrix.
     * @returns {Boolean} True if the matrices are equal, false otherwise.
     */
    fun equals(matrix: Mat2): Boolean {
        val a0 = this.matrix[0]
        val a1 = this.matrix[1]
        val a2 = this.matrix[2]
        val a3 = this.matrix[3]
        val b0 = matrix[0]
        val b1 = matrix[1]
        val b2 = matrix[2]
        val b3 = matrix[3]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
    }

    /**
     * Returns a string representation of a Mat2
     *
     * @param {Mat2} a matrix to represent as a string
     * @returns {String} string representation of the matrix
     */
    override fun toString(): String {
        return "Mat2(${this.matrix[0]}, ${this.matrix[1]}, ${this.matrix[2]}, ${this.matrix[3]})"
    }

    /**
     * Returns a Float32Array of a Mat2
     *
     * @param {Mat2} a matrix to represent as a Float32Array
     * @returns {Mat2} Float32Array
     */
    fun toFloat32Array(): Float32Array {
        val output = Float32Array(4)
        output[0] = this.matrix[0].toFloat()
        output[1] = this.matrix[1].toFloat()
        output[2] = this.matrix[2].toFloat()
        output[3] = this.matrix[3].toFloat()
        return output
    }

    /**
     * Returns a DoubleArray of a Mat2
     *
     * @param {Mat2} a matrix to represent as a DoubleArray
     * @returns {Mat2} DoubleArray
     */
    fun toDoubleArray(): DoubleArray {
        val output = DoubleArray(4)
        output[0] = this.matrix[0]
        output[1] = this.matrix[1]
        output[2] = this.matrix[2]
        output[3] = this.matrix[3]
        return output
    }


    companion object {

        /**
         * Copy the values from one Mat2 to another
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} toCopy the source matrix
         * @returns {Mat2} out
         */
        fun copy(source: Mat2, inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = source[0]
            inOut[1] = source[1]
            inOut[2] = source[2]
            inOut[3] = source[3]
            return inOut
        }

        fun copy(toCopy: DoubleArray, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            return inOut
        }

        fun copy(toCopy: Float32Array, inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            return inOut
        }

        /**
         * Set a Mat2 to the identityDoubleArray matrix
         *
         * @param {Mat2} out the receiving matrix
         * @returns {Mat2} out
         */
        fun identity(inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            return inOut
        }

        fun identityDoubleArray(inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            return inOut
        }

        fun identityFloat32Array(inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 1.0f
            return inOut
        }

        /**
         * Create a new Mat2 with the given values
         *
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m10 Component in column 1, row 0 position (index 2)
         * @param {Number} m11 Component in column 1, row 1 position (index 3)
         * @returns {Mat2} out A new 2x2 matrix
         */
        fun fromValues(m00: Double, m01: Double, m10: Double, m11: Double, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m10
            inOut[3] = m11
            return inOut
        }

        fun fromValues(m00: Float, m01: Float, m10: Float, m11: Float, inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m10
            inOut[3] = m11
            return inOut
        }

        /**
         * Set the components of a Mat2 to the given values
         *
         * @param {Mat2} out the receiving matrix
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m10 Component in column 1, row 0 position (index 2)
         * @param {Number} m11 Component in column 1, row 1 position (index 3)
         * @returns {Mat2} out
         */
        fun set(m00: Double, m01: Double, m10: Double, m11: Double, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m10
            inOut[3] = m11
            return inOut
        }

        fun set(m00: Float, m01: Float, m10: Float, m11: Float, inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m10
            inOut[3] = m11
            return inOut
        }

        /**
         * Transpose the values of source Mat2
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} source the source matrix
         * @returns {Mat2} out
         */
        fun transpose(source: Mat2, inOut: Mat2 = Mat2()): Mat2 {
            // If we are transposing ourselves we can skip source few steps but have to cache
            // some values
            if (inOut === source) {
                val a1 = source[1]
                inOut[1] = source[2]
                inOut[2] = a1
            } else {
                inOut[0] = source[0]
                inOut[1] = source[2]
                inOut[2] = source[1]
                inOut[3] = source[3]
            }
            return inOut
        }

        fun transpose(source: DoubleArray, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            // If we are transposing ourselves we can skip source few steps but have to cache
            // some values
            if (inOut === source) {
                val a1 = source[1]
                inOut[1] = source[2]
                inOut[2] = a1
            } else {
                inOut[0] = source[0]
                inOut[1] = source[2]
                inOut[2] = source[1]
                inOut[3] = source[3]
            }
            return inOut
        }

        fun transpose(source: Float32Array, inOut: Float32Array = Float32Array(4)): Float32Array {
            // If we are transposing ourselves we can skip source few steps but have to cache
            // some values
            if (inOut === source) {
                val a1 = source[1]
                inOut[1] = source[2]
                inOut[2] = a1
            } else {
                inOut[0] = source[0]
                inOut[1] = source[2]
                inOut[2] = source[1]
                inOut[3] = source[3]
            }
            return inOut
        }

        /**
         * Inverts source Mat2
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} source the source matrix
         * @returns {Mat2} out
         */
        fun invert(source: Mat2, inOut: Mat2 = Mat2()): Mat2 {
            val a0 = source[0]
            val a1 = source[1]
            val a2 = source[2]
            val a3 = source[3]
            // Calculate the determinant
            var det = a0 * a3 - a2 * a1
            if (det < 0) {
                return Mat2()
            }
            det = 1.0 / det
            inOut[0] = a3 * det
            inOut[1] = -a1 * det
            inOut[2] = -a2 * det
            inOut[3] = a0 * det
            return inOut
        }

        fun invert(source: DoubleArray, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            val a0 = source[0]
            val a1 = source[1]
            val a2 = source[2]
            val a3 = source[3]
            // Calculate the determinant
            var det = a0 * a3 - a2 * a1
            if (det < 0) {
                return DoubleArray(4)
            }
            det = 1.0 / det
            inOut[0] = a3 * det
            inOut[1] = -a1 * det
            inOut[2] = -a2 * det
            inOut[3] = a0 * det
            return inOut
        }

        fun invert(source: Float32Array, inOut: Float32Array = Float32Array(4)): Float32Array {
            val a0 = source[0]
            val a1 = source[1]
            val a2 = source[2]
            val a3 = source[3]
            // Calculate the determinant
            var det = a0 * a3 - a2 * a1
            if (det < 0) {
                return Float32Array(4)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = a3 * det
            inOut[1] = -a1 * det
            inOut[2] = -a2 * det
            inOut[3] = a0 * det
            return inOut
        }

        /**
         * Calculates the adjugate of source Mat2
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} source the source matrix
         * @returns {Mat2} out
         */
        fun adjoint(source: Mat2, inOut: Mat2 = Mat2()): Mat2 {
            // Caching this.matrix value is nessecary if out == source
            val a0 = source[0]
            inOut[0] = source[3]
            inOut[1] = -source[1]
            inOut[2] = -source[2]
            inOut[3] = a0
            return inOut
        }

        fun adjoint(source: DoubleArray, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            // Caching this.matrix value is nessecary if out == source
            val a0 = source[0]
            inOut[0] = source[3]
            inOut[1] = -source[1]
            inOut[2] = -source[2]
            inOut[3] = a0
            return inOut
        }

        fun adjoint(source: Float32Array, inOut: Float32Array = Float32Array(4)): Float32Array {
            // Caching this.matrix value is nessecary if out == source
            val a0 = source[0]
            inOut[0] = source[3]
            inOut[1] = -source[1]
            inOut[2] = -source[2]
            inOut[3] = a0
            return inOut
        }


        /**
         * Calculates the determinant of source Mat2
         *
         * @param {Mat2} source the source matrix
         * @returns {Number} determinant of source
         */
        fun determinant(source: Mat2): Double {
            return source[0] * source[3] - source[2] * source[1]
        }

        fun determinant(source: DoubleArray): Double {
            return source[0] * source[3] - source[2] * source[1]
        }

        fun determinant(source: Float32Array): Float {
            return source[0] * source[3] - source[2] * source[1]
        }

        /**
         * Multiplies two Mat2's
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} multiplier the first operand
         * @param {Mat2} multiplicand the second operand
         * @returns {Mat2} out
         */
        fun multiply(multiplicand: Mat2, multiplier: Mat2, inOut: Mat2 = Mat2()): Mat2 {
            val a0 = multiplicand[0]
            val a1 = multiplicand[1]
            val a2 = multiplicand[2]
            val a3 = multiplicand[3]
            val b0 = multiplier[0]
            val b1 = multiplier[1]
            val b2 = multiplier[2]
            val b3 = multiplier[3]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            return inOut
        }

        fun multiply(multiplicand: DoubleArray, multiplier: DoubleArray, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            val a0 = multiplicand[0]
            val a1 = multiplicand[1]
            val a2 = multiplicand[2]
            val a3 = multiplicand[3]
            val b0 = multiplier[0]
            val b1 = multiplier[1]
            val b2 = multiplier[2]
            val b3 = multiplier[3]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            return inOut
        }

        fun multiply(multiplicand: Float32Array, multiplier: Float32Array, inOut: Float32Array = Float32Array(4)): Float32Array {
            val a0 = multiplicand[0]
            val a1 = multiplicand[1]
            val a2 = multiplicand[2]
            val a3 = multiplicand[3]
            val b0 = multiplier[0]
            val b1 = multiplier[1]
            val b2 = multiplier[2]
            val b3 = multiplier[3]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            return inOut
        }

        /**
         * Rotates matrixToRotate Mat2 by the given angle
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {Mat2} out
         */
        fun rotate(matrixToRotate: Mat2, angleInRad: Double, inOut: Mat2 = Mat2()): Mat2 {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            return inOut
        }

        fun rotate(matrixToRotate: DoubleArray, angleInRad: Double, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val s = Math.sin(angleInRad)
            val c = Math.cos(angleInRad)
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            return inOut
        }

        fun rotate(matrixToRotate: Float32Array, angleInRad: Double, inOut: Float32Array = Float32Array(4)): Float32Array {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            return inOut
        }

        /**
         * Scales the Mat2 by the dimensions in the given Vec2
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} matrixToRotate the matrix to rotate
         * @param {Vec2} vec2ToScaleBy the Vec2 to scale the matrix by
         * @returns {Mat2} out
         **/
        fun scale(matrixToRotate: Mat2, vec2ToScaleBy: Vec2, inOut: Mat2 = Mat2()): Mat2 {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val v0 = vec2ToScaleBy[0]
            val v1 = vec2ToScaleBy[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            return inOut
        }

        fun scale(matrixToRotate: DoubleArray, vec2ToScaleBy: Vec2, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val v0 = vec2ToScaleBy[0]
            val v1 = vec2ToScaleBy[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            return inOut
        }

        fun scale(matrixToRotate: DoubleArray, vec2ToScaleBy: Array<Double>, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val v0 = vec2ToScaleBy[0]
            val v1 = vec2ToScaleBy[1]
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            return inOut
        }

        /**
         * Creates a matrix from a given angle
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat2.identityDoubleArray(dest);
         *     Mat2.rotate(dest, dest, angleToRotateByInRad);
         *
         * @param {Mat2} out Mat2 receiving operation result
         * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
         * @returns {Mat2} out
         */
        fun fromRotation(angleToRotateByInRad: Double, inOut: Mat2 = Mat2()): Mat2 {
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            val s = Math.sin(angleToRotateByInRad)
            val c = Math.cos(angleToRotateByInRad)
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            return inOut
        }

        fun fromRotation(angleToRotateByInRad: Double, inOut: Float32Array = Float32Array(4)): Float32Array {
            val s = (Math.sin(angleToRotateByInRad)).toFloat()
            val c = (Math.cos(angleToRotateByInRad)).toFloat()
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            return inOut
        }

        /**
         * Creates a matrix from a vector scaling
         * this.matrix is equivalent to (but much faster than):
         *
         *     Mat2.identityDoubleArray(dest);
         *     Mat2.scale(dest, dest, vec);
         *
         * @param {Mat2} out Mat2 receiving operation result
         * @param {Vec2} scalingVec2 Scaling vector
         * @returns {Mat2} out
         */
        fun fromScaling(scalingVec2: Vec2, inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = scalingVec2[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = scalingVec2[1]
            return inOut
        }

        fun fromScaling(scalingVec2: Array<Double>, inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = scalingVec2[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = scalingVec2[1]
            return inOut
        }

        fun fromScaling(scalingVec2: Vec2, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = scalingVec2[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = scalingVec2[1]
            return inOut
        }

        fun fromScaling(scalingVec2: Array<Double>, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = scalingVec2[0]
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = scalingVec2[1]
            return inOut
        }

        fun fromScaling(scalingVec2: Vec2, inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = scalingVec2[0].toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = scalingVec2[1].toFloat()
            return inOut
        }

        fun fromScaling(scalingVec2: Array<Double>, inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = scalingVec2[0].toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = scalingVec2[1].toFloat()
            return inOut
        }

        /**
         * Returns L, D and U matrices (Lower triangular, Diagonal and Upper triangular) by factorizing the input matrix
         * @param {Mat2} L the lower triangular matrix
         * @param {Mat2} D the diagonal matrix
         * @param {Mat2} U the upper triangular matrix
         * @param {Mat2} a the input matrix to factorize
         */
        fun LDU(lowerTriangularMatrix: Mat2, diagonalMatrix: Mat2, upperTriangularMatrix: Mat2, matrixToFactorize: Mat2): Triple<Mat2, Mat2, Mat2> {
            lowerTriangularMatrix[2] = matrixToFactorize[2] / matrixToFactorize[0]
            upperTriangularMatrix[0] = matrixToFactorize[0]
            upperTriangularMatrix[1] = matrixToFactorize[1]
            upperTriangularMatrix[3] = matrixToFactorize[3] - lowerTriangularMatrix[2] * upperTriangularMatrix[1]
            return Triple(lowerTriangularMatrix, diagonalMatrix, upperTriangularMatrix)
        }

        fun LDU(lowerTriangularMatrix: DoubleArray, diagonalMatrix: DoubleArray, upperTriangularMatrix: DoubleArray, matrixToFactorize: DoubleArray): Triple<DoubleArray, DoubleArray, DoubleArray> {
            lowerTriangularMatrix[2] = matrixToFactorize[2] / matrixToFactorize[0]
            upperTriangularMatrix[0] = matrixToFactorize[0]
            upperTriangularMatrix[1] = matrixToFactorize[1]
            upperTriangularMatrix[3] = matrixToFactorize[3] - lowerTriangularMatrix[2] * upperTriangularMatrix[1]
            return Triple(lowerTriangularMatrix, diagonalMatrix, upperTriangularMatrix)
        }

        fun LDU(lowerTriangularMatrix: Float32Array, diagonalMatrix: Float32Array, upperTriangularMatrix: Float32Array, matrixToFactorize: Float32Array): Triple<Float32Array, Float32Array, Float32Array> {
            lowerTriangularMatrix[2] = matrixToFactorize[2] / matrixToFactorize[0]
            upperTriangularMatrix[0] = matrixToFactorize[0]
            upperTriangularMatrix[1] = matrixToFactorize[1]
            upperTriangularMatrix[3] = matrixToFactorize[3] - lowerTriangularMatrix[2] * upperTriangularMatrix[1]
            return Triple(lowerTriangularMatrix, diagonalMatrix, upperTriangularMatrix)
        }

        /**
         * Adds two Mat2's
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} firstSummand the first operand
         * @param {Mat2} secondSummand the second operand
         * @returns {Mat2} out
         */
        fun add(firstSummand: Mat2, secondSummand: Mat2, inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            return inOut
        }

        fun add(firstSummand: DoubleArray, secondSummand: DoubleArray, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            return inOut
        }

        fun add(firstSummand: Float32Array, secondSummand: Float32Array, inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            return inOut
        }

        /**
         * Subtracts matrix subtrahend from matrix minuend
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} minuend the first operand
         * @param {Mat2} subtrahend the second operand
         * @returns {Mat2} out
         */
        fun subtract(minuend: Mat2, subtrahend: Mat2, inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            return inOut
        }

        fun subtract(minuend: DoubleArray, subtrahend: DoubleArray, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            return inOut
        }

        fun subtract(minuend: Float32Array, subtrahend: Float32Array, inOut: Float32Array = Float32Array(4)): Float32Array {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            return inOut
        }

        /**
         * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
         *
         * @param {Mat2} firstMatrix The first matrix.
         * @param {Mat2} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun exactEquals(firstMatrix: Mat2, secondMatrix: Mat2): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
        }

        fun exactEquals(firstMatrix: DoubleArray, secondMatrix: DoubleArray): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
        }

        fun exactEquals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
        }

        /**
         * Returns whether or not the matrices have approximately the same elements in the same position.
         *
         * @param {Mat2} firstMatrix The first matrix.
         * @param {Mat2} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun equals(firstMatrix: Mat2, secondMatrix: Mat2): Boolean {
            val a0 = firstMatrix[0]
            val a1 = firstMatrix[1]
            val a2 = firstMatrix[2]
            val a3 = firstMatrix[3]
            val b0 = secondMatrix[0]
            val b1 = secondMatrix[1]
            val b2 = secondMatrix[2]
            val b3 = secondMatrix[3]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
        }

        fun equals(firstMatrix: DoubleArray, secondMatrix: DoubleArray): Boolean {
            val a0 = firstMatrix[0]
            val a1 = firstMatrix[1]
            val a2 = firstMatrix[2]
            val a3 = firstMatrix[3]
            val b0 = secondMatrix[0]
            val b1 = secondMatrix[1]
            val b2 = secondMatrix[2]
            val b3 = secondMatrix[3]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
        }

        fun equals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            val a0 = firstMatrix[0].toDouble()
            val a1 = firstMatrix[1].toDouble()
            val a2 = firstMatrix[2].toDouble()
            val a3 = firstMatrix[3].toDouble()
            val b0 = secondMatrix[0].toDouble()
            val b1 = secondMatrix[1].toDouble()
            val b2 = secondMatrix[2].toDouble()
            val b3 = secondMatrix[3].toDouble()
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
        }

        /**
         * Multiply each element of the matrix by matrixToScale scalar.
         *
         * @param {Mat2} out the receiving matrix
         * @param {Mat2} matrixToScale the matrix to scale
         * @param {Number} amountToScaleBy amount to scale the matrix's elements by
         * @returns {Mat2} out
         */
        fun multiplyScalar(matrixToScale: Mat2, amountToScaleBy: Double, inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = matrixToScale[0] * amountToScaleBy
            inOut[1] = matrixToScale[1] * amountToScaleBy
            inOut[2] = matrixToScale[2] * amountToScaleBy
            inOut[3] = matrixToScale[3] * amountToScaleBy
            return inOut
        }

        fun multiplyScalar(matrixToScale: DoubleArray, amountToScaleBy: Double, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = matrixToScale[0] * amountToScaleBy
            inOut[1] = matrixToScale[1] * amountToScaleBy
            inOut[2] = matrixToScale[2] * amountToScaleBy
            inOut[3] = matrixToScale[3] * amountToScaleBy
            return inOut
        }

        fun multiplyScalar(matrixToScale: Float32Array, amountToScaleBy: Double, inOut: Float32Array = Float32Array(4)): Float32Array {
            val amountToScaleTheMatrix = amountToScaleBy.toFloat()
            inOut[0] = matrixToScale[0] * amountToScaleTheMatrix
            inOut[1] = matrixToScale[1] * amountToScaleTheMatrix
            inOut[2] = matrixToScale[2] * amountToScaleTheMatrix
            inOut[3] = matrixToScale[3] * amountToScaleTheMatrix
            return inOut
        }

        /**
         * Adds two Mat2's after multiplying each element of the second operand by firstSummand scalar value.
         *
         * @param {Mat2} out the receiving vector
         * @param {Mat2} firstSummand the first operand
         * @param {Mat2} secondSummand the second operand
         * @param {Number} amountToScaleBy the amount to amountToScaleBy secondSummand's elements by before adding
         * @returns {Mat2} out
         */
        fun multiplyScalarAndAdd(firstSummand: Mat2, secondSummand: Mat2, amountToScaleBy: Double, inOut: Mat2 = Mat2()): Mat2 {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleBy)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleBy)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleBy)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleBy)
            return inOut
        }

        fun multiplyScalarAndAdd(firstSummand: DoubleArray, secondSummand: DoubleArray, amountToScaleBy: Double, inOut: DoubleArray = DoubleArray(4)): DoubleArray {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleBy)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleBy)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleBy)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleBy)
            return inOut
        }

        fun multiplyScalarAndAdd(firstSummand: Float32Array, secondSummand: Float32Array, amountToScaleBy: Double, inOut: Float32Array = Float32Array(4)): Float32Array {
            val amountToScaleTheMatrix = amountToScaleBy.toFloat()
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleTheMatrix)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleTheMatrix)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleTheMatrix)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleTheMatrix)
            return inOut
        }

        /**
         * Returns a string representation of a Mat2
         *
         * @param {Mat2} a matrix to represent as a string
         * @returns {String} string representation of the matrix
         */
        fun toString(matrix: Mat2): String {
            return "Mat2(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        fun toString(matrix: DoubleArray): String {
            return "Mat2(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        fun toString(matrix: Float32Array): String {
            return "Mat2(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        /**
         * Returns Frobenius norm of matrix Mat2
         *
         * @param {Mat2} matrix the matrix to calculate Frobenius norm of
         * @returns {Number} Frobenius norm
         */
        fun frob(matrix: Mat2): Double {
            return (Math.sqrt(Math.pow(matrix[0], 2.0) + Math.pow(matrix[1], 2.0)
                    + Math.pow(matrix[2], 2.0) + Math.pow(matrix[3], 2.0)))
        }

        fun frob(matrix: DoubleArray): Double {
            return (Math.sqrt(Math.pow(matrix[0], 2.0) + Math.pow(matrix[1], 2.0)
                    + Math.pow(matrix[2], 2.0) + Math.pow(matrix[3], 2.0)))
        }

        fun frob(matrix: Float32Array): Double {
            return (Math.sqrt(Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(matrix[1].toDouble(), 2.0)
                    + Math.pow(matrix[2].toDouble(), 2.0) + Math.pow(matrix[3].toDouble(), 2.0)))
        }

        /**
         * Returns a Float32Array of a Mat2
         *
         * @param {Mat2} a matrix to represent as a Float32Array
         * @returns {Mat2} Float32Array
         */
        fun toFloat32Array(matrix: DoubleArray): Float32Array {
            val output = Float32Array(4)
            output[0] = matrix[0].toFloat()
            output[1] = matrix[1].toFloat()
            output[2] = matrix[2].toFloat()
            output[3] = matrix[3].toFloat()
            return output
        }

        /**
         * Returns a DoubleArray of a Mat2
         *
         * @param {Mat2} a matrix to represent as a DoubleArray
         * @returns {Mat2} DoubleArray
         */
        fun toDoubleArray(matrix: Float32Array): DoubleArray {
            val output = DoubleArray(4)
            output[0] = matrix[0].toDouble()
            output[1] = matrix[1].toDouble()
            output[2] = matrix[2].toDouble()
            output[3] = matrix[3].toDouble()
            return output
        }
    }
}