package spr5.matrix

import kotlin.js.Math

class Vec3() : glMatrix() {

    private val vector: Array<Double> = arrayOf(0.0, 0.0, 0.0)

    constructor(componentX: Double, componentY: Double, componentZ: Double) : this() {
        vector[0] = componentX
        vector[1] = componentY
        vector[2] = componentZ
    }

    operator fun get(index: Int): Double {
        return vector[index]
    }

    operator fun set(index: Int, value: Double) {
        vector[index] = value
    }

    /**
     * Creates vec3ToClone new Vec3 initialized with values from an existing vector
     *
     * @param {Vec3} vec3ToClone vector to clone
     * @returns {Vec3} vec3ToClone new 3D vector
     */
    fun clone(): Vec3 {
        return Vec3(this.vector[0], this.vector[1], this.vector[2])
    }

    /**
     * Calculates the length of vec3ToCalculateLengthOf Vec3
     *
     * @param {Vec3} vec3ToCalculateLengthOf vector to calculate length of
     * @returns {Number} length of vec3ToCalculateLengthOf
     */
    fun length(): Double {
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        return Math.sqrt(x * x + y * y + z * z)
    }

    /**
     * Calculates the size of Vec3
     *
     * @returns {Number} elements of Vec3
     */
    fun size(): Int {
        return this.vector.size
    }

    /**
     * Copy the values from one Vec3 to another
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} source the source vector
     * @returns {Vec3} inOut
     */
    fun copy(source: Vec3): Vec3 {
        this.vector[0] = source[0]
        this.vector[1] = source[1]
        this.vector[2] = source[2]
        return this
    }

    /**
     * Adds two Vec3's
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} firstSummand the first operand
     * @param {Vec3} secondSummand the second operand
     * @returns {Vec3} inOut
     */
    fun add(summand: Vec3): Vec3 {
        this.vector[0] += summand[0]
        this.vector[1] += summand[1]
        this.vector[2] += summand[2]
        return this
    }

    operator fun plus(summand: Vec3): Vec3 {
        return clone().add(summand);
    }

    /**
     * Subtracts vector subtrahend from vector minuend
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} minuend the first operand
     * @param {Vec3} subtrahend the second operand
     * @returns {Vec3} inOut
     */
    fun subtract(subtrahend: Vec3): Vec3 {
        this.vector[0] -= subtrahend[0]
        this.vector[1] -= subtrahend[1]
        this.vector[2] -= subtrahend[2]
        return this
    }

    operator fun minus(subtrahend: Vec3): Vec3 {
        return clone().subtract(subtrahend);
    }

    /**
     * Multiplies two Vec3's
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} multiplier the first operand
     * @param {Vec3} multiplicand the second operand
     * @returns {Vec3} inOut
     */
    fun multiply(multiplier: Vec3): Vec3 {
        this.vector[0] *= multiplier[0]
        this.vector[1] *= multiplier[1]
        this.vector[2] *= multiplier[2]
        return this
    }

    operator fun times(multiplier: Vec3): Vec3 {
        return multiplier.clone().multiply(this);
    }

    /**
     * Divides two Vec3's
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} dividend the first operand
     * @param {Vec3} divisor the second operand
     * @returns {Vec3} inOut
     */
    fun divide(divisor: Vec3): Vec3 {
        this.vector[0] /= divisor[0]
        this.vector[1] /= divisor[1]
        this.vector[2] /= divisor[2]
        return this
    }

    operator fun div(divisor: Vec3): Vec3 {
        return clone().divide(divisor);
    }

    /**
     * Math.ceil the components of vec3ToCeil Vec3
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToCeil vector to ceil
     * @returns {Vec3} inOut
     */
    fun ceil(): IntArray {
        val output = IntArray(3)
        output[0] = Math.ceil(this.vector[0])
        output[1] = Math.ceil(this.vector[1])
        output[2] = Math.ceil(this.vector[2])
        return output
    }

    /**
     * Math.floor the components of vec3ToFloor Vec3
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToFloor vector to floor
     * @returns {Vec3} inOut
     */
    fun floor(): IntArray {
        val output = IntArray(3)
        output[0] = Math.floor(this.vector[0])
        output[1] = Math.floor(this.vector[1])
        output[2] = Math.floor(this.vector[2])
        return output
    }

    /**
     * Returns the minimum of two Vec3's
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} firstOperand the first operand
     * @param {Vec3} secondOperand the second operand
     * @returns {Vec3} inOut
     */
    fun min(operand: Vec3): Vec3 {
        return Vec3(Math.min(this.vector[0], operand[0]),
                Math.min(this.vector[1], operand[1]),
                Math.min(this.vector[2], operand[2]))
    }

    /**
     * Returns the maximum of two Vec3's
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} firstOperand the first operand
     * @param {Vec3} secondOperand the second operand
     * @returns {Vec3} inOut
     */
    fun max(operand: Vec3): Vec3 {
        return Vec3(Math.max(this.vector[0], operand[0]),
                Math.max(this.vector[1], operand[1]),
                Math.max(this.vector[2], operand[2]))
    }

    /**
     * Math.round the components of vec3ToRound Vec3
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToRound vector to round
     * @returns {Vec3} inOut
     */
    fun round(): Vec3 {
        return Vec3((Math.round(this[0])).toDouble(),
                (Math.round(this[1])).toDouble(),
                (Math.round(this[2])).toDouble())
    }

    /**
     * Scales vec3ToScale Vec3 by vec3ToScale scalar number
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToScale the vector to scale
     * @param {Number} amountToScaleBy amount to scale the vector by
     * @returns {Vec3} inOut
     */
    fun scale(amountToScaleBy: Double): Vec3 {
        val output = Vec3()
        output[0] = this.vector[0] * amountToScaleBy
        output[1] = this.vector[1] * amountToScaleBy
        output[2] = this.vector[2] * amountToScaleBy
        return output
    }

    /**
     * Adds two Vec3's after scaling the second operand by firstSummand scalar value
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} firstSummand the first operand
     * @param {Vec3} secondSummand the second operand
     * @param {Number} amountToScale the amount to amountToScale secondSummand by before adding
     * @returns {Vec3} inOut
     */
    fun scaleAndAdd(summand: Vec3, amountToScale: Double): Vec3 {
        val output = Vec3()
        output[0] = this.vector[0] + (summand[0] * amountToScale)
        output[1] = this.vector[1] + (summand[1] * amountToScale)
        output[2] = this.vector[2] + (summand[2] * amountToScale)
        return output
    }

    /**
     * Calculates the euclidian distance between two Vec3's
     *
     * @param {Vec3} firstOperand the first operand
     * @param {Vec3} secondOperand the second operand
     * @returns {Number} distance between firstOperand and secondOperand
     */
    fun distance(operand: Vec3): Double {
        val x = this.vector[0] - operand[0]
        val y = this.vector[1] - operand[1]
        val z = this.vector[2] - operand[2]
        return Math.sqrt(x * x + y * y + z * z)
    }

    /**
     * Calculates the squared euclidian distance between two Vec3's
     *
     * @param {Vec3} firstOperand the first operand
     * @param {Vec3} secondOperand the second operand
     * @returns {Number} squared distance between firstOperand and secondOperand
     */
    fun squaredDistance(operand: Vec3): Double {
        val x = this.vector[0] - operand[0]
        val y = this.vector[1] - operand[1]
        val z = this.vector[2] - operand[2]
        return x * x + y * y + z * z
    }

    /**
     * Calculates the squared length of vec3ToCalculateSquaredLength Vec3
     *
     * @param {Vec3} vec3ToCalculateSquaredLength vector to calculate squared length of
     * @returns {Number} squared length of vec3ToCalculateSquaredLength
     */
    fun squaredLength(): Double {
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        return x * x + y * y + z * z
    }

    /**
     * Negates the components of vec3ToNegate Vec3
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToNegate vector to negate
     * @returns {Vec3} inOut
     */
    fun negate(): Vec3 {
        val output = Vec3()
        output[0] = -this.vector[0]
        output[1] = -this.vector[1]
        output[2] = -this.vector[2]
        return output
    }

    /**
     * Returns the inverse of the components of vec3ToInvert Vec3
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToInvert vector to invert
     * @returns {Vec3} inOut
     */
    fun inverse(): Vec3 {
        val output = Vec3()
        output[0] = 1.0 / this.vector[0]
        output[1] = 1.0 / this.vector[1]
        output[2] = 1.0 / this.vector[2]
        return output
    }

    /**
     * Normalize vec3ToNormalize Vec3
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToNormalize vector to normalize
     * @returns {Vec3} inOut
     */
    fun normalize(): Vec3 {
        val output = Vec3()
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        var len = x * x + y * y + z * z
        if (len > 0) {
            //TODO: evaluate use of glm_invsqrt here?
            len = 1 / Math.sqrt(len)
            output[0] = this.vector[0] * len
            output[1] = this.vector[1] * len
            output[2] = this.vector[2] * len
        }
        return output
    }

    /**
     * Calculates the dot product of two Vec3's
     *
     * @param {Vec3} firstOperand the first operand
     * @param {Vec3} secondOperand the second operand
     * @returns {Number} dot product of firstOperand and secondOperand
     */
    fun dot(operand: Vec3): Double {
        return this.vector[0] * operand[0] + this.vector[1] * operand[1] + this.vector[2] * operand[2]
    }

    /**
     * Computes the cross product of two Vec3's
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} firstOperand the first operand
     * @param {Vec3} secondOperand the second operand
     * @returns {Vec3} inOut
     */
    fun cross(operand: Vec3): Vec3 {
        val output = Vec3()
        val ax = this.vector[0]
        val ay = this.vector[1]
        val az = this.vector[2]
        val bx = operand[0]
        val by = operand[1]
        val bz = operand[2]
        output[0] = ay * bz - az * by
        output[1] = az * bx - ax * bz
        output[2] = ax * by - ay * bx
        return output
    }

    /**
     * Performs a linear interpolation between two Vec3's
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} firstOperand the first operand
     * @param {Vec3} secondOperand the second operand
     * @param {Number} interpolationAmount interpolation amount between the two inputs
     * @returns {Vec3} inOut
     */
    fun lerp(operand: Vec3, interpolationAmount: Double): Vec3 {
        val output = Vec3()
        val ax = this.vector[0]
        val ay = this.vector[1]
        val az = this.vector[2]
        output[0] = ax + interpolationAmount * (operand[0] - ax)
        output[1] = ay + interpolationAmount * (operand[1] - ay)
        output[2] = az + interpolationAmount * (operand[2] - az)
        return output
    }

    /**
     * Performs a hermite interpolation with two control points
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} `this.vector` the first operand
     * @param {Vec3} firstOperand the second operand
     * @param {Vec3} secondOperand the third operand
     * @param {Vec3} thirdOperand the fourth operand
     * @param {Number} interpolationAmount interpolation amount between the two inputs
     * @returns {Vec3} inOut
     */
    fun hermite(firstOperand: Vec3, secondOperand: Vec3, thirdOperand: Vec3, interpolationAmount: Double): Vec3 {
        val output = Vec3()
        val factorTimes2 = interpolationAmount * interpolationAmount
        val factor1 = factorTimes2 * (2 * interpolationAmount - 3) + 1
        val factor2 = factorTimes2 * (interpolationAmount - 2) + interpolationAmount
        val factor3 = factorTimes2 * (interpolationAmount - 1)
        val factor4 = factorTimes2 * (3 - 2 * interpolationAmount)
        output[0] = this.vector[0] * factor1 + firstOperand[0] * factor2 + secondOperand[0] * factor3 + thirdOperand[0] * factor4
        output[1] = this.vector[1] * factor1 + firstOperand[1] * factor2 + secondOperand[1] * factor3 + thirdOperand[1] * factor4
        output[2] = this.vector[2] * factor1 + firstOperand[2] * factor2 + secondOperand[2] * factor3 + thirdOperand[2] * factor4
        return output
    }

    /**
     * Performs a bezier interpolation with two control points
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} `this.vector` the first operand
     * @param {Vec3} firstOperand the second operand
     * @param {Vec3} secondOperand the third operand
     * @param {Vec3} thirdOperand the fourth operand
     * @param {Number} interpolationAmount interpolation amount between the two inputs
     * @returns {Vec3} inOut
     */
    fun bezier(firstOperand: Vec3, secondOperand: Vec3, thirdOperand: Vec3, interpolationAmount: Double): Vec3 {
        val output = Vec3()
        val inverseFactor = 1 - interpolationAmount
        val inverseFactorTimesTwo = inverseFactor * inverseFactor
        val factorTimes2 = interpolationAmount * interpolationAmount
        val factor1 = inverseFactorTimesTwo * inverseFactor
        val factor2 = 3 * interpolationAmount * inverseFactorTimesTwo
        val factor3 = 3 * factorTimes2 * inverseFactor
        val factor4 = factorTimes2 * interpolationAmount
        output[0] = this.vector[0] * factor1 + firstOperand[0] * factor2 + secondOperand[0] * factor3 + thirdOperand[0] * factor4
        output[1] = this.vector[1] * factor1 + firstOperand[1] * factor2 + secondOperand[1] * factor3 + thirdOperand[1] * factor4
        output[2] = this.vector[2] * factor1 + firstOperand[2] * factor2 + secondOperand[2] * factor3 + thirdOperand[2] * factor4
        return output
    }

    /**
     * Generates a random vector with the given vectorScale
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Number} [vectorScale] Length of the resulting vector. If ommitted, a unit vector will be returned
     * @returns {Vec3} inOut
     */
    fun random(vectorScale: Double = 1.0): Vec3 {
        val output = Vec3()
        val r = RANDOM * 2.0 * Math.PI
        val z = (RANDOM * 2.0) - 1.0
        val zScale = Math.sqrt(1.0 - z * z) * vectorScale
        output[0] = Math.cos(r) * zScale
        output[1] = Math.sin(r) * zScale
        output[2] = z * vectorScale
        return output
    }

    /**
     * Transforms the Vec3 with vec3ToTransform Mat4.
     * 4th vector component is implicitly '1'
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToTransform the vector to transform
     * @param {Mat4} mat4ToTransformWith matrix to transform with
     * @returns {Vec3} inOut
     */
    fun transformMat4(mat4ToTransformWith: DoubleArray): Vec3 {
        val output = Vec3()
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        var w = mat4ToTransformWith[3] * x + mat4ToTransformWith[7] * y + mat4ToTransformWith[11] * z + mat4ToTransformWith[15]
        if (w < 0 || w > 1.0) {
            w = 1.0
        }
        output[0] = (mat4ToTransformWith[0] * x + mat4ToTransformWith[4] * y + mat4ToTransformWith[8] * z + mat4ToTransformWith[12]) / w
        output[1] = (mat4ToTransformWith[1] * x + mat4ToTransformWith[5] * y + mat4ToTransformWith[9] * z + mat4ToTransformWith[13]) / w
        output[2] = (mat4ToTransformWith[2] * x + mat4ToTransformWith[6] * y + mat4ToTransformWith[10] * z + mat4ToTransformWith[14]) / w
        return output
    }

    /**
     * Transforms the Vec3 with vec3ToTransform Mat3.
     *
     * @param {Mat3} mat3ToTransformWith the 3x3 matrix to transform with
     * @returns {Vec3} inOut
     */
    fun transformMat3(mat3ToTransformWith: DoubleArray): Vec3 {
        val output = Vec3()
        val x = this.vector[0]
        val y = this.vector[1]
        val z = this.vector[2]
        output[0] = x * mat3ToTransformWith[0] + y * mat3ToTransformWith[3] + z * mat3ToTransformWith[6]
        output[1] = x * mat3ToTransformWith[1] + y * mat3ToTransformWith[4] + z * mat3ToTransformWith[7]
        output[2] = x * mat3ToTransformWith[2] + y * mat3ToTransformWith[5] + z * mat3ToTransformWith[8]
        return output
    }

    /**
     * Transforms the Vec3 with vec3ToTransform Quat
     *
     * @param {Vec3} inOut the receiving vector
     * @param {Vec3} vec3ToTransform the vector to transform
     * @param {Quat} quatToTransformWith quaternion to transform with
     * @returns {Vec3} inOut
     */
    fun transformQuat(quatToTransformWith: Quat): Vec3 {
        // benchmarks: http://jsperf.com/quaternion-transform-vec3-implementations
        val output = Vec3()
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
        return output
    }

    /**
     * Rotate vec3ToRotate 3D vector around the x-axis
     * @param {Vec3} inOut The receiving Vec3
     * @param {Vec3} vec3ToRotate The Vec3 point to rotate
     * @param {Vec3} originOfRotation The origin of the rotation
     * @param {Number} angleInRad The angle of rotation
     * @returns {Vec3} inOut
     */
    fun rotateX(originOfRotation: Array<Double>, angleInRad: Double): Vec3 {
        val output = Vec3()
        //Translate point to the origin
        val p = arrayOf(
                (this.vector[0] - originOfRotation[0]),
                (this.vector[1] - originOfRotation[1]),
                (this.vector[2] - originOfRotation[2])
        )
        //perform rotation
        val r = arrayOf(
                p[0],
                (p[1] * Math.cos(angleInRad) - p[2] * Math.sin(angleInRad)),
                (p[1] * Math.sin(angleInRad) + p[2] * Math.cos(angleInRad))
        )
        //translate to correct position
        output[0] = r[0] + originOfRotation[0]
        output[1] = r[1] + originOfRotation[1]
        output[2] = r[2] + originOfRotation[2]
        return output
    }

    /**
     * Rotate vec3ToRotate 3D vector around the y-axis
     * @param {Vec3} inOut The receiving Vec3
     * @param {Vec3} vec3ToRotate The Vec3 point to rotate
     * @param {Vec3} originOfRotation The origin of the rotation
     * @param {Number} angleInRad The angle of rotation
     * @returns {Vec3} inOut
     */
    fun rotateY(originOfRotation: Array<Double>, angleInRad: Double): Vec3 {
        val output = Vec3()
        //Translate point to the origin
        val p = arrayOf(
                (this.vector[0] - originOfRotation[0]),
                (this.vector[1] - originOfRotation[1]),
                (this.vector[2] - originOfRotation[2])
        )
        //perform rotation
        val r = arrayOf(
                (p[2] * Math.sin(angleInRad) + p[0] * Math.cos(angleInRad)),
                p[1],
                (p[2] * Math.cos(angleInRad) - p[0] * Math.sin(angleInRad))
        )
        //translate to correct position
        output[0] = r[0] + originOfRotation[0]
        output[1] = r[1] + originOfRotation[1]
        output[2] = r[2] + originOfRotation[2]
        return output
    }

    /**
     * Rotate vec3ToRotate 3D vector around the z-axis
     * @param {Vec3} inOut The receiving Vec3
     * @param {Vec3} vec3ToRotate The Vec3 point to rotate
     * @param {Vec3} originOfRotation The origin of the rotation
     * @param {Number} angleInRad The angle of rotation
     * @returns {Vec3} inOut
     */
    fun rotateZ(originOfRotation: Array<Double>, angleInRad: Double): Vec3 {
        val output = Vec3()
        //Translate point to the origin
        val p = arrayOf(
                (this.vector[0] - originOfRotation[0]),
                (this.vector[1] - originOfRotation[1]),
                (this.vector[2] - originOfRotation[2])
        )
        //perform rotation
        val r = arrayOf(
                (p[0] * Math.cos(angleInRad) - p[1] * Math.sin(angleInRad)),
                (p[0] * Math.sin(angleInRad) + p[1] * Math.cos(angleInRad)),
                p[2]
        )
        //translate to correct position
        output[0] = r[0] + originOfRotation[0]
        output[1] = r[1] + originOfRotation[1]
        output[2] = r[2] + originOfRotation[2]
        return output
    }

    /**
     * Get the angle between two 3D vectors
     * @param {Vec3} firstVec3 The first operand
     * @param {Vec3} secondVec3 The second operand
     * @returns {Number} The angle in radians
     */
    fun angle(vector: Vec3): Double {
        val tempA = Vec3(this.vector[0], this.vector[1], this.vector[2]).normalize()
        val tempB = Vec3(vector[0], vector[1], vector[2]).normalize()
        val cosine = tempA.dot(tempB)
        if (cosine > 1.0) {
            return 0.0
        } else if (cosine < -1.0) {
            return Math.PI
        } else {
            return Math.acos(cosine)
        }
    }

    /**
     * Returns a string representation of a vector
     *
     * @param {Vec3} a vector to represent as a string
     * @returns {String} string representation of the vector
     */
    override fun toString(): String {
        return "Vec3(${this.vector[0]}, ${this.vector[1]}, ${this.vector[2]})"
    }

    /**
     * Returns whether or not the vectors have exactly the same elements in the same position (when compared with ===)
     *
     * @param {Vec3} firstVector The first vector.
     * @param {Vec3} secondVector The second vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun exactEquals(vector: Vec3): Boolean {
        return this.vector[0] == vector[0] && this.vector[1] == vector[1] && this.vector[2] == vector[2]
    }

    /**
     * Returns whether or not the vectors have approximately the same elements in the same position.
     *
     * @param {Vec3} firstVector The first vector.
     * @param {Vec3} secondVector The second vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun equals(vector: Vec3): Boolean {
        val a0 = this.vector[0]
        val a1 = this.vector[1]
        val a2 = this.vector[2]
        val b0 = vector[0]
        val b1 = vector[1]
        val b2 = vector[2]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)))
    }
}
