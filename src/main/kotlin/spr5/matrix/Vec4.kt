package spr5.matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import kotlin.js.Math

class Vec4 : GlMatrix() {
    companion object {
        /**
         * 4 Dimensional Vector
         * @module Vec4
         */
        /**
         * Creates a new, empty Vec4
         *
         * @returns {Vec4} a new 4D vector
         */
        fun create(): Array<Double> {
            return arrayOf(
                    0.0,
                    0.0,
                    0.0,
                    0.0
            )
        }

        /**
         * Creates vec4ToClone new Vec4 initialized with values from an existing vector
         *
         * @param {Vec4} vec4ToClone vector to clone
         * @returns {Vec4} vec4ToClone new 4D vector
         */
        fun clone(vec4ToClone: Array<Double>): Array<Double> {
            return arrayOf(
                    vec4ToClone[0],
                    vec4ToClone[1],
                    vec4ToClone[2],
                    vec4ToClone[0]
            )
        }

        /**
         * Creates a new Vec4 initialized with the given values
         *
         * @param {Number} componentX X component
         * @param {Number} componentY Y component
         * @param {Number} componentZ Z component
         * @param {Number} componentW W component
         * @returns {Vec4} a new 4D vector
         */
        fun fromValues(componentX: Double, componentY: Double, componentZ: Double, componentW: Double): Array<Double> {
            return arrayOf(
                    componentX,
                    componentY,
                    componentZ,
                    componentW
            )
        }

        /**
         * Copy the values from one Vec4 to another
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} toCopy the source vector
         * @returns {Vec4} inOut
         */
        fun copy(inOut: Array<Double>, toCopy: Array<Double>): Array<Double> {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            return inOut
        }

        /**
         * Set the components of a Vec4 to the given values
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Number} x X component
         * @param {Number} y Y component
         * @param {Number} z Z component
         * @param {Number} w W component
         * @returns {Vec4} inOut
         */
        fun set(inOut: Array<Double>, componentX: Double, componentY: Double, componentZ: Double, componentW: Double): Array<Double> {
            inOut[0] = componentX
            inOut[1] = componentY
            inOut[2] = componentZ
            inOut[3] = componentW
            return inOut
        }

        /**
         * Adds two Vec4's
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} firstSummand the first operand
         * @param {Vec4} secondSummand the second operand
         * @returns {Vec4} inOut
         */
        fun add(inOut: Array<Double>, firstSummand: Array<Double>, secondSummand: Array<Double>): Array<Double> {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            return inOut
        }

        /**
         * Subtracts vector subtrahend from vector minuend
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} minuend the first operand
         * @param {Vec4} subtrahend the second operand
         * @returns {Vec4} inOut
         */
        fun subtract(inOut: Array<Double>, minuend: Array<Double>, subtrahend: Array<Double>): Array<Double> {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            return inOut
        }

        /**
         * Multiplies two Vec4's
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} multiplier the first operand
         * @param {Vec4} multiplicand the second operand
         * @returns {Vec4} inOut
         */
        fun multiply(inOut: Array<Double>, multiplier: Array<Double>, multiplicand: Array<Double>): Array<Double> {
            inOut[0] = multiplier[0] * multiplicand[0]
            inOut[1] = multiplier[1] * multiplicand[1]
            inOut[2] = multiplier[2] * multiplicand[2]
            inOut[3] = multiplier[3] * multiplicand[3]
            return inOut
        }

        /**
         * Divides two Vec4's
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} dividend the first operand
         * @param {Vec4} divisor the second operand
         * @returns {Vec4} inOut
         */
        fun divide(inOut: Array<Double>, dividend: Array<Double>, divisor: Array<Double>): Array<Double> {
            inOut[0] = dividend[0] / divisor[0]
            inOut[1] = dividend[1] / divisor[1]
            inOut[2] = dividend[2] / divisor[2]
            inOut[3] = dividend[3] / divisor[3]
            return inOut
        }

        /**
         * Math.ceil the components of vec4ToCeil Vec4
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToCeil vector to ceil
         * @returns {Vec4} inOut
         */
        fun ceil(inOut: Array<Double>, vec4ToCeil: Array<Double>): Array<Double> {
            inOut[0] = (Math.ceil(vec4ToCeil[0])).toDouble()
            inOut[1] = (Math.ceil(vec4ToCeil[1])).toDouble()
            inOut[2] = (Math.ceil(vec4ToCeil[2])).toDouble()
            inOut[3] = (Math.ceil(vec4ToCeil[3])).toDouble()
            return inOut
        }

        /**
         * Math.floor the components of vec4ToFloor Vec4
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToFloor vector to floor
         * @returns {Vec4} inOut
         */
        fun floor(inOut: Array<Double>, vec4ToFloor: Array<Double>): Array<Double> {
            inOut[0] = (Math.floor(vec4ToFloor[0])).toDouble()
            inOut[1] = (Math.floor(vec4ToFloor[1])).toDouble()
            inOut[2] = (Math.floor(vec4ToFloor[2])).toDouble()
            inOut[3] = (Math.floor(vec4ToFloor[3])).toDouble()
            return inOut
        }

        /**
         * Returns the minimum of two Vec4's
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} firstOperand the first operand
         * @param {Vec4} secondOperand the second operand
         * @returns {Vec4} inOut
         */
        fun min(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
            inOut[0] = Math.min(firstOperand[0], secondOperand[0])
            inOut[1] = Math.min(firstOperand[1], secondOperand[1])
            inOut[2] = Math.min(firstOperand[2], secondOperand[2])
            inOut[3] = Math.min(firstOperand[3], secondOperand[3])
            return inOut
        }

        /**
         * Returns the maximum of two Vec4's
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} firstOperand the first operand
         * @param {Vec4} secondOperand the second operand
         * @returns {Vec4} inOut
         */
        fun max(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>): Array<Double> {
            inOut[0] = Math.max(firstOperand[0], secondOperand[0])
            inOut[1] = Math.max(firstOperand[1], secondOperand[1])
            inOut[2] = Math.max(firstOperand[2], secondOperand[2])
            inOut[3] = Math.max(firstOperand[3], secondOperand[3])
            return inOut
        }

        /**
         * Math.round the components of vec4ToRound Vec4
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToRound vector to round
         * @returns {Vec4} inOut
         */
        fun round(inOut: Array<Double>, vec4ToRound: Array<Double>): Array<Double> {
            inOut[0] = (Math.round(vec4ToRound[0])).toDouble()
            inOut[1] = (Math.round(vec4ToRound[1])).toDouble()
            inOut[2] = (Math.round(vec4ToRound[2])).toDouble()
            inOut[3] = (Math.round(vec4ToRound[3])).toDouble()
            return inOut
        }

        /**
         * Scales vec4ToScale Vec4 by vec4ToScale scalar number
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToScale the vector to scale
         * @param {Number} amountToScaleBy amount to scale the vector by
         * @returns {Vec4} inOut
         */
        fun scale(inOut: Array<Double>, vec4ToScale: Array<Double>, amountToScaleBy: Double): Array<Double> {
            inOut[0] = vec4ToScale[0] * amountToScaleBy
            inOut[1] = vec4ToScale[1] * amountToScaleBy
            inOut[2] = vec4ToScale[2] * amountToScaleBy
            inOut[3] = vec4ToScale[3] * amountToScaleBy
            return inOut
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
        fun scaleAndAdd(inOut: Array<Double>, firstSummand: Array<Double>, secondSummand: Array<Double>, amountToScale: Double): Array<Double> {
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScale)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScale)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScale)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScale)
            return inOut
        }

        /**
         * Calculates the euclidian distance between two Vec4's
         *
         * @param {Vec4} firstOperand the first operand
         * @param {Vec4} secondOperand the second operand
         * @returns {Number} distance between firstOperand and secondOperand
         */
        fun distance(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
            val x = secondOperand[0] - firstOperand[0]
            val y = secondOperand[1] - firstOperand[1]
            val z = secondOperand[2] - firstOperand[2]
            val w = secondOperand[3] - firstOperand[3]
            return Math.sqrt(x * x + y * y + z * z + w * w)
        }

        /**
         * Calculates the squared euclidian distance between two Vec4's
         *
         * @param {Vec4} firstOperand the first operand
         * @param {Vec4} secondOperand the second operand
         * @returns {Number} squared distance between firstOperand and secondOperand
         */
        fun squaredDistance(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
            val x = secondOperand[0] - firstOperand[0]
            val y = secondOperand[1] - firstOperand[1]
            val z = secondOperand[2] - firstOperand[2]
            val w = secondOperand[3] - firstOperand[3]
            return x * x + y * y + z * z + w * w
        }

        /**
         * Calculates the length of vec4ToCalculateLengthOf Vec4
         *
         * @param {Vec4} vec4ToCalculateLengthOf vector to calculate length of
         * @returns {Number} length of vec4ToCalculateLengthOf
         */
        fun length(vec4ToCalculateLengthOf: Array<Double>): Double {
            val x = vec4ToCalculateLengthOf[0]
            val y = vec4ToCalculateLengthOf[1]
            val z = vec4ToCalculateLengthOf[2]
            val w = vec4ToCalculateLengthOf[3]
            return Math.sqrt(x * x + y * y + z * z + w * w)
        }

        /**
         * Calculates the squared length of vec4ToCalculateSquaredLength Vec4
         *
         * @param {Vec4} vec4ToCalculateSquaredLength vector to calculate squared length of
         * @returns {Number} squared length of vec4ToCalculateSquaredLength
         */
        fun squaredLength(vec4ToCalculateSquaredLength: Array<Double>): Double {
            val x = vec4ToCalculateSquaredLength[0]
            val y = vec4ToCalculateSquaredLength[1]
            val z = vec4ToCalculateSquaredLength[2]
            val w = vec4ToCalculateSquaredLength[3]
            return x * x + y * y + z * z + w * w
        }

        /**
         * Negates the components of vec4ToNegate Vec4
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToNegate vector to negate
         * @returns {Vec4} inOut
         */
        fun negate(inOut: Array<Double>, vec4ToNegate: Array<Double>): Array<Double> {
            inOut[0] = -vec4ToNegate[0]
            inOut[1] = -vec4ToNegate[1]
            inOut[2] = -vec4ToNegate[2]
            inOut[3] = -vec4ToNegate[3]
            return inOut
        }

        /**
         * Returns the inverse of the components of vec4ToInvert Vec4
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToInvert vector to invert
         * @returns {Vec4} inOut
         */
        fun inverse(inOut: Array<Double>, vec4ToInvert: Array<Double>): Array<Double> {
            inOut[0] = 1.0 / vec4ToInvert[0]
            inOut[1] = 1.0 / vec4ToInvert[1]
            inOut[2] = 1.0 / vec4ToInvert[2]
            inOut[3] = 1.0 / vec4ToInvert[3]
            return inOut
        }

        /**
         * Normalize vec4ToNormalize Vec4
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToNormalize vector to normalize
         * @returns {Vec4} inOut
         */
        fun normalize(inOut: Array<Double>, vec4ToNormalize: Array<Double>): Array<Double> {
            val x = vec4ToNormalize[0]
            val y = vec4ToNormalize[1]
            val z = vec4ToNormalize[2]
            val w = vec4ToNormalize[3]
            var len = x * x + y * y + z * z + w * w
            if (len > 0) {
                len = 1 / Math.sqrt(len)
                inOut[0] = x * len
                inOut[1] = y * len
                inOut[2] = z * len
                inOut[3] = w * len
            }
            return inOut
        }

        /**
         * Calculates the dot product of two Vec4's
         *
         * @param {Vec4} firstOperand the first operand
         * @param {Vec4} secondOperand the second operand
         * @returns {Number} dot product of firstOperand and secondOperand
         */
        fun dot(firstOperand: Array<Double>, secondOperand: Array<Double>): Double {
            return firstOperand[0] * secondOperand[0] + firstOperand[1] * secondOperand[1] + firstOperand[2] * secondOperand[2] + firstOperand[3] * secondOperand[3]
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
        fun lerp(inOut: Array<Double>, firstOperand: Array<Double>, secondOperand: Array<Double>, interpolationAmount: Double): Array<Double> {
            val ax = firstOperand[0]
            val ay = firstOperand[1]
            val az = firstOperand[2]
            val aw = firstOperand[3]
            inOut[0] = ax + interpolationAmount * (secondOperand[0] - ax)
            inOut[1] = ay + interpolationAmount * (secondOperand[1] - ay)
            inOut[2] = az + interpolationAmount * (secondOperand[2] - az)
            inOut[3] = aw + interpolationAmount * (secondOperand[3] - aw)
            return inOut
        }

        /**
         * Generates a random vector with the given scale
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Number} [vectorScale] Length of the resulting vector. If ommitted, a unit vector will be returned
         * @returns {Vec4} inOut
         */
        fun random(inOut: Array<Double>, vectorScale: Double = 1.0): Array<Double> {
            //TODO: This is a pretty awful way of doing this. Find something better.
            inOut[0] = RANDOM
            inOut[1] = RANDOM
            inOut[2] = RANDOM
            inOut[3] = RANDOM
            normalize(inOut, inOut)
            scale(inOut, inOut, vectorScale)
            return inOut
        }

        /**
         * Transforms the Vec4 with a Mat4.
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} a the vector to transform
         * @param {Mat4} m matrix to transform with
         * @returns {Vec4} inOut
         */
        fun transformMat4(inOut: Array<Double>, vec4ToTransform: Array<Double>, mat4ToTransformWith: Float32Array): Array<Double> {
            val x = vec4ToTransform[0]
            val y = vec4ToTransform[1]
            val z = vec4ToTransform[2]
            val w = vec4ToTransform[3]
            inOut[0] = mat4ToTransformWith[0] * x + mat4ToTransformWith[4] * y + mat4ToTransformWith[8] * z + mat4ToTransformWith[12] * w
            inOut[1] = mat4ToTransformWith[1] * x + mat4ToTransformWith[5] * y + mat4ToTransformWith[9] * z + mat4ToTransformWith[13] * w
            inOut[2] = mat4ToTransformWith[2] * x + mat4ToTransformWith[6] * y + mat4ToTransformWith[10] * z + mat4ToTransformWith[14] * w
            inOut[3] = mat4ToTransformWith[3] * x + mat4ToTransformWith[7] * y + mat4ToTransformWith[11] * z + mat4ToTransformWith[15] * w
            return inOut
        }

        /**
         * Transforms the Vec4 with vec4ToTransform Quat
         *
         * @param {Vec4} inOut the receiving vector
         * @param {Vec4} vec4ToTransform the vector to transform
         * @param {Quat} quatToTransformWith quaternion to transform with
         * @returns {Vec4} inOut
         */
        fun transformQuat(inOut: Array<Double>, vec4ToTransform: Array<Double>, quatToTransformWith: Array<Double>): Array<Double> {
            val x = vec4ToTransform[0]
            val y = vec4ToTransform[1]
            val z = vec4ToTransform[2]
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
            inOut[0] = ix * qw + iw * -qx + iy * -qz - iz * -qy
            inOut[1] = iy * qw + iw * -qy + iz * -qx - ix * -qz
            inOut[2] = iz * qw + iw * -qz + ix * -qy - iy * -qx
            inOut[3] = vec4ToTransform[3]
            return inOut
        }

        /**
         * Returns a string representation of a vector
         *
         * @param {Vec4} a vector to represent as a string
         * @returns {String} string representation of the vector
         */
        fun toString(vector: Float32Array): String {
            return "Vec4(${vector[0]}, ${vector[1]}, ${vector[2]}, ${vector[3]})"
        }

        /**
         * Returns whether or not the vectors have exactly the same elements in the same position (when compared with ===)
         *
         * @param {Vec4} firstVector The first vector.
         * @param {Vec4} secondVector The second vector.
         * @returns {Boolean} True if the vectors are equal, false otherwise.
         */
        fun exactEquals(firstVector: Array<Double>, secondVector: Array<Double>): Boolean {
            return firstVector[0] == secondVector[0] && firstVector[1] == secondVector[1] && firstVector[2] == secondVector[2] && firstVector[3] == secondVector[3];
        }

        /**
         * Returns whether or not the vectors have approximately the same elements in the same position.
         *
         * @param {Vec4} firstVector The first vector.
         * @param {Vec4} secondVector The second vector.
         * @returns {Boolean} True if the vectors are equal, false otherwise.
         */
        fun equals(firstVector: Array<Double>, secondVector: Array<Double>): Boolean {
            val a0 = firstVector[0]
            val a1 = firstVector[1]
            val a2 = firstVector[2]
            val a3 = firstVector[3]
            val b0 = secondVector[0]
            val b1 = secondVector[1]
            val b2 = secondVector[2]
            val b3 = secondVector[3]
            return (Math.abs(a0 - b0) <= EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
        }
    }
}