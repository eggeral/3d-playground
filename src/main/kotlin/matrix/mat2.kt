package matrix

import org.khronos.webgl.Float32Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Math

class mat2 : glMatrix() {
    companion object {
        /**
         * 2x2 Matrix
         * @module mat2
         */
        /**
         * Creates a new identity mat2
         *
         * @returns {mat2} a new 2x2 matrix
         */
        fun create(): Float32Array {
            val out = Float32Array(4)
            out[0] = 1.0f
            out[1] = 0.0f
            out[2] = 0.0f
            out[3] = 1.0f
            return out
        }

        /**
         * Creates matrixToClone new mat2 initialized with values from an existing matrix
         *
         * @param {mat2} matrixToClone matrix to clone
         * @returns {mat2} matrixToClone new 2x2 matrix
         */
        fun clone(matrixToClone: Float32Array): Float32Array {
            val out = Float32Array(4)
            out[0] = matrixToClone[0]
            out[1] = matrixToClone[1]
            out[2] = matrixToClone[2]
            out[3] = matrixToClone[3]
            return out
        }

        fun clone(a: Array<Float>): Array<Float> {
            return arrayOf(a[0], a[1], a[2], a[3])
        }

        /**
         * Copy the values from one mat2 to another
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} toCopy the source matrix
         * @returns {mat2} out
         */
        fun copy(inOut: Float32Array, toCopy: Float32Array): Float32Array {
            inOut[0] = toCopy[0]
            inOut[1] = toCopy[1]
            inOut[2] = toCopy[2]
            inOut[3] = toCopy[3]
            return inOut
        }

        /**
         * Set a mat2 to the identity matrix
         *
         * @param {mat2} out the receiving matrix
         * @returns {mat2} out
         */
        fun identity(inOut: Float32Array): Float32Array {
            inOut[0] = 1.0f
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = 1.0f
            return inOut
        }

        /**
         * Create a new mat2 with the given values
         *
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m10 Component in column 1, row 0 position (index 2)
         * @param {Number} m11 Component in column 1, row 1 position (index 3)
         * @returns {mat2} out A new 2x2 matrix
         */
        fun fromValues(m00: Float, m01: Float, m10: Float, m11: Float): Float32Array {
            val out = Float32Array(4)
            out[0] = m00
            out[1] = m01
            out[2] = m10
            out[3] = m11
            return out
        }

        fun fromValues(m00: Double, m01: Double, m10: Double, m11: Double): Float32Array {
            val out = Float32Array(4)
            out[0] = m00.toFloat()
            out[1] = m01.toFloat()
            out[2] = m10.toFloat()
            out[3] = m11.toFloat()
            return out
        }

        /**
         * Set the components of a mat2 to the given values
         *
         * @param {mat2} out the receiving matrix
         * @param {Number} m00 Component in column 0, row 0 position (index 0)
         * @param {Number} m01 Component in column 0, row 1 position (index 1)
         * @param {Number} m10 Component in column 1, row 0 position (index 2)
         * @param {Number} m11 Component in column 1, row 1 position (index 3)
         * @returns {mat2} out
         */
        fun set(inOut: Float32Array, m00: Float, m01: Float, m10: Float, m11: Float): Float32Array {
            inOut[0] = m00
            inOut[1] = m01
            inOut[2] = m10
            inOut[3] = m11
            return inOut
        }

        fun set(inOut: Float32Array, m00: Double, m01: Double, m10: Double, m11: Double): Float32Array {
            inOut[0] = m00.toFloat()
            inOut[1] = m01.toFloat()
            inOut[2] = m10.toFloat()
            inOut[3] = m11.toFloat()
            return inOut
        }

        /**
         * Transpose the values of source mat2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} source the source matrix
         * @returns {mat2} out
         */
        fun transpose(inOut: Float32Array, source: Float32Array): Float32Array {
            // If we are transposing ourselves we can skip source few steps but have to cache
            // some values
            if (inOut === source) {
                val a1 = source[1]
                inOut[1] = source[2]
                inOut[2] = a1
            } else {
                inOut[0] = source[0]
                inOut[1] = source[2]
                inOut[2] = source[1]
                inOut[3] = source[3]
            }
            return inOut
        }

        /**
         * Inverts source mat2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} source the source matrix
         * @returns {mat2} out
         */
        fun invert(inOut: Float32Array, source: Float32Array): Float32Array {
            val a0 = source[0]
            val a1 = source[1]
            val a2 = source[2]
            val a3 = source[3]
            // Calculate the determinant
            var det = a0 * a3 - a2 * a1
            if (det < 0) {
                return Float32Array(0)
            }
            det = (1.0 / det).toFloat()
            inOut[0] = a3 * det
            inOut[1] = -a1 * det
            inOut[2] = -a2 * det
            inOut[3] = a0 * det
            return inOut
        }

        /**
         * Calculates the adjugate of source mat2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} source the source matrix
         * @returns {mat2} out
         */
        fun adjoint(inOut: Float32Array, source: Float32Array): Float32Array {
            // Caching this value is nessecary if out == source
            val a0 = source[0]
            inOut[0] = source[3]
            inOut[1] = -source[1]
            inOut[2] = -source[2]
            inOut[3] = a0
            return inOut
        }

        /**
         * Calculates the determinant of source mat2
         *
         * @param {mat2} source the source matrix
         * @returns {Number} determinant of source
         */
        fun determinant(source: Float32Array): Double {
            return (source[0] * source[3] - source[2] * source[1]).toDouble()
        }

        /**
         * Multiplies two mat2's
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} multiplier the first operand
         * @param {mat2} multiplicand the second operand
         * @returns {mat2} out
         */
        fun multiply(inOut: Float32Array, multiplier: Float32Array, multiplicand: Float32Array): Float32Array {
            val a0 = multiplier[0]
            val a1 = multiplier[1]
            val a2 = multiplier[2]
            val a3 = multiplier[3]
            val b0 = multiplicand[0]
            val b1 = multiplicand[1]
            val b2 = multiplicand[2]
            val b3 = multiplicand[3]
            inOut[0] = a0 * b0 + a2 * b1
            inOut[1] = a1 * b0 + a3 * b1
            inOut[2] = a0 * b2 + a2 * b3
            inOut[3] = a1 * b2 + a3 * b3
            return inOut
        }

        /**
         * Rotates matrixToRotate mat2 by the given angle
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} matrixToRotate the matrix to rotate
         * @param {Number} angleInRad the angle to rotate the matrix by
         * @returns {mat2} out
         */
        fun rotate(inOut: Float32Array, matrixToRotate: Float32Array, angleInRad: Double): Float32Array {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val s = (Math.sin(angleInRad)).toFloat()
            val c = (Math.cos(angleInRad)).toFloat()
            inOut[0] = a0 * c + a2 * s
            inOut[1] = a1 * c + a3 * s
            inOut[2] = a0 * -s + a2 * c
            inOut[3] = a1 * -s + a3 * c
            return inOut
        }

        /**
         * Scales the mat2 by the dimensions in the given vec2
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} matrixToRotate the matrix to rotate
         * @param {vec2} vec2ToScaleBy the vec2 to scale the matrix by
         * @returns {mat2} out
         **/
        fun scale(inOut: Float32Array, matrixToRotate: Float32Array, vec2ToScaleBy: Array<Double>): Float32Array {
            val a0 = matrixToRotate[0]
            val a1 = matrixToRotate[1]
            val a2 = matrixToRotate[2]
            val a3 = matrixToRotate[3]
            val v0 = vec2ToScaleBy[0].toFloat()
            val v1 = vec2ToScaleBy[1].toFloat()
            inOut[0] = a0 * v0
            inOut[1] = a1 * v0
            inOut[2] = a2 * v1
            inOut[3] = a3 * v1
            return inOut
        }

        /**
         * Creates a matrix from a given angle
         * This is equivalent to (but much faster than):
         *
         *     mat2.identity(dest);
         *     mat2.rotate(dest, dest, angleToRotateByInRad);
         *
         * @param {mat2} out mat2 receiving operation result
         * @param {Number} angleToRotateByInRad the angle to rotate the matrix by
         * @returns {mat2} out
         */
        fun fromRotation(inOut: Float32Array, angleToRotateByInRad: Double): Float32Array {
            val s = (Math.sin(angleToRotateByInRad)).toFloat()
            val c = (Math.cos(angleToRotateByInRad)).toFloat()
            inOut[0] = c
            inOut[1] = s
            inOut[2] = -s
            inOut[3] = c
            return inOut
        }

        /**
         * Creates a matrix from a vector scaling
         * This is equivalent to (but much faster than):
         *
         *     mat2.identity(dest);
         *     mat2.scale(dest, dest, vec);
         *
         * @param {mat2} out mat2 receiving operation result
         * @param {vec2} scalingVec2 Scaling vector
         * @returns {mat2} out
         */
        fun fromScaling(inOut: Float32Array, scalingVec2: Array<Double>): Float32Array {
            inOut[0] = scalingVec2[0].toFloat()
            inOut[1] = 0.0f
            inOut[2] = 0.0f
            inOut[3] = scalingVec2[1].toFloat()
            return inOut
        }

        /**
         * Returns L, D and U matrices (Lower triangular, Diagonal and Upper triangular) by factorizing the input matrix
         * @param {mat2} L the lower triangular matrix
         * @param {mat2} D the diagonal matrix
         * @param {mat2} U the upper triangular matrix
         * @param {mat2} a the input matrix to factorize
         */
        fun LDU(L: Float32Array, D: Float32Array, U: Float32Array, a: Float32Array): Triple<Float32Array, Float32Array, Float32Array> {
            L[2] = a[2] / a[0]
            U[0] = a[0]
            U[1] = a[1]
            U[3] = a[3] - L[2] * U[1]
            return Triple(L, D, U)
        }

        /**
         * Adds two mat2's
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} firstSummand the first operand
         * @param {mat2} secondSummand the second operand
         * @returns {mat2} out
         */
        fun add(inOut: Float32Array, firstSummand: Float32Array, secondSummand: Float32Array): Float32Array {
            inOut[0] = firstSummand[0] + secondSummand[0]
            inOut[1] = firstSummand[1] + secondSummand[1]
            inOut[2] = firstSummand[2] + secondSummand[2]
            inOut[3] = firstSummand[3] + secondSummand[3]
            return inOut
        }

        /**
         * Subtracts matrix subtrahend from matrix minuend
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} minuend the first operand
         * @param {mat2} subtrahend the second operand
         * @returns {mat2} out
         */
        fun subtract(inOut: Float32Array, minuend: Float32Array, subtrahend: Float32Array): Float32Array {
            inOut[0] = minuend[0] - subtrahend[0]
            inOut[1] = minuend[1] - subtrahend[1]
            inOut[2] = minuend[2] - subtrahend[2]
            inOut[3] = minuend[3] - subtrahend[3]
            return inOut
        }

        /**
         * Returns whether or not the matrices have exactly the same elements in the same position (when compared with ===)
         *
         * @param {mat2} firstMatrix The first matrix.
         * @param {mat2} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun exactEquals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            return firstMatrix[0] == secondMatrix[0] && firstMatrix[1] == secondMatrix[1] && firstMatrix[2] == secondMatrix[2] && firstMatrix[3] == secondMatrix[3]
        }

        /**
         * Returns whether or not the matrices have approximately the same elements in the same position.
         *
         * @param {mat2} firstMatrix The first matrix.
         * @param {mat2} secondMatrix The second matrix.
         * @returns {Boolean} True if the matrices are equal, false otherwise.
         */
        fun equals(firstMatrix: Float32Array, secondMatrix: Float32Array): Boolean {
            val a0 = firstMatrix[0].toDouble()
            val a1 = firstMatrix[1].toDouble()
            val a2 = firstMatrix[2].toDouble()
            val a3 = firstMatrix[3].toDouble()
            val b0 = secondMatrix[0].toDouble()
            val b1 = secondMatrix[1].toDouble()
            val b2 = secondMatrix[2].toDouble()
            val b3 = secondMatrix[3].toDouble()
            return (Math.abs(a0 - b0) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a0), Math.abs(b0)) &&
                    Math.abs(a1 - b1) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a1), Math.abs(b1)) &&
                    Math.abs(a2 - b2) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a2), Math.abs(b2)) &&
                    Math.abs(a3 - b3) <= glMatrix.EPSILON * Math.max(1.0, Math.abs(a3), Math.abs(b3)))
        }

        /**
         * Multiply each element of the matrix by matrixToScale scalar.
         *
         * @param {mat2} out the receiving matrix
         * @param {mat2} matrixToScale the matrix to scale
         * @param {Number} amountToScaleBy amount to scale the matrix's elements by
         * @returns {mat2} out
         */
        fun multiplyScalar(inOut: Float32Array, matrixToScale: Float32Array, amountToScaleBy: Double): Float32Array {
            val amountToScaleTheMatrix = amountToScaleBy.toFloat()
            inOut[0] = matrixToScale[0] * amountToScaleTheMatrix
            inOut[1] = matrixToScale[1] * amountToScaleTheMatrix
            inOut[2] = matrixToScale[2] * amountToScaleTheMatrix
            inOut[3] = matrixToScale[3] * amountToScaleTheMatrix
            return inOut
        }

        /**
         * Adds two mat2's after multiplying each element of the second operand by firstSummand scalar value.
         *
         * @param {mat2} out the receiving vector
         * @param {mat2} firstSummand the first operand
         * @param {mat2} secondSummand the second operand
         * @param {Number} amountToScale the amount to amountToScale secondSummand's elements by before adding
         * @returns {mat2} out
         */
        fun multiplyScalarAndAdd(inOut: Float32Array, firstSummand: Float32Array, secondSummand: Float32Array, amountToScale: Double): Float32Array {
            val amountToScaleTheMatrix = amountToScale.toFloat()
            inOut[0] = firstSummand[0] + (secondSummand[0] * amountToScaleTheMatrix)
            inOut[1] = firstSummand[1] + (secondSummand[1] * amountToScaleTheMatrix)
            inOut[2] = firstSummand[2] + (secondSummand[2] * amountToScaleTheMatrix)
            inOut[3] = firstSummand[3] + (secondSummand[3] * amountToScaleTheMatrix)
            return inOut
        }

        /**
         * Returns a string representation of a mat2
         *
         * @param {mat3} a matrix to represent as a string
         * @returns {String} string representation of the matrix
         */
        fun toString(matrix: Float32Array): String {
            return "mat2(${matrix[0]}, ${matrix[1]}, ${matrix[2]}, ${matrix[3]})"
        }

        /**
         * Returns Frobenius norm of matrix mat2
         *
         * @param {mat2} matrix the matrix to calculate Frobenius norm of
         * @returns {Number} Frobenius norm
         */
        fun frob(matrix: Float32Array): Double {
            return (Math.sqrt(Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(matrix[1].toDouble(), 2.0)
                    + Math.pow(matrix[2].toDouble(), 2.0) + Math.pow(matrix[3].toDouble(), 2.0)))
        }

    }
}