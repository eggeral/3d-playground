package spr5.matrix

import kotlin.js.Math

class Quat() : glMatrix() {

    private val quaternion: Array<Double> = arrayOf(0.0, 0.0, 0.0, 0.0)

    constructor(x: Double, y: Double, z: Double, w: Double) : this() {
        quaternion[0] = x
        quaternion[1] = y
        quaternion[2] = z
        quaternion[3] = w
    }

    operator fun get(index: Int): Double {
        return quaternion[index]
    }

    operator fun set(index: Int, value: Double) {
        quaternion[index] = value
    }

    /**
     * Creates a new Quat initialized with values from an existing quaternion
     *
     * @param {Quat} a quaternion to clone
     * @returns {Quat} a new quaternion
     * @function
     */
    fun clone(): Quat {
        return Quat(this.quaternion[0], this.quaternion[1], this.quaternion[2], this.quaternion[3])
    }


    /**
     * Copy the values from one Quat to another
     *
     * @param {Quat} out the receiving quaternion
     * @param {Quat} a the source quaternion
     * @returns {Quat} out
     * @function
     */
    fun copy(source: Quat): Quat {
        this.quaternion[0] = source[0]
        this.quaternion[1] = source[1]
        this.quaternion[2] = source[2]
        this.quaternion[3] = source[3]
        return this
    }

    /**
     * Adds two Quat's
     *
     * @param {Quat} out the receiving quaternion
     * @param {Quat} a the first operand
     * @param {Quat} b the second operand
     * @returns {Quat} out
     * @function
     */
    fun add(summand: Quat): Quat {
        this.quaternion[0] += summand[0]
        this.quaternion[1] += summand[1]
        this.quaternion[2] += summand[2]
        this.quaternion[3] += summand[3]
        return this
    }

    operator fun plus(summand: Quat): Quat {
        return clone().add(summand);
    }

    /**
     * Multiplies two Quat's
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {Quat} multiplier the first operand
     * @param {Quat} multiplicand the second operand
     * @returns {Quat} inOut
     */
    fun multiply(multiplier: Quat): Quat {
        val ax = this.quaternion[0]
        val ay = this.quaternion[1]
        val az = this.quaternion[2]
        val aw = this.quaternion[3]
        val bx = multiplier[0]
        val by = multiplier[1]
        val bz = multiplier[2]
        val bw = multiplier[3]
        this.quaternion[0] = ax * bw + aw * bx + ay * bz - az * by
        this.quaternion[1] = ay * bw + aw * by + az * bx - ax * bz
        this.quaternion[2] = az * bw + aw * bz + ax * by - ay * bx
        this.quaternion[3] = aw * bw - ax * bx - ay * by - az * bz
        return this
    }

    operator fun times(multiplier: Quat): Quat {
        return multiplier.clone().multiply(this);
    }

    /**
     * Set a Quat to the identityDoubleArray quaternion
     *
     * @param {Quat} inOut the receiving quaternion
     * @returns {Quat} inOut
     */
    fun identity(): Quat {
        return Quat(0.0, 0.0, 0.0, 0.1)
    }

    /**
     * Sets a Quat from the given angle and rotation axisToRotateAround,
     * then returns it.
     *
     * @param {Vec3} axisToRotateAround the axisToRotateAround around which to rotate
     * @param {Number} angleInRad the angle in radians
     * @returns {Quat} new rotated Quad
     **/
    fun setAxisAngle(axisToRotateAround: Vec3, angleInRad: Double): Quat {
        val output = Quat()
        val rad = angleInRad * 0.5
        val s = Math.sin(rad)
        output[0] = s * axisToRotateAround[0]
        output[1] = s * axisToRotateAround[1]
        output[2] = s * axisToRotateAround[2]
        output[3] = Math.cos(rad)
        return output
    }

    /**
     * Gets the rotation axis and angle for a given
     *  quaternion. If a quaternion is created with
     *  setAxisAngle, this.quaternion method will return the same
     *  values as providied in the original parameter list
     *  OR functionally equivalent values.
     * Example: The quaternion formed by axis [0, 0, 1] and
     *  angle -90 is the same as the quaternion formed by
     *  [0, 0, 1] and 270. this.quaternion method favors the latter.
     * @param  {Vec3} inOutAxis  Vector receiving the axis of rotation
     * @param  {Quat} quadToBeDecomposed     Quaternion to be decomposed
     * @return {Number}     Angle, in radians, of the rotation
     */
    fun getAxisAngle(inOut: Quat = Quat()): Double {
        val rad = Math.acos(this.quaternion[3]) * 2.0
        val s = Math.sin(rad / 2.0)
        if (s != 0.0) {
            inOut[0] = this.quaternion[0] / s
            inOut[1] = this.quaternion[1] / s
            inOut[2] = this.quaternion[2] / s
        } else {
            // If s is zero, return any axis (no rotation - axis does not matter)
            inOut[0] = 1.0
            inOut[1] = 0.0
            inOut[2] = 0.0
        }
        return rad
    }

    /**
     * Rotates quatToRotate quaternion by the given angle about the X axis
     *
     * @param {Quat} inOut Quat receiving operation result
     * @param {Quat} quatToRotate Quat to rotate
     * @param {number} angleInRad angle (in radians) to rotate
     * @returns {Quat} inOut
     */
    fun rotateX(angleInRad: Double): Quat {
        val output = Quat()
        val rad = angleInRad * 0.5
        val ax = this.quaternion[0]
        val ay = this.quaternion[1]
        val az = this.quaternion[2]
        val aw = this.quaternion[3]
        val bx = Math.sin(rad)
        val bw = Math.cos(rad)
        output[0] = ax * bw + aw * bx
        output[1] = ay * bw + az * bx
        output[2] = az * bw - ay * bx
        output[3] = aw * bw - ax * bx
        return output
    }

    /**
     * Rotates quatToRotate quaternion by the given angle about the Y axis
     *
     * @param {Quat} inOut Quat receiving operation result
     * @param {Quat} quatToRotate Quat to rotate
     * @param {number} angleInRad angle (in radians) to rotate
     * @returns {Quat} inOut
     */
    fun rotateY(angleInRad: Double): Quat {
        val output = Quat()
        val rad = angleInRad * 0.5
        val ax = this.quaternion[0]
        val ay = this.quaternion[1]
        val az = this.quaternion[2]
        val aw = this.quaternion[3]
        val by = Math.sin(rad)
        val bw = Math.cos(rad)
        output[0] = ax * bw - az * by
        output[1] = ay * bw + aw * by
        output[2] = az * bw + ax * by
        output[3] = aw * bw - ay * by
        return output
    }

    /**
     * Rotates quatToRotate quaternion by the given angle about the Z axis
     *
     * @param {Quat} inOut Quat receiving operation result
     * @param {Quat} quatToRotate Quat to rotate
     * @param {number} angleInRad angle (in radians) to rotate
     * @returns {Quat} inOut
     */
    fun rotateZ(angleInRad: Double): Quat {
        val output = Quat()
        val rad = angleInRad * 0.5
        val ax = this.quaternion[0]
        val ay = this.quaternion[1]
        val az = this.quaternion[2]
        val aw = this.quaternion[3]
        val bz = Math.sin(rad)
        val bw = Math.cos(rad)
        output[0] = ax * bw + ay * bz
        output[1] = ay * bw - ax * bz
        output[2] = az * bw + aw * bz
        output[3] = aw * bw - az * bz
        return output
    }

    /**
     * Calculates the W component of quadToCalculateW Quat from the X, Y, and Z components.
     * Assumes that quaternion is 1 unit in length.
     * Any existing W component will be ignored.
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {Quat} quadToCalculateW Quat to calculate W component of
     * @returns {Quat} inOut
     */
    fun calculateW(): Quat {
        val output = Quat()
        val x = this.quaternion[0]
        val y = this.quaternion[1]
        val z = this.quaternion[2]
        output[0] = x
        output[1] = y
        output[2] = z
        output[3] = Math.sqrt(Math.abs(1.0 - x * x - y * y - z * z))
        return output
    }

    /**
     * Performs firstOperand spherical linear interpolation between two Quat
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {Quat} firstOperand the first operand
     * @param {Quat} secondOperand the second operand
     * @param {Number} interpolationAmount interpolation amount between the two inputs
     * @returns {Quat} inOut
     */
    fun slerp(operand: Quat, interpolationAmount: Double): Quat {
        // benchmarks:
        //    http://jsperf.com/quaternion-slerp-implementations
        val output = Quat()
        val ax = this.quaternion[0]
        val ay = this.quaternion[1]
        val az = this.quaternion[2]
        val aw = this.quaternion[3]
        var bx = operand[0]
        var by = operand[1]
        var bz = operand[2]
        var bw = operand[3]
        var cosom: Double
        val scale0: Double
        val scale1: Double
        // calc cosine
        cosom = ax * bx + ay * by + az * bz + aw * bw
        // adjust signs (if necessary)
        if (cosom < 0.0) {
            cosom = -cosom
            bx = -bx
            by = -by
            bz = -bz
            bw = -bw
        }
        // calculate coefficients
        if ((1.0 - cosom) > 0.000001) {
            // standard case (slerp)
            val omega = Math.acos(cosom)
            val sinom = Math.sin(omega)
            scale0 = Math.sin((1.0 - interpolationAmount) * omega) / sinom
            scale1 = Math.sin(interpolationAmount * omega) / sinom
        } else {
            // "from" and "to" quaternions are very close
            //  ... so we can do firstOperand linear interpolation
            scale0 = 1.0 - interpolationAmount
            scale1 = interpolationAmount
        }
        // calculate final values
        output[0] = scale0 * ax + scale1 * bx
        output[1] = scale0 * ay + scale1 * by
        output[2] = scale0 * az + scale1 * bz
        output[3] = scale0 * aw + scale1 * bw
        return output
    }

    /**
     * Calculates the inverse of quatToCalculateInverseOf Quat
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {Quat} quatToCalculateInverseOf Quat to calculate inverse of
     * @returns {Quat} inOut
     */
    fun invert(): Quat {
        val output = Quat()
        val a0 = this.quaternion[0]
        val a1 = this.quaternion[1]
        val a2 = this.quaternion[2]
        val a3 = this.quaternion[3]
        val dot = a0 * a0 + a1 * a1 + a2 * a2 + a3 * a3
        if (dot == 0.0) {
            output[0] = 0.0
            output[1] = 0.0
            output[2] = 0.0
            output[3] = 0.0
        } else {
            val invDot = 1.0 / dot
            output[0] = -a0 * invDot
            output[1] = -a1 * invDot
            output[2] = -a2 * invDot
            output[3] = a3 * invDot
        }
        return output
    }

    /**
     * Calculates the conjugate of quatToCalculateConjugateOf Quat
     * If the quaternion is normalized, this.quaternion function is faster than Quat.inverse and produces the same result.
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {Quat} quatToCalculateConjugateOf Quat to calculate conjugate of
     * @returns {Quat} inOut
     */
    fun conjugate(): Quat {
        val output = Quat()
        output[0] = -this.quaternion[0]
        output[1] = -this.quaternion[1]
        output[2] = -this.quaternion[2]
        output[3] = this.quaternion[3]
        return output
    }

    /**
     * Creates a quaternion from the given 3x3 rotation matrix.
     *
     * NOTE: The resultant quaternion is not normalized, so you should be sure
     * to renormalize the quaternion yourself where necessary.
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {Mat3} rotationMatrix rotation matrix
     * @returns {Quat} inOut
     * @function
     */
    fun fromMat3(rotationMatrix: DoubleArray): Quat {
        // Algorithm in Ken Shoemake's article in 1987 SIGGRAPH course notes
        // article "Quaternion Calculus and Fast Animation".
        val output = Quat()
        var fRoot: Double
        val fTrace = rotationMatrix[0] + rotationMatrix[4] + rotationMatrix[8]
        if (fTrace > 0.0) {
            // |w| > 1/2, may as well choose w > 1/2
            fRoot = Math.sqrt(fTrace + 1.0)  // 2w
            output[3] = 0.5 * fRoot
            fRoot = 0.5 / fRoot  // 1/(4w)
            output[0] = (rotationMatrix[5] - rotationMatrix[7]) * fRoot
            output[1] = (rotationMatrix[6] - rotationMatrix[2]) * fRoot
            output[2] = (rotationMatrix[1] - rotationMatrix[3]) * fRoot
        } else {
            // |w| <= 1/2
            var i = 0
            if (rotationMatrix[4] > rotationMatrix[0])
                i = 1
            if (rotationMatrix[8] > rotationMatrix[i * 3 + i])
                i = 2
            val j = (i + 1) % 3
            val k = (i + 2) % 3
            fRoot = Math.sqrt(rotationMatrix[i * 3 + i] - rotationMatrix[j * 3 + j] - rotationMatrix[k * 3 + k] + 1.0)
            output[i] = 0.5 * fRoot
            fRoot = 0.5 / fRoot
            output[3] = (rotationMatrix[j * 3 + k] - rotationMatrix[k * 3 + j]) * fRoot
            output[j] = (rotationMatrix[j * 3 + i] + rotationMatrix[i * 3 + j]) * fRoot
            output[k] = (rotationMatrix[k * 3 + i] + rotationMatrix[i * 3 + k]) * fRoot
        }
        return output
    }

    /**
     * Creates a quaternion from the given euler angle angleToRotateAroundX, angleToRotateAroundY, angleToRotateAroundZ.
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {angleToRotateAroundX} Angle to rotate around X axis in degrees.
     * @param {angleToRotateAroundY} Angle to rotate around Y axis in degrees.
     * @param {angleToRotateAroundZ} Angle to rotate around Z axis in degrees.
     * @returns {Quat} inOut
     * @function
     */
    fun fromEuler(angleToRotateAroundX: Double, angleToRotateAroundY: Double, angleToRotateAroundZ: Double): Quat {
        val output = Quat()
        val halfToRad = 0.5 * Math.PI / 180.0
        val angleX = angleToRotateAroundX * halfToRad
        val angleY = angleToRotateAroundY * halfToRad
        val angleZ = angleToRotateAroundZ * halfToRad
        val sx = Math.sin(angleX)
        val cx = Math.cos(angleX)
        val sy = Math.sin(angleY)
        val cy = Math.cos(angleY)
        val sz = Math.sin(angleZ)
        val cz = Math.cos(angleZ)
        output[0] = sx * cy * cz - cx * sy * sz
        output[1] = cx * sy * cz + sx * cy * sz
        output[2] = cx * cy * sz - sx * sy * cz
        output[3] = cx * cy * cz + sx * sy * sz
        return output
    }

    /**
     * Returns a string representation of a quatenion
     *
     * @param {Quat} a vector to represent as a string
     * @returns {String} string representation of the vector
     */
    override fun toString(): String {
        return "Quat(${this.quaternion[0]}, ${this.quaternion[1]}, ${this.quaternion[2]}, ${this.quaternion[3]})"
    }

    /**
     * Scales a Quat by a scalar number
     *
     * @param {Quat} out the receiving vector
     * @param {Quat} a the vector to scale
     * @param {Number} b amount to scale the vector by
     * @returns {Quat} out
     * @function
     */
    fun scale(amountToScaleBy: Double): Quat {
        val output = Quat()
        output[0] = this.quaternion[0] * amountToScaleBy
        output[1] = this.quaternion[1] * amountToScaleBy
        output[2] = this.quaternion[2] * amountToScaleBy
        output[3] = this.quaternion[3] * amountToScaleBy
        return output
    }

    /**
     * Calculates the dot product of two Quat's
     *
     * @param {Quat} a the first operand
     * @param {Quat} b the second operand
     * @returns {Number} dot product of a and b
     * @function
     */
    fun dot(operand: Quat): Double {
        return this.quaternion[0] * operand[0] + this.quaternion[1] * operand[1] + this.quaternion[2] * operand[2]
    }

    /**
     * Performs a linear interpolation between two Quat's
     *
     * @param {Quat} out the receiving quaternion
     * @param {Quat} a the first operand
     * @param {Quat} b the second operand
     * @param {Number} t interpolation amount between the two inputs
     * @returns {Quat} out
     * @function
     */
    fun lerp(operand: Quat, interpolationAmount: Double): Quat {
        val output = Quat()
        val ax = this.quaternion[0]
        val ay = this.quaternion[1]
        val az = this.quaternion[2]
        val aw = this.quaternion[3]
        output[0] = ax + interpolationAmount * (operand[0] - ax)
        output[1] = ay + interpolationAmount * (operand[1] - ay)
        output[2] = az + interpolationAmount * (operand[2] - az)
        output[3] = aw + interpolationAmount * (operand[3] - aw)
        return output
    }

    /**
     * Calculates the length of a Quat
     *
     * @param {Quat} a vector to calculate length of
     * @returns {Number} length of a
     */
    fun length(): Double {
        val x = this.quaternion[0]
        val y = this.quaternion[1]
        val z = this.quaternion[2]
        val w = this.quaternion[3]
        return Math.sqrt(x * x + y * y + z * z + w * w)
    }

    /**
     * Calculates the squared length of a Quat
     *
     * @param {Quat} a vector to calculate squared length of
     * @returns {Number} squared length of a
     * @function
     */
    fun squaredLength(): Double {
        val x = this.quaternion[0]
        val y = this.quaternion[1]
        val z = this.quaternion[2]
        val w = this.quaternion[3]
        return x * x + y * y + z * z + w * w
    }

    /**
     * Normalize a Quat
     *
     * @param {Quat} out the receiving quaternion
     * @param {Quat} a quaternion to normalize
     * @returns {Quat} out
     * @function
     */
    fun normalize(): Quat {
        val output = Quat()
        val x = this.quaternion[0]
        val y = this.quaternion[1]
        val z = this.quaternion[2]
        val w = this.quaternion[3]
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
     * Returns whether or not the quaternions have exactly the same elements in the same position (when compared with ===)
     *
     * @param {Quat} a The first quaternion.
     * @param {Quat} b The second quaternion.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun exactEquals(quaternion: Quat): Boolean {
        return this.quaternion[0] == quaternion[0] && this.quaternion[1] == quaternion[1] && this.quaternion[2] == quaternion[2] && this.quaternion[3] == quaternion[3]
    }

    /**
     * Returns whether or not the quaternions have approximately the same elements in the same position.
     *
     * @param {Quat} a The first vector.
     * @param {Quat} b The second vector.
     * @returns {Boolean} True if the vectors are equal, false otherwise.
     */
    fun equals(quaternion: Quat): Boolean {
        val a0 = this.quaternion[0]
        val a1 = this.quaternion[1]
        val a2 = this.quaternion[2]
        val a3 = this.quaternion[3]
        val b0 = quaternion[0]
        val b1 = quaternion[1]
        val b2 = quaternion[2]
        val b3 = quaternion[3]
        return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
    }


    /**
     * Sets a quaternion to represent the shortest rotation from one
     * vector to another.
     *
     * Both vectors are assumed to be unit length.
     *
     * @param {Quat} out the receiving quaternion.
     * @param {Vec3} a the initial vector
     * @param {Vec3} b the destination vector
     * @returns {Quat} out
     */
    fun rotationTo(initialVec3: Vec3, destinationVec3: Vec3): Quat {
        val output = Quat()
        var tmpvec3: Vec3
        val xUnitVec3 = Vec3(1.0, 0.0, 0.0)
        val yUnitVec3 = Vec3(0.0, 1.0, 0.0)
        val dot = initialVec3.dot(destinationVec3)
        if (dot < -0.999999) {
            tmpvec3 = xUnitVec3.cross(initialVec3)
            if (tmpvec3.length() < 0.000001)
                tmpvec3 = yUnitVec3.cross(initialVec3).normalize()
            output.setAxisAngle(tmpvec3, Math.PI)
            return output
        } else if (dot > 0.999999) {
            output[0] = 0.0
            output[1] = 0.0
            output[2] = 0.0
            output[3] = 1.0
            return output
        } else {
            tmpvec3 = initialVec3.cross(destinationVec3)
            output[0] = tmpvec3[0]
            output[1] = tmpvec3[1]
            output[2] = tmpvec3[2]
            output[3] = 1 + dot
            return output.normalize()
        }
    }

    /**
     * Performs firstOperand spherical linear interpolation with two control points
     *
     * @param {Quat} inOut the receiving quaternion
     * @param {Quat} firstOperand the first operand
     * @param {Quat} firstOperand the second operand
     * @param {Quat} secondOperand the third operand
     * @param {Quat} thirdOperand the fourth operand
     * @param {Number} interpolationAmount interpolation amount
     * @returns {Quat} inOut
     */


    fun sqlerp(firstOperand: Quat, secondOperand: Quat, thirdOperand: Quat, interpolationAmount: Double): Quat {
        val temp1 = firstOperand.slerp(thirdOperand, interpolationAmount)
        val temp2 = firstOperand.slerp(secondOperand, interpolationAmount)
        return temp1.slerp(temp2, 2 * interpolationAmount * (1 - interpolationAmount))
    }

    /**
     * Sets the specified quaternion with values corresponding to the given
     * axes. Each axis is a Vec3 and is expected to be unit length and
     * perpendicular to all other specified axes.
     *
     * @param {Vec3} viewDirection  the vector representing the viewing direction
     * @param {Vec3} rightDirection the vector representing the local "rightDirection" direction
     * @param {Vec3} upDirection    the vector representing the local "upDirection" direction
     * @returns {Quat} inOut
     */
    fun setAxes(viewDirection: Vec3, rightDirection: Vec3, upDirection: Vec3): Quat {
        val output = Quat()
        output[0] = rightDirection[0]
        output[3] = rightDirection[1]
        output[6] = rightDirection[2]
        output[1] = upDirection[0]
        output[4] = upDirection[1]
        output[7] = upDirection[2]
        output[2] = -viewDirection[0]
        output[5] = -viewDirection[1]
        output[8] = -viewDirection[2]
        return output.fromMat3(output.toDoubleArray()).normalize()
    }

    fun toDoubleArray(): DoubleArray {
        val output = DoubleArray(4)
        output[0] = this.quaternion[0]
        output[1] = this.quaternion[1]
        output[2] = this.quaternion[2]
        output[3] = this.quaternion[3]
        return output
    }
}