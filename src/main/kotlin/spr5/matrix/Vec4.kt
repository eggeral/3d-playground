package spr5.matrix

import kotlin.js.Math

class Vec4() : glMatrix() {

    private val vector: Array<Double> = arrayOf(0.0, 0.0, 0.0, 0.0)

    constructor(x: Double, y: Double, z: Double, w: Double) : this() {
        vector[0] = x
        vector[1] = y
        vector[2] = z
        vector[3] = w
    }

    operator fun get(index: Int): Double {
        return vector[index]
    }

    operator fun set(index: Int, value: Double) {
        vector[index] = value
    }

    /**
     * Creates vec4ToClone new Vec4 initialized with values from an existing vector
     *
     * @param {Vec4} vec4ToClone vector to clone
     * @returns {Vec4} vec4ToClone new 4D vector
     */
    fun clone(): Vec4 {
        return Vec4(this.vector[0], this.vector[1], this.vector[2], this.vector[3])

    }

    /**
     * Copy the values from one Vec4 to another
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} toCopy the source vector
     * @returns {Vec4} inOut
     */
    fun copy(source: Vec4): Vec4 {
        this.vector[0] = source[0]
        this.vector[1] = source[1]
        this.vector[2] = source[2]
        this.vector[3] = source[3]
        return this
    }

    /**
     * Adds two Vec4's
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} firstSummand the first operand
     * @param {Vec4} secondSummand the second operand
     * @returns {Vec4} inOut
     */
    fun add(summand: Vec4): Vec4 {
        this.vector[0] += summand[0]
        this.vector[1] += summand[1]
        this.vector[2] += summand[2]
        this.vector[3] += summand[3]
        return this
    }

    operator fun plus(summand: Vec4): Vec4 {
        return clone().add(summand);
    }

    /**
     * Subtracts vector subtrahend from vector minuend
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} minuend the first operand
     * @param {Vec4} subtrahend the second operand
     * @returns {Vec4} inOut
     */
    fun subtract(subtrahend: Vec4): Vec4 {
        this.vector[0] -= subtrahend[0]
        this.vector[1] -= subtrahend[1]
        this.vector[2] -= subtrahend[2]
        this.vector[3] -= subtrahend[3]
        return this
    }

    operator fun minus(subtrahend: Vec4): Vec4 {
        return clone().subtract(subtrahend);
    }

    /**
     * Multiplies two Vec4's
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} multiplier the first operand
     * @param {Vec4} multiplicand the second operand
     * @returns {Vec4} inOut
     */
    fun multiply(multiplier: Vec4): Vec4 {
        this.vector[0] *= multiplier[0]
        this.vector[1] *= multiplier[1]
        this.vector[2] *= multiplier[2]
        this.vector[3] *= multiplier[3]
        return this
    }

    operator fun times(multiplier: Vec4): Vec4 {
        return multiplier.clone().multiply(this);
    }

    /**
     * Divides two Vec4's
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} dividend the first operand
     * @param {Vec4} divisor the second operand
     * @returns {Vec4} inOut
     */
    fun divide(divisor: Vec4): Vec4 {
        this.vector[0] /= divisor[0]
        this.vector[1] /= divisor[1]
        this.vector[2] /= divisor[2]
        this.vector[3] /= divisor[3]
        return this
    }

    operator fun div(divisor: Vec4): Vec4 {
        return clone().divide(divisor);
    }

    /**
     * Math.ceil the components of vec4ToCeil Vec4
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToCeil vector to ceil
     * @returns {Vec4} inOut
     */
    fun ceil(): IntArray {
        val output = IntArray(4)
        output[0] = Math.ceil(this.vector[0])
        output[1] = Math.ceil(this.vector[1])
        output[2] = Math.ceil(this.vector[2])
        output[3] = Math.ceil(this.vector[3])
        return output
    }

    /**
     * Math.floor the components of vec4ToFloor Vec4
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToFloor vector to floor
     * @returns {Vec4} inOut
     */
    fun floor(): IntArray {
        val output = IntArray(4)
        output[0] = Math.floor(this.vector[0])
        output[1] = Math.floor(this.vector[1])
        output[2] = Math.floor(this.vector[2])
        output[3] = Math.floor(this.vector[3])
        return output
    }

    /**
     * Returns the minimum of two Vec4's
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} firstOperand the first operand
     * @param {Vec4} secondOperand the second operand
     * @returns {Vec4} inOut
     */
    fun min(operand: Array<Double>): Vec4 {
        return Vec4(Math.min(this.vector[0], operand[0]),
                Math.min(this.vector[1], operand[1]),
                Math.min(this.vector[2], operand[2]),
                Math.min(this.vector[3], operand[3]))
    }

    /**
     * Returns the maximum of two Vec4's
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} firstOperand the first operand
     * @param {Vec4} secondOperand the second operand
     * @returns {Vec4} inOut
     */
    fun max(operand: Array<Double>): Vec4 {
        return Vec4(Math.max(this.vector[0], operand[0]),
                Math.max(this.vector[1], operand[1]),
                Math.max(this.vector[2], operand[2]),
                Math.max(this.vector[3], operand[3]))
    }

    /**
     * Math.round the components of vec4ToRound Vec4
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToRound vector to round
     * @returns {Vec4} inOut
     */
    fun round(): Vec4 {
        return Vec4((Math.round(this.vector[0])).toDouble(),
                (Math.round(this.vector[1])).toDouble(),
                (Math.round(this.vector[2])).toDouble(),
                (Math.round(this.vector[3])).toDouble())
    }

    /**
     * Scales vec4ToScale Vec4 by vec4ToScale scalar number
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToScale the vector to scale
     * @param {Number} amountToScaleBy amount to scale the vector by
     * @returns {Vec4} inOut
     */
    fun scale(amountToScaleBy: Double): Vec4 {
        val output = Vec4()
        output[0] = this.vector[0] * amountToScaleBy
        output[1] = this.vector[1] * amountToScaleBy
        output[2] = this.vector[2] * amountToScaleBy
        output[3] = this.vector[3] * amountToScaleBy
        return output
    }

    /**
     * Adds two Vec4's after scaling the second operand by firstSummand scalar value
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} firstSummand the first operand
     * @param {Vec4} secondSummand the second operand
     * @param {Number} amountToScale the amount to amountToScale secondSummand by before adding
     * @returns {Vec4} inOut
     */
    fun scaleAndAdd(summand: Vec4, amountToScale: Double): Vec4 {
        val output = Vec4()
        output[0] = this.vector[0] + (summand[0] * amountToScale)
        output[1] = this.vector[1] + (summand[1] * amountToScale)
        output[2] = this.vector[2] + (summand[2] * amountToScale)
        output[3] = this.vector[3] + (summand[3] * amountToScale)
        return output
    }

    /**
     * Calculates the euclidian distance between two Vec4's
     *
     * @param {Vec4} firstOperand the first operand
     * @param {Vec4} secondOperand the second operand
     * @returns {Number} distance between firstOperand and secondOperand
     */
    fun distance(operand: Vec4): Double {
        val x = this.vector[0] - operand[0]
        val y = this.vector[1] - operand[1]
        val z = this.vector[2] - operand[2]
        val w = this.vector[3] - operand[3]
        return Math.sqrt(x * x + y * y + z * z + w * w)
    }

    /**
     * Calculates the squared euclidian distance between two Vec4's
     *
     * @param {Vec4} firstOperand the first operand
     * @param {Vec4} secondOperand the second operand
     * @returns {Number} squared distance between firstOperand and secondOperand
     */
    fun squaredDistance(operand: Vec4): Double {
        val x = this.vector[0] - operand[0]
        val y = this.vector[1] - operand[1]
        val z = this.vector[2] - operand[2]
        val w = this.vector[3] - operand[3]
        return x * x + y * y + z * z + w * w
    }

    /**
     * Calculates the length of vec4ToCalculateLengthOf Vec4
     *
     * @param {Vec4} vec4ToCalculateLengthOf vector to calculate length of
     * @returns {Number} length of vec4ToCalculateLengthOf
     */
    fun length(): Double {
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        val w = this.vector[3]
        return Math.sqrt(x * x + y * y + z * z + w * w)
    }

    /**
     * Calculates the squared length of vec4ToCalculateSquaredLength Vec4
     *
     * @param {Vec4} vec4ToCalculateSquaredLength vector to calculate squared length of
     * @returns {Number} squared length of vec4ToCalculateSquaredLength
     */
    fun squaredLength(): Double {
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        val w = this.vector[3]
        return x * x + y * y + z * z + w * w
    }

    /**
     * Calculates the size of Vec4
     *
     * @returns {Number} elements of Vec4
     */
    fun size(): Int {
        return this.vector.size
    }

    /**
     * Negates the components of vec4ToNegate Vec4
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToNegate vector to negate
     * @returns {Vec4} inOut
     */
    fun negate(): Vec4 {
        val output = Vec4()
        output[0] = -this.vector[0]
        output[1] = -this.vector[1]
        output[2] = -this.vector[2]
        output[3] = -this.vector[3]
        return this
    }

    /**
     * Returns the inverse of the components of vec4ToInvert Vec4
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToInvert vector to invert
     * @returns {Vec4} inOut
     */
    fun inverse(): Vec4 {
        val output = Vec4()
        output[0] = 1.0 / this.vector[0]
        output[1] = 1.0 / this.vector[1]
        output[2] = 1.0 / this.vector[2]
        output[3] = 1.0 / this.vector[3]
        return output
    }

    /**
     * Normalize vec4ToNormalize Vec4
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToNormalize vector to normalize
     * @returns {Vec4} inOut
     */
    fun normalize(): Vec4 {
        val output = Vec4()
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        val w = this.vector[3]
        var len = x * x + y * y + z * z + w * w
        if (len > 0) {
            len = 1 / Math.sqrt(len)
            output[0] = x * len
            output[1] = y * len
            output[2] = z * len
            output[3] = w * len
        }
        return output
    }

    /**
     * Calculates the dot product of two Vec4's
     *
     * @param {Vec4} firstOperand the first operand
     * @param {Vec4} secondOperand the second operand
     * @returns {Number} dot product of firstOperand and secondOperand
     */
    fun dot(operand: Vec4): Double {
        return this.vector[0] * operand[0] + this.vector[1] * operand[1] + this.vector[2] * operand[2] + this.vector[3] * operand[3]
    }

    /**
     * Performs firstOperand linear interpolation between two Vec4's
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} firstOperand the first operand
     * @param {Vec4} secondOperand the second operand
     * @param {Number} interpolationAmount interpolation amount between the two inputs
     * @returns {Vec4} inOut
     */
    fun lerp(operand: Vec4, interpolationAmount: Double): Vec4 {
        val output = Vec4()
        val ax = this.vector[0]
        val ay = this.vector[1]
        val az = this.vector[2]
        val aw = this.vector[3]
        output[0] = ax + interpolationAmount * (operand[0] - ax)
        output[1] = ay + interpolationAmount * (operand[1] - ay)
        output[2] = az + interpolationAmount * (operand[2] - az)
        output[3] = aw + interpolationAmount * (operand[3] - aw)
        return output
    }

    /**
     * Generates a random vector with the given scale
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Number} [vectorScale] Length of the resulting vector. If ommitted, a unit vector will be returned
     * @returns {Vec4} inOut
     */
    fun random(vectorScale: Double = 1.0): Vec4 {
        val output = Vec4(RANDOM, RANDOM, RANDOM, RANDOM)
        return output.normalize().scale(vectorScale)
    }

    /**
     * Transforms the Vec4 with a Mat4.
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} a the vector to transform
     * @param {Mat4} m matrix to transform with
     * @returns {Vec4} inOut
     */
    fun transformMat4(mat4ToTransformWith: DoubleArray): Vec4 {
        val output = Vec4()
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        val w = this.vector[3]
        output[0] = mat4ToTransformWith[0] * x + mat4ToTransformWith[4] * y + mat4ToTransformWith[8] * z + mat4ToTransformWith[12] * w
        output[1] = mat4ToTransformWith[1] * x + mat4ToTransformWith[5] * y + mat4ToTransformWith[9] * z + mat4ToTransformWith[13] * w
        output[2] = mat4ToTransformWith[2] * x + mat4ToTransformWith[6] * y + mat4ToTransformWith[10] * z + mat4ToTransformWith[14] * w
        output[3] = mat4ToTransformWith[3] * x + mat4ToTransformWith[7] * y + mat4ToTransformWith[11] * z + mat4ToTransformWith[15] * w
        return output
    }

    /**
     * Transforms the Vec4 with vec4ToTransform Quat
     *
     * @param {Vec4} inOut the receiving vector
     * @param {Vec4} vec4ToTransform the vector to transform
     * @param {Quat} quatToTransformWith vector to transform with
     * @returns {Vec4} inOut
     */
    fun transformQuat(quatToTransformWith: Quat): Vec4 {
        val output = Vec4()
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        val qx = quatToTransformWith[0]
        val qy = quatToTransformWith[1]
        val qz = quatToTransformWith[2]
        val qw = quatToTransformWith[3]
        // calculate Quat * vec
        val ix = qw * x + qy * z - qz * y
        val iy = qw * y + qz * x - qx * z
        val iz = qw * z + qx * y - qy * x
        val iw = -qx * x - qy * y - qz * z
        // calculate result * inverse Quat
        output[0] = ix * qw + iw * -qx + iy * -qz - iz * -qy
        output[1] = iy * qw + iw * -qy + iz * -qx - ix * -qz
        output[2] = iz * qw + iw * -qz + ix * -qy - iy * -qx
        output[3] = this.vector[3]
        return output
    }

    /**
     * Returns a string representation of a vector
     *
     * @param {Vec4} a vector to represent as a string
     * @returns {String} string representation of the vector
     */
    override fun toString(): String {
        return "Vec4(${this.vector[0]}, ${this.vector[1]}, ${this.vector[2]}, ${this.vector[3]})"
    }

    /**
     * Returns whether or not the vectors have exactly the same elements in the same position (when compared with ===)
     *
     * @param {Vec4} firstVector The first vector.
     * @param {Vec4} secondVector The second vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun exactEquals(vector: Vec4): Boolean {
        return this.vector[0] == vector[0] && this.vector[1] == vector[1] && this.vector[2] == vector[2] && this.vector[3] == vector[3]
    }

    /**
     * Returns whether or not the vectors have approximately the same elements in the same position.
     *
     * @param {Vec4} firstVector The first vector.
     * @param {Vec4} secondVector The second vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun equals(vector: Vec4): Boolean {
        val a0 = this.vector[0]
        val a1 = this.vector[1]
        val a2 = this.vector[2]
        val a3 = this.vector[3]
        val b0 = vector[0]
        val b1 = vector[1]
        val b2 = vector[2]
        val b3 = vector[3]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
    }
}
