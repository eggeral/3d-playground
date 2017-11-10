package spr5.matrix

import kotlin.js.Math

class Vec2() : glMatrix() {

    private val vector: Array<Double> = arrayOf(0.0, 0.0)

    constructor(componentX: Double, componentY: Double) : this() {
        vector[0] = componentX
        vector[1] = componentY
    }

    operator fun get(index: Int): Double {
        return vector[index]
    }

    operator fun set(index: Int, value: Double) {
        vector[index] = value
    }

    /**
     * Creates a new Vec2 initialized with values from an existing vector
     *
     * @param {Vec2} vec2ToClone vector to clone
     * @returns {Vec2} vec2ToClone new 2D vector
     */
    fun clone(): Vec2 {
        return Vec2(this.vector[0], this.vector[1])
    }

    /**
     * Copy the values from one Vec2 to another
     *
     * @param {Vec2} source the source vector
     * @returns {Vec2} inOut
     */
    fun copy(source: Vec2): Vec2 {
        this.vector[0] = source[0]
        this.vector[1] = source[1]
        return this
    }

    /**
     * Adds two Vec2's
     *
     * @param {Vec2} the vector to summand
     * @returns {Vec2} this.vector
     */
    fun add(summand: Vec2): Vec2 {
        this.vector[0] += summand[0]
        this.vector[1] += summand[1]
        return this
    }

    operator fun plus(summand: Vec2): Vec2 {
        return clone().add(summand);
    }

    /**
     * Subtracts the subtrahend from this.vector
     *
     * @param {Vec2} subtrahend the second operand
     * @returns {Vec2} this.vector
     */
    fun subtract(subtrahend: Vec2): Vec2 {
        this.vector[0] -= subtrahend[0]
        this.vector[1] -= subtrahend[1]
        return this
    }

    operator fun minus(subtrahend: Vec2): Vec2 {
        return clone().subtract(subtrahend);
    }

    /**
     * Multiplies two Vec2's
     *
     * @param {Vec2} multiplier the first operand
     * @returns {Vec2} this.vector
     */
    fun multiply(multiplier: Vec2): Vec2 {
        this.vector[0] *= multiplier[0]
        this.vector[1] *= multiplier[1]
        return this
    }

    operator fun times(multiplier: Vec2): Vec2 {
        return multiplier.clone().multiply(multiplier);
    }

    /**
     * Divides two Vec2's
     *
     * @param {Vec2} divisor the second operand
     * @returns {Vec2} this.vector
     */
    fun divide(divisor: Vec2): Vec2 {
        this.vector[0] /= divisor[0]
        this.vector[1] /= divisor[1]
        return this
    }

    operator fun div(divisor: Vec2): Vec2 {
        return clone().divide(divisor);
    }

    /**
     * Math.ceil the components of vec2ToCeil Vec2
     *
     * @param {Vec2} vec2ToCeil vector to ceil
     * @returns {Vec2} inOut
     */
    fun ceil(): IntArray {
        val output = IntArray(2)
        output[0] = Math.ceil(this.vector[0])
        output[1] = Math.ceil(this.vector[1])
        return output
    }

    /**
     * Math.floor the components of vec2ToFloor Vec2
     *
     * @param {Vec2} vec2ToFloor vector to floor
     * @returns {Vec2} inOut
     */
    fun floor(): IntArray {
        val output = IntArray(2)
        output[0] = Math.floor(this.vector[0])
        output[1] = Math.floor(this.vector[1])
        return output
    }

    /**
     * Returns the minimum of two Vec2's
     *
     * @param {Vec2} the operand
     * @returns {Vec2} new minimum Vec2
     */
    fun min(operand: Vec2): Vec2 {
        return Vec2(Math.min(this.vector[0], operand[0]),
                Math.min(this.vector[1], operand[1]))
    }

    /**
     * Returns the maximum of two Vec2's
     *
     * @param {Vec2} the operand
     * @returns {Vec2} new maximum Vec2
     */
    fun max(operand: Vec2): Vec2 {
        return Vec2(Math.max(this.vector[0], operand[0]),
                Math.max(this.vector[1], operand[1]))
    }

    /**
     * Math.round the components of vec2ToRound Vec2
     *
     * @param {Vec2} vec2ToRound the receiving vector
     * @returns {Vec2} new rounded Vec2
     */
    fun round(): Vec2 {
        return Vec2((Math.round(this[0])).toDouble(),
                (Math.round(this[1])).toDouble())
    }

    /**
     * Scales vec3ToScale Vec2 by vec3ToScale scalar number
     *
     * @param {Vec2} inOut the receiving vector
     * @param {Vec2} vec3ToScale the vector to scale
     * @param {Number} amountToScaleBy amount to scale the vector by
     * @returns {Vec2} inOut
     */
    fun scale(amountToScaleBy: Double): Vec2 {
        val output = Vec2()
        output[0] = this.vector[0] * amountToScaleBy
        output[1] = this.vector[1] * amountToScaleBy
        return output
    }

    /**
     * Adds two Vec2's after scaling the second operand by firstSummand scalar value
     *
     * @param {Vec2} inOut the receiving vector
     * @param {Vec2} firstSummand the first operand
     * @param {Vec2} secondSummand the second operand
     * @param {Number} amountToScale the amount to amountToScale secondSummand by before adding
     * @returns {Vec2} inOut
     */
    fun scaleAndAdd(summand: Vec2, amountToScale: Double): Vec2 {
        val output = Vec2()
        output[0] = this.vector[0] + (summand[0] * amountToScale)
        output[1] = this.vector[1] + (summand[1] * amountToScale)
        return output
    }

    /**
     * Calculates the euclidian distance between two Vec2's
     *
     * @param {Vec2} firstOperand the first operand
     * @param {Vec2} secondOperand the second operand
     * @returns {Number} distance between firstOperand and secondOperand
     */
    fun distance(operand: Vec2): Double {
        val x = this.vector[0] - operand[0]
        val y = this.vector[1] - operand[1]
        return Math.sqrt(x * x + y * y)
    }

    /**
     * Calculates the squared euclidian distance between two Vec2's
     *
     * @param {Vec2} firstOperand the first operand
     * @param {Vec2} secondOperand the second operand
     * @returns {Number} squared distance between firstOperand and secondOperand
     */
    fun squaredDistance(operand: Vec2): Double {
        val x = this.vector[0] - operand[0]
        val y = this.vector[1] - operand[1]
        return x * x + y * y
    }

    /**
     * Calculates the length of Vec2
     *
     * @returns {Number} length of this.vector
     */
    fun length(): Double {
        val x = this.vector[0]
        val y = this.vector[1]
        return Math.sqrt(x * x + y * y)
    }

    /**
     * Calculates the size of Vec2
     *
     * @returns {Number} elements of Vec2
     */
    fun size(): Int {
        return this.vector.size
    }

    /**
     * Calculates the squared length of Vec2
     *
     * @returns {Number} squared length of this.vector
     */
    fun squaredLength(): Double {
        val x = this.vector[0]
        val y = this.vector[1]
        return x * x + y * y
    }

    /**
     * Negates the components of vec2ToNegate Vec2
     *
     * @param {Vec2} inOut the receiving vector
     * @param {Vec2} vec2ToNegate vector to negate
     * @returns {Vec2} inOut
     */
    fun negate(): Vec2 {
        val output = Vec2()
        output[0] = -this.vector[0]
        output[1] = -this.vector[1]
        return output
    }

    /**
     * Returns the inverse of the components of vec2ToInvert Vec2
     *
     * @param {Vec2} inOut the receiving vector
     * @returns {Vec2} inOut
     */
    fun inverse(): Vec2 {
        val output = Vec2()
        output[0] = 1.0 / this.vector[0]
        output[1] = 1.0 / this.vector[1]
        return output
    }

    /**
     * Normalize vec2ToNormalize Vec2
     *
     * @param {Vec2} inOut the receiving vector
     * @returns {Vec2} inOut
     */
    fun normalize(): Vec2 {
        val output = Vec2()
        val x = this.vector[0]
        val y = this.vector[1]
        var len = x * x + y * y
        if (len > 0) {
            //TODO: evaluate use of glm_invsqrt here?
            len = 1 / Math.sqrt(len)
            output[0] = this.vector[0] * len
            output[1] = this.vector[1] * len
        }
        return output
    }

    /**
     * Calculates the dot product of two Vec2's
     *
     * @param {Vec2} the operand
     * @returns {Number} dot product of this.vector and operand
     */
    fun dot(operand: Vec2): Double {
        return this.vector[0] * operand[0] + this.vector[1] * operand[1]
    }

    /**
     * Computes the cross product of two Vec2's
     * Note that the cross product must by definition produce firstOperand 3D vector
     *
     * @param {Vec2} the operand
     * @returns {Vec3} a new crossed Vec2
     */
    fun cross(operand: Vec2): Vec2 {
        val output = Vec2()
        val z = this.vector[0] * operand[1] - this.vector[1] * operand[0]
        output[0] = 0.0
        output[1] = 0.0
        output[2] = z
        return output
    }

    /**
     * Performs a linear interpolation between two Vec2's
     *
     * @param {Vec2} the operand
     * @param {Number} interpolationAmount interpolation amount between the two inputs
     * @returns {Vec2} a new interpolated Vec2
     */
    fun lerp(operand: Vec2, interpolationAmount: Double): Vec2 {
        val output = Vec2()
        val ax = this.vector[0]
        val ay = this.vector[1]
        output[0] = ax + interpolationAmount * (operand[0] - ax)
        output[1] = ay + interpolationAmount * (operand[1] - ay)
        return output
    }

    /**
     * Generates a random vector with the given vec3ToScaleBy
     *
     * @param {Number} vec3ToScaleBy Length of the resulting vector. If ommitted, a unit vector will be returned
     * @returns {Vec2} a new random Vec2 with the given number to scale by
     */
    fun random(vec3ToScaleBy: Double = 1.0): Vec2 {
        val output = Vec2()
        val r = RANDOM * 2.0 * Math.PI
        output[0] = Math.cos(r) * vec3ToScaleBy
        output[1] = Math.sin(r) * vec3ToScaleBy
        return output
    }

    /**
     * Transforms the Vec2 with vec2ToTransform Mat2
     *
     * @param {Mat2} mat2ToTransformWith matrix to transform with
     * @returns {Vec2} a new transformed Vec2
     */
    fun transformMat2(mat2ToTransformWith: DoubleArray): Vec2 {
        val output = Vec2()
        val x = this.vector[0]
        val y = this.vector[1]
        output[0] = mat2ToTransformWith[0] * x + mat2ToTransformWith[2] * y
        output[1] = mat2ToTransformWith[1] * x + mat2ToTransformWith[3] * y
        return output
    }

    /**
     * Transforms the Vec2 with vec2ToTransform Mat2d
     *
     * @param {Mat2d} mat2dToTransformWith matrix to transform with
     * @returns {Vec2} a new transformed Vec2
     */
    fun transformMat2d(mat2dToTransformWith: DoubleArray): Vec2 {
        val output = Vec2()
        val x = this.vector[0]
        val y = this.vector[1]
        output[0] = mat2dToTransformWith[0] * x + mat2dToTransformWith[2] * y + mat2dToTransformWith[4]
        output[1] = mat2dToTransformWith[1] * x + mat2dToTransformWith[3] * y + mat2dToTransformWith[5]
        return output
    }

    /**
     * Transforms the Vec2 with vec2ToTransform Mat3
     * 3rd vector component is implicitly '1'
     *
     * @param {Mat3} mat3ToTransformWith matrix to transform with
     * @returns {Vec2} a new transformed Vec2
     */
    fun transformMat3(mat3ToTransformWith: DoubleArray): Vec2 {
        val output = Vec2()
        val x = this.vector[0]
        val y = this.vector[1]
        output[0] = mat3ToTransformWith[0] * x + mat3ToTransformWith[3] * y + mat3ToTransformWith[6]
        output[1] = mat3ToTransformWith[1] * x + mat3ToTransformWith[4] * y + mat3ToTransformWith[7]
        return output
    }

    /**
     * Transforms the Vec2 with vec2ToTransform Mat4
     * 3rd vector component is implicitly '0'
     * 4th vector component is implicitly '1'
     *
     * @param {Mat4} mat4ToTransformWith matrix to transform with
     * @returns {Vec2} a new transformed Vec2
     */
    fun transformMat4(mat4ToTransformWith: DoubleArray): Vec2 {
        val output = Vec2()
        val x = this.vector[0]
        val y = this.vector[1]
        output[0] = mat4ToTransformWith[0] * x + mat4ToTransformWith[4] * y + mat4ToTransformWith[12]
        output[1] = mat4ToTransformWith[1] * x + mat4ToTransformWith[5] * y + mat4ToTransformWith[13]
        return output
    }

    /**
     * Returns a string representation of a vector
     *
     * @returns {String} string representation of the vector
     */
    override fun toString(): String {
        return "Vec2(${this.vector[0]}, ${this.vector[1]})"
    }

    /**
     * Returns whether or not the vectors exactly have the same elements in the same position (when compared with ===)
     *
     * @param {Vec2} The vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun exactEquals(vector: Vec2): Boolean {
        return this.vector[0] == vector[0] && this.vector[1] == vector[1]
    }

    /**
     * Returns whether or not the vectors have approximately the same elements in the same position.
     *
     * @param {Vec2}  The vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun equals(vector: Vec2): Boolean {
        val a0 = this.vector[0]
        val a1 = this.vector[1]
        val b0 = vector[0]
        val b1 = vector[1]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)))
    }
}