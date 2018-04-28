package glmatrix

import kotlin.js.Math

class Mat2d() : GlMatrix() {

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
     * Creates a new Mat2d initialized with values from an existing matrix
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
     * Set a Mat2d to the identity matrix
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
     * Inverts Mat2d
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
     * Calculates the determinant of Mat2d
     */
    fun determinant(): Double {
        return this.matrix[0] * this.matrix[3] - this.matrix[1] * this.matrix[2]
    }

    /**
     * Multiplies multiplier matrix with this
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

    /**
     * Multiplies multiplier matrix with * operator
     */
    operator fun times(multiplier: Mat2d): Mat2d {
        return multiplier.clone().multiply(this)
    }

    /**
     * Adds summand matrix to this
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

    /**
     * Adds summand matrix with + operator
     */
    operator fun plus(summand: Mat2d): Mat2d {
        return clone().add(summand)
    }

    /**
     * Subtracts subtrahend matrix from this
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

    /**
     * Subtracts subtrahend matrix with - operator
     */
    operator fun minus(subtrahend: Mat2d): Mat2d {
        return clone().subtract(subtrahend)
    }

    /**
     * Rotates matrixToRotate Mat2d by the given angle
     *
     * @param {Double} angleInRad the angle to rotate the matrix by
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
     * @param {Vec2} vec2ToScaleBy the Vec2 to scale the matrix by
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

    /**
     * Scales the Mat2d by the dimensions in the given Vec2
     *
     * @param {Array<Double>} vec2ToScaleBy the Vec2 to scale the matrix by
     **/
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
     * @param {Vec2} vec2ToTranslateBy the Vec2 to translate the matrix by
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

    /**
     * Translates the Mat2d by the dimensions in the given Vec2
     *
     * @param {Array<Double>} vec2ToTranslateBy the Vec2 to translate the matrix by
     **/
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
     *
     * @param {Double} angleToRotateByInRad the angle to rotate the matrix by
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
     *
     * @param {Vec2} scalingVec2 Scaling vector
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

    /**
     * Creates a matrix from a vector scaling
     *
     * @param {Array<Double>} scalingVec2 Scaling vector
     */
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
     *
     * @param {Vec2} translationVec2 Translation vector
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

    /**
     * Creates a matrix from a vector translation
     *
     * @param {Array<Double>} translationVec2 Translation vector
     */
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
     */
    override fun toString(): String {
        return "Mat2d(${this.matrix[0]}, ${this.matrix[1]}, ${this.matrix[2]}, ${this.matrix[3]})"
    }

    /**
     * Returns Frobenius norm of matrix Mat2d
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
     * Multiply each element of the matrix by amountToScaleBy
     *
     * @param {Double} amountToScaleBy amount to scale the matrix's elements by
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
}