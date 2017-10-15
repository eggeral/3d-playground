package matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class quat : glMatrix() {
    companion object {
        /**
         * Quaternion
         * @module quat
         */
        /**
         * Creates a new identity quat
         *
         * @returns {quat} a new quaternion
         */
        fun create(): Array<Double> {
            return arrayOf(
                    0.0,
                    0.0,
                    0.0,
                    1.0
            )
        }

        /**
         * Set a quat to the identity quaternion
         *
         * @param {quat} inOut the receiving quaternion
         * @returns {quat} inOut
         */
        fun identity(inOut: Array<Double>): Array<Double> {
            inOut[0] = 0.0
            inOut[1] = 0.0
            inOut[2] = 0.0
            inOut[3] = 1.0
            return inOut
        }

        /**
         * Sets a quat from the given angle and rotation axisToRotateAround,
         * then returns it.
         *
         * @param {quat} inOut the receiving quaternion
         * @param {vec3} axisToRotateAround the axisToRotateAround around which to rotate
         * @param {Number} angleInRad the angle in radians
         * @returns {quat} inOut
         **/
        fun setAxisAngle(inOut: Array<Double>, axisToRotateAround: Array<Double>, angleInRad: Double): Array<Double> {
            val rad = angleInRad * 0.5
            val s = Math.sin(rad)
            inOut[0] = s * axisToRotateAround[0]
            inOut[1] = s * axisToRotateAround[1]
            inOut[2] = s * axisToRotateAround[2]
            inOut[3] = Math.cos(rad)
            return inOut
        }

        /**
         * Gets the rotation axis and angle for a given
         *  quaternion. If a quaternion is created with
         *  setAxisAngle, this method will return the same
         *  values as providied in the original parameter list
         *  OR functionally equivalent values.
         * Example: The quaternion formed by axis [0, 0, 1] and
         *  angle -90 is the same as the quaternion formed by
         *  [0, 0, 1] and 270. This method favors the latter.
         * @param  {vec3} inOutAxis  Vector receiving the axis of rotation
         * @param  {quat} quadToBeDecomposed     Quaternion to be decomposed
         * @return {Number}     Angle, in radians, of the rotation
         */
        fun getAxisAngle(inOutAxis: Array<Double>, quadToBeDecomposed: Array<Double>): Double {
            val rad = Math.acos(quadToBeDecomposed[3]) * 2.0
            val s = Math.sin(rad / 2.0)
            if (s != 0.0) {
                inOutAxis[0] = quadToBeDecomposed[0] / s
                inOutAxis[1] = quadToBeDecomposed[1] / s
                inOutAxis[2] = quadToBeDecomposed[2] / s
            } else {
                // If s is zero, return any axis (no rotation - axis does not matter)
                inOutAxis[0] = 1.0
                inOutAxis[1] = 0.0
                inOutAxis[2] = 0.0
            }
            return rad
        }

        /**
         * Multiplies two quat's
         *
         * @param {quat} inOut the receiving quaternion
         * @param {quat} multiplier the first operand
         * @param {quat} multiplicand the second operand
         * @returns {quat} inOut
         */
        fun multiply(inOut: Array<Double>, multiplier: Array<Double>, multiplicand: Array<Double>): Array<Double> {
            val ax = multiplier[0]
            val ay = multiplier[1]
            val az = multiplier[2]
            val aw = multiplier[3]
            val bx = multiplicand[0]
            val by = multiplicand[1]
            val bz = multiplicand[2]
            val bw = multiplicand[3]
            inOut[0] = ax * bw + aw * bx + ay * bz - az * by
            inOut[1] = ay * bw + aw * by + az * bx - ax * bz
            inOut[2] = az * bw + aw * bz + ax * by - ay * bx
            inOut[3] = aw * bw - ax * bx - ay * by - az * bz
            return inOut
        }

        /**
         * Rotates quatToRotate quaternion by the given angle about the X axis
         *
         * @param {quat} inOut quat receiving operation result
         * @param {quat} quatToRotate quat to rotate
         * @param {number} angleInRad angle (in radians) to rotate
         * @returns {quat} inOut
         */
        fun rotateX(inOut: Array<Double>, quatToRotate: Array<Double>, angleInRad: Double): Array<Double> {
            val rad = angleInRad * 0.5
            val ax = quatToRotate[0]
            val ay = quatToRotate[1]
            val az = quatToRotate[2]
            val aw = quatToRotate[3]
            val bx = Math.sin(rad)
            val bw = Math.cos(rad)
            inOut[0] = ax * bw + aw * bx
            inOut[1] = ay * bw + az * bx
            inOut[2] = az * bw - ay * bx
            inOut[3] = aw * bw - ax * bx
            return inOut
        }

        /**
         * Rotates quatToRotate quaternion by the given angle about the Y axis
         *
         * @param {quat} inOut quat receiving operation result
         * @param {quat} quatToRotate quat to rotate
         * @param {number} angleInRad angle (in radians) to rotate
         * @returns {quat} inOut
         */
        fun rotateY(inOut: Array<Double>, quatToRotate: Array<Double>, angleInRad: Double): Array<Double> {
            val rad = angleInRad * 0.5
            val ax = quatToRotate[0]
            val ay = quatToRotate[1]
            val az = quatToRotate[2]
            val aw = quatToRotate[3]
            val by = Math.sin(rad)
            val bw = Math.cos(rad)
            inOut[0] = ax * bw - az * by
            inOut[1] = ay * bw + aw * by
            inOut[2] = az * bw + ax * by
            inOut[3] = aw * bw - ay * by
            return inOut
        }

        /**
         * Rotates quatToRotate quaternion by the given angle about the Z axis
         *
         * @param {quat} inOut quat receiving operation result
         * @param {quat} quatToRotate quat to rotate
         * @param {number} angleInRad angle (in radians) to rotate
         * @returns {quat} inOut
         */
        fun rotateZ(inOut: Array<Double>, quatToRotate: Array<Double>, angleInRad: Double): Array<Double> {
            val rad = angleInRad * 0.5
            val ax = quatToRotate[0]
            val ay = quatToRotate[1]
            val az = quatToRotate[2]
            val aw = quatToRotate[3]
            val bz = Math.sin(rad)
            val bw = Math.cos(rad)
            inOut[0] = ax * bw + ay * bz
            inOut[1] = ay * bw - ax * bz
            inOut[2] = az * bw + aw * bz
            inOut[3] = aw * bw - az * bz
            return inOut
        }

        /**
         * Calculates the W component of quadToCalculateW quat from the X, Y, and Z components.
         * Assumes that quaternion is 1 unit in length.
         * Any existing W component will be ignored.
         *
         * @param {quat} inOut the receiving quaternion
         * @param {quat} quadToCalculateW quat to calculate W component of
         * @returns {quat} inOut
         */
        fun calculateW(inOut: Array<Double>, quadToCalculateW: Array<Double>): Array<Double> {
            val x = quadToCalculateW[0]
            val y = quadToCalculateW[1]
            val z = quadToCalculateW[2]
            inOut[0] = x
            inOut[1] = y
            inOut[2] = z
            inOut[3] = Math.sqrt(Math.abs(1.0 - x * x - y * y - z * z))
            return inOut
        }

        /**
         * Performs firstOperand spherical linear interpolation between two quat
         *
         * @param {quat} inOut the receiving quaternion
         * @param {quat} firstOperand the first operand
         * @param {quat} secondOperand the second operand
         * @param {Number} interpolationAmount interpolation amount between the two inputs
         * @returns {quat} inOut
         */
        fun slerp(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
            // benchmarks:
            //    http://jsperf.com/quaternion-slerp-implementations
            val ax = firstOperand[0]
            val ay = firstOperand[1]
            val az = firstOperand[2]
            val aw = firstOperand[3]
            var bx = secondOperand[0]
            var by = secondOperand[1]
            var bz = secondOperand[2]
            var bw = secondOperand[3]
            var cosom: Double
            val scale0: Double
            val scale1: Double
            // calc cosine
            cosom = ax * bx + ay * by + az * bz + aw * bw;
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
            inOut[0] = scale0 * ax + scale1 * bx
            inOut[1] = scale0 * ay + scale1 * by
            inOut[2] = scale0 * az + scale1 * bz
            inOut[3] = scale0 * aw + scale1 * bw
            return inOut
        }

        /**
         * Calculates the inverse of quatToCalculateInverseOf quat
         *
         * @param {quat} inOut the receiving quaternion
         * @param {quat} quatToCalculateInverseOf quat to calculate inverse of
         * @returns {quat} inOut
         */
        fun invert(inOut: Array<Double>, quatToCalculateInverseOf: Array<Double>): Array<Double> {
            val a0 = quatToCalculateInverseOf[0]
            val a1 = quatToCalculateInverseOf[1]
            val a2 = quatToCalculateInverseOf[2]
            val a3 = quatToCalculateInverseOf[3]
            val dot = a0 * a0 + a1 * a1 + a2 * a2 + a3 * a3
            if (dot == 0.0) {
                inOut[0] = 0.0
                inOut[1] = 0.0
                inOut[2] = 0.0
                inOut[3] = 0.0
            } else {
                val invDot = 1.0 / dot
                inOut[0] = -a0 * invDot
                inOut[1] = -a1 * invDot
                inOut[2] = -a2 * invDot
                inOut[3] = a3 * invDot
            }
            return inOut
        }

        /**
         * Calculates the conjugate of quatToCalculateConjugateOf quat
         * If the quaternion is normalized, this function is faster than quat.inverse and produces the same result.
         *
         * @param {quat} inOut the receiving quaternion
         * @param {quat} quatToCalculateConjugateOf quat to calculate conjugate of
         * @returns {quat} inOut
         */
        fun conjugate(inOut: Array<Double>, quatToCalculateConjugateOf: Array<Double>): Array<Double> {
            inOut[0] = -quatToCalculateConjugateOf[0]
            inOut[1] = -quatToCalculateConjugateOf[1]
            inOut[2] = -quatToCalculateConjugateOf[2]
            inOut[3] = quatToCalculateConjugateOf[3]
            return inOut
        }

        /**
         * Creates a quaternion from the given 3x3 rotation matrix.
         *
         * NOTE: The resultant quaternion is not normalized, so you should be sure
         * to renormalize the quaternion yourself where necessary.
         *
         * @param {quat} inOut the receiving quaternion
         * @param {mat3} rotationMatrix rotation matrix
         * @returns {quat} inOut
         * @function
         */
        fun fromMat3(inOut: Array<Double>, rotationMatrix: Float32Array): Array<Double> {
            // Algorithm in Ken Shoemake's article in 1987 SIGGRAPH course notes
            // article "Quaternion Calculus and Fast Animation".
            var fRoot: Double
            val fTrace = rotationMatrix[0] + rotationMatrix[4] + rotationMatrix[8]
            if (fTrace > 0.0) {
                // |w| > 1/2, may as well choose w > 1/2
                fRoot = Math.sqrt(fTrace + 1.0)  // 2w
                inOut[3] = 0.5 * fRoot
                fRoot = 0.5 / fRoot  // 1/(4w)
                inOut[0] = (rotationMatrix[5] - rotationMatrix[7]) * fRoot
                inOut[1] = (rotationMatrix[6] - rotationMatrix[2]) * fRoot
                inOut[2] = (rotationMatrix[1] - rotationMatrix[3]) * fRoot
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
                inOut[i] = 0.5 * fRoot
                fRoot = 0.5 / fRoot
                inOut[3] = (rotationMatrix[j * 3 + k] - rotationMatrix[k * 3 + j]) * fRoot
                inOut[j] = (rotationMatrix[j * 3 + i] + rotationMatrix[i * 3 + j]) * fRoot
                inOut[k] = (rotationMatrix[k * 3 + i] + rotationMatrix[i * 3 + k]) * fRoot
            }
            return inOut
        }

        /**
         * Creates a quaternion from the given euler angle angleToRotateAroundX, angleToRotateAroundY, angleToRotateAroundZ.
         *
         * @param {quat} inOut the receiving quaternion
         * @param {angleToRotateAroundX} Angle to rotate around X axis in degrees.
         * @param {angleToRotateAroundY} Angle to rotate around Y axis in degrees.
         * @param {angleToRotateAroundZ} Angle to rotate around Z axis in degrees.
         * @returns {quat} inOut
         * @function
         */
        fun fromEuler(inOut: Array<Double>, angleToRotateAroundX: Double, angleToRotateAroundY: Double, angleToRotateAroundZ: Double): Array<Double> {
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
            inOut[0] = sx * cy * cz - cx * sy * sz
            inOut[1] = cx * sy * cz + sx * cy * sz
            inOut[2] = cx * cy * sz - sx * sy * cz
            inOut[3] = cx * cy * cz + sx * sy * sz
            return inOut
        }

        /**
         * Returns a string representation of a quatenion
         *
         * @param {quat} a vector to represent as a string
         * @returns {String} string representation of the vector
         */
        fun toString(matrix: Array<Double>): String {
            return "quat(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        /**
         * Creates a new quat initialized with values from an existing quaternion
         *
         * @param {quat} a quaternion to clone
         * @returns {quat} a new quaternion
         * @function
         */
        fun clone(matrixToClone: Array<Double>): Array<Double> {
            return vec4.clone(matrixToClone)

        }

        /**
         * Creates a new quat initialized with the given values
         *
         * @param {Number} x X component
         * @param {Number} y Y component
         * @param {Number} z Z component
         * @param {Number} w W component
         * @returns {quat} a new quaternion
         * @function
         */
        fun fromValues(componentX: Double, componentY: Double, componentZ: Double, componentW: Double): Array<Double> {
            return vec4.fromValues(componentX, componentY, componentZ, componentW)
        }

        /**
         * Copy the values from one quat to another
         *
         * @param {quat} out the receiving quaternion
         * @param {quat} a the source quaternion
         * @returns {quat} out
         * @function
         */
        fun copy(inOut: Array<Double>, toCopy: Array<Double>): Array<Double> {
            return vec4.copy(inOut, toCopy)
        }

        /**
         * Set the components of a quat to the given values
         *
         * @param {quat} out the receiving quaternion
         * @param {Number} x X component
         * @param {Number} y Y component
         * @param {Number} z Z component
         * @param {Number} w W component
         * @returns {quat} out
         * @function
         */
        fun set(inOut: Array<Double>, componentX: Double, componentY: Double, componentZ: Double, componentW: Double): Array<Double> {
            return vec4.set(inOut, componentX, componentY, componentZ, componentW)
        }

        /**
         * Adds two quat's
         *
         * @param {quat} out the receiving quaternion
         * @param {quat} a the first operand
         * @param {quat} b the second operand
         * @returns {quat} out
         * @function
         */
        fun add(inOut: Array<Double>, firstSummand: Array<Double>, secondSummand: Array<Double>): Array<Double> {
            return vec4.add(inOut, firstSummand, secondSummand)
        }

        /**
         * Scales a quat by a scalar number
         *
         * @param {quat} out the receiving vector
         * @param {quat} a the vector to scale
         * @param {Number} b amount to scale the vector by
         * @returns {quat} out
         * @function
         */
        fun scale(inOut: Array<Double>, matrixToScale: Array<Double>, vec4ToScaleBy: Double): Array<Double> {
            return vec4.scale(inOut, matrixToScale, vec4ToScaleBy)
        }

        /**
         * Calculates the dot product of two quat's
         *
         * @param {quat} a the first operand
         * @param {quat} b the second operand
         * @returns {Number} dot product of a and b
         * @function
         */
        fun dot(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
            return vec4.dot(firstOperand, secondOperand)
        }

        /**
         * Performs a linear interpolation between two quat's
         *
         * @param {quat} out the receiving quaternion
         * @param {quat} a the first operand
         * @param {quat} b the second operand
         * @param {Number} t interpolation amount between the two inputs
         * @returns {quat} out
         * @function
         */
        fun lerp(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
            return vec4.lerp(inOut, firstOperand, secondOperand, interpolationAmount)
        }

        /**
         * Calculates the length of a quat
         *
         * @param {quat} a vector to calculate length of
         * @returns {Number} length of a
         */
        fun length(quatToCalculateLengthOf: Array<Double>): Double {
            return vec4.length(quatToCalculateLengthOf)
        }

        /**
         * Calculates the squared length of a quat
         *
         * @param {quat} a vector to calculate squared length of
         * @returns {Number} squared length of a
         * @function
         */
        fun squaredLength(quatToCalculateSquaredLength: Array<Double>): Double {
            return vec4.squaredLength(quatToCalculateSquaredLength)
        }

        /**
         * Normalize a quat
         *
         * @param {quat} out the receiving quaternion
         * @param {quat} a quaternion to normalize
         * @returns {quat} out
         * @function
         */
        fun normalize(inOut: Array<Double>, quatToNormalize: Array<Double>): Array<Double> {
            return vec4.normalize(inOut, quatToNormalize)
        }

        /**
         * Returns whether or not the quaternions have exactly the same elements in the same position (when compared with ===)
         *
         * @param {quat} a The first quaternion.
         * @param {quat} b The second quaternion.
         * @returns {Boolean} True if the vectors are equal, false otherwise.
         */
        fun exactEquals(firstMatrix: Array<Double>, secondMatrix: Array<Double>): Boolean {
            return vec4.exactEquals(firstMatrix, secondMatrix)
        }

        /**
         * Returns whether or not the quaternions have approximately the same elements in the same position.
         *
         * @param {quat} a The first vector.
         * @param {quat} b The second vector.
         * @returns {Boolean} True if the vectors are equal, false otherwise.
         */
        fun equals(firstMatrix: Array<Double>, secondMatrix: Array<Double>): Boolean {
            return vec4.equals(firstMatrix, secondMatrix)
        }

        /**
         * Sets a quaternion to represent the shortest rotation from one
         * vector to another.
         *
         * Both vectors are assumed to be unit length.
         *
         * @param {quat} out the receiving quaternion.
         * @param {vec3} a the initial vector
         * @param {vec3} b the destination vector
         * @returns {quat} out
         */
        fun rotationTo(inOut: Array<Double>, initialVec3: Array<Double>, destinationVec3: Array<Double>): Array<Double> {
            val tmpvec3 = vec3.create()
            val xUnitVec3 = vec3.fromValues(1.0, 0.0, 0.0)
            val yUnitVec3 = vec3.fromValues(0.0, 1.0, 0.0)
            var dot = vec3.dot(initialVec3, destinationVec3)
            if (dot < -0.999999) {
                vec3.cross(tmpvec3, xUnitVec3, initialVec3)
                if (vec3.length(tmpvec3) < 0.000001)
                    vec3.cross(tmpvec3, yUnitVec3, initialVec3)
                vec3.normalize(tmpvec3, tmpvec3)
                setAxisAngle(inOut, tmpvec3, Math.PI)
                return inOut
            } else if (dot > 0.999999) {
                inOut[0] = 0.0
                inOut[1] = 0.0
                inOut[2] = 0.0
                inOut[3] = 1.0
                return inOut
            } else {
                vec3.cross(tmpvec3, initialVec3, destinationVec3);
                inOut[0] = tmpvec3[0]
                inOut[1] = tmpvec3[1]
                inOut[2] = tmpvec3[2]
                inOut[3] = 1 + dot
                return normalize(inOut, inOut)
            }
        }

        /**
         * Performs firstOperand spherical linear interpolation with two control points
         *
         * @param {quat} inOut the receiving quaternion
         * @param {quat} firstOperand the first operand
         * @param {quat} secondOperand the second operand
         * @param {quat} thirdOperand the third operand
         * @param {quat} fourthOperand the fourth operand
         * @param {Number} interpolationAmount interpolation amount
         * @returns {quat} inOut
         */
        fun sqlerp(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, thirdOperand: Array<Double>, fourthOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
            val temp1 = create()
            val temp2 = create()
            slerp(temp1, firstOperand, fourthOperand, interpolationAmount)
            slerp(temp2, secondOperand, thirdOperand, interpolationAmount)
            slerp(inOut, temp1, temp2, 2 * interpolationAmount * (1 - interpolationAmount))
            return inOut

        }

        /**
         * Sets the specified quaternion with values corresponding to the given
         * axes. Each axis is a vec3 and is expected to be unit length and
         * perpendicular to all other specified axes.
         *
         * @param {vec3} viewDirection  the vector representing the viewing direction
         * @param {vec3} rightDirection the vector representing the local "rightDirection" direction
         * @param {vec3} upDirection    the vector representing the local "upDirection" direction
         * @returns {quat} inOut
         */
        fun setAxes(inOut: Array<Double>, viewDirection: Array<Double>, rightDirection: Array<Double>, upDirection: Array<Int>): Array<Double> {
            var matr = mat3.create()
            matr[0] = rightDirection[0].toFloat()
            matr[3] = rightDirection[1].toFloat()
            matr[6] = rightDirection[2].toFloat()
            matr[1] = upDirection[0].toFloat()
            matr[4] = upDirection[1].toFloat()
            matr[7] = upDirection[2].toFloat()
            matr[2] = -viewDirection[0].toFloat()
            matr[5] = -viewDirection[1].toFloat()
            matr[8] = -viewDirection[2].toFloat()
            return normalize(inOut, fromMat3(inOut, matr))
        }
    }
}