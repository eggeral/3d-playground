package glmatrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.set
import kotlin.js.Math

class Mat2() : GlMatrix() {

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
}