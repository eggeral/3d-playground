package spr5.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import kotlin.js.Math

class vec3 : glMatrix() {
    companion object {
        /**
         * 3 Dimensional Vector
         * @module vec3
         */
        /**
         * Creates a new, empty vec3
         *
         * @returns {vec3} a new 3D vector
         */
        fun create(): Array<Double> {
            return arrayOf(
                    0.0,
                    0.0,
                    0.0
            )
        }

        /**
         * Creates vec3ToClone new vec3 initialized with values from an existing vector
         *
         * @param {vec3} vec3ToClone vector to clone
         * @returns {vec3} vec3ToClone new 3D vector
         */
        fun clone(vec3ToClone: Array<Double>): Array<Double> {
            return arrayOf(
                    vec3ToClone[0],
                    vec3ToClone[1],
                    vec3ToClone[2]
            )
        }

        /**
         * Calculates the length of vec3ToCalculateLengthOf vec3
         *
         * @param {vec3} vec3ToCalculateLengthOf vector to calculate length of
         * @returns {Number} length of vec3ToCalculateLengthOf
         */
        fun length(vec3ToCalculateLengthOf: Array<Double>): Double {
            val x = vec3ToCalculateLengthOf[0]
            val y = vec3ToCalculateLengthOf[1]
            val z = vec3ToCalculateLengthOf[2]
            return Math.sqrt(x * x + y * y + z * z)
        }

        /**
         * Creates a new vec3 initialized with the given values
         *
         * @param {Number} componentX X component
         * @param {Number} componentY Y component
         * @param {Number} componentZ Z component
         * @returns {vec3} a new 3D vector
         */
        fun fromValues(componentX: Double, componentY: Double, componentZ: Double): Array<Double> {
            return arrayOf(
                    componentX,
                    componentY,
                    componentZ
            )
        }

        /**
         * Copy the values from one vec3 to another
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} source the source vector
         * @returns {vec3} inOut
         */
        fun copy(inOut: Array<Double>, source: Array<Double>): Array<Double> {
            inOut[0] = source[0]
            inOut[1] = source[1]
            inOut[2] = source[2]
            return inOut
        }

        /**
         * Set the components of a vec3 to the given values
         *
         * @param {vec3} inOut the receiving vector
         * @param {Number} componentX X component
         * @param {Number} componentY Y component
         * @param {Number} componentZ Z component
         * @returns {vec3} inOut
         */
        fun set(inOut: Array<Double>, componentX: Double, componentY: Double, componentZ: Double): Array<Double> {
            inOut[0] = componentX
            inOut[1] = componentY
            inOut[2] = componentZ
            return inOut
        }

        /**
         * Adds two vec3's
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstSummand the first operand
         * @param {vec3} secondSummand the second operand
         * @returns {vec3} inOut
         */
        fun add(inOut: Array<Double>, firstSummand: Array<Double>, secondSummand: Array<Double>): Array<Double> {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            return inOut
        }

        /**
         * Subtracts vector subtrahend from vector minuend
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} minuend the first operand
         * @param {vec3} subtrahend the second operand
         * @returns {vec3} inOut
         */
        fun subtract(inOut: Array<Double>, minuend: Array<Double>, subtrahend: Array<Double>): Array<Double> {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            return inOut
        }

        /**
         * Multiplies two vec3's
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} multiplier the first operand
         * @param {vec3} multiplicand the second operand
         * @returns {vec3} inOut
         */
        fun multiply(inOut: Array<Double>, multiplier: Array<Double>, multiplicand: Array<Double>): Array<Double> {
            inOut[0] = multiplier[0] * multiplicand[0]
            inOut[1] = multiplier[1] * multiplicand[1]
            inOut[2] = multiplier[2] * multiplicand[2]
            return inOut
        }

        /**
         * Divides two vec3's
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} dividend the first operand
         * @param {vec3} divisor the second operand
         * @returns {vec3} inOut
         */
        fun divide(inOut: Array<Double>, dividend: Array<Double>, divisor: Array<Double>): Array<Double> {
            inOut[0] = dividend[0] / divisor[0]
            inOut[1] = dividend[1] / divisor[1]
            inOut[2] = dividend[2] / divisor[2]
            return inOut
        }

        /**
         * Math.ceil the components of vec3ToCeil vec3
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToCeil vector to ceil
         * @returns {vec3} inOut
         */
        fun ceil(inOut: Array<Double>, vec3ToCeil: Array<Double>): Array<Double> {
            inOut[0] = (Math.ceil(vec3ToCeil[0])).toDouble()
            inOut[1] = (Math.ceil(vec3ToCeil[1])).toDouble()
            inOut[2] = (Math.ceil(vec3ToCeil[2])).toDouble()
            return inOut
        }

        /**
         * Math.floor the components of vec3ToFloor vec3
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToFloor vector to floor
         * @returns {vec3} inOut
         */
        fun floor(inOut: Array<Double>, vec3ToFloor: Array<Double>): Array<Double> {
            inOut[0] = (Math.floor(vec3ToFloor[0])).toDouble()
            inOut[1] = (Math.floor(vec3ToFloor[1])).toDouble()
            inOut[2] = (Math.floor(vec3ToFloor[2])).toDouble()
            return inOut
        }

        /**
         * Returns the minimum of two vec3's
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @returns {vec3} inOut
         */
        fun min(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
            inOut[0] = Math.min(firstOperand[0], secondOperand[0])
            inOut[1] = Math.min(firstOperand[1], secondOperand[1])
            inOut[2] = Math.min(firstOperand[2], secondOperand[2])
            return inOut
        }

        /**
         * Returns the maximum of two vec3's
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @returns {vec3} inOut
         */
        fun max(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
            inOut[0] = Math.max(firstOperand[0], secondOperand[0])
            inOut[1] = Math.max(firstOperand[1], secondOperand[1])
            inOut[2] = Math.max(firstOperand[2], secondOperand[2])
            return inOut
        }

        /**
         * Math.round the components of vec3ToRound vec3
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToRound vector to round
         * @returns {vec3} inOut
         */
        fun round(inOut: Array<Double>, vec3ToRound: Array<Double>): Array<Double> {
            inOut[0] = (Math.round(vec3ToRound[0])).toDouble()
            inOut[1] = (Math.round(vec3ToRound[1])).toDouble()
            inOut[2] = (Math.round(vec3ToRound[2])).toDouble()
            return inOut
        }

        /**
         * Scales vec3ToScale vec3 by vec3ToScale scalar number
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToScale the vector to scale
         * @param {Number} amountToScaleBy amount to scale the vector by
         * @returns {vec3} inOut
         */
        fun scale(inOut: Array<Double>, vec3ToScale: Array<Double>, amountToScaleBy: Double): Array<Double> {
            inOut[0] = vec3ToScale[0] * amountToScaleBy
            inOut[1] = vec3ToScale[1] * amountToScaleBy
            inOut[2] = vec3ToScale[2] * amountToScaleBy
            return inOut
        }

        /**
         * Adds two vec3's after scaling the second operand by firstSummand scalar value
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstSummand the first operand
         * @param {vec3} secondSummand the second operand
         * @param {Number} amountToScale the amount to amountToScale secondSummand by before adding
         * @returns {vec3} inOut
         */
        fun scaleAndAdd(inOut: Array<Double>, firstSummand: Array<Double>, secondSummand: Array<Double>, amountToScale: Double): Array<Double> {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScale)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScale)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScale)
            return inOut
        }

        /**
         * Calculates the euclidian distance between two vec3's
         *
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @returns {Number} distance between firstOperand and secondOperand
         */
        fun distance(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
            val x = secondOperand[0] - firstOperand[0]
            val y = secondOperand[1] - firstOperand[1]
            val z = secondOperand[2] - firstOperand[2]
            return Math.sqrt(x * x + y * y + z * z)
        }

        /**
         * Calculates the squared euclidian distance between two vec3's
         *
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @returns {Number} squared distance between firstOperand and secondOperand
         */
        fun squaredDistance(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
            val x = secondOperand[0] - firstOperand[0]
            val y = secondOperand[1] - firstOperand[1]
            val z = secondOperand[2] - firstOperand[2]
            return x * x + y * y + z * z
        }

        /**
         * Calculates the squared length of vec3ToCalculateSquaredLength vec3
         *
         * @param {vec3} vec3ToCalculateSquaredLength vector to calculate squared length of
         * @returns {Number} squared length of vec3ToCalculateSquaredLength
         */
        fun squaredLength(vec3ToCalculateSquaredLength: Array<Double>): Double {
            val x = vec3ToCalculateSquaredLength[0]
            val y = vec3ToCalculateSquaredLength[1]
            val z = vec3ToCalculateSquaredLength[2]
            return x * x + y * y + z * z
        }

        /**
         * Negates the components of vec3ToNegate vec3
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToNegate vector to negate
         * @returns {vec3} inOut
         */
        fun negate(inOut: Array<Double>, vec3ToNegate: Array<Double>): Array<Double> {
            inOut[0] = -vec3ToNegate[0]
            inOut[1] = -vec3ToNegate[1]
            inOut[2] = -vec3ToNegate[2]
            return inOut
        }

        /**
         * Returns the inverse of the components of vec3ToInvert vec3
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToInvert vector to invert
         * @returns {vec3} inOut
         */
        fun inverse(inOut: Array<Double>, vec3ToInvert: Array<Double>): Array<Double> {
            inOut[0] = 1.0 / vec3ToInvert[0]
            inOut[1] = 1.0 / vec3ToInvert[1]
            inOut[2] = 1.0 / vec3ToInvert[2]
            return inOut
        }

        /**
         * Normalize vec3ToNormalize vec3
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToNormalize vector to normalize
         * @returns {vec3} inOut
         */
        fun normalize(inOut: Array<Double>, vec3ToNormalize: Array<Double>): Array<Double> {
            val x = vec3ToNormalize[0]
            val y = vec3ToNormalize[1]
            val z = vec3ToNormalize[2]
            var len = x * x + y * y + z * z
            if (len > 0) {
                //TODO: evaluate use of glm_invsqrt here?
                len = 1 / Math.sqrt(len)
                inOut[0] = vec3ToNormalize[0] * len
                inOut[1] = vec3ToNormalize[1] * len
                inOut[2] = vec3ToNormalize[2] * len
            }
            return inOut
        }

        /**
         * Calculates the dot product of two vec3's
         *
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @returns {Number} dot product of firstOperand and secondOperand
         */
        fun dot(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
            return firstOperand[0] * secondOperand[0] + firstOperand[1] * secondOperand[1] + firstOperand[2] * secondOperand[2]
        }

        /**
         * Computes the cross product of two vec3's
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @returns {vec3} inOut
         */
        fun cross(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
            val ax = firstOperand[0]
            val ay = firstOperand[1]
            val az = firstOperand[2]
            val bx = secondOperand[0]
            val by = secondOperand[1]
            val bz = secondOperand[2]
            inOut[0] = ay * bz - az * by
            inOut[1] = az * bx - ax * bz
            inOut[2] = ax * by - ay * bx
            return inOut
        }

        /**
         * Performs a linear interpolation between two vec3's
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @param {Number} interpolationAmount interpolation amount between the two inputs
         * @returns {vec3} inOut
         */
        fun lerp(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
            val ax = firstOperand[0]
            val ay = firstOperand[1]
            val az = firstOperand[2]
            inOut[0] = ax + interpolationAmount * (secondOperand[0] - ax)
            inOut[1] = ay + interpolationAmount * (secondOperand[1] - ay)
            inOut[2] = az + interpolationAmount * (secondOperand[2] - az)
            return inOut
        }

        /**
         * Performs a hermite interpolation with two control points
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @param {vec3} thirdOperand the third operand
         * @param {vec3} fourthOperand the fourth operand
         * @param {Number} interpolationAmount interpolation amount between the two inputs
         * @returns {vec3} inOut
         */
        fun hermite(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, thirdOperand: Array<Double>, fourthOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
            val factorTimes2 = interpolationAmount * interpolationAmount
            val factor1 = factorTimes2 * (2 * interpolationAmount - 3) + 1
            val factor2 = factorTimes2 * (interpolationAmount - 2) + interpolationAmount
            val factor3 = factorTimes2 * (interpolationAmount - 1)
            val factor4 = factorTimes2 * (3 - 2 * interpolationAmount)
            inOut[0] = firstOperand[0] * factor1 + secondOperand[0] * factor2 + thirdOperand[0] * factor3 + fourthOperand[0] * factor4
            inOut[1] = firstOperand[1] * factor1 + secondOperand[1] * factor2 + thirdOperand[1] * factor3 + fourthOperand[1] * factor4
            inOut[2] = firstOperand[2] * factor1 + secondOperand[2] * factor2 + thirdOperand[2] * factor3 + fourthOperand[2] * factor4
            return inOut
        }

        /**
         * Performs a bezier interpolation with two control points
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} firstOperand the first operand
         * @param {vec3} secondOperand the second operand
         * @param {vec3} thirdOperand the third operand
         * @param {vec3} fourthOperand the fourth operand
         * @param {Number} interpolationAmount interpolation amount between the two inputs
         * @returns {vec3} inOut
         */
        fun bezier(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, thirdOperand: Array<Double>, fourthOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
            val inverseFactor = 1 - interpolationAmount
            val inverseFactorTimesTwo = inverseFactor * inverseFactor
            val factorTimes2 = interpolationAmount * interpolationAmount
            val factor1 = inverseFactorTimesTwo * inverseFactor
            val factor2 = 3 * interpolationAmount * inverseFactorTimesTwo
            val factor3 = 3 * factorTimes2 * inverseFactor
            val factor4 = factorTimes2 * interpolationAmount
            inOut[0] = firstOperand[0] * factor1 + secondOperand[0] * factor2 + thirdOperand[0] * factor3 + fourthOperand[0] * factor4
            inOut[1] = firstOperand[1] * factor1 + secondOperand[1] * factor2 + thirdOperand[1] * factor3 + fourthOperand[1] * factor4
            inOut[2] = firstOperand[2] * factor1 + secondOperand[2] * factor2 + thirdOperand[2] * factor3 + fourthOperand[2] * factor4
            return inOut
        }

        /**
         * Generates a random vector with the given vectorScale
         *
         * @param {vec3} inOut the receiving vector
         * @param {Number} [vectorScale] Length of the resulting vector. If ommitted, a unit vector will be returned
         * @returns {vec3} inOut
         */
        fun random(inOut: Array<Double>, vectorScale: Double = 1.0): Array<Double> {
            val r = RANDOM * 2.0 * Math.PI
            val z = (RANDOM * 2.0) - 1.0
            val zScale = Math.sqrt(1.0 - z * z) * vectorScale
            inOut[0] = Math.cos(r) * zScale
            inOut[1] = Math.sin(r) * zScale
            inOut[2] = z * vectorScale
            return inOut
        }

        /**
         * Transforms the vec3 with vec3ToTransform mat4.
         * 4th vector component is implicitly '1'
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToTransform the vector to transform
         * @param {mat4} mat4ToTransformWith matrix to transform with
         * @returns {vec3} inOut
         */
        fun transformMat4(inOut: Array<Double>, vec3ToTransform: Array<Double>, mat4ToTransformWith: Float32Array): Array<Double> {
            val x = vec3ToTransform[0]
            val y = vec3ToTransform[1]
            val z = vec3ToTransform[2]
            var w = mat4ToTransformWith[3] * x + mat4ToTransformWith[7] * y + mat4ToTransformWith[11] * z + mat4ToTransformWith[15]
            if (w < 0 || w > 1.0) {
                w = 1.0
            }
            inOut[0] = (mat4ToTransformWith[0] * x + mat4ToTransformWith[4] * y + mat4ToTransformWith[8] * z + mat4ToTransformWith[12]) / w
            inOut[1] = (mat4ToTransformWith[1] * x + mat4ToTransformWith[5] * y + mat4ToTransformWith[9] * z + mat4ToTransformWith[13]) / w
            inOut[2] = (mat4ToTransformWith[2] * x + mat4ToTransformWith[6] * y + mat4ToTransformWith[10] * z + mat4ToTransformWith[14]) / w
            return inOut
        }

        /**
         * Transforms the vec3 with vec3ToTransform mat3.
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToTransform the vector to transform
         * @param {mat3} mat3ToTransformWith the 3x3 matrix to transform with
         * @returns {vec3} inOut
         */
        fun transformMat3(inOut: Array<Double>, vec3ToTransform: Array<Double>, mat3ToTransformWith: Float32Array): Array<Double> {
            val x = vec3ToTransform[0]
            val y = vec3ToTransform[1]
            val z = vec3ToTransform[2]
            inOut[0] = x * mat3ToTransformWith[0] + y * mat3ToTransformWith[3] + z * mat3ToTransformWith[6]
            inOut[1] = x * mat3ToTransformWith[1] + y * mat3ToTransformWith[4] + z * mat3ToTransformWith[7]
            inOut[2] = x * mat3ToTransformWith[2] + y * mat3ToTransformWith[5] + z * mat3ToTransformWith[8]
            return inOut
        }

        /**
         * Transforms the vec3 with vec3ToTransform quat
         *
         * @param {vec3} inOut the receiving vector
         * @param {vec3} vec3ToTransform the vector to transform
         * @param {quat} quatToTransformWith quaternion to transform with
         * @returns {vec3} inOut
         */
        fun transformQuat(inOut: Array<Double>, vec3ToTransform: Array<Double>, quatToTransformWith: Array<Double>): Array<Double> {
            // benchmarks: http://jsperf.com/quaternion-transform-vec3-implementations
            val x = vec3ToTransform[0]
            val y = vec3ToTransform[1]
            val z = vec3ToTransform[2]
            val qx = quatToTransformWith[0]
            val qy = quatToTransformWith[1]
            val qz = quatToTransformWith[2]
            val qw = quatToTransformWith[3]
            // calculate quat * vec
            val ix = qw * x + qy * z - qz * y
            val iy = qw * y + qz * x - qx * z
            val iz = qw * z + qx * y - qy * x
            val iw = -qx * x - qy * y - qz * z
            // calculate result * inverse quat
            inOut[0] = ix * qw + iw * -qx + iy * -qz - iz * -qy
            inOut[1] = iy * qw + iw * -qy + iz * -qx - ix * -qz
            inOut[2] = iz * qw + iw * -qz + ix * -qy - iy * -qx
            return inOut
        }

        /**
         * Rotate vec3ToRotate 3D vector around the x-axis
         * @param {vec3} inOut The receiving vec3
         * @param {vec3} vec3ToRotate The vec3 point to rotate
         * @param {vec3} originOfRotation The origin of the rotation
         * @param {Number} angleInRad The angle of rotation
         * @returns {vec3} inOut
         */
        fun rotateX(inOut: Array<Double>, vec3ToRotate: Array<Double>, originOfRotation: Array<Double>, angleInRad: Double): Array<Double> {
            //Translate point to the origin
            val p = arrayOf(
                    (vec3ToRotate[0] - originOfRotation[0]),
                    (vec3ToRotate[1] - originOfRotation[1]),
                    (vec3ToRotate[2] - originOfRotation[2])
            )
            //perform rotation
            val r = arrayOf(
                    p[0],
                    (p[1] * Math.cos(angleInRad) - p[2] * Math.sin(angleInRad)),
                    (p[1] * Math.sin(angleInRad) + p[2] * Math.cos(angleInRad))
            )
            //translate to correct position
            inOut[0] = r[0] + originOfRotation[0]
            inOut[1] = r[1] + originOfRotation[1]
            inOut[2] = r[2] + originOfRotation[2]
            return inOut
        }

        /**
         * Rotate vec3ToRotate 3D vector around the y-axis
         * @param {vec3} inOut The receiving vec3
         * @param {vec3} vec3ToRotate The vec3 point to rotate
         * @param {vec3} originOfRotation The origin of the rotation
         * @param {Number} angleInRad The angle of rotation
         * @returns {vec3} inOut
         */
        fun rotateY(inOut: Array<Double>, vec3ToRotate: Array<Double>, originOfRotation: Array<Double>, angleInRad: Double): Array<Double> {
            //Translate point to the origin
            val p = arrayOf(
                    (vec3ToRotate[0] - originOfRotation[0]),
                    (vec3ToRotate[1] - originOfRotation[1]),
                    (vec3ToRotate[2] - originOfRotation[2])
            )
            //perform rotation
            val r = arrayOf(
                    (p[2] * Math.sin(angleInRad) + p[0] * Math.cos(angleInRad)),
                    p[1],
                    (p[2] * Math.cos(angleInRad) - p[0] * Math.sin(angleInRad))
            )
            //translate to correct position
            inOut[0] = r[0] + originOfRotation[0]
            inOut[1] = r[1] + originOfRotation[1]
            inOut[2] = r[2] + originOfRotation[2]
            return inOut
        }

        /**
         * Rotate vec3ToRotate 3D vector around the z-axis
         * @param {vec3} inOut The receiving vec3
         * @param {vec3} vec3ToRotate The vec3 point to rotate
         * @param {vec3} originOfRotation The origin of the rotation
         * @param {Number} angleInRad The angle of rotation
         * @returns {vec3} inOut
         */
        fun rotateZ(inOut: Array<Double>, vec3ToRotate: Array<Double>, originOfRotation: Array<Double>, angleInRad: Double): Array<Double> {
            //Translate point to the origin
            val p = arrayOf(
                    (vec3ToRotate[0] - originOfRotation[0]),
                    (vec3ToRotate[1] - originOfRotation[1]),
                    (vec3ToRotate[2] - originOfRotation[2])
            )
            //perform rotation
            val r = arrayOf(
                    (p[0] * Math.cos(angleInRad) - p[1] * Math.sin(angleInRad)),
                    (p[0] * Math.sin(angleInRad) + p[1] * Math.cos(angleInRad)),
                    p[2]
            )
            //translate to correct position
            inOut[0] = r[0] + originOfRotation[0]
            inOut[1] = r[1] + originOfRotation[1]
            inOut[2] = r[2] + originOfRotation[2]
            return inOut
        }

        /**
         * Get the angle between two 3D vectors
         * @param {vec3} firstVec3 The first operand
         * @param {vec3} secondVec3 The second operand
         * @returns {Number} The angle in radians
         */
        fun angle(firstVec3: Array<Double>, secondVec3: Array<Double>): Double {
            val tempA = fromValues(firstVec3[0], firstVec3[1], firstVec3[2])
            val tempB = fromValues(secondVec3[0], secondVec3[1], secondVec3[2])
            normalize(tempA, tempA)
            normalize(tempB, tempB)
            val cosine = dot(tempA, tempB)
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
         * @param {vec3} a vector to represent as a string
         * @returns {String} string representation of the vector
         */
        fun toString(vector: Array<Double>): String {
            return "vec3(${vector[0]}, ${vector[1]}, ${vector[2]})"
        }

        /**
         * Returns whether or not the vectors have exactly the same elements in the same position (when compared with ===)
         *
         * @param {vec3} firstVector The first vector.
         * @param {vec3} secondVector The second vector.
         * @returns {Boolean} True if the vectors are equal, false otherwise.
         */
        fun exactEquals(firstVector: Array<Double>, secondVector: Array<Double>): Boolean {
            return firstVector[0] == secondVector[0] && firstVector[1] == secondVector[1] && firstVector[2] == secondVector[2]
        }

        /**
         * Returns whether or not the vectors have approximately the same elements in the same position.
         *
         * @param {vec3} firstVector The first vector.
         * @param {vec3} secondVector The second vector.
         * @returns {Boolean} True if the vectors are equal, false otherwise.
         */
        fun equals(firstVector: Array<Double>, secondVector: Array<Double>): Boolean {
            val a0 = firstVector[0]
            val a1 = firstVector[1]
            val a2 = firstVector[2]
            val b0 = secondVector[0]
            val b1 = secondVector[1]
            val b2 = secondVector[2]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)))
        }
    }
}