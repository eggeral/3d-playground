package matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import kotlin.js.Math

class vec2 : glMatrix() {
    /**
     * 2 Dimensional Vector
     * @module vec2
     */
    /**
     * Creates a new, empty vec2
     *
     * @returns {vec2} a new 2D vector
     */
    fun create(): Array<Double> {
        return arrayOf(
                0.0,
                0.0
        )
    }

    /**
     * Creates vec2ToClone new vec2 initialized with values from an existing vector
     *
     * @param {vec2} vec2ToClone vector to clone
     * @returns {vec2} vec2ToClone new 2D vector
     */
    fun clone(vec2ToClone: Array<Double>): Array<Double> {
        return arrayOf(
                vec2ToClone[0],
                vec2ToClone[1]
        )
    }

    /**
     * Creates a new vec2 initialized with the given values
     *
     * @param {Number} componentX X component
     * @param {Number} componentY Y component
     * @returns {vec2} a new 2D vector
     */
    fun fromValues(componentX: Double, componentY: Double): Array<Double> {
        return arrayOf(
                componentX,
                componentY
        )
    }

    /**
     * Copy the values from one vec2 to another
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} source the source vector
     * @returns {vec2} inOut
     */
    fun copy(inOut: Array<Double>, source: Array<Double>): Array<Double> {
        inOut[0] = source[0]
        inOut[1] = source[1]
        return inOut
    }

    /**
     * Set the components of a vec2 to the given values
     *
     * @param {vec2} inOut the receiving vector
     * @param {Number} componentX X component
     * @param {Number} componentY Y component
     * @returns {vec2} inOut
     */
    fun set(inOut: Array<Double>, componentX: Double, componentY: Double): Array<Double> {
        inOut[0] = componentX
        inOut[1] = componentY
        return inOut
    }

    /**
     * Adds two vec2's
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} firstSummand the first operand
     * @param {vec2} secondSummand the second operand
     * @returns {vec2} inOut
     */
    fun add(inOut: Array<Double>, firstSummand: Array<Double>, secondSummand: Array<Double>): Array<Double> {
        inOut[0] = firstSummand[0] + secondSummand[0]
        inOut[1] = firstSummand[1] + secondSummand[1]
        return inOut
    }

    /**
     * Subtracts vector subtrahend from vector minuend
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} minuend the first operand
     * @param {vec2} subtrahend the second operand
     * @returns {vec2} inOut
     */
    fun subtract(inOut: Array<Double>, minuend: Array<Double>, subtrahend: Array<Double>): Array<Double> {
        inOut[0] = minuend[0] - subtrahend[0]
        inOut[1] = minuend[1] - subtrahend[1]
        return inOut
    }

    /**
     * Multiplies two vec2's
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} multiplier the first operand
     * @param {vec2} multiplicand the second operand
     * @returns {vec2} inOut
     */
    fun multiply(inOut: Array<Double>, multiplier: Array<Double>, multiplicand: Array<Double>): Array<Double> {
        inOut[0] = multiplier[0] * multiplicand[0]
        inOut[1] = multiplier[1] * multiplicand[1]
        return inOut
    }

    /**
     * Divides two vec2's
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} dividend the first operand
     * @param {vec2} divisor the second operand
     * @returns {vec2} inOut
     */
    fun divide(inOut: Array<Double>, dividend: Array<Double>, divisor: Array<Double>): Array<Double> {
        inOut[0] = dividend[0] / divisor[0]
        inOut[1] = dividend[1] / divisor[1]
        return inOut
    }

    /**
     * Math.ceil the components of vec2ToCeil vec2
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToCeil vector to ceil
     * @returns {vec2} inOut
     */
    fun ceil(inOut: Array<Double>, vec2ToCeil: Array<Double>): Array<Double> {
        inOut[0] = (Math.ceil(vec2ToCeil[0])).toDouble()
        inOut[1] = (Math.ceil(vec2ToCeil[1])).toDouble()
        return inOut
    }

    /**
     * Math.floor the components of vec2ToFloor vec2
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToFloor vector to floor
     * @returns {vec2} inOut
     */
    fun floor(inOut: Array<Double>, vec2ToFloor: Array<Double>): Array<Double> {
        inOut[0] = (Math.floor(vec2ToFloor[0])).toDouble()
        inOut[1] = (Math.floor(vec2ToFloor[1])).toDouble()
        return inOut
    }

    /**
     * Returns the minimum of two vec2's
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} firstOperand the first operand
     * @param {vec2} secondOperand the second operand
     * @returns {vec2} inOut
     */
    fun min(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
        inOut[0] = Math.min(firstOperand[0], secondOperand[0])
        inOut[1] = Math.min(firstOperand[1], secondOperand[1])
        return inOut
    }

    /**
     * Returns the maximum of two vec2's
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} firstOperand the first operand
     * @param {vec2} secondOperand the second operand
     * @returns {vec2} inOut
     */
    fun max(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
        inOut[0] = Math.max(firstOperand[0], secondOperand[0])
        inOut[1] = Math.max(firstOperand[1], secondOperand[1])
        return inOut
    }

    /**
     * Math.round the components of vec2ToRound vec2
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToRound vector to round
     * @returns {vec2} inOut
     */
    fun round(inOut: Array<Double>, vec2ToRound: Array<Double>): Array<Double> {
        inOut[0] = (Math.round(vec2ToRound[0])).toDouble()
        inOut[1] = (Math.round(vec2ToRound[1])).toDouble()
        return inOut
    }

    /**
     * Scales vec3ToScale vec2 by vec3ToScale scalar number
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec3ToScale the vector to scale
     * @param {Number} amountToScaleBy amount to scale the vector by
     * @returns {vec2} inOut
     */
    fun scale(inOut: Array<Double>, vec3ToScale: Array<Double>, amountToScaleBy: Double): Array<Double> {
        inOut[0] = vec3ToScale[0] * amountToScaleBy
        inOut[1] = vec3ToScale[1] * amountToScaleBy
        return inOut
    }

    /**
     * Adds two vec2's after scaling the second operand by firstSummand scalar value
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} firstSummand the first operand
     * @param {vec2} secondSummand the second operand
     * @param {Number} amountToScale the amount to amountToScale secondSummand by before adding
     * @returns {vec2} inOut
     */
    fun scaleAndAdd(inOut: Array<Double>, firstSummand: Array<Double>, secondSummand: Array<Double>, amountToScale: Double): Array<Double> {
        inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScale)
        inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScale)
        return inOut
    }

    /**
     * Calculates the euclidian distance between two vec2's
     *
     * @param {vec2} firstOperand the first operand
     * @param {vec2} secondOperand the second operand
     * @returns {Number} distance between firstOperand and secondOperand
     */
    fun distance(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
        val x = secondOperand[0] - firstOperand[0]
        val y = secondOperand[1] - firstOperand[1]
        return Math.sqrt(x * x + y * y)
    }

    /**
     * Calculates the squared euclidian distance between two vec2's
     *
     * @param {vec2} firstOperand the first operand
     * @param {vec2} secondOperand the second operand
     * @returns {Number} squared distance between firstOperand and secondOperand
     */
    fun squaredDistance(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
        val x = secondOperand[0] - firstOperand[0]
        val y = secondOperand[1] - firstOperand[1]
        return x * x + y * y
    }

    /**
     * Calculates the length of vec2ToCalculateLengthOf vec2
     *
     * @param {vec2} vec2ToCalculateLengthOf vector to calculate length of
     * @returns {Number} length of vec2ToCalculateLengthOf
     */
    fun length(vec2ToCalculateLengthOf: Array<Double>): Double {
        val x = vec2ToCalculateLengthOf[0]
        val y = vec2ToCalculateLengthOf[1]
        return Math.sqrt(x * x + y * y)
    }

    /**
     * Calculates the squared length of vec2ToCalculateSquaredLength vec2
     *
     * @param {vec2} vec2ToCalculateSquaredLength vector to calculate squared length of
     * @returns {Number} squared length of vec2ToCalculateSquaredLength
     */
    fun squaredLength(vec2ToCalculateSquaredLength: Array<Double>): Double {
        val x = vec2ToCalculateSquaredLength[0]
        val y = vec2ToCalculateSquaredLength[1]
        return x * x + y * y
    }

    /**
     * Negates the components of vec2ToNegate vec2
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToNegate vector to negate
     * @returns {vec2} inOut
     */
    fun negate(inOut: Array<Double>, vec2ToNegate: Array<Double>): Array<Double> {
        inOut[0] = -vec2ToNegate[0]
        inOut[1] = -vec2ToNegate[1]
        return inOut
    }

    /**
     * Returns the inverse of the components of vec2ToInvert vec2
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToInvert vector to invert
     * @returns {vec2} inOut
     */
    fun inverse(inOut: Array<Double>, vec2ToInvert: Array<Double>): Array<Double> {
        inOut[0] = 1.0 / vec2ToInvert[0]
        inOut[1] = 1.0 / vec2ToInvert[1]
        return inOut
    }

    /**
     * Normalize vec2ToNormalize vec2
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToNormalize vector to normalize
     * @returns {vec2} inOut
     */
    fun normalize(inOut: Array<Double>, vec2ToNormalize: Array<Double>): Array<Double> {
        val x = vec2ToNormalize[0]
        val y = vec2ToNormalize[1]
        var len = x * x + y * y
        if (len > 0) {
            //TODO: evaluate use of glm_invsqrt here?
            len = 1 / Math.sqrt(len)
            inOut[0] = vec2ToNormalize[0] * len
            inOut[1] = vec2ToNormalize[1] * len
        }
        return inOut
    }

    /**
     * Calculates the dot product of two vec2's
     *
     * @param {vec2} firstOperand the first operand
     * @param {vec2} secondOperand the second operand
     * @returns {Number} dot product of firstOperand and secondOperand
     */
    fun dot(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
        return firstOperand[0] * secondOperand[0] + firstOperand[1] * secondOperand[1]
    }

    /**
     * Computes the cross product of two vec2's
     * Note that the cross product must by definition produce firstOperand 3D vector
     *
     * @param {vec3} inOut the receiving vector
     * @param {vec2} firstOperand the first operand
     * @param {vec2} secondOperand the second operand
     * @returns {vec3} inOut
     */
    fun cross(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
        val z = firstOperand[0] * secondOperand[1] - firstOperand[1] * secondOperand[0]
        inOut[0] = 0.0
        inOut[1] = 0.0
        inOut[2] = z
        return inOut
    }

    /**
     * Performs a linear interpolation between two vec2's
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} firstOperand the first operand
     * @param {vec2} secondOperand the second operand
     * @param {Number} interpolationAmount interpolation amount between the two inputs
     * @returns {vec2} inOut
     */
    fun lerp(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
        val ax = firstOperand[0]
        val ay = firstOperand[1]
        inOut[0] = ax + interpolationAmount * (secondOperand[0] - ax)
        inOut[1] = ay + interpolationAmount * (secondOperand[1] - ay)
        return inOut
    }

    /**
     * Generates a random vector with the given vec3ToScale
     *
     * @param {vec2} inOut the receiving vector
     * @param {Number} [vec3ToScale] Length of the resulting vector. If ommitted, a unit vector will be returned
     * @returns {vec2} inOut
     */
    fun random(inOut: Array<Double>, vec3ToScale: Double = 1.0): Array<Double> {
        val r = RANDOM * 2.0 * Math.PI
        inOut[0] = Math.cos(r) * vec3ToScale
        inOut[1] = Math.sin(r) * vec3ToScale
        return inOut
    }

    /**
     * Transforms the vec2 with vec2ToTransform mat2
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToTransform the vector to transform
     * @param {mat2} mat2ToTransformWith matrix to transform with
     * @returns {vec2} inOut
     */
    fun transformMat2(inOut: Array<Double>, vec2ToTransform: Array<Double>, mat2ToTransformWith: Float32Array): Array<Double> {
        val x = vec2ToTransform[0]
        val y = vec2ToTransform[1]
        inOut[0] = mat2ToTransformWith[0] * x + mat2ToTransformWith[2] * y
        inOut[1] = mat2ToTransformWith[1] * x + mat2ToTransformWith[3] * y
        return inOut
    }

    /**
     * Transforms the vec2 with vec2ToTransform mat2d
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToTransform the vector to transform
     * @param {mat2d} mat2dToTransformWith matrix to transform with
     * @returns {vec2} inOut
     */
    fun transformMat2d(inOut: Array<Double>, vec2ToTransform: Array<Double>, mat2dToTransformWith: Float32Array): Array<Double> {
        val x = vec2ToTransform[0]
        val y = vec2ToTransform[1]
        inOut[0] = mat2dToTransformWith[0] * x + mat2dToTransformWith[2] * y + mat2dToTransformWith[4]
        inOut[1] = mat2dToTransformWith[1] * x + mat2dToTransformWith[3] * y + mat2dToTransformWith[5]
        return inOut
    }

    /**
     * Transforms the vec2 with vec2ToTransform mat3
     * 3rd vector component is implicitly '1'
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToTransform the vector to transform
     * @param {mat3} mat3ToTransformWith matrix to transform with
     * @returns {vec2} inOut
     */
    fun transformMat3(inOut: Array<Double>, vec2ToTransform: Array<Double>, mat3ToTransformWith: Float32Array): Array<Double> {
        val x = vec2ToTransform[0]
        val y = vec2ToTransform[1]
        inOut[0] = mat3ToTransformWith[0] * x + mat3ToTransformWith[3] * y + mat3ToTransformWith[6]
        inOut[1] = mat3ToTransformWith[1] * x + mat3ToTransformWith[4] * y + mat3ToTransformWith[7]
        return inOut
    }

    /**
     * Transforms the vec2 with vec2ToTransform mat4
     * 3rd vector component is implicitly '0'
     * 4th vector component is implicitly '1'
     *
     * @param {vec2} inOut the receiving vector
     * @param {vec2} vec2ToTransform the vector to transform
     * @param {mat4} mat4ToTransformWith matrix to transform with
     * @returns {vec2} inOut
     */
    fun transformMat4(inOut: Array<Double>, vec2ToTransform: Array<Double>, mat4ToTransformWith: Float32Array): Array<Double> {
        val x = vec2ToTransform[0]
        val y = vec2ToTransform[1]
        inOut[0] = mat4ToTransformWith[0] * x + mat4ToTransformWith[4] * y + mat4ToTransformWith[12]
        inOut[1] = mat4ToTransformWith[1] * x + mat4ToTransformWith[5] * y + mat4ToTransformWith[13]
        return inOut
    }

    /**
     * Returns a string representation of a vector
     *
     * @param {vec2} a vector to represent as a string
     * @returns {String} string representation of the vector
     */
    fun toString(vector: Array<Double>): String {
        return "vec2(${vector[0]}, ${vector[1]})"
    }

    /**
     * Returns whether or not the vectors exactly have the same elements in the same position (when compared with ===)
     *
     * @param {vec2} firstVector The first vector.
     * @param {vec2} secondVector The second vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun exactEquals(firstVector: Array<Double>, secondVector: Array<Double>): Boolean {
        return firstVector[0] == secondVector[0] && firstVector[1] == secondVector[1]
    }

    /**
     * Returns whether or not the vectors have approximately the same elements in the same position.
     *
     * @param {vec2} firstVector The first vector.
     * @param {vec2} secondVector The second vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun equals(firstVector: Array<Double>, secondVector: Array<Double>): Boolean {
        val a0 = firstVector[0]
        val a1 = firstVector[1]
        val b0 = secondVector[0]
        val b1 = secondVector[1]
        return (Math.abs(a0 - b0) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)))
    }
}